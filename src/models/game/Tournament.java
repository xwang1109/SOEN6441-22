package models.game;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import models.game.GameState.Phase;
import models.map.Map;
import views.game.StateView;
public class Tournament {

	
	List<Map> maps;
	List<Player> players;
	int numOfGames;
	int turns;
	
	String [][] results;
	
	
	public List<Map> getMaps() {
		return maps;
	}
	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getNumOfGames() {
		return numOfGames;
	}
	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
	
	public Tournament(List<Map> maps, List<Player> players, int numOfGames, int turns) {
		super();
		this.maps = maps;
		this.players = players;
		this.numOfGames = numOfGames;
		this.turns = turns;
		results=new String[maps.size()][numOfGames];
	}
	
	public void run()
	{
		for(int m=0;m<maps.size();m++)
		{			
			Map map=maps.get(m);
			for(int i=0;i<this.numOfGames;i++)
			{
				GameState.getInstance().setPlayerList(players);  
				GameState.getInstance().setMap(map);
				
				GameState.getInstance().randomAssignCountry();
				GameState.getInstance().assignInitialArmy();
				
				for(Player p:players){
					GameState.getInstance().setPhase(Phase.SETUP);
					
					p.doStrategySetup();
					GameState.getInstance().endPlayerTurn();

				}
				
				boolean finished=false;
				
				for(int currentTurn=0;currentTurn<turns;currentTurn++)
				{
					for(int j=0;j<players.size();j++){
						Player p=players.get(j);
						GameState.getInstance().setPhase(Phase.REINFORCEMENT);
						p.doStrategyReinforcement();
						GameState.getInstance().setPhase(Phase.ATTACK);
						p.doStrategyAttack();
						if (GameState.getInstance().getMap().mapOwner(p)) {
							GameState.getInstance().setPhase(Phase.FINISHED);
							System.out.println("Player "+p.getId()+":"+p.getStrategy().toString()+" success");
							results[m][i]="Player "+p.getId()+":"+p.getStrategy().toString();
							finished=true;
							break;
						}
						else{
							GameState.getInstance().setPhase(Phase.FORTIFICATION);
							p.doStrategyfortification();
							GameState.getInstance().endPlayerTurn();
						}
					}
					if(finished){
						break;
					}
				}
				results[m][i]="Draw";
			}
		}

		report();
	}
	private void report()
	{
		   StringBuilder sb = new StringBuilder();
	        sb.append("<html><body><table border=1>");

	        sb.append("<tr>");
	        sb.append("<th>");
            sb.append("");
            sb.append("</th>");
	        for(int i=0;i<numOfGames;i++) {
	            sb.append("<th>");
	            sb.append("Game "+(i+1));
	            sb.append("</th>");
	        }
	        sb.append("</tr>");
	        
	        for(int m=0;m<maps.size();m++) {
	        	sb.append("<tr>");
	        	
	        	 sb.append("<td>");
	                sb.append("Map "+(m+1));
	                sb.append("</td>");
	                
	                
	            for (int j=0;j<numOfGames;j++) {
	                sb.append("<td>");
	                sb.append(results[m][j]);
	                sb.append("</td>");

	            }
	            sb.append("</tr>");
	        }
	        sb.append("</table>");
	        JLabel html = new JLabel(sb.toString());

	        JOptionPane.showMessageDialog(null, html);
	        
	}
	
	
}
