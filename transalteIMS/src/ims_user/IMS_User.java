package ims_user;

import java.util.ArrayList;
import java.util.List;

public class IMS_User {
	
	
	private String name;
	private String password;
	private List<String> labels = new ArrayList<String>();
	
	
	public IMS_User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
	
	public IMS_User() {
		// TODO Auto-generated constructor stub
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