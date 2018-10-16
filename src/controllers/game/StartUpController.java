package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import views.game.StartUpView;
import views.game.ViewState;
import models.game.Player;
import models.map.Country;
import models.map.GameState;
import models.map.GameState.Phase;

public class StartUpController implements ActionListener {

	private StartUpView starUpView;
	
	public StartUpController(StartUpView view) {
		starUpView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Add Army":
			addArmy();
			break;
		case "Change Card":
			changeCard();			
		}
	}
	public void addArmy() {
		if (GameState.getInstance().getPhase().equals(Phase.SETUP)) {
			Country selectedCoutnry = starUpView.getSelectedCountry();
			selectedCoutnry.AddArmy();
			if(starUpView.decreaseLeftArmies() == 0) {
				if (starUpView.getPlayerCounter() < GameState.getInstance().getPlayerList().size()-1) {
					starUpView.setPlayerCounter(starUpView.getPlayerCounter()+1);
					starUpView.setLeftArmies(GameState.getInstance().getPlayerList().get(starUpView.getPlayerCounter()).getArmyNumber());
					starUpView.showPlayer();
				}
				else {
					GameState.getInstance().setPhase(Phase.REINFORCEMENT);
					starUpView.setPlayerCounter(0);
					starUpView.setLeftArmies(GameState.getInstance().getPlayerList().get(starUpView.getPlayerCounter()).addReinforcementArmy());
					starUpView.showPlayer();
					starUpView.changeToReinforcement();
//					ViewState.getInstance().showReinforcementView();
				}
			} else {
				starUpView.showLeftArmies();
			}
		} else if (GameState.getInstance().getPhase().equals(Phase.REINFORCEMENT)) {
			Country selectedCoutnry = starUpView.getSelectedCountry();
			selectedCoutnry.AddArmy();
			if(starUpView.decreaseLeftArmies() == 0) {
				GameState.getInstance().setPhase(Phase.ATTACK);
				starUpView.changeToAttack();
			} else {
				starUpView.showLeftArmies();
			}
		}
	}
	public void changeCard() {
		starUpView.setLeftArmies(starUpView.getLeftArmies() + starUpView.getPlayer().addArmyForCard());
		starUpView.updateLabels();
	}
}
