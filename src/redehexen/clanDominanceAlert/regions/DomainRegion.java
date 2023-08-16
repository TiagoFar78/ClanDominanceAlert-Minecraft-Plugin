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
		
		return getMostPopulatedTeamAlliance();
	}
	
	private List<Team> getMostPopulatedTeamAlliance() {
		return getMostPopulatedTeamAllianceAux(new ArrayList<Team>(), 0, new ArrayList<Team>());
	}
	
	private List<Team> getMostPopulatedTeamAllianceAux(List<Team> fixedTeams, int firstIndex, List<Team> mostPopulatedAlliance) {		
		List<Team> newFixedTeams = new ArrayList<Team>(fixedTeams);
		
		boolean addedANewTeam = false;
		for (int i = firstIndex; i < _teams.size(); i++) {
			Team team = _teams.get(i);
			
			if (isAlliedToEveryTeam(newFixedTeams, team)) {
				newFixedTeams.add(team);
				addedANewTeam = true;
				firstIndex = i;
				break;
			}
		}
		
		if (Team.getTeamsTotalMembers(newFixedTeams) > Team.getTeamsTotalMembers(mostPopulatedAlliance)) {
			mostPopulatedAlliance = fixedTeams;
		}
		
		if (!addedANewTeam) {
			return mostPopulatedAlliance;
		}
		
		return getMostPopulatedTeamAllianceAux(newFixedTeams, firstIndex, mostPopulatedAlliance);
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
		
		if (alliance != null) {
			//_announcer = new DominatorAnnouncer(alliance, _name);
			System.out.println("schedulou o announcer");
		}
	}
	
	public List<Team> getDominatingTeams() {
		return _currentDominators;
	}
	
}
