package models.game;

import models.map.Country;

public class Cheater implements Strategy{

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
	public int attackPhase(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
		
	}

}
