package redehexen.clanDominanceAlert.managers;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import redehexen.clanDominanceAlert.teams.Team;

public class TeamsManager {
	
	private static SimpleClans simpleClans = SimpleClans.getInstance();
	
	public static Team getTeam(String playerName) {
		Clan clan = simpleClans.getClanManager().getClanByPlayerName(playerName);
		
		return new Team(clan.getTag(), clan.getAllies());
	}

}
