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

import models.game.Player;
import models.map.Country;
import models.map.GameState;
import models.map.Map;
import views.game.ViewState;


/**
 * The Class GameStartController.
 * @author Bingyang Yu
 * @version 1.0
 */
public class GameStartController implements ActionListener {
	
	/** The number of player. */
	private JComboBox numberOfPlayer;
	
	/** The selected file. */
	private File selectedFile;
	
	/** The frame. */
	private JFrame frame;
	
	/** The map. */
	private Map map;
	
	/**
	 * Instantiates a new game start controller. Load map into the controller. Display the map on the frame. If map is not valid, show an error message.
	 *
	 * @param numberOfPlayer a JComboBox to choose the number of players
	 * @param selectedFile player selected map file. Format: .map
	 * @param frame the frame of the entire program.
	 */
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
    
	/**
	 * this method is triggered by "Start" button. Players are created and countries are randomly assigned.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
		}
		
		
		//System.out.println(num);

		
		/*if ( true ) { // TODO need a map... GameState.getInstance().isMapLoaded() ) {
			// Do something about players
			ViewState.getInstance().showReinforcementView();
		} else {
			// TODO do a popup
			System.out.println("Issue with parsing the map");
		}*/
	}
	
	
	
	
}
