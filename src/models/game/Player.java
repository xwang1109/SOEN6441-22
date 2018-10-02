package models.game;

import java.util.ArrayList;
import java.util.List;

import models.map.Country;

public class Player {
	private int id;
	private List<Country> countryList = new ArrayList();
	private List<Card> cardList = new ArrayList();
	private List<Army> armyList = new ArrayList();
	
	public boolean attack(Country attacker,Country defender) {
		//to do check connections between countries, roll dice,determine winner
		//reassign country and armies
		return false;
	}
	public List<Army> getNewArmy() {
		int num = countryList.size()/3;
		List<Army> newArmyList = new ArrayList();
		for(int i=0;i<num;i++) {
			Army army = new Army(this);
			newArmyList.add(army);
		}
		return newArmyList;
	}
}
