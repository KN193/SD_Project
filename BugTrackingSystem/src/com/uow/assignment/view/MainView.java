package com.uow.assignment.view;

import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;

import com.uow.assignment.model.User;
import com.uow.assignment.view.user.UserView;

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
	}
}
