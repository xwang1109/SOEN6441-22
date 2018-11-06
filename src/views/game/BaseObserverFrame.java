package views.game;

import javax.swing.JFrame;

import models.game.Player;

public abstract class BaseObserverFrame extends JFrame {
	
	protected 	 Player player;

	public abstract void update();
}
