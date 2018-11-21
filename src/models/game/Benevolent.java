package models.game;

import java.util.ArrayList;

import models.map.Country;

public class Benevolent implements Strategy{

	@Override
	public void reinforcementPhase(Player player) {
		// TODO Auto-generated method stub
		int reinforcementArmyNumber = player.CalculateReinforcementArmyNumber();
		int min = Integer.MAX_VALUE;
		Country country = null;
		
		for(Country c:player.getCountryList()) {
			if(c.getNumOfArmies()<min) {
				country = c;
				min = c.getNumOfArmies();
			}
		}
		
		player.addReinforcementArmy(reinforcementArmyNumber);
		for (int i=0; i<reinforcementArmyNumber; i++)
			country.AddArmy();
	}

	@Override
	public void attackPhase(Player player) {
		// do not attack
		return;
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
