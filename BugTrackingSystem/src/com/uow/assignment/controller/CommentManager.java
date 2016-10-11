package com.uow.assignment.controller;

import java.util.ArrayList;

import com.uow.assignment.DAO.CommentDAO;
import com.uow.assignment.model.Comment;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;

public class CommentManager {
	private CommentDAO commentDAO = new CommentDAO();

	public void addNewComment(Comment cmt) {
		commentDAO.addNewComment(cmt);
	}

	public ArrayList<Comment> getAllComment(Ticket t, User u) {
		return commentDAO.getAllComment(t, u);
	}
}
