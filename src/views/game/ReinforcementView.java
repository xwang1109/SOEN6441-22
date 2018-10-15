package views.game;

import java.awt.FlowLayout;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.game.FinshAttackController;
import controllers.game.GameStartController;
import controllers.game.ReinforcementController;
import controllers.map.MapEditorStartController;
import models.game.Army;
import models.game.Player;
import models.map.Country;

public class ReinforcementView {
	private Player player;
	public Player getPlayer() {
		return player;	
	}
	private int armyNumber;
	private int armyLeft;
	private Country clickedCountry;
	public Country getClickedCountry() {
		return clickedCountry;
	}
	
    JLabel playerLabel;
    JLabel armyNumberLable;

    public ReinforcementView(JPanel controlPanel,File selectedFile,JFrame frame, Player player, List<Army> reinforcementArmy) {
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JButton finishAttackButton = new JButton("Finish Attach");
		finishAttackButton.addActionListener(new FinshAttackController());

		
		controlPanel.add(playerLabel);
		controlPanel.add(finishAttackButton);
				
		this.player = player;
		armyNumber = armyLeft = reinforcementArmy.size();
        playerLabel.setText(Integer.toString(player.getId()));
        armyNumberLable.setText(Integer.toString(armyLeft));
		
	}
    
}
