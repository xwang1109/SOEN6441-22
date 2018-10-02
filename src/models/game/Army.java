package models.game;

import models.map.Country;

public class Army {
	private Player player;
	private Country country;
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Army(Player player) {
		super();
		this.player = player;
	}
	

}
