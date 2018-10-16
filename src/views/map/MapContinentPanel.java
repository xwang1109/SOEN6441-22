package views.map;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import models.map.*;


public class MapContinentPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3592055955052270313L;
	
	private JTable continentTable;
	
	public MapContinentPanel() {
		super();
	}
	
	public JTable getContinentTable() {
		return this.continentTable;
	}
	
	
	
	public void addContinentTableForMapEditor(Map map) {
		
		ArrayList<Continent>continentList = map.getContinentList();
		String[][] mapData = new String[continentList.size()][3];
        for(int i=0;i<continentList.size();i++) {
        	Continent continent = continentList.get(i);
        	mapData[i][0] = Integer.toString(continent.getID());
        	mapData[i][1] = continent.getName();
        	mapData[i][2] = Integer.toString(continent.getControlValue());
        }
        String[] columnNames = {"ID" ,"Continent Name", "Value" }; 
        
        JTable mapTable = new JTable(mapData,columnNames);
        mapTable.setBounds(30, 40, 200, 300); 
        this.continentTable = mapTable;
        this.getViewport().add(continentTable);  
        continentTable.setDefaultEditor(Object.class, null);
	}
	
	
	
}
