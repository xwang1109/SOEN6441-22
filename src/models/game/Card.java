package models.game;

import java.util.Random;

public class Card {
	private Player player;
	private CardType cardType;
	public enum CardType {
		INFANTRY(0),
		CAVALRY(1),
		ARTILLERY(2);
	    private final int cardTypeCode;
	    private CardType(int cardTypeCode) {
	        this.cardTypeCode = cardTypeCode;
	    }
	    public int getCardTypeCode(){
	    	return cardTypeCode;
	    }
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public Card(Player player) {
		this.player = player;
		int rand = (int) (Math.random()*(2));
		switch(rand) {
		case 0: 
			cardType = CardType.INFANTRY;
			break;
		case 1:
			cardType = cardType.CAVALRY;
			break;
		default:
			cardType = cardType.ARTILLERY;
		}
	}
	


}

