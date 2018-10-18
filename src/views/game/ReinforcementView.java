package views.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JFrame;

import controllers.game.GameStartController;
import controllers.game.PlayerSetupController;
import controllers.game.ReinforcementController;
import controllers.map.MapEditorStartController;
import models.game.Player;
import models.game.Card;
import models.map.Country;
import models.map.GameState;
import views.map.MapCountryPanel;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * Class ReinforcementView is the view for a part of setup phase and the reinforcement phase
 * in this view players place their given armies one by one on their own countries
 * then reinforcement phase begins
 * @author Mehrnaz
 * @see controllers.game.ReinforcementController
 */
public class ReinforcementView{
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
	JButton finishAttackButton;
	JComboBox<String> comboBox;
	JLabel playerLabel;
	JLabel leftArmyLabel;
	JLabel cardNumberLabel;
	JLabel phaseLabel;
	JLabel messageLable;
	JLabel leftArmyLabelTitle;
	JLabel cardNumberLabelTitle;
	JPanel buttonPane;

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
	
/**
 * The constructor of the class which adds the components to the controlPanel
 * and initials the first player for placing his given armies on his countries
 * @param JPanel controlPanel
 */
	public ReinforcementView (JPanel controlPanel) {
		gameState = GameState.getInstance();
		playerList = gameState.getPlayerList();
		mapPanel = new MapCountryPanel();
		thisPanel = controlPanel;
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		addArmyButton = new JButton("Add Army");
		addArmyButton.addActionListener(new ReinforcementController(this));
		changeCardButton = new JButton("Exchange Card");
		changeCardButton.addActionListener(new ReinforcementController(this));
		finishAttackButton = new JButton("Finish Attack");
		finishAttackButton.addActionListener(new ReinforcementController(this));
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

		JPanel labelPane = new JPanel(new GridLayout(0,1));
		JLabel phaseLabelTitle = new JLabel("Phase:"); 
		phaseLabelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel playerLabelTitle = new JLabel("Player ID:"); 
		playerLabelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		leftArmyLabelTitle = new JLabel("Left Armies:");
		leftArmyLabelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		cardNumberLabelTitle = new JLabel("Number Of Cards:");
		cardNumberLabelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		labelPane.add(phaseLabelTitle);
		labelPane.add(playerLabelTitle);
		labelPane.add(leftArmyLabelTitle);
		labelPane.add(cardNumberLabelTitle);
		
		JPanel labelValuePane = new JPanel(new GridLayout(0,1));
		playerLabel = new JLabel("");
		leftArmyLabel = new JLabel("");
		phaseLabel = new JLabel("");
		cardNumberLabel = new JLabel("");
		labelValuePane.add(phaseLabel);		
		labelValuePane.add(playerLabel);
		labelValuePane.add(leftArmyLabel);
		labelValuePane.add(cardNumberLabel);

		JPanel mapPane = new JPanel(new GridLayout(0,1));
		mapPane.add(mapPanel);
		
		buttonPane = new JPanel();
		messageLable = new JLabel("");
		buttonPane.add(messageLable);
		buttonPane.add(comboBox);
		buttonPane.add(addArmyButton);

		controlPanel.add(labelPane);
		controlPanel.add(labelValuePane);
		controlPanel.add(buttonPane);
		mapPane.setSize(200,200);
		controlPanel.add(mapPane);
		
		///////test change cards///////
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//playerList.get(0).getCardList().add(new Card(playerList.get(0)));
		//////////////////////////////////////
		
		playerCounter = 0;
		player = playerList.get(playerCounter);		
//		leftArmies = player.getArmyNumber();
		leftArmies = player.getLeftArmyNumber();
		showPlayer();
	}
/**
 * Refresh the number of armies left to assign to countries
 */
	public void showLeftArmies() {
		leftArmyLabel.setText(Integer.toString(leftArmies));
		mapPanel.addCountryTableForReinforcement(player);
	}
/**
 * Make changes on the view of panel when the game phase changes to reinforcement
 * also checks that if player owns 5 cards, exchanges them for armies 
 */
	public void changeToReinforcement() {
		if (player.enforceExchangeCard()) {
			leftArmies += player.addArmyForCard();
			messageLable.setText("Your cards have exchanged with armies");
		} else if (player.isPossibleExchangeCard()) {
			buttonPane.add(changeCardButton);				
		}
		updateLabels();
	}
/**
 * Make changes on the view of panel when the game phase changes to attack
 */
	public void changeToAttack() {
		buttonPane.add(finishAttackButton);
		addArmyButton.setVisible(false);
		leftArmyLabelTitle.setVisible(false);;
		cardNumberLabelTitle.setVisible(false);
		leftArmyLabel.setVisible(false);;
		cardNumberLabel.setVisible(false);
		comboBox.setVisible(false);
		updateLabels();
	}
/**
 * Disable exchange card button when player exchanged cards for armies
 */
	public void changedCard() {
		changeCardButton.setVisible(false);;				
		updateLabels();
	}
/**
 * Update values of labels 
 */
	public void updateLabels() {
		playerLabel.setText(Integer.toString(player.getId()));
		leftArmyLabel.setText(Integer.toString(leftArmies));
		cardNumberLabel.setText(Integer.toString(player.getCardList().size()));
		phaseLabel.setText(GameState.getInstance().getPhase().toString());
		leftArmyLabel.setText(Integer.toString(leftArmies));
		cardNumberLabel.setText(Integer.toString(player.getCardList().size()));
	}
/**
 * This method is called in setup phase when next player is given the term
 * to place his given armies one by one on his countries
 */
	public void showPlayer() {
		player = playerList.get(playerCounter);
		countryList = player.getCountryList();
		mapPanel.addCountryTableForReinforcement(player);
		updateLabels();
		
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
