package redehexen.clanDominanceAlert.teams;

import java.util.List;

public class Team {
	
	private String _name;
	private List<String> _alliesNames;
	
	public Team(String name, List<String> alliesNames) {
		_name = name;
		_alliesNames = alliesNames;
	}
	
	public String getName() {
		return _name;
	}
	
	public List<String> getAlliedTeams() {
		return _alliesNames;
	}
	
	public boolean isAllied(String teamName) {
		return _alliesNames.contains(teamName);
	}

}
