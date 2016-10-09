package com.uow.assignment.model;

import java.util.Date;

public class Ticket {

	private String ID, description;
	private Component component;
	private Priority priority;
	private Status status;
	private String patch;
	private User assignedUser, reportedUser;
	private Date creationTime;
	
	public Ticket(String id, String description, Priority priority, Status status,
			User assignedUser, User reportedUser, Date creationTime, Component component) {
		super();
		this.ID = id;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.assignedUser = assignedUser;
		this.reportedUser = reportedUser;
		this.creationTime = creationTime;
		this.component = component;
	}
	
	public Ticket(){};
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getPatch() {
		return patch;
	}
	public void setPatch(String patch) {
		this.patch = patch;
	}
	public User getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}
	public User getReportedUser() {
		return reportedUser;
	}
	public void setReportedUser(User reportedUser) {
		this.reportedUser = reportedUser;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
}
