package finalProxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL; 

public class Proxy_Post {

	public static void postProcess(String[] data, DataOutputStream toClient) throws Exception {

		String[] specDetailes;
		URL url = null;
		
		if (data[1].contains("?")) {
			specDetailes = data[1].split("\\?");
			Proxy_GUI.displayInGui("Post details: " + specDetailes[1]);
			url = new URL(specDetailes[0]);
		} else {
			specDetailes = "empty?empty".split("\\?");
			url = new URL(data[1]);
			Proxy_GUI.displayInGui("Url: " + url );
		}

	
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");

		String[] postData = data[2].split("&&&");
		for (int j = 0; j < postData.length; j++) {

			String[] temp = postData[j].split(":");
			
			temp[1] = temp[1].replaceAll("\n", "");
			
			if (temp[0].toLowerCase().equals("content-type")) {
				connection.setRequestProperty("Content-Type", temp[1]);
				Proxy_GUI.displayInGui("content type set" + temp[1]);
				//System.out.println("content type set" + temp[1]);

			} else if (temp[0].toLowerCase().equals("content-length")) {
				int contentLenght = 0;
				connection.setRequestProperty("Content-Length", temp[1]);
				temp[1] = temp[1].replaceAll("\\s","");
				contentLenght = temp[1].toString().length();
				Proxy_GUI.displayInGui("Content lenght set " + temp[1].toString().length());
				//System.out.println("content lenght set " + temp[1].toString().length());

			} else if (temp[0].toLowerCase().equals("connection")) {
				connection.setRequestProperty("Connection", temp[1]);
				Proxy_GUI.displayInGui("Connection set " + temp[1]);
				//System.out.println("connection set " + temp[1]);
				

			} else if (temp[0].toLowerCase().equals("accept-language")) {
				connection.setRequestProperty("Accept-Language", temp[1]);
				Proxy_GUI.displayInGui("Accept launguage set" + temp[1]);
				//System.out.println("Accept launguage set" + temp[1]);
				
				
			} else if (temp[0].toLowerCase().equals("user-agent")) {
				//System.out.println("User Agent: "  + temp[1]);
				connection.setRequestProperty("User-Agent", temp[1]);
				Proxy_GUI.displayInGui("User Agent: "  + temp[1]);

				
			} else if (temp[0].toLowerCase().equals("accept-encoding")) {
				//System.out.println("Accept-Encoding: "  + temp[1]);
				connection.setRequestProperty("Accept-Encoding", temp[1]);
				Proxy_GUI.displayInGui("Accept-Encoding: "  + temp[1]);

				
			} else if (temp[0].toLowerCase().equals("host")) {
				//System.out.println("Host: "  + temp[1]);
				connection.setRequestProperty("Host", temp[1]);
				Proxy_GUI.displayInGui("Host: "  + temp[1]);

				
			} else if (temp[0].toLowerCase().equals("charset")) {
				//System.out.println("charset: "  + temp[1]);
				connection.setRequestProperty("charset", temp[1]);
				Proxy_GUI.displayInGui("Charset: "  + temp[1]);

				
			} else if (temp[0].toLowerCase().equals("accept")) {
				//System.out.println("Accept: "  + temp[1]);
				connection.setRequestProperty("Accept", temp[1]);
				Proxy_GUI.displayInGui("Accept: "  + temp[1]);

				
			} else if (temp[0].toLowerCase().equals("dnt")) {
				//System.out.println("DNT:"  + temp[1]);
				connection.setRequestProperty("DNT", temp[1]);
				Proxy_GUI.displayInGui("DNT:"  + temp[1]);
			}	
		}

	
		DataOutputStream dataOutServer = new DataOutputStream(connection.getOutputStream());
	
		if(specDetailes[1].equals("empty")){
			dataOutServer.writeBytes("");
			
		}else{
			dataOutServer.writeBytes(specDetailes[1]);
		}
		
		final int SIZE = 32768;

		int responseCode = connection.getResponseCode();
		
		// send response to client
		byte by[] = new byte[SIZE];
		DataInputStream dataInServer = new DataInputStream(connection.getInputStream());
		int index = dataInServer.read(by, 0, SIZE);

		
		while (index != -1) {
			Proxy_GUI.displayInGui("Sending  Data to Client");
			toClient.write(by, 0, index);
			index = dataInServer.read(by, 0, SIZE);
		}
		
		toClient.flush();
		
		toClient.close();
		dataOutServer.close();
		dataInServer.close();
		connection.disconnect();
	}
}
