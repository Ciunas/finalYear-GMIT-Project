package ims_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import ims_user.IMS_User;

public class IMS_Server_ThreadBuilder  implements Runnable{

	
	
	private Socket serverSocket = null;
	String[] tokens = null;
	BufferedReader bReader = null;
	PrintWriter dataOut = null;

	// Bind to client socket.
	public IMS_Server_ThreadBuilder(Socket socket) {
		super();
		this.serverSocket = socket;
		try {

			bReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			dataOut = new PrintWriter(serverSocket.getOutputStream());

		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		UserNew_Authenticate ua = new UserNew_Authenticate();
		
		IMS_User user = ua.getCredentials(bReader, dataOut);
		

//		try {
//			database = new DataBaseAccess();
//		} catch (Exception exception) {
//			exception.printStackTrace();
//			System.exit(1);
//		}

		// IMS_User user = new IMS_User(getUsername(),getPassword() );
		//     // create database connection
//      try {
//          database = new DataBaseAccess();
//      }
//      catch (Exception exception) {
//          exception.printStackTrace();
//          System.exit(1);
//      }
		
		//database.newUser(user)
		
	}

}
