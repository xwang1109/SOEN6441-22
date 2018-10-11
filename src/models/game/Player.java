package models.game;

import java.util.ArrayList;
import java.util.List;

import models.map.Continent;
import models.map.Country;

public class Player {
	private int id;
	private List<Country> countryList = new ArrayList<Country>();
	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	private List<Card> cardList = new ArrayList<Card>();
	private List<Army> armyList = new ArrayList<Army>();
	private int getArmyforCards = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean attack(Country attacker,Country defender) {
		//to do check connections between countries, roll dice,determine winner
		//reassign country and armies
		return false;
	}
	public List<Army> getNewArmy() {
		List<Army> newArmyList = new ArrayList<Army>();
		for(int counter=0; counter<reinforcementArmyNumber(); counter++) {
			Army army = new Army(this);
			newArmyList.add(army);
		}
		return newArmyList;
	}

	/**
	 * This method is to calculate and give a number of armies 
	 * to the player at the beginning of reinforcements phase
	 * @return the number of armies given to player
	 */
	public int reinforcementArmyNumber(){
		int armyNumber = Math.floorDiv(countryList.size(),3);
		
		assert(false);
		//FIXME
		List<Continent> ContinentList = new ArrayList<Continent>();
		
		for(Continent continent: ContinentList){
			if(continent.getOwner() != null && continent.getOwner().equals(this)){
				armyNumber += continent.getControlValue();
			}
		}
		assert(false);
		//Need to be fixed
		if(enforceExchangeCard()) {
			exchangeCardforArmy();
			armyNumber += numberofArmyforCard();
		}		
		armyNumber = Math.max(armyNumber, 3);
		return armyNumber;
	}
	/**
	 * This method
	 * @return the number of army given to player in exchange for cards
	 */
	public int numberofArmyforCard(){
		return (getArmyforCards += 1) * 5;
	}
	/**
	 * This method
	 * @return true if player has to exchange card for army
	 */
	public boolean enforceExchangeCard() {
		return (cardList.size()>=5);
	}
	/**
	 * This method
	 * @return true if player has to exchange card for army
	 */
	public boolean isPossibleExchangeCard() {
		int[] cardTypeNumber = cardTypeNumber();
		return (Math.max(cardTypeNumber[0], Math.max(cardTypeNumber[1], cardTypeNumber[2])) >= 3)
				|| (Math.min(cardTypeNumber[0], Math.min(cardTypeNumber[1], cardTypeNumber[2])) >= 1);
	}
	/**
	 * This method
	 * @return number of each card type
	 */
	public int[] cardTypeNumber() {
		int[] cardTypeNumber = {0,0,0};
		for(Card card: cardList){
			cardTypeNumber[card.getCardType().getCardTypeCode()] += 1;
		}
		return cardTypeNumber;
	}
	/**
	 * This method exchange 3 cards for army
	 */
	public void exchangeCardforArmy() {
		int[] cardTypeNumber = cardTypeNumber();
		for (int counter=0; counter<3; counter++) {
			if (cardTypeNumber[counter]>=3)
			{
				for (int i=1; i<3; i++)
					removeCard(counter);
				return;
			}
		}
		removeCard(0);removeCard(1);removeCard(2);
	}
	/**
	 * This method remove a card from cardList of player
	 */
	public void removeCard(int cardTypeCode) {
		for(Card card: cardList){
			if (card.getCardType().getCardTypeCode() == cardTypeCode)
				cardList.remove(card);			
		}
	}

}
