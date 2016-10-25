package com.uow.assignment.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UserTest.class,
				TicketTest.class,
				CommentTest.class,
				UltilityTest.class,
				ConnectionTest.class
			})
public class AllTests {

}
