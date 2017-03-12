package ims_server;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;


/**
 * @author ciunas
 *
 */
public class IMS_Server_PushUpdate extends WebSocketServer {

	private static List<WebSocket> list = new ArrayList<WebSocket>();  
	

//	 private static Set<Session> userSessions = Collections.newSetFromMap(new
//	   ConcurrentHashMap<Session, Boolean>());
	//
	// @OnOpen
	// public void onOpen(Session userSession) {
	// userSessions.add(userSession);
	// }
	//
	// @OnClose
	// public void onClose(Session userSession) {
	// userSessions.remove(userSession);
	// }
	//
	// @OnMessage
	// public void onMessage(String message, Session userSession) {
	// broadcast(message);
	// }
	//
	public static void broadcast(String msg) {
		
//		for (Session session : userSessions) {
//			session.getAsyncRemote().sendText(msg);
//		}
		for (WebSocket serverThread : list) {

			if (serverThread != null) {						//check websocket is still connected
				serverThread.send(msg);
			}
		}
	}

	public IMS_Server_PushUpdate(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public IMS_Server_PushUpdate(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {

		list.add(conn);
		broadcast("Help");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {

		System.out.println(list.size());
		System.out.println("Closing connection");
		int i = 0;		
		List<WebSocket> list1 = new ArrayList<WebSocket>(list);  

		for (WebSocket serverThread : list1) {

			if (serverThread.equals(conn)  ) {
 
				list.remove(i);
			}
			i++;
		}
		
		broadcast("Help");
		System.out.println(list.size());

	}

	@Override
	public void onMessage(WebSocket conn, String message) {

		broadcast( message );
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			System.out.println("Exception");
		}
	}
	

}
