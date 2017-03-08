package ims_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ims_user.IMS_User;

/**
 * @author ciunas
 *
 */
public class IMS_Server_DataBaseAccess implements IMS_Server_DataAccess {

	// reference to database connection
	private Connection connection;

	// reference to prepared statement for locating entry
	private PreparedStatement sqlInsertUser;
	private PreparedStatement sqlReturnUser;
	private PreparedStatement sqlReturnLabels;
	private PreparedStatement sqlUpdateStatusIp;
	private PreparedStatement sqlReturnOnlineUser;
	private PreparedStatement sqlReturnIfUserExists;
	private PreparedStatement sqlChangeStatus;

	/**
	 * DataBaseAccess
	 * 
	 * Set up PreparedStatements to access database.
	 * 
	 * @throws Exception
	 */
	public IMS_Server_DataBaseAccess() throws Exception {

		connect(); // connect to addressbook database

		sqlInsertUser = connection.prepareStatement("INSERT INTO `users`(`userName`,`launguage`, `password`, `salt`, `status`, `ip`) VALUES ( ? , ? , ?, ?, ?, ? )");
		
		sqlUpdateStatusIp = connection.prepareStatement("UPDATE `users` SET status = ( ? ), ip = ( ? ) WHERE userName = ( ? )");
		
		sqlReturnUser = connection.prepareStatement("SELECT * FROM `users` WHERE userName = ( ? )");
		
		sqlReturnLabels = connection.prepareStatement("SELECT * FROM `translations` WHERE launguage = ( ? )");
		
		sqlReturnOnlineUser = connection.prepareStatement("SELECT `userName`, `ip` FROM `users` WHERE status = ( ? )");

		sqlReturnIfUserExists = connection.prepareStatement("SELECT `userName` FROM `users` WHERE `userName` = ( ? )");
		
		sqlChangeStatus = connection.prepareStatement("UPDATE `users` SET status = ( ? ) WHERE userName = ( ? )");
	} 
	
