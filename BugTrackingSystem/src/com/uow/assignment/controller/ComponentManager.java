package com.uow.assignment.controller;

import java.util.ArrayList;

import com.uow.assignment.DAO.ComponentDAO;
import com.uow.assignment.model.Component;

public class ComponentManager {
	private static ArrayList<Component> allComponents = new ArrayList<Component>();
	private ComponentDAO componentDAO =  new ComponentDAO();
	
	public ComponentManager() {
		// initialize
		if (allComponents.size() == 0)
			allComponents = componentDAO.getAllComponent();
	}
	
	public String[] getComponentList() {
		String[] list = new String[allComponents.size()];
		int counter = 0;
		for (Component c : allComponents) {
			list[counter] = c.getName();
			counter++;
		}
		
		return list;
	}

	public Component getComponentByName(String com) {
		for (Component c : allComponents) {
			if (c.getName().equalsIgnoreCase(com))
				return c;
		}
		
		return null;
	}
	
	public Component getComponentByID(int id) {
		for (Component c : allComponents) {
			if (c.getID() == id)
				return c;
		}
		
		return null;
	}
}
