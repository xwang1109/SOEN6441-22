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
			ViewState.getInstance().showFortificationView();
		}
	}
/**
 * Perform actions when Add Army button clicked
 * 
 */
	public void addArmy() {
		if (GameState.getInstance().getPhase().equals(Phase.SETUP)) {
			Country selectedCoutnry = starUpView.getSelectedCountry();
			selectedCoutnry.AddArmy();
			if(starUpView.decreaseLeftArmies() == 0) {
				if (starUpView.getPlayerCounter() < GameState.getInstance().getPlayerList().size()-1) {
					starUpView.setPlayerCounter(starUpView.getPlayerCounter()+1);
					starUpView.setLeftArmies(GameState.getInstance().getPlayerList().get(starUpView.getPlayerCounter()).getLeftArmyNumber());
					starUpView.showPlayer();
				}
				else {
					GameState.getInstance().setPhase(Phase.REINFORCEMENT);
					starUpView.setPlayerCounter(0);
					starUpView.setLeftArmies(GameState.getInstance().getPlayerList().get(starUpView.getPlayerCounter()).addReinforcementArmy());
					starUpView.showPlayer();
					if(starUpView.getPlayer().getCardList().size()>4)//if there are more or equal to 5 cards, force to change card
					{
						starUpView.exchangeCard();;

					}
				}
			} else {
				starUpView.showLeftArmies();
			}
		} else if (GameState.getInstance().getPhase().equals(Phase.REINFORCEMENT)) {
			Country selectedCoutnry = starUpView.getSelectedCountry();
			selectedCoutnry.AddArmy();
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
}
