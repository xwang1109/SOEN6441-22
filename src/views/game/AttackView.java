package views.game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.game.AttackController;
import controllers.game.FortificationController;
import models.game.Player;
import models.map.Country;
import models.map.GameState;

/**
 * class AttackView is the view for attack phase
 */
public class AttackView {
	
	/**
	 * Different situations when clicking different buttons
	 */
	public final static String EndAttackPhaseStr =  "End Attack Phase";
	public final static String RollDiceStr =  "ROLL THE DICE!";
	public final static String RollAgainStr =  "Roll Dice Again";
	public final static String MoveArmiesStr =  "Move Armies";
	public final static String MoreAttackStr =  "More Attack";
	public final static String StopAttackStr =  "Stop Attack";
	
	/**
	 * UI Objects that will be modified depending of state
	 */
	private JPanel actionPanel;
	private JButton endAttBtn;
	
	/**
	 * Objects requiring a full lifescope to transit informations with the controller
	 */
	private JComboBox<String> fromDropBox = new JComboBox<String>();
	private JComboBox<String> targetDropBox = new JComboBox<String>();
	private JTextField qtTextField = new JTextField();
	public ArrayList<Integer> results = new ArrayList<Integer>();
	
	/**
	 * Clear the action Panel
	 */
	private void clearAction() {
		actionPanel.removeAll();
		actionPanel.revalidate();
		actionPanel.repaint();
	}
	
	/**
	 * Constructor of class AttackView
	 * @param controlPanel
	 */
	public AttackView(JPanel controlPanel) {
		// Prepare this view layout ; info, middle panel that changes, end phase button
		controlPanel.setLayout(new GridLayout(0,3));
		
		//prepare the three panels
		prepareInformationPanel(controlPanel);
		
		actionPanel = new JPanel();
		controlPanel.add(actionPanel);
		actionPanel.setLayout(new GridLayout(2, 1));
		
		JPanel rightPanel = new JPanel();
		endAttBtn = new JButton(EndAttackPhaseStr);
		endAttBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		endAttBtn.addActionListener(new AttackController(fromDropBox, targetDropBox, qtTextField, this));
		rightPanel.add(endAttBtn);
		controlPanel.add(rightPanel);
		
		showSelectionState();
	}
	
	/**
	 * Static information panel to display current turn information,
	 * the “phase view” using the Observer pattern
	 * @param controlPanel
	 */
	private void prepareInformationPanel(JPanel controlPanel) {
		JPanel informationPanel = new JPanel();
		controlPanel.add(informationPanel);
		informationPanel.setLayout(new GridLayout(0, 2));
		
		JPanel labelColumn = new JPanel();
		informationPanel.add(labelColumn);
		labelColumn.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel phaseTextLabel = new JLabel("Phase: ");
		labelColumn.add(phaseTextLabel);
		
		JLabel currentPlayerTextLabel = new JLabel("Current Player: ");
		labelColumn.add(currentPlayerTextLabel);
		
		JPanel dataColumn = new JPanel();
		informationPanel.add(dataColumn);
		dataColumn.setLayout(new GridLayout(0, 1));
		dataColumn.add(ViewState.getInstance().getPhaseLabel()); // observer
		
		JLabel currentPlayerIndicator = new JLabel(String.valueOf(GameState.getInstance().getCurrentPlayer().getId()));
		dataColumn.add(currentPlayerIndicator);
	}

	class CountLabelListener implements ActionListener {
		private JLabel label;
		private boolean needsSame; //to facilitate code re-use
		
		CountLabelListener(JLabel label, boolean needsSame) {
			this.label = label;
			this.needsSame = needsSame;
		}
		
