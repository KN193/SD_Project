package com.uow.assignment.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JPanel {
	private JTextField usr;
	private JPasswordField pwd;

	/**
	 * Create the panel.
	 */
	public LoginView() {
		setLayout(null);
		
		usr = new JTextField();
		usr.setBounds(124, 144, 130, 26);
		add(usr);
		usr.setColumns(10);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(22, 149, 90, 16);
		add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 177, 90, 16);
		add(lblPassword);
		
		pwd = new JPasswordField();
		pwd.setBounds(124, 172, 130, 26);
		add(pwd);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLogin.setBounds(64, 209, 90, 29);
		add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(149, 210, 90, 29);
		add(btnReset);

	}
}
