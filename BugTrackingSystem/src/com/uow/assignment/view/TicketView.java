package com.uow.assignment.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.uow.assignment.controller.ComponentManager;
import com.uow.assignment.controller.PriorityManager;
import com.uow.assignment.controller.StatusManager;
import com.uow.assignment.controller.TicketManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Component;
import com.uow.assignment.model.Priority;
import com.uow.assignment.model.Status;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;
import com.uow.assignment.utility.DateFormatter;
import com.uow.assignment.view.component.CustomCombobox;

public class TicketView extends JPanel {

	private TicketManager bugmng = new TicketManager();
	private StatusManager sttmng = new StatusManager();
	private ComponentManager commng = new ComponentManager();
	private PriorityManager primng = new PriorityManager();
	private User crrUser;
	private JComboBox component_cbb;
	private JTextArea des; 
	private JButton newBugBtn,bugListBtn, btnSearchBug;
	private CustomCombobox usr_cbb;
	private UserManager usrmng = new UserManager();
	private JDatePickerImpl datePicker;
	private JPanel bug_detail_panel;
	private JTable ticketTable;
	private ArrayList<Ticket> allTickets;
	private final CardLayout cardLayout;
	private final JPanel content_panel;
	private JPanel bug_list_panel, new_bug_panel, bug_search_panel;
	private JTextField txtIdsearch;
	private JTextField txtPriSearch;
	private JTextField txtSttSearch;
	private JTextField txtAssUsrSearch;
	private JTextField txtCompSearch;
	private JLabel lblReportedUser;
	private JTextField txtReprtdSearch;
	private JButton btnSearch;
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
		
		content_panel = new JPanel();
		cardLayout = new CardLayout(0, 0);
		content_panel.setBounds(0, 32, 695, 395);
		add(content_panel);
		content_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		content_panel.setLayout(cardLayout);
		
		bug_list_panel = new JPanel();
		content_panel.add(bug_list_panel, "bug_list");
		bug_list_panel.setLayout(null);
		initializeTable(true);
		
		bug_detail_panel = new JPanel();
		content_panel.add(bug_detail_panel, "bug_detail");
		bug_detail_panel.setLayout(null);
		
		new_bug_panel = new JPanel();
		content_panel.add(new_bug_panel, "new_bug");
		new_bug_panel.setLayout(null);
		initializeNewBug();
		
		bug_search_panel = new JPanel();
		content_panel.add(bug_search_panel, "bug_search");
		bug_search_panel.setLayout(null);
		initializeBugSearch();
		
//		JLabel lblcreateDate = new JLabel("Created Date:");
//		lblcreateDate.setBounds(6, 34, 106, 29);
//		new_bug_panel.add(lblcreateDate);
//		
		
//		JLabel lblAssignedusr = new JLabel("Assign To:");
//		lblAssignedusr.setBounds(6, 297, 106, 29);
//		new_bug_panel.add(lblAssignedusr);
		
		// Get list of user
//		String[] usrs = usrmng.getListOfUser();
//		usr_cbb = new CustomCombobox(usrs);
//		usr_cbb.setBounds(124, 299, 119, 27);
//		new_bug_panel.add(usr_cbb);
		
