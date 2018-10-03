package views.game;

import javax.swing.*;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controllers.game.*;
import controllers.map.*;
public class BasicView extends JFrame {
	public BasicView() {
		this.setSize(1024,800);
		JPanel controlPanel = new JPanel();
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new GameStartController());
		controlPanel.add(newGameButton);
		
		JButton openMapEditorButton = new JButton("Open Map Editor");
		openMapEditorButton.addActionListener(new MapEditorController());
		controlPanel.add(openMapEditorButton);
	}
	
	
	
	
	
	
}
