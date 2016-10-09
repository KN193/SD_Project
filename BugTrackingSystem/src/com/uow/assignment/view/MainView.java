package com.uow.assignment.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.uow.assignment.model.User;

public class MainView extends JPanel {

	/**
	 * Create the panel.
	 * @param user 
	 */
	public MainView(User user) {
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 708, 428);
		add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
