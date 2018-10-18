package models.map;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import models.game.Army;
import models.game.Player;
import views.game.ViewState;

/**
 * class GameState to store and pass the current state of the game
 * @author Lin Li
 * @see models.map.loadMapFromFile
 */
public class GameState {

	private File selectedFile;
	private  Map map;
	
	private int currentPlayer;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Country> destinationCountryList;
	// hold players
	
	// hold phase to switch between map info and current state
	public enum Phase
	{
		SETUP,
		REINFORCEMENT,
		ATTACK,
		FORTIFY,
		FINISHED
	};
	
	private Phase phase = Phase.SETUP;
	
	/**
	 * Public method to return the current phase of game
	 * @return Phase
	 */
	public Phase getPhase() { 
		return phase; 
	}
	
	/**
	 * Public method to set the current phase of game as parameter passed
	 * @param Phase phase
	 */
	public void setPhase(Phase phase) { 
		this.phase = phase; 
	}
	
	/**
	 * Constructor of class GameState
	 */
	private GameState() {
		this.map = new Map();
	}
	
	
	static private GameState instance = new GameState();
	
	/**
	 * Static public method to get the instance of GameState
	 * @return GameState
	 */
	static public GameState getInstance() {
		return instance;
	}
	
	/**
	 * Public method to check if map is loaded
	 * @return boolean
	 */
	public boolean isMapLoaded() {
		return map.isLoaded();
	}	
	
	/**
	 * Public method to check if fortification succeed
	 * @param String from, String to, int qt
	 * @return boolean
	 */
	public boolean fortify(String from, String to, int qt) {
		return map.fortify(from, to, qt);
	}
	
	/**
	 * Public method to check if file is selected
	 * @return File
	 */
	public File getSelectedFile() {
		return this.selectedFile;
	}
	
	/**
	 * Public method to set the file to be selected
	 * @param File selectedFile
	 */
	public void setSelectedFile( File selectedFile ) {
		this.selectedFile = selectedFile;
	}
	
	/**
	 * Public method to get the array list of players
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	/**
	 * Public method to set the array list of players
	 * @param ArrayList<Player> playerList
	 */
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
		this.currentPlayer = 0;
	}
	
	/**
	 * Public method to check if map is loaded from selected file
	 * @param File selectedFile2
	 * @return boolean
	 */
	public boolean loadMapFromFile(File selectedFile2) {
		return map.loadMapFromFile(selectedFile2);
	}
	
	/**
	 * Public method to get the map
	 * @return Map
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * Randomly assign countries to players
	 */
	public void randomAssignCountry() {
		int player_index=0;
		
		List<Continent> countinentList = this.map.getContinentList();
		
		for(Continent continent: countinentList)//this is try to avoid one play takes over an entire continent in the first round
		{
			
			List<Country> countryList = continent.getCountryList();  //get all countries from each continent
			
			Collections.shuffle(countryList);

			for(int i = 0; i < countryList.size(); i++)  
			{
				Country c = countryList.get(i);                // loop for get each country of the map
				Player p = this.playerList.get(player_index);  // find the corresponding player by the order of the player
				p.getCountryList().add(c);                     // assign country to each player
				c.setOwner(p);
				
				if(player_index<this.playerList.size()-1)      //if not all players get a new country in this round
				{
					player_index++;
				}
				else                                           //if all players get a new counter in this round, start from player 1
				{
					player_index=0;
				}
				
			}
			
		}
	}
	
	/**
	 * Public method to get initial army number
	 * @return initial number of armies
	 */
	public int getInitialArmyNumber(){
		return Math.round(this.map.getCountryList().size() / this.playerList.size()) + this.playerList.size();
	}
	
	/**
	 * Allocate a number of initial armies to players
	 */
	public void assignInitialArmy() {		
		for (Player player:this.playerList) {
			for (int i=0; i<getInitialArmyNumber(); i++) {
				Army army = new Army(player);
				player.getArmyList().add(army);				
			}
			// place one army in each country
			for(Country coutnry: player.getCountryList()) {
				coutnry.AddArmy();				
			}
		}
	}

	/**
	 * Take user selection, set number of players
	 * @param int num
	 */
	public void assignInitialPlayers(int num) {
		assert(playerList.size() == 0); // shouldn't be called with an initialized player list, for future debug
		
		playerList.clear(); // clear player list to make sure no previous record is there
		for(int i = 0; i < num; i++) {
			Player p = new Player();
			p.setId(i);
			playerList.add(p);
		}
		
		// TODO Determine first player (0 can be good)
		currentPlayer = 0;
	}

	/**
	 * Get current Player
	 * @return Player
	 */
	public Player getCurrentPlayer() {
		return playerList.get(currentPlayer);
	}

	/**
	 * End turn for current player, and set the "currentPlayer" to next player
	 */
	public void endPlayerTurn() {
		currentPlayer = ++currentPlayer % playerList.size();
	}

	/**
	 * Find all possible destination countries for the parameter country, and store them in an ArrayList
	 * @param Country selectedCountry
	 * @return ArrayList<Country>
	 */
	public ArrayList<Country> getValidDestination(Country selectedCountry) {		
		assert( selectedCountry.getOwner() == playerList.get(currentPlayer) );// defensive programming
		//if no country is selected, or the owner of selected country is not the current player, return an empty ArrayList
		if ( selectedCountry == null || selectedCountry.getOwner() != playerList.get(currentPlayer) ) 
			return new ArrayList<Country>();
		
		// query the map
		return map.getValidDestination(selectedCountry);
	}
}
