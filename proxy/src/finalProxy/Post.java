package finalProxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {

	
	public  static void postProcess(String [] data, DataOutputStream toClient) throws Exception {
		
		//String urlCall = null;
		////String[] components = urlCall.split("\\?");
		
		String [] specDetailes = data[1].split("\\?");
		System.out.println("Post details:" + specDetailes[1]);
		URL url = new URL(specDetailes[0]);
		int contentLenght = 0;
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		

		String[] postData = data[2].split("&&&");
		
		for (int j = 0; j < postData.length; j++) {
			
			System.out.println("Line " + j + " " + postData[j]); 
			String [] temp = postData[j].split(" ");
			temp[1].replaceAll("\n", "");
			if(temp[0].toLowerCase().equals("content-type:")){
				
				connection.setRequestProperty("Content-Type ",temp[1]);
				System.out.println("content type set");
				
			}else if(temp[0].toLowerCase().equals("content-length:")){
				
				connection.setRequestProperty("Content-Length ", temp[1]);
				contentLenght =  Integer.parseInt(temp[1]);
				System.out.println("content lenght set");
				
			}else if(temp[0].toLowerCase().equals("connection:")){
				
				connection.setRequestProperty("Connection ", temp[1]);
				
				System.out.println("connection  set");
				
			}else if(temp[0].toLowerCase().equals("accept-language:")){
				System.out.println("Accept launguage set:"  +  temp[1]);
				//connection.setRequestProperty("Accept-Language ", temp[1]);
			
			}
		}
		
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		
		DataOutputStream dataOutServer = new DataOutputStream(connection.getOutputStream());
		DataInputStream dataInServer = new DataInputStream(connection.getInputStream());
		
		dataOutServer.writeBytes(specDetailes[1]);
//		int read;
//		byte[] buffer = new byte[100];
//		StringBuilder sb = new StringBuilder();
		final int SIZE = 32768;
		
		
		int responseCode = connection.getResponseCode();
		
		
		//send response to client
        byte by[] = new byte[ SIZE ];
        int index = dataInServer.read( by, 0, SIZE );
        
        while ( index != -1 )
        {
        	System.out.println("Inside send to client");
        	
        	toClient.write( by, 0, index );
        	index = dataInServer.read( by, 0, SIZE );
        }
        
        toClient.flush();
		
		
		
//		System.out.println("\nSending 'POST' request to URL : " + url);
//		System.out.println("Post parameters : " + urlParameters);
//		System.out.println("Response Code : " + responseCode);
//
//		BufferedReader dataInServer = new BufferedReader(
//		        new InputStreamReader(connection.getInputStream()));
//		String inputLine;
//		//StringBuffer response = new StringBuffer();
//
//		while ((inputLine = dataInServer.readLine()) != null) {
//			sb.append(inputLine);
//		}
//		System.out.println(responseCode);
//		
//		System.out.println(sb.toString());
//		dataInServer.close();
//		
//	
//
//	
//		String inputLine;
//		
//		dataOut.writeByte(sb.tb);
//		dataOut
//		 String line;
//	        while ( (line = dataInServer.readLine()) != null)
//	        	sb.append(line);
//	        
//	        System.out.println("Data returnd from serve: " + sb.toString());
//	        
//	        for(int i = 0; i<sb.length(); i++){
//	        	 dataOut.write(sb.toString());
//	        }
//	        
//		
//		while ((read = dataIn.read(buffer)) != -1) {
//		if (erred) {
//			err.write(buffer, 0, read);
//		} else {
//			dataOut.write(buffer, 0, read);
//		}
//	}
//		
//		
//		dataIn.flush();
//		InputStream is;
//		
//		boolean erred = false;
////		
//		if (connection.getResponseCode() >= 400) {
//			is = connection.getErrorStream();
//			erred = true;
//		} else {
//			is = connection.getInputStream();
//		}
//		int read;
//		byte[] buffer = new byte[100];
//		
//		//DataOutputStream out = new DataOutputStream(client.getOutputStream());
//		DataOutputStream err = null;
//		if (erred) {
//			// err = new DataOutputStream(new FileOutputStream(new File("error"
//			// + generateRand(15) + ".html")));
//		}
//		while ((read = is.read(buffer)) != -1) {
//			if (erred) {
//				err.write(buffer, 0, read);
//			} else {
//				dataOut.write(buffer, 0, read);
//			}
//		}
//		if (erred) {
//			err.close();
//			dataOut.writeBytes("HTTP/1.1 502 Bad Gateway\r\nProxy-Connection: keep-alive\r\n\r\n");
//		}
//		dataOut.flush();
//		dataOut.close();

	        dataOutServer.close();
	        dataInServer.close();
		connection.disconnect();
	}
}
