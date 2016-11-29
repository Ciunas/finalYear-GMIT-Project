package finalProxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Post {

	public static void postProcess(String[] data, DataOutputStream toClient) throws Exception {

		String[] specDetailes;
		URL url = null;
		
		if (data[1].contains("?")) {
			specDetailes = data[1].split("\\?");
			System.out.println("Post details: " + specDetailes[1]);
			url = new URL(specDetailes[0]);
		} else {
			
			
			System.out.println("no extra details");
			specDetailes = "empty?empty".split("\\?");
			url = new URL(data[1]);
			System.out.println("Url: " + url );
		}

		int contentLenght = 0;
		
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");

		String[] postData = data[2].split("&&&");
		System.out.println("Number of Post data fields: " + postData.length);
		for (int j = 0; j < postData.length; j++) {

			System.out.println("Line " + j + " " + postData[j]);
			String[] temp = postData[j].split(" ");
			temp[1].replaceAll("\n", "");
			
			if (temp[0].toLowerCase().equals("content-type:")) {

				connection.setRequestProperty("Content-Type ", temp[1]);
				System.out.println("content type set" + temp[1]);

			} else if (temp[0].toLowerCase().equals("content-length:")) {

				connection.setRequestProperty("Content-Length ", temp[1]);
				contentLenght = Integer.parseInt(temp[1]);
				System.out.println("content lenght set");

			} else if (temp[0].toLowerCase().equals("connection:")) {

				connection.setRequestProperty("Connection ", temp[1]);

				System.out.println("connection set " + temp[1]);

			} else if (temp[0].toLowerCase().equals("accept-language:")) {
				System.out.println("Accept launguage set:" + temp[1]);
				connection.setRequestProperty("Accept-Language ", temp[1]);

			}
			else if (temp[0].toLowerCase().equals("user-agent:")) {
				System.out.println("User Agent: "  + temp[1]);
				connection.setRequestProperty("User-Agent ", temp[1]);

			}
			else if (temp[0].toLowerCase().equals("accept-encoding:")) {
				System.out.println("Accept-Encoding: "  + temp[1]);
				connection.setRequestProperty("Accept-Encoding ", temp[1]);

			}
			else if (temp[0].toLowerCase().equals("host:")) {
				System.out.println("Host: "  + temp[1]);
				connection.setRequestProperty("Host ", temp[1]);

			}
			else if (temp[0].toLowerCase().equals("charset:")) {
				System.out.println("charset: "  + temp[1]);
				connection.setRequestProperty("charset ", temp[1]);

			}
			else if (temp[0].toLowerCase().equals("accept:")) {
				System.out.println("Accept: "  + temp[1]);
				connection.setRequestProperty("Accept ", temp[1]);

			}
			else if (temp[0].toLowerCase().equals("dnt:")) {
				System.out.println("DNT: "  + temp[1]);
				connection.setRequestProperty("DNT ", temp[1]);

			}
			
			
		}

		DataOutputStream dataOutServer = new DataOutputStream(connection.getOutputStream());
		System.out.println("here2");
		DataInputStream dataInServer = new DataInputStream(connection.getInputStream());

		System.out.println("here!");
		
		if(specDetailes[1].equals("empty")){
			System.out.println("No details. ");
			dataOutServer.writeBytes("");
		}else{
			System.out.println("SpecDetails: " + specDetailes[1]);
			dataOutServer.writeBytes(specDetailes[1]);
		}
		final int SIZE = 32768;

		// int responseCode = connection.getResponseCode();

		// send response to client
		byte by[] = new byte[SIZE];
		int index = dataInServer.read(by, 0, SIZE);

		while (index != -1) {
			System.out.println("Inside send to client");

			toClient.write(by, 0, index);
			index = dataInServer.read(by, 0, SIZE);
		}
		
		while (contentLenght != -1) {
			System.out.println("Inside send to client: content lenght");

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
