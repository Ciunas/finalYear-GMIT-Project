package applet;

import java.io.Serializable;

public class Rules implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String name;				//User name for rule
	String type;				//TCP or UDP
	String direction; 			//In or Out direction
	int port; 					//Port number 
	String ip; 					//ip to block
	
	//Constructor
	public Rules() {
		super();
	}
	
	//Constructor using all parameters
	public Rules(String name, String type, String direction, int port, String ip) {
		super();
		this.name = name;
		this.type = type;
		this.direction = direction;
		this.port = port;
		this.ip = ip;
	}

	
	
	/*
	* Getters and setters
	*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
