package models.game;

/**
 * interface Strategy implements the different player behaviors using the Strategy pattern
 * @author Mehrnaz
*/
public interface Strategy {

	
	/**
	 * Reinforcement phase should be implemented in different behaviors
	 * @param player current player who is in reinforcement phase
	 */
	public void reinforcementPhase(Player player);
	
	
	/**
	 * Attack phase should be implemented in different behaviors
	 * @param player current player who is in attack phase
	 * @return 
	 */
	public int attackPhase(Player player);
	
	
	/**
	 * Fortification phase should be implemented in different behaviors
	 * @param player current player who is in fortification phase
	 */
	public void fortificationPhase(Player player);
	
}