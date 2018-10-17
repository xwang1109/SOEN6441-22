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
        String[][] mapData = new String[countryList.size()][4];
        for(int i=0;i<countryList.size();i++) {
        	
        	Country country = countryList.get(i);
        	mapData[i][0] = Integer.toString(country.getID());
        	mapData[i][1] = country.getName();
        	mapData[i][2] = country.getContinent().getName();
        	mapData[i][3] = "";
        	ArrayList<Country> adjCountryList = country.getAdjacentCountryList();
        	
        	for(int j=0;j<adjCountryList.size();j++) {
        		Country adjCountry = adjCountryList.get(j);
        		if(j!=adjCountryList.size()-1) {
        			mapData[i][3]+=adjCountry.getName()+", ";
        		}
        		else {
        			mapData[i][3]+=adjCountry.getName();
        		}
        		
        	}  	
        }
        String[] columnNames = { "ID","Country Name", "Continent", "Adj Country" }; 
        
        JTable mapTable = new JTable(mapData,columnNames);
        mapTable.setBounds(30, 40, 200, 300); 
        mapTable.setRowHeight(40);
        mapTable.setFont(new Font("Serif", Font.BOLD, 20));
        mapTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mapTable.getColumnModel().getColumn(0).setMinWidth(100);
        mapTable.getColumnModel().getColumn(0).setMaxWidth(100);
        
        mapTable.getColumnModel().getColumn(1).setMinWidth(150);
        mapTable.getColumnModel().getColumn(1).setMaxWidth(150);
        
        mapTable.getColumnModel().getColumn(2).setMinWidth(150);
        mapTable.getColumnModel().getColumn(2).setMaxWidth(150);
       
        mapTable.getColumnModel().getColumn(3).setMinWidth(150);
        mapTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        mapTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        			mapData[i][3]+=adjCountry.getName()+", ";
        		}
        		else {
        			mapData[i][3]+=adjCountry.getName();
        		}
        	}
        	mapData[i][2] = Integer.toString(country.getNumOfArmies()) ;
        }
        String[] columnNames = { "Country Name", "Continent","Numer of Armies", "Adj Country"}; 
        
        
        
        
        JTable mapTable = new JTable(mapData,columnNames);
        mapTable.setBounds(30, 40, 200, 300); 
        mapTable.setRowHeight(40);
        mapTable.setFont(new Font("Serif", Font.BOLD, 20));
        
        mapTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mapTable.getColumnModel().getColumn(0).setMinWidth(150);
        mapTable.getColumnModel().getColumn(0).setMaxWidth(150);
        
        mapTable.getColumnModel().getColumn(1).setMinWidth(150);
        mapTable.getColumnModel().getColumn(1).setMaxWidth(150);
        
        mapTable.getColumnModel().getColumn(2).setMinWidth(100);
        mapTable.getColumnModel().getColumn(2).setMaxWidth(100);
       
        mapTable.getColumnModel().getColumn(3).setMinWidth(150);
     
        mapTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        mapTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.countryTable = mapTable;
        this.getViewport().add(countryTable); 
        countryTable.setDefaultEditor(Object.class, null);
	}
	
	
	
	
	
}
