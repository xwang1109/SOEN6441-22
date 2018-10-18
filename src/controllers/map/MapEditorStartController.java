package controllers.map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.map.Map;
import views.map.*;
/**
 * The Class MapEditorStartController. 
 */
public class MapEditorStartController implements ActionListener{

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Map map = new Map();
		MapEditorView editorView = new MapEditorView(map);
		map.addObserver(editorView);
		editorView.setVisible(true);
		
	}

}
