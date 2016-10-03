package com.uow.assignment.controller;

import com.uow.assignment.DAO.UsersDAO;
import com.uow.assignment.model.User;

public class UserManager {
	private UsersDAO usersDAO =  new UsersDAO();
	
	public User loginUser (User usr) {
		User loggedIn = usersDAO.loginUser(usr);
		return loggedIn;
	}
}
