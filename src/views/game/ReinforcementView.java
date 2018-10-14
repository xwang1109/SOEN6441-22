package views.game;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.game.AttackController;
import controllers.game.GameStartController;
import controllers.map.MapEditorStartController;

public class ReinforcementView {

	public ReinforcementView(JPanel controlPanel) {
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		

		JComboBox numberOfPlayer = new JComboBox();
		for (int i = 2; i < 6; i++) {
			numberOfPlayer.addItem(i);
		}
		
		//TODO below 3 lines to be modified by Mehrnaz when reinforcement is done
		JButton fortificationButton = new JButton("REINFORCEMENT");
		fortificationButton.addActionListener(new AttackController(numberOfPlayer));
		controlPanel.add(fortificationButton);
		
		//click to enter FORTIFICATION phase
		JButton newGameButton = new JButton("FORTIFICATION");
		newGameButton.addActionListener(new AttackController(numberOfPlayer));
		controlPanel.add(newGameButton);

	}

}
