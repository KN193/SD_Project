package com.uow.assignment.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InformationView extends JPanel {

	/**
	 * Create the panel.
	 */
	public InformationView() {
		setLayout(null);
		
		JLabel lblGroupProject = new JLabel("Group Project");
		lblGroupProject.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblGroupProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupProject.setBounds(250, 6, 246, 42);
		add(lblGroupProject);
		
		JLabel lblGroupMembers = new JLabel("Group Members:");
		lblGroupMembers.setBounds(6, 82, 113, 16);
		add(lblGroupMembers);
		
		JLabel lblGroupNumber = new JLabel("Group Number:  11");
		lblGroupNumber.setBounds(6, 56, 160, 16);
		add(lblGroupNumber);
		
		JLabel lblHoangKim = new JLabel("- Hoang Kim Nguyen");
		lblHoangKim.setBounds(131, 82, 259, 16);
		add(lblHoangKim);
		
		JLabel label = new JLabel("- Hamza Shabbir");
		label.setBounds(131, 110, 259, 16);
		add(label);
		
		JLabel label_1 = new JLabel("- Sean Rodden");
		label_1.setBounds(131, 138, 259, 16);
		add(label_1);
		
		JLabel label_2 = new JLabel("- Qiusheng (Tim) Chu");
		label_2.setBounds(131, 166, 259, 16);
		add(label_2);
		
		JLabel label_3 = new JLabel("- Zhao (Frank) Jianbo");
		label_3.setBounds(131, 194, 259, 16);
		add(label_3);
		
		JLabel label_4 = new JLabel("- Adhith Thyagarajan");
		label_4.setBounds(131, 222, 259, 16);
		add(label_4);
		
		JLabel label_5 = new JLabel("All the source is updated and maitained at: ");
		label_5.setBounds(6, 298, 280, 16);
		add(label_5);
		
		JLabel label_6 = new JLabel("https://github.com/KN193/SD_Project");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
	                try {
	                    Desktop.getDesktop().browse(new URI("https://github.com/KN193/SD_Project"));
	                } catch (Throwable t) {
	                    //
	                }
	            }
			}
		});
		label_6.setBounds(298, 298, 280, 16);
		label_6.setForeground(Color.BLUE.darker());
		label_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(label_6);
	}
}
