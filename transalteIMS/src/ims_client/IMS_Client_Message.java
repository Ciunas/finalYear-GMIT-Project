package ims_client;

import java.util.List;

import org.java_websocket.WebSocket;

public class IMS_Client_Message {
	
	
	private String name;
	private List<String> messages;
	private String message;
	private WebSocket conn;

	//Getters an setters
	
	public WebSocket getConn() {
		return conn;
	}


	public void setConn(WebSocket conn) {
		this.conn = conn;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	
	public List<String> getMessages() {
		return messages;
	}
	
	
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
