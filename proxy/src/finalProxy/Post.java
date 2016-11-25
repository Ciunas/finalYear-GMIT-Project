package finalProxy;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Post {

	
	public  static void postProcess(String [] data, DataOutputStream dataOut) throws Exception {
		
		//String urlCall = null;
		////String[] components = urlCall.split("\\?");
		System.out.println("Url to call:" + data[1]);
		URL url = new URL(data[1]);
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
				//System.out.println("content type set");
				
			}else if(temp[0].toLowerCase().equals("content-length:")){
				
				connection.setRequestProperty("Content-Length ", temp[1]);
				//System.out.println("content lenght set");
				
			}else if(temp[0].toLowerCase().equals("connection:")){
				
				connection.setRequestProperty("Connection ", temp[1]);
				contentLenght =  Integer.parseInt(temp[1]);
				//System.out.println("connection  set");
				
			}else if(temp[0].toLowerCase().equals("accept-language:")){
				System.out.println("Accept launguage set:"  +  temp[1]);
				//connection.setRequestProperty("Accept-Language ", temp[1]);
			
			}
		}
		
		connection.setRequestProperty("charset", "utf-8");
		
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		//dataOut.writeBytes(components[1]);
		dataOut.flush();
		InputStream is;
		
		boolean erred = false;
		if (connection.getResponseCode() >= 400) {
			is = connection.getErrorStream();
			erred = true;
		} else {
			is = connection.getInputStream();
		}
		int read;
		byte[] buffer = new byte[100];
		//DataOutputStream out = new DataOutputStream(client.getOutputStream());
		DataOutputStream err = null;
		if (erred) {
			// err = new DataOutputStream(new FileOutputStream(new File("error"
			// + generateRand(15) + ".html")));
		}
		while ((read = is.read(buffer)) != -1) {
			if (erred) {
				err.write(buffer, 0, read);
			} else {
				dataOut.write(buffer, 0, read);
			}
		}
		if (erred) {
			err.close();
			dataOut.writeBytes("HTTP/1.1 502 Bad Gateway\r\nProxy-Connection: keep-alive\r\n\r\n");
		}
		dataOut.flush();
		dataOut.close();

		wr.close();
		connection.disconnect();
	}
}
