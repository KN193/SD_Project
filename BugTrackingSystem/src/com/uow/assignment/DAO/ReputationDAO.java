package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.uow.assignment.controller.ComponentManager;
import com.uow.assignment.controller.PriorityManager;
import com.uow.assignment.controller.StatusManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Reputation;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;

public class ReputationDAO {

	public void addReputation(Ticket ticket, User user, boolean likeOrDislike) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("INSERT INTO Reputation values (?,?,?)");
			stt.setInt(1, Integer.parseInt(user.getID()));
			stt.setInt(2, Integer.parseInt(ticket.getID()));
			stt.setBoolean(3, likeOrDislike);
			
			stt.executeUpdate();
			
			stt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Reputation checkReputation(Ticket ticket, User user) {
		Reputation rep = null;
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("SELECT * FROM Reputation WHERE UserID = ?  AND TicketID = ?");
			stt.setInt(1, Integer.parseInt(user.getID()));
			stt.setInt(2, Integer.parseInt(ticket.getID()));
			
			ResultSet rs = stt.executeQuery();

			while (rs.next()) {
				boolean likeOrDislike = rs.getBoolean("reputation");

				rep = new Reputation();
				rep.setLikeOrDislike(likeOrDislike);
			}
			
			rs.close();
			stt.close();
			conn.close();
			return rep;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public void updateReputation(Ticket ticket, User crrUsr, boolean likeOrDislike) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("UPDATE Reputation SET reputation = ? WHERE UserID = ? AND TicketID = ?");
			stt.setBoolean(1, likeOrDislike);
			stt.setInt(2, Integer.parseInt(crrUsr.getID()));
			stt.setInt(3, Integer.parseInt(ticket.getID()));
			
			stt.executeUpdate();
			
			stt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeReputation(Ticket ticket, User crrUsr) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("DELETE FROM Reputation WHERE UserID = ? AND TicketID = ?");
			stt.setInt(1, Integer.parseInt(crrUsr.getID()));
			stt.setInt(2, Integer.parseInt(ticket.getID()));
			
			stt.executeUpdate();
			
			stt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int countReputationForUser(User crrUsr, boolean likeOrDislike) {
		Connection conn = MySQLConnection.getConnection();
		int count = 0;
		try {
			PreparedStatement stt = conn.prepareStatement("SELECT COUNT(*) AS total FROM Reputation WHERE reputation=? AND TicketID IN (SELECT ID FROM Tickets WHERE reportedUser=?)");
			stt.setBoolean(1, likeOrDislike);
			stt.setInt(2, Integer.parseInt(crrUsr.getID()));
			ResultSet rs = stt.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt("total");
			}
			
			rs.close();
			stt.close();
			conn.close();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return count = 0;
		}
	}
}
