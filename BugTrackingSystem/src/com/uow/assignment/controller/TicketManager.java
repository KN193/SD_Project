package com.uow.assignment.controller;

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
}
