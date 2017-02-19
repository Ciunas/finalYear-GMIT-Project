package ims_server;

import transalteIMS.IMS_User;

public interface DataAccess {
    
	   // Locate specified person by last name. Return 
	   // AddressBookEntry containing information.
	   //public ArrayList<AddressBookEntry> findPerson(String lastName);
	   
	   // Update information for specified person.
	   // Return boolean indicating success or failure.
	   //public boolean savePerson(AddressBookEntry person)

	   // Insert a new person. Return boolean indicating 
	   // success or failure.
	   public boolean newUser(IMS_User person);
	      
	   // Delete specified person. Return boolean indicating if 
	   // success or failure.
//	   public boolean deletePerson(
//	           AddressBookEntry person) throws DataAccessException;
	      
	   // close data source connection
	   public void close();

	   // close data source connection
	   //public int calculateEntries()throws SQLException;

	}  // end interface AddressBookDataAccess