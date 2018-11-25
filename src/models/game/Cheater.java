package models.game;

import models.map.Country;

public class Cheater implements Strategy{

	@Override
	public String toString() {
		return "Cheater";
	}
	
	@Override
	public void reinforcementPhase(Player player) {
		// TODO Auto-generated method stub
		for (Country c:player.getCountryList()){
			int reinforcementArmyNumber = c.getNumOfArmies();
			player.addReinforcementArmy(reinforcementArmyNumber);
			for (int i=0; i<reinforcementArmyNumber; i++)
				c.AddArmy();
		}	
	}

	@Override
	public void attackPhase(Player player) {
		for(Country c:player.getCountryList()) {
			for(Country adjCountry: c.getAdjacentCountryList()) {
				 if(adjCountry.getOwner().getId()!=player.getId()) {
					 adjCountry.setOwner(player);
				 }
			}			
		}
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
		for (Country c:player.getCountryList()){
			if(c.hasAdjacentControlledByOthers()) {
				int armyNumber = c.getNumOfArmies();
				player.addReinforcementArmy(armyNumber);
				for (int i=0; i<armyNumber; i++)
					c.AddArmy();				
			}
		}			
	}

}
