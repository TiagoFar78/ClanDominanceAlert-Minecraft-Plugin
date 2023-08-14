package redehexen.clanDominanceAlert.regions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Region {
	
	private Location _loc1;
	private Location _loc2;
	
	public Region(Location loc1, Location loc2) {		
		World world = Bukkit.getWorld(loc1.getWorld().getName());
		
		int x1 = loc1.getBlockX();
		int y1 = loc1.getBlockY();
		int z1 = loc1.getBlockZ();
		
		int x2 = loc2.getBlockX();
		int y2 = loc2.getBlockY();
		int z2 = loc2.getBlockZ();
		
		if (x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		
		if (y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		if (z1 > z2) {
			int temp = z1;
			z1 = z2;
			z2 = temp;
		}
		
		_loc1 = new Location(world, x1, y1, z1);
		_loc2 = new Location(world, x2, y2, z2);
	}
	
	public boolean isOnRegion(Location loc) {
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		
		return loc.getWorld().getName().equals(_loc1.getWorld().getName()) &&
				x >= _loc1.getBlockX() && x <= _loc2.getBlockX() &&
				y >= _loc1.getBlockY() && y <= _loc2.getBlockY() &&
				z >= _loc1.getBlockZ() && z <= _loc2.getBlockZ();
	}

}
