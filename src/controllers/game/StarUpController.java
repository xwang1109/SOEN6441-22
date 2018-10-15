package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.game.StartUpView;
import models.game.Player;
import models.map.Country;

public class StarUpController implements ActionListener {

	private StartUpView starUpView;
	
	public StarUpController(StartUpView view) {
		starUpView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Country clickedCoutnry = starUpView.getClickedCountry();
		clickedCoutnry.AddArmy();
	}

}
