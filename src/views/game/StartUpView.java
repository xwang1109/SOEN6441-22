package views.game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import controllers.game.PlayerSetupController;
import controllers.game.StarUpController;
import controllers.map.MapEditorStartController;
import models.game.Player;
import models.map.Country;
import models.map.GameState;
import views.map.MapCountryPanel;

import javax.swing.JTextPane;

public class StartUpView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Player> playerList;
	private ArrayList<Country> countryList;
	private Player player;
	private int playerCounter;
	private GameState gameState;
	private int leftArmies;
	private Country selectedCountry;
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Country getSelectedCountry() {
		return selectedCountry;
	}
	public void setSelectedCountry(Country country) {
		this.selectedCountry = country;
	}
	public int getLeftArmies() {
		return leftArmies;
	}
	public int decreaseLeftArmies() {
		leftArmies--;
		return leftArmies;
	}
	public void setLeftArmies(int leftArmies) {
		this.leftArmies = leftArmies;
	}
	public int getPlayerCounter() {
		return playerCounter;
	}
	public void setPlayerCounter(int counter) {
		playerCounter = counter;
	}

	MapCountryPanel mapPanel;
	JButton addArmyButton;
	JComboBox<String> comboBox;
	JLabel playerLabel;
	JLabel leftArmyLabel;
	public StartUpView (JPanel controlPanel) {
		gameState = GameState.getInstance();
		playerList = gameState.getPlayerList();
		countryList = gameState.getMap().getCountryList();
		
		mapPanel = new MapCountryPanel();
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		addArmyButton = new JButton("Add Army");
		addArmyButton.addActionListener(new StarUpController(this));

		comboBox = new JComboBox<String>();
		comboBox.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                String selected = (String)comboBox.getSelectedItem();
                for(Country c:countryList) {
                	if (c.getName().equals(selected))
                		selectedCountry = c;
                }
            }
		});
				
		playerLabel = new JLabel("");
		leftArmyLabel = new JLabel("");
		
		controlPanel.add(playerLabel);
		controlPanel.add(leftArmyLabel);
		controlPanel.add(addArmyButton);
		
		this.add(controlPanel);
		this.add(mapPanel);		

		playerCounter = 0;
		leftArmies = player.getArmyNumber();
		showPlayer();
	}
	
	public void showPlayer() {
		player = playerList.get(playerCounter);
		countryList = player.getCountryList();
		playerLabel.setText(Integer.toString(player.getId()));
		mapPanel.addCountryTableForReinforcement(player);
		leftArmyLabel.setText(Integer.toString(leftArmies));

		for (Country country:countryList) {
			comboBox.addItem(country.toString());
		}
		
	}

}
