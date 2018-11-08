/**
 * The Class Dice . after  players rolled Dice  ,
 * actions of assign random value to Dices
 * @author Parisa khazaei
 * @version 1.0
 */
package models.game;
import java.util.Random;

public class Dice {
	private int numberOfDots;
	
	

	
	public int getNumberOfDots() {
		return numberOfDots;
	}

	public Dice() {
		
	}
	//this method assign random value to Dices.
	 public int[] diceRoll(int numberofDices){

		 	int[] dice = new int[numberofDices];
		    Random random = new Random();
		    
		    for(int i=0;i<dice.length;i++)
		    {
		    	dice[i] = 1 + random.nextInt(6);
		    }
		    return dice;
		    }
}
