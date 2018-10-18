package test.models;
import static org.junit.Assert.*;
import models.map.GameState;
import org.junit.Before;
import org.junit.Test;
import java.io.*;





public class MapTests  {
	File selectedFile;
	File fileCorrectMap;
	File fileFalseConnection;
	File fileFalseContinent;

	
	@Before
	public void InitiateMap() {


		String currentPath=System.getProperty("user.dir");
		fileCorrectMap=new File(currentPath+"\\MapFileTests\\AlabamaCorrect.map");
		fileFalseConnection=new File(currentPath+"\\MapFileTests\\IranFalseConnection.MAP");
		fileFalseContinent=new File(currentPath+"\\MapFileTests\\AlabamaFalseContinent.map");
		

			
	}			

 
				

   @Test
   public void TestCorrectMap() {
	   
		boolean resultCorrectMap = GameState.getInstance().loadMapFromFile(fileCorrectMap);		
	    assertTrue(resultCorrectMap);
	   
   }
  @Test
   public void TestFalseConnection() {
	   
	   boolean resultFalseConnection = GameState.getInstance().loadMapFromFile(fileFalseConnection);
	   assertFalse(resultFalseConnection);
   }
  @Test
  public void TestFalseContinent() {
	   
	   boolean resultFalseContinent = GameState.getInstance().loadMapFromFile(fileFalseContinent);
	   assertFalse(resultFalseContinent);
   }
   
 
}