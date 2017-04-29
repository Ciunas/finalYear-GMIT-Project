package cosineDatabox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
 

/**
 * @author ciunas
 *
 */

public class Requests_Synonyms {
	
	/**
	 * Default constructor
	 * 
	 * no args
	 */
	public Requests_Synonyms(){
		
	}
 
	
	public static void main(String[] args) {

		Requests_Synonyms rq = new Requests_Synonyms();
		String[] parts = rq.requests_Synonyms("car", "en_US", "password", "xml");
		System.out.println(parts[2]);
	}
	

	/**
	 * requests_Synonyms
	 * @param word  to get synonoums for
	 * @param language  to check word in
	 * @param key users API key 
	 * @param output format of returned data xml or json
	 * @return a String array containing synonoums of word passed to method
	 */
	public String[] requests_Synonyms(String word, String language, String key, String output) {
		
		String[] parts = null;
		try {
			URL serverAddress = new URL("http://thesaurus.altervista.org/thesaurus/v1" + "?word=" + URLEncoder.encode(word, "UTF-8") + "&language=" + language
					+ "&key=" + key + "&output=" + output);
			HttpURLConnection connection = (HttpURLConnection) serverAddress.openConnection();
			connection.connect();
			int rc = connection.getResponseCode();
			if (rc == 200) {
				String line = null;
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				int i =1;
				String temp = null;
				while ((line = br.readLine()) != null){
					if( i++ == 3){						
						temp = (line);						
					} 
				} 
				temp =  temp.replace("<list><category>(noun)</category><synonyms>", "").replace("</synonyms></list>", "");				
				parts = temp.split("\\|"); 
			
			} else
				System.out.println("HTTP error:" + rc);
			connection.disconnect();
		} catch (java.net.MalformedURLException e) {
			e.printStackTrace();
		} catch (java.net.ProtocolException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return parts;
	}
}
