package com.uow.assignment.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnection {
	static Connection conn;
	
	// singular
	public static Connection getConnection(){
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setServerName("127.0.0.1");
		dataSource.setDatabaseName("BTS");
//		dataSource.setRequireSSL(true);
//		dataSource.setUseSSL(true);
		try {
			if (conn == null || conn.isClosed()) {
				conn = dataSource.getConnection();
				// Remember to close the connection after using because connecting is expensive task
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		return conn;
	}
}
