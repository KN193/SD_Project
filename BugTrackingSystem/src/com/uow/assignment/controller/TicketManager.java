package com.uow.assignment.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import com.uow.assignment.DAO.TicketsDAO;
import com.uow.assignment.model.Ticket;

public class TicketManager {
private TicketsDAO bugsDAO =  new TicketsDAO();
	
	public Ticket createNewBug (Ticket bug) {
		Ticket ticket = bugsDAO.createNewBug(bug);
		return ticket;
	}

	public void updateBug(Ticket ticket) {
		bugsDAO.updateBug(ticket);
	}
	
	public void updateBugPriority(Ticket ticket) {
		bugsDAO.updateBugPriority(ticket);
	}

	public void updateBugStatus(Ticket ticket) {
		bugsDAO.updateBugStatus(ticket);
	}

	public void updateBugAssign(Ticket ticket) {
		bugsDAO.updateBugAssign(ticket);		
	}

	public void addTicketPatch(Ticket ticket) {
		bugsDAO.addTicketPatch(ticket);	
	}
	
	public InputStream getTicketPatch(Ticket ticket) {
		return bugsDAO.getTicketPatch(ticket);
	}

	public ArrayList<Ticket> getAllTicket() {
		return bugsDAO.getAllTicket();
	}
	
	public ArrayList<Ticket> getTicketbyCriteria(Map<String, Object> criteria) {
		return bugsDAO.getTicketbyCriteria(criteria);
	}
}
