package models.game;

import java.util.Random;

/**
 * The Class Card.
 */
public class Card {
	
	/** The player. */
	private Player player;
	
	/** The card type. */
	private CardType cardType;
	
	/**
	 * The Enum CardType.
	 */
	public enum CardType {
		
		/** The infantry. */
		INFANTRY(0),
		
		/** The cavalry. */
		CAVALRY(1),
		
		/** The artillery. */
		ARTILLERY(2);
	    
    	/** The card type code. */
    	private final int cardTypeCode;
	    
    	/**
    	 * Instantiates a new card type.
    	 *
    	 * @param cardTypeCode the card type code
    	 */
    	private CardType(int cardTypeCode) {
	        this.cardTypeCode = cardTypeCode;
	    }
	    
    	/**
    	 * Gets the card type code.
    	 *
    	 * @return the card type code
    	 */
    	public int getCardTypeCode(){
	    	return cardTypeCode;
	    }
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Gets the card type.
	 *
	 * @return the card type
	 */
	public CardType getCardType() {
		return cardType;
	}
	
	/**
	 * Sets the card type.
	 *
	 * @param cardType the new card type
	 */
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	/**
	 * Instantiates a new card.
	 *
	 * @param player the player
	 */
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

