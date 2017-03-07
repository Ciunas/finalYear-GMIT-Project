package ims_client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class IMS_Client_JsonEncode {

	private String jsonInString;
	IMS_Client_Message data;

	public IMS_Client_JsonEncode(IMS_Client_Message data) {
		this.data = data;

	}

	public String encodeToString() {
		ObjectMapper mapper = new ObjectMapper();

		try {

			jsonInString = mapper.writeValueAsString(data);
			//System.out.println(jsonInString);

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