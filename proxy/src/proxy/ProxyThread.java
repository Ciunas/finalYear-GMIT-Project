package proxy;


import java.net.*;
import java.io.*;
import java.util.*;
import proxy.proxyGUI;
//import proxy.httpsThread.StreamForwarder;

public class ProxyThread extends Thread {
	
    private Socket socket = null;
    private static final int BUFFER_SIZE = 32768;
    BufferedReader rd = null;
    
    public ProxyThread(Socket socket) {
        super("ProxyThread");
        this.socket = socket;
    }
    
    


    public void run() {
        //get input from user
        //send request to server
        //get response from server
        //send response to user

        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));

            String inputLine, outputLine;
            int cnt = 0;
            String urlToCall = "";
            String getPost = "";
            ///////////////////////////////////
            //begin get request from client
            while ((inputLine = in.readLine()) != null) {
                try {
                    StringTokenizer tok = new StringTokenizer(inputLine);
                    tok.nextToken();
                } catch (Exception e) {
                    break;
                }
                //parse the first line of the request to find the url
                if (cnt == 0) {
                    String[] tokens = inputLine.split(" ");
                    
                    getPost = tokens[0];
                    urlToCall = tokens[1];
                    //can redirect this to output log
                    System.out.println(getPost + " Request for : " + urlToCall);
                    proxyGUI.updateOutput(getPost +" Request for : " + urlToCall);
                    
                   
                }

                cnt++;
            }
            //end get request from client
            ///////////////////////////////////
            if(getPost.equalsIgnoreCase("CONNECT")){
            	System.out.println("CONNECT METHOD");
            	String[] tokens = urlToCall.split(":");
            	try {
        			Socket conn = new Socket(tokens[0], 443);
        			InputStream connIn = conn.getInputStream();
        			OutputStream connOut = conn.getOutputStream();
        			OutputStream out1 = socket.getOutputStream();
        			out1.write("HTTP/1.1 200 OK\n\n".getBytes());
        			out1.flush();
        			new StreamForwarder(connIn, out1).start();
        			System.out.println("out");
        			new StreamForwarder(socket.getInputStream(), connOut).start();

        		} catch (Exception e) {
        			e.printStackTrace();
        		}
 
            	
            }else{

            
            try {
                System.out.println("sending request to real server for url: " + urlToCall);
                proxyGUI.updateOutput("sending request to real server for url: " + urlToCall);
                ///////////////////////////////////
                //begin send request to server, get response from server
                URL url = new URL(urlToCall);
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                //not doing HTTP posts
                conn.setDoOutput(false);
                System.out.println("Type is: " + conn.getContentType());
                proxyGUI.updateOutput("Type is: " + conn.getContentType());
                System.out.println("content length: " + conn.getContentLength());
                proxyGUI.updateOutput("content length: " + conn.getContentLength());
                System.out.println("allowed user interaction: " + conn.getAllowUserInteraction());
                proxyGUI.updateOutput("allowed user interaction: " + conn.getAllowUserInteraction());
                System.out.println("content encoding: " + conn.getContentEncoding());
                proxyGUI.updateOutput("content encoding: " + conn.getContentEncoding());
                System.out.println("content type: " + conn.getContentType());
                proxyGUI.updateOutput("content type: " + conn.getContentType());

                // Get the response
                InputStream is = null;
                HttpURLConnection huc = (HttpURLConnection)conn;
                
                if (conn.getContentLength() > 0) {
                    try {
                        is = conn.getInputStream();
                        rd = new BufferedReader(new InputStreamReader(is));
                    } catch (IOException ioe) {
                        System.out.println("********* IO EXCEPTION **********: " + ioe);
                    }
                }
                //end send request to server, get response from server
                ///////////////////////////////////

                ///////////////////////////////////
                //begin send response to client
                byte by[] = new byte[ BUFFER_SIZE ];
                int index = is.read( by, 0, BUFFER_SIZE );
                while ( index != -1 )
                {
                  out.write( by, 0, index );
                  index = is.read( by, 0, BUFFER_SIZE );
                }
                out.flush();

                //end send response to client
                ///////////////////////////////////
            } catch (Exception e) {
                //can redirect this to error log
                System.err.println("Encountered exception: " + e);
                //proxyGUI.updateOutput("Encountered exception: " + e);
                //encountered error - just send nothing back, so
                //processing can continue
                out.writeBytes("");
            }
            }

            //close out all resources
            if (rd != null) {
                rd.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

	private class StreamForwarder extends Thread {

		private InputStream in;
		private OutputStream out;

		StreamForwarder(InputStream in, OutputStream out) {
			System.out.println("here");
			this.in = in;
			this.out = out;
		}

		@Override
		public void run() {
			int b;
			try {
				System.out.println("reading");
				while ((b = in.read()) != -1) {
					out.write(b);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
