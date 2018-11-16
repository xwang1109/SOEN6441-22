package models.game;

public class Human implements Strategy{

	@Override
	public void reinforcementPhase(Player player) {
		// TODO Auto-generated method stub
		player.addReinforcementArmy(player.CalculateReinforcementArmyNumber());
	}

	@Override
	public int attackPhase(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
		
	}

}
