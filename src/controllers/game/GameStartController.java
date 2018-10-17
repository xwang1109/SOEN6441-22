package controllers.game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.game.Army;
import models.game.Player;
import models.map.Country;
import models.map.GameState;
import views.game.ViewState;

public class GameStartController implements ActionListener {

	private JComboBox numberOfPlayer;

	public GameStartController(JComboBox numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
		File selectedFile = GameState.getInstance().getSelectedFile();
		boolean result = GameState.getInstance().loadMapFromFile(selectedFile);

		if(result) {

		}
		else {
			JOptionPane.showMessageDialog(ViewState.getInstance(), "not a valid map");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		ArrayList<Player> playerList = new ArrayList<Player>();

		int num = (Integer)numberOfPlayer.getSelectedItem();
		for(int i = 0;i < num; i++) {
			Player p = new Player();
			p.setId(i);
			playerList.add(p);
		}
		GameState.getInstance().setPlayerList(playerList);

		List<Country> countryList = GameState.getInstance().getMap().getCountryList();
		Collections.shuffle(countryList);

		for(int i = 0; i < countryList.size(); i++)  
		{
			Country c = countryList.get(i);                // loop for get each country of the map
			Player p = playerList.get(i%num);              // find the corresponding player by the order of the player
			p.getCountryList().add(c);                     // assign country to each player
			c.setOwner(p);
		}

		
//		System.out.println(num);

		
/**
 * Allocate a number of initial armies to players
 */
		int initialArmy = Math.round(countryList.size() / playerList.size()) + playerList.size();
		for (Player player:playerList) {
			for (int i=0; i<initialArmy; i++) {
				Army army = new Army(player);
				player.getArmyList().add(army);				
			}
		}
		
		ViewState.getInstance().showReinforcementView();

		/*if ( true ) { // TODO need a map... GameState.getInstance().isMapLoaded() ) {
			// Do something about players
			ViewState.getInstance().showReinforcementView();
		} else {
			// TODO do a popup
			System.out.println("Issue with parsing the map");
		}*/
	}




}
