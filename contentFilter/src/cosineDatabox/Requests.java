package cosineDatabox;

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

	/**
	 * Create a POST to datumbox, sets all required detials and returns if string is safe or not.
	 * @param text2Classify text you want checked in adult filter.
	 * @return bolean, true if safe for chrildren, false if not.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public boolean creteRequest(String text2Classify) throws MalformedURLException, IOException {

		String key = "";
		URL url = new URL("http://api.datumbox.com/1.0/AdultContentDetection.json");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		int i;

		InputStream is = postURL(connection, url, "api_key=19d40e1166d2fc0d3b1f8b3315b2ba91" + key + "&text=" + text2Classify);
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

	 
	public static void main(String[] args) throws MalformedURLException, IOException {

		Requests rq = new Requests();
		rq.creteRequest("Hello");

	}

	/**
	 * POSTS to datumbox the string(s) that are to be catagoriesd as safe or unsafe
	 * @param connection	Connection to datumbox.
	 * @param url datumbox address
	 * @param urlParameters APIKEY and string to check
	 * @return returns  an Inputstream connected to datumbox
	 * @throws MalformedURLException
	 * @throws IOException
	 */
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
