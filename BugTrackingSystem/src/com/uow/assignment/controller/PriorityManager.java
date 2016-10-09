package com.uow.assignment.controller;

import java.util.ArrayList;

import com.uow.assignment.DAO.PriorityDAO;
import com.uow.assignment.model.Priority;

public class PriorityManager {
	private static ArrayList<Priority> allPriorities = new ArrayList<Priority>();
	private PriorityDAO priorityDAO =  new PriorityDAO();
	
	public PriorityManager() {
		// initialize
		if (allPriorities.size() == 0)
			allPriorities = priorityDAO.getAllPriority();
	}
	
	public String[] getPriorityList() {
		String[] list = new String[allPriorities.size()];
		int counter = 0;
		for (Priority p : allPriorities) {
			list[counter] = p.getName();
			counter++;
		}
		
		return list;
	}

	public Priority getPriorityByName(String pri) {
		for (Priority s : allPriorities) {
			if (s.getName().equalsIgnoreCase(pri))
				return s;
		}
		
		return null;
	}
	
	public Priority getPriorityByID(int id) {
		for (Priority s : allPriorities) {
			if (s.getID() == id)
				return s;
		}
		
		return null;
	}
}
