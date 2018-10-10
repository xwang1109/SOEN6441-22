package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.game.ViewState;

public class PlayerSetupController implements ActionListener{
	public PlayerSetupController() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this is called on new game. Validate that the map is valid, or display error
		
		if ( true ) { // TODO need a map... GameState.getInstance().isMapLoaded() ) {
			// Do something about players
			ViewState.getInstance().showPlayerView();
		} else {
			// TODO do a popup
			System.out.println("Issue with parsing the map");
		}
	}
	

}
