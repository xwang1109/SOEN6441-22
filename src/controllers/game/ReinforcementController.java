package controllers.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JComboBox;

import views.game.CardExchangeView;
import views.game.ReinforcementView;
import views.game.StateView;
import models.game.GameState;
import models.game.Player;
import models.game.GameState.Phase;
import models.game.Human;
import models.map.Country;

/**
 * Class ReinforcementController is the controller which is bound to ReinforcementView
 * and instructs the model to perform actions based on user inputs
 * @author Mehrnaz, Bingyang Yu
 * @see controllers.game.ReinforcementView
 * @version 2.0
 */
public class ReinforcementController implements ActionListener {

	private ReinforcementView starUpView;
	private CardExchangeView exchangeview;
	private Player player;
/**
 * Constructor of the class which initialize the view  	
 * @param view ReinforcementView
 */
	public ReinforcementController(ReinforcementView view) {
		starUpView = view;
	}
/**
 * Performs actions based on user inputs
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Add Army":
			addArmy();
			break;
		case "Open exchange card view":
			starUpView.exchangeCard();
			break;
		case "Finish Attack":
			StateView.getInstance().showAttackView();
		}
	}
/**
 * Perform actions when Add Army button clicked
 * 
 */
	public void addArmy() {
		Country selectedCoutnry = starUpView.getSelectedCountry();
		selectedCoutnry.AddArmy();	
		if (GameState.getInstance().getPhase().equals(Phase.SETUP)) {		
			if(starUpView.decreaseLeftArmies() == 0) {//finish setup	
				Boolean isLastPlayer=!GameState.getInstance().setUpRoundRobin();
				
				if (isLastPlayer) {//if this is the last player to setup, directly jump to reinforcement
					GameState.getInstance().setPhase(Phase.REINFORCEMENT);
					GameState.getInstance().setFirstPlayer();
					GameState.getInstance().getCurrentPlayer().addReinforcementArmy(GameState.getInstance().getCurrentPlayer().CalculateReinforcementArmyNumber());
					//starUpView.changeToReinforcement();
					starUpView.showPlayer();
					Player currentPlayer= GameState.getInstance().getCurrentPlayer();
					if(currentPlayer.getStrategy() instanceof Human)
					{
						if(currentPlayer.getCardList().size() > 4) { //if there are more or equal to 5 cards, force to change card
						starUpView.exchangeCard();
						}
					}
					else
					{
						if(currentPlayer.getCardList().size() > 4) { 
							//if there are more or equal to 5 cards, force to automatically change card
							
							currentPlayer.autoExchangeCardforArmy();
						}
							//automatically do the three parts by calling strategy functions
						
						GameState.getInstance().setPhase(Phase.REINFORCEMENT);
						currentPlayer.doStrategyReinforcement();
						GameState.getInstance().setPhase(Phase.ATTACK);
						currentPlayer.doStrategyAttack();
						GameState.getInstance().setPhase(Phase.FORTIFICATION);
						currentPlayer.doStrategyfortification();
						GameState.getInstance().endPlayerTurn();
						
						//remember to refresh the map view all the time
						StateView.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
						
						GameState.getInstance().setPhase(Phase.REINFORCEMENT);
						StateView.getInstance().showReinforcementView();		
						
					}
				}
				else//if this is not the last player, check if next player is human
				{
					Player nextPlayer=GameState.getInstance().getCurrentPlayer();
				
					while(!(nextPlayer.getStrategy() instanceof Human)&&(!isLastPlayer))//if not human
					{
						Player currentPlayer= GameState.getInstance().getCurrentPlayer();
						int leftArmy=currentPlayer.getLeftArmyNumber();
						for(int i=0; i<leftArmy;i++)
						{
							Random rand = new Random();
						    List<Country> playerCountrylist=currentPlayer.getCountryList();
							Country randomCountry = currentPlayer.getCountryList().get(rand.nextInt(playerCountrylist.size()));
							randomCountry.AddArmy();	
						}
						
						//currentPlayer.doStrategyReinforcement();
						isLastPlayer=!GameState.getInstance().setUpRoundRobin();
						nextPlayer=GameState.getInstance().getCurrentPlayer();
					}
					if(isLastPlayer)//jump to next view
					{
						GameState.getInstance().setPhase(Phase.REINFORCEMENT);
						StateView.getInstance().showReinforcementView();	
					}
				}
				
				starUpView.showPlayer();
			} else {
				starUpView.showLeftArmies();
			}
		} else if (GameState.getInstance().getPhase().equals(Phase.REINFORCEMENT)) {
			if(starUpView.decreaseLeftArmies() == 0) {
				GameState.getInstance().setPhase(Phase.ATTACK);
				StateView.getInstance().showAttackView();
			} else {
				starUpView.showLeftArmies();
			}
		}

		//refresh the table for map
		StateView.getInstance().getMapPanel().addCountryTableForMap(GameState.getInstance().getMap());
				
	}
}
