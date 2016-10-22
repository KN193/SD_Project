package com.uow.assignment.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.uow.assignment.model.User;
import com.uow.assignment.view.user.UserView;

public class SideMenu extends JPanel {

	private JLabel lbl_Report;
	private User crrUser;
	/**
	 * Create the panel.
	 * @param mview 
	 * @param user 
	 */
	public SideMenu(final MainView mview, final User user) {
		setLayout(null);
		
		crrUser = user;
		// User button
		JLabel lblusr = new JLabel("");
		lblusr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JPanel content = (JPanel) mview.getComponent(0);
				content.removeAll();
				UserView usrView = new UserView(user);
				content.add(usrView);
				usrView.setBounds(0, 0, 695, 428);
				usrView.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});

		lblusr.setBounds(6, 6, 70, 68);
		lblusr.setHorizontalAlignment(SwingConstants.CENTER);
		lblusr.setIcon(new ImageIcon("res/icon/png/search_male_user_4.png"));
		add(lblusr);
		
		// Ticket button
		JLabel lblBug = new JLabel("");
		lblBug.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JPanel content = (JPanel) mview.getComponent(0);
				content.removeAll();
				TicketView bugView = new TicketView(user);
				content.add(bugView);
				bugView.setBounds(0, 0, 695, 428);
				bugView.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});
		lblBug.setIcon(new ImageIcon("res/icon/png/ticket_4.png"));
		lblBug.setHorizontalAlignment(SwingConstants.CENTER);
		lblBug.setBounds(6, 86, 70, 68);
		add(lblBug);
		
		// Report button
		lbl_Report = new JLabel("");
		lbl_Report.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JPanel content = (JPanel) mview.getComponent(0);
				content.removeAll();
				ReportView rpView = new ReportView();
				content.add(rpView);
				rpView.setBounds(0, 0, 695, 428);
				rpView.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});
		lbl_Report.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Report.setIcon(new ImageIcon("res/icon/png/reports_4.png"));
		lbl_Report.setBounds(6, 246, 70, 68);
		add(lbl_Report);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JPanel content = (JPanel) mview.getComponent(0);
				content.removeAll();
				InformationView iView = new InformationView();
				content.add(iView);
				iView.setBounds(0, 0, 695, 428);
				iView.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("res/icon/png/web_management_4.png"));
		lblNewLabel.setBounds(6, 166, 70, 68);
		add(lblNewLabel);

		filterRoles();
	}
	
	public void filterRoles() {
		if (crrUser.getRoles().equals("Manager")) {
			lbl_Report.setVisible(true);
		} else {
			lbl_Report.setVisible(false);
		}
	}

}
