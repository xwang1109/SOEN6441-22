package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import views.map.*;
import models.map.*;



public class ContinentController implements ActionListener {

	
	private NewContinentView view;
	
	
	
	public ContinentController(NewContinentView view) {
		this.view = view;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String actionCommand = e.getActionCommand();

		switch(actionCommand) {
		case "Save":
			saveContinent();
			break;
		case "Delete":
			break;
		}
	}
	
	public void saveContinent() {
		String continentName = view.getContinentName();
		int cotinentValue = view.getContinentValue();
		
		Continent continent = new Continent();
		continent.setName(continentName);
		//continent.setValue(cotinentValue);
		
		
		
	}
}
