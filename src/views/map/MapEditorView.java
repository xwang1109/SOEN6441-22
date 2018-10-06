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
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MapEditorView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Map map;
	
	
	
	public MapEditorView() {
		
		this.setSize(617,423);
		getContentPane().setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JButton addCountryButton = new JButton("Add Country");
		addCountryButton.addActionListener(new MapEditorController(map));
		
		
		JButton addContinentButton = new JButton("Add Continent");
		panel.add(addCountryButton);
		panel.add(addContinentButton);
		
		JButton deleteContinentButton = new JButton("Delete Continent");
		deleteContinentButton.addActionListener(new MapEditorController(map));
		
		JButton deleteCountryButton = new JButton("Delete Country");
		deleteCountryButton.addActionListener(new MapEditorController(map));
		panel.add(deleteCountryButton);
		panel.add(deleteContinentButton);
		addContinentButton.addActionListener(new MapEditorController(map));
		
		
		
	}	
}
