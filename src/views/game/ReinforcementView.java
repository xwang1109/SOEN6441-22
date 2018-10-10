package views.game;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import controllers.map.MapEditorStartController;

public class ReinforcementView {

	public ReinforcementView(JPanel controlPanel) {
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JButton newGameButton = new JButton("RE");
		newGameButton.addActionListener(new GameStartController());
		controlPanel.add(newGameButton);
		
		JButton openMapEditorButton = new JButton("REINFORCEME");
		openMapEditorButton.addActionListener(new MapEditorStartController());

		controlPanel.add(openMapEditorButton);
	}

}
