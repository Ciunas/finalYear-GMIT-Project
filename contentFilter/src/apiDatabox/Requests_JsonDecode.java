package apiDatabox;

import java.io.IOException; 
import org.codehaus.jackson.map.ObjectMapper;


/**
 * @author ciunas
 *
 */

public class Requests_JsonDecode {

	private String jsonInString;

	public Requests_JsonDecode(String jsonInString) {
		super();
		this.jsonInString = jsonInString;
	}


	/**
	 * Convert string to a Message JSON Object.
	 * @return Requests_Message Object from decoded string
	 */
	public Requests_Message decodeFormString() {

	    Requests_Message message = null;
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(jsonInString);
		try {
			message = mapper.readValue(jsonInString,  Requests_Message.class);	// Convert JSON string to Object
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
}