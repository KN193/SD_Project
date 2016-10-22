package com.uow.assignment.view.user;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.uow.assignment.utility.StringEncryptor;

public class PasswordChange extends JPanel {

	private JPasswordField pwdPasswordtxt;
	private JPasswordField pwdConfirmtxt;
	private JPasswordField oldpwd;
	
	/**
	 * Create the panel.
	 */
	public PasswordChange() {
		setLayout(null);
		JLabel lblPassword = new JLabel("New Password:");
		lblPassword.setBounds(6, 48, 106, 16);
		add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setBounds(6, 76, 119, 16);
		add(lblConfirmPassword);
		
		pwdPasswordtxt = new JPasswordField();
		pwdPasswordtxt.setBounds(130, 43, 123, 26);
		add(pwdPasswordtxt);
		
		pwdConfirmtxt = new JPasswordField();
		pwdConfirmtxt.setBounds(130, 71, 123, 26);
		add(pwdConfirmtxt);
		
		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setBounds(6, 20, 106, 16);
		add(lblOldPassword);
		
		oldpwd = new JPasswordField();
		oldpwd.setBounds(130, 15, 123, 26);
		add(oldpwd);
	}
	
	public boolean validatePwd() {
		if (!pwdPasswordtxt.getText().equals(pwdConfirmtxt.getText())) {
			JOptionPane.showMessageDialog(this, "Password and confirm password are mismatched !", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public String retrieveNewPassword() {
		return new StringEncryptor().encryptPassword(pwdPasswordtxt.getText());
	}
	
	public String retrieveOldPassword() {
		return new StringEncryptor().encryptPassword(oldpwd.getText());
	}
}
