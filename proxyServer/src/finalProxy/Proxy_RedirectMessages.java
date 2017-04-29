package finalProxy;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Proxy_RedirectMessages {

	/**
	 * Returns an access forbidden Header and sends back plain page informing
	 * the user that the website is forbidden
	 * 
	 * @param dataOut
	 *            connection to client
	 * @throws IOException
	 */
	public void pmessage(String protocol, OutputStream dataOut) throws IOException {


		if ( protocol.equalsIgnoreCase("CONNECT") ) {

			//Write message to client port 433
			dataOut.write("HTTP/1.1 302 Object moved".getBytes());
			dataOut.write("\r\n".getBytes());
			dataOut.write("Location: https://s3.amazonaws.com/aws-website-myfinalyearproject-4ljth/block.html".getBytes());
			dataOut.write("\r\n".getBytes());

		} else {

			// Send headers and error message to client
			dataOut.write("HTTP/1.1 302 Object moved".getBytes());
			dataOut.write("\r\n".getBytes());
			dataOut.write("Location: https://s3.amazonaws.com/aws-website-myfinalyearproject-4ljth/block.html".getBytes());
			dataOut.write("\r\n".getBytes());
		}
		dataOut.flush();

		return;
	}

}
