package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.map.Map;

public class MapEditorController implements ActionListener {
	
	private Map map;
	
	public MapEditorController(Map map) {
		this.map = map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// something with path
		map.loadMapFromFile();
	}
	
}
