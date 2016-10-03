package com.uow.assignment.model;

public class User {
	String userName, pwd, roles, ID;

	public User(String userName, String pwd, String roles, String iD) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.roles = roles;
		ID = iD;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public User(){};
	
	public User(String userName, String pwd, String roles) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.roles = roles;
	}
	
	public User(String userName, String pwd) {
		super();
		this.userName = userName;
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
