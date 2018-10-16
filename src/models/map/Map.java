package models.map;


import java.util.*;
import java.io.*;

public class Map extends Observable {

	public Map() {
		loaded = false;
	}
	private final int FILE_HEAD_LINE_NUMBER = 7;

	private String author;
	private String image;
	private String wrap = "no";
	private String scroll = "";
	private String warn = "no";
	private boolean loaded = false;
	
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
	
	public String getWrap() {
		return wrap;
	}
	public void setWrap(String wrap) {
		this.wrap = wrap;
	}
	public String getScroll() {
		return scroll;
	}
	public void setScroll(String scroll) {
		this.scroll = scroll;
	}
	public String getWarn() {
		return warn;
	}
	public void setWarn(String warn) {
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
	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}
	
	public void addContinent(Continent continent) {
		this.continentList.add(continent);
		setChanged();
		notifyObservers();
	}
	
	public void addCountry(Country country) {
		this.countryList.add(country);
	}
	
	
	public boolean isLoaded() {
		return loaded;
	}
	
	public Continent getContinentByName(String continentName) {
		for(int i=0;i<continentList.size();i++) {
			if(continentList.get(i).getName().equals(continentName)){
				return continentList.get(i);
			}
		}
		return null;
	}
	
	public Continent getContinentByID(int continentID) {
		for(int i=0;i<continentList.size();i++) {
			if(continentList.get(i).getID() == continentID){
				return continentList.get(i);
			}
		}
		return null;
	}
	
	public Country getCountryByName(String countryName) {
		for(int i=0;i<this.countryList.size();i++) {
			if(this.countryList.get(i).getName().equals(countryName)){
				return this.countryList.get(i);
			}
		}
		return null;
	}
	
	public Country getCountryByID(int countryID) {
		for(int i=0;i<this.countryList.size();i++) {
			if(this.countryList.get(i).getID()==countryID){
				return this.countryList.get(i);
			}
		}
		return null;
	}
	
	

	public boolean loadMapFromFile(File mapFile) {
		// TODO this can't be set true if the map didn't load
		loaded = true;
		boolean continentBegin = false;
		boolean countryBegin = false;
		
		
		try {
		
			FileReader reader = new FileReader(mapFile);
		    BufferedReader bufferedReader = new BufferedReader (reader);
		    String line;
		    int lineNum = 1;

	    	String[] splitLine;
	    	Hashtable<Country,String[]> connectivityHashTable = new Hashtable<Country,String[]>();
	    	
	    	
		    while ((line=bufferedReader.readLine())!=null) {
		    	if(line.isEmpty()) {
		    		continue;
		    	}
		    	if(lineNum<this.FILE_HEAD_LINE_NUMBER) {
			    	switch(lineNum) {
			    	case 1: 
			    		if(!line.equals("[Map]")) {
			    			return false;
			    		}
			    		break;
			    	case 2:
			    		splitLine = line.split("=",2);
			    		if(!splitLine[0].equals("author")) {
			    			return false;
			    		}
			    		this.author = splitLine[1];
			    		break;
			    	case 3:
			    		splitLine = line.split("=",2);
			    		if(!splitLine[0].equals("image")) {
			    			return false;
			    		}
			    		this.image = splitLine[1];
			    		break;
			    		
			    	case 4:
			    		splitLine = line.split("=",2);
			    		if(!splitLine[0].equals("wrap")) {
			    			return false;
			    		}
			    		this.wrap = splitLine[1];
			    		break;
			    	case 5:
			    		splitLine = line.split("=",2);
			    		if(!splitLine[0].equals("scroll")) {
			    			return false;
			    		}
			    		this.scroll = splitLine[1];
			    		break;
			    	case 6:
			    		splitLine = line.split("=",2);
			    		if(!splitLine[0].equals("warn")) {
			    			return false;
			    		}
			    		this.warn = splitLine[1];
			    		break;
			    		
			    	}
		    		
		    	}
		    	
		    	
		    	else {
		    		
		    		if(!continentBegin && !countryBegin) {
			    		if(!line.equals("[Continents]")) {
			    			return false;
			    		}
			    		continentBegin = true;
		    		}
		    		else if(continentBegin && !countryBegin) {
		    			if(line.equals("[Territories]")) {
		    				continentBegin = false;
		    				countryBegin = true;
		    			}
		    			else {
		    				splitLine = line.split("=", 2);
		    				continentList.add(new Continent(splitLine[0], Integer.parseInt(splitLine[1])));
		    			}
		    			
		    		}
		    		else if(countryBegin && !continentBegin) {
		    			
		    			
		    			splitLine = line.split(",");
		    			Country country = new Country(splitLine[0]);
		    			
		    			int locationX = Integer.parseInt(splitLine[1]);
		    			int locationY = Integer.parseInt(splitLine[2]);
		    			country.setLocationX(locationX);
		    			country.setLocationY(locationY);
		    			
		    			String continentName = splitLine[3];
		    			Continent continent = this.getContinentByName(continentName);
		    			if(continent == null) {
		    				return false;
		    			}
		    			country.setContinent(continent);
		    			continent.addCountry(country);
		    			
		    			// the connectivity part begins
		    			// first store the information in an ArrayList
		    			// after finish reading the countries, add the connection relationship
		    			
		    			int numConnectedCountry = splitLine.length-4;
		    			
		    			String[] connectedCountryNameArray = new String[numConnectedCountry];
		    			System.arraycopy(splitLine, 4, connectedCountryNameArray, 0, numConnectedCountry);
		    			
		    			connectivityHashTable.put(country, connectedCountryNameArray);
		    			this.countryList.add(country);
		    		}
		    	}
		    	
		    	
		    	
		    	lineNum++;
		    }
		    
		    
		    for(int i=0;i<this.countryList.size();i++) {
		    	Country country = this.countryList.get(i);
		    	String[] connectedCountryNameArray = connectivityHashTable.get(country);
		    	for(int j = 0;j<connectedCountryNameArray.length;j++) {
		    		Country connectedCountry = this.getCountryByName(connectedCountryNameArray[j]);
		    		if(connectedCountry == null) {
		    			return false;
		    		}
		    		country.addAdjacentCountry(connectedCountry);
		    	}
		    }
		}
		catch (Exception e) {
			return false;
		}
		setChanged();
		notifyObservers();
		
		return true;
	}
	
	/**
	 * Execute the fortification move
	 * return true if the fortification order was executed
	 * false in case of error
	 */
	public boolean fortify(String from, String to, int qt) {
		// TODO
		return false;
	}
	
	public boolean checkDuplicateContinentName(String name) {
		for(int i=0;i<this.continentList.size();i++) {
			Continent continent = continentList.get(i);
			if(name.equals(continent.getName())) {
				return true;
			}	
		}
		return false;
	}
	
	public boolean checkDuplicateCountryName(String name) {
		for(int i=0;i<this.countryList.size();i++) {
			Country country = countryList.get(i);
			if(name.equals(country.getName())) {
				return true;
			}	
		}
		return false;
	}
	
	
	
}
