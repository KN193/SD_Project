package com.uow.assignment.view.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
//		User test = new User("kim", new StringEncryptor().encrypted("123456"), "Developer");
//		new UsersDAO().createUser(test);
	}

}
