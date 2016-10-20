package com.uow.assignment.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import com.uow.assignment.DAO.TicketsDAO;
import com.uow.assignment.model.Ticket;

public class TicketManager {
private TicketsDAO bugsDAO =  new TicketsDAO();
	
	public Ticket createNewTicket (Ticket bug) {
		Ticket ticket = bugsDAO.createNewTicket(bug);
		return ticket;
	}

	public void updateTicket(Ticket ticket) {
		bugsDAO.updateTicket(ticket);
	}
	
	public void updateTicketPriority(Ticket ticket) {
		bugsDAO.updateTicketPriority(ticket);
	}

	public void updateTicketStatus(Ticket ticket) {
		bugsDAO.updateTicketStatus(ticket);
	}

	public void updateTicketAssign(Ticket ticket) {
		bugsDAO.updateTicketAssign(ticket);		
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
