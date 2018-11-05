package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import models.map.GameState;
import models.map.GameState.Phase;
import views.game.AttackView;
import views.game.ViewState;

/**
 * The Class AttackController. player perform attacks according to the rule of risk
 * @author Bingyang Yu
 * @version 1.0
 */
public class AttackController implements ActionListener {

	private JComboBox<String> fromDropBox = new JComboBox<String>();
	private JComboBox<String> destDropBox = new JComboBox<String>();
	private JTextField qtTextField = new JTextField();
	private AttackView attackView;
	
	/**
	 * Instantiates a new attack controller.
	 *
	 * @param numberOfPlayer the number of player
	 * @param attackView 
	 * @param qtTextField 
	 * @param destDropBox 
	 */
	public AttackController(JComboBox fromDropBox, JComboBox<String> destDropBox, JTextField qtTextField, AttackView attackView) {
		this.fromDropBox = fromDropBox;
		this.destDropBox = destDropBox;
		this.qtTextField = qtTextField;
		this.attackView = attackView;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ViewState.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
		
		switch ( e.getActionCommand() ) {
		case AttackView.RollDiceStr:
		case AttackView.RollAgainStr:
			//TODO here is pseudocode
			// do a roll, fill results, 
			// if src dead ; switch back to losing 
			// else if dst dead ; switch back to win
			// else switch back to resolution
			break;

		// Move, stop and more will send to selection afterward
		case AttackView.MoveArmiesStr:
		    //TODO performMove();
			attackView.showSelectionState();
			break;
		case AttackView.StopAttackStr:
		case AttackView.MoreAttackStr:
			attackView.showSelectionState();
			break;

		// end attack transitions to next phase
		case AttackView.EndAttackPhaseStr:
			GameState.getInstance().setPhase(Phase.FORTIFICATION);
			ViewState.getInstance().showFortificationView();
			break;
		}
				
	}

}
