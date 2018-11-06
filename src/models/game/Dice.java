package models.game;
import java.util.Random;

public class Dice {
	private int numberOfDots;
	
	

	
	public int getNumberOfDots() {
		return numberOfDots;
	}

	public Dice() {
		
	}
	
	 public int[] attackerDiceRandomFill(int numberAttackerDice){

		 	int[] attackerDice = new int[numberAttackerDice];
		    Random randAttcker = new Random();
		    
		    for(int i=0;i<attackerDice.length;i++)
		    {
		    	attackerDice[i] = randAttcker.nextInt();
		    	
		    }
		    return attackerDice;
		    }
	 public int[] defenderDiceRandomFill(int numberDefenderDice){
		 	
		 int[] defenderDice = new int[numberDefenderDice];
		    Random randDefender = new Random();
		    
		    for(int i=0;i<defenderDice.length;i++)
		    {
		    	defenderDice[i] = randDefender.nextInt();
		    	
		    }
		    return defenderDice;
		    }
}
