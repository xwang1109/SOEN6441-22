package views.game;

import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import controllers.map.MapEditorStartController;
import models.game.Player;
import models.map.Country;
import javax.swing.JTextPane;

public class StartUpView {
	private ArrayList<Player> playerList;
	private ArrayList<Country> countryList;
	private Country clickedCountry;
	public Country getClickedCountry() {
		return clickedCountry;
	}

	public StartUpView (JPanel controlPanel,File selectedFile,JFrame frame, List<Player> playerList, List<Country> countryList) {
		this.playerList = playerList;
		this.countryList = countryList;
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JLabel playerLabel = new JLabel("");
		
		controlPanel.add(playerLabel);
		

		for (Player player:playerList) {
			playerLabel.setText(Integer.toString(player.getId()));
			int armyNumber = player.getArmyList().size();

			while (armyNumber>0) {
			//click event
			//armyNumber--;
			//clickedCountry set
			}
		}
		
		ViewState.getInstance().showReinforcementView(selectedFile, playerList, countryList);
		
	}

}
