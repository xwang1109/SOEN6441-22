package models.map;

import models.game.*;
import java.util.ArrayList;
import java.util.List;

public class Country {
	private String name;
	private Continent continent;
	private Player owner;
	private int numOfArmies;
	private int locationX;
	private int locationY;
	private ArrayList<Country> adjacentCountryList = new ArrayList<Country>();
	private List<Army> armyList;
	
	public Country(String name) {
		this.name = name;
		numOfArmies =0;
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
		return numOfArmies;
	}
	public void setNumOfArmies(int numOfArmies) {
		this.numOfArmies = numOfArmies;
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
	
	public void AddArmy() {
		for (Army army:owner.getArmyList()) {
			if(army.getCountry().equals(null)) {
				army.setCountry(this);
				armyList.add(army);
				break;
			}
		}
		numOfArmies++;
	}	
	
	
}