	/**
	 * connect.
	 * 
	 * Obtain a connection to database.
	 * 
	 * @throws Exception
	 */
	private void connect() throws Exception {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.122.228:3306/IMSUsers?autoReconnect=true&useSSL=false"; //URL to connect to database.	
        Class.forName(driver);
        connection = DriverManager.getConnection(url, "ciunas", "1");
        connection.setAutoCommit(false);
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see ims_server.IMS_Server_DataAccess#changeStatus(ims_user.IMS_User)
	 */
	@Override
	public boolean changeStatus(String username, int status ) {
		
		//Insert new IP and Change status of User to active.
		int result = 0;	
		
		try {
			sqlChangeStatus.setInt(1, status);
			sqlChangeStatus.setString(2, username);
			
			result = sqlChangeStatus.executeUpdate();
			
			if (result == 0) { // if insert fails, rollback and discontinue
				try {
					connection.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				} // rollback insert
				System.out.println("failure updateing data");

			}else{
	            connection.commit();   // commit update
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	
		
		
		
		
		return true;
	}

	
	
   
	/* (non-Javadoc)
	 * @see ims_server.IMS_Server_DataAccess#newUser(ims_user.IMS_User)
	 */
	@Override
	public boolean newUser(IMS_User user) {

		try {

			
			sqlReturnIfUserExists.setString(1, user.getName());
			ResultSet resultSets = sqlReturnIfUserExists.executeQuery();

			if (!resultSets.next())
				System.out.println("User Does not exist");
			else {
			
				return false;
			}
			
			int result;			
			// Insert name, launguage, password, status and IP in DB
			sqlInsertUser.setString(1, user.getName());
			sqlInsertUser.setString(2, user.getLaunguage());
			sqlInsertUser.setBytes(3, user.getPassword());
			sqlInsertUser.setBytes(4, user.getSalt());
			sqlInsertUser.setInt(5, user.getStatus());
			sqlInsertUser.setString(6, user.getIp());
			
			result = sqlInsertUser.executeUpdate();

			if (result == 0) { // if insert fails, rollback and discontinue
				connection.rollback(); // rollback insert
				System.out.println("failure");
				return false; // insert unsuccessful

			}

            System.out.println("connection cmmmited");
            connection.commit();   // commit update
            return true;           // update successful

		} catch (SQLException sqlException) {
			try {
				connection.rollback(); // rollback update
				return false; // update unsuccessful
			} catch (SQLException exception) {
			}
		}
		return true;
	}
	
	
 
	/* (non-Javadoc)
	 * @see ims_server.IMS_Server_DataAccess#returnUser(ims_user.IMS_User)
	 */
	@Override
	public IMS_User returnUser(IMS_User user) {

		IMS_User userReturned;
		
		try {
			
			sqlReturnIfUserExists.setString(1, user.getName());
			ResultSet resultSets = sqlReturnIfUserExists.executeQuery();

			if (!resultSets.next())
				return null;
			else {
				System.out.println("User exists");
				
			}
				
			//Insert new IP and Change status of User to active.
			int result;			
			sqlUpdateStatusIp.setInt(1, user.getStatus());
			sqlUpdateStatusIp.setString(2, user.getIp());
			sqlUpdateStatusIp.setString(3, user.getName());
			
			result = sqlUpdateStatusIp.executeUpdate();

			if (result == 0) { // if insert fails, rollback and discontinue
				connection.rollback(); // rollback insert
				System.out.println("failure updateing data");

			}else{
	            connection.commit();   // commit update
			}
			
			
			userReturned  =  returnOnlineUser(user);

			// Search for user with username and get details;
			sqlReturnUser.setString(1, user.getName());

			ResultSet resultSet = sqlReturnUser.executeQuery();

			if (!resultSet.next())
				return null;
			else {
				
				// get name, launguage, password, status and IP in DB
				userReturned.setName(resultSet.getString(2));
				userReturned.setLaunguage(resultSet.getString(3));
				userReturned.setPassword(resultSet.getBytes(4));
				userReturned.setSalt(resultSet.getBytes(5));
				userReturned.setStatus(resultSet.getInt(6));
				userReturned.setIp(resultSet.getString(7));
			}
			
			// Search for launguage labels
			System.out.println(userReturned.getLaunguage());
			sqlReturnLabels.setString(1, userReturned.getLaunguage());

			ResultSet resultSet1 = sqlReturnLabels.executeQuery();

			if (!resultSet1.next())
				return null;
			else {
				
				userReturned.getLabels().add(resultSet1.getString(1));
				userReturned.getLabels().add(resultSet1.getString(2));
				userReturned.getLabels().add(resultSet1.getString(3));
				userReturned.getLabels().add(resultSet1.getString(4));
				userReturned.getLabels().add(resultSet1.getString(5));
				userReturned.getLabels().add(resultSet1.getString(6));
				userReturned.getLabels().add(resultSet1.getString(7));
				userReturned.getLabels().add(resultSet1.getString(8));
				userReturned.getLabels().add(resultSet1.getString(9));
				userReturned.getLabels().add(resultSet1.getString(10));
			}
			

		} 
		catch (SQLException sqlException) {
			return null;
		}	
		
		return userReturned;
	}
	
	
		/* (non-Javadoc)
		 * @see ims_server.IMS_Server_DataAccess#returnOnlineUser(ims_user.IMS_User)
		 */
		@Override
		public IMS_User returnOnlineUser(IMS_User user) {

			IMS_User userReturned = new IMS_User();
			
			try {
						
				
				// Search for launguage labels
				System.out.println("Return Online users");
				sqlReturnOnlineUser.setInt(1, 1);
				ResultSet resultSet2 = sqlReturnOnlineUser.executeQuery();

				if (!resultSet2.next())
					return null;
				else {
					
				     do {
				    	 System.out.println(resultSet2.getString(2));
				    		userReturned.getOnlineUsers().add(resultSet2.getString(1));
							userReturned.getOnlineUsers().add(resultSet2.getString(2));
		                } while (resultSet2.next());
									
				}

			} 
			catch (SQLException sqlException) {
				return null;
			}	
			
			return userReturned;
		}
	
  
	
	
	/* (non-Javadoc)
	 * @see ims_server.IMS_Server_DataAccess#close()
	 */
	@Override
	public void close() {

		try {
			sqlInsertUser.close();
			sqlReturnUser.close();
			sqlReturnLabels.close();
	      	sqlUpdateStatusIp.close();
        	sqlReturnOnlineUser.close();
            connection.close();
        } 

        // detect problems closing statements and connection
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    // Method to clean up database connection. Provided in case
    // CloudscapeDataAccess object is garbage collected.
    protected void finalize() {
        close();
    }



}
