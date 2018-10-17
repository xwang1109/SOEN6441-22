package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import models.map.GameState;
import views.game.ViewState;

/**
 * class FortificationController to receive player action and call methods from model
 * @author Lin Li
 * @see ViewState.getInstance(), GameState.getInstance()
 */
public class FortificationController implements ActionListener {
	
	private JTextField from, to, quantity;
	
	/**
	 * Constructor of class FortificationController
	 * @param from
	 * @param to
	 * @param quantity
	 */
	public FortificationController(JTextField from, JTextField to, JTextField quantity) {
		this.from = from;
		this.to = to;
		this.quantity = quantity;
	}
	
	@Override
	/**
	 * Perform Fortification if player click on button
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		String fromStr = from.getText();
		String toStr = to.getText();
		int qt = Integer.parseInt( quantity.getText() );
		
		if (GameState.getInstance().fortify(fromStr, toStr, qt ) ) {
			// TODO current player ended his turn.
			// set the gameState to next player.
			
			//remember to refresh the map view all the time
			ViewState.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());

			
			ViewState.getInstance().showReinforcementView();			
		} else {
			System.out.println("Invalid reinforcement");
		}
	}
}
