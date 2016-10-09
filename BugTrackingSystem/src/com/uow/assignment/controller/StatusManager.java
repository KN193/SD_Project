package com.uow.assignment.controller;

import java.util.ArrayList;

import com.uow.assignment.DAO.StatusDAO;
import com.uow.assignment.model.Status;

public class StatusManager {

	private static ArrayList<Status> allStatus = new ArrayList<Status>();
	private StatusDAO statusDAO =  new StatusDAO();
	
	public StatusManager() {
		// initialize
		if (allStatus.size() == 0)
		allStatus = statusDAO.getAllStatus();
	}
	
	public ArrayList<Status> getAllStatuses() {
		return allStatus;
	}

	public Status getStatusByName(String sts) {
		for (Status s : allStatus) {
			if (s.getName().equalsIgnoreCase(sts))
				return s;
		}
		
		return null;
	}
	
	public Status getStatusByID(int id) {
		for (Status s : allStatus) {
			if (s.getID() == id)
				return s;
		}
		
		return null;
	}

	public String[] getAllStatusesList() {
		String[] list = new String[allStatus.size()];
		int counter = 0;
		for (Status s : allStatus) {
			list[counter] = s.getName();
			counter++;
		}
		
		return list;
	}
}
