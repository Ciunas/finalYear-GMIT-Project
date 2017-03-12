package ims_server;

import ims_user.IMS_User;

public interface IMS_Server_DataAccess {
    
	
	/**
	 * returns a user object from a database
	 * @param user only containes username and password
	 * @return a user object containg all paramaters that are applicable(name, launguage, ip, status, translated labels)
	 */
	public IMS_User returnUser(IMS_User user);
	
	 
	/**
	 * changes the status of the users in databse
	 * @param user object containing name and new status
	 * @return boolean true user status offline false user online.
	 */
	public boolean changeStatus(String name, int status);

 
	/**
	 * add  a new user to the database 
	 * @param user Object containing name password launguage
	 * @return true if user added to databse, false if user not added to database
	 */
	public boolean newUser(IMS_User user);
 
	
	/**
	 * close the connection to database
	 */
	public void close();


	/**
	 * returns a list of all the users that are currently online
	 * @param user object containing name
	 * @return user object containing a list of all users online
	 */
	public IMS_User returnOnlineUser( );		

	} 