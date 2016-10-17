package com.uow.assignment.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import com.uow.assignment.controller.ReportManager;
import com.uow.assignment.utility.DateFormatter;

public class ReportView extends JPanel {

	private JDatePickerImpl frmDatePicker,toDatePicker;
	private final JPanel content_panel;
	private final CardLayout cardLayout;
	private JPanel user_report_panel, ticket_report_panel, ticketPanel, userPanel;
	ReportManager rpmng = new ReportManager();
	/**
	 * Create the panel.
	 */
	public ReportView() {
		setLayout(null);
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
		
		user_report_panel = new JPanel();
		content_panel.add(user_report_panel, "user_report");
		user_report_panel.setLayout(null);
		
		ticket_report_panel = new JPanel();
		content_panel.add(ticket_report_panel, "ticket_report");
		ticket_report_panel.setLayout(null);
		
		JButton usrrpBtn = new JButton("");
		usrrpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content_panel, "user_report");
				initializeUserReport();
			}
		});
		usrrpBtn.setIcon(new ImageIcon("res/icon/png/search_male_user_7.png"));
		menu_panel.add(usrrpBtn);
		
		JButton ticketRpBtn = new JButton("");
		ticketRpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content_panel, "ticket_report");
				initializeTicketReport();
			}
		});
		ticketRpBtn.setBackground(Color.WHITE);
		ticketRpBtn.setIcon(new ImageIcon("res/icon/png/ticket1_7.png"));
		menu_panel.add(ticketRpBtn);
	}
	
	protected void initializeUserReport() {
		user_report_panel.removeAll();
		
		userPanel = new JPanel();
		userPanel.setBounds(6, 6, 681, 381);
		user_report_panel.add(userPanel);
		
		initPopulationGraph();
		
		user_report_panel.revalidate();
		user_report_panel.repaint();
	}
	
	private void initPopulationGraph() {
		userPanel.removeAll();
		CategoryDataset dataset = createUserDataset();
		JFreeChart chart = ChartFactory.createStackedBarChart("User Reputation Chart","User",     // domain axis label
				        "Number of Like/Dislike", // range axis label
				              dataset,         // data
				              PlotOrientation.HORIZONTAL,
				              true,            // include legend
				              true,            // tooltips
				              true            // urls
				          );
		ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(445, 369));
        chartPanel.setBounds(6, 47, 681, 340);
        userPanel.add(chartPanel);
        userPanel.repaint();
        userPanel.revalidate();
        
        userPanel.revalidate();
        userPanel.repaint();
	}

	protected void initializeTicketReport() {
		ticket_report_panel.removeAll();
		
		JLabel lblfrmDate = new JLabel("From Date:");
		lblfrmDate.setBounds(6, 6, 77, 29);
		ticket_report_panel.add(lblfrmDate);
		
		// Date picker open source
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		frmDatePicker = new JDatePickerImpl(datePanel, new DateFormatter());
		frmDatePicker.setBounds(95, 6, 202, 29);
		ticket_report_panel.add(frmDatePicker);
		
		JLabel lbltoDate = new JLabel("To Date:");
		lbltoDate.setBounds(309, 6, 63, 29);
		ticket_report_panel.add(lbltoDate);
		
		// Date picker open source
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		toDatePicker = new JDatePickerImpl(datePanel2, new DateFormatter());
		toDatePicker.setBounds(384, 6, 202, 29);
		ticket_report_panel.add(toDatePicker);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initGraph();
			}
		});
		btnGenerate.setBounds(598, 7, 89, 29);
		ticket_report_panel.add(btnGenerate);
		
		ticketPanel = new JPanel();
		ticketPanel.setBounds(6, 47, 681, 340);
		ticket_report_panel.add(ticketPanel);
		ticketPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ticket_report_panel.revalidate();
		ticket_report_panel.repaint();
	}
	
	private CategoryDataset createUserDataset() {
		DefaultKeyedValues2DDataset data = rpmng.getUserReputation();
//		 data.addValue(-6.0, "Male", "70+");
//		        data.addValue(-8.0, "Male", "60-69");
//		         data.addValue(-11.0, "Male", "50-59");
//       data.addValue(-13.0, "Male", "40-49");
//		         data.addValue(-14.0, "Male", "30-39");
//		         data.addValue(-15.0, "Male", "20-29");
//		         data.addValue(-19.0, "Male", "10-19");
//		         data.addValue(-21.0, "Male", "0-9");
//		         data.addValue(10.0, "Female", "70+");
//		         data.addValue(12.0, "Female", "60-69");
//		         data.addValue(13.0, "Female", "50-59");
//		         data.addValue(14.0, "Female", "40-49");
//		        data.addValue(15.0, "Female", "30-39");
//		         data.addValue(17.0, "Female", "20-29");
//		         data.addValue(19.0, "Female", "10-19");
//		         data.addValue(20.0, "Female", "0-9");
		return data;
	}

	private void initGraph() {
		ticketPanel.removeAll();
		// This will create the dataset 
        PieDataset dataset = createTicketDataset();
        
        if (dataset == null) {
        	return;
        }
        
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, "Ticket Report");
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(445, 369));
        chartPanel.setBounds(6, 47, 681, 340);
        ticketPanel.add(chartPanel);
        ticketPanel.repaint();
        ticketPanel.revalidate();
	}

	private JFreeChart createChart(PieDataset dataset, String title) {
		JFreeChart chart = ChartFactory.createPieChart(title,          // chart title
	            dataset,                // data
	            true,                   // include legend
	            true,
	            false);

	        PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setStartAngle(290);
	        plot.setDirection(Rotation.CLOCKWISE);
	        plot.setForegroundAlpha(0.5f);
	        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
	                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
	        plot.setLabelGenerator(gen);
	        return chart;
	}

	private PieDataset createTicketDataset() {
		Date frmDateValue = (Date) frmDatePicker.getModel().getValue();
		Date toDateValue =(Date) toDatePicker.getModel().getValue();
		
		if (frmDateValue == null || toDateValue == null) {
			JOptionPane.showMessageDialog(this, "From date and to date cannot be blank", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Map<String , Integer> map = rpmng.getTicketProportationByTime(frmDateValue, toDateValue);
		
		DefaultPieDataset result = new DefaultPieDataset();
		
		if (!map.isEmpty()) {
			for (String key : map.keySet()) {
				if (key.equals("")) {
					result.setValue("Undefined", (Integer) map.get(key));
				} else {
					result.setValue(key, (Integer) map.get(key));
				}

			}
		}
        return result;
	}
}
