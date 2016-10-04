package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.uow.assignment.model.Bug;

public class BugsDAO {

	public void createNewBug(Bug bug) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("INSERT INTO Bugs (description, priority, status, assignedUser, reportedUser, creationDate) values(?,?,?,?,?,?)");
			stt.setString(1, bug.getDescription());
			stt.setString(2, bug.getPriority());
			stt.setString(3, bug.getStatus());
			stt.setInt(4, Integer.parseInt(bug.getAssignedUser().getID()));
			stt.setInt(5, Integer.parseInt(bug.getReportedUser().getID()));
			stt.setDate(6, new java.sql.Date(bug.getCreationTime().getTime()));
			
			stt.executeUpdate();
			stt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
