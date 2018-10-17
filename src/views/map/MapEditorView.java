package views.map;

import javax.swing.JFrame;

import controllers.map.*;
import models.map.*;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class MapEditorView extends JFrame implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Map map;
	private JTable mapTable;
	
	
	
	public MapEditorView() {
		map = new Map();
		File mapFile = new File("C:\\Users\\Xinyan Wang\\Documents\\Earth.map");
		map.loadMapFromFile(mapFile);
		this.setSize(1024,800);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel controlPanel = new JPanel();
		getContentPane().add(controlPanel, BorderLayout.LINE_START);
		
		JButton addCountryButton = new JButton("Add Country");
		addCountryButton.addActionListener(new MapEditorController(map));
		
		
		JButton addContinentButton = new JButton("Add Continent");
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.add(addCountryButton);
		controlPanel.add(addContinentButton);
		
		JButton deleteContinentButton = new JButton("Delete Continent");
		deleteContinentButton.addActionListener(new MapEditorController(map));
		
		JButton deleteCountryButton = new JButton("Delete Country");
		deleteCountryButton.addActionListener(new MapEditorController(map));
		controlPanel.add(deleteCountryButton);
		controlPanel.add(deleteContinentButton);
		addContinentButton.addActionListener(new MapEditorController(map));
		
		
		JPanel menuPanel = new JPanel();
		getContentPane().add(menuPanel, BorderLayout.PAGE_START);
		
		JMenuBar menuBar = new JMenuBar();
		menuPanel.add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem loadMenuItem = new JMenuItem("Load");
		fileMenu.add(loadMenuItem);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		
		
		
		 // Data to be displayed in the JTable 
        
        /*
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
        		mapData[i][2]+=adjCountry.getName()+" ";
        		
        	}  	
        }
        
        
  
        // Column Names 
        String[] columnNames = { "Country Name", "Continent", "Adj Country" }; 
        
        
        
        mapTable = new JTable(mapData,columnNames);
        mapTable.setBounds(30, 40, 200, 300); 
        JScrollPane countryPane = new JScrollPane(mapTable);
        */
		
		MapCountryPanel mapCountryPanel = new MapCountryPanel();
		mapCountryPanel.addCountryTableForMapEditor(map);
		
        getContentPane().add(mapCountryPanel, BorderLayout.CENTER);
        
        
        
        
        
        
	}



	
	@Override
	public void update(Observable map, Object x) {
		this.map = (Map)map;
		
		
	}	

	

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
