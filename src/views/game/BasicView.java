package views.game;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import controllers.game.PlayerSetupController;
import controllers.map.MapEditorStartController;

public class BasicView {

	public BasicView(JPanel controlPanel) {
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new PlayerSetupController());
		controlPanel.add(newGameButton);
		
		JButton openMapEditorButton = new JButton("Open Map Editor");
		openMapEditorButton.addActionListener(new MapEditorStartController());

		controlPanel.add(openMapEditorButton);
	}
	
	
	
	
	
	
}
