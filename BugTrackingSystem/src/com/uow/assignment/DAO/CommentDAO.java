package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.uow.assignment.controller.TicketManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Comment;
import com.uow.assignment.model.Component;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;

public class CommentDAO {
	UserManager usrmng = new UserManager();
	TicketManager tkmng = new TicketManager();

	public ArrayList<Comment> getAllComment(Ticket t, User u) {
		Connection conn = MySQLConnection.getConnection();
		try {
			ArrayList<Comment> comments = new ArrayList<Comment>();
			PreparedStatement stt = conn.prepareStatement("SELECT * FROM Comments WHERE TicketID = ? AND UserID = ?");
			stt.setInt(1, Integer.parseInt(t.getID()));
			stt.setInt(2, Integer.parseInt(u.getID()));
			
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				String content = rs.getString("content");
				int id = rs.getInt("ID");
				int usrid = rs.getInt("UserID");
				int ticketid = rs.getInt("TicketID");
				Date creationTime = rs.getTimestamp("creationTime");
				Comment returnedCom = new Comment(content, id, creationTime, usrmng.findByUserID(usrid), t);
				comments.add(returnedCom);
			}
			rs.close();
			stt.close();
			conn.close();
			
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addNewComment(Comment cmt) {
		Connection conn = MySQLConnection.getConnection();
		try {
			PreparedStatement stt = conn.prepareStatement("INSERT INTO Comments (TicketID,UserID,content,creationTime) values(?,?,?,?)");
			stt.setInt(1, Integer.parseInt(cmt.getTicketCommented().getID()));
			stt.setInt(2, Integer.parseInt(cmt.getCommentUser().getID()));
			stt.setString(3, cmt.getContent());
			stt.setTimestamp(4, new java.sql.Timestamp(cmt.getCreatedDate().getTime()));

			stt.executeUpdate();
			stt.close();
			conn.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
