package models.map;

import models.game.*;
import java.util.ArrayList;
import java.util.List;

public class Country {
	private String name;
	private Continent continent;
	private Player owner;
	private int locationX;
	private int locationY;
	private ArrayList<Country> adjacentCountryList = new ArrayList<Country>();
	private ArrayList<Army> armyList = new ArrayList<Army>();	
	private int id;
	private static int idGenerator=0;

	public Country(String name) {
		this.name = name;
		idGenerator++;
		this.id = idGenerator;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Continent getContinent() {
		return continent;
	}
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public int getNumOfArmies() {
		return armyList.size();
	}
	public int getLocationX() {
		return locationX;
	}
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	public int getLocationY() {
		return locationY;
	}
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	public ArrayList<Country> getAdjacentCountryList() {
		return adjacentCountryList;
	}
	public void setAdjacentCountryList(ArrayList<Country> adjacentCountryList) {
		this.adjacentCountryList = adjacentCountryList;
	}
	
	public void addAdjacentCountry(Country country) {
		this.adjacentCountryList.add(country);
		
	}
	public void removeAdjacentCountryByID(int countryID) {
		for(Country country:this.adjacentCountryList) {
			if(country.getID() == countryID) {
				this.adjacentCountryList.remove(country);
				return;
			}
		}
	}
	
	public void AddArmy() {
		for (Army army:owner.getArmyList()) {
			if(army.getCountry() == null) {
				army.setCountry(this);
				armyList.add(army);
				break;
			}
		}
	}	
	/**
	 * Decrease number of Army in this country
	 */
	public void decreaseArmy() {
		if(armyList.size()>1){
			armyList.remove(1);
		}		
	}
	
	/**
	 * Decrease number of Army in this country
	 */
	public void increaseArmy() {
		Army army = new Army(owner);
		armyList.add(army);
	}
}
