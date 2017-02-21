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

public class IMS_Server_ThreadBuilder  implements Runnable{

	
	
	private Socket serverSocket = null;
	String[] tokens = null;
	BufferedReader bReader = null;
	PrintWriter dataOut = null;
	ObjectInputStream in = null;
	ObjectOutputStream out  = null;

	// Bind to client socket.
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

	@Override
	public void run() {
		
		UserNew_Authenticate ua = new UserNew_Authenticate();
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
	}

}
