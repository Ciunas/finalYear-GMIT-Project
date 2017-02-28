package ims_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import ims_user.IMS_User;

public class UserNew_Authenticate {

	private DataAccess database;
	private boolean authent = false;
	private String userName;
	private String password;
	private String newNotnew;
	private String launguage;
	private String tempIP;
	String temp;
	
	public UserNew_Authenticate() {

	}

	public IMS_User getCredentials(BufferedReader bReader, PrintWriter dataOut) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {


		

		IMS_User userReturned = new IMS_User();
		
		while (authent == false) {

			if ((temp = bReader.readLine()).compareTo("Start Authentication") == 0) {

				dataOut.println("Authentication started");
				dataOut.flush();
				userName = bReader.readLine();
				password = bReader.readLine();
				launguage = bReader.readLine();
				tempIP = bReader.readLine();
				newNotnew = bReader.readLine();

				if ((temp = bReader.readLine()).compareTo("Data Sent") == 0) {
					System.out.println("User authenticated, username: " + userName + " Password: " + password
							+ " New or not new: " + newNotnew + " Launguage: " + launguage + " IP: " + tempIP );
				}
			} else {
				temp = bReader.readLine();
			}

			// Create encrypted password

			PasswordEncryptionService pes = new PasswordEncryptionService();

			// create database connection
			try {
				database = new DataBaseAccess();
			} catch (Exception exception) {
				exception.printStackTrace();
				System.exit(1);
			}

			if (newNotnew.compareTo("New") == 0) {

				// Create salt and encrypted password.
				byte[] salt = pes.generateSalt();
				byte[] encryptPassword = pes.getEncryptedPassword(password, salt);

				
				IMS_User userCreate = new IMS_User(userName, encryptPassword, launguage, salt, 0, tempIP);

				database.newUser(userCreate);
				
			} else {

				IMS_User userCreate = new IMS_User(userName, 1, tempIP );
				userReturned = database.returnUser(userCreate);

				if (pes.authenticate(password, userReturned.getPassword(), userReturned.getSalt())) {
					System.out.println("Password correct");
					dataOut.println("Success");
					dataOut.flush();
					authent = true;
					// System.out.println(userReturned.getPassword().length);
					// System.out.println(userReturned.getSalt().length);
				} else {
					dataOut.println("Failure");
					dataOut.flush();
					System.out.println("Password wrong");
				}
			}
		}

		return userReturned;
	}
	

}
