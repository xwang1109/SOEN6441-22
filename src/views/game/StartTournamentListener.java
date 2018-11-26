package views.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import models.game.Player;
import models.game.Tournament;
import models.map.Map;

public class StartTournamentListener implements ActionListener {
	
	private List<Map> maps;
	private List<Player> players;
	private int numOfGames;
	private int turns;
	private int numberOfMaps;
	
	public StartTournamentListener(List<Map> maps, List<Player> players, int numOfGames,
			int turns, int numberOfMaps) {
		this.maps=maps;
		this.players=players;
		this.numOfGames=numOfGames;
		this.turns=turns;
		this.numberOfMaps=numberOfMaps;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(numberOfMaps!=maps.size()){
			//todo popup error
			System.out.println("missing maps");
		}
		else{
			Tournament tournament=new Tournament(maps, players, numOfGames, turns);
			tournament.run();
		}
	}

}
