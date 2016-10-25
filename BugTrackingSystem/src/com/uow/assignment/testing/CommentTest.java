package com.uow.assignment.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uow.assignment.model.Comment;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;

public class CommentTest {

	private Comment tmp;
	@Before
	public void setUp() throws Exception {
		tmp = new Comment();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTicketCommentedGetterSetter() {
		Ticket tck = new Ticket();
		tck.setID("1");
		tmp.setTicketCommented(tck);
		
		Ticket testtck = new Ticket();
		testtck.setID("1");
		assertEquals(tmp.getTicketCommented(), testtck);
	}

	@Test
	public void testContentGetterSetter() {
		String newContent = "Content";
		tmp.setContent(newContent);
		assertEquals(tmp.getContent(), "Content");
	}

	@Test
	public void testIDGetterSetter() {
		int newID = 1;
		tmp.setID(newID);
		assertEquals(tmp.getID(), 1);
	}

	@Test
	public void testGetCreatedDate() {
		Date now = new Date();
		tmp.setCreatedDate(now);
		assertEquals(tmp.getCreatedDate(), now);
	}

	@Test
	public void testCommentUserGetterSetter() {
		
		User newusr = new User();
		newusr.setID("1");
		newusr.setUserName("user1");
		tmp.setCommentUser(newusr);
		
		User testusr = new User();
		testusr.setID("1");
		testusr.setUserName("user1");
		
		assertEquals(tmp.getCommentUser(), testusr);
	}

}
