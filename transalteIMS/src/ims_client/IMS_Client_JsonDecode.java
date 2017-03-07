package ims_client;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class IMS_Client_JsonDecode {

	private IMS_Client_Message message = null;
	private String jsonInString;

	public IMS_Client_JsonDecode(String jsonInString) {
		super();
		this.jsonInString = jsonInString;
	}

	// Convert string to a message JSON Object.
	public IMS_Client_Message decodeFormString() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			message = mapper.readValue(jsonInString, IMS_Client_Message.class);	// Convert JSON string to Object
		} catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}
}
