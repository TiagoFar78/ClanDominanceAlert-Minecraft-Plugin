package redehexen.clanDominanceAlert.regions;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Location;

import redehexen.clanDominanceAlert.managers.TeamsManager;
import redehexen.clanDominanceAlert.teams.Alliance;
import redehexen.clanDominanceAlert.teams.DominatingAlliance;
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
		System.out.println("Player enter for team: " + team.getName());
		
		if (!alreadyExists(team)) {
			createTeam(team);
		}
		
		addPlayerToTeam(team);
		
		setNewDominator();
	}
	
	public void playerLeft(Team team) {
		System.out.println("Player left for team: " + team.getName());
		
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
			return null;
		}
		
		
		
		return bestAlliance;
	}
	
	private List<Team> getMostPopulatedTeamAlliance(List<Team> fixedTeams, int firstIndex) {
		List<Team> mostPopulatedTeams = new ArrayList<Team>();
		int mostPlayers = 0;
		
		List<Team> newFixedTeams = new ArrayList<Team>(fixedTeams);
		
		boolean addedANewTeam = false;
		for (int i = firstIndex; i < _teams.size(); i++) {
			Team team = _teams.get(i);
			
			if (isAlliedToEveryTeam(newFixedTeams, team)) {
				newFixedTeams.add(team);
				addedANewTeam = true;
				firstIndex = i;
			}
		}
		
		if (!addedANewTeam) {
			return newFixedTeams;
		}
		
		return getMostPopulatedTeamAlliance(newFixedTeams, firstIndex);
	}
	
	private boolean isAlliedToEveryTeam(List<Team> alliedTeams, Team team) {
		List<String> alliesNames = TeamsManager.getAlliesNames(team.getName());
		List<String> alliedTeamsNames = alliedTeams.stream().map(Team::getName).toList();
		
		return alliesNames.containsAll(alliedTeamsNames);
	}
	
	private boolean isSameDominator(Alliance newDominator) {
		if (_currentDominator == null) {
			return newDominator == null;
		}
		
		if (newDominator == null) {
			return false;
		}
		
		List<String> currentDominatorAlliance = new ArrayList<String>(_currentDominator.getAllianceMembers());
		currentDominatorAlliance.add(_currentDominator.getFounderName());
		
		List<String> newDominatorAlliance = new ArrayList<String>(newDominator.getAllianceMembers());
		newDominatorAlliance.add(newDominator.getFounderName());
		
		return currentDominatorAlliance.containsAll(newDominatorAlliance);
	}
	
	private void setNewDominator() {
		setNewDominator(calculateNewDominator());
	}
	
	private void setNewDominator(Alliance alliance) {
		System.out.println("New dominator: " + (alliance == null ? "null" : alliance.getFounderName()) + " / Prev : " + (_currentDominator == null ? "null" : _currentDominator.getFounderName()));
		if (isSameDominator(alliance)) {
			return;
		}
		
		_currentDominator = alliance;
		
		if (_announcer != null) {
			_announcer.cancel();
		}
		
		if (alliance != null) {
			//_announcer = new DominatorAnnouncer(alliance, _name);
			System.out.println("schedulou o announcer");
		}
	}
	
	public DominatingAlliance getWinning() {
		if (_currentDominator == null) {
			return null;
		}
		
		return new DominatingAlliance(_currentDominator.getFounderName(), _currentDominator.getAllianceMembers(), _currentDominator.getTotalMembers());
	}
	
}
