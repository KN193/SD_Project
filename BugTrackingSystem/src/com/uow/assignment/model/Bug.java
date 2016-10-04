package com.uow.assignment.model;

import java.util.Date;

public class Bug {

	private String ID, Description, Priority, Status;
	private String patch;
	private User assignedUser, reportedUser;
	private Date creationTime;
	
	public Bug(String description, String priority, String status,
			User assignedUser, User reportedUser, Date creationTime) {
		super();
		Description = description;
		Priority = priority;
		Status = status;
		this.assignedUser = assignedUser;
		this.reportedUser = reportedUser;
		this.creationTime = creationTime;
	}
	
	public Bug(){};
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
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPriority() {
		return Priority;
	}
	public void setPriority(String priority) {
		Priority = priority;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
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
}
