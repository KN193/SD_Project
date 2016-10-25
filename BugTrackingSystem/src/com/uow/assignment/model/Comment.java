package com.uow.assignment.model;

import java.util.Date;

public class Comment {

	private int ID;
	private String content;
	private Date createdDate;
	private User commentUser;
	private Ticket ticketCommented;
	public Comment(){};
	public Comment(String content, int ID, Date createdDate,
			User commentUser, Ticket ticketCommented) {
		super();
		this.content = content;
		this.ID = ID;
		this.createdDate = createdDate;
		this.commentUser = commentUser;
		this.ticketCommented = ticketCommented;
	}
	public Ticket getTicketCommented() {
		return ticketCommented;
	}
	public void setTicketCommented(Ticket ticketCommented) {
		this.ticketCommented = ticketCommented;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}
	
}
