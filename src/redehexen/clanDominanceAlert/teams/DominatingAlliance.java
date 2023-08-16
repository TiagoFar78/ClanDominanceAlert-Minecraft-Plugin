package redehexen.clanDominanceAlert.teams;

import java.util.List;

public class DominatingAlliance {
	
	private List<String> _teamsNames;
	private int _totalMembers;

	public DominatingAlliance(List<String> teamsNames, int totalMembers) {
		_teamsNames = teamsNames;
		_totalMembers = totalMembers;
	}
	
	public List<String> getTeamsNames() {
		return _teamsNames;
	}
	
	public int getTotalMembers() {
		return _totalMembers;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof DominatingAlliance)) {
			return false;
		}
		
		DominatingAlliance other = (DominatingAlliance) o;
		System.out.println("Sizes: " + other.getTeamsNames().size() + " " + getTeamsNames().size());
		System.out.println("Lists");
		System.out.println(other.getTeamsNames());
		System.out.println(getTeamsNames());
		System.out.println("TotalMembers: " + other.getTotalMembers() + " " + getTotalMembers());
		
		return other.getTeamsNames().size() == getTeamsNames().size() && 
				other.getTeamsNames().containsAll(getTeamsNames()) && other.getTotalMembers() == getTotalMembers();
	}
	
}
