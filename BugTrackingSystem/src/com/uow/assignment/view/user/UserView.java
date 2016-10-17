package com.uow.assignment.view.user;

import javax.swing.JPanel;

import com.uow.assignment.controller.ReputationManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Roles;
import com.uow.assignment.model.User;
import com.uow.assignment.utility.StringEncryptor;
import com.uow.assignment.view.TicketView;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserView extends JPanel {

	User user;
	private JPanel content_panel;
	private UserManager usrmng = new UserManager();
	private ReputationManager repmng = new ReputationManager();
	private User crrUsr;
	private JLabel lblRolestxt,email;
	private JButton btnFindUser, btnEditRoles, btnEditEmail, btnEditPassword,btnCreateUser;
	/**
	 * Create the panel.
	 */
	public UserView(User usr) {
		this.crrUsr = usr;
		setLayout(null);
		content_panel = new JPanel();
		content_panel.setLayout(null);
		content_panel.setBounds(6, 6, 703, 421);
		add(content_panel);
		
		initializeView();
		filterRoles();
	}
	
	private void initializeView() {
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(30, 20, 61, 16);
		content_panel.add(lblName);
		
		JLabel name = new JLabel("New label");
		name.setBounds(103, 20, 153, 16);
		content_panel.add(name);
		name.setText(crrUsr.getUserName());
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(30, 48, 61, 16);
		content_panel.add(lblEmail);
		
		email = new JLabel("New label");
		email.setBounds(103, 48, 153, 16);
		content_panel.add(email);
		email.setText(crrUsr.getEmail());
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setBounds(30, 76, 61, 16);
		content_panel.add(lblRole);
		
		lblRolestxt = new JLabel("");
		lblRolestxt.setBounds(103, 76, 153, 16);
		content_panel.add(lblRolestxt);
		lblRolestxt.setText(crrUsr.getRoles());
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(6, 104, 686, 34);
		content_panel.add(panel);
		
		btnEditRoles = new JButton("Edit Roles");
		btnEditRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = Roles.getAllRoles();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Set User Role",
			        "Set User Role", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[0]); // Initial choice
			    
			    crrUsr.setRoles(input);
			    usrmng.updateRoles(crrUsr);
			    lblRolestxt.setText(input);
			    
			}
		});
		panel.add(btnEditRoles);
		
		btnEditEmail = new JButton("Edit Email");
		btnEditEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Set User Role", crrUsr.getEmail()); // Initial choice
			    
			    // validate email if have one
			    // validateEmail();
			    if (!input.equals("")) {
				    crrUsr.setEmail(input);
				    usrmng.updateEmail(crrUsr);
				    email.setText(input);
			    }
			}
		});
		panel.add(btnEditEmail);
		
		btnEditPassword = new JButton("Edit Password");
		btnEditPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmOldPassword();
			}

		});
		panel.add(btnEditPassword);
		
		btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel content = (JPanel) getParent().getParent();
				content.removeAll();
				CreateUserView createView = new CreateUserView();
				content.add(createView);
				createView.setBounds(0, 0, 695, 428);
				createView.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});
		panel.add(btnCreateUser);
		
		btnFindUser = new JButton("Find User");
		btnFindUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = usrmng.getListOfUser();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Choose User",
			        "Choose User", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[0]); // Initial choice
			    
			    User tmp = usrmng.findByUserName(input);
			    
			    content_panel.removeAll();
			    crrUsr = tmp;
			    initializeView();
			    content_panel.revalidate();
			    content_panel.repaint();
			}
		});
		panel.add(btnFindUser);
		
		JLabel lblReputation = new JLabel("Reputation:");
		lblReputation.setBounds(303, 20, 77, 16);
		content_panel.add(lblReputation);
		
		int like = repmng.countLikeReputation(crrUsr, true);
		JLabel lblTxtlike = new JLabel(like + " Like");
		lblTxtlike.setBounds(392, 20, 61, 16);
		content_panel.add(lblTxtlike);
		
		int dislike = repmng.countLikeReputation(crrUsr, false);
		JLabel txtDisLike = new JLabel(dislike + " Dislike");
		txtDisLike.setBounds(465, 20, 61, 16);
		content_panel.add(txtDisLike);
		
	}

	protected void confirmOldPassword() {
		PasswordChange pwdChange = new PasswordChange();
		pwdChange.setPreferredSize(new Dimension(285, 77));
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(this, pwdChange, "Confirm your old Password",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		if(option == 0) // pressing OK button
		{
			if (pwdChange.validatePwd()) {
				String userName = crrUsr.getUserName();
				String pass = pwdChange.retrieveNewPassword();
				User login = new User(userName, pass);
				User loggedIn = usrmng.loginUser(login);
				if (loggedIn != null) {
					JOptionPane.showMessageDialog(this, "Password is confirmed ! Please help to enter your new password", "Information", JOptionPane.INFORMATION_MESSAGE);
					showPasswordChange();
				}
			}
		}
		
	}

	private void filterRoles() {
		if (crrUsr.getRoles().equals("Developer")) {
			btnCreateUser.setVisible(false);
			btnEditEmail.setVisible(true);
			btnEditPassword.setVisible(true);
			btnEditRoles.setVisible(false);
			btnFindUser.setVisible(false);
		} else if (crrUsr.getRoles().equals("Reporter")) {
			btnCreateUser.setVisible(false);
			btnEditEmail.setVisible(true);
			btnEditPassword.setVisible(true);
			btnEditRoles.setVisible(false);
			btnFindUser.setVisible(false);
		} else if (crrUsr.getRoles().equals("Triager")) {
			btnCreateUser.setVisible(false);
			btnEditEmail.setVisible(true);
			btnEditPassword.setVisible(true);
			btnEditRoles.setVisible(false);
			btnFindUser.setVisible(false);
		} else if (crrUsr.getRoles().equals("Reviewer")) {
			btnCreateUser.setVisible(false);
			btnEditEmail.setVisible(true);
			btnEditPassword.setVisible(true);
			btnEditRoles.setVisible(false);
			btnFindUser.setVisible(false);
		} else if (crrUsr.getRoles().equals("Manager")) {
			btnCreateUser.setVisible(true);
			btnEditEmail.setVisible(true);
			btnEditPassword.setVisible(true);
			btnEditRoles.setVisible(true);
			btnFindUser.setVisible(true);
		}
	}

	private void showPasswordChange() {
		PasswordChange pwdChange = new PasswordChange();
		pwdChange.setPreferredSize(new Dimension(285, 77));
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(this, pwdChange, "Change Password",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		if(option == 0) // pressing OK button
		{
			if (pwdChange.validatePwd()) {
				String password = pwdChange.retrieveNewPassword();
			    crrUsr.setPwd(password);
			    usrmng.updatePassword(crrUsr);
			    JOptionPane.showMessageDialog(this, "Password is updated successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
