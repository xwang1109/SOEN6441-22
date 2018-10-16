package views.game;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.game.FortificationController;

/**
 * class FortificationView is the view for fortification phase
 * @author Lin Li
 * @see controllers.game.FortificationController
 */
public class FortificationView {

	/**
	 * Constructor of class FortificationView
	 * @param JPanel controlPanel
	 */
	public FortificationView(JPanel controlPanel) {
		// input for country one
		JTextArea textFrom = new JTextArea("From Country:");
		JTextField from = new JTextField(10);
		// input for country two
		JTextArea textTo = new JTextArea("To Country:");
		JTextField to = new JTextField(10);
		// number
		JTextArea textQuantity = new JTextArea("Number of Armies:");
		JTextField quantity = new JTextField(5);
		// callback to fortification controller
		JButton executeFortification = new JButton("Fortify!");
		executeFortification.addActionListener(new FortificationController( to, from , quantity ));
		
		controlPanel.add(textFrom);
		controlPanel.add(from);
		controlPanel.add(textTo);
		controlPanel.add(to);
		controlPanel.add(textQuantity);
		controlPanel.add(quantity);
		controlPanel.add(executeFortification);
	}
}
