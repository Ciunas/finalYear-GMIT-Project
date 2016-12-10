package finalProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class HeaderParser {

	
	public static String[]  parser(BufferedReader bReader) throws IOException {

		String inputLine;
		String[] tokens = null;
		int count = 0;
		String url;
		String requestType;
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
				requestType = tokens[0];
				url = tokens[1];
				ProxyGUI.displayInGui(requestType + " Request for: " + url);
				//System.out.println(requestType + " Request for: " + url);
			}else{
				//Add the rest of the request This data is needed for a post request.
				sb.append(inputLine + "&&&");
			}

			count++;
		}
		tokens[2]= sb.toString();
		return tokens;
	}

}
