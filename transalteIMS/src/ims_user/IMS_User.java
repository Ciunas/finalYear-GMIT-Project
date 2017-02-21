package ims_user;

import java.util.ArrayList;
import java.util.List;

public class IMS_User {
	
	private String launguage;

	private String name;
	private byte[] password;
	private byte[] salt;
	private List<String> labels = new ArrayList<String>();
	
	
	public IMS_User(String name, byte[] password, String launguage, byte[] salt) {
		super();
		this.name = name;
		this.password = password;
		this.launguage = launguage;
		this.salt = salt;
	}

	
	//Getter and setters
	
	public IMS_User(String name) {
		super();
		this.name = name;
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


	public void setLabels(List<String> labels) {
		this.labels = labels;
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