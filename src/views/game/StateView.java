package views.game;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import java.lang.String;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.game.GameState;
import views.map.MapCountryPanel;

/**
 * class ViewState to switching between different views of the game
 * @author Lin Li,Yanxin
 * @version 2.0
 * 
 */
public class StateView extends JFrame {
	private static final long serialVersionUID = 7243006502142830314L;

	private JPanel controlPanel = new JPanel();
	private MapCountryPanel mapPanel = new MapCountryPanel();             //all the views need to share a table for map
	private PlayerWorldDomainView panel;
	// Phase view implementation through observer pattern
	class PhaseView implements Observer {
		
		private static final long serialVersionUID = 1L;
		private JLabel phaseLabel = new JLabel();
		private JLabel playerLabel = new JLabel();
		private JLabel infoLabel = new JLabel();
		private JLabel ruleInfoLabel = new JLabel();
		
		
		public PhaseView() {
			doUpdateLabel();
		}
		
		@Override
		public void update(Observable o, Object arg) {
			doUpdateLabel();
		}
		
		public JLabel getLabel() {
			return phaseLabel;
		}
		
		public JLabel getRuleInfo() {
			return ruleInfoLabel;
		}
		
		private void doUpdateLabel() {
			phaseLabel.setText(GameState.getInstance().getPhase().toString());
			if(GameState.getInstance().getPlayerList().size()!=0) {
				playerLabel.setText(Integer.toString(GameState.getInstance().getCurrentPlayer().getId()));
			}
			infoLabel.setText(GameState.getInstance().getPhaseState().getPhaseInfo());
			
			String setUpInfoRule="<html>the number of players is determined,all the countries randomly assigned </html>";
			 
			String reinforcementInfoRule="<html>the player is given</html>";
			
			String attackInfoRule="<html>the player may choose one of the countries he owns that contains two or more armies</html>";
					
			String fortificationInfoRule="<html>the player may move any number of armies </html>";
			 
			 String finishedInfoRule="The game ends at any time one of the players owns all the countries in the map. ";	
			 
			 String phaseState;
			 phaseState=GameState.getInstance().getPhase().toString();
			 
		
			
			switch(phaseState){
			
				case "SETUP":
					ruleInfoLabel.setText(setUpInfoRule);
					break;
					
				case "REINFORCEMENT":
					ruleInfoLabel.setText(reinforcementInfoRule);
					break;
					
				case "ATTACK":
					ruleInfoLabel.setText(attackInfoRule);
					break;
					
				case "FORTIFICATION":
					ruleInfoLabel.setText(fortificationInfoRule);
					break;
				
				case "FINISHED":
					ruleInfoLabel.setText(finishedInfoRule);
					break;
					
				
			}
		}
		
	}
	PhaseView phaseDisplay = new PhaseView();
	
	JLabel getPhaseLabel() {
		return phaseDisplay.getLabel();
	}
	JLabel getRuleInfoLabel() {
		return phaseDisplay.getRuleInfo();
	}
	
	/**
	 * Constructor of class ViewState to set the window of the game
	 */
	private StateView() {
		controlPanel = new JPanel();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		GameState.getInstance().addPhaseObserver(phaseDisplay);
		panel = new PlayerWorldDomainView();
		GameState.getInstance().addObserver(panel);
		GameState.getInstance().addPhaseObserver(panel);
	}
	
	static private StateView instance = new StateView();
	
	/**
	 * Get the instance of view state
	 * @return ViewState
	 */
	static public StateView getInstance() {
		return instance;
	}
	
	/**
	 * Clear the control panel
	 */
	private void clear() {
		controlPanel.removeAll();
		controlPanel.revalidate();
		controlPanel.repaint();
	}
	
	/**
	 * Show basic view. only shows a "new" and "map editor" button.
	 */
	public void showBasicView() {
		clear();
		new BasicView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	/**
	 * Show player view. shows map info.
	 */
	public void showPlayerView() {
		clear();
		new PlayerSetupView(controlPanel,this);
		getContentPane().add(controlPanel, BorderLayout.CENTER);
		getContentPane().add(mapPanel, BorderLayout.SOUTH);
		getContentPane().add(panel, BorderLayout.EAST);
		setVisible(true);
	}
	
	/**
	 * Show reinforcement view.
	 */
	public void showReinforcementView() {
		clear();
		new ReinforcementView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.CENTER);
		getContentPane().add(panel, BorderLayout.EAST);
		setVisible(true);
	}
	
	/**
	 * Show attack view
	 */
	public void showAttackView() {
		clear();
		new AttackView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		getContentPane().add(panel,  BorderLayout.EAST);
		setVisible(true);
	}
	
	/**
	 * Show fortification view
	 */
	public void showFortificationView() {
		clear();
		new FortificationView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		getContentPane().add(panel, BorderLayout.EAST);
		setVisible(true);
	}

	/**
	 * Show End Game view
	 */
	public void showEndGameView() {
		clear();
		new EndGameView(controlPanel);
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		setVisible(true);
	}
	
	public MapCountryPanel getMapPanel() {
		return mapPanel;
	}

	public void setMapPanel(MapCountryPanel mapPanel) {
		this.mapPanel = mapPanel;
	}
	
	public void setWorldPanel(PlayerWorldDomainView panel) {
		this.panel = panel;
	}
}
