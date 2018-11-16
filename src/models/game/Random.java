package models.game;

public class Random implements Strategy{

	@Override
	public void reinforcementPhase(Player player) {
		// TODO Auto-generated method stub
		int reinforcementArmyNumber = (int) (1 + (Math.random() * player.CalculateReinforcementArmyNumber()));
		int randomCountry = (int) (Math.random() * player.getCountryList().size());

		player.addReinforcementArmy(reinforcementArmyNumber);
		for (int i=0; i<reinforcementArmyNumber; i++)
			player.getCountryList().get(randomCountry).AddArmy();
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
