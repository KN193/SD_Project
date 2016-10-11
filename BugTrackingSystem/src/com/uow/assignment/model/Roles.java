package com.uow.assignment.model;

public class Roles {

	public static String DEVELOPER = "Developer";
	public static String REVIEWER = "Reviewer";
	public static String TRIAGER = "Triager";
	public static String REPORTER = "Reporter";
	public static String MANAGER = "Manager";
	
	public static String[] getAllRoles() {
		return new String[] {DEVELOPER, REVIEWER, TRIAGER, REPORTER, MANAGER};
	}
}
