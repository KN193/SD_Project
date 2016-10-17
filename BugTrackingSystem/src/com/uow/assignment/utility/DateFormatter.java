package com.uow.assignment.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    public DateFormatter(){};
	public DateFormatter(String pattern) {
		this.datePattern = pattern;
		dateFormatter = null;
		dateFormatter = new SimpleDateFormat(datePattern);
	}
	
    @Override
    public Object stringToValue(String text){
        try {
			return dateFormatter.parseObject(text);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }

    @Override
    public String valueToString(Object value){
        if (value != null) {
        	if (value instanceof Calendar) {
        		 Calendar cal = (Calendar) value;
        		 return dateFormatter.format(cal.getTime());
        	} else {
        		return dateFormatter.format(value);
        	}
        }

        return "";
    }
    
}
