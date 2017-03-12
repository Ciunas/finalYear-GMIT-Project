package ims_client;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import ims_user.IMS_User;


/**
 * @author ciunas
 *
 */

public class IMS_Clinet_JsonDecodeOnlineUsers {

	private IMS_User object = null;
	private String jsonInString;

	public IMS_Clinet_JsonDecodeOnlineUsers(String jsonInString) {
		super();
		this.jsonInString = jsonInString;
	}


	/**
	 * Convert string to a Message JSON Object.
	 * @return IMS_CLient_message Object from decoded string
	 */
	public IMS_User decodeFormString() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			object = mapper.readValue(jsonInString, IMS_User.class);	// Convert JSON string to Object
		} catch (IOException e) {
			e.printStackTrace();
		}

		return object;
	}
}
