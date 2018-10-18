package test.models;
import static org.junit.Assert.*;
import models.map.GameState;
import models.map.Map;

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
	Map map;

	/**
	 * Loading Map Files
	 */
	@Before
	public void InitiateMap() {


		String currentPath=System.getProperty("user.dir");
		//this is the correct map
		fileCorrectMap=new File(currentPath+"\\TestCaseMapFiles\\AlabamaCorrect.map");
		//there is a wrong connection for country, one of country does not exist.
		fileFalseConnection=new File(currentPath+"\\TestCaseMapFiles\\IranFalseConnection.MAP");
		//there is a wrong continent. one of continent does not exist.
		fileFalseContinent=new File(currentPath+"\\TestCaseMapFiles\\AlabamaFalseContinent.map");
		
		map = new Map();
			
	}			

 
				

   @Test
   public void TestCorrectMap() {
	   
		boolean resultCorrectMap = map.loadMapFromFile(fileCorrectMap);
	    assertTrue(resultCorrectMap);
	   
   }
  @Test
   public void TestFalseConnection() {
	   
	   boolean resultFalseConnection = map.loadMapFromFile(fileFalseConnection);
	   assertFalse(resultFalseConnection);
   }
  @Test
  public void TestFalseContinent() {
	   
	   boolean resultFalseContinent = map.loadMapFromFile(fileFalseContinent);
	   assertFalse(resultFalseContinent);
   }
  
  @Test
  public void TestMapValidationEmptyMap() {
	  Map map = new Map();
	  assertFalse(map.isValid());
  }
   
 
}