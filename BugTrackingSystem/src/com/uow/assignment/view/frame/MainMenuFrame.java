package com.uow.assignment.view.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.uow.assignment.model.User;
import com.uow.assignment.view.MainView;
import com.uow.assignment.view.SideMenu;

public class MainMenuFrame extends JFrame {
	private User user;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainMenuFrame(User user) {
		if (user == null) {
			JOptionPane.showMessageDialog(getParent(), "Please help to login again", "Error", JOptionPane.ERROR_MESSAGE);
			logoutAction();
		}
		
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblusrName = new JLabel("Welcome " + user.getUserName());
		lblusrName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblusrName.setBounds(487, 6, 194, 16);
		contentPane.add(lblusrName);
		
		//Log out button
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutAction();
			}
		});
		btnLogout.setBounds(693, 1, 88, 29);
		contentPane.add(btnLogout);
		
		MainView mview = new MainView(user);
		getContentPane().add(mview);
		mview.setBounds(90, 34, 697, 428);
		mview.setBorder(BorderFactory.createLineBorder(Color.black));
		mview.setLayout(null);
		
		SideMenu sideMenu = new SideMenu(mview, user);
		sideMenu.setBounds(6, 34, 81, 428);
		sideMenu.setBorder(BorderFactory.createLineBorder(Color.black));
		contentPane.add(sideMenu);
	}

	private void logoutAction() {
		JFrame topFrame = (JFrame) SwingUtilities.getRoot(this);
		topFrame.setVisible(false);
		topFrame.dispose();
		LoginFrame login = new LoginFrame();
		login.setVisible(true);
	}
}
