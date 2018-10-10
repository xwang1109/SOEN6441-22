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
	private JFrame frame;
	public PlayerSetupController(JFrame frame) {
		this.frame = frame;
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
			Map map = new Map();
			boolean result = map.loadMapFromFile(selectedFile);
			if(result) {
				//get different separator from different system
				String mapFileString = selectedFile.getParentFile().getAbsolutePath() + java.nio.file.FileSystems.getDefault().getSeparator() + map.getImage();
				File imageFile = new File(mapFileString);
		        Image i;
				try {
					i = ImageIO.read(imageFile);
					//Image newImage = i.getScaledInstance(1000, 750,Image.SCALE_SMOOTH);
					//ImageIcon image = new ImageIcon(newImage);
					ImageIcon image = new ImageIcon(i);
			        JLabel imageLabel = new JLabel(image);
			        frame.add(imageLabel);
			        frame.setLayout(null);
			      
			        imageLabel.setLocation(0, 20);
			        int width = i.getWidth(null);
			        int height = i.getHeight(null);
			        imageLabel.setSize(width, height);
			        imageLabel.setVisible(true);
			        //frame.setVisible(true);
			        frame.setSize(width, height+20);
			       
				} catch (IOException ex) {
					
					ex.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "not a valid map");
			}
			
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
