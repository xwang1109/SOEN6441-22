package models.map;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import models.game.Army;
import models.game.Player;

/**
 * class GameState to store and pass the current state of the game
 * @author Lin Li
 */
public class GameState extends Observable {

	private File selectedFile;
	private  Map map;
	
	private int currentPlayer;
	private ArrayList<Player> playerList = new ArrayList<Player>();	// hold players

	private ArrayList<Country> destinationCountryList;	// hold phase to switch between map info and current state

	
	
	public enum Phase
	{
		SETUP,
		REINFORCEMENT,
		ATTACK,
		FORTIFICATION,
		FINISHED
	};
	
	public class PhaseState extends Observable {
		private Phase phase = Phase.SETUP;
		
		public Phase getPhase() { 
			return phase; 
		}
		public void setPhase(Phase phase) { 
			this.phase = phase; 
			setChanged();
			notifyObservers();		
		}
	}
	
	private PhaseState phaseState = new PhaseState();
	/**
	 * Public method to return the current phase of game
	 * @return Phase
	 */
	public Phase getPhase() { 
		return phaseState.getPhase(); 
	}
	
	/**
	 * Public method to set the current phase of game as parameter passed
	 * @param phase
	 */
	public void setPhase(Phase phase) { 
		phaseState.setPhase(phase);	
	}

	public void addPhaseObserver(Observer observer) {
		phaseState.addObserver(observer);
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
	 * check if fortification succeed
	 *
	 * @param from the from
	 * @param to the to
	 * @param qt the qt
	 * @return true, if successful
	 */
	public boolean fortify(String from, String to, int qt) {
		return getCurrentPlayer().fortify(from, to, qt);
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
	 * @param selectedFile
	 */
	public void setSelectedFile( File selectedFile ) {
		this.selectedFile = selectedFile;
	}
	
	/**
	 * Public method to get the array list of players
	 * @return ArrayList
	 */
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	/**
	 * Public method to set the array list of players
	 * @param playerList
	 */
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
		this.currentPlayer = 0;
	}
	
	/**
	 * Public method to check if map is loaded from selected file
	 * @param selectedFile2
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
			
		setChanged();
		notifyObservers();
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
	 * place an army in each country
	 */
	public void assignInitialArmy() {		
		for (Player player:this.playerList) {
			for (int i=0; i<getInitialArmyNumber(); i++) {
				Army army = new Army(player);
				player.getArmyList().add(army);				
			}
			for(Country country: player.getCountryList()) {
				country.AddArmy();				
			}		
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Take user selection, set number of players
	 * @param num
	 */
	public void assignInitialPlayers(int num) {
		assert(playerList.size() == 0); // shouldn't be called with an initialized player list, for future debug
		
		playerList.clear(); // clear player list to make sure no previous record is there
		for(int i = 0; i < num; i++) {
			Player p = new Player();
			p.setId(i);
			playerList.add(p);
		}
		setChanged();
		notifyObservers();
		
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
	 * Get current Player
	 * @return Player
	 */
	public void setFirstPlayer() {
		currentPlayer = 0;
	}
	
	/**
	 * End turn for current player and set the "currentPlayer" to next player in setUp Phase
	 */
	public boolean setUpRoundRobin() {
		return ++currentPlayer < playerList.size();
	}
	
	/**
	 * Find all possible destination countries for the parameter country, and store them in an ArrayList
	 * @param selectedCountry
	 * @return ArrayList
	 */
	public ArrayList<Country> getValidDestination(Country selectedCountry) {		
		assert( selectedCountry.getOwner() == playerList.get(currentPlayer) );// defensive programming
		//if no country is selected, or the owner of selected country is not the current player, return an empty ArrayList
		if ( selectedCountry == null || selectedCountry.getOwner() != playerList.get(currentPlayer) ) 
			return new ArrayList<Country>();
		
		// query the map
		return getCurrentPlayer().getValidDestination(selectedCountry);
	}

}
