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
		case "Open exchange card view":
			starUpView.exchangeCard();
			break;
		case "Finish Attack":
			ViewState.getInstance().showAttackView();
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

					//starUpView.setPlayerCounter(0);
					//starUpView.setLeftArmies(GameState.getInstance().getPlayerList().get(starUpView.getPlayerCounter()).addReinforcementArmy());
					//starUpView.showPlayer();
					//if(starUpView.getPlayer().getCardList().size()>4)//if there are more or equal to 5 cards, force to change card
					//{
					//	starUpView.exchangeCard();;
					//}

				}
				starUpView.showPlayer();
			} else {
				starUpView.showLeftArmies();
			}
		} else if (GameState.getInstance().getPhase().equals(Phase.REINFORCEMENT)) {
			if(starUpView.decreaseLeftArmies() == 0) {
				GameState.getInstance().setPhase(Phase.ATTACK);
				ViewState.getInstance().showAttackView();
			} else {
				starUpView.showLeftArmies();
			}
		}

		//refresh the table for map
		ViewState.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
				
	}
}
