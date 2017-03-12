package ims_server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author ciunas
 *
 */
public class IMS_Server {

	public static void main(String[] args) throws IOException {

		int port = 1234;		//default port if nothing is specified
		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception ex) {
		}

		ServerSocket serverSocket = null;
		boolean proxyRunnig = true;

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Started on: " + port);
		} catch (IOException e) {
			System.exit(-1);
		}
		
		new Thread(new IMS_Server_PushUpdate(8888)).start();			//Starts the websocket push server. Port 8888
		
		while (proxyRunnig) {

			IMS_Server_ThreadBuilder thread = new IMS_Server_ThreadBuilder(serverSocket.accept());
			new Thread(thread).start();
			System.out.println("New thread");
		}
		serverSocket.close();
	}
}
