package views.game;

import java.awt.FlowLayout;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.game.ExchangeController;
import controllers.game.ReinforcementController;
import models.game.Player;
import models.map.Country;

public class ExchangeView {

	private Player player;
	public Player getPlayer() {
		return player;	
	}
	private boolean requestToChangeCard;

    JLabel playerLabel;
    JLabel cardLabel;
	JComboBox<String> wantChangeCard;

    public ExchangeView(JPanel controlPanel,File selectedFile,JFrame frame, Player player) {
		FlowLayout fl_controlPanel = (FlowLayout) controlPanel.getLayout();
		fl_controlPanel.setAlignment(FlowLayout.LEADING);
		
		JButton startReinforcement = new JButton("Start Reinforcement");
		startReinforcement.addActionListener(new ExchangeController(this, player, wantChangeCard()));

		wantChangeCard = new JComboBox<String>();
		wantChangeCard.addItem ("Don't Change Card");
		wantChangeCard.addItem("Change Card");
		wantChangeCard.setSelectedIndex(0);
		
		controlPanel.add(playerLabel);
		controlPanel.add(startReinforcement);
				
        playerLabel.setText(Integer.toString(player.getId()));
        cardLabel.setText(Integer.toString(player.getCardList().size()));

        this.player = player;
	}
    
    private boolean wantChangeCard() {
    	return wantChangeCard.getSelectedIndex() == 0 ? false : true;
    }

}
