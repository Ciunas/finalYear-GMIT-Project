package ims_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import ims_user.IMS_User;

public class IMS_Server_UserNew_Authenticate {

	private IMS_Server_DataAccess database;
	private boolean authent = false;
	private String userName;
	private String password;
	private String newNotnew;
	private String launguage;
	private String tempIP;
	String temp;

	public IMS_Server_UserNew_Authenticate() {

	}

	public IMS_User getCredentials(BufferedReader bReader, PrintWriter dataOut)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

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
//					System.out.println("User authenticated, username: " + userName + " Password: " + password
//							+ " New or not new: " + newNotnew + " Launguage: " + launguage + " IP: " + tempIP);
				}
			} else {
				temp = bReader.readLine();
			}

			// Create encrypted password
			IMS_Server_PasswordEncryptionService pes = new IMS_Server_PasswordEncryptionService();

			// Create database connection
			try {
				database = new IMS_Server_DataBaseAccess();
			} catch (Exception exception) {
				exception.printStackTrace();
				System.exit(1);
			}

			if (newNotnew.compareTo("New") == 0) {

				// Create salt and encrypted password.
				byte[] salt = pes.generateSalt();
				byte[] encryptPassword = pes.getEncryptedPassword(password, salt);

				IMS_User userCreate = new IMS_User(userName, launguage, encryptPassword, salt, 1, tempIP);

				if (database.newUser(userCreate) != false) {
					dataOut.println("Success");
					dataOut.flush();
					userReturned = database.returnUser(userCreate);
					authent = true;
				} else {
					dataOut.println("Failure");
					dataOut.flush();
					//System.out.println("No User");
				}

			} else {

				System.out.println(userName);
				IMS_User userCreate = new IMS_User(userName, 1, tempIP);
				System.out.println(userCreate.getName());

				if ((userReturned = database.returnUser(userCreate)) != null) {

					if (pes.authenticate(password, userReturned.getPassword(), userReturned.getSalt())) {
						System.out.println("Password correct");
						System.out.println(userReturned.getLaunguage());
						dataOut.println("Success");
						dataOut.flush();
						authent = true; 
					} else {
						dataOut.println("Failure");
						dataOut.flush();
						System.out.println("Password wrong");
					}
				} else {
					dataOut.println("Failure");
					dataOut.flush();
					System.out.println("No User");
				}

			}
		}
		database.close();
		return userReturned;
	}

}
