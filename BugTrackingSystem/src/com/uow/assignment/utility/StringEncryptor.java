package com.uow.assignment.utility;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.ZeroSaltGenerator;

public class StringEncryptor {
	private String key = "BTS_SDProject";
	
	public String encryptPassword(String source) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		encryptor.setSaltGenerator(new ZeroSaltGenerator()); // to generate consistent result
		String encrypted= encryptor.encrypt(source);
		
		return encrypted;
	}
}
