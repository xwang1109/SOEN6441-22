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
		setVisible(true);
	}

	public void showPlayerView() {
		clear();
		new PlayerSetupView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	public void showStarUpView(File selectedFile, List<Player> playerList, List<Country> countryList) {
		clear();
		new StartUpView(controlPanel, selectedFile, this, playerList, countryList);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	
//	public void showReinforcementView(File selectedFile, List<Player> playerList, List<Country> countryList) {
//		new ReinforcementView(controlPanel,selectedFile,this, playerList, countryList);
	public void showReinforcementView() {
		clear();
		new ReinforcementView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	public void showFortificationView() {
		clear();
		new FortificationView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
}
