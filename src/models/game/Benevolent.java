package models.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import models.map.Country;

public class Benevolent implements Strategy{

	@Override
	public String toString() {
		return "Benevolent";
	}
	
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
		HashMap <Country, ArrayList<Country>> connectedCountryList = new HashMap<Country, ArrayList<Country>>();
		
		for (Country c:player.getCountryList()) {
			if(c.getNumOfArmies()>0) {
				ArrayList<Country> destination = player.getValidDestination(c);
				for (int i=0; i<destination.size(); i++) {
					connectedCountryList.put(c, destination);				
				}
			}
		}	
		
		int min = Integer.MAX_VALUE;
		ArrayList<Country> fromCountries = null;
		Country toCountry = null;
		
		for(Country f: connectedCountryList.keySet()) {
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
	
	@Override
	public void setupPhase(Player player) {
		// TODO Auto-generated method stub
		int leftArmy=player.getLeftArmyNumber();
		for(int i=0; i<leftArmy;i++)
		{
			Random rand = new Random();
		    List<Country> playerCountrylist=player.getCountryList();
			Country randomCountry = player.getCountryList().get(rand.nextInt(playerCountrylist.size()));
			randomCountry.AddArmy();	
		}
	}
	
}
