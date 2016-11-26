package finalProxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Websock implements Runnable{

	private OutputStream OutStream = null;
	private InputStream InStream = null;
	 Boolean  runnnig = false;
	//public  synchronized

	public Websock(InputStream in, OutputStream os, Boolean running) {

		this.runnnig = running;
		this.OutStream = os;
		this.InStream = in;

	}

	@Override
	public void run() {

		while (runnnig) {
			//System.out.println("inside  running thread port 80 websock");
			
			int temp = 0;
			try {
				temp = InStream.read();
				if(temp == -1){
					runnnig = false;
				}
				//System.out.println(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OutStream.write(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("finished writing Websocket");
		return;
	}
}
