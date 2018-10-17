package models.game;

import java.util.ArrayList;
import java.util.List;

import models.map.Continent;
import models.map.Country;
import models.map.GameState;

public class Player {
	private int id;
	private ArrayList<Country> countryList = new ArrayList<Country>();
	private ArrayList<Card> cardList = new ArrayList<Card>();
	private ArrayList<Army> armyList = new ArrayList<Army>();
	private int getArmyforCards = 0; //number of times player is given army for cards
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Army> getArmyList() {
		return armyList;
	}
	public int getArmyNumber() {
		return armyList.size();
	}
	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}
	public ArrayList<Country> getCountryList() {
		return countryList;
	}
	public ArrayList<Card> getCardList() {
		return cardList;
	}
	public void setArmyforCards(int i) {
		getArmyforCards = i;
	}
	
	
	public boolean attack(Country attacker,Country defender) {
		//to do check connections between countries, roll dice,determine winner
		//reassign country and armies
		return false;
	}
	/**
	 * This method add armies to the player according to the reinforcement rules
	 * @return
	 */
	public int addReinforcementArmy() {
		for(int i=0; i<CalculateReinforcementArmyNumber(); i++) {
			Army army = new Army(this);
			armyList.add(army);
		}
		return CalculateReinforcementArmyNumber();
	}

	/**
	 * This method is to calculate and give a number of armies 
	 * to the player at the beginning of reinforcements phase
	 * @return the number of armies given to player
	 */
	public int CalculateReinforcementArmyNumber(){
		int armyNumber = Math.floorDiv(countryList.size(),3);
		
		ArrayList<Continent> ContinentList = GameState.getInstance().getMap().getContinentList();		
		for(Continent continent: ContinentList){
			if(continent.getOwner() != null && continent.getOwner().equals(this)){
				armyNumber += continent.getControlValue();
			}
		}
		armyNumber = Math.max(armyNumber, 3);
		return armyNumber;
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
	 * This method
	 * @return the number of army given to player in exchange for cards
	 */
	public int numberOfArmyForCard(){
		return (getArmyforCards += 1) * 5;
	}
	/**
	 * This method exchange 3 cards for army
	 */
	public void exchangeCardforArmy() {
		int[] cardTypeNumber = cardTypeNumber();
		for (int counter=0; counter<3; counter++) {
			if (cardTypeNumber[counter]>=3)
			{
				for (int i=0; i<3; i++)
					removeCard(counter);
				return;
			}
		}
		removeCard(0);removeCard(1);removeCard(2);
	}
	public int addArmyForCard() {
		exchangeCardforArmy();
		int armyForCard = numberOfArmyForCard();
		for(int i=0; i<armyForCard; i++) {
			Army army = new Army(this);
			armyList.add(army);
		}
		return armyForCard;
		}
	/**
	 * This method remove a card from cardList of player
	 */
	public void removeCard(int cardTypeCode) {
		for(Card card: cardList){
			if (card.getCardType().getCardTypeCode() == cardTypeCode) {
				cardList.remove(card);			
				return;
			}
		}
	}

}
