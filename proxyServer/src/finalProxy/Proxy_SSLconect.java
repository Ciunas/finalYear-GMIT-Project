package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Proxy_SSLconect implements Runnable {

	private OutputStream outStream = null;
	private InputStream inStream = null;
	private Proxy_Record currentRecord;
	private boolean running = true;

	/**
	 * reads and writes records to client
	 * 
	 * @param in
	 *            inpustream from client
	 * @param out
	 *            outputstream to client
	 */
	public Proxy_SSLconect(InputStream in, OutputStream out) {
		this.outStream = out;
		this.inStream = in;
		currentRecord = new Proxy_Record();
	}

	public void run() {

		// reading and writing messages
		while (currentRecord.read(inStream) > -1 && running == true) {

			currentRecord.write(outStream);
		}
		Proxy_GUI.displayInGui("Finished writeing records for CONNECT method", "BLACK");
		try {
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void stopThread(boolean running, long id) {
		Proxy_GUI.displayInGui("Stopping Thread: " + id, "BLACK");
		this.running = running;
	}

}
