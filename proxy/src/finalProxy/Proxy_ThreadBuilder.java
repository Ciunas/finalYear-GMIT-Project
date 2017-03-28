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
	// Streams from the Client and Server
	private InputStream fromClient = null;
	private OutputStream toClient = null;
	BufferedReader bReader = null;
	DataOutputStream dataOut = null;

	// Bind to client socket.
	public Proxy_ThreadBuilder(Socket socket) {
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

		String[] tokens = null;
		Requests_CosineSimilarity cs = new Requests_CosineSimilarity();
		Proxy_RedirectMessages rm = new Proxy_RedirectMessages();

		try {
			tokens = Proxy_HeaderParser.parser(bReader);
			Proxy_GUI.displayInGui("Connecting and returnig URL");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(tokens[1].toString());

		if (cs.cosineSimilarity(tokens[1].toString()) > 0.5) {

			System.out.println("Low Similatity");
			if (tokens[0].equalsIgnoreCase("GET")) {
				try {

					Proxy_HttpRequests.processHttp(tokens[1].toString(), dataOut);

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
					Proxy_Post.postProcess(tokens, dataOut);

				} catch (Exception e) {
					e.printStackTrace();
				}

				// System.out.println("POST Thread Finsihed");
			}
			try {
				if (bReader != null) {
					bReader.close();
				}
				if (dataOut != null) {
					dataOut.close();
				}
				if (clinetSocket != null) {
					clinetSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println("High Similarity");
			try {
				rm.pmessage(dataOut);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
