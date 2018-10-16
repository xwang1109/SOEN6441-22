package views.game;

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.game.Player;
import models.map.Country;

/**
 * class ViewState to switching between different views of the game
 * @author Lin Li
 */
public class ViewState extends JFrame {
	private static final long serialVersionUID = 7243006502142830314L;

	private JPanel controlPanel = new JPanel();
	//private JPanel mapPanel = new JPanel();
	
	/**
	 * Constructor of class ViewState to set the window of the game
	 */
	private ViewState() {
		controlPanel = new JPanel();
		this.setSize(1024,800);
	}
	
	static private ViewState instance = new ViewState();
	
	/**
	 * Get the instance of view state
	 * @return ViewState
	 */
	static public ViewState getInstance() {
		return instance;
	}
	
	/**
	 * Clear the control panel
	 */
	private void clear() {
		controlPanel.removeAll();
		controlPanel.revalidate();
		controlPanel.repaint();
	}
	
	/**
	 * Show basic view
	 */
	public void showBasicView() {
		clear();
		new BasicView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	/**
	 * Show player view
	 */
	public void showPlayerView() {
		clear();
		new PlayerSetupView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	/**
	 * Show reinforcement view
	 */
	public void showReinforcementView() {
		clear();
		new ReinforcementView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	/**
	 * Show fortification view
	 */
	public void showFortificationView() {
		clear();
		new FortificationView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
}
