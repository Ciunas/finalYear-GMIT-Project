package selproxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConnectHandler {
	
    ConnectionHandler ch = null;

    InputStream fromClient = null;
    InputStream fromServer = null;

    OutputStream toClient = null;
    OutputStream toServer = null;

    SSLStreamHandler streamToServer;
    SSLStreamHandler streamToClient;

	
	
    private void connectionEstablished(){
        try {
            toClient.write("HTTP/1.1 200 Connection established".getBytes());
            toClient.write("\r\n".getBytes());
            toClient.write("Proxy-agent: SelProxyv0.1".getBytes());
            toClient.write("\r\n".getBytes());
            toClient.write("\r\n".getBytes());
            toClient.flush();
        } catch(IOException ioe) {
            return;
        }
    }

    private void closeStreams() {
        try {
            this.toClient.close();
            this.toServer.close();
            this.fromClient.close();
            this.fromServer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
