package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Proxy_HttpsRequests {

	static Socket serverSocket = null;

	/**
	 *  passes connection to relevent method, either port 433 or 80
	 * depending on request.
	 * 
	 * @param serverDetails
	 *            contains all data relating to url connection
	 * @param fromClient
	 *            inputstream from client
	 * @param toClient
	 *            outputstream to client
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void processHttps(String[] serverDetails, InputStream fromClient, OutputStream toClient)
			throws UnknownHostException, IOException {

		String[] serverInfo = serverDetails[1].split(":");
		System.out.println("Address : " + serverInfo[0]);
		System.out.println("Port number: " + serverInfo[1]);

		if (Integer.parseInt(serverInfo[1]) == 443) {
			processHttps443(serverInfo, fromClient, toClient);
			Proxy_GUI.displayInGui("Processing Https Connection on port 433", "BLACK"); 
		} else {
			processHttps80(serverInfo, fromClient, toClient);
			Proxy_GUI.displayInGui("Processing Https Connection on port 80 data", "BLACK"); 
		}
	}
 
	/**
	 * process any https connections on port 433
	 * 
	 * @param serverInfo
	 *            contains all data relating to url connection
	 * @param fromClient
	 *            inputstream from client
	 * @param toClient
	 *            outputstream to client
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void processHttps443(String[] serverInfo, InputStream fromClient, OutputStream toClient)
			throws UnknownHostException, IOException {
		long threadId = 0;
		long threadId2 = 0;
		serverSocket = new Socket(serverInfo[0], Integer.parseInt(serverInfo[1]));
		// serverSocket.setSoTimeout(4*1000);

		InputStream fromServer = serverSocket.getInputStream();
		OutputStream toServer = serverSocket.getOutputStream();

		// Write message to client
		toClient.write("HTTP/1.1 200 Connection Established".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("Proxy-agent: CiunasProxy".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.flush();

		// CLient to Server
		Proxy_SSLconect streamToServer = new Proxy_SSLconect(fromClient, toServer);
		// Server to Client
		Proxy_SSLconect streamToClient = new Proxy_SSLconect(fromServer, toClient);

		// separate threads for each stream
		Thread t1 = new Thread(streamToServer);
		Thread t2 = new Thread(streamToClient);
		t1.start();
		t2.start();

		while (t1.isAlive() || t2.isAlive()) {
			threadId = t1.getId();
			threadId2 = t2.getId(); 
			try {

				Thread.sleep(5000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		}

		streamToServer.stopThread(false, threadId);
		streamToClient.stopThread(false, threadId2);

		try {
			toClient.close();
			toServer.close();
			fromClient.close();
			fromServer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * process any https connections on port 80
	 * 
	 * @param serverInfo
	 *            contains all data relating to url connection
	 * @param fromClient
	 *            inputstream from client
	 * @param toClient
	 *            outputstream to client
	 * @throws IOException
	 */
	public static void processHttps80(String[] serverDetails, InputStream fromClient, OutputStream toClient)
			throws IOException {
		long threadId;
		long threadId2;
		serverSocket = new Socket(serverDetails[0], Integer.parseInt(serverDetails[1]));

		InputStream fromServer = serverSocket.getInputStream();
		OutputStream toServer = serverSocket.getOutputStream();

		// Write message to client
		toClient.write("HTTP/1.1 200 Connection Established".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("Proxy-agent: CiunasProxy".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.write("\r\n".getBytes());
		toClient.flush();

		Proxy_Websock streamToServer = new Proxy_Websock(fromClient, toServer, true);
		Proxy_Websock streamToClient = new Proxy_Websock(fromServer, toClient, true);

		Thread t1 = new Thread(streamToServer);
		Thread t2 = new Thread(streamToClient);
		t1.start();
		t2.start();

		while (t1.isAlive() || t2.isAlive()) {
			threadId = t1.getId();
			threadId2 = t2.getId(); 
			try {

				Thread.sleep(5000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}

		streamToServer.stopThread(false);
		streamToClient.stopThread(false);

		try {

			toClient.close();
			toServer.close();
			fromClient.close();
			fromServer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}