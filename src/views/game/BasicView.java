package views.game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.game.*;
import controllers.map.*;
import models.map.Map;

public class BasicView extends JFrame {
	private static final long serialVersionUID = 8758263371385532077L;

	public BasicView(Map map) {
		this.setSize(1024,800);
		JPanel controlPanel = new JPanel();
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new GameStartController(map));
		controlPanel.add(newGameButton);
		
		JButton openMapEditorButton = new JButton("Open Map Editor");
		openMapEditorButton.addActionListener(new MapEditorStartController());

		controlPanel.add(openMapEditorButton);
	}
	
	
	
	
	
	
}
