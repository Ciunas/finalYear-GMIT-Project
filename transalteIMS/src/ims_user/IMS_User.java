package ims_user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IMS_User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String launguage;
	private String name;
	private byte[] password;
	private byte[] salt;
	public List<String> labels = new ArrayList<String>();
	public List<String> onlineUsers = new ArrayList<String>();
	private int status;
	private String ip;
	
	
	
	public IMS_User(String name, String launguage, byte[] password, byte[] salt, int status, String ip) {
		super();
		this.name = name;
		this.password = password;
		this.launguage = launguage;
		this.salt = salt;
		this.ip = ip;
		this.status = status;
	}
	
	public IMS_User(String name, int status, String ip) {
		super();
		this.name = name;
		this.status = status;
		this.ip = ip;
	}
	
	
	public IMS_User(String name) {
		this.name = name;
	}

	
	//Getter and setters
	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public byte[] getSalt() {
		return salt;
	}


	public void setSalt(byte[] salt) {
		this.salt = salt;
	}


	public String getLaunguage() {
		return launguage;
	}


	public void setLaunguage(String launguage) {
		this.launguage = launguage;
	}


	public List<String> getLabels() {
		return labels;
	}
	
	
	public String getLabel(int a ){
		
		return labels.get(a);
	}


	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	
	public List<String> getOnlineUsers() {
		return onlineUsers;
	}
	
	
	public String getOnlineUsers(int a ){
		
		return onlineUsers.get(a);
	}


	public void setOnlineUsers(List<String> labels) {
		this.onlineUsers = labels;
	}
	
	
	
	
	public IMS_User() {
	}



	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public byte[] getPassword() {
		return password;
	}
	
	
	public void setPassword(byte[] password) {
		this.password = password;
	}

}