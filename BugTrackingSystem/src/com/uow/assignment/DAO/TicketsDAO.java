package com.uow.assignment.DAO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.uow.assignment.controller.ComponentManager;
import com.uow.assignment.controller.PriorityManager;
import com.uow.assignment.controller.StatusManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Ticket;

public class TicketsDAO {

	public Ticket createNewBug(Ticket bug) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn
					.prepareStatement(
							"INSERT INTO Tickets (description, status, reportedUser, creationDate, component) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			stt.setString(1, bug.getDescription());
			// stt.setString(2, bug.getPriority());
			stt.setInt(2, bug.getStatus().getID());
			// stt.setInt(4, Integer.parseInt(bug.getAssignedUser().getID()));
			stt.setInt(3, Integer.parseInt(bug.getReportedUser().getID()));
			stt.setTimestamp(4, new java.sql.Timestamp(bug.getCreationTime().getTime()));
			stt.setInt(5, bug.getComponent().getID());

			stt.executeUpdate();
			long id = 0;
			ResultSet generatedKeys = stt.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Creating ticket failed, no ID obtained.");
			}
			generatedKeys.close();
			stt.close();
			conn.close();

			if (id != 0) {
				Ticket toReturn = findTicketByID(id);
				return toReturn;
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private Ticket findTicketByID(long id) {
		try {
			Connection conn = MySQLConnection.getConnection();
			PreparedStatement stt = conn.prepareStatement("SELECT ID, description, status, priority, assignedUser, reportedUser, creationDate, component, isPatchAttached FROM Tickets WHERE ID=?");
			stt.setLong(1, id);

			ResultSet rs = stt.executeQuery();

			while (rs.next()) {
				int ticketID = rs.getInt("ID");
				String description = rs.getString("description");
				int priority = rs.getInt("priority");
				int status = rs.getInt("status");
				int assignedUser = rs.getInt("assignedUser");
				int reportedUser = rs.getInt("reportedUser");
				int component = rs.getInt("component");
				Date creationDate = rs.getDate("creationDate");
				boolean isPatchAttached = rs.getBoolean("isPatchAttached");

				Ticket toReturn = new Ticket(ticketID + "", description,
						new PriorityManager().getPriorityByID(priority),
						new StatusManager().getStatusByID(status),
						new UserManager().findByUserID(assignedUser),
						new UserManager().findByUserID(reportedUser),
						creationDate,
						new ComponentManager().getComponentByID(component),
						isPatchAttached);

				return toReturn;
			}
			
			rs.close();
			stt.close();
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateBug(Ticket ticket) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("UPDATE Tickets SET description = ?, priority = ?, status = ?, assignedUser = ?, reportedUser = ?, component = ? WHERE ID = ?");
			stt.setString(1, ticket.getDescription());
			stt.setInt(2, ticket.getPriority().getID());
			stt.setInt(3, ticket.getStatus().getID());
			stt.setInt(4, Integer.parseInt(ticket.getAssignedUser().getID()));
			stt.setInt(5, Integer.parseInt(ticket.getReportedUser().getID()));
			stt.setInt(6, ticket.getComponent().getID());
			stt.setInt(7, Integer.parseInt(ticket.getID()));
			
			stt.executeUpdate();
			stt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateBugPriority(Ticket ticket) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("UPDATE Tickets SET priority = ? WHERE ID = ?");
			stt.setInt(1, ticket.getPriority().getID());
			stt.setInt(2, Integer.parseInt(ticket.getID()));
			
			stt.executeUpdate();
			stt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateBugStatus(Ticket ticket) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("UPDATE Tickets SET status = ? WHERE ID = ?");
			stt.setInt(1, ticket.getStatus().getID());
			stt.setInt(2, Integer.parseInt(ticket.getID()));
			
			stt.executeUpdate();
			stt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBugAssign(Ticket ticket) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("UPDATE Tickets SET assignedUser = ? WHERE ID = ?");
			stt.setInt(1, Integer.parseInt(ticket.getAssignedUser().getID()));
			stt.setInt(2, Integer.parseInt(ticket.getID()));
			
			stt.executeUpdate();
			stt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addTicketPatch(Ticket ticket) {
		Connection conn = MySQLConnection.getConnection();
		try {
			FileInputStream fin = new FileInputStream(ticket.getPatch());
			
			
			PreparedStatement stt = conn.prepareStatement("UPDATE Tickets SET patch = ?, isPatchAttached = ? WHERE ID = ?");
			stt.setBinaryStream(1, (InputStream)fin, (int)ticket.getPatch().length());
			stt.setBoolean(2, true);
			stt.setInt(3, Integer.parseInt(ticket.getID()));
			
			stt.executeUpdate();
			stt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public InputStream getTicketPatch(Ticket ticket) {
		Connection conn = MySQLConnection.getConnection();
		try {
			InputStream in = null;
			
			PreparedStatement stt = conn.prepareStatement("SELECT patch FROM Tickets WHERE ID = ?");
			stt.setInt(1, Integer.parseInt(ticket.getID()));
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				in = rs.getBinaryStream(1);
			}
			rs.close();
			stt.close();
			conn.close();
			return in;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Ticket> getAllTicket() {
		try {
			ArrayList<Ticket> list = new ArrayList<Ticket>();
			Connection conn = MySQLConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT ID, description, status, priority, assignedUser, reportedUser, creationDate, component, isPatchAttached FROM Tickets");

			ResultSet rSet = statement.executeQuery();

			while (rSet.next()) {
				int ticketID = rSet.getInt("ID");
				String description = rSet.getString("description");
				int assignedUser = rSet.getInt("assignedUser");
				int status = rSet.getInt("status");
				int priority = rSet.getInt("priority");
				int reportedUser = rSet.getInt("reportedUser");
				int component = rSet.getInt("component");
				Date creationDate = rSet.getTimestamp("creationDate");
				boolean isPatchAttached = rSet.getBoolean("isPatchAttached");

				Ticket toReturn = new Ticket(ticketID + "", description,
						new PriorityManager().getPriorityByID(priority),
						new StatusManager().getStatusByID(status),
						new UserManager().findByUserID(assignedUser),
						new UserManager().findByUserID(reportedUser),
						creationDate,
						new ComponentManager().getComponentByID(component),
						isPatchAttached);

				list.add(toReturn);
			}

			rSet.close();
			statement.close();
			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
