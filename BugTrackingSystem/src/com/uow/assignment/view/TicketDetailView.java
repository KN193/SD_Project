package com.uow.assignment.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.uow.assignment.controller.PriorityManager;
import com.uow.assignment.controller.StatusManager;
import com.uow.assignment.controller.TicketManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Ticket;

public class TicketDetailView extends JPanel {

	private Ticket ticket;
	private JLabel priorityTxt;
	private JLabel statusTxt;
	private JLabel lblAssignedUser, assignedUserTxt;
	private TicketManager tkmng = new TicketManager();
	private StatusManager sttmng = new StatusManager();
	private PriorityManager primng = new PriorityManager();
	private UserManager usrmng = new UserManager();
	/**
	 * Create the panel.
	 * @param bug_detail_panel 
	 */
	public TicketDetailView(final Ticket ticket, final JPanel bug_detail_panel) {
		this.ticket = ticket;
		setBounds(0, 32, 695, 395);
		setLayout(null);
		
		JLabel lblBugId = new JLabel("Bug Id:");
		lblBugId.setBounds(6, 6, 91, 16);
		add(lblBugId);
		
		JLabel lblComponent = new JLabel("Component:");
		lblComponent.setBounds(6, 34, 91, 16);
		add(lblComponent);
		
		JLabel lblReportedUser = new JLabel("Reported User:");
		lblReportedUser.setBounds(443, 6, 100, 16);
		add(lblReportedUser);
		
		lblAssignedUser = new JLabel("Assigned User:");
		lblAssignedUser.setBounds(443, 34, 100, 16);
		add(lblAssignedUser);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(6, 62, 91, 16);
		add(lblStatus);
		
		JLabel lblPriority = new JLabel("Priority:");
		lblPriority.setBounds(6, 90, 91, 16);
		add(lblPriority);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(6, 118, 91, 16);
		add(lblDescription);
		
		JLabel bugIdTxt = new JLabel("New label");
		bugIdTxt.setBounds(109, 6, 228, 16);
		bugIdTxt.setText(ticket.getID());;
		add(bugIdTxt);
		
		JLabel componentTxt = new JLabel("New label");
		componentTxt.setBounds(109, 34, 228, 16);
		componentTxt.setText(ticket.getComponent().getName());
		add(componentTxt);
		
		statusTxt = new JLabel("New label");
		statusTxt.setBounds(109, 62, 228, 16);
		statusTxt.setText(ticket.getStatus().getName());
		add(statusTxt);
		
		priorityTxt = new JLabel("New label");
		priorityTxt.setBounds(109, 90, 228, 16);
		priorityTxt.setText(ticket.getPriority() == null ? "":ticket.getPriority().getName());
		add(priorityTxt);
		
		JLabel reportedUserTxt = new JLabel("New label");
		reportedUserTxt.setBounds(550, 6, 139, 16);
		reportedUserTxt.setText(ticket.getReportedUser() == null ? "":ticket.getReportedUser().getUserName());;
		add(reportedUserTxt);
		
		assignedUserTxt = new JLabel("New label");
		assignedUserTxt.setBounds(550, 34, 139, 16);
		assignedUserTxt.setText(ticket.getAssignedUser() == null ? "":ticket.getAssignedUser().getUserName());
		add(assignedUserTxt);
		
		JTextArea descriptionTxt = new JTextArea();
		descriptionTxt.setBounds(109, 118, 283, 178);
		descriptionTxt.setEditable(false);
		descriptionTxt.setText(ticket.getDescription());
		add(descriptionTxt);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(0, 308, 695, 40);
		add(panel);
		
		JButton btnPriority = new JButton("Set Priority");
		btnPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = primng.getPriorityList();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Set Priority",
			        "Set Priority", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[1]); // Initial choice
			    
			    updateTicketPriority(input);
			    priorityTxt.setText(input);
			}

		});
		panel.add(btnPriority);
		
		JButton btnChangeStatus = new JButton("Change Status");
		btnChangeStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = sttmng.getAllStatusesList();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Set Status",
			        "Set Status", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[1]); // Initial choice
			    
			    updateTicketStatus(input);
			    statusTxt.setText(input);
			}
		});
		panel.add(btnChangeStatus);
		
		JButton btnAssignTicket = new JButton("Assign Ticket");
		btnAssignTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = usrmng.getListOfUser();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Assign to",
			        "Assign Ticket", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[1]); // Initial choice
			    
			    updateTicketAssign(input);
			    assignedUserTxt.setText(input);
			}

		});
		panel.add(btnAssignTicket);
		
		JButton btnAddPatch = new JButton("Add Patch");
		panel.add(btnAddPatch);
		
		JButton btnEditTicket = new JButton("Edit Ticket");
		panel.add(btnEditTicket);
		
	}
	
	private void updateTicketPriority(String input) {
		// TODO Auto-generated method stub
		 ticket.setPriority(primng.getPriorityByName(input));
		 tkmng.updateBugPriority(ticket);
	}

	private void updateTicketStatus(String input) {
		// TODO Auto-generated method stub
		ticket.setStatus(sttmng.getStatusByName(input));
		tkmng.updateBugStatus(ticket);
	}
	
	private void updateTicketAssign(String input) {
		ticket.setAssignedUser(usrmng.findByUserName(input));
		tkmng.updateBugAssign(ticket);
		
	}
}
