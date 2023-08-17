package redehexen.clanDominanceAlert.regions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Location;

import redehexen.clanDominanceAlert.managers.TeamsManager;
import redehexen.clanDominanceAlert.teams.Team;

public class DomainRegion {
	
	private String _name;
	private Region _region;
	private DominatorAnnouncer _announcer = null;
	private List<Team> _teams = new ArrayList<Team>();
	private List<Team> _currentDominators = new ArrayList<Team>();
	
	public DomainRegion() {
		// TESTING ONLY
	}
	
//	>-------------------------------------{ Region }-------------------------------------<
	
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
	
//	>--------------------------------------{ Team }--------------------------------------<
	
	private boolean alreadyExists(Team team) {
		for (Team savedTeam : _teams) {
			if (savedTeam.getName().equals(team.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	private void createTeam(Team team) {
		_teams.add(team);
	}
	
	private void addPlayerToTeam(Team team) {
		team.addPlayer();
	}
	
	private void removePlayerFromTeam(Team team) {
		team.removePlayer();
	}
	
	private boolean isTeamExtint(Team team) {
		return team.getPlayers() == 0;
	}
	
	private void deleteTeam(Team team) {
		for (int i = 0; i < _teams.size(); i++) {
			if (_teams.get(i).getName().equals(team.getName())) {
				_teams.remove(i);
				return;
			}
		}
	}
	
//	>-------------------------------------{ Player }-------------------------------------<
	
	public void playerEntered(Team team) {
		if (!alreadyExists(team)) {
			createTeam(team);
		}
		
		addPlayerToTeam(team);
		
		setNewDominator();
	}
	
	public void playerLeft(Team team) {
		removePlayerFromTeam(team);
		
		if (isTeamExtint(team)) {
			deleteTeam(team);
		}
		
		setNewDominator();
	}
	
//	>-------------------------------------{ Allies }-------------------------------------<
	
	public void updateAllies() {
		setNewDominator();
	}

//	>---------------------------------{ New Dominator }---------------------------------<
	
	private List<Team> calculateNewDominator() {
		if (_teams.size() == 0) {
			return _teams;
		}
		
		List<Team> bestAlliance = null;
		int maxMembers = 0;
		
		for (List<Team> alliance : getAlliances()) {
			int allianceMembers = Team.getTeamsTotalMembers(alliance);
			if (allianceMembers > maxMembers) {
				bestAlliance = alliance;
				maxMembers = allianceMembers;
			}
		}
		
		return bestAlliance;
	}
	
	private List<List<Team>> getAlliances() {
		return getAlliancesAux(0, new ArrayList<Team>());
	}
	
	private List<List<Team>> getAlliancesAux(int firstIndex, List<Team> currentAlliance) {
		List<List<Team>> alliances = new ArrayList<List<Team>>();
		if (currentAlliance.size() != 0) {
			alliances.add(currentAlliance);
		}
		
		for (int i = firstIndex; i < _teams.size(); i++) {
			Team team = _teams.get(i);
			
			if (isAlliedToEveryTeam(currentAlliance, team)) {
				List<Team> newCurrentAlliance = new ArrayList<Team>(currentAlliance);
				newCurrentAlliance.add(team);
				
				alliances.addAll(getAlliancesAux(i + 1, newCurrentAlliance));
			}
		}
		
		return alliances;
	}
	
	private boolean isAlliedToEveryTeam(List<Team> alliedTeams, Team team) {
		List<String> alliesNames = TeamsManager.getAlliesNames(team.getName());
		List<String> alliedTeamsNames = alliedTeams.stream().map(Team::getName).collect(Collectors.toList());
		
		return alliesNames.containsAll(alliedTeamsNames);
	}
	
	private boolean isSameDominator(List<Team> newDominators) {
		if (_currentDominators.size() == 0) {
			return newDominators.size() == 0;
		}
		
		if (newDominators.size() == 0) {
			return false;
		}
		
		return _currentDominators.size() == newDominators.size() && _currentDominators.containsAll(newDominators);
	}
	
	private void setNewDominator() {
		setNewDominator(calculateNewDominator());
	}
	
	private void setNewDominator(List<Team> alliance) {
		if (isSameDominator(alliance)) {
			return;
		}
		
		_currentDominators = alliance;
		
		if (_announcer != null) {
			_announcer.cancel();
		}
		
		if (alliance.size() > 0) {
			_announcer = new DominatorAnnouncer(alliance, _name);
		}
	}

//	>------------------------------------{ Testing }------------------------------------<
	
	public List<Team> getDominatingTeams() {
		return _currentDominators;
	}
	
}
