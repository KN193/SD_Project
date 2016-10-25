package com.uow.assignment.testing;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uow.assignment.model.Component;
import com.uow.assignment.model.Priority;
import com.uow.assignment.model.Status;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;

public class TicketTest {

	private Ticket tmp;
	@Before
	public void setUp() throws Exception {
		tmp = new Ticket();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreationTimeGetterSetter() {
		Date now = new Date();
		tmp.setCreationTime(now);
		assertEquals(tmp.getCreationTime(), now);
	}

	@Test
	public void testIDGetterSetter() {
		String newID = "001";
		tmp.setID(newID);
		assertEquals(tmp.getID(), "001");
	}

	@Test
	public void testDescriptionGetterSetter() {
		String newDes = "Description";
		tmp.setDescription(newDes);
		assertEquals(tmp.getDescription(), "Description");
	}

	@Test
	public void testPriorityGetterSetter() {
		Priority newpri = new Priority(1, "High");
		tmp.setPriority(newpri);
		assertEquals(tmp.getPriority(), new Priority(1, "High"));
	}

	@Test
	public void testStatusGetterSetter() {
		Status newstt = new Status(1, "New");
		tmp.setStatus(newstt);
		assertEquals(tmp.getStatus(), new Status(1, "New") );
	}

	@Test
	public void testAssignedUserGetterSetter() {
		User newusr = new User();
		newusr.setID("1");
		newusr.setUserName("user1");
		tmp.setAssignedUser(newusr);
		
		User testusr = new User();
		testusr.setID("1");
		testusr.setUserName("user1");
		
		assertEquals(tmp.getAssignedUser(), testusr);
	}

	@Test
	public void testReportedUserGetterSetter() {
		User newusr = new User();
		newusr.setID("1");
		newusr.setUserName("user1");
		tmp.setReportedUser(newusr);
		
		User testusr = new User();
		testusr.setID("1");
		testusr.setUserName("user1");
		
		assertEquals(tmp.getReportedUser(), testusr);
	}

	@Test
	public void testGetComponent() {
		Component newcomp = new Component(1, "FrontEnd");
		tmp.setComponent(newcomp);
		assertEquals(tmp.getComponent(), new Component(1, "FrontEnd") );
	}

}
