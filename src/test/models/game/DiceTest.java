/**
 * this is the test Case for checking dice value between 6 & 1
 * @author Parisa
 * @version 2.0.0
 */
package test.models.game;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import models.game.Dice;

public class DiceTest {
int[] diceRoll;
@Before	
public void setupTest() {
		Dice diceRollTest=new Dice();
		diceRoll=diceRollTest.diceRoll(5);
	}

@Test
public void validDiceTestValue() {
	int a=diceRoll[2];
	boolean diceValue1 = false;
	if (1<=a & a<=6) {
		diceValue1=true;
	}
	assertTrue(diceValue1);
}

}
