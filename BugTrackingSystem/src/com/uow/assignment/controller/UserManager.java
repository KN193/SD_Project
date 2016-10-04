package com.uow.assignment.controller;

import java.util.ArrayList;

import com.uow.assignment.DAO.UsersDAO;
import com.uow.assignment.model.User;

public class UserManager {
	private UsersDAO usersDAO =  new UsersDAO();
	private ArrayList<User> allUser = null;
	
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
	
}
