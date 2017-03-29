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

		final int SIZE = 32768;

		ClassLoader classloader = Thread.currentThread().getContextClassLoader(); // read HTML page
		InputStream is = classloader.getResourceAsStream("resources/block.html");

		if ( protocol.equalsIgnoreCase("CONNECT") ) {

			//Write message to client port 433
			dataOut.write("HTTP/1.1 403 Not Allowed Mime-Type: text/html".getBytes());
			dataOut.write("\r\n".getBytes());
			dataOut.write("Cache-Control: no-cache,no-store".getBytes());
			dataOut.write("\r\n".getBytes());
			dataOut.write("Connection: close".getBytes());
			dataOut.write("\r\n".getBytes());

		} else {

			// Send headers and error message to client
			byte by[] = new byte[SIZE];
			dataOut.write("HTTP/1.1 403 Not Allowed Mime-Type: text/html\r\n".getBytes());
			dataOut.write("Cache-Control: no-cache,no-store\r\n".getBytes());
			dataOut.write("Connection: close\r\n".getBytes());
			dataOut.write("Content-Type: text/html\n".getBytes());
			dataOut.write("Content-Length: 278\r\n".getBytes());
			dataOut.write("\r\n".getBytes());

			int index = is.read(by, 0, SIZE);
			while (index != -1) {
				dataOut.write(by, 0, index);
				index = is.read(by, 0, SIZE);
			}
		}
		dataOut.flush();

		return;
	}

}
