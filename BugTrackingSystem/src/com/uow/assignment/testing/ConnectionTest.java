package com.uow.assignment.testing;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uow.assignment.DAO.MySQLConnection;

public class ConnectionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Connection con = MySQLConnection.getConnection();
		assertNotNull(con);
	}

}
