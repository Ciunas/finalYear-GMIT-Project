package ims_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import ims_user.IMS_User;

public class UserNew_Authenticate {

	private DataAccess database;
	private boolean authent = false;
	private String userName;
	private String password;
	private String newNotnew;
	private String launguage;

	public UserNew_Authenticate() {

	}

	public IMS_User getCredentials(BufferedReader bReader, PrintWriter dataOut) throws IOException {


		
		String temp;
		
		while (authent == false) {

			if ((temp = bReader.readLine()).compareTo("Start Authentication") == 0) {

				dataOut.println("Authentication started");
				dataOut.flush();
				userName = bReader.readLine();
				password = bReader.readLine();
				launguage = bReader.readLine();
				newNotnew = bReader.readLine();

				if ((temp = bReader.readLine()).compareTo("Data Sent") == 0) {
					authent = true;
					System.out.println("User authenticated, username: " + userName + " Password: " + password
							+ " New or not new: " + newNotnew + " Launguage: " + launguage);
				}
			} else {
				temp = bReader.readLine();
			}
		}
		IMS_User userCreate = new IMS_User(userName, password, launguage);
		IMS_User userReturned = new IMS_User();
		// create database connection
		try {
			database = new DataBaseAccess();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		if (newNotnew.compareTo("New") == 0) {

			database.newUser(userCreate);
		} else
			userReturned = database.returnUser(userCreate);

		return userReturned;
	}
	

}
