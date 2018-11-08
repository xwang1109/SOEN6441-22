package views.game;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import models.game.GameState;
import models.game.Player;
import models.map.Continent;
import models.map.Country;
import models.map.Map;

public class PlayerWorldDomainView extends JScrollPane implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2418047363323959194L;
	private JTable table;

	public PlayerWorldDomainView() {
		
		table = new JTable();
		add(table);
		addWorldDomainTable(GameState.getInstance());
	}
	
	public JTable getWorldDomainTable() {
		return this.table;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		this.remove(table);
		this.add(table);
		addWorldDomainTable(GameState.getInstance());
		
		this.revalidate();
		this.repaint();
		StateView.getInstance().getContentPane().add(this, BorderLayout.EAST);
	
	}
	/**
	 * this method will create a table to show player world domination info
	 * @param gs
	 */
	public void addWorldDomainTable(GameState gs) {
		
		String[] columnNames = { "Player","Map Controll Percentage", "Continents Controlled", "Number of armies" }; 
		
		Map map = gs.getMap();
		int totalCountries = map.getCountryNumber();
		
		ArrayList<Player> playerList = gs.getPlayerList();
		String[][] mapData = new String[playerList.size()][4];
		
		for(int i=0;i<playerList.size();i++) {
			Player player = playerList.get(i);
			mapData[i][0] = "Player"+player.getId();
			int numControlledCountries = player.getCountryList().size();
			double controllP = numControlledCountries*100/totalCountries;
			DecimalFormat df = new DecimalFormat("#.##");
			mapData[i][1] = df.format(controllP)+"%";
			String controlledContinent = "";
			for(Continent continent:gs.getMap().getContinentList()) {
				if(continent.getOwner()!=null && continent.getOwner().getId() == player.getId()) {
					controlledContinent += continent.getName()+", ";
				}
			}
			mapData[i][2] = controlledContinent;
			mapData[i][3] = ""+player.getArmyNumber();
		}
		JTable worldDomainTable = new JTable(mapData, columnNames);
		
		worldDomainTable.setBounds(30, 40, 200, 300); 
		worldDomainTable.setRowHeight(40);
		worldDomainTable.setFont(new Font("Serif", Font.BOLD, 20));
		this.table = worldDomainTable;
		this.getViewport().add(table);  
		table.setDefaultEditor(Object.class, null);
	}
}
