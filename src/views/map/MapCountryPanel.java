package views.map;


import java.awt.Font;
import java.util.ArrayList;

import javax.swing.*;

import models.game.Player;
import models.map.Country;
import models.map.Map;

public class MapCountryPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 827155075130092035L;
	private JTable countryTable;
	public MapCountryPanel() {
		super();
		 	
	}
	
	
	public JTable getCountryTable() {
		return this.countryTable;
	}
	
	public void addCountryTableForMapEditor(Map map) {
		ArrayList<Country> countryList = map.getCountryList();
        String[][] mapData = new String[countryList.size()][3];
        for(int i=0;i<countryList.size();i++) {
        	
        	Country country = countryList.get(i);
        	mapData[i][0] = country.getName();
        	mapData[i][1] = country.getContinent().getName();
        	mapData[i][2] = "";
        	ArrayList<Country> adjCountryList = country.getAdjacentCountryList();
        	
        	for(int j=0;j<adjCountryList.size();j++) {
        		Country adjCountry = adjCountryList.get(j);
        		if(j!=adjCountryList.size()-1) {
        			mapData[i][2]+=adjCountry.getName()+", ";
        		}
        		else {
        			mapData[i][2]+=adjCountry.getName();
        		}
        		
        	}  	
        }
        String[] columnNames = { "Country Name", "Continent", "Adj Country" }; 
        
        JTable mapTable = new JTable(mapData,columnNames);
        mapTable.setBounds(30, 40, 200, 300); 
        mapTable.setRowHeight(40);
        mapTable.setFont(new Font("Serif", Font.BOLD, 20));
        this.countryTable = mapTable;
        this.getViewport().add(countryTable);
        countryTable.setDefaultEditor(Object.class, null);

	}
	
	public void addCountryTableForReinforcement(Player player) {
		ArrayList<Country> countryList = player.getCountryList();
		String[][] mapData = new String[countryList.size()][4];
        for(int i=0;i<countryList.size();i++) {
        	
        	Country country = countryList.get(i);
        	mapData[i][0] = country.getName();
        	mapData[i][1] = country.getContinent().getName();
        	mapData[i][2] = "";
        	ArrayList<Country> adjCountryList = country.getAdjacentCountryList();
        	
        	for(int j=0;j<adjCountryList.size();j++) {
        		Country adjCountry = adjCountryList.get(j);
        		if(j!=adjCountryList.size()-1) {
        			mapData[i][2]+=adjCountry.getName()+", ";
        		}
        		else {
        			mapData[i][2]+=adjCountry.getName();
        		}
        	}
        	mapData[i][3] = Integer.toString(country.getNumOfArmies()) ;
        }
        String[] columnNames = { "Country Name", "Continent", "Adj Country", "Numer of Armies" }; 
        
        JTable mapTable = new JTable(mapData,columnNames);
        mapTable.setBounds(30, 40, 200, 300); 
        mapTable.setRowHeight(40);
        mapTable.setFont(new Font("Serif", Font.BOLD, 20));
        this.countryTable = mapTable;
        this.getViewport().add(countryTable); 
        countryTable.setDefaultEditor(Object.class, null);
	}
	
	
	
	
	
}
