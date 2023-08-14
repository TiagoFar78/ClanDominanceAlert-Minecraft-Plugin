package redehexen.clanDominanceAlert.teams;

import java.util.ArrayList;
import java.util.List;

public class Alliance {
	
	private String _founderTeamName;
	private int _founderTeamMembers = 0;
	private List<String> _alliedTeams = new ArrayList<String>();
	private int _alliedTeamsMembers = 0;
	
	public Alliance(String founderTeamName) {
		_founderTeamName = founderTeamName;
	}
	
	public String getFounderName() {
		return _founderTeamName;
	}
	
	public boolean addTeam(String teamName) {
		return _alliedTeams.add(teamName);
	}
	
	public boolean removeTeam(String removeTeam) {
		return _alliedTeams.remove(removeTeam);
	}
	
	public boolean isAlliedTo(String teamName) {
		return _alliedTeams.contains(teamName);
	}
	
	public List<String> getAllianceMembers() {
		return _alliedTeams;
	}
	
	public void addPlayersFromAlliedTeam(int alliedTeamMembers) {
		_alliedTeamsMembers += alliedTeamMembers;
	}
	
	public void addPlayerFromAlliedTeam() {
		_alliedTeamsMembers++;
	}
	
	public void removePlayersFromAlliedTeam(int alliedTeamMembers) {
		_alliedTeamsMembers -= alliedTeamMembers;
	}
	
	public void removePlayerFromAlliedTeam() {
		_alliedTeamsMembers--;
	}
	
	public void addPlayerFromFounder() {
		_founderTeamMembers++;
	}
	
	public void removePlayerFromFounder() {
		_founderTeamMembers--;
	}
	
	public int getTotalMembers() {
		return _alliedTeamsMembers + _founderTeamMembers;
	}
	
	public int getFounderMembers() {
		return _founderTeamMembers;
	}
	
	public boolean founderTeamIsAlive() {
		return _founderTeamMembers != 0;
	}

}
