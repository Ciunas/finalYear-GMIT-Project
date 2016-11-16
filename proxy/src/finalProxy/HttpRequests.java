package finalProxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequests {
	
	public static void processHttp(String urlCall, DataOutputStream dataOut) throws IOException{
		final int SIZE = 32768;
		InputStream is = null;
		BufferedReader rd = null;
        URL url = new URL(urlCall);
        URLConnection conn = url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(false);
        System.out.println("Type is: " + conn.getContentType());
        System.out.println("content length: " + conn.getContentLength());
        System.out.println("allowed user interaction: " + conn.getAllowUserInteraction());
        System.out.println("content encoding: " + conn.getContentEncoding());
        System.out.println("content type: " + conn.getContentType());
       
        if (conn.getContentLength() > 0) {
            try {
                is = conn.getInputStream();
                rd = new BufferedReader(new InputStreamReader(is));
            } catch (IOException ioe) {
                System.out.println("** IO EXCEPTION **: " + ioe);
            }
        }
        ///////////////////////////////////
        //send response to client
        byte by[] = new byte[ SIZE ];
        int index = is.read( by, 0, SIZE );
        while ( index != -1 )
        {
        	dataOut.write( by, 0, index );
          index = is.read( by, 0, SIZE );
        }
        dataOut.flush();
        
        return;
	}

}
