package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SSLconect implements Runnable {

	private OutputStream outStream = null;
	private InputStream inStream = null;

	private Record currentRecord = new Record();
	private boolean upStream;
	private  boolean running = true;

	public SSLconect(InputStream in, OutputStream out, boolean upStream) {
		this.outStream = out;
		this.inStream = in;
		this.upStream = upStream;
	}

	public void run() {

		// reading and writing messages
		while (currentRecord.read(inStream) > -1  && running == true) {

			currentRecord.write(outStream);
			// System.out.println("alive in records");
		}
		ProxyGUI.displayInGui("Finished writeing records for CONNECT method");
		try {
			inStream.close();
			outStream.close();
		} catch (IOException e) {
						e.printStackTrace();
		}
		return;
	}

	public void stopThread(boolean running, long id) {
		ProxyGUI.displayInGui("Stopping Thread: " + id);
		this.running = running;
	}

}
