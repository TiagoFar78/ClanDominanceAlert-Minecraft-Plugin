package redehexen.clanDominanceAlert.managers;

import java.util.Hashtable;
import java.util.List;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class TeamsManager {
	
	private static SimpleClans simpleClans = SimpleClans.getInstance();
	
	public static String getTeam(String playerName) {
		Clan clan = simpleClans.getClanManager().getClanByPlayerName(playerName);
		if (clan == null) {
			return null;
		}
		
		return clan.getTag();
	}
	
	public static List<String> getAlliesNames(String tag) {
//		if (allies != null) {
//			return allies.get(tag) == null ? new ArrayList<String>() : allies.get(tag);
//		}
		
		Clan clan = simpleClans.getClanManager().getClan(tag);
		if (clan == null) {
			return null;
		}
		
		return clan.getAllies();
	}
	
	public static String getColoredTag(String tag) {
		Clan clan = simpleClans.getClanManager().getClan(tag);
		if (clan == null) {
			return null;
		}
		
		return clan.getColorTag();
	}
	
//	TESTING ONLY
	
	private static Hashtable<String, List<String>> allies = new Hashtable<String, List<String>>();
	
	public static void clear() {
		allies.clear();
	}
	
	public static void addAllies(String team, List<String> teamAllies) {
		allies.put(team, teamAllies);
	}

}
