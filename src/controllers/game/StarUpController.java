package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import views.game.StartUpView;
import views.game.ViewState;
import models.game.Player;
import models.map.Country;
import models.map.GameState;

public class StarUpController implements ActionListener {

	private StartUpView starUpView;
	
	public StarUpController(StartUpView view) {
		starUpView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Country selectedCoutnry = starUpView.getSelectedCountry();
		selectedCoutnry.AddArmy();
		
		if(starUpView.decreaseLeftArmies() == 0) {
			if (starUpView.getPlayerCounter() < GameState.getInstance().getPlayerList().size()-1) {
				starUpView.setPlayerCounter(starUpView.getPlayerCounter()+1);
				starUpView.setLeftArmies(GameState.getInstance().getPlayerList().get(starUpView.getPlayerCounter()).getArmyNumber());
				starUpView.showPlayer();
			}
			else {
				ViewState.getInstance().showReinforcementView();
			}
		}
	}
}
