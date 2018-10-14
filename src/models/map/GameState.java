package models.map;

import java.io.File;

import views.game.ViewState;

public class GameState {

	private File selectedFile;
	private Map map;
	
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
	
	public boolean fortify(String from, String to, int qt) {
		return map.fortify(from, to, qt);
	}
	
	public File getSelectedFile() {
		return this.selectedFile;
	}
	
	public void setSelectedFile( File selectedFile ) {
		this.selectedFile = selectedFile;
	}
	
	public boolean loadMapFromFile(File selectedFile2) {
		return map.loadMapFromFile(selectedFile2);
	}
	
	public Map getMap() {
		return map;
	}
}
