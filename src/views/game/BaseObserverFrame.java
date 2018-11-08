package views.game;

import javax.swing.JFrame;

import models.game.Player;


/**
 * The Class BaseObserverFrame.
 * @author Bingyang
 * @version 2.0
 */
public abstract class BaseObserverFrame extends JFrame {
	
	/** The player. */
	protected 	 Player player;

	/**
	 * Update.
	 */
	public abstract void update();
}
