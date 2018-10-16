package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.map.*;
import views.map.CountryView;

public class CountryController implements ActionListener {
	
	Map map;
	CountryView view;
	int id;
	
	public CountryController(CountryView view, Map map) {
		this.map = map;
		this.view = view;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		switch(actionCommand) {
		case "Create":
			createCountry();
			break;
		case "Save":
			editCountry();
			break;
		case "Delete":
			deleteCountry();
			break;
		}
		
	}
	
	
	public void createCountry() {
		String countryName = view.getCountryName();
		String continentName = view.getContinentName();
		
		if(map.checkDuplicateCountryName(countryName)) {
			JOptionPane.showMessageDialog(null, "The country's name must be unique!");
		}
		
		else {
			Country country = new Country(countryName);
			country.setContinent(map.getContinentByName(continentName));
			map.addCountry(country);
			view.setVisible(false);
			view.dispose();
			
		}
		
		
	}

	
	public void editCountry() {
		
	}
	
	public void deleteCountry() {
		
	}
}
