package com.uow.assignment.view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Roles;
import com.uow.assignment.model.User;
import com.uow.assignment.utility.StringEncryptor;

public class CreateUserView extends JPanel {

	private UserManager usrmng = new UserManager();
	private JPasswordField pwdPasswordtxt;
	private JPasswordField pwdConfirmtxt;
	private JTextField email, name;
	private JComboBox rolecbb;
	/**
	 * Create the panel.
	 */
	public CreateUserView() {
		setLayout(null);
		
		JLabel lblName = new JLabel("UserName:");
		lblName.setBounds(30, 20, 126, 16);
		add(lblName);
		
		name = new JTextField();
		name.setBounds(168, 20, 153, 16);
		add(name);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(30, 48, 126, 16);
		add(lblEmail);
		
		email = new JTextField();
		email.setBounds(168, 48, 153, 16);
		add(email);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setBounds(30, 76, 126, 16);
		add(lblRole);
		
		rolecbb = new JComboBox();
		rolecbb.setModel(new DefaultComboBoxModel(Roles.getAllRoles()));
		rolecbb.setBounds(168, 72, 153, 27);
		add(rolecbb);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(30, 104, 126, 16);
		add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setBounds(30, 132, 126, 16);
		add(lblConfirmPassword);
		
		pwdPasswordtxt = new JPasswordField();
		pwdPasswordtxt.setBounds(168, 104, 153, 16);
		add(pwdPasswordtxt);
		
		pwdConfirmtxt = new JPasswordField();
		pwdConfirmtxt.setBounds(168, 132, 153, 16);
		add(pwdConfirmtxt);
		
		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateInfo()) {
					User u = new User();
					u.setEmail(email.getText());
					u.setPwd(new StringEncryptor().encryptPassword(pwdPasswordtxt.getText()));
					u.setUserName(name.getText());
					u.setRoles((String)rolecbb.getSelectedItem());
					
					usrmng.createUser(u);
					JOptionPane.showMessageDialog(getParent(), "Created new User successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
					resetAllFields();
				}
			}

		});
		btnCreateNewUser.setBounds(109, 160, 138, 29);
		add(btnCreateNewUser);
	}
	
	private boolean validateInfo() {
		// validate mandatory fields
		if (name.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "User name cannot be null !", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (!pwdPasswordtxt.getText().equals(pwdConfirmtxt.getText())) {
			JOptionPane.showMessageDialog(this, "Password and confirm password are mismatched !", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (pwdPasswordtxt.getText().trim().equals("") || pwdConfirmtxt.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Password and confirm password must not be blank !", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private void resetAllFields() {
		name.setText("");
		email.setText("");
		rolecbb.setSelectedIndex(0);
		pwdConfirmtxt.setText("");
		pwdPasswordtxt.setText("");
	}
}
