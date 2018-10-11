package controllers.game;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import models.map.Map;
import views.game.ViewState;

public class PlayerSetupController implements ActionListener{
	
	public PlayerSetupController() {
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// this is called on new game. Validate that the map is valid, or display error
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			
			ViewState.getInstance().showPlayerView(selectedFile);
		}

		/*
		if ( true ) { // TODO need a map... GameState.getInstance().isMapLoaded() ) {
			// Do something about players
			ViewState.getInstance().showPlayerView();
		} else {
			// TODO do a popup
			System.out.println("Issue with parsing the map");
		}*/
	}
	

}
