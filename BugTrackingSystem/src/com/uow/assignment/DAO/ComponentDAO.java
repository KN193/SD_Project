package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uow.assignment.model.Component;

public class ComponentDAO {
	public ArrayList<Component> getAllComponent() {
		Connection conn = MySQLConnection.getConnection();
		try {
			ArrayList<Component> components = new ArrayList<Component>();
			PreparedStatement stt = conn.prepareStatement("SELECT * FROM Component");
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("ID");
				Component returnedCom = new Component(id, name);
				components.add(returnedCom);
			}
			stt.close();
			conn.close();
			
			return components;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
