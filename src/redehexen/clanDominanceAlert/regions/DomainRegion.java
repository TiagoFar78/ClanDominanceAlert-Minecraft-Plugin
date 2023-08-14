package redehexen.clanDominanceAlert.regions;

import java.util.Hashtable;

import org.bukkit.Location;

import redehexen.clanDominanceAlert.teams.Alliance;
import redehexen.clanDominanceAlert.teams.Team;

public class DomainRegion {
	
	private String _name;
	private Region _region;
	private DominatorAnnouncer _announcer;
	private Hashtable<String, Integer> _teamMembersAmount;
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
		// cria alianca nova e recolhe as equipas aliadas e adiciona os jogadores à aliança se for preciso
		
		// adiciona um a equipa
		
		// adiciona às alianças das equipas aliadas 1
		
	}
	
	public void playerLeft(Team team) {
		// remove um da equipa
		
		// apaga a equipa das equipas aliadas se for team == 0
		
		// remove um da aliança
		
		// remove um de todos os aliados
		
	}
	
	public void removeAlliance(String team1Name, String team2Name) {
		// remove a aliaca
		
		// remove os membros de cada equipa da sua alianca
		
	}
	
	public void addAlliance(String team1Name, String team2Name) {
		// adiciona a alianca
		
		// adiciona o numero de membros da equipa aliada a ambas as aliancas
	}
	
	private void calculateNewDominator() {
		// fazer uma lista ordenada das alliances e usar a primeira
	}
	
	private boolean isDifferentDominator(Alliance newDominator) {
		return !_currentDominator.getAllianceMembers().containsAll(newDominator.getAllianceMembers());
	}
	
	private void newDominator(Alliance alliance) {
		// se for o dominator é para cagar
		
		// cancel announcer
		
		// create new announcer
		
		// muda o current dominator
	}
	
	

}
