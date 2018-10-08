package models.game;

import java.util.ArrayList;
import java.util.List;

import models.map.Continent;
import models.map.Country;

public class Player {
	private int id;
	private List<Country> countryList = new ArrayList<Country>();
	private List<Card> cardList = new ArrayList<Card>();
	private List<Army> armyList = new ArrayList<Army>();
	private int getArmyforCards = 0;
	
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
		armyNumber += numberofArmyforCard();
		armyNumber = Math.max(armyNumber, 3);
		return armyNumber;
	}
	public int numberofArmyforCard(){
		int infantryNumber = 0;
		int cavalryNumber = 0;
		int artilleryYNumber = 0;
		for(Card card: cardList){
			switch(card.getCardType().toString()){
			case "INFANTRY":
				infantryNumber += 1;
				break;
			case "CAVALRY":
				cavalryNumber += 1;
				break;				
			case "ARTILLERY":
				artilleryYNumber += 1;
			}
		}	
		if (inforceExchangeCard()){
			//implement exchange
		}
		getArmyforCards += 1;
		
		assert(false);
		// TODOFIXTHIS
		return -1;
	}
	/**
	 * If a player holds five cards during reinforcement phase
	 * they must exchange three of them for armies
	 * @return true if player holds five cards during reinforcement phase
	 */
	public boolean inforceExchangeCard() {
		return (cardList.size()>=5);
	}
}