		bugListBtn = new JButton("");
		bugListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content_panel, "bug_list");
				initializeTable(true);
			}
		});
		bugListBtn.setIcon(new ImageIcon("res/icon/png/reports_7.png"));
		menu_panel.add(bugListBtn);
		
		newBugBtn = new JButton("");
		newBugBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content_panel, "new_bug");
				initializeNewBug();
			}
		});
		newBugBtn.setBackground(Color.WHITE);
		newBugBtn.setIcon(new ImageIcon("res/icon/png/ticket1_7.png"));
		menu_panel.add(newBugBtn);
		
		btnSearchBug = new JButton("");
		btnSearchBug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content_panel, "bug_search");
				initializeBugSearch();
			}
		});
		btnSearchBug.setIcon(new ImageIcon("res/icon/png/Search_16.png"));
		menu_panel.add(btnSearchBug);
		
		filterRoles();
	}
	
	protected void initializeBugSearch() {
		bug_search_panel.removeAll();
		
		txtIdsearch = new JTextField();
        txtIdsearch.setBounds(143, 60, 117, 26);
        bug_search_panel.add(txtIdsearch);
        txtIdsearch.setColumns(10);
        
        txtPriSearch = new JTextField();
        txtPriSearch.setColumns(10);
        txtPriSearch.setBounds(143, 88, 117, 26);
        bug_search_panel.add(txtPriSearch);
        
        txtSttSearch = new JTextField();
        txtSttSearch.setColumns(10);
        txtSttSearch.setBounds(143, 116, 117, 26);
        bug_search_panel.add(txtSttSearch);
        
        txtAssUsrSearch = new JTextField();
        txtAssUsrSearch.setColumns(10);
        txtAssUsrSearch.setBounds(143, 144, 117, 26);
        bug_search_panel.add(txtAssUsrSearch);
        
        txtCompSearch = new JTextField();
        txtCompSearch.setColumns(10);
        txtCompSearch.setBounds(143, 172, 117, 26);
        bug_search_panel.add(txtCompSearch);
        
		// Date picker open source
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		datePicker.setBounds(406, 57, 202, 29);
		bug_search_panel.add(datePicker);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(28, 65, 54, 16);
        bug_search_panel.add(lblId);
        
        JLabel lblStatus = new JLabel("Priority");
        lblStatus.setBounds(28, 93, 61, 16);
        bug_search_panel.add(lblStatus);
        
        JLabel label = new JLabel("Status");
        label.setBounds(28, 121, 61, 16);
        bug_search_panel.add(label);
        
        JLabel lblAssignedUser = new JLabel("Assigned User");
        lblAssignedUser.setBounds(28, 149, 103, 16);
        bug_search_panel.add(lblAssignedUser);
        
        JLabel lblComponent_1 = new JLabel("Component");
        lblComponent_1.setBounds(28, 177, 82, 16);
        bug_search_panel.add(lblComponent_1);
        
        JLabel lblCreationDate = new JLabel("Creation Date");
        lblCreationDate.setBounds(291, 65, 103, 16);
        bug_search_panel.add(lblCreationDate);
        
        lblReportedUser = new JLabel("Reported User");
        lblReportedUser.setBounds(28, 205, 103, 16);
        bug_search_panel.add(lblReportedUser);
        
        txtReprtdSearch = new JTextField();
        txtReprtdSearch.setColumns(10);
        txtReprtdSearch.setBounds(143, 200, 117, 26);
        bug_search_panel.add(txtReprtdSearch);
        
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Map<String, Object> criteria = new HashMap<String, Object>();
        		// get criteria
        		String ID = txtIdsearch.getText();
        		String pri = txtPriSearch.getText();
        		String stt = txtSttSearch.getText();
        		String assusr = txtAssUsrSearch.getText();
        		String com = txtCompSearch.getText();
        		String repusr = txtReprtdSearch.getText();
        		Date creationDate = (Date) datePicker.getModel().getValue();
        		
        		if (!ID.equals("")) {
        			criteria.put("ID", Integer.parseInt(ID));
        		}
        		
        		if (!pri.equals("")) {
        			Priority tmp = primng.getPriorityByName(pri);
        			if (tmp != null) {
        				criteria.put("priority", tmp.getID());
        			} else {
        				criteria.put("priority", null);
        			}
        		}
        		
        		if (!stt.equals("")) {
        			Status tmp = sttmng.getStatusByName(stt);
        			if (tmp != null) {
        				criteria.put("status", tmp.getID());
        			}else {
        				criteria.put("status", null);
        			}
        		}
        		
        		if (!assusr.equals("")) {
        			User tmp = usrmng.findByUserName(assusr);
        			if (tmp != null) {
        				criteria.put("assignedUser", Integer.parseInt(tmp.getID()));
        			}else {
        				criteria.put("assignedUser", null);
        			}
        		}
        		
        		if (!com.equals("")) {
        			Component tmp = commng.getComponentByName(com);
        			if (tmp != null) {
        				criteria.put("component", tmp.getID());
        			}else {
        				criteria.put("component", null);
        			}
        		}
        		
        		if (!repusr.equals("")) {
        			User tmp = usrmng.findByUserName(repusr);
        			if (tmp != null) {
        				criteria.put("reportedUser", Integer.parseInt(tmp.getID()));
        			}else {
        				criteria.put("reportedUser", null);
        			}
        		}
        		
        		if (creationDate != null) {
        			criteria.put("creationDate",creationDate);
        		}
        		
        		allTickets = bugmng.getTicketbyCriteria(criteria);
        		initializeTable(false);
        		cardLayout.show(content_panel, "bug_list");
        	}
        });
        btnSearch.setBounds(222, 261, 117, 29);
        bug_search_panel.add(btnSearch);
        
        bug_search_panel.revalidate();
        bug_search_panel.repaint();
	}

	private void filterRoles() {
		if (crrUser.getRoles().equals("Developer")) {
			newBugBtn.setVisible(false);
			bugListBtn.setVisible(true);
		} else if (crrUser.getRoles().equals("Reporter")) {
			newBugBtn.setVisible(true);
			bugListBtn.setVisible(true);
		} else if (crrUser.getRoles().equals("Triager")) {
			newBugBtn.setVisible(false);
			bugListBtn.setVisible(true);
		} else if (crrUser.getRoles().equals("Reviewer")) {
			newBugBtn.setVisible(false);
			bugListBtn.setVisible(true);
		} else if (crrUser.getRoles().equals("Manager")) {
			newBugBtn.setVisible(false);
			bugListBtn.setVisible(true);
		}
	}

	private void initializeNewBug() {
		new_bug_panel.removeAll();
		
		JLabel lblNewLabel_1 = new JLabel("Create a new Bug");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_1.setBounds(0, 18, 693, 33);
		new_bug_panel.add(lblNewLabel_1);
		
		JLabel lblDes = new JLabel("Description:");
		lblDes.setBounds(6, 92, 106, 29);
		new_bug_panel.add(lblDes);
		
		des = new JTextArea();
		des.setBounds(124, 98, 331, 213);
		new_bug_panel.add(des);
		
		JLabel lblComponent = new JLabel("Component:");
		lblComponent.setBounds(6, 63, 106, 29);
		new_bug_panel.add(lblComponent);
		
		component_cbb = new JComboBox();
		component_cbb.setModel(new DefaultComboBoxModel(commng.getComponentList()));
		component_cbb.setBounds(124, 65, 119, 27);
		new_bug_panel.add(component_cbb);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ticket ticket = recordNewBug();
				cardLayout.show(content_panel, "bug_detail");
				initializeBugDetail(ticket);
			}

		});
		btnCreate.setBounds(126, 358, 117, 29);
		new_bug_panel.add(btnCreate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(243, 358, 117, 29);
		new_bug_panel.add(btnReset);
		
		new_bug_panel.revalidate();;
		new_bug_panel.repaint();
	}

	private void initializeBugDetail(Ticket ticket) {
		bug_detail_panel.removeAll();
		TicketDetailView view = new TicketDetailView(ticket, crrUser, bug_detail_panel);
		bug_detail_panel.add(view);
		bug_detail_panel.revalidate();
		bug_detail_panel.repaint();
	}

	public void initializeTable(boolean getAllTicket) {
		bug_list_panel.removeAll();
		if (getAllTicket)
		allTickets = bugmng.getAllTicket();
		
		if (allTickets.size() == 0) {
			return;
		}
		
		Object[] columns = {"ID", "Priority", "Status", "Assigned User", "Component", "Creation Date"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column){  
		          return false;  
		    }
			
		}; // create with zero rows then will continue to add rows
		tableModel.setColumnIdentifiers(columns);
		ticketTable = new JTable(tableModel);
		ticketTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // double right click
					selectRow();
				}
			}
		});
		
		for (Ticket t : allTickets) {
			String[] row = { t.getID(), // ID
							t.getPriority() == null ? "":t.getPriority().getName(), // priority
							t.getStatus() == null ? "":t.getStatus().getName(), // status
							t.getAssignedUser() == null ? "":t.getAssignedUser().getUserName(), // assigned user
							t.getComponent() == null ? "":t.getComponent().getName(), // component
							new DateFormatter().valueToString(t.getCreationTime().getTime()), // creation date
			};
			tableModel.addRow(row);
		}
		
		ticketTable.setBounds(6, 21, 681, 260);
		JScrollPane scroll = new JScrollPane(ticketTable);
		scroll.setBounds(6, 6, 681, 381);
        ticketTable.setFillsViewportHeight(true);
//        ticketTable.setForeground(Color.RED);
        ticketTable.setRowHeight(30);
        ticketTable.setAutoCreateRowSorter(true);
        
        ticketTable.setGridColor(Color.BLACK);
        bug_list_panel.add(scroll);
        
        bug_list_panel.revalidate();
        bug_list_panel.repaint();
	}

	private void selectRow() {
		int selectedRow = ticketTable.convertRowIndexToModel(ticketTable.getSelectedRow());
		Ticket ticket = allTickets.get(selectedRow);
		cardLayout.show(content_panel, "bug_detail");
		initializeBugDetail(ticket);
	}
	
	private Ticket recordNewBug () {
		// Record new Bug
		String descr = des.getText();
		String comp = (String)component_cbb.getSelectedItem();
		Component component =  commng.getComponentByName(comp);
		Status status = sttmng.getStatusByName("New");
//		User assignedUser = usrmng.findByUserName((String)usr_cbb.getSelectedItem());
		User reportedUser = crrUser;
		
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
