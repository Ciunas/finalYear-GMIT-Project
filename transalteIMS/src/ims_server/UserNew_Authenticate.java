package ims_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import ims_user.IMS_User;

public class UserNew_Authenticate {

    private DataAccess database;	
	
	public  UserNew_Authenticate() {
	
	}
	
	public IMS_User getCredentials(BufferedReader bReader, PrintWriter dataOut) throws IOException {
		
		IMS_User user = new IMS_User();

		try {
			database = new DataBaseAccess();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		String temp;
		if(temp.compareTo("Start Authentication") = bReader.readLine() == 0);
		

		return user;
	}
	

}
