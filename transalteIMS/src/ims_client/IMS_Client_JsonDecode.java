package ims_client;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import ims_user.IMS_User;

/**
 * @author ciunas
 *
 */
public class IMS_Client_JsonDecode {

	private IMS_Client_Message message = null;
	private IMS_User user = null;
	private String jsonInString;

	public IMS_Client_JsonDecode(String jsonInString) {
		super();
		this.jsonInString = jsonInString;
	}


	/**
	 * Convert string to a Message JSON Object.
	 * @return IMS_CLient_message Object from decoded string
	 */
	public IMS_Client_Message decodeFormString() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			message = mapper.readValue(jsonInString, IMS_Client_Message.class);	// Convert JSON string to Object
		} catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}
	
	/**
	 * Convert string to a Message JSON Object.
	 * @return IMS_CLient_message Object from decoded string
	 */
	public IMS_User decodeFormStringIMS_User() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			user = mapper.readValue(jsonInString, IMS_User.class);	// Convert JSON string to Object
		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;
	}
}
