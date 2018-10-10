package views.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.game.GameStartController;
import controllers.game.PlayerSetupController;
import controllers.map.MapEditorStartController;

public class BasicView {

	public BasicView(JPanel controlPanel, JFrame frame) {
		
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new PlayerSetupController(frame));
		
		controlPanel.add(newGameButton);
		
		JButton openMapEditorButton = new JButton("Open Map Editor");
		openMapEditorButton.addActionListener(new MapEditorStartController());

		controlPanel.add(openMapEditorButton);
		
		
		
        
	}
	
	
	
	
	
	
}
