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

/**
 * Class ReinforcementController is the controller which is bound to ReinforcementView
 * and instructs the model to perform actions based on user inputs
 * @author Mehrnaz
 * @see controllers.game.ReinforcementView
 */
public class ReinforcementController implements ActionListener {

	private ReinforcementView starUpView;
/**
 * Constructor of the class which initialize the view  	
 * @param view ReinforcementView
 */
	public ReinforcementController(ReinforcementView view) {
		starUpView = view;
	}
/**
 * Performs actions based on user inputs
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Add Army":
			addArmy();
			break;
		case "Exchange Card":
			changeCard();
			break;
		case "Finish Attack":
			ViewState.getInstance().showFortificationView();
		}
	}
/**
 * Perform actions when Add Army button clicked
 * 
 */
	public void addArmy() {
		Country selectedCoutnry = starUpView.getSelectedCountry();
		selectedCoutnry.AddArmy();
		if (GameState.getInstance().getPhase().equals(Phase.SETUP)) {
			if(starUpView.decreaseLeftArmies() == 0) {
				if (!GameState.getInstance().setUpRoundRobin()) {
					GameState.getInstance().setPhase(Phase.REINFORCEMENT);
					GameState.getInstance().setFirstPlayer();
					GameState.getInstance().getCurrentPlayer().addReinforcementArmy();
					starUpView.changeToReinforcement();
				}
				starUpView.showPlayer();
			} else {
				starUpView.showLeftArmies();
			}
		} else if (GameState.getInstance().getPhase().equals(Phase.REINFORCEMENT)) {
			if(starUpView.decreaseLeftArmies() == 0) {
				GameState.getInstance().setPhase(Phase.ATTACK);
				//starUpView.changeToAttack();
				ViewState.getInstance().showFortificationView();
			} else {
				starUpView.showLeftArmies();
			}
		}

		//refresh the table for map
		ViewState.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
				
	}
/**
 * Perform actions when Exchange Card button clicked
 */
	public void changeCard() {
		starUpView.setLeftArmies(starUpView.getLeftArmies() + starUpView.getPlayer().addArmyForCard());
		starUpView.changedCard();
	}
}
