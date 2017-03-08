package ims_client;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList; 
import java.util.List; 
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer; 
import javax.swing.SwingUtilities; 

/**
 * @author ciunas
 *
 */
public class IMS_Client_ChatServer extends WebSocketServer  {
	
	private int portNumber;
	List<IMS_Client_ServerConnectThreadGUI> list = new ArrayList<IMS_Client_ServerConnectThreadGUI>();
	private boolean run = true;
	
	public IMS_Client_ChatServer( int port , String st) throws UnknownHostException{
		
		this.portNumber =  port;
	
	}

	public IMS_Client_ChatServer( int port ) throws UnknownHostException {
		super( new InetSocketAddress( port ) );
	}

	public IMS_Client_ChatServer( InetSocketAddress address ) {
		super( address );
	}

	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {

		
		IMS_Client_ServerConnectThreadGUI sThreadframe = new IMS_Client_ServerConnectThreadGUI( conn ); //associate a websocket with each IMS_Server_ConnectThread object
		new Thread(sThreadframe).start();
		list.add(sThreadframe);	
		//this.reply( "new connection: " + handshake.getResourceDescriptor() );
		//System.out.println( conn  + " entered the room!" );
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
	public void onMessage( WebSocket conn, String message ) {
		
		recievedMessage( message, conn);	
	}
	



//	public static void main( String[] args ) throws InterruptedException , IOException {
//		WebSocketImpl.DEBUG = true;
//		int port = 8887; // 843 flash policy port
//		try {
//			port = Integer.parseInt( args[ 0 ] );
//		} catch ( Exception ex ) {
//		}
//		IMS_Client_ChatServer s = new IMS_Client_ChatServer( port );
//		s.start();
//		System.out.println( "ChatServer started on port: " + s.getPort() );
//
//		//BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
//		while ( true ) {
////			String in = sysin.readLine();
////			s.reply( in );
////			if( in.equals( "exit" ) ) {
////				s.stop();
////				break;
////			} else if( in.equals( "restart" ) ) {
////				s.stop();
////				s.start();
////				break;
////			}
//		}
//	}
	
	
	@Override
	public void onError( WebSocket conn, Exception ex ) {
		ex.printStackTrace();
		if( conn != null ) {
			// some errors like port binding failed may not be assignable to a specific websocket
			System.out.println("Exception");
		}
	}

	/**
	 *  Sends the recieved message to GUI and updates using right formatting.
	 *  
	 * @param message (read through websocket)
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
						
						if( serverThread.getName() ==  null ){
							serverThread.setName(messageObject.getName());
						}
						
						serverThread.recievedMessage(messageObject.getMessage());			//Send message to correct thread.						
					}
				}
			}
		});
	}

}