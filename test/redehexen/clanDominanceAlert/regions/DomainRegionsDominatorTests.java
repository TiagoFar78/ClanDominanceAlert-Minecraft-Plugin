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
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testSingleClanMultiplePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testMultipleClanSinlePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		Team team2 = new Team(TESTING_TEAM_NAME + "2");
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testMultipleClanMultiplePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		Team team2 = new Team(TESTING_TEAM_NAME + "2");
		Team team3 = new Team(TESTING_TEAM_NAME + "3");
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME + "3");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME + "3");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team3);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "3");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "3");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testMultipleAlliedClansSinlePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		Team team2 = new Team(TESTING_TEAM_NAME + "2");
		Team team3 = new Team(TESTING_TEAM_NAME + "3");
		
		List<String> allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME + "3");
		TeamsManager.addAllies(TESTING_TEAM_NAME, allies);
		allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME);
		TeamsManager.addAllies(TESTING_TEAM_NAME + "3", allies);
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerEntered(TESTING_TEAM_NAME + "3");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		expectedWinners.add(team3);
		
		System.out.println(expectedWinners);
		System.out.println(domainRegion.getDominatingTeams());
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "2");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team3);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "3");
		
		expectedWinners = new ArrayList<Team>();
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testMultipleAlliedClansMultiplePlayer() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		Team team2 = new Team(TESTING_TEAM_NAME + "2");
		
		List<String> allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME + "2");
		allies.add(TESTING_TEAM_NAME + "3");
		TeamsManager.addAllies(TESTING_TEAM_NAME, allies);
		allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME);
		TeamsManager.addAllies(TESTING_TEAM_NAME + "3", allies);
		TeamsManager.addAllies(TESTING_TEAM_NAME + "2", allies);
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		domainRegion.playerEntered(TESTING_TEAM_NAME + "3");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		
		List<Team>expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		expectedWinners.add(team2);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}
	
	@Test
	void testMultipleAlliedClansMultiplePlayerComplex() {
		setUp();
		
		Team team1 = new Team(TESTING_TEAM_NAME);
		Team team2 = new Team(TESTING_TEAM_NAME + "2");
		Team team3 = new Team(TESTING_TEAM_NAME + "3");
		Team team4 = new Team(TESTING_TEAM_NAME + "4");
		
		List<String> allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME + "2");
		allies.add(TESTING_TEAM_NAME + "3");
		allies.add(TESTING_TEAM_NAME + "4");
		TeamsManager.addAllies(TESTING_TEAM_NAME, allies);
		allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME);
		allies.add(TESTING_TEAM_NAME + "3");
		allies.add(TESTING_TEAM_NAME + "4");
		TeamsManager.addAllies(TESTING_TEAM_NAME + "2", allies);
		allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME);
		allies.add(TESTING_TEAM_NAME + "2");
		TeamsManager.addAllies(TESTING_TEAM_NAME + "3", allies);
		allies = new ArrayList<String>();
		allies.add(TESTING_TEAM_NAME);
		allies.add(TESTING_TEAM_NAME + "2");
		TeamsManager.addAllies(TESTING_TEAM_NAME + "4", allies);
		
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		domainRegion.playerEntered(TESTING_TEAM_NAME);
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "2");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "3");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "4");
		domainRegion.playerEntered(TESTING_TEAM_NAME + "4");
		
		List<Team> expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		expectedWinners.add(team2);
		expectedWinners.add(team4);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		domainRegion.playerLeft(TESTING_TEAM_NAME);
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		expectedWinners.add(team2);
		expectedWinners.add(team4);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
		
		domainRegion.playerLeft(TESTING_TEAM_NAME + "4");
		domainRegion.playerLeft(TESTING_TEAM_NAME + "4");
		
		expectedWinners = new ArrayList<Team>();
		expectedWinners.add(team1);
		expectedWinners.add(team2);
		expectedWinners.add(team3);
		
		assertTrue(areSameAlliances(domainRegion.getDominatingTeams(), expectedWinners));
	}

}
