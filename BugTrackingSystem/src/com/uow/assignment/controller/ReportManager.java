package com.uow.assignment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.jfree.data.general.DefaultKeyedValues2DDataset;

import com.uow.assignment.DAO.ReportDAO;
import com.uow.assignment.model.Comment;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;

public class ReportManager {
	private ReportDAO reportDAO = new ReportDAO();

	public Map<String, Integer> getTicketProportationByTime(Date startDate, Date endDate)  {
		return reportDAO.getTicketProportationByTime(startDate, endDate);
	}

	public DefaultKeyedValues2DDataset getUserReputation() {
		// TODO Auto-generated method stub
		return reportDAO.getUserReputation();
	}
}
