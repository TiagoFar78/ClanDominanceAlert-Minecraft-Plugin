package redehexen.clanDominanceAlert.managers;

import java.util.List;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import redehexen.clanDominanceAlert.teams.Team;

public class TeamsManager {
	
	private static SimpleClans simpleClans = SimpleClans.getInstance();
	
	public static Team getTeam(String playerName) {
		Clan clan = simpleClans.getClanManager().getClanByPlayerName(playerName);
		if (clan == null) {
			return null;
		}
		
		return new Team(clan.getTag(), clan.getAllies());
	}
	
	public static List<String> getAlliesNames(String tag) {
		Clan clan = simpleClans.getClanManager().getClan(tag);
		if (clan == null) {
			return null;
		}
		
		return clan.getAllies();
	}

}
