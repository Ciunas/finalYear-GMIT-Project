package finalProxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Proxy_RedirectMessages {
 

	/**
	 * Returns an access forbidden Header and sends back plain page informing the user that the website is forbidden
	 * @param dataOut connection to client
	 * @throws IOException
	 */
	public void pmessage(DataOutputStream dataOut) throws IOException {

		System.out.println("Here ya");
		final int SIZE = 32768;

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();			//read  HTML page 
		InputStream is = classloader.getResourceAsStream("resources/block.html");
		 
//		 Send headers and error message to client
		byte by[] = new byte[SIZE];
		dataOut.writeBytes("HTTP/1.1 403 FORBIDDEN\n"); 
		dataOut.writeBytes("Content-Type: text/html\n"); 
		dataOut.writeBytes("Content-Length: 278\n"); 
		dataOut.writeBytes("\n"); 

		int index = is.read(by, 0, SIZE);		
		while (index != -1) { 
			dataOut.write(by, 0, index);
			index = is.read(by, 0, SIZE);
		}
		dataOut.flush();

		return;
	}

}
