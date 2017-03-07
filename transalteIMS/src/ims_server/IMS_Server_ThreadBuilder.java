package ims_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import ims_user.IMS_User;

public class IMS_Server_ThreadBuilder implements Runnable {

	private Socket serverSocket = null;
	String[] tokens = null;
	BufferedReader bReader = null;
	PrintWriter dataOut = null;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;

	// Bind to client socket and create input and output streams
	
	public IMS_Server_ThreadBuilder(Socket socket) {
		super();
		this.serverSocket = socket;
		try {

			bReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			dataOut = new PrintWriter(serverSocket.getOutputStream());

			out = new ObjectOutputStream(serverSocket.getOutputStream());
			in = new ObjectInputStream(serverSocket.getInputStream());

		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
	
	/* (non-Javadoc)
	 * Creates a  IMS_Server_UserNew_Authenticate object, then creates an IMS_User object with the creadentials that are returned 
	 * from IMS_Server_UserNew_Authenticate. Sends this object out the socket flushes output stream and then closes the streams.
	 */
	@Override
	public void run() {

		IMS_Server_UserNew_Authenticate ua = new IMS_Server_UserNew_Authenticate();

		System.out.println("getting creadiantiels");
		try {

			IMS_User user = ua.getCredentials(bReader, dataOut);
			out.writeObject(user);
			out.flush();
			out.close();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

	}

}
