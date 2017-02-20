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
	
	public  UserNew_Authenticate() {
	
	}
	
	public IMS_User getCredentials(BufferedReader bReader, PrintWriter dataOut) throws IOException {
		
	

		String temp;
		while (authent == false) {
			
			if ((temp = bReader.readLine()).compareTo("Start Authentication") == 0) {
				
				dataOut.println("Authentication started");
				dataOut.flush();
				userName = bReader.readLine();
				password = bReader.readLine();
				newNotnew = bReader.readLine();
				
				if((temp = bReader.readLine()).compareTo("Data Sent") == 0){
					authent = true;
					System.out.println("User authenticated, username: " +userName + " Password: " + password + " New o not new: " + newNotnew);
				}				
			} else {
				temp = bReader.readLine();
			}
		}
		
		IMS_User user = new IMS_User(userName, password);
		
		     // create database connection
      try {
          database = new DataBaseAccess();
      }
      catch (Exception exception) {
          exception.printStackTrace();
          System.exit(1);
      }
		if(newNotnew.compareTo("New") == 0){
			System.out.println();
			database.newUser(user);
		}else
			database.newUser(user);
		
		return user;
	}
	

}
