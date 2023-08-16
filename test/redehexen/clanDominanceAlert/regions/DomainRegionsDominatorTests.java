package redehexen.clanDominanceAlert.regions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import redehexen.clanDominanceAlert.managers.TeamsManager;
import redehexen.clanDominanceAlert.teams.Team;

class DomainRegionsDominatorTests {

	private static final String TESTING_TEAM_NAME = "TES";
	
	private static DomainRegion domainRegion;
	
	private boolean areSameAlliances(List<Team> alliance1, List<Team> alliance2) {
		return alliance1.containsAll(alliance2) && alliance1.size() == alliance2.size();
	}
	
	
	@Before
	void setUp() {
		domainRegion = new DomainRegion();
		
		TeamsManager.clear();
	}
	
	@Test
	void testSingleClanSinglePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		
		domainRegion.playerEntered(team1);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}

}
