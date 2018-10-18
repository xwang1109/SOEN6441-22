package test.models;
import static org.junit.Assert.*;
import models.map.GameState;
import org.junit.Before;
import org.junit.Test;
import java.io.*;


/**
 * This class tests Validity Of Map Files in Map Model
 * @author Parisa
 *
 */


public class MapTests  {
	File selectedFile;
	File fileCorrectMap;
	File fileFalseConnection;
	File fileFalseContinent;

	/**
	 * Loading Map Files
	 */
	@Before
	public void InitiateMap() {


		String currentPath=System.getProperty("user.dir");
		//this is the correct map
		fileCorrectMap=new File(currentPath+"\\MapFileTests\\AlabamaCorrect.map");
		//there is a wrong connection for country, one of country does not exist.
		fileFalseConnection=new File(currentPath+"\\MapFileTests\\IranFalseConnection.MAP");
		//there is a wrong continent. one of continent does not exist.
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