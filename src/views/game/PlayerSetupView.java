package views.game;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;

public class PlayerSetupView {

	public PlayerSetupView(JPanel controlPanel) {
		// feed the beast
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JButton newGameButton = new JButton("STARTTHEGAME");
		newGameButton.addActionListener(new GameStartController());
		controlPanel.add(newGameButton);
	}
}
