package finalProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class HeaderParser {

	
	public static String[] parser(BufferedReader bReader) throws IOException {

		String inputLine;
		String[] tokens = null;
		int count = 0;
		String url;
		String requestType;
		///////////////////////////////////
		// begin get request from client
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
				requestType = tokens[0];
				url = tokens[1];
				System.out.println(requestType + " Request for: " + url);
			}

			count++;
		}
		return tokens;
	}

}
