package views.game;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class PlayerSetupView {

	public PlayerSetupView(JPanel controlPanel) {
		// feed the beast
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JComboBox numberOfPlayer = new JComboBox();
		for (int i = 2; i < 6; i++) {
			numberOfPlayer.addItem(i);
		}
		
		JButton newGameButton = new JButton("STARTTHEGAME");
		newGameButton.addActionListener(new GameStartController(numberOfPlayer));
		controlPanel.add(numberOfPlayer);
		controlPanel.add(newGameButton);
		
	}
}
