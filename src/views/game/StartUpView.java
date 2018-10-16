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
import controllers.game.StartUpController;
import controllers.map.MapEditorStartController;
import models.game.Player;
import models.game.Card;
import models.map.Country;
import models.map.GameState;
import views.map.MapCountryPanel;

import javax.swing.JTextPane;


public class StartUpView{
	private ArrayList<Player> playerList;
	private ArrayList<Country> countryList;
	private Player player;
	private int playerCounter;
	private GameState gameState;
	private int leftArmies;
	private Country selectedCountry;
	private boolean isActionListenerActive;
	
	JPanel thisPanel;
	MapCountryPanel mapPanel;
	JButton addArmyButton;
	JButton changeCardButton;
	JComboBox<String> comboBox;
	JLabel playerLabel;
	JLabel leftArmyLabel;
	JLabel cardNumberLabel;
	JLabel phaseLabel;
	
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
	
	public StartUpView (JPanel controlPanel) {
		gameState = GameState.getInstance();
		playerList = gameState.getPlayerList();
		mapPanel = new MapCountryPanel();
		thisPanel = controlPanel;
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		addArmyButton = new JButton("Add Army");
		addArmyButton.addActionListener(new StartUpController(this));

		changeCardButton = new JButton("Change Card");
		changeCardButton.addActionListener(new StartUpController(this));


		comboBox = new JComboBox<String>();
		comboBox.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(isActionListenerActive) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    String selected = (String)comboBox.getSelectedItem();
                    for(Country c:countryList) {
                    	if (c.getName().equals(selected))
                    		selectedCountry = c; 
                    }           		
            	}
            }
		});
				
		JLabel playerLabelTitle = new JLabel("Player ID"); 
		JLabel leftArmyLabelTitle = new JLabel("Left Armies");
		JLabel cardNumberLabelTitle = new JLabel("Number Of Cards");		
		
		playerLabel = new JLabel("");
		leftArmyLabel = new JLabel("");
		phaseLabel = new JLabel("");
		cardNumberLabel = new JLabel("");
		
		controlPanel.add(phaseLabel);		
		controlPanel.add(playerLabelTitle);
		controlPanel.add(playerLabel);
		controlPanel.add(leftArmyLabelTitle);
		controlPanel.add(leftArmyLabel);
		controlPanel.add(cardNumberLabelTitle);
		controlPanel.add(cardNumberLabel);
		controlPanel.add(comboBox);
		controlPanel.add(addArmyButton);
		controlPanel.add(mapPanel);
		
		///////test change cards///////
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//////////////////////////////////////
		
		playerCounter = 0;
		player = playerList.get(playerCounter);		
		leftArmies = player.getArmyNumber();
		showPlayer();
	}
	
	public void showLeftArmies() {
		leftArmyLabel.setText(Integer.toString(leftArmies));
		mapPanel.addCountryTableForReinforcement(player);
	}
	public void changeToReinforcement() {
		if (player.enforceExchangeCard()) {
			leftArmies += player.addArmyForCard();
			//set label
		} else if (player.isPossibleExchangeCard()) {
			thisPanel.add(changeCardButton);				
		}
		updateLabels();
	}
	public void changeToAttack() {
		updateLabels();
	}
	public void updateLabels() {
		phaseLabel.setText(GameState.getInstance().getPhase().toString());
		leftArmyLabel.setText(Integer.toString(leftArmies));
		cardNumberLabel.setText(Integer.toString(player.getCardList().size()));
	}
	public void showPlayer() {
		player = playerList.get(playerCounter);
		countryList = player.getCountryList();
		playerLabel.setText(Integer.toString(player.getId()));
		mapPanel.addCountryTableForReinforcement(player);
		leftArmyLabel.setText(Integer.toString(leftArmies));
		cardNumberLabel.setText(Integer.toString(player.getCardList().size()));
		
		isActionListenerActive = false;
		comboBox.removeAllItems();
		for (Country country:countryList) {
			comboBox.addItem(country.getName());
		}
		comboBox.revalidate();
		comboBox.repaint();
		isActionListenerActive = true;
		comboBox.setSelectedIndex(0);
	}

}
