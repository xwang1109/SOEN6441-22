package models.map;

import java.io.File;
import java.util.ArrayList;

import models.game.Player;
import views.game.ViewState;

public class GameState {

	private File selectedFile;
	private Map map;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	// hold players
	// hold phase to switch between map info and current state
	enum Phase
	{
		SETUP,
		REINFORCEMENT,
		ATTACK,
		FORTIFY,
		FINISHED
	};
	
	private Phase phase = Phase.SETUP;
	
	public Phase getPhase() { return phase; }
	public void setPhase(Phase phase) { this.phase = phase; }
	
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
	
	public boolean fortify(String from, String to, int qt) {
		return map.fortify(from, to, qt);
	}
	
	public File getSelectedFile() {
		return this.selectedFile;
	}
	
	public void setSelectedFile( File selectedFile ) {
		this.selectedFile = selectedFile;
	}
	
	
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	public boolean loadMapFromFile(File selectedFile2) {
		return map.loadMapFromFile(selectedFile2);
	}
	
	public Map getMap() {
		return map;
	}
}
