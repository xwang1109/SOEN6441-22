package views.game;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import controllers.map.MapEditorStartController;

public class ReinforcementView {

	public ReinforcementView(JPanel controlPanel,File selectedFile,JFrame frame) {
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		

		JComboBox numberOfPlayer = new JComboBox();
		for (int i = 2; i < 6; i++) {
			numberOfPlayer.addItem(i);
		}
		
		JButton newGameButton = new JButton("RE");
		newGameButton.addActionListener(new GameStartController(numberOfPlayer,selectedFile,frame));
		controlPanel.add(newGameButton);
		
		JButton openMapEditorButton = new JButton("REINFORCEME");
		openMapEditorButton.addActionListener(new MapEditorStartController());

		controlPanel.add(openMapEditorButton);
	}

}
