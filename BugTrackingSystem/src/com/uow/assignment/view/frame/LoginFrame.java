package com.uow.assignment.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uow.assignment.DAO.UsersDAO;
import com.uow.assignment.model.User;
import com.uow.assignment.utility.StringEncryptor;
import com.uow.assignment.view.LoginView;

public class LoginFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setBounds(100, 100, 381, 181);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LoginView login = new LoginView();
		getContentPane().add(login);
		login.setLayout(null);
		
//		User test = new User("user1", new StringEncryptor().encryptPassword("123456"), "Reporter");
//		new UsersDAO().createUser(test);
	}

}
