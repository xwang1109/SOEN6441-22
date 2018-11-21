package models.game;

import java.util.ArrayList;

import models.map.Country;

public class Random implements Strategy{

	@Override
	public void reinforcementPhase(Player player) {
		// TODO Auto-generated method stub
		int reinforcementArmyNumber = (int) (1 + (Math.random() * player.CalculateReinforcementArmyNumber()));
		int randomCountry = (int) (Math.random() * player.getCountryList().size());

		player.addReinforcementArmy(reinforcementArmyNumber);
		for (int i=0; i<reinforcementArmyNumber; i++)
			player.getCountryList().get(randomCountry).AddArmy();
		}

	@Override
	public void attackPhase(Player player) {
		int randomCountry = (int) (Math.random() * player.getCountryList().size());
		Country country = player.getCountryList().get(randomCountry);
		int randomTime = (int) Math.random()*10;
		int i = 0;
		if(country!=null) {
			while(country.getNumOfArmies()>1 && country.hasAdjacentControlledByOthers()
					&& country.getOwner().getId() == player.getId() && i<randomTime) {
				i++;
				int numAttackDice = Math.max(3, country.getNumOfArmies());
				ArrayList<Country> defenseCountryList = country.getAdjacentCountryList();
				Country defenseCountry = null;
				for(Country c:defenseCountryList) {
					if(c.getOwner().getId() != player.getId()) {
						defenseCountry = c;
						break;
					}
				}
				
				while(defenseCountry.getNumOfArmies()!=0 && country.getNumOfArmies()>1) {
					int numDefendDice = Math.min(2, defenseCountry.getNumOfArmies());
					Dice dice = new Dice();
					int[] attackerDice = dice.diceRoll(numAttackDice);
					int[] defenderDice = dice.diceRoll(numDefendDice);
					int[] attackResult = player.attack(attackerDice, defenderDice);
					country.removeArmies(attackResult[0]);
					defenseCountry.removeArmies(attackResult[1]);
				}
				if(country.getNumOfArmies()<=0) {
					defenseCountry.getOwner().conquer(country);
					defenseCountry.decreaseArmy();
					country.increaseArmy();
					return;
				}
				if(defenseCountry.getNumOfArmies() == 0) {
					country.decreaseArmy();
					defenseCountry.increaseArmy();
					player.conquer(defenseCountry);
				}
			}
		}
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
		
	}

}
