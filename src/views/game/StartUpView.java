package views.game;

import java.awt.FlowLayout;
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

import javax.swing.JTextPane;

public class StartUpView {
	private ArrayList<Player> playerList;
	private List<Country> countryList;
	private Player player;
	private GameState gameState;
	private int leftArmies;
	public int getLeftArmies() {
		return leftArmies;
	}
	private Country clickedCountry;
	public Country getClickedCountry() {
		return clickedCountry;
	}

	public StartUpView (JPanel controlPanel) {
		gameState = GameState.getInstance();
		playerList = gameState.getPlayerList();
		countryList = gameState.getMap().getCountryList();
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new StarUpController(this));

		JLabel playerLabel = new JLabel("");
		JLabel leftArmyLabel = new JLabel("");
		
		controlPanel.add(playerLabel);
		controlPanel.add(leftArmyLabel);
		controlPanel.add(newGameButton);
		

		player = playerList.get(0);
		playerLabel.setText(Integer.toString(player.getId()));
		countryList = player.getCountryList();

		JComboBox<String> playerCountryList = new JComboBox<String>();
		for (Country country:countryList) {
			playerCountryList.addItem(country.toString());
		}
		
		leftArmies = player.getArmyNumber();
		leftArmyLabel.setText(Integer.toString(leftArmies));

		
		ViewState.getInstance().showReinforcementView(selectedFile, playerList, countryList);
		
	}

}
