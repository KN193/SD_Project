package com.uow.assignment.view.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.uow.assignment.controller.CommentManager;
import com.uow.assignment.model.Comment;
import com.uow.assignment.model.Ticket;
import com.uow.assignment.model.User;
import com.uow.assignment.utility.DateFormatter;

public class CommentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private CommentManager cmtmng = new CommentManager();
	private Ticket crrTicket;
	private User crrUsr;
	private JTextArea inputComment;

	/**
	 * Create the dialog.
	 */
	public CommentDialog(Ticket ticket, final User crrUsr, Window arg0) {
		super(arg0);
		crrTicket = ticket;
		this.crrUsr = crrUsr;
		setBounds(100, 100, 562, 491);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 532, 346);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scroll = new JScrollPane(contentPanel,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 346, 556, 123);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("Add Comment");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!inputComment.getText().trim().equals(""))
						{
							Comment temp =new Comment(inputComment.getText(), 0, new Date(), crrUsr, crrTicket);
							cmtmng.addNewComment(temp);
							addNewComment(temp);
							inputComment.setText("");
						}
					}

				});
				okButton.setBounds(450, 6, 100, 82);
				okButton.setActionCommand("Add Comment");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			inputComment = new JTextArea();
			JScrollPane scrollInput = new JScrollPane(inputComment);
			scrollInput.setBounds(6, 6, 439, 111);
			buttonPane.add(scrollInput);
		}
		scroll.setBounds(0, 0, 556, 346);
		getContentPane().add(scroll);
		initializeComment();
	}
	
	private void initializeComment() {
		ArrayList<Comment> all = cmtmng.getAllComment(crrTicket, crrUsr);
		for (Comment m : all) {
			addNewComment(m);
		}
	}

	private void addNewComment(Comment cmt) {
		JLabel label = new JLabel("User " + cmt.getCommentUser().getUserName() + " on " + new DateFormatter("yyyy-MM-dd HH:mm:ss").valueToString(cmt.getCreatedDate().getTime())
				, SwingConstants.LEFT);
		label.setPreferredSize(new Dimension(200, 15));
		label.setForeground(Color.BLUE);
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txt);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		txt.setText(cmt.getContent());
		contentPanel.add(label);
		contentPanel.add(scrollPane);
		contentPanel.add(Box.createRigidArea(new Dimension(10,10)));
		contentPanel.revalidate();
		contentPanel.repaint();
		
	}
}
