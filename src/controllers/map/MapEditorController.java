package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import views.map.*;

import models.map.Map;

public class MapEditorController implements ActionListener {
	
	private Map map;
	private MapEditorView view;
	
	public MapEditorController(Map map, MapEditorView view) {
		this.map = map;
		this.view = view;
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
		
		case "Edit Country":
			editCountry();
			break;
			
		case "Edit Continent":
			editContinent();
			break;
		}	
	}
	
	
	public void addCountry() {
		CountryView newCountryView = new CountryView(map);
		newCountryView.setVisible(true);

	}
	
	public void addContinent() {
		ContinentView newContinentView = new ContinentView(map);
		newContinentView.setVisible(true);
	}
	
	
	public void deleteCountry() {
		
	}
	
	
	public void deleteContinent() {
		
	}
	
	
	public void editCountry() {
		
		
	}
	
	public void editContinent() {
		
		int id = this.view.getSelectedContinentID();
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "Please select a continent first!");
		}
		
		ContinentView editContinentView = new ContinentView(map,id);
		editContinentView.setVisible(true);
	}
	
	
	
}
