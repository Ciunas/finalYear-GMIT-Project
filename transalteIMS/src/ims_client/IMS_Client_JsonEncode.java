package ims_client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author ciunas
 *
 */
public class IMS_Client_JsonEncode {

	private String jsonInString;
	IMS_Client_Message data;

	/**
	 * used to set local IMS_Clinet object.
	 * @param data IMS_User Object
	 */
	public IMS_Client_JsonEncode(IMS_Client_Message data) {
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