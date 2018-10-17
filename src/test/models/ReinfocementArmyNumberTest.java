package test.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import models.game.Player;
import models.map.Continent;
import models.map.Country;
import models.map.GameState;

public class ReinfocementArmyNumberTest {

	Player player = new Player();
	
	@Before
	public void setUp() throws Exception {
		//create an object of Player
		//create map continents and set the player as the owner of continent with control value 4
		//set the number of his countries to 10
		//
		ArrayList<Continent> continentList = new ArrayList<Continent>();
		for(int i=0; i<5; i++) {
			continentList.add(new Continent("continent" + i, i));
		}
		continentList.get(4).setOwner(player);
		GameState.getInstance().getMap().setContinentList(continentList);
		for(int i=0; i<10; i++) {
			player.getCountryList().add(new Country("country" + i));
		}
	}

	@Test
	public void test() {
		int expectedResult = 7;
		int result = player.CalculateReinforcementArmyNumber();
		assertEquals(expectedResult, result);
	}

}
