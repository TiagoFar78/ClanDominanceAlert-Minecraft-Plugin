package redehexen.clanDominanceAlert.regions;

import redehexen.clanDominanceAlert.teams.Alliance;

public class DominatorAnnouncer {
	
	private Alliance _alliance;
	private boolean _isCancelled;
	
	public DominatorAnnouncer(Alliance alliance) {
		_alliance = alliance;
		
		scheduleAnnouncement();
	}
	
	public void cancel() {
		_isCancelled = true;
	}
	
	public void scheduleAnnouncement() {
		
	}

}
