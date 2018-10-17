package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import views.game.ReinforcementView;
import views.game.ViewState;
import models.game.Player;
import models.map.Country;
import models.map.GameState;
import models.map.GameState.Phase;

public class ReinforcementController implements ActionListener {

	private ReinforcementView starUpView;
	
	public ReinforcementController(ReinforcementView view) {
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
			break;
		case "Finish Attack":
			ViewState.getInstance().showFortificationView();
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
		
		//refresh the table for map
		ViewState.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
				

	}
	public void changeCard() {
		starUpView.setLeftArmies(starUpView.getLeftArmies() + starUpView.getPlayer().addArmyForCard());
		starUpView.changedCard();
	}
}
