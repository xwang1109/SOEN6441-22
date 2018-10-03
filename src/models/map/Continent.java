package models.map;

import java.util.ArrayList;

import models.game.*;



public class Continent {
	private String name;
	private ArrayList<Country> countryList = new ArrayList<Country>();
	private int value;
	private Player owner;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public void addCountry(Country country) {
		this.countryList.add(country);
	}

	
}
