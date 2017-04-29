package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Proxy_Websock implements Runnable{

	private OutputStream OutStream = null;
	private InputStream InStream = null;
	Boolean  runnig = false; 

	/**
	 * sets connection details for websocket
	 * 
	 * @param in
	 *            InputStream tfrom connection
	 * @param os
	 *            Outputstream to connection
	 * @param running
	 */
	public Proxy_Websock(InputStream in, OutputStream os, Boolean running) {

		this.runnig = running;
		this.OutStream = os;
		this.InStream = in;

	}

	@Override
	public void run() {

		while (runnig) {
			int temp = 0;
			try {
				temp = InStream.read();
				if(temp == -1){
					runnig = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				OutStream.write(temp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		Proxy_GUI.displayInGui("Closing Websocket for Port 80", "BLACK");
		return;
	}
	
	/**
	 * used to stop websocket thread from running
	 * 
	 * @param runnig
	 */
	public void stopThread(boolean runnig) {
		this.runnig = runnig;
	}
}
