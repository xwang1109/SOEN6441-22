package views.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.game.FortificationController;
import models.game.Player;
import models.map.Country;
import models.map.GameState;

/**
 * class FortificationView is the view for fortification phase
 * @author Lin Li
 * @see controllers.game.FortificationController
 */
public class FortificationView {
	JComboBox<String> fromDropDown; //declare JComboBox to show a drop-down list
	JComboBox<String> toDropDown; //declare JComboBox to show a drop-down list
	
	/**
	 * Constructor of class FortificationView
	 * @param JPanel controlPanel
	 */
	public FortificationView(JPanel controlPanel) {
		// initialized before handler
		fromDropDown = new JComboBox<String>();
		toDropDown = new JComboBox<String>(); 
		
		JTextArea currentPlayerIndicator = new JTextArea("Current Player: " + GameState.getInstance().getCurrentPlayer().getId());
		
		// text shown to guide user to select from which country the armies should go out
		JTextArea textFrom = new JTextArea("From Country:");
		
		// text shown to guide user to select destination country
		JTextArea textTo = new JTextArea("To Country:");
		
		// declare quantity fields
		JTextArea textQuantity = new JTextArea("Number of Armies:");
		JTextField quantity = new JTextField(5);
		
		
		Player player = GameState.getInstance().getCurrentPlayer(); //get current player
		ArrayList<Country> countryList = player.getCountryList(); //get country list of current player
		
		// create drop-down list for all the countries owned by current player
		for (Country country:countryList) {
			fromDropDown.addItem(country.getName());
		}
		
		fromDropDown.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent event){
                JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
                String selected = (String)comboBox.getSelectedItem();
            	Country selectedCountry = null;
                for(Country c:countryList) {
                	if (c.getName().equals(selected))
                		selectedCountry = c; 
                }
                
                // get valid destinations
                ArrayList<Country> destinationCountryList = GameState.getInstance().getValidDestination(selectedCountry);
                                
                toDropDown.removeAllItems(); // clear the to combobox
                
                // add all possible destinations (belong to current player and there is a path) to the toDropDown JComboBox
                for (Country country:destinationCountryList) {
        			toDropDown.addItem(country.getName());
        		}
                
                // by default show the maximum number of armies that can be moved, but also able to use user input
                if (selectedCountry != null) {
                	quantity.setText(String.valueOf(Math.max(0,selectedCountry.getNumOfArmies()-1)));
                }               	
        	}
		});
			
		// callback to fortification controller
		JButton executeFortification = new JButton("Fortify!");
		executeFortification.addActionListener(new FortificationController(fromDropDown, toDropDown, quantity));
		
		controlPanel.add(currentPlayerIndicator);
		controlPanel.add(textFrom);
		controlPanel.add(fromDropDown);
		controlPanel.add(textTo);
		controlPanel.add(toDropDown);
		controlPanel.add(textQuantity);
		controlPanel.add(quantity);
		controlPanel.add(executeFortification);
	}
}
