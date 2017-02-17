package transalteIMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

	/**
	 * DataBaseAccess
	 * 
	 * Set up PreparedStatements to access database.
	 * 
	 * @throws Exception
	 */
	public DataBaseAccess() throws Exception {

		connect(); // connect to addressbook database

		sqlInsertUser = connection.prepareStatement("INSERT INTO `users`(`userName`, `password`) VALUES ( ? , ? )");
		
		//sqlReturnUser = connection.prepareStatement("SELECT MAX(personID) FROM users");

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
	        //String url = "jdbc:mysql://localhost:3306/IMSUsers?autoReconnect=true&useSSL=false";

		String url = "jdbc:mysql://192.168.122.228:3306/IMSUsers?autoReconnect=true&useSSL=false"; //URL to connect to database.	
        Class.forName(driver);
        connection = DriverManager.getConnection(url, "username", "password");
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
			// insert name and password in DB
			sqlInsertUser.setString(1, user.getName());
			sqlInsertUser.setString(2, user.getPassword());
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
	
	
	
	
	
	
	
//	
//    // Delete an entry. Method returns boolean indicating
//    // success or failure.
//    public boolean deleteUser()
//            throws DataAccessException {
//        // delete a person from database
//        try {
//            int result;
//
//            // delete address from addresses table
//            sqlDeleteAddress.setInt(1, person.getPersonID());
//            result = sqlDeleteAddress.executeUpdate();
//
//            // if delete fails, rollback and discontinue
//            if (result == 0) {
//                connection.rollback(); // rollback delete
//                return false;          // delete unsuccessful
//            }
//
//            // delete phone number from phoneNumbers table
//            sqlDeletePhone.setInt(1, person.getPersonID());
//            result = sqlDeletePhone.executeUpdate();
//
//            // if delete fails, rollback and discontinue
//            if (result == 0) {
//                connection.rollback(); // rollback delete
//                return false;          // delete unsuccessful
//            }
//
//            // delete email address from emailAddresses table
//            sqlDeleteEmail.setInt(1, person.getPersonID());
//            result = sqlDeleteEmail.executeUpdate();
//
//            // if delete fails, rollback and discontinue
//            if (result == 0) {
//                connection.rollback(); // rollback delete
//                return false;          // delete unsuccessful
//            }
//
//            // delete name from names table
//            sqlDeleteName.setInt(1, person.getPersonID());
//            result = sqlDeleteName.executeUpdate();
//
//            // if delete fails, rollback and discontinue
//            if (result == 0) {
//                connection.rollback(); // rollback delete
//                return false;          // delete unsuccessful
//            }
//
//            connection.commit();   // commit delete
//            System.out.println("commited to database");
//            return true;           // delete successful
//        }  // end try
//
//        // detect problems updating database
//        catch (SQLException sqlException) {
//            // rollback transaction
//            try {
//                System.out.println("connection roolbacked");
//                connection.rollback(); // rollback update
//                return false;          // update unsuccessful
//            }
//
//            // handle exception rolling back transaction
//            catch (SQLException exception) {
//               
//            }
//        }
//    }  // end method deletePerson
//	
//	
//	
//	
//	
//	
//	
	
	
	
	
	
	
	
	
	
	
	   // method to close statements and database connection
    public void close() {
        // close database connection
        try {
        	sqlInsertUser.close();
//            sqlPersonID.close();
//            sqlInsertName.close();
//            sqlInsertAddress.close();
//            sqlInsertPhone.close();
//            sqlInsertEmail.close();
//            sqlUpdateName.close();
//            sqlUpdateAddress.close();
//            sqlUpdatePhone.close();
//            sqlUpdateEmail.close();
//            sqlDeleteName.close();
//            sqlDeleteAddress.close();
//            sqlDeletePhone.close();
//            sqlDeleteEmail.close();
            connection.close();
        }  // end try

        // detect problems closing statements and connection
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }  // end method close
    
    // Method to clean up database connection. Provided in case
    // CloudscapeDataAccess object is garbage collected.
    protected void finalize() {
        close();
    }

//	@Override
//	public boolean newPerson(IMS_User person) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
