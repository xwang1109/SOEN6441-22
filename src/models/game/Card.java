package models.game;

public class Card {
	private Player player;
	private CardType cardType;
	private enum CardType {
		INFANTRY,CAVALRY,ARTILLERY
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
		super();
		this.player = player;
		// to do random assign card type
	}
	


}

