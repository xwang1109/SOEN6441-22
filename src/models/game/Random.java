package models.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javafx.util.Pair;
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
	public int attackPhase(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
		HashMap <Country, ArrayList<Country>> connectedCountryList = new HashMap<Country, ArrayList<Country>>();
		
		for (Country c:player.getCountryList()) {
			if(c.getNumOfArmies()>1) {
				ArrayList<Country> destination = player.getValidDestination(c);
				for (int i=0; i<destination.size(); i++) {
					connectedCountryList.put(c, destination);				
				}
			}
		}
		
		if(connectedCountryList.size()>0) {
			int randomFromCountry =  (int) (Math.random()*connectedCountryList.size());
			Country[] fromCountryList = (Country[]) connectedCountryList.keySet().toArray();
			Country fromCountry = fromCountryList[randomFromCountry];
			ArrayList<Country> destination = connectedCountryList.get(fromCountry);
			
			if (destination.size()>0) {
				int randomToCountry =  (int) (Math.random()*destination.size());
				Country toCountry = destination.get(randomToCountry);
				
				int randomQt =  (int) (1 + Math.random()*(fromCountry.getNumOfArmies()-1));
				
				player.fortify(fromCountry.getName() , toCountry.getName(), randomQt);
			}
		}
		
	}

}
