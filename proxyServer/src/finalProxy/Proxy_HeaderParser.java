package finalProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Proxy_HeaderParser {

	/**
	 * reads Header fields and parses data into a string array
	 * 
	 * @param bReader
	 *            connection to clinet
	 * @return string array containing header data
	 * @throws IOException
	 */
	public static String[] parser(BufferedReader bReader) throws IOException {

		String inputLine;
		String[] tokens = null;
		int count = 0;
		StringBuffer sb = new StringBuffer();

		// request from client
		while ((inputLine = bReader.readLine()) != null) {
			try {

				StringTokenizer tok = new StringTokenizer(inputLine);
				tok.nextToken();
			} catch (Exception e) {
				break;
			}
			// parse the first line of the request to find the url
			if (count == 0) {
				
				
				tokens = inputLine.split(" ");
				Proxy_GUI.displayInGui(tokens[0] + " Request for: " + tokens[1], "BLUE");
			} else {
				// Add the rest of the request This data is needed for a post request.
				sb.append(inputLine + "&&&");
			}

			count++;
		}
		tokens[2] = sb.toString();
		return tokens;
	}

}
