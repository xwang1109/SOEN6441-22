package test.models;
import static org.junit.Assert.*;

import models.map.GameState;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;




public class MapTests  {
	File fileTest;
	@Before
	public void InitiateMap() {
		 fileTest=new File("C:\\Users\\MAIN\\Documents\\Iran.map");
		//File selectedFileT = GameState.getInstance().getSelectedFile();
		//boolean resultT = GameState.getInstance().loadMapFromFile(fileTest);
 
				
	}
   @Test
   public void TestMap() {
	   boolean resultT = GameState.getInstance().loadMapFromFile(fileTest);
	   //boolean result2;
	   //MapTests maptest=new MapTests();
	   //result2=maptest.InitiateMap()
	   
	   assertTrue(resultT);
   }
 
}
