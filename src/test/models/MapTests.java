package test.models;
import org.junit.Assert.*;

import models.map.GameState;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;




public class MapTests extends TestCase {
	@Before
	public void InitiateMap() {
		File fileTest=new File("C:\\Users\\MAIN\\Documents\\Iran.map");
		//File selectedFileT = GameState.getInstance().getSelectedFile();
		//boolean resultT = GameState.getInstance().loadMapFromFile(fileTest);
 
				
	}
   @Test
   public void TestMap() {
	   boolean resultT = GameState.getInstance().loadMapFromFile(fileTest);
	   //boolean result2;
	   //MapTests maptest=new MapTests();
	   //result2=maptest.InitiateMap()
	   
	   assretTrue(resultT);
   }
 
}
