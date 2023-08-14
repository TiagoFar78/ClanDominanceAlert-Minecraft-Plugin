package redehexen.clanDominanceAlert.teams;

import java.util.ArrayList;
import java.util.List;

public class Alliance {
	
	private List<String> _alliedTeams = new ArrayList<String>();
	private int _totalMembers = 0;
	
	public void addTeam(String teamName) {
		_alliedTeams.add(teamName);
	}
	
	public List<String> getAllianceMembers() {
		return _alliedTeams;
	}
	
	public void addPlayer() {
		_totalMembers++;
	}
	
	public void removePlayer() {
		_totalMembers--;
	}
	
	public int getTotalMembers() {
		return _totalMembers;
	}

}
