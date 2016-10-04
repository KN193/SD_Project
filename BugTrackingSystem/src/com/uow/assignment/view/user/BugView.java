package com.uow.assignment.view.user;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePickerImpl;

import com.uow.assignment.controller.BugManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Bug;
import com.uow.assignment.model.User;
import com.uow.assignment.model.state.BugStatus;
import com.uow.assignment.model.state.PriorityState;
import com.uow.assignment.view.CustomCombobox;

public class BugView extends JPanel {

	private BugManager bugmng = new BugManager();
	private User crrUser;
	private JComboBox priority_cbb;
	private JTextArea des; 
	private CustomCombobox usr_cbb;
	private UserManager usrmng = new UserManager();
	private JDatePickerImpl datePicker;
	/**
	 * Create the panel.
	 * @param user 
	 */
	public BugView(User user) {
		setLayout(null);
		crrUser = user;
		JPanel menu_panel = new JPanel();
		FlowLayout fl_menu_panel = (FlowLayout) menu_panel.getLayout();
		fl_menu_panel.setAlignment(FlowLayout.LEFT);
		menu_panel.setBounds(0, 0, 695, 32);
		menu_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(menu_panel);
		
		final JPanel content_panel = new JPanel();
		final CardLayout cardLayout = new CardLayout(0, 0);
		content_panel.setBounds(0, 32, 695, 395);
		content_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(content_panel);
		content_panel.setLayout(cardLayout);
		
		JPanel bug_list_panel = new JPanel();
		content_panel.add(bug_list_panel, "bug_list");
		bug_list_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("bug list");
		lblNewLabel.setBounds(26, 21, 61, 16);
		bug_list_panel.add(lblNewLabel);
		
		JPanel new_bug_panel = new JPanel();
		content_panel.add(new_bug_panel, "new_bug");
		new_bug_panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Create a new Bug");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_1.setBounds(0, 18, 693, 33);
		new_bug_panel.add(lblNewLabel_1);
		
//		JLabel lblcreateDate = new JLabel("Created Date:");
//		lblcreateDate.setBounds(6, 34, 106, 29);
//		new_bug_panel.add(lblcreateDate);
//		
//		// Date picker open source
//		UtilDateModel model = new UtilDateModel();
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
//		datePicker.setBounds(124, 34, 202, 29);
//		new_bug_panel.add(datePicker);
		
		JLabel lblDes = new JLabel("Description:");
		lblDes.setBounds(6, 75, 106, 29);
		new_bug_panel.add(lblDes);
		
		des = new JTextArea();
		des.setBounds(124, 81, 259, 155);
		new_bug_panel.add(des);
		
		JLabel lblPriority = new JLabel("Priority:");
		lblPriority.setBounds(6, 254, 106, 29);
		new_bug_panel.add(lblPriority);
		
		priority_cbb = new JComboBox();
		priority_cbb.setModel(new DefaultComboBoxModel(PriorityState.getAllState()));
		priority_cbb.setBounds(124, 256, 119, 27);
		new_bug_panel.add(priority_cbb);
		
		JLabel lblAssignedusr = new JLabel("Assign To:");
		lblAssignedusr.setBounds(6, 297, 106, 29);
		new_bug_panel.add(lblAssignedusr);
		
		// Get list of user
		String[] usrs = usrmng.getListOfUser();
		usr_cbb = new CustomCombobox(usrs);
		usr_cbb.setBounds(124, 299, 119, 27);
		new_bug_panel.add(usr_cbb);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recordNewBug();
				JOptionPane.showMessageDialog(getParent(), "Recorded new bug to system", "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCreate.setBounds(126, 358, 117, 29);
		new_bug_panel.add(btnCreate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(243, 358, 117, 29);
		new_bug_panel.add(btnReset);

		JButton bugListBtn = new JButton("");
		bugListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content_panel, "bug_list");
			}
		});
		bugListBtn.setIcon(new ImageIcon("res/icon/png/reports_7.png"));
		menu_panel.add(bugListBtn);
		
		JButton newBugBtn = new JButton("");
		newBugBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content_panel, "new_bug");
			}
		});
		newBugBtn.setBackground(Color.WHITE);
		newBugBtn.setIcon(new ImageIcon("res/icon/png/ticket1_7.png"));
		menu_panel.add(newBugBtn);
		
	}
	
	private void recordNewBug () {
		// Record new Bug
		String descr = des.getText();
		String prior = (String)priority_cbb.getSelectedItem();
		String status = BugStatus.NEW;
		User assignedUser = usrmng.findByUserName((String)usr_cbb.getSelectedItem());
		User reportedUser = crrUser;
		
//		Calendar selectedValue = (Calendar) datePicker.getModel().getValue();
//		Date creationDate = selectedValue.getTime();
		Date creationDate = new Date();
		Bug newBug =  new Bug(descr, prior, status, assignedUser, reportedUser, creationDate);
		bugmng.createNewBug(newBug);
		
	}
}
