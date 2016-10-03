package com.uow.assignment.view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LoginView extends JPanel {
	private JTextField usr;
	private JPasswordField pwd;

	/**
	 * Create the panel.
	 */
	public LoginView() {
		setLayout(null);
		
		usr = new JTextField();
		usr.setBounds(124, 53, 130, 26);
		add(usr);
		usr.setColumns(10);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(22, 58, 90, 16);
		add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 86, 90, 16);
		add(lblPassword);
		
		pwd = new JPasswordField();
		pwd.setBounds(124, 81, 130, 26);
		add(pwd);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLogin.setBounds(51, 114, 90, 29);
		add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(134, 114, 90, 29);
		add(btnReset);
		
		JLabel img = new JLabel("");
		img.setIcon(new ImageIcon("res/icon/ico/Friends.png"));
		img.setBounds(280, 45, 69, 62);
		add(img);
		
		JLabel lblNewLabel = new JLabel("Bug Tracking System");
		lblNewLabel.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 15));
		lblNewLabel.setBounds(51, 6, 160, 40);
		add(lblNewLabel);

	}
}
