package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.uow.assignment.model.User;

public class UsersDAO {

	public void createUser(User usr) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("INSERT INTO Users (userName, password, roles) values(?,?,?)");
			stt.setString(1, usr.getUserName());
			stt.setString(2, usr.getPwd());
			stt.setString(3, usr.getRoles());
			
			stt.executeUpdate();
			stt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public User loginUser(User usr) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("SELECT * FROM Users WHERE userName=? and password=?");
			stt.setString(1, usr.getUserName());
			stt.setString(2, usr.getPwd());
			User returnedUser = null;
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				String usrName = rs.getString("userName");
				String roles = rs.getString("roles");
				int id = rs.getInt("ID");
				returnedUser = new User();
				returnedUser.setRoles(roles);
				returnedUser.setUserName(usrName);
				returnedUser.setID(id+"");
			}
			stt.close();
			conn.close();
			
			return returnedUser;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public ArrayList<User> getAllUser() {
		Connection conn = MySQLConnection.getConnection();
		try {
			ArrayList<User> usrs = new ArrayList<User>();
			PreparedStatement stt = conn.prepareStatement("SELECT ID, userName FROM Users");
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				String usrName = rs.getString("userName");
				int id = rs.getInt("ID");
				User returnedUser = new User();
				returnedUser.setUserName(usrName);
				returnedUser.setID(id+"");
				usrs.add(returnedUser);
			}
			stt.close();
			conn.close();
			
			return usrs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
