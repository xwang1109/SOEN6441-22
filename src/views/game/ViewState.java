package views.game;

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.game.Player;
import models.map.Country;

public class ViewState extends JFrame {
	private static final long serialVersionUID = 7243006502142830314L;

	/*
	 * Contains function for switching between different views.
	 */
	private JPanel controlPanel = new JPanel();
	//private JPanel mapPanel = new JPanel();
	
	private ViewState() {
		controlPanel = new JPanel();
		this.setSize(1024,800);
	}
	
	static private ViewState instance = new ViewState();
	static public ViewState getInstance() {
		return instance;
	}
	
	private void clear() {
		controlPanel.removeAll();
		controlPanel.revalidate();
		controlPanel.repaint();
	}
	
	public void showBasicView() {
		clear();
		new BasicView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		//getContentPane().add(mapPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	public void showPlayerView(File selectedFile) {
		clear();
		new PlayerSetupView(controlPanel,selectedFile,this);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	public void showStarUpView(File selectedFile, List<Player> playerList, List<Country> countryList) {
		clear();
		new StartUpView(controlPanel, selectedFile, this, playerList, countryList);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	public void showReinforcementView(File selectedFile, List<Player> playerList, List<Country> countryList) {
		clear();
		new ReinforcementView(controlPanel,selectedFile,this, playerList, countryList);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	
	
}
