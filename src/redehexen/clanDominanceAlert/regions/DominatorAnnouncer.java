package redehexen.clanDominanceAlert.regions;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import redehexen.clanDominanceAlert.ClanDominanceAlert;
import redehexen.clanDominanceAlert.managers.ConfigManager;
import redehexen.clanDominanceAlert.teams.Alliance;

public class DominatorAnnouncer {
	
	private static int TICKS_IN_SECOND = 20;
	
	private Alliance _alliance;
	private BukkitTask _task;
	private int _announcesAmount = 0;
	
	public DominatorAnnouncer(Alliance alliance) {
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
				
				
			}
		}, announcementDelay * TICKS_IN_SECOND);
		
	}

}
