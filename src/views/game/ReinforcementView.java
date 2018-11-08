package views.game;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import models.game.Card.CardType;
import models.game.GameState.Phase;
import models.game.Card;
import models.game.GameState;
import models.map.Country;
import views.map.MapCountryPanel;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

/**
 * Class ReinforcementView is the view for a part of setup phase and the reinforcement phase
 * in this view players place their given armies one by one on their own countries
 * then reinforcement phase begins
 * @author Mehrnaz, Bingyang
 * @see controllers.game.ReinforcementController
 * @version 2.0
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
	JButton finishAttackButton;
	JComboBox<String> comboBox;
	JLabel playerLabel;
	JLabel leftArmyLabel;
	
	
	JLabel infantrycardNumberLabel;
	JLabel cavalrycardNumberLabel;
	JLabel artillerycardNumberLabel;

	
	
	JLabel messageLable;
	JLabel leftArmyLabelTitle;
	JLabel infantrycardNumberLabelTitle;	
	JLabel cavalrycardNumberLabelTitle;
	JLabel artillerycardNumberLabelTitle;

	JPanel buttonPane;
	private JButton exchangeCardViewButton;

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
 * @param controlPanel  to control panel
 * 
 */
	public ReinforcementView  (JPanel controlPanel) {
		
		gameState = GameState.getInstance();
		playerList = gameState.getPlayerList();
		mapPanel = new MapCountryPanel();
		thisPanel = controlPanel;
		
	    FlowLayout fl_controlPanel = new FlowLayout();
	    controlPanel.setLayout(fl_controlPanel);
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		addArmyButton = new JButton("Add Army");
		addArmyButton.addActionListener(new ReinforcementController(this));

		exchangeCardViewButton = new JButton("Open exchange card view");
		exchangeCardViewButton.addActionListener(new ReinforcementController(this));
		exchangeCardViewButton.setVisible(false);
		
		
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
		
		
		
		infantrycardNumberLabelTitle = new JLabel("Number Of Infantry Cards:");
		infantrycardNumberLabelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		

		cavalrycardNumberLabelTitle = new JLabel("Number Of Cavary Cards:");
		cavalrycardNumberLabelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		

		artillerycardNumberLabelTitle = new JLabel("Number Of Artilery Cards:");
		artillerycardNumberLabelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		
		
		labelPane.add(phaseLabelTitle);
		labelPane.add(playerLabelTitle);
		labelPane.add(leftArmyLabelTitle);
		labelPane.add(infantrycardNumberLabelTitle);
		labelPane.add(cavalrycardNumberLabelTitle);
		labelPane.add(artillerycardNumberLabelTitle);

		
		JPanel labelValuePane = new JPanel(new GridLayout(0,1));
		playerLabel = new JLabel("");
		leftArmyLabel = new JLabel("");
		infantrycardNumberLabel = new JLabel("");
		cavalrycardNumberLabel = new JLabel("");
		artillerycardNumberLabel = new JLabel("");

		labelValuePane.add(StateView.getInstance().getPhaseLabel());		
		labelValuePane.add(playerLabel);
		labelValuePane.add(leftArmyLabel);
		labelValuePane.add(infantrycardNumberLabel);
		labelValuePane.add(cavalrycardNumberLabel);
		labelValuePane.add(artillerycardNumberLabel);


		JPanel mapPane = new JPanel(new GridLayout(0,1));
		mapPane.add(mapPanel);
		
		buttonPane = new JPanel();
		messageLable = new JLabel("");
		buttonPane.add(messageLable);
		buttonPane.add(comboBox);
		buttonPane.add(addArmyButton);
		buttonPane.add(exchangeCardViewButton);
		
		controlPanel.add(labelPane);
		controlPanel.add(labelValuePane);
		controlPanel.add(buttonPane);
		mapPane.setSize(200,200);
		controlPanel.add(mapPane);
		
		///////test change cards///////
		/*playerList.get(0).getNewCard();
		playerList.get(0).getNewCard();
		playerList.get(0).getNewCard();
		playerList.get(0).getNewCard();
		playerList.get(0).getNewCard();*/

		//////////////////////////////////////
		
		if (GameState.getInstance().getPhase().equals(Phase.SETUP)){
			playerCounter = 0;
			player = playerList.get(playerCounter);		

			leftArmies = player.getLeftArmyNumber();		
		}
		
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
	public void exchangeCard() {
		CardExchangeView ccv=new CardExchangeView(player,this);
		ccv.setVisible(true);
		
		
		updateLabels();
	}
/**
 * Make changes on the view of panel when the game phase changes to attack
 */
	public void changeToAttack() {
		buttonPane.add(finishAttackButton);
		addArmyButton.setVisible(false);
		leftArmyLabelTitle.setVisible(false);;
		infantrycardNumberLabelTitle.setVisible(false);
		artillerycardNumberLabelTitle.setVisible(false);
		cavalrycardNumberLabelTitle.setVisible(false);

		leftArmyLabel.setVisible(false);;
		infantrycardNumberLabel.setVisible(false);
		cavalrycardNumberLabel.setVisible(false);
		artillerycardNumberLabel.setVisible(false);

		comboBox.setVisible(false);
		updateLabels();
	}

/**
 * Update values of labels 
 */
	public void updateLabels() {
		playerLabel.setText(Integer.toString(player.getId()));
		
		leftArmies=player.getLeftArmyNumber();
		leftArmyLabel.setText(Integer.toString(leftArmies));
		if(GameState.getInstance().getPhase().equals(GameState.Phase.REINFORCEMENT))
		{
			this.exchangeCardViewButton.setVisible(true);
		}
		else
		{
			this.exchangeCardViewButton.setVisible(false);

		}
		
		
		
		infantrycardNumberLabel.setText(Integer.toString(player.cardTypeNumber()[0]));
		cavalrycardNumberLabel.setText(Integer.toString(player.cardTypeNumber()[1]));
		artillerycardNumberLabel.setText(Integer.toString(player.cardTypeNumber()[2]));
		
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
	public void disable()
	{
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(thisPanel);

		thisPanel.setVisible(false);
		mapPanel.setVisible(false);
		topFrame.setVisible(false);
	}
	public void enable()
	{
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(thisPanel);

		thisPanel.setVisible(true);
		mapPanel.setVisible(true);
		topFrame.setVisible(true);

	}

}
