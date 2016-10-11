package com.uow.assignment.view.user;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.uow.assignment.utility.StringEncryptor;

public class PasswordChange extends JPanel {

	private JPasswordField pwdPasswordtxt;
	private JPasswordField pwdConfirmtxt;
	
	/**
	 * Create the panel.
	 */
	public PasswordChange() {
		setLayout(null);
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(6, 10, 63, 16);
		add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setBounds(6, 38, 119, 16);
		add(lblConfirmPassword);
		
		pwdPasswordtxt = new JPasswordField();
		pwdPasswordtxt.setBounds(130, 5, 123, 26);
		add(pwdPasswordtxt);
		
		pwdConfirmtxt = new JPasswordField();
		pwdConfirmtxt.setBounds(130, 33, 123, 26);
		add(pwdConfirmtxt);
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

}
