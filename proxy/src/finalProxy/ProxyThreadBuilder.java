package finalProxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ProxyThreadBuilder extends Thread {

	private Socket clinetSocket = null;
	// Streams from the Client and Server
	 private InputStream fromClient = null;
	 private OutputStream toClient = null;
	// private InputStream fromServer = null;
	// private OutputStream toServer = null;
	String[] tokens = null;
	BufferedReader bReader = null;
	DataOutputStream dataOut = null;

	// Bind to client socket.
	public ProxyThreadBuilder(Socket socket) {
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

	// @Override
	public void run() {
		try {
			tokens = HeaderParser.parser(bReader);
			System.out.println("Connecting and returnig URL");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tokens[0].equalsIgnoreCase("GET")) {
			try {
				String temp = tokens[1].toString();
				HttpRequests.processHttp(tokens[1].toString(), dataOut);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (tokens[0].equalsIgnoreCase("CONNECT")) {
			
			try {
				
				HttpsRequests.processHttps(tokens, fromClient, toClient);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("CONNECT Thread Finsihed");
		} else if (tokens[0].equalsIgnoreCase("POST")) {
			System.out.println("POST Thread Finsihed");
		}
		try{
	    if (bReader != null) {
	    	bReader.close();
        }
        if (dataOut != null) {
        	dataOut.close();
        }
        if (clinetSocket != null) {
        	clinetSocket.close();
        }
		}catch (IOException e) {
            e.printStackTrace();
        }
	}

}
