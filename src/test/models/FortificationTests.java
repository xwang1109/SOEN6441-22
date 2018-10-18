package test.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import models.game.Army;
import models.game.Card;
import models.game.Player;
import models.game.Card.CardType;
import models.map.Continent;
import models.map.Country;
import models.map.GameState;

public class FortificationTests {	
	Player player1 = new Player();
	Player player2 = new Player();
	
	/**
	 * Initialization method
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		//player1 owns 10 countries 
		//player1 owns a continent with control value 4
		ArrayList<Continent> continentList = new ArrayList<Continent>();
		for(int i=0; i<5; i++) {
			continentList.add(new Continent("continent" + i, i));
		}
		continentList.get(4).setOwner(player1);
		GameState.getInstance().getMap().setContinentList(continentList);
		
		for(int i=0; i<10; i++) {
			player1.getCountryList().add(new Country("country" + i));
		}		

		//Assign 10 armies to player 1
		for (int i=0; i<10; i++) {
			Army army = new Army(player1);
			player1.getArmyList().add(army);				
		}
		
		//player2 owns 1 country
		//player2 owns no continent
		player2.getCountryList().add(new Country("country10"));
		
		//Assign 10 armies to player 2
		for (int i=0; i<10; i++) {
			Army army = new Army(player2);
			player2.getArmyList().add(army);				
		}
	}
	
	@Ignore
	public void testFortify(String player1Name, String player2Name, int numberOfArmy ){		
		
		int oldArmyNumberPlayer1 = player1.getArmyNumber();
		int oldArmyNumberPlayer2 = player2.getArmyNumber();
		
		//move army (quantity = numberOfArmy) from player 1 to player 2 in fortification phase
/*		fortify(String player1Name, String player2Name, int numberOfArmy){
		}*/
		
		assertEquals(player1.getArmyNumber(), (oldArmyNumberPlayer1 - numberOfArmy));
		assertEquals(player2.getArmyNumber(), (oldArmyNumberPlayer2 + numberOfArmy));
	}
}
