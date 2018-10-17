package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.map.*;
import views.map.ConnectionView;

public class ConnectionController implements ActionListener {
	private ConnectionView view;
	private int id;
	private Map map;
	
	
	public ConnectionController(ConnectionView view, Map map, int id) {
		this.view = view;
		this.map = map;
		this.id = id;	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		switch(actionCommand) {
		case "Add":
			addConnection();
			break;
		case "Delete":
			deleteConnection();
			break;
		}
	}
	
	public void addConnection() {
		
		String newConnectedCountryName = this.view.getSelectedCountryName();
		
		int newConnectedCountryID = map.getCountryByName(newConnectedCountryName).getID();
		
		map.addConnection(id, newConnectedCountryID);
		this.view.setVisible(false);
		this.view.dispose();
		
		
	}
	
	public void deleteConnection() {
		
	}
	

}
