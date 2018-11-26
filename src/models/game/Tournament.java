package models.game;
import java.util.List;

import models.game.GameState.Phase;
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
		for(Map map:maps)
		{
			for(int i=0;i<this.numOfGames;i++)
			{
				GameState.getInstance().setPlayerList(players);  
				GameState.getInstance().setMap(map);
				
				GameState.getInstance().randomAssignCountry();
				GameState.getInstance().assignInitialArmy();
				
				for(Player p:players){
					GameState.getInstance().setPhase(Phase.SETUP);

					p.doStrategySetup();
				}
				//todo check who wins
				for(int currentTurn=0;currentTurn<turns;currentTurn++)
				{
					for(Player p:players){
						GameState.getInstance().setPhase(Phase.REINFORCEMENT);
						p.doStrategyReinforcement();
						GameState.getInstance().setPhase(Phase.ATTACK);
						p.doStrategyAttack();
						GameState.getInstance().setPhase(Phase.FORTIFICATION);
						p.doStrategyfortification();
						GameState.getInstance().endPlayerTurn();
					}
				}
			}
		}

		report();
	}
	private String report()
	{
		//todo
		return null;
	}
	
	
}
