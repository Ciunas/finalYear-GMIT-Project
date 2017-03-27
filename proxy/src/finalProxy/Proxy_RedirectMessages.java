package finalProxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Proxy_RedirectMessages {

	public static void main(String[] args) throws IOException {
		DataOutputStream dataOut = null;
		Proxy_RedirectMessages rm = new Proxy_RedirectMessages();
		rm.pmessage(dataOut);
	}

	public void pmessage(DataOutputStream dataOut) throws IOException {

		System.out.println("Here ya");
		final int SIZE = 32768;

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("resources/block.html");

		 
//		 send error message to client
		byte by[] = new byte[SIZE];
		int index = is.read(by, 0, SIZE);
		
		while (index != -1) { 
			dataOut.write(by, 0, index);
			index = is.read(by, 0, SIZE);
		}
		dataOut.flush();

		return;
	}

}
