package com.uow.assignment;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.uow.assignment.DAO.UsersDAO;
import com.uow.assignment.controller.ComponentManager;
import com.uow.assignment.controller.PriorityManager;
import com.uow.assignment.controller.StatusManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.User;
import com.uow.assignment.utility.StringEncryptor;
import com.uow.assignment.view.LoginView;
import com.uow.assignment.view.frame.LoginFrame;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the frame.
	 */
	private void initialize() {
		frame = new LoginFrame();
		
		// Initialize all Manager Components
		
		PriorityManager primng = new PriorityManager();
		StatusManager sttmng = new StatusManager();
		UserManager usrmng = new UserManager();
		ComponentManager cpn = new ComponentManager();
		
//		frame = new JFrame();
//		frame.setBounds(100, 100, 381, 181);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		LoginView login = new LoginView();
//		frame.getContentPane().add(login);
//		login.setLayout(null);
		
//		User test = new User("kim", new StringEncryptor().encrypted("123456"), "Developer");
//		new UsersDAO().createUser(test);
	}

}
