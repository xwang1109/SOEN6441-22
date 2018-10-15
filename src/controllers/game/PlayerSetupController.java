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


/**
 * The Class PlayerSetupController. 
 */
public class PlayerSetupController implements ActionListener{
	
	/**
	 * Instantiates a new player setup controller.
	 */
	public PlayerSetupController() {
		
	}
	

	/**
	 * This method is triggered by "NEW GAME" button. It opens up a new dialog and allows player to choose map file. The file is passed to PlayerSetupView
	 * @see views.game.PlayerSetupView
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
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
