package com.uow.assignment.view.user;

import javax.swing.JPanel;

import com.uow.assignment.model.User;
import javax.swing.JLabel;

public class UserView extends JPanel {

	User user;
	/**
	 * Create the panel.
	 */
	public UserView(User usr) {
		setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(30, 20, 61, 16);
		add(lblName);
		
		JLabel name = new JLabel("New label");
		name.setBounds(103, 20, 153, 16);
		add(name);
		name.setText(usr.getUserName());
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(30, 48, 61, 16);
		add(lblEmail);
		
		JLabel email = new JLabel("New label");
		email.setBounds(103, 48, 153, 16);
		add(email);
		email.setText(usr.getEmail());
		
		
	}
}
