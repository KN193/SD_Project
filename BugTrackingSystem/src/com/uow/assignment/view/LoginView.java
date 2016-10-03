package com.uow.assignment.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.User;
import com.uow.assignment.utility.StringEncryptor;
import com.uow.assignment.view.frame.MainMenuFrame;

public class LoginView extends JPanel {
	private JTextField usr;
	private JPasswordField pwd;
	private UserManager usrMng = new UserManager();

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
		pwd.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// key press ENTER
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginAction();
				}
			}
		});
		
		// Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});
		btnLogin.setBounds(51, 114, 90, 29);
		add(btnLogin);
		
		//Reset button
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
	
	private void loginAction() {
		String userName = usr.getText();
		String pass = pwd.getText();
		String encryptedPwd = new StringEncryptor().encryptPassword(pass);
		User login = new User(userName, encryptedPwd);
		User loggedIn = usrMng.loginUser(login);
		if (loggedIn != null) {
			JFrame topFrame = (JFrame) SwingUtilities.getRoot(this);
			topFrame.setVisible(false);
			topFrame.dispose();
			MainMenuFrame main = new MainMenuFrame(loggedIn);
			main.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(getParent(), "User name or password is not correct", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
