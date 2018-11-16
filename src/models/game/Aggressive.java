package models.game;

import models.map.Country;

public class Aggressive implements Strategy{

	@Override
	public void reinforcementPhase(Player player) {
		// TODO Auto-generated method stub
		int reinforcementArmyNumber = player.CalculateReinforcementArmyNumber();
		int maxCountry = Integer.MIN_VALUE;
		int maxCountryCanAttack = Integer.MIN_VALUE;
		Country country = null;
		Country countryCanAttack = null;
		
		for(Country c:player.getCountryList()) {
			if (c.hasAdjacentControlledByOthers()) {
				if(c.getNumOfArmies()>maxCountryCanAttack) {
					countryCanAttack = c;
					maxCountryCanAttack = c.getNumOfArmies();
				}				
			}
			else {
				if(c.getNumOfArmies()>maxCountry) {
					country = c;
					maxCountry = c.getNumOfArmies();
				}				
			}
		}
		
		player.addReinforcementArmy(reinforcementArmyNumber);
		if(countryCanAttack != null) {
			country = countryCanAttack;
		}
		
		for (int i=0; i<reinforcementArmyNumber; i++)
			country.AddArmy();		
	}

	@Override
	public int attackPhase(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
		
	}

}
