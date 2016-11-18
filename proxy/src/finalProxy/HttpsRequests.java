package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class HttpsRequests {

	static Socket serverSocket = null;
	
	
	public static void processHttps(String[] serverDetails, InputStream fromClient, OutputStream toClient) throws UnknownHostException, IOException{
		
		String[] serverInfo = serverDetails[1].split(":");
		System.out.println("Address : " + serverInfo[0]);
		System.out.println("Port number: " + serverInfo[1]);
		
		
		
		if(Integer.parseInt(serverInfo[1]) == 443){
			processHttps443(serverInfo,  fromClient,  toClient);
			System.out.println("processing port 433 data");
		}else{
			processHttps80(serverInfo, fromClient ,  toClient);
			System.out.println("processing port 80 data");
		}
	}

	//Will process any https connections on port 433
	public static void processHttps443(String[] serverInfo, InputStream fromClient, OutputStream toClient) throws UnknownHostException, IOException {

		serverSocket = new Socket(serverInfo[0], Integer.parseInt(serverInfo[1]));
		
		InputStream fromServer = serverSocket.getInputStream();
		OutputStream toServer = serverSocket.getOutputStream();
		
		//Write message to client
		toClient.write("HTTP/1.1 200 Connection Established".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("Proxy-agent: CiunasProxy".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.flush();

		SSLconect streamToServer = new SSLconect(fromClient, toServer, true);
		// Server ---> Client
		SSLconect streamToClient = new SSLconect(fromServer, toClient, false);

		// separate thread dealing with each directional stream....
		Thread t1 = new Thread(streamToServer);
		Thread t2 = new Thread(streamToClient);
		t1.start();
		t2.start();
		// Don't close streams early before both sides of the connection have
		// finished.
		while (t1.isAlive() || t2.isAlive()) {
			System.out.println("Ciunas Proxy inside SSLConnection");
			System.out.println("is thread t1 still alve " + t1.isAlive());
			try {
				
				Thread.sleep(5000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		try {
			toClient.close();
			toServer.close();
			fromClient.close();
			fromServer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	
	public static void processHttps80(String[] serverDetails, InputStream fromClient, OutputStream toClient) throws IOException {
		
		serverSocket = new Socket(serverDetails[0], Integer.parseInt(serverDetails[1]));
		
		InputStream fromServer = serverSocket.getInputStream();
		OutputStream toServer = serverSocket.getOutputStream();
		//Write message to client
		
		toClient.write("HTTP/1.1 200 Connection Established".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("Proxy-agent: CiunasProxy".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.flush();
		
		System.out.println("writing message to client");
		
		Websock streamToServer  = new Websock(fromClient, toServer, true);
		Websock streamToClient = new Websock(fromServer, toClient, true);
		
		Thread t1 = new Thread(streamToServer);
		Thread t2 = new Thread(streamToClient);
		t1.start();
		t2.start();
		
		while (t1.isAlive() || t2.isAlive()) {
			System.out.println("Ciunas Proxy inside Websock");
			System.out.println("is thread t1 still alve " + t1.isAlive());
			try {
				
				Thread.sleep(5000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		try {
			System.out.println("");
			
			toClient.close();
			toServer.close();
			fromClient.close();
			fromServer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}