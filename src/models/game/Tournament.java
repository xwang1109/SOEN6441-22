package models.game;
import java.util.List;

import models.map.Map;
public class Tournament {

	
	List<Map> maps;
	List<Player> players;
	int numOfGames;
	int turns;
	public List<Map> getMaps() {
		return maps;
	}
	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getNumOfGames() {
		return numOfGames;
	}
	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
	
	public Tournament(List<Map> maps, List<Player> players, int numOfGames, int turns) {
		super();
		this.maps = maps;
		this.players = players;
		this.numOfGames = numOfGames;
		this.turns = turns;
	}
	
	public void run()
	{
		//todo: run the games
		
		report();
	}
	private String report()
	{
		return null;
	}
	
	
}
