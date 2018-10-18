package views.game;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import models.map.GameState;
import views.map.MapCountryPanel;

import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
/**
 * Class PlayerSetupView is the view for player to start up by selecting number of players
 * @author 
 * @see GameState.getInstance().getMap(), 
 */
public class PlayerSetupView {

	public PlayerSetupView(JPanel controlPanel,ViewState frame) {
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		//Prompt user to select number of players
		JTextArea selectNoOfPlayers = new JTextArea("Select Number of Players:");
				
		controlPanel.add(selectNoOfPlayers);
		
		//set the drop-down box for selecting number of players, and set the options to be integer between 2 and 5
		JComboBox numberOfPlayer = new JComboBox();
		for (int i = 2; i < 6; i++) {
			numberOfPlayer.addItem(i);
		}
		
		JButton newGameButton = new JButton("START THE GAME");
		
		//receive user clicking ""START THE GAME" by GameStartController
		newGameButton.addActionListener(new GameStartController(numberOfPlayer));
		controlPanel.add(numberOfPlayer);
		controlPanel.add(newGameButton);
		
		MapCountryPanel mapPanel = frame.getMapPanel();
		
		mapPanel.addCountryTableForMap(GameState.getInstance().getMap());	
	}
}
