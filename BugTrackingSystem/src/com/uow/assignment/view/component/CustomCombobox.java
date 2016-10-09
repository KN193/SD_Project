package com.uow.assignment.view.component;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.uow.assignment.model.User;

public class CustomCombobox extends JComboBox {
	private String[] array;

	public CustomCombobox(String[] array) {
		super(array);
		this.array = array;
		this.setEditable(true);
		final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						filter(textfield);
					}
				});
			}
		});

	}

	private void filter(JTextField textfield) {
		ArrayList<String> filteredArray = new ArrayList<String>();
		String enteredText = textfield.getText();
		for (int i = 0; i < array.length; i++) {
			if (array[i].toLowerCase().startsWith(enteredText.toLowerCase())) {
				filteredArray.add(array[i]);
			}
		}

		// revalidate cbb
		DefaultComboBoxModel model = (DefaultComboBoxModel) this.getModel();
		model.removeAllElements();
		for (String s : filteredArray)
			model.addElement(s);

		textfield.setText(enteredText);
	}
}
