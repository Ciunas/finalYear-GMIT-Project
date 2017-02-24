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
public class DataBaseAccess implements DataAccess {

	// reference to database connection
	private Connection connection;

	// reference to prepared statement for locating entry
	private PreparedStatement sqlInsertUser;
	private PreparedStatement sqlReturnUser;
	private PreparedStatement sqlReturnLabels;

	/**
	 * DataBaseAccess
	 * 
	 * Set up PreparedStatements to access database.
	 * 
	 * @throws Exception
	 */
	public DataBaseAccess() throws Exception {

		connect(); // connect to addressbook database

		sqlInsertUser = connection.prepareStatement("INSERT INTO `users`(`userName`,`launguage`, `password`, `salt`) VALUES ( ? , ? , ?, ?)");
		
		sqlReturnUser = connection.prepareStatement("SELECT * FROM `users` WHERE userName = ( ? )");
		
		sqlReturnLabels = connection.prepareStatement("SELECT * FROM `translations` WHERE launguage = ( ? )");

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
	
	
	
	
	
    /**
	 * newUser
	 * 
	 * @return
	 */
	public boolean newUser(IMS_User user) {

		try {

			
			int result;			
			// Insert name, launguage, and password in DB
			sqlInsertUser.setString(1, user.getName());
			sqlInsertUser.setString(2, user.getLaunguage());
			sqlInsertUser.setBytes(3, user.getPassword());
			sqlInsertUser.setBytes(4, user.getSalt());
			
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
	
	
    /**
	 * newUser
	 * 
	 * @return
	 */
	public IMS_User returnUser(IMS_User user) {

		IMS_User userReturned = new IMS_User();
		
		try {

			// Search for user with username
			sqlReturnUser.setString(1, user.getName());

			ResultSet resultSet = sqlReturnUser.executeQuery();

			if (!resultSet.next())
				return null;
			else {

				userReturned.setName(resultSet.getString(2));
				userReturned.setLaunguage(resultSet.getString(3));
				userReturned.setPassword(resultSet.getBytes(4));
				userReturned.setSalt(resultSet.getBytes(5));
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
			}

		} 
		catch (SQLException sqlException) {
			return null;
		}	
		
		return userReturned;
	}
	
	
  
	
	
	
	   // method to close statements and database connection
    public void close() {
    	
        try {
        	sqlInsertUser.close();
        	sqlReturnUser.close();
        	sqlReturnLabels.close();
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
