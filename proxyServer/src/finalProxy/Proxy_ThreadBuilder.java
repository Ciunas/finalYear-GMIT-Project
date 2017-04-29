package finalProxy;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import cosineDatabox.Requests_CosineSimilarity;
import de.l3s.boilerpipe.BoilerpipeProcessingException;

/**
 * @author ciunas
 *
 */

public class Proxy_ThreadBuilder extends Thread {

	private Socket clinetSocket = null; 
	private InputStream fromClient = null;
	private OutputStream toClient = null;
	private BufferedReader bReader = null;
	private boolean flag = false;


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

	

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		String[] tokens = null;
		Requests_CosineSimilarity cs = new Requests_CosineSimilarity();
		Proxy_RedirectMessages rm = new Proxy_RedirectMessages();

		try {
			tokens = Proxy_HeaderParser.parser(bReader);
			Proxy_GUI.displayInGui("Connecting and returnig URL" , "BLACK");
		} catch (IOException e) {
			e.printStackTrace();
		}

		double temp;
		if ( (temp = cs.cosineSimilarity(tokens[1].toString())) < .8 ) {					//checks for cosine similarity
			
			
			System.out.println("Low Similatity: " +  temp); 
			
			if( temp > .75 && temp < .8){
				
				

				if (tokens[1].toString().contains(".jpg") || tokens[1].toString().contains(".js")
						|| tokens[1].toString().contains(".ttf") || tokens[1].toString().contains(".gif")
						|| tokens[1].toString().contains(".css") || tokens[1].toString().contains(".txt")
						|| tokens[1].toString().contains(".png")  || tokens[1].toString().contains(".ico")) {

				} else {
					
					Proxy_GUI.displayInGui("Pasing Data to DataboxAPI", "BLACK");
					try {
						Proxy_BoilerPipe bp = new Proxy_BoilerPipe();

						if (bp.proxy_BoilerPipe(tokens[1].toString())) {

							Proxy_GUI.displayInGui("DataBox classificaiotn is Adult", "RED");
							Proxy_GUI.displayInGui("Website Blocked", "RED"); 
							
							try {
								rm.pmessage(tokens[0], toClient);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return;
							
						} else {
							Proxy_GUI.displayInGui("DataBox classificaiotn is non Adult", "RED");
							connectionExecute(tokens);
						}

					} catch (BoilerpipeProcessingException | IOException e) {
						e.printStackTrace();
					}
				}
			}else{
				
				connectionExecute(tokens);
			}

	

		} else {
			Proxy_GUI.displayInGui("Website Blocked", "RED");
			System.out.println("High Similarity");
			try {
				rm.pmessage(tokens[0], toClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			if (bReader != null) {
				bReader.close();
			}
			if (toClient != null) {
				toClient.close();
			}
			if (fromClient != null) {
				fromClient.close();
			}
			if (clinetSocket != null) {
				clinetSocket.close();
				Proxy_GUI.displayInGui("Close Socket", "BLACK");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * creates thread for connections
	 * @param tokens string array conatining details about connection
	 */
	private void connectionExecute(	String[] tokens ) {
		
		if (tokens[0].equalsIgnoreCase("GET")) {
			try {

				Proxy_HttpRequests.processHttp(tokens[1].toString(), toClient, flag);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (tokens[0].equalsIgnoreCase("CONNECT")) {

			try {
				Proxy_HttpsRequests.processHttps(tokens, fromClient, toClient);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (tokens[0].equalsIgnoreCase("POST")) {

			try {
				Proxy_Post.postProcess(tokens, toClient);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	

}
