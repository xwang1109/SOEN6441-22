package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.map.Map;
import views.map.BasicInfoView;

public class BasicInfoController implements ActionListener{

	Map map;
	BasicInfoView view;
	
	
	public BasicInfoController(Map map, BasicInfoView view){
		this.map = map;
		this.view = view;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		updateBasicInfo();
	}
	
	public void updateBasicInfo() {
		map.setAuthor(view.getAuthorText());
		map.setImage(view.getImageText());
		map.setScroll(view.getScrollText());
		map.setWrap(view.getWrapText());
		map.setWarn(view.getWarnText());
		this.view.setVisible(false);
	}

}
