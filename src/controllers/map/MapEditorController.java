package controllers.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import views.map.*;

import models.map.*;

public class MapEditorController implements ActionListener {
	
	private Map map;
	private MapEditorView view;
	
	public MapEditorController(Map map, MapEditorView view) {
		this.map = map;
		this.view = view;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		switch(actionCommand) {
		case "Load":
			loadMapFromFile();
			break;
		case "Save":
			saveMapToFile();
			break;
			
		case "Add Country":
			addCountry();
			break;
			
		case "Add Continent":
			addContinent();
			break;
		
		case "Delete Country":
			deleteCountry();
			break;
		
		case "Delete Continent":
			deleteContinent();
			break;
		
		case "Edit Country":
			editCountry();
			break;
			
		case "Edit Continent":
			editContinent();
			break;
			
		case "Add Connection":
			addConnection();
			break;
			
		case "Delete Connection":
			deleteConnection();
			break;
			
		case "Edit Basic Info":
			editBasicInfo();
			break;
		}	
		
		
	}
	
	public void loadMapFromFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			if(!map.loadMapFromFile(selectedFile)) {
				map.clear();
				JOptionPane.showMessageDialog(null, "Invalid map file!");
			}
		}
	}
	
	public void saveMapToFile() {
		
		if(!this.map.isValid()) {
			JOptionPane.showMessageDialog(null, "This map is not valid! You can not save it. Please check it again.");
		}
		else {
			try {
				String fileName = JOptionPane.showInputDialog("Please input the name of the map");
	        	String currentPath=System.getProperty("user.dir");
	        	String absoluteFilePath = currentPath+"\\maps\\"+fileName+".map";
				File file = new File(absoluteFilePath);
				if(!file.createNewFile()) {
					JOptionPane.showMessageDialog(null, "This map already exists! Please use another name.");
				}
				else {
					if(this.map.saveMapToFile(file)) {
						JOptionPane.showMessageDialog(null, "Save successfully!");
					}
					else {
						JOptionPane.showMessageDialog(null, "Save failed, please try again later.");
					}
				}
			}
			catch(Exception e) {
				System.out.println("Save failed, please try again later.");
			}
		}
		
		
	}
	
	
	public void addCountry() {
		if(map.getContinentNumber()==0) {
			JOptionPane.showMessageDialog(null, "Please add a continent first!");
		}
		else {
			CountryView newCountryView = new CountryView(map);
			newCountryView.setVisible(true);
		}
	}
	
	public void addContinent() {
		ContinentView newContinentView = new ContinentView(map);
		newContinentView.setVisible(true);
	}
	
	
	public void deleteCountry() {
		
		int id = this.view.getSelectedCountryID();
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "Please select a country first!");
		}
		else {
			String msg = "Are you sure that you want to delete country "+
					map.getCountryByID(id)+"?";
		int option = JOptionPane.showConfirmDialog(null, msg);
		if(option == JOptionPane.YES_OPTION) {
			map.removeCountryByID(id);	
		}
		}
	}
	
	
	public void deleteContinent() {
		int id = this.view.getSelectedContinentID();
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "Please select a continent first!");
			
		}
		else {
			String msg = "Are you sure that you want to delete continent "+
						map.getContinentByID(id).getName()+
						"? All countries in this continent will also be deleted.";
			int option = JOptionPane.showConfirmDialog(null, msg);
			if(option == JOptionPane.YES_OPTION) {
				map.removeContinentByID(id);
				
				
			}
			
		}
	}
	
	
	public void editCountry() {
		int id = this.view.getSelectedCountryID();
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "Please select a country first!");
		}
		else {
			CountryView editCountryView = new CountryView(map,id);
			editCountryView.setVisible(true);
		}
		
		
	}
	
	public void editContinent() {
		
		int id = this.view.getSelectedContinentID();
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "Please select a continent first!");
			
		}
		else {
			ContinentView editContinentView = new ContinentView(map,id);
			editContinentView.setVisible(true);
		}
		
		
	}
	
	public void addConnection() {
		int id = this.view.getSelectedCountryID();
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "Please select a country first!");
		}
		else {
			Country country = map.getCountryByID(id);
			ArrayList<String> availableCountryName = new ArrayList<String>();
			ArrayList<String> connectedCountryName = new ArrayList<String>();
			
			for(Country c: map.getCountryList()) {
				availableCountryName.add(c.getName());
			}
			
			for(Country c:country.getAdjacentCountryList()) {
				connectedCountryName.add(c.getName());
			}
			availableCountryName.removeAll(connectedCountryName);
			availableCountryName.remove(country.getName());
			if(availableCountryName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There is no country can connect to the selected country!");
			}
			else {
				ConnectionView addConnectionView = new ConnectionView(map,id,ConnectionView.ADD_CONNECTION_OPTION);
				addConnectionView.setVisible(true);
			}
		}
		
	}
	
	
	public void deleteConnection() {
		int id = this.view.getSelectedCountryID();
		if(id == -1) {
			JOptionPane.showMessageDialog(null, "Please select a country first!");
		}
		else {
			if(map.getCountryByID(id).getAdjacentCountryList().size()==0) {
				JOptionPane.showMessageDialog(null, "There is no connection to delete! Choose another country");
			}
			
			else {
				ConnectionView addConnectionView = new ConnectionView(map,id,ConnectionView.DELETE_CONNECTION_OPTION);
				addConnectionView.setVisible(true);
			}
		}
	}
	
	public void editBasicInfo() {
		BasicInfoView view = new BasicInfoView(map);
		view.setVisible(true);
	}
	
	
	
}
