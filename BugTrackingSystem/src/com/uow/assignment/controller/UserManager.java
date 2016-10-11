package com.uow.assignment.controller;

import java.util.ArrayList;

import com.uow.assignment.DAO.UsersDAO;
import com.uow.assignment.model.User;

public class UserManager {
	private UsersDAO usersDAO =  new UsersDAO();
	private static ArrayList<User> allUser = new ArrayList<User>();
	
	public UserManager(){
		if (allUser.size() == 0)
			allUser = usersDAO.getAllUser();
	}
	
	public ArrayList<User> getAllUser() {
		return allUser;
	}

	public void setAllUser(ArrayList<User> allUser) {
		this.allUser = allUser;
	}

	public User loginUser (User usr) {
		User loggedIn = usersDAO.loginUser(usr);
		return loggedIn;
	}

	public String[] getListOfUser() {
		// Return list of User
		ArrayList<User> users = usersDAO.getAllUser();
		setAllUser(users);
		
		String[] usrs = new String[users.size()]; 
		for (int i = 0; i < users.size(); i++) {
			usrs[i] = users.get(i).getUserName();
		}
		
		return usrs;
	}

	public User findByUserName(String selectedItem) {
		User returnedUser =  null;
		for (User u : allUser) {
			if (u.getUserName().equalsIgnoreCase(selectedItem)) {
				returnedUser = u;
			}
		}
		return returnedUser;
	}
	
	public User findByUserID(int id) {
		User returnedUser =  null;
		for (User u : allUser) {
			if (u.getID().equals(id+"")) {
				returnedUser = u;
			}
		}
		return returnedUser;
	}
	
	public void createUser(User user) {
		usersDAO.createUser(user);
	}

	public void updateRoles(User input) {
		usersDAO.updateRoles(input);
	}

	public void updateEmail(User input) {
		usersDAO.updateEmail(input);
	}

	public void updatePassword(User input) {
		usersDAO.updatePassword(input);
	}
	
}
