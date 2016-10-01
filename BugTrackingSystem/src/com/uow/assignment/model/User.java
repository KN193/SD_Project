package com.uow.assignment.model;

public class User {
	String userName, pwd, roles;

	public User(){};
	
	public User(String userName, String pwd, String roles) {
		super();
		this.userName = userName;
		this.pwd = pwd;
		this.roles = roles;
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
