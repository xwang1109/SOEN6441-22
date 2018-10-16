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

	private MapCountryPanel mapCountryPanel;
	private MapContinentPanel mapContinentPanel;
	
	
	public MapEditorView(Map map) {
		
		File mapFile = new File("C:\\Users\\Xinyan Wang\\Documents\\3D Cliff.map");
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
		
		JButton deleteCountryButton = new JButton("Delete Country");
		deleteCountryButton.addActionListener(new MapEditorController(map));
		
		JButton editCountryButton = new JButton("Edit Country");
		controlPanel.add(editCountryButton);
		controlPanel.add(deleteCountryButton);
		controlPanel.add(addContinentButton);
		
		JButton deleteContinentButton = new JButton("Delete Continent");
		deleteContinentButton.addActionListener(new MapEditorController(map));
		
		JButton editContinentButton = new JButton("Edit Continent");
		controlPanel.add(editContinentButton);
		controlPanel.add(deleteContinentButton);
		
		JButton addConnectionButton = new JButton("Add Connection");
		controlPanel.add(addConnectionButton);
		
		JButton deleteConnectionButton = new JButton("Delete Connection");
		controlPanel.add(deleteConnectionButton);
		addContinentButton.addActionListener(new MapEditorController(map));
		
		editContinentButton.addActionListener(new MapEditorController(map));
		
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
		
		this.mapCountryPanel = new MapCountryPanel();
		mapCountryPanel.addCountryTableForMapEditor(map);
        getContentPane().add(mapCountryPanel, BorderLayout.CENTER);
        
        this.mapContinentPanel = new MapContinentPanel();
        mapContinentPanel.addContinentTableForMapEditor(map);
        getContentPane().add(mapContinentPanel, BorderLayout.LINE_END);

	}



	
	@Override
	public void update(Observable map, Object x) {
		
		getContentPane().remove(mapContinentPanel);
		getContentPane().remove(mapContinentPanel);
		
		
		this.mapContinentPanel = new MapContinentPanel();
	    mapContinentPanel.addContinentTableForMapEditor((Map)map);
		this.mapCountryPanel = new MapCountryPanel();
		mapCountryPanel.addCountryTableForMapEditor((Map)map);
		
        getContentPane().add(mapCountryPanel, BorderLayout.CENTER);
        getContentPane().add(mapContinentPanel, BorderLayout.LINE_END);

		
		this.revalidate();
		
		
		
	}	

}
