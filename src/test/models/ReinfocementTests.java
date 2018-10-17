package test.models;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import models.game.Card;
import models.game.Player;
import models.game.Card.CardType;
import models.map.Continent;
import models.map.Country;
import models.map.GameState;

public class ReinfocementTests {

	Player player1 = new Player();
	Player player2 = new Player();
	
	@Before
	public void setUp() throws Exception {
		//player1 owns 10 countries 
		//player1 owns a continent with control value 4
		//player1 owns 3 INFANTRY cards
		ArrayList<Continent> continentList = new ArrayList<Continent>();
		for(int i=0; i<5; i++) {
			continentList.add(new Continent("continent" + i, i));
		}
		continentList.get(4).setOwner(player1);
		GameState.getInstance().getMap().setContinentList(continentList);
		
		for(int i=0; i<10; i++) {
			player1.getCountryList().add(new Country("country" + i));
		}
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i=0; i<3; i++) {
			cards.add(new Card(player1));
			cards.get(i).setCardType(CardType.INFANTRY);
		}
		player1.getCardList().addAll(cards);
		player1.setArmyforCards(2);

		//player2 owns 1 country
		//player2 owns no continent
		//player2 owns 1 INFANTRY cards
		player2.getCountryList().add(new Country("country10"));
		player2.getCardList().add(cards.get(0));		
		player2.setArmyforCards(0);		
	}

	@Test
	public void testReinfocementArmyNumberCalculation() {
		int expectedResult = 7;
		int result = player1.CalculateReinforcementArmyNumber();
		assertEquals(expectedResult, result);

		expectedResult = 3;
		result = player2.CalculateReinforcementArmyNumber();
		assertEquals(expectedResult, result);
	}

	@Test
	public void testIsPossibleExchangeCard() {
		boolean result = player1.isPossibleExchangeCard();
		assertTrue(result);

		result = player2.isPossibleExchangeCard();
		assertFalse(result);		
	}

	@Test
	public void testCalculateNumberOfArmyForCard() {
		int expectedResult = 15;
		int result = player1.numberOfArmyForCard();
		assertEquals(expectedResult, result);

		expectedResult = 5;
		result = player2.numberOfArmyForCard();
		assertEquals(expectedResult, result);
	}
}
