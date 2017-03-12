package ims_client;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel; 
import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import ims_user.IMS_User;


/**
 * @author ciunas
 *
 */

public class IMS_Client_PushConnection implements Runnable{
	


	private boolean run = true;
	private String name;
	private WebSocketClient wsc;
	private String ip;
	private DefaultTableModel table;
	
	public IMS_Client_PushConnection(boolean run,  String ip, DefaultTableModel table, String name) {
		super();
		this.name = name;
		this.run = run; 
		this.ip = ip;
		this.table = table;
	}
	
	/**
	 * run
	 */
	@Override
	public void run() {

		WebSocketImpl.DEBUG = true;
		connectWebCocket();
		while (run == true) {

		}
	}

	/**
	 * creates the websocket connection, listens for any change in state and
	 * posts to swingutilites with any messages.
	 */
	void connectWebCocket() {

		try {

			wsc = new WebSocketClient(new URI(ip)) {

				

				@Override
				public void onMessage(String message) {
					
					recievedMessage(message);				
				}

				@Override
				public void onOpen(ServerHandshake handshake) {

				}

				@Override
				public void onClose(int code, String reason, boolean remote) {
					
				}

				@Override
				public void onError(Exception ex) {
					ex.printStackTrace();
				}
			};

			wsc.connect(); // connect to the websocket that is specified by
							// variable "ip"

		} catch (URISyntaxException ex) {

		}
	}

	
	/**
	 * message that is read from text field, sent to websocket using
	 * swingutilities, then GUI is updated using left formatting
	 */
	void sentMessage(String message) {

		if (wsc != null) { // check websocket is still connected
			wsc.send(message);
		}
	
	}

	/**
	 * Sends the recieved message to the GUI and updates using right formatting.
	 * 
	 * @param message
	 *            (read through websocket)
	 */
	void recievedMessage(String message) {

		IMS_Clinet_JsonDecodeOnlineUsers jdc = new IMS_Clinet_JsonDecodeOnlineUsers(message);

		IMS_User messageObject = jdc.decodeFormString();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				table.setRowCount(0);
				
				for (int i = 0, j = 0; i < messageObject.onlineUsers.size(); i += 2 , j++) {

					Object[] objs = { j + 1, messageObject.getOnlineUsers(i), messageObject.getOnlineUsers(i + 1) };

					table.addRow(objs);
				}

			}
		});
	}
	
	
	//Getters and Setters
	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
}
