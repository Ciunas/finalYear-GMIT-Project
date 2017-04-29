package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;


public class Proxy_HttpRequests {
	
	/**
	 * processes any http request coming through port 80
	 * 
	 * @param urlCall
	 *            endpoint of request
	 * @param dataOut
	 *            outputstream to client
	 * @throws IOException
	 */
	public static void processHttp(String urlCall, OutputStream dataOut, boolean flag) throws IOException{
		
		final int SIZE = 32768;
		InputStream is = null; 
        URL url = new URL(urlCall);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(false);

        
		try {
			is = connection.getInputStream();
		} catch (IOException ioe) {
			//System.out.println("** IO EXCEPTION **: " + ioe);
		}

        //send response to client
        byte by[] = new byte[ SIZE ];
        int index = is.read( by, 0, SIZE );
  
		while (index != -1) {
			dataOut.write(by, 0, index);
			index = is.read(by, 0, SIZE);
		
		}
        dataOut.flush();
        
        return;
	}

}
