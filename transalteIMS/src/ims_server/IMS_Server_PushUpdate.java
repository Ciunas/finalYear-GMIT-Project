package ims_server;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import ims_client.IMS_Client_JsonEncode;
import ims_client.IMS_Client_ServerConnectThreadGUI;
import ims_user.IMS_User;


/**
 * @author ciunas
 *
 */
public class IMS_Server_PushUpdate extends WebSocketServer {

	private static List<WebSocket> list = new ArrayList<WebSocket>();
	private IMS_Server_DataBaseAccess database;  
	

	public IMS_Server_PushUpdate(int port) throws UnknownHostException {
		super(new InetSocketAddress(port)); 
	}

	public IMS_Server_PushUpdate(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {

		System.out.println("New connection on Push server");
		list.add(conn);
		
		// Create database connection
		try {
			database = new IMS_Server_DataBaseAccess();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		
		//Encode list as a JSON string and broadcast the message to all connected users.
		IMS_User user = database.returnOnlineUser();	
		IMS_Server_JsonEncode jec = new IMS_Server_JsonEncode(user);
		String messageCreate = jec.encodeToString();
		database.close();
		broadcast(messageCreate);
	}

	@Override
	public void onClose(WebSocket conn, int code, String name, boolean remote) {

		// Create database connection
		try {
			database = new IMS_Server_DataBaseAccess();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		List<WebSocket> list1 = new ArrayList<WebSocket>(list);
		
		for (WebSocket websock : list1) {

			if (websock == conn) {

				list.remove(conn);
			}
		}
						
		//Encode list as a JSON string and broadcast the message to all connected users.
		IMS_User user = database.returnOnlineUser();
		database.close();
		
		IMS_Server_JsonEncode jec = new IMS_Server_JsonEncode(user);
		String messageCreate = jec.encodeToString();
		
		broadcast(messageCreate); 
		
		System.out.println(list.size());

	}
	
	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			System.out.println("Exception");
		}
	}

	@Override
	public void onMessage(WebSocket conn, String message) {

		if (message.charAt(0) == '#'){
			
			updateDabase( message );
		}
	}
	
	public static void broadcast(String message) {

		for (WebSocket serverThread : list) {

			if (serverThread != null) {						//check websocket is still connected
				serverThread.send( message );
			}
		}
	}
	
	public void updateDabase( String name ) {  
		
		// Create database connection
		try {
			database = new IMS_Server_DataBaseAccess();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(1);
		}
		name = name.replace("#", "");
		
		System.out.println("Close connetion with name: " + name);
		database.changeStatus( name, 0 );
		database.close();		
	}

	

}
