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

	JComboBox<String> fromDropDown;
	JComboBox<String> toDropDown;
	/**
	 * Constructor of class FortificationView
	 * @param JPanel controlPanel
	 */
	public FortificationView(JPanel controlPanel) {
		// List all country for current player
		JTextArea textFrom = new JTextArea("From Country:");
		
		fromDropDown = new JComboBox<String>();
		toDropDown = new JComboBox<String>(); // initialized before handler
		
		// feed values
		Player player = GameState.getInstance().getCurrentPlayer();
		ArrayList<Country> countryList = player.getCountryList();
		for (Country country:countryList) {
			fromDropDown.addItem(country.getName());
		}
		
		fromDropDown.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent event){
                JComboBox comboBox = (JComboBox) event.getSource();
                String selected = (String)comboBox.getSelectedItem();
            	Country selectedCountry;
                for(Country c:countryList) {
                	if (c.getName().equals(selected))
                		selectedCountry = c; 
                }
                // get valid destinations
                // GameState.getInstance().getValidDestination(selectedCountry);
                // clear the to combobox
                // feed the toComboBox
        	}
		});
		// List all valid destination for current player
		JTextArea textTo = new JTextArea("To Country:");
		
		// number
		JTextArea textQuantity = new JTextArea("Number of Armies:");
		JTextField quantity = new JTextField(5);
		// callback to fortification controller
		JButton executeFortification = new JButton("Fortify!");
		executeFortification.addActionListener(new FortificationController(fromDropDown, toDropDown, quantity));
		
		controlPanel.add(textFrom);
		controlPanel.add(fromDropDown);
		controlPanel.add(textTo);
		controlPanel.add(toDropDown);
		controlPanel.add(textQuantity);
		controlPanel.add(quantity);
		controlPanel.add(executeFortification);
	}
}
