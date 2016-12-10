package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Websock implements Runnable{

	private OutputStream OutStream = null;
	private InputStream InStream = null;
	Boolean  runnig = false;
	//public  synchronized

	public Websock(InputStream in, OutputStream os, Boolean running) {

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
		ProxyGUI.displayInGui("Closeing Websocket for Port 80");
		return;
	}
	

	public void stopThread(boolean runnig) {
		this.runnig = runnig;
	}
}
