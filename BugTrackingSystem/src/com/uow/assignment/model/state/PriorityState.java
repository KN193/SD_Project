package com.uow.assignment.model.state;

public class PriorityState {

	public static String LOW = "Low";
	public static String HIGH = "High";
	public static String MEDIUM = "Medium";
	public static String CRITICAL = "Critical";
	
	public static String[] getAllState() {
		return new String[] {LOW, HIGH, MEDIUM, CRITICAL};
	}
}
