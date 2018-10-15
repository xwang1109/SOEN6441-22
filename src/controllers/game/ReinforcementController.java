package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.game.Player;
import models.map.Country;
import views.game.ReinforcementView;

public class ReinforcementController implements ActionListener{

	private ReinforcementView reinforcementView;
	private Player player;

	
	public ReinforcementController(ReinforcementView view, Player player) {
		reinforcementView = view;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Country clickedCoutnry = reinforcementView.getClickedCountry();
		clickedCoutnry.AddArmy();
	}

}
