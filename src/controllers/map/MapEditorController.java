package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.map.*;

import models.map.Map;

public class MapEditorController implements ActionListener {
	
	private Map map;
	
	public MapEditorController(Map map) {
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		switch(actionCommand) {
		case "Add Country":
			addCountry();
			break;
			
		case "Add Continent":
			addContinent();
			break;
		
		case "Delete Country":
			deleteCountry();
			break;
		
		case "Delete Continent":
			deleteContinent();
			break;
		}	
	}
	
	
	public void addCountry() {
		

	}
	
	
	public void addContinent() {
		NewContinentView newContinentView = new NewContinentView();
		newContinentView.setVisible(true);
	}
	
	
	public void deleteCountry() {
		
	}
	
	
	public void deleteContinent() {
		
	}
	
	
	
}
