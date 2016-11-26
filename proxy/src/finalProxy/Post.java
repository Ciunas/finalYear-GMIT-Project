package finalProxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {

	public static void postProcess(String[] data, DataOutputStream toClient) throws Exception {

		// String urlCall = null;
		//// String[] components = urlCall.split("\\?");

		String[] specDetailes = data[1].split("\\?");
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
			String[] temp = postData[j].split(" ");
			temp[1].replaceAll("\n", "");
			if (temp[0].toLowerCase().equals("content-type:")) {

				connection.setRequestProperty("Content-Type ", temp[1]);
				System.out.println("content type set");

			} else if (temp[0].toLowerCase().equals("content-length:")) {

				connection.setRequestProperty("Content-Length ", temp[1]);
				contentLenght = Integer.parseInt(temp[1]);
				System.out.println("content lenght set");

			} else if (temp[0].toLowerCase().equals("connection:")) {

				connection.setRequestProperty("Connection ", temp[1]);

				// System.out.println("connection set");

			} else if (temp[0].toLowerCase().equals("accept-language:")) {
				System.out.println("Accept launguage set:" + temp[1]);
				connection.setRequestProperty("Accept-Language ", temp[1]);

			}
		}

		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");

		DataOutputStream dataOutServer = new DataOutputStream(connection.getOutputStream());
		DataInputStream dataInServer = new DataInputStream(connection.getInputStream());

		dataOutServer.writeBytes(specDetailes[1]);
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
		toClient.flush();

		dataOutServer.close();
		dataInServer.close();
		connection.disconnect();
	}
}
