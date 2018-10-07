package controllers.map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.map.*;

public class MapEditorStartController implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		MapEditorView editorView = new MapEditorView();
		editorView.setVisible(true);
		
	}

}
