package models.map;

import views.game.ViewState;

public class GameState {

	private Map map;
	
	// hold players
	// hold phase to switch between map info and current state
	
	private GameState() {
		this.map = new Map();
	}
	
	
	static private GameState instance = new GameState();
	
	static public GameState getInstance() {
		return instance;
	}
	public boolean isMapLoaded() {
		return map.isLoaded();
	}	
	public int getInitialArmyNumber(){
		assert(false);
		//Need to be fixed
		return map.getCountryList().size();
	}
}
