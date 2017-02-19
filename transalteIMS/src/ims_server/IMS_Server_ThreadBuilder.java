package ims_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import transalteIMS.IMS_User;

public class IMS_Server_ThreadBuilder  implements Runnable{

	
	
	private Socket clinetSocket = null;
	// Streams from the Client and Server
	private InputStream fromClient = null;
	private OutputStream toClient = null;
    private DataAccess database;
	String[] tokens = null;
	BufferedReader bReader = null;
	DataOutputStream dataOut = null;

	// Bind to client socket.
	public IMS_Server_ThreadBuilder(Socket socket) {
		super();
		this.clinetSocket = socket;
		try {

			fromClient = clinetSocket.getInputStream();
			toClient = clinetSocket.getOutputStream();

			bReader = new BufferedReader(new InputStreamReader(fromClient));
			dataOut = new DataOutputStream(toClient);

		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		
		//IMS_User user = new IMS_User(getUsername(),getPassword() );
//      // create database connection
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
