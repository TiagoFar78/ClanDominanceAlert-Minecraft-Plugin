package redehexen.clanDominanceAlert.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import redehexen.clanDominanceAlert.ClanDominanceAlert;
import redehexen.clanDominanceAlert.regions.DomainRegion;
import redehexen.clanDominanceAlert.teams.Team;

public class DomainRegionsManager {

	private static final String REGION_PREFIX = "Regions";
	
	private static List<DomainRegion> _regions = populateRegions();
	
	private static List<DomainRegion> populateRegions() {
		YamlConfiguration config = ClanDominanceAlert.getYamlConfiguration();
		
		List<DomainRegion> regionsList = new ArrayList<DomainRegion>();
		
		List<String> regionsPath = config.getKeys(true).stream().filter(p -> p.startsWith(REGION_PREFIX) && p.lastIndexOf(".") == REGION_PREFIX.length()).collect(Collectors.toList());
		
		for (String regionPath : regionsPath) {
			String regionName = regionPath.substring(REGION_PREFIX.length());
			Location loc1 = getLocationFromConfig(config, regionPath, "1");
			Location loc2 = getLocationFromConfig(config, regionPath, "2");
			
			regionsList.add(new DomainRegion(regionName, loc1, loc2));
		}
		
		return regionsList;
	}
	
	public static void reload() {
		_regions = populateRegions();
	}
	
	private static Location getLocationFromConfig(YamlConfiguration config, String path, String locationIndex) {
		String worldName = config.getString(path + ".World");
		int x = config.getInt(path + ".Loc" + locationIndex + ".X");
		int y = config.getInt(path + ".Loc" + locationIndex + ".Y");
		int z = config.getInt(path + ".Loc" + locationIndex + ".Z");
		
		return new Location(Bukkit.getWorld(worldName), x, y, z);
	}
	
	public static List<DomainRegion> getRegions() {
		return _regions;
	}
	
	public static void detectMovement(Location locFrom, Location locTo, String playerName) {
		if (locFrom.getBlockX() == locTo.getBlockX() && locFrom.getBlockY() == locTo.getBlockY() &&
				locFrom.getBlockZ() == locTo.getBlockZ()) {
			return;
		}
		
		Team team = TeamsManager.getTeam(playerName);
		if (team == null) {
			return;
		}
		
		for (DomainRegion region : getRegions()) {
			boolean isLocFromOnRegion = region.isOnRegion(locFrom);
			boolean isLocToOnRegion = region.isOnRegion(locTo);
			
			if (!isLocFromOnRegion && isLocToOnRegion) {
				playerEntered(region, team);
				return;
			}
			
			if (isLocFromOnRegion && !isLocToOnRegion) {
				playerLeft(region, team);
				return;
			}
		}
	}
	
	public static void detectAbandoning(Location loc, String playerName) {	
		Team team = TeamsManager.getTeam(playerName);
		if (team == null) {
			return;
		}
		
		for (DomainRegion region : getRegions()) {
			if (region.isOnRegion(loc)) {
				playerLeft(region, team);
				return;
			}
		}
	}
	
	private static void playerEntered(DomainRegion region, Team team) {
		region.playerEntered(team);
	}
	
	private static void playerLeft(DomainRegion region, Team team) {
		region.playerLeft(team);
	}

}
