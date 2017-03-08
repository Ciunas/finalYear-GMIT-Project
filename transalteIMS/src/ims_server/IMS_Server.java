package ims_server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author ciunas
 *
 */
public class IMS_Server {

	
	   public static void main(String[] args) throws IOException {
	    	
	        ServerSocket serverSocket = null;
	        boolean proxyRunnig = true;
	        int port = 1237;	
	     
	        try {
	            serverSocket = new ServerSocket(port);
	            System.out.println("Started on: " + port);
	        } catch (IOException e) {
	            System.exit(-1);
	        }

	        while (proxyRunnig) {
	        	
	           
	            IMS_Server_ThreadBuilder thread = new IMS_Server_ThreadBuilder(serverSocket.accept());
	            new Thread(thread).start();
	            System.out.println("New thread");
	        }
	        serverSocket.close();
	    }
}
