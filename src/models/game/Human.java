package models.game;

public class Human implements Strategy{

	@Override
	public String toString() {
		return "Human";
	}
	
	@Override
	public void reinforcementPhase(Player player) {
		// TODO Auto-generated method stub
		player.addReinforcementArmy(player.CalculateReinforcementArmyNumber());
	}

	@Override
	public void attackPhase(Player player) {
		// TODO Auto-generated method stub
	}

	@Override
	public void fortificationPhase(Player player) {
		// TODO Auto-generated method stub
	}

}
