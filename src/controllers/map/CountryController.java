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
	
	public CountryController(CountryView view, Map map, int id) {
		this(view, map);
		this.id = id;
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
		
		if(map.checkDuplicateCountryName(countryName,-1)) {
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
		String countryName = view.getCountryName();
		String continentName = view.getContinentName();
		
		if(map.checkDuplicateCountryName(countryName,id)) {
			JOptionPane.showMessageDialog(null, "The country's name must be unique!");
		}
		
		else {
			map.updateCountryByID(id, countryName, continentName);
			view.setVisible(false);
			view.dispose();	
		}
	}
	
	public void deleteCountry() {
		
	}
}
