package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.map.Map;

public class GameStartController implements ActionListener {


	private Map map;
	
	public GameStartController(Map map) {
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this is called on new game. Validate that the map is ready, or display error
		
		if ( map.isLoaded() ) {
			// ready to start ; launch Reinforment view
			// ReinforcementView.setVisible(true);
		}
	}
	
	
	
	
}
