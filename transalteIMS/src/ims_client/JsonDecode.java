package ims_client;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonDecode {

	private Message message = null;
	private String jsonInString;

	public JsonDecode(String jsonInString) {
		super();
		this.jsonInString = jsonInString;
	}

	// Convert string to a message JSON Object.
	public Message decodeFormString() {

		ObjectMapper mapper = new ObjectMapper();

		try {
			message = mapper.readValue(jsonInString, Message.class);	// Convert JSON string to Object
		} catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}
}
