package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.models.map.*;

import test.models.game.*;
@RunWith(Suite.class)
@SuiteClasses({ContinentTest.class,CountryTests.class, MapTests.class,
	DiceTest.class,AttackTest.class,FortificationTest.class,GameStateTest.class,
	PlayerTest.class,ReinforcementTests.class
	})
public class TestSuite {

	

}
