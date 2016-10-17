package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jfree.data.general.DefaultKeyedValues2DDataset;

import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.User;

public class ReportDAO {
	UserManager usrmng = new UserManager();
	public Map<String, Integer> getTicketProportationByTime(Date startDate, Date endDate) {
		Connection conn = MySQLConnection.getConnection();
		try {
			Map<String,Integer> map = new HashMap<String,Integer>();
			PreparedStatement stt = conn.prepareStatement(""
					+ "SELECT B.name, COUNT(*) as count FROM BTS.Tickets as A, BTS.Status as B WHERE A.status = B.ID "
					+ "AND A.creationDate BETWEEN ? AND ? GROUP BY A.status;");
			
			stt.setTimestamp(1, new Timestamp(startDate.getTime()));
			stt.setTimestamp(2, new Timestamp(endDate.getTime()));
			ResultSet rs = stt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int count = rs.getInt("count");
				map.put(name, new Integer(count));
			}
			rs.close();
			stt.close();
			conn.close();
			
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public DefaultKeyedValues2DDataset getUserReputation() {
		DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
		Connection conn = MySQLConnection.getConnection();
		try {
			ArrayList<User> allusrs = usrmng.getAllUser();
			for (User u : allusrs) {
				PreparedStatement stt = conn.prepareStatement("SELECT reputation, COUNT(*) as count FROM BTS.Reputation WHERE  TicketID IN (SELECT ID FROM Tickets WHERE reportedUser=?) GROUP BY reputation");
				stt.setInt(1, Integer.parseInt(u.getID()));
				ResultSet rs = stt.executeQuery();
				boolean liked;
				int likeCount = 0;
				int disLikeCount = 0;
				while (rs.next()) {
					liked = rs.getBoolean("reputation");
					if (liked) { // true
						likeCount = rs.getInt("count");
					} else {
						disLikeCount = rs.getInt("count");;
					}
				}
				rs.close();
				stt.close();
				if (likeCount != 0 && disLikeCount !=0) {
					data.addValue(disLikeCount*-1, "Dislike" , u.getUserName());
					data.addValue(likeCount, "Like" , u.getUserName());
				}
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return data;
	}
}
