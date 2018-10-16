package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import views.map.*;
import models.map.*;



public class ContinentController implements ActionListener {

	
	private ContinentView view;
	private Map map;
	private int id;
	
	public ContinentController(ContinentView view, Map map) {
		this.view = view;
		this.map = map;
	}
	
	public ContinentController(ContinentView view,  Map map, int continentID) {
		this(view,map);
		this.id = continentID;
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String actionCommand = e.getActionCommand();

		switch(actionCommand) {
		case "Create":
			createContinent();
			break;
		
		case "Save":
			editContinent();
			break;
		case "Delete":
			break;
		}
	}
	
	
	public void createContinent() {
		String continentName = view.getContinentName();
		String cotinentValueString = view.getContinentValue();
		int continentValue = 0;
		
		try {
			continentValue = Integer.parseInt(cotinentValueString);
			if(continentValue<=0) {
				JOptionPane.showMessageDialog(null, "Please enter a positive numvber!");
			}
			else {
				if(map.checkDuplicateContinentName(continentName,-1)) {
					JOptionPane.showMessageDialog(null, "The cotinent's name must be unique!");
				}
				else {
					Continent continent = new Continent(continentName,continentValue);
					map.addContinent(continent);
					this.view.setVisible(false);
					view.dispose();
					
				}	
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a integer for cotinent value!");	
		}
		
	}
	
	
	private void editContinent() {
		String continentName = view.getContinentName();
		String cotinentValueString = view.getContinentValue();
		int continentValue = 0;
		
		try {
			continentValue = Integer.parseInt(cotinentValueString);
			if(continentValue<=0) {
				JOptionPane.showMessageDialog(null, "Please enter a positive numvber!");
			}
			else {
				
				// if the name exists and it is not the selected continent's name
				if(map.checkDuplicateContinentName(continentName, id)) {
					
					JOptionPane.showMessageDialog(null, "The cotinent's name must be unique!");
				}
				else {
					this.map.updateContinentByID(id, continentName, continentValue);
					this.view.setVisible(false);
					view.dispose();
					
				}	
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a integer for cotinent value!");	
		}
		
	}

	
	
}
