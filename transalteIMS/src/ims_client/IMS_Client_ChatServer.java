package ims_client;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.java_websocket.WebSocket; 
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import javax.swing.SwingUtilities;

/**
 * @author ciunas
 *
 */
public class IMS_Client_ChatServer extends WebSocketServer {

	List<IMS_Client_ServerConnectThreadGUI> list = new ArrayList<IMS_Client_ServerConnectThreadGUI>();
	private boolean run = true;
	String launguage = null;


	public IMS_Client_ChatServer(int port, String launguage) throws UnknownHostException {
		super(new InetSocketAddress(port));
		this.launguage = launguage;
	}

	public IMS_Client_ChatServer(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {

		IMS_Client_ServerConnectThreadGUI sThreadframe = new IMS_Client_ServerConnectThreadGUI(conn); // associate  a websocket with each IMS_Server_ConnectThread object
		new Thread(sThreadframe).start();
		list.add(sThreadframe); 
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {

		System.out.println(list.size());
		System.out.println("Closing connection");
		int i = 0;
		List<IMS_Client_ServerConnectThreadGUI> list1 = new ArrayList<IMS_Client_ServerConnectThreadGUI>(list);

		for (IMS_Client_ServerConnectThreadGUI serverThread : list1) {

			if (serverThread.getWs() == conn) {

				serverThread.closeConnection();
				list.remove(i);
			}
			i++;
		}
		System.out.println(list.size());

	}

	@Override
	public void onMessage(WebSocket conn, String message) {

		recievedMessage(message, conn);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			// some errors like port binding failed may not be assignable to a
			// specific websocket
			System.out.println("Exception");
		}
	}

	/**
	 * Sends the recieved message to GUI and updates using right formatting.
	 * 
	 * @param message
	 *            (read through websocket)
	 */
	void recievedMessage(String message, WebSocket conn) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				IMS_Client_JsonDecode jdc = new IMS_Client_JsonDecode(message);

				IMS_Client_Message messageObject = jdc.decodeFormString();

				messageObject.setConn(conn);

				for (IMS_Client_ServerConnectThreadGUI serverThread : list) {

					if (serverThread.getWs() == messageObject.getConn()) {

						if (serverThread.getName() == null) {
							serverThread.setName(messageObject.getName());
							serverThread.setConnectedLaunguage(messageObject.getLaunguage());
							serverThread.setLaunguage(launguage);
						}
						System.out.println(messageObject.getMessage());
						serverThread.recievedMessage(messageObject.getMessage()); // Send message  to correct thread.
					}
				}
			}
		});
	}

}