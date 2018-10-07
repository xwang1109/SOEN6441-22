
import models.map.Map;
import views.game.BasicView;


public class Risk {
	
	public static void main(String[] args) {
		Map map = new Map();
		BasicView view = new BasicView(map);
		view.setVisible(true);
		
		
		// below code for workflow should be in all other files
		
		// Load things
		
		// while !finished
			// for each player never end
				// reinforcement
					// analysis map, determine how many troops are given to the current player 
					// player input for placement
		        // attack
					// while player not finished
						// select source country
						// select destination country
						// resolve 1 attack (attack valid?) (result)
						// 
						// check for victory, game finishes?
						// query for next attack or end
		        // fortification
					// query source
					// query destination
					// validate  (pathfinding) and execute
		
	}
}
