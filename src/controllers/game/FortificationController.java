package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import models.map.GameState;
import views.game.ViewState;

public class FortificationController implements ActionListener {
	
	private JTextField from, to, quantity;
	public FortificationController(JTextField from, JTextField to, JTextField quantity) {
		this.from = from;
		this.to = to;
		this.quantity = quantity;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String fromStr = from.getText();
		String toStr = to.getText();
		int qt = Integer.parseInt( quantity.getText() );
		
		if (GameState.getInstance().fortify(fromStr, toStr, qt ) ) {
			// TODO current player ended his turn.
			// set the gameState to next player.
			
			ViewState.getInstance().showReinforcementView();			
		} else {
			System.out.println("Invalid reinforcement");
		}
	}
}