        public void actionPerformed(ActionEvent event) {
        	JComboBox comboBox = (JComboBox) event.getSource();
            String selected = (String)comboBox.getSelectedItem();
            Country selectedCountry = null;
            for(Country c: GameState.getInstance().getMap().getCountryList()) {
            	if (c.getName().equals(selected))
            		selectedCountry = c; 
            }
            
            //check if the owner of the selected country is the same as current player
            boolean sameOwner = selectedCountry.getOwner() == GameState.getInstance().getCurrentPlayer();
            
            //if select a country and the owner is the current player,
            //show the number of armies of this country
            if ( selectedCountry != null && (sameOwner == needsSame) ) {
            	label.setText(String.valueOf(selectedCountry.getNumOfArmies()));
            }
        }
	}
	
	public void showSelectionState() {
		clearAction();
		endAttBtn.setEnabled(true);

		JPanel actionInforPanel = new JPanel();
		actionPanel.add(actionInforPanel);
		actionInforPanel.setLayout(new GridLayout(1, 3));
		
		JPanel actionTextPanel = new JPanel();
		actionInforPanel.add(actionTextPanel);
		actionTextPanel.setLayout(new GridLayout(2, 1));
		
		JLabel fromTextPanel = new JLabel("Attack From: ");
		actionTextPanel.add(fromTextPanel);
		
		JLabel targetTextPanel = new JLabel("Attack Target: ");
		actionTextPanel.add(targetTextPanel);
		
		JPanel actionCountryInfoPanel = new JPanel();
		actionInforPanel.add(actionCountryInfoPanel);

		JLabel numberOfArmyInFromCountry = new JLabel("Number of Armies");
		JLabel numberOfArmyInTargetCountry = new JLabel("Number of Armies");
		
		//clear drop down box
		fromDropBox.removeAllItems();
		targetDropBox.removeAllItems();
		
		//get list for drop down box
		for(Country c: GameState.getInstance().getMap().getCountryList()) {
        	if (c.getOwner() == GameState.getInstance().getCurrentPlayer() &&
        		c.getNumOfArmies() > 1)
        		fromDropBox.addItem(c.getName());
        }
		actionCountryInfoPanel.setLayout(new GridLayout(0, 2, 0, 0));
		actionCountryInfoPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		actionCountryInfoPanel.add(fromDropBox);
		
		fromDropBox.addActionListener(new CountLabelListener(numberOfArmyInFromCountry, true));
		
		fromDropBox.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	JComboBox comboBox = (JComboBox) event.getSource();
                String selected = (String)comboBox.getSelectedItem();
                Country selectedCountry = null;
                for(Country c: GameState.getInstance().getMap().getCountryList()) {
                	if (c.getName().equals(selected))
                		selectedCountry = c; 
                }
                
                //get target countries for the drop down box, only show the adjacent countries of the "from country" which has different owner
                for( Country n: selectedCountry.getAdjacentCountryList()) {
                	if ( n.getOwner() != GameState.getInstance().getCurrentPlayer() ) {
                		targetDropBox.addItem(n.getName());
                	}
                }
            }
         });
		
		JLabel label = new JLabel("");
		actionCountryInfoPanel.add(label);
		actionCountryInfoPanel.add(targetDropBox);
		targetDropBox.addActionListener(new CountLabelListener(numberOfArmyInTargetCountry, false));
		
		JPanel actionNumberOfArmy = new JPanel();
		actionInforPanel.add(actionNumberOfArmy);
		actionNumberOfArmy.setLayout(new GridLayout(2, 1));
		
		actionNumberOfArmy.add(numberOfArmyInFromCountry);
		
		actionNumberOfArmy.add(numberOfArmyInTargetCountry);
		
		JPanel actionButtonPanel = new JPanel();
		actionPanel.add(actionButtonPanel);
		
		JButton actionButton = new JButton(RollDiceStr);
		actionButton.addActionListener(new AttackController(fromDropBox, targetDropBox, qtTextField, this));
		
		JLabel label_1 = new JLabel("");
		actionCountryInfoPanel.add(label_1);
		actionButtonPanel.add(actionButton);
	}
}
