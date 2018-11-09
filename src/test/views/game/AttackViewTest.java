package test.views.game;

import java.util.ArrayList;

import javax.swing.JComboBox;

import org.junit.Before;
import org.junit.Ignore;

import models.game.GameState;
import models.game.Player;
import models.map.Country;
import views.game.AttackView;

/**
 * This class tests attack view of the game
 * @author Lin Li
 *
 */

public class AttackViewTest {
	
	Player player0 = new Player();
	Player player1 = new Player();
	Country attackerCountry = new Country("attackerCountryStr");
	Country defenderCountry = new Country("defenderCountryStr");
	AttackView av = new AttackView(null);
	
	/**
	 * Set up before test
	 */
	@Before
	public void setUp(){
		GameState.reset();
		
		GameState.getInstance().getPlayerList().add(player0);
		GameState.getInstance().getPlayerList().add(player1);
		
		attackerCountry.setOwner(player0);
		defenderCountry.setOwner(player1);
		
		player0.getCountryList().add(attackerCountry);
		player1.getCountryList().add(defenderCountry);
		
		for (int i = 0; i<10; i++){
			attackerCountry.increaseArmy();
			defenderCountry.increaseArmy();
		}
		
		attackerCountry.addAdjacentCountry(defenderCountry);
		
		ArrayList<Country> countryList = new ArrayList<Country>();
		countryList.add(attackerCountry);
		countryList.add(defenderCountry);
		GameState.getInstance().getMap().setCountryList(countryList);
		
		attackerCountry.setName("attackerCountryStr");
		defenderCountry.setName("defenderCountryStr");
		
	}
	

	/**
	 * test for attack of attacker validation
	 */
	@Ignore
	public void testAttackerValidation(){
		
		
	}
	
	/**
	 * test for attack of defender validation
	 */
	@Ignore
	public void testDefenderValidation(){
		
	}

}
