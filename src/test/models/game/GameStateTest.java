package test.models.game;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import models.game.GameState;
import models.game.Player;
import models.map.Country;

public class GameStateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		GameState.getInstance().loadMapFromFile (new File("res/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map"));
		//System.out.println(GameState.getInstance().getMap().getCountryList().size());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRandomAssignCountry() {
		List<Country> testCountryList = GameState.getInstance().getMap().getCountryList();
		GameState.getInstance().assignInitialPlayers(2);
		List<Player> testPlayerList = GameState.getInstance().getPlayerList();
		GameState.getInstance().randomAssignCountry();
		for(int i = 0; i < testCountryList.size(); i++) {
			Country countryID = testCountryList.get(i);
			boolean find = false;
			for(int j = 0; j< testPlayerList.size(); j++) {
				Player testPlayer = testPlayerList.get(j);
				if (testPlayer.getCountryList().contains(countryID)) {
					find = true;
					break;
				}
			}
			assertTrue(find);
		}
		
	}

}
