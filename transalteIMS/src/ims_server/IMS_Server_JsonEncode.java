package ims_server;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper; 
import ims_user.IMS_User;

/*
/**
 * @author ciunas
 *
 */

public class IMS_Server_JsonEncode {
	
	private String jsonInString;
	IMS_User data;

	/**
	 * used to set local IMS_Clinet object.
	 * @param data IMS_User Object
	 */
	public IMS_Server_JsonEncode(IMS_User data) {
		this.data = data;

	}

	/**
	 * generates a Objectmapper and enocdes IMS_Clinet message object into a string
	 * @return the string of the message object
	 */
	public String encodeToString() {
		
		ObjectMapper mapper = new ObjectMapper();

		try {

			jsonInString = mapper.writeValueAsString(data);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonInString;
	}
}
