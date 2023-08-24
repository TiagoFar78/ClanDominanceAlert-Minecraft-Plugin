package redehexen.clanDominanceAlert.regions;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import redehexen.clanDominanceAlert.ClanDominanceAlert;
import redehexen.clanDominanceAlert.managers.ConfigManager;
import redehexen.clanDominanceAlert.managers.TeamsManager;
import redehexen.clanDominanceAlert.teams.Team;

public class DominatorAnnouncer {
	
	private static int TICKS_IN_SECOND = 20;
	
	private List<Team> _alliance;
	private String _regionName;
	private BukkitTask _task;
	private int _announcesAmount = 0;
	
	public DominatorAnnouncer(List<Team> alliance, String regionName) {
		_alliance = alliance;
		_regionName = regionName;
		
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
				
				boolean isSingleDominator = _alliance.size() > 1;
				String dominatingMessage = !isSingleDominator ? configManager.getDominatingMessageSingular() : configManager.getDominatingMessagePlural();
				
				int totalMembers = Team.getTeamsTotalMembers(_alliance);
				
				String allianceClans = formatClansList(configManager);
				int dominationMinutes = announcementDelay * _announcesAmount / 60;
				
				dominatingMessage = dominatingMessage.replace("{AREA}", _regionName);
				dominatingMessage = dominatingMessage.replace("{TIME}", Integer.toString(dominationMinutes));
				dominatingMessage = dominatingMessage.replace("{MEMBERS}", Integer.toString(totalMembers));
				dominatingMessage = dominatingMessage.replace("{CLAN}", allianceClans);
				
				Bukkit.broadcastMessage(dominatingMessage);
				
				scheduleAnnouncement();
			}
			
		}, announcementDelay * TICKS_IN_SECOND);
		
	}
	
	private String formatClansList(ConfigManager configManager) {
		String teamJoiner = configManager.getDominatingTeamJoinerMessage();
		String lastJoiner = configManager.getDominatingLastJoinerMessage();
		
		List<String> teamsNames = _alliance.stream().map(team -> TeamsManager.getColoredTag(team.getName())).collect(Collectors.toList());
		int size = _alliance.size();
		
		if (size == 1) {
			return teamsNames.get(0);
		}
		
		String result = "";
		for (int i = 0; i < size - 2; i++) {
			result += teamsNames.get(i) + teamJoiner;
		}
		
		result += teamsNames.get(size - 2) + lastJoiner + teamsNames.get(size - 1);
		
		return result;
	}

}
