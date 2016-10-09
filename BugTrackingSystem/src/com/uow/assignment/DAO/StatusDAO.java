package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uow.assignment.model.Status;

public class StatusDAO {
	public ArrayList<Status> getAllStatus() {
		Connection conn = MySQLConnection.getConnection();
		try {
			ArrayList<Status> statuses = new ArrayList<Status>();
			PreparedStatement stt = conn.prepareStatement("SELECT * FROM Status");
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("ID");
				Status returnedStatus = new Status(id, name);
				statuses.add(returnedStatus);
			}
			stt.close();
			conn.close();
			
			return statuses;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
