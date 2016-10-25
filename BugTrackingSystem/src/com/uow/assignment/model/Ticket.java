package com.uow.assignment.model;

import java.io.File;
import java.util.Date;

public class Ticket {

	private String ID, description;
	private Component component;
	private Priority priority;
	private Status status;
	private File patch;
	private User assignedUser, reportedUser;
	private Date creationTime;
	private boolean isPatchAttached;
	
	public Ticket(String id, String description, Priority priority, Status status,
			User assignedUser, User reportedUser, Date creationTime, Component component,boolean isPatchAttached) {
		super();
		this.ID = id;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.assignedUser = assignedUser;
		this.reportedUser = reportedUser;
		this.creationTime = creationTime;
		this.component = component;
		this.isPatchAttached = isPatchAttached;
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
	public File getPatch() {
		return patch;
	}
	public void setPatch(File patch) {
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
	public boolean isPatchAttached() {
		return isPatchAttached;
	}

	public void setPatchAttached(boolean isPatchAttached) {
		this.isPatchAttached = isPatchAttached;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return ((Ticket)obj).getID().equals(this.ID);
	}
}
