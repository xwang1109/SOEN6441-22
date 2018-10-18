package test.models;
import static org.junit.Assert.*;

import models.map.GameState;
import models.map.Map;
import views.game.ViewState;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import java.io.*;




public class MapTests  {
	File selectedFile;
	@Before
	public void InitiateMap() {

			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				GameState.getInstance().setSelectedFile(selectedFile);
			}			

 
				
	}
   @Test
   public void TestMap() {

		File selectedFile = GameState.getInstance().getSelectedFile();
		boolean result = GameState.getInstance().loadMapFromFile(selectedFile);
	   
	   assertTrue(result);
   }
 
}