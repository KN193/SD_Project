package com.uow.assignment.controller;

import com.uow.assignment.DAO.BugsDAO;
import com.uow.assignment.model.Bug;

public class BugManager {
private BugsDAO bugsDAO =  new BugsDAO();
	
	public void createNewBug (Bug bug) {
		bugsDAO.createNewBug(bug);
	}
}
