package com.uow.assignment.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uow.assignment.utility.StringEncryptor;

public class UltilityTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncryptPassword() {
		String newPwd = "123456";
		StringEncryptor encryptor = new StringEncryptor();
		String encryptedPwd = encryptor.encryptPassword(newPwd);
		assertNotEquals(newPwd, encryptedPwd);
	}

}
