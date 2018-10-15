package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.game.Army;
import models.game.Player;
import views.game.ExchangeView;

public class ExchangeController implements ActionListener{
	private ExchangeView exchangeView;
	private Player player;
	private boolean requestToChangeCard;

	public ExchangeController(ExchangeView view, Player player, boolean requestToChangeCard) {
		exchangeView = view;
		this.player = player;
		this.requestToChangeCard = requestToChangeCard;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<Army> reinforcementArmy = player.addReinforcementArmy(requestToChangeCard);
		
	}
}
