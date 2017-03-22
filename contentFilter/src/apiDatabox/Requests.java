package apiDatabox;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author ciunas
 */

public class Requests {

	

	public Requests() {
	}

	public boolean creteRequest(String text2Classify) throws MalformedURLException, IOException {

		String key = "";
		URL url = new URL("http://api.datumbox.com/1.0/AdultContentDetection.json");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int i;

		InputStream is = postURL(connection, url, "api_key=" + key + "&text=" + text2Classify);
		StringBuilder sb = new StringBuilder();
		while ((i = is.read()) != -1) {
			sb.append((char) i);
		}

		Requests_JsonDecode rjdc = new Requests_JsonDecode(sb.toString());
		Requests_Message rm = rjdc.decodeFormString();

		if (rm.output.toString().contains("noadult")) {
			return true;
		}
		return false;
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {

		Requests rq = new Requests();
		rq.creteRequest("Hello");

	}

	public static InputStream postURL(HttpURLConnection connection, URL url, String urlParameters)
			throws MalformedURLException, IOException {

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
		connection.setUseCaches(false);

		DataOutputStream wr = null;

		try {
			wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
		} finally {
			wr.close();
		}
		InputStream is = connection.getInputStream();
		return is;
	}
}
