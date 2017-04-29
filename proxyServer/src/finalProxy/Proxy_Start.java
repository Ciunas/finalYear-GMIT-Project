package finalProxy;

import java.io.IOException;
import java.net.ServerSocket;

//

public class Proxy_Start {

    
    /**
     *  
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	
        ServerSocket serverSocket = null;
        boolean proxyRunnig = true;
        int port = 1234;	
     
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Started on: " + port);
        } catch (IOException e) {
            System.exit(-1);
        }

        while (proxyRunnig) {
            new Proxy_ThreadBuilder(serverSocket.accept()).start();
        }
        serverSocket.close();
    }
}
