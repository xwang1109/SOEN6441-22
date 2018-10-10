package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.map.GameState;
import models.map.Map;
import views.game.ViewState;

public class GameStartController implements ActionListener {
	
	public GameStartController() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this is called on new game. Validate that the map is valid, or display error
		
		if ( true ) { // TODO need a map... GameState.getInstance().isMapLoaded() ) {
			// Do something about players
			ViewState.getInstance().showReinforcementView();
		} else {
			// TODO do a popup
			System.out.println("Issue with parsing the map");
		}
	}
	
	
	
	
}
