package redehexen.clanDominanceAlert.regions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import redehexen.clanDominanceAlert.teams.DominatingAlliance;
import redehexen.clanDominanceAlert.teams.Team;

class DomainRegionsDominatorTests {

	private static final String TESTING_TEAM_NAME = "TES";
	
	private static DomainRegion domainRegion;
	
	@Before
	void setUp() {
		domainRegion = new DomainRegion();
	}
	
	@Test
	void testSingleClanSinglePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME, new ArrayList<String>());
		
		domainRegion.playerEntered(team1);
		
		List<String> expectedWinners = new ArrayList<String>();
		DominatingAlliance expectedAlliance = new DominatingAlliance(TESTING_TEAM_NAME, expectedWinners, 1);
		
		System.out.println(expectedAlliance.getTeamsNames());
		System.out.println(domainRegion.getWinning().getTeamsNames());
		System.out.println("expected: " + expectedAlliance.getTotalMembers() + " / got: " + domainRegion.getWinning().getTotalMembers());
		
		assertTrue(domainRegion.getWinning().equals(expectedAlliance));
	}

}
