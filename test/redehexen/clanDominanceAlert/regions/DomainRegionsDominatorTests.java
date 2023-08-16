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
		
		domainRegion.playerLeft(team1);
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testSingleClanMultiplePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		
		domainRegion.playerEntered(team1);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(team1);
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testMultipleClanSinlePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		Team team2 = new Team(TESTING_TEAM_NAME + "2");
		
		domainRegion.playerEntered(team1);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(team2);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(team1);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(team2);
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}

}
