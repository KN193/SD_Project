package com.uow.assignment.testing;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uow.assignment.model.Roles;
import com.uow.assignment.model.User;

public class UserTest {

	private User tmp;
	@Before
	public void setUp() throws Exception {
		tmp = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEmailGetterSetter() {
		String newEmail = "testingEmail@email.com";
		tmp.setEmail(newEmail);
		assertEquals(tmp.getEmail(), "testingEmail@email.com");
	}

	@Test
	public void testIDGetterSetter() {
		String newID = "001";
		tmp.setID(newID);
		assertEquals(tmp.getID(), "001");
	}

	@Test
	public void testUserNameGetterSetter() {
		String newusrName = "user1";
		tmp.setUserName(newusrName);
		assertEquals(tmp.getUserName(), "user1");
	}

	@Test
	public void testPwdGetterSetter() {
		String newPwd = "123456";
		tmp.setPwd(newPwd);
		assertEquals(tmp.getPwd(), "123456");
	}

	@Test
	public void testRolesGetterSetter() {
		String newRole = Roles.DEVELOPER;
		tmp.setRoles(newRole);
		assertEquals(tmp.getRoles(), "Developer");
	}

}
