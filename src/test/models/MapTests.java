package test.models;
import static org.junit.Assert.*;

import models.map.GameState;
import models.map.Map;
import views.game.ViewState;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

//import javax.swing.JFileChooser;
//import javax.swing.filechooser.FileSystemView;

import java.io.*;




public class MapTests  {
	File selectedFile;
	File fileCorrectMap;
	File fileFalseConnection;
	File fileFalseContinent;

	
	@Before
	public void InitiateMap() {

			//JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			//int returnValue = jfc.showOpenDialog(null);
			//if (returnValue == JFileChooser.APPROVE_OPTION) {
			//	File selectedFile = jfc.getSelectedFile();
				//GameState.getInstance().setSelectedFile(selectedFile);
		fileCorrectMap=new File("C:\\Users\\MAIN\\Documents\\GitHub\\SOEN6441-22\\TestCaseMapFiles\\AlabamaCorrect.map");
		fileFalseConnection=new File("C:\\Users\\MAIN\\Documents\\GitHub\\SOEN6441-22\\TestCaseMapFiles\\AlabamaCorrect.map\\IranFalseConnection.MAP");
		fileFalseContinent=new File("C:\\Users\\MAIN\\Documents\\GitHub\\SOEN6441-22\\TestCaseMapFiles\\AlabamaFalseContinent.map");

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