package proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class httpsThread implements Runnable {

	Socket socket;

	public httpsThread(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {
		try {
			Socket conn = new Socket("www.google.com", 443);
			InputStream connIn = conn.getInputStream();
			OutputStream connOut = conn.getOutputStream();

			OutputStream out = socket.getOutputStream();
			out.write("HTTP/1.1 200 OK\n\n".getBytes());
			out.flush();
			new StreamForwarder(connIn, out).start();
			new StreamForwarder(socket.getInputStream(), connOut).start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private class StreamForwarder extends Thread {

		private InputStream in;
		private OutputStream out;

		StreamForwarder(InputStream in, OutputStream out) {
			this.in = in;
			this.out = out;
		}

		@Override
		public void run() {
			int b;
			try {
				while ((b = in.read()) != -1) {
					out.write(b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
