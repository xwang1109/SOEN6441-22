package models.map;

import java.util.ArrayList;

import models.game.*;



public class Continent {
	private int id;
	
	private String name;
	private ArrayList<Country> countryList = new ArrayList<Country>();
	private int controlValue;
	private Player owner;
	
	private static int idGenerator=0;
	
	public Continent(String name, int controlValue) {
		this.name = name;
		this.controlValue = controlValue;
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
	public ArrayList<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}
	public int getControlValue() {
		return controlValue;
	}
	public void setControlValue(int value) {
		this.controlValue = value;
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

	public Player checkOwnership() {
		return null;
	}
}
