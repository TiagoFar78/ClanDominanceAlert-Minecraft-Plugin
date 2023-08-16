package redehexen.clanDominanceAlert.teams;

public class Team {
	
	private String _name;
	private int _totalPlayers = 0;
	
	public Team(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	public int getPlayers() {
		return _totalPlayers;
	}
	
	public void addPlayer() {
		_totalPlayers++;
	}
	
	public void removePlayer() {
		_totalPlayers--;
	}

}
