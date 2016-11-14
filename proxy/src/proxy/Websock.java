package proxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Websock   {
	
	
    
	public static void main(String[] args) throws IOException {
		Websock();
	  }
	
	@SuppressWarnings("null")
	public static void  Websock() throws IOException{

	HttpServletResponse response = null;
	HttpServletRequest request = null;
	
	request.getRequestURI();
	
	System.out.println("here");
	InetSocketAddress address =  InetSocketAddress("www.google.ie", 80);
			//getRequestedAddress(request);
    try (SocketChannel targetChannel = SocketChannel.open();) {
    	System.out.println("here1");
        targetChannel.socket().setTcpNoDelay(true);
        targetChannel.configureBlocking(false);
        targetChannel.connect(address);
        
        System.out.println("here2");
        
        EndPoint downstreamEndPoint = HttpConnection.getCurrentConnection().getEndPoint();
        System.out.println("here2.1");
        java.nio.channels.SocketChannel requestChannel = (java.nio.channels.SocketChannel)downstreamEndPoint.getTransport();
        
        
        System.out.println("here3");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().close();
        response.setStatus(HttpServletResponse.SC_SWITCHING_PROTOCOLS);
        
        Selector selector = Selector.open();
        SelectionKey targetKey = targetChannel.register(selector, SelectionKey.OP_READ);
        SelectionKey requestKey = requestChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("here4");
        
        while (true) {
        	System.out.println("here");
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            if(selectedKeys.size() == 0) continue;
            if (selectedKeys.contains(targetKey)) {
                int read = copyData(targetChannel,requestChannel);
                selectedKeys.remove(targetKey);
                if (read==-1) {
                    break;
                }
            }
            if (selectedKeys.contains(requestKey)) {
                int read = copyData(requestChannel,targetChannel);
                selectedKeys.remove(requestKey);
                if (read==-1) {
                    break;
                }
            }
        }
    }

	}
	
	
    private static int copyData(SocketChannel source, SocketChannel target) throws IOException  {
        ByteBuffer buffer = ByteBuffer.allocate(16384);
        buffer.clear();
        int read = source.read(buffer);
        buffer.flip();
        if (buffer.limit()>0) {
            target.write(buffer);
        }
        return read;
    }
        

}
