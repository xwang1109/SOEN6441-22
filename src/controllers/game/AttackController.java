package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import models.game.Dice;
import models.game.GameState;
import models.game.GameState.Phase;
import models.map.Country;
import views.game.AttackView;
import views.game.StateView;

/**
 * The Class AttackController. player perform attacks according to the rule of risk
 * @author Bingyang Yu
 * @author Mehrnaz
 * @version 2.0
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

	public AttackController(AttackView attackView) {
		
		this.attackView = attackView;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		StateView.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
		
		switch ( e.getActionCommand() ) {
		case AttackView.RollDiceStr:
			if (attackView.getAttacherDiceNumber()!=0 && attackView.getDefenderDiceNumber()!=0) {
				int attackerDiceNumber = attackView.getAttacherDiceNumber();
				int defenderDiceNumber = attackView.getDefenderDiceNumber();
				Country attackerCountry = attackView.getSelecterdCountryFrom();
				Country defenderCountry = attackView.getSelecterdCountryTo();

				Dice dice = new Dice();
				int[] attackerDice = dice.diceRoll(attackerDiceNumber);
				int[] defenderDice = dice.diceRoll(defenderDiceNumber);
				int[] attackResult = GameState.getInstance().getCurrentPlayer().attack(attackerDice, defenderDice);
				attackerCountry.removeArmies(attackResult[0]);
				defenderCountry.removeArmies(attackResult[1]);
				attackView.showResolutionState(attackerDice, defenderDice, attackResult[0], attackResult[1]);
			}
			break;
			
		case AttackView.ContinueStr:
			Country fromCountry = attackView.getSelecterdCountryFrom();
			Country toCountry = attackView.getSelecterdCountryTo();
			int diceNumber = attackView.getAttacherDiceNumber();
			
			if (GameState.getInstance().getCurrentPlayer().conquer(toCountry)) {
				if (GameState.getInstance().getMap().mapOwner(GameState.getInstance().getCurrentPlayer()))
					StateView.getInstance().showEndGameView();
				else
					attackView.showMoveArmiesState(diceNumber);
			}
			else if(GameState.getInstance().getCurrentPlayer().getArmyNumber() == 0) {
				// current player ended his/her turn.
				GameState.getInstance().endPlayerTurn();
				StateView.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
				
				GameState.getInstance().setPhase(Phase.REINFORCEMENT);
				StateView.getInstance().showReinforcementView();					
			}
			else if(GameState.getInstance().getCurrentPlayer().isAttackPossible())
				attackView.showSelectionState();
			else {
				GameState.getInstance().setPhase(Phase.FORTIFICATION);
				StateView.getInstance().showFortificationView();
			}
			break;
		case AttackView.MoveArmiesStr:
			//GameState.getInstance().fortify(attackView.getSelecterdCountryFrom().getName(), attackView.getSelecterdCountryTo().getName(), attackView.getArmiesNumberToMove());
			GameState.getInstance().getCurrentPlayer().moveArmies(attackView.getSelecterdCountryFrom(), attackView.getSelecterdCountryTo(), attackView.getArmiesNumberToMove());
			attackView.showSelectionState();
			break;
		case AttackView.EndAttackPhaseStr:
			GameState.getInstance().setPhase(Phase.FORTIFICATION);
			StateView.getInstance().showFortificationView();
			break;

		case AttackView.AllOutStr:
			Country attackerCountry = attackView.getSelecterdCountryFrom();
			Country defenderCountry = attackView.getSelecterdCountryTo();
			int diceNo = 0;
			while(defenderCountry.getNumOfArmies()!=0 && attackerCountry.getNumOfArmies()>1) {
				diceNo = doAttack(attackerCountry, defenderCountry);
			}
							
			//if(defenderCountry.getNumOfArmies() == 0) {
			if (GameState.getInstance().getCurrentPlayer().conquer(defenderCountry)) {
				//defenderCountry.setOwner(GameState.getInstance().getCurrentPlayer());
				if (GameState.getInstance().getMap().mapOwner(GameState.getInstance().getCurrentPlayer()))
					StateView.getInstance().showEndGameView();
				else
					attackView.showMoveArmiesState(diceNo);
			}
			else if(GameState.getInstance().getCurrentPlayer().getArmyNumber() == 0) {
				// current player ended his/her turn.
				GameState.getInstance().endPlayerTurn();
				StateView.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
				
				GameState.getInstance().setPhase(Phase.REINFORCEMENT);
				StateView.getInstance().showReinforcementView();					
			}
			else if(GameState.getInstance().getCurrentPlayer().isAttackPossible())
				attackView.showSelectionState();
			else {
				GameState.getInstance().setPhase(Phase.FORTIFICATION);
				StateView.getInstance().showFortificationView();
			}
			
			break;
		}
	}
	
	public int doAttack(Country attackerCountry, Country defenderCountry) {

		int attackerDiceNumber = Math.min(3,attackerCountry.getNumOfArmies());
		int defenderDiceNumber = Math.min(2,Math.min(attackerCountry.getNumOfArmies(), defenderCountry.getNumOfArmies()));

		Dice dice = new Dice();
		int[] attackerDice = dice.diceRoll(attackerDiceNumber);
		int[] defenderDice = dice.diceRoll(defenderDiceNumber);
		int[] attackResult = GameState.getInstance().getCurrentPlayer().attack(attackerDice, defenderDice);
		attackerCountry.removeArmies(attackResult[0]);
		defenderCountry.removeArmies(attackResult[1]);
		return attackerDiceNumber;
	}

}
