package redehexen.clanDominanceAlert.teams;

import java.util.List;

public class Team {
	
	private String _name;
	private int _totalPlayers = 0;
	
	public Team(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	public int getPlayers() {
		return _totalPlayers;
	}
	
	public void addPlayer() {
		_totalPlayers++;
	}
	
	public void removePlayer() {
		_totalPlayers--;
	}
	
	public static int getTeamsTotalMembers(List<Team> teams) {
		int totalMembers = 0;
		
		for (Team team : teams) {
			totalMembers += team.getPlayers();
		}
		
		return totalMembers;
	}

}
