package redehexen.clanDominanceAlert.regions;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import redehexen.clanDominanceAlert.ClanDominanceAlert;
import redehexen.clanDominanceAlert.managers.ConfigManager;
import redehexen.clanDominanceAlert.teams.Alliance;

public class DominatorAnnouncer {
	
	private static int TICKS_IN_SECOND = 20;
	
	private Alliance _alliance;
	private String _regionName;
	private BukkitTask _task;
	private int _announcesAmount = 0;
	
	public DominatorAnnouncer(Alliance alliance, String regionName) {
		_alliance = alliance;
		
		scheduleAnnouncement();
	}
	
	public void cancel() {
		_task.cancel();
	}
	
	public void scheduleAnnouncement() {
		ConfigManager configManager = ConfigManager.getInstance();
		
		int announcementDelay = configManager.getDominationAlertDelay();		
		
		_task = Bukkit.getScheduler().runTaskLater(ClanDominanceAlert.getClanDominanceAlert(), new Runnable() {
			
			@Override
			public void run() {
				_announcesAmount++;
				
				boolean hasAllies = _alliance.hasAllies();
				String dominatingMessage = hasAllies ? configManager.getDominatingMessageSingular() : configManager.getDominatingMessagePlural();
				
				String allianceClans = getAllianceClans(configManager);
				int dominationMinutes = announcementDelay * _announcesAmount / 60;
				
				dominatingMessage = dominatingMessage.replace("{AREA}", _regionName);
				dominatingMessage = dominatingMessage.replace("{TIME}", Integer.toString(dominationMinutes));
				dominatingMessage = dominatingMessage.replace("{MEMBERS}", Integer.toString(_alliance.getTotalMembers()));
				dominatingMessage = dominatingMessage.replace("{CLAN}", allianceClans);
				
				Bukkit.broadcastMessage(dominatingMessage);
				
				scheduleAnnouncement();
			}
			
		}, announcementDelay * TICKS_IN_SECOND);
		
	}
	
	private String getAllianceClans(ConfigManager configManager) {
		String teamJoiner = configManager.getDominatingTeamJoinerMessage();
		String lastJoiner = configManager.getDominatingLastJoinerMessage();
		
		List<String> alliedTeams = _alliance.getAllianceMembers();
		
		String result = String.join(teamJoiner, alliedTeams);
		if (!alliedTeams.isEmpty()) {
			result += lastJoiner;
		}
		
		result += _alliance.getFounderName();
		
		return result;
	}

}
