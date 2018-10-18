package test.models;
import static org.junit.Assert.*;

import models.map.GameState;
import models.map.Map;
import views.game.ViewState;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;
//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileSystemView;
import java.nio.file.Paths;
import java.io.*;




public class MapTests  {
	File selectedFile;
	File fileCorrectMap;
	File fileFalseConnection;
	File fileFalseContinent;

	
	@Before
	public void InitiateMap() {


		String currentPath=System.getProperty("user.dir");
		System.out.println("Current dir:"+currentPath);
		fileCorrectMap=new File(currentPath+"\\MapFileTests\\AlabamaCorrect.map");
		fileFalseConnection=new File(currentPath+"\\MapFileTests\\IranFalseConnection.MAP");
		fileFalseContinent=new File(currentPath+"\\MapFileTests\\AlabamaFalseContinent.map");
		

			
	}			

 
				

   @Test
   public void TestMap() {


		boolean resultCorrectMap = GameState.getInstance().loadMapFromFile(fileCorrectMap);
		boolean resultFalseConnection = GameState.getInstance().loadMapFromFile(fileFalseConnection);
		boolean resultFalseContinent = GameState.getInstance().loadMapFromFile(fileFalseContinent);
		
	   assertTrue(resultCorrectMap);
	   assertFalse(resultFalseConnection);
	   assertFalse(resultFalseContinent);
   }
 
}