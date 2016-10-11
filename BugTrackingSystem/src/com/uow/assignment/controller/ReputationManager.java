package com.uow.assignment.controller;

import com.uow.assignment.DAO.ReputationDAO;
import com.uow.assignment.model.Reputation;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;

public class ReputationManager {

	private ReputationDAO repDAO = new ReputationDAO();
	
	public void addReputation(Ticket ticket, User user, boolean likeOrDislike) {
		repDAO.addReputation(ticket, user, likeOrDislike);
	}
	
	public Reputation checkReputation(Ticket ticket, User user) {
		return repDAO.checkReputation(ticket, user);
	}

	public void updateReputation(Ticket ticket, User crrUsr, boolean likeOrDislike) {
		repDAO.updateReputation(ticket, crrUsr, likeOrDislike);
	}

	public void removeReputation(Ticket ticket, User crrUsr) {
		repDAO.removeReputation(ticket, crrUsr);
	}

	public int countLikeReputation(User crrUsr, boolean likeOrDislike) {
		return repDAO.countReputationForUser(crrUsr, likeOrDislike);
	}
	
}
