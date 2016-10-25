package com.uow.assignment.model;

public class Status {

	private int ID;
	private String name;
	public Status(){};
	public Status(int iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	public boolean equals(Object obj) {
		return (((Status)obj).getName().equals(name));
	}
	
}
