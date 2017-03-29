package finalProxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import apiDatabox.Requests_CosineSimilarity;

public class Proxy_ThreadBuilder extends Thread {

	private Socket clinetSocket = null; 
	private InputStream fromClient = null;
	private OutputStream toClient = null;
	BufferedReader bReader = null;


	/**
	 * 	Bind to client socket.
	 * @param socket
	 */
	public Proxy_ThreadBuilder(Socket socket) {
		super();
		this.clinetSocket = socket;
		try {

			fromClient = clinetSocket.getInputStream();
			toClient = clinetSocket.getOutputStream();
			bReader = new BufferedReader(new InputStreamReader(fromClient));

		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// @Override
	public void run() {

		String[] tokens = null;
		Requests_CosineSimilarity cs = new Requests_CosineSimilarity();
		Proxy_RedirectMessages rm = new Proxy_RedirectMessages();

		try {
			tokens = Proxy_HeaderParser.parser(bReader);
			Proxy_GUI.displayInGui("Connecting and returnig URL");
		} catch (IOException e) {
			e.printStackTrace();
		}


		if (cs.cosineSimilarity(tokens[1].toString()) < 0.7 ) {

			System.out.println("Low Similatity");
			if (tokens[0].equalsIgnoreCase("GET")) {
				try {

					Proxy_HttpRequests.processHttp(tokens[1].toString(), toClient);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (tokens[0].equalsIgnoreCase("CONNECT")) {

				try {
					Proxy_HttpsRequests.processHttps(tokens, fromClient, toClient);

				} catch (IOException e) {
					e.printStackTrace();
				}

				// System.out.println("CONNECT Thread Finshed");

			} else if (tokens[0].equalsIgnoreCase("POST")) {

				try {
					Proxy_Post.postProcess(tokens, toClient);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// System.out.println("POST Thread Finsihed");
			}
			

		} else {
			System.out.println("High Similarity");
			System.out.println(tokens[0]);
			System.out.println(tokens[1]);
			try {
				rm.pmessage( tokens[0],  toClient );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			if (bReader != null) {
				bReader.close();
				System.out.println("Close Breader");
			}
			if (toClient != null) {
				toClient.close();
				System.out.println("Close toClient");
			}
			if (fromClient != null) {
				fromClient.close();
				System.out.println("Close fromCLient");
			}
			if (clinetSocket != null) {
				clinetSocket.close();
				System.out.println("Close Socket");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
