package models.game;

import java.util.ArrayList;
import java.util.HashMap;

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
		
		int min = Integer.MAX_VALUE;
		ArrayList<Country> fromCountries = null;
		Country toCountry = null;
		
		for(Country f: (Country[])connectedCountryList.keySet().toArray()) {
			for(Country t: connectedCountryList.get(f)) {
				if(t.getNumOfArmies()<min) {
					fromCountries.add(f);
					toCountry = t;
					min = t.getNumOfArmies();					
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		Country fromCountry = null;
		for(Country c:fromCountries) {
			if(c.getNumOfArmies()>max) {
				fromCountry = c;
				max = c.getNumOfArmies();
			}
		}
		
		if (fromCountry != null) {
			player.fortify(fromCountry.getName() , toCountry.getName(), fromCountry.getNumOfArmies()-1);
		}
		
	}

}
