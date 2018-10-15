package controllers.game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.game.Army;
import models.game.Player;
import models.map.Country;
import models.map.GameState;
import models.map.Map;
import views.game.ViewState;

public class GameStartController implements ActionListener {
	
	private JComboBox numberOfPlayer;
	private File selectedFile;
	private JFrame frame;
	private Map map;
	
	public GameStartController(JComboBox numberOfPlayer,File selectedFile,JFrame frame) {
		
           this.numberOfPlayer = numberOfPlayer;
           this.selectedFile = selectedFile;
           this.frame = frame;
           
           map = new Map();
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
   		      
   		        imageLabel.setLocation(0, 50);
   		        int width = i.getWidth(null);
   		        int height = i.getHeight(null);
   		        imageLabel.setSize(width, height);
   		        imageLabel.setVisible(true);
   		        //frame.setVisible(true);
   		        frame.setSize(width, height+50);
   		       
   			} catch (IOException ex) {
   				
   				ex.printStackTrace();
   			}
   		}
   		else {
   			JOptionPane.showMessageDialog(frame, "not a valid map");
   		}
   		
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		List<Player> playerList = new ArrayList();
		
		int num = (Integer)numberOfPlayer.getSelectedItem();
		for(int i = 0;i < num; i++) {
			Player p = new Player();
			p.setId(i);
			playerList.add(p);
		}
		
		List<Country> countryList = map.getCountryList();
		Collections.shuffle(countryList);
		
		for(int i = 0; i < countryList.size(); i++)  
		{
			Country c = countryList.get(i);                // loop for get each country of the map
			Player p = playerList.get(i%num);              // find the corresponding player by the order of the player
			p.getCountryList().add(c);                     // assign country to each player
			c.setOwner(p);
		}
		
//		System.out.println(num);

		
		/**
		 * Allocate a number of initial armies to players
		 */
		int initialArmy = Math.round(countryList.size() / playerList.size()) + playerList.size();
		for (Player player:playerList) {
			for (int i=0; i<initialArmy; i++) {
				Army army = new Army(player);
				player.getArmyList().add(army);				
			}
		}
		
		ViewState.getInstance().showStarUpView(selectedFile, playerList, countryList);
		
		/*if ( true ) { // TODO need a map... GameState.getInstance().isMapLoaded() ) {
			// Do something about players
			ViewState.getInstance().showReinforcementView();
		} else {
			// TODO do a popup
			System.out.println("Issue with parsing the map");
		}*/
	}
	
	
	
	
}
