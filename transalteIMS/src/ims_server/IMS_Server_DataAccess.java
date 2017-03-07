package ims_server;

import ims_user.IMS_User;

public interface IMS_Server_DataAccess {
    
	// Return details as an User object
	public IMS_User returnUser(IMS_User user);

	// Insert a new Usser. Return boolean indicating
	// success or failure.
	public boolean newUser(IMS_User user);

	// close data source connection
	public void close();		

	} 