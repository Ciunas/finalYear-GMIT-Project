package ims_user;

import java.util.ArrayList;
import java.util.List;

public class IMS_User {
	
	private String launguage;
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
	private String name;
	private String password;
	private List<String> labels = new ArrayList<String>();
	
	
	public IMS_User(String name, String password, String launguage) {
		super();
		this.name = name;
		this.password = password;
		this.launguage = launguage;
	}
	
	
	public IMS_User() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}