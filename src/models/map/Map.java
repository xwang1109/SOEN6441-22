package models.map;

import java.util.ArrayList;

public class Map {

	private String author;
	private String image;
	private boolean wrap = false;
	private boolean scroll = false;
	private boolean warn = false;
	
	private ArrayList<Continent> continentList = new ArrayList<Continent>();
	private ArrayList<Country> countryList = new ArrayList<Country>();
	
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isWrap() {
		return wrap;
	}
	public void setWrap(boolean wrap) {
		this.wrap = wrap;
	}
	public boolean isScroll() {
		return scroll;
	}
	public void setScroll(boolean scroll) {
		this.scroll = scroll;
	}
	public boolean isWarn() {
		return warn;
	}
	public void setWarn(boolean warn) {
		this.warn = warn;
	}
	public ArrayList<Continent> getContinentList() {
		return continentList;
	}
	public void setContinentList(ArrayList<Continent> continentList) {
		this.continentList = continentList;
	}
	public ArrayList<Country> getCountryList() {
		return countryList;
	}
	public void setCountry(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}
	
	
	public void loadMapFromFile() {
		// to do
	}
	
	
	
}
