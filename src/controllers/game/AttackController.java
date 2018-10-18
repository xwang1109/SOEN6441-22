package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import models.map.GameState;
import views.game.ViewState;

/**
 * The Class AttackController. player perform attacks according to the rule of risk
 * @author Bingyang Yu
 * @version 1.0
 */
public class AttackController implements ActionListener {

	/**
	 * Instantiates a new attack controller.
	 *
	 * @param numberOfPlayer the number of player
	 */
	public AttackController(JComboBox numberOfPlayer) {
		// TODO Auto-generated constructor stub
		// TODO skipping it because it's not coded
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO player should perform attacks
		// everytime there's a successful attack ; check if the game is finished
		// have a button for end of attack;
		// for now, skip always directly to fortification
		
		//remember to refresh the map view all the time
		ViewState.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());

		ViewState.getInstance().showFortificationView();
		
	}

}
