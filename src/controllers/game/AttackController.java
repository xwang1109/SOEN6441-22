package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import views.game.ViewState;

public class AttackController implements ActionListener {

	public AttackController(JComboBox numberOfPlayer) {
		// TODO Auto-generated constructor stub
		// TODO skipping it because it's not coded
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO player should perform attacks
		// everytime there's a successful attack ; check if the game is finished
		// have a button for end of attack;
		// for now, skip always directly to fortification
		ViewState.getInstance().showFortificationView();
		
	}

}
