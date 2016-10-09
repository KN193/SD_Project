package com.uow.assignment.view;

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

import com.uow.assignment.controller.ComponentManager;
import com.uow.assignment.controller.StatusManager;
import com.uow.assignment.controller.TicketManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Component;
import com.uow.assignment.model.Status;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;
import com.uow.assignment.view.component.CustomCombobox;

public class TicketView extends JPanel {

	private TicketManager bugmng = new TicketManager();
	private StatusManager sttmng = new StatusManager();
	private ComponentManager commng = new ComponentManager();
	private User crrUser;
	private JComboBox component_cbb;
	private JTextArea des; 
	private CustomCombobox usr_cbb;
	private UserManager usrmng = new UserManager();
	private JDatePickerImpl datePicker;
	private JPanel bug_detail_panel;
	/**
	 * Create the panel.
	 * @param user 
	 */
	public TicketView(User user) {
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
		add(content_panel);
		content_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		content_panel.setLayout(cardLayout);
		
		JPanel bug_list_panel = new JPanel();
		content_panel.add(bug_list_panel, "bug_list");
		bug_list_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("bug list");
		lblNewLabel.setBounds(26, 21, 61, 16);
		bug_list_panel.add(lblNewLabel);
		
		bug_detail_panel = new JPanel();
		content_panel.add(bug_detail_panel, "bug_detail");
		bug_detail_panel.setLayout(null);
		
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
		lblDes.setBounds(6, 92, 106, 29);
		new_bug_panel.add(lblDes);
		
		des = new JTextArea();
		des.setBounds(124, 98, 331, 213);
		new_bug_panel.add(des);
		
		JLabel lblPriority = new JLabel("Component:");
		lblPriority.setBounds(6, 63, 106, 29);
		new_bug_panel.add(lblPriority);
		
		component_cbb = new JComboBox();
		component_cbb.setModel(new DefaultComboBoxModel(commng.getComponentList()));
		component_cbb.setBounds(124, 65, 119, 27);
		new_bug_panel.add(component_cbb);
		
//		JLabel lblAssignedusr = new JLabel("Assign To:");
//		lblAssignedusr.setBounds(6, 297, 106, 29);
//		new_bug_panel.add(lblAssignedusr);
		
		// Get list of user
//		String[] usrs = usrmng.getListOfUser();
//		usr_cbb = new CustomCombobox(usrs);
//		usr_cbb.setBounds(124, 299, 119, 27);
//		new_bug_panel.add(usr_cbb);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ticket ticket = recordNewBug();
				cardLayout.show(content_panel, "bug_detail");
				TicketDetailView view = new TicketDetailView(ticket, bug_detail_panel);
				bug_detail_panel.add(view);
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
	
	private Ticket recordNewBug () {
		// Record new Bug
		String descr = des.getText();
		String comp = (String)component_cbb.getSelectedItem();
		Component component =  commng.getComponentByName(comp);
		Status status = sttmng.getStatusByName("New");
//		User assignedUser = usrmng.findByUserName((String)usr_cbb.getSelectedItem());
		User reportedUser = crrUser;
		
//		Calendar selectedValue = (Calendar) datePicker.getModel().getValue();
//		Date creationDate = selectedValue.getTime();
		Date creationDate = new Date();
		Ticket newBug =  new Ticket();
		newBug.setDescription(descr);
		newBug.setStatus(status);
		newBug.setReportedUser(reportedUser);
		newBug.setCreationTime(creationDate);
		newBug.setComponent(component);
		try {
			Ticket ticket = bugmng.createNewBug(newBug);
			JOptionPane.showMessageDialog(getParent(), "Recorded new bug to system", "Information", JOptionPane.INFORMATION_MESSAGE);
			return ticket;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getParent(), "Cannot record new bug. Error: \n"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
	}
}
