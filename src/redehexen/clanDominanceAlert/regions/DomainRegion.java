package redehexen.clanDominanceAlert.regions;

import java.util.Hashtable;

import org.bukkit.Location;

import redehexen.clanDominanceAlert.teams.Alliance;
import redehexen.clanDominanceAlert.teams.Team;

public class DomainRegion {
	
	private String _name;
	private Region _region;
	private DominatorAnnouncer _announcer;
	private Hashtable<String, Alliance> _alliances;
	private Alliance _currentDominator;
	
	public DomainRegion(String name, Location loc1, Location loc2) {
		_name = name;
		_region = new Region(loc1, loc2);
	}
	
	public String getName() {
		return _name;
	}
	
	public boolean isOnRegion(Location loc) {
		return _region.isOnRegion(loc);
	}
	
	public void playerEntered(Team team) {
		String teamName = team.getName();
		
		if (!_alliances.contains(teamName)) {
			Alliance alliance = new Alliance(teamName);
			_alliances.put(teamName, alliance);
			
			for (String alliedName : team.getAlliedTeams()) {
				if (_alliances.containsKey(alliedName)) {
					alliance.addPlayersFromAlliedTeam(_alliances.get(alliedName).getFounderMembers());
				}
			}
		}
		
		_alliances.get(teamName).addPlayerFromFounder();
		
		for (String alliedName : team.getAlliedTeams()) {
			if (_alliances.containsKey(alliedName)) {
				_alliances.get(alliedName).addPlayerFromAlliedTeam();
			}
		}
		
		setNewDominator();
	}
	
	public void playerLeft(Team team) {
		Alliance alliance = _alliances.get(team.getName());
		
		alliance.removePlayerFromFounder();
		
		if (!alliance.founderTeamIsAlive()) {
			for (String alliedName : alliance.getAllianceMembers()) {
				_alliances.get(alliedName).removeTeam(alliedName);
			}
			
			_alliances.remove(team.getName());
		}
		
		for (String alliedName : team.getAlliedTeams()) {
			if (_alliances.containsKey(alliedName)) {
				_alliances.get(alliedName).removePlayerFromAlliedTeam();
			}
		}
		
		setNewDominator();
	}
	
	public void removeAlliance(String team1Name, String team2Name) {
		Alliance team1Alliance = _alliances.get(team1Name);
		Alliance team2Alliance = _alliances.get(team2Name);
		
		team1Alliance.removeTeam(team2Name);
		team2Alliance.removeTeam(team1Name);
		
		team1Alliance.removePlayersFromAlliedTeam(team2Alliance.getFounderMembers());
		team2Alliance.removePlayersFromAlliedTeam(team1Alliance.getFounderMembers());		
		
		setNewDominator();
	}
	
	public void addAlliance(String team1Name, String team2Name) {
		Alliance team1Alliance = _alliances.get(team1Name);
		Alliance team2Alliance = _alliances.get(team2Name);
		
		team1Alliance.addTeam(team2Name);
		team2Alliance.addTeam(team1Name);
		
		team1Alliance.addPlayersFromAlliedTeam(team2Alliance.getFounderMembers());
		team2Alliance.addPlayersFromAlliedTeam(team1Alliance.getFounderMembers());
		
		setNewDominator();
	}
	
	private Alliance calculateNewDominator() {
		if (_alliances.size() == 0) {
			return null;
		}
		
		Alliance bestAlliance = _currentDominator;
		for (Alliance alliance : _alliances.values()) {
			if (alliance.getTotalMembers() > bestAlliance.getTotalMembers()) {
				bestAlliance = alliance;
			}
		}
		
		return bestAlliance;
	}
	
	private boolean isSameDominator(Alliance newDominator) {
		if (_currentDominator == null) {
			return newDominator != null;
		}
		
		return _currentDominator.getAllianceMembers().containsAll(newDominator.getAllianceMembers());
	}
	
	private void setNewDominator() {
		setNewDominator(calculateNewDominator());
	}
	
	private void setNewDominator(Alliance alliance) {
		if (isSameDominator(alliance)) {
			return;
		}
		
		_currentDominator = alliance;
		_announcer.cancel();
		
		if (alliance != null) {
			_announcer = new DominatorAnnouncer(alliance);
		}
	}
	
}
