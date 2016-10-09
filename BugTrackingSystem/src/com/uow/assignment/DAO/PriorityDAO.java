package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uow.assignment.model.Priority;

public class PriorityDAO {
	public ArrayList<Priority> getAllPriority() {
		Connection conn = MySQLConnection.getConnection();
		try {
			ArrayList<Priority> priorities = new ArrayList<Priority>();
			PreparedStatement stt = conn.prepareStatement("SELECT * FROM Priority");
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("ID");
				Priority returnedPri = new Priority(id, name);
				priorities.add(returnedPri);
			}
			stt.close();
			conn.close();
			
			return priorities;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
