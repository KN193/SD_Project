package com.uow.assignment.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.uow.assignment.controller.PriorityManager;
import com.uow.assignment.controller.ReputationManager;
import com.uow.assignment.controller.StatusManager;
import com.uow.assignment.controller.TicketManager;
import com.uow.assignment.controller.UserManager;
import com.uow.assignment.model.Reputation;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;
import com.uow.assignment.view.dialog.CommentDialog;

public class TicketDetailView extends JPanel {

	private User crrUsr;
	private Ticket ticket;
	private JLabel priorityTxt;
	private JLabel statusTxt;
	private JButton btnPriority,btnChangeStatus,btnAssignTicket,btnAddPatch,btnDownloadPatch,btnCommentTicket;
	private JLabel lblAssignedUser, assignedUserTxt, patchTxt, lblLike, lblDislike;
	private Reputation rep;
	private TicketManager tkmng = new TicketManager();
	private StatusManager sttmng = new StatusManager();
	private PriorityManager primng = new PriorityManager();
	private UserManager usrmng = new UserManager();
	private ReputationManager repmng = new ReputationManager();
	/**
	 * Create the panel.
	 * @param bug_detail_panel 
	 */
	public TicketDetailView(final Ticket ticket, final User crrUsr, final JPanel bug_detail_panel) {
		this.ticket = ticket;
		this.crrUsr = crrUsr;
		setBounds(0, 32, 695, 379);
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
		panel.setBounds(0, 308, 695, 69);
		add(panel);
		
		btnPriority = new JButton("Set Priority");
		btnPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = primng.getPriorityList();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Set Priority",
			        "Set Priority", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[0]); // Initial choice
			    if (input != null && !input.equals("")) {
				    updateTicketPriority(input);
				    priorityTxt.setText(input);
			    }
			}

		});
		panel.add(btnPriority);
		
		btnChangeStatus = new JButton("Change Status");
		btnChangeStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = sttmng.getAllStatusesList();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Set Status",
			        "Set Status", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[0]); // Initial choice
			    
			    if (input != null && !input.equals("")) {
				    updateTicketStatus(input);
				    statusTxt.setText(input);
			    }
			}
		});
		panel.add(btnChangeStatus);
		
		btnAssignTicket = new JButton("Assign Ticket");
		btnAssignTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] choices = usrmng.getListOfUser();
			    String input = (String) JOptionPane.showInputDialog(getParent(), "Assign to",
			        "Assign Ticket", JOptionPane.QUESTION_MESSAGE, null,
			        choices, // Array of choices
			        choices[0]); // Initial choice
			    if (input != null && !input.equals("")) {
			    	updateTicketAssign(input);
			    	assignedUserTxt.setText(input);
			    }
			}

		});
		panel.add(btnAssignTicket);
		
		btnAddPatch = new JButton("Add Patch");
		btnAddPatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}

		});
		panel.add(btnAddPatch);
		
		btnDownloadPatch = new JButton("Download Patch");
		btnDownloadPatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (patchTxt.getText().equalsIgnoreCase("Patch is attached")) {
					savePatch();
				} else {
					JOptionPane.showMessageDialog(getParent(), "No Patch is attached for this ticket", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		panel.add(btnDownloadPatch);
		
//		JButton btnEditTicket = new JButton("Edit Ticket");
//		panel.add(btnEditTicket);
		
		btnCommentTicket = new JButton("Comment Ticket");
		btnCommentTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommentDialog dialog = new CommentDialog(ticket, crrUsr, SwingUtilities.windowForComponent(getParent()));
				dialog.setModal(true);
				dialog.setVisible(true);
			}
		});
		panel.add(btnCommentTicket);
		
		JLabel lblAttachedPatch = new JLabel("Attached patch:");
		lblAttachedPatch.setBounds(443, 62, 100, 16);
		add(lblAttachedPatch);
		
		String patchDisplay = ticket.isPatchAttached() ? "Patch is attached": "No patch attached";
		patchTxt = new JLabel(patchDisplay);
		patchTxt.setBounds(550, 62, 139, 16);
		
		if (ticket.isPatchAttached()) {
			patchTxt.setForeground(Color.BLUE);
		} else {
			patchTxt.setForeground(Color.RED);
		}
		add(patchTxt);
		
		JLabel lblIsThisA = new JLabel("Is this a good bug:");
		lblIsThisA.setBounds(443, 90, 129, 16);
		add(lblIsThisA);
		
		lblLike = new JLabel("");
		lblLike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rep == null) {
					repmng.addReputation(ticket, crrUsr, true); // like
					checkReputation();
					showNotifyMessage();
				} else if (!rep.isLikeOrDislike()) { // if this already rated as dislike
					repmng.updateReputation(ticket, crrUsr, true); // update to like
					checkReputation();
					showNotifyMessage();
				} else { // remove the reputation
					repmng.removeReputation(ticket, crrUsr); // remove the reputation
					checkReputation();
					showNotifyMessage();
				}
			}

		});
		lblLike.setIcon(new ImageIcon("res/icon/png/up_dis.png"));
		lblLike.setBounds(584, 90, 34, 31);
		add(lblLike);
		
		lblDislike = new JLabel("");
		lblDislike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rep == null) {
					repmng.addReputation(ticket, crrUsr, false); // dislike
					checkReputation();
					showNotifyMessage();
				} else if (rep.isLikeOrDislike()) { // if this already rated as like
					repmng.updateReputation(ticket, crrUsr, false); // update to dislike
					checkReputation();
					showNotifyMessage();
				} else { // remove the reputation
					repmng.removeReputation(ticket, crrUsr); // remove the reputation
					checkReputation();
					showNotifyMessage();
				}
			}
		});
		lblDislike.setIcon(new ImageIcon("res/icon/png/down_dis.png"));
		lblDislike.setBounds(630, 90, 34, 31);
		add(lblDislike);
		
		checkReputation();
		filterRoles();
	}
	
	private void filterRoles() {
		if (crrUsr.getRoles().equals("Developer")) {
			btnAddPatch.setVisible(true);
			btnAssignTicket.setVisible(false);
			btnChangeStatus.setVisible(false);
			btnCommentTicket.setVisible(true);
			btnDownloadPatch.setVisible(true);
			btnPriority.setVisible(false);
		} else if (crrUsr.getRoles().equals("Reporter")) {
			btnAddPatch.setVisible(false);
			btnAssignTicket.setVisible(false);
			btnChangeStatus.setVisible(false);
			btnCommentTicket.setVisible(true);
			btnDownloadPatch.setVisible(false);
			btnPriority.setVisible(false);
		} else if (crrUsr.getRoles().equals("Triager")) {
			btnAddPatch.setVisible(false);
			btnAssignTicket.setVisible(true);
			btnChangeStatus.setVisible(true);
			btnCommentTicket.setVisible(true);
			btnDownloadPatch.setVisible(false);
			btnPriority.setVisible(true);
		} else if (crrUsr.getRoles().equals("Reviewer")) {
			btnAddPatch.setVisible(false);
			btnAssignTicket.setVisible(false);
			btnChangeStatus.setVisible(false);
			btnCommentTicket.setVisible(true);
			btnDownloadPatch.setVisible(true);
			btnPriority.setVisible(false);
		} else if (crrUsr.getRoles().equals("Manager")) {
			btnAddPatch.setVisible(false);
			btnAssignTicket.setVisible(true);
			btnChangeStatus.setVisible(true);
			btnCommentTicket.setVisible(true);
			btnDownloadPatch.setVisible(true);
			btnPriority.setVisible(true);
		}
		
	}

	private void checkReputation() {
		rep = repmng.checkReputation(ticket, crrUsr);
		if (rep != null) {
			if (rep.isLikeOrDislike()) {  //True is like, False is Dislike
				lblLike.setIcon(new ImageIcon("res/icon/png/up_ava.png"));
				lblDislike.setIcon(new ImageIcon("res/icon/png/down_dis.png"));
			} else {
				lblDislike.setIcon(new ImageIcon("res/icon/png/down_ava.png"));
				lblLike.setIcon(new ImageIcon("res/icon/png/up_dis.png"));
			}
		} else {
			lblLike.setIcon(new ImageIcon("res/icon/png/up_dis.png"));
			lblDislike.setIcon(new ImageIcon("res/icon/png/down_dis.png"));
		}
	}

	private void showNotifyMessage() {
		if (rep == null) {
			JOptionPane.showMessageDialog(this, "You have removed reputation for this ticket", "Reputation Changed", JOptionPane.INFORMATION_MESSAGE);
		} else if (rep.isLikeOrDislike()) { // like
			JOptionPane.showMessageDialog(this, "You have agreed that this ticket is good", "Reputation Changed", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "You have agreed that this ticket is bad", "Reputation Changed", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void savePatch() {
		JFileChooser fileChooser = new JFileChooser();
		int returnedValue = fileChooser.showSaveDialog(this);
		if (returnedValue == JFileChooser.APPROVE_OPTION) {
			String fileName = fileChooser.getSelectedFile().getName();
			String fullPath = fileChooser.getCurrentDirectory().getAbsolutePath();
			InputStream in = tkmng.getTicketPatch(ticket);
			
			OutputStream f;
			try {
				f = new FileOutputStream(new File(fullPath + "/" + fileName));

				int c = 0;
				while ((c = in.read()) > -1) {
					f.write(c);
				}
				f.close();
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
        
	}
	
	private void chooseFile() {
		JFileChooser fileChooser = new JFileChooser();
        int returnedValue = fileChooser.showOpenDialog(this);
        if (returnedValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          ticket.setPatch(selectedFile);
          ticket.setPatchAttached(true);
          tkmng.addTicketPatch(ticket);
        }
        
        patchTxt.setText("Patch is attached");
        patchTxt.setForeground(Color.BLUE);
	}
	
	private void updateTicketPriority(String input) {
		// TODO Auto-generated method stub
		 ticket.setPriority(primng.getPriorityByName(input));
		 tkmng.updateTicketPriority(ticket);
	}

	private void updateTicketStatus(String input) {
		// TODO Auto-generated method stub
		ticket.setStatus(sttmng.getStatusByName(input));
		tkmng.updateTicketStatus(ticket);
	}
	
	private void updateTicketAssign(String input) {
		ticket.setAssignedUser(usrmng.findByUserName(input));
		tkmng.updateTicketAssign(ticket);
		
	}
}
