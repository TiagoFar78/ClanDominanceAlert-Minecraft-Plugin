package redehexen.clanDominanceAlert;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import redehexen.clanDominanceAlert.managers.ConfigManager;
import redehexen.clanDominanceAlert.managers.DomainRegionsManager;

public class Events implements Listener {
	
	@EventHandler
	public void playerMove(PlayerMoveEvent e) {
		Location locFrom = e.getFrom();
		Location locTo = e.getTo();
		
		DomainRegionsManager.detectMovement(locFrom, locTo, e.getPlayer().getName());
	}
	
	@EventHandler
	public void playerTeleport(PlayerTeleportEvent e) {
		Location locFrom = e.getFrom();
		Location locTo = e.getTo();
		
		DomainRegionsManager.detectMovement(locFrom, locTo, e.getPlayer().getName());
	}
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent e) {
		ConfigManager configManager = ConfigManager.getInstance();
		if (configManager.doesLeaveAreaOnDisconnecting()) {
			Player player = e.getPlayer();
			DomainRegionsManager.detectAbandoning(player.getLocation(), player.getName());
		}
	}
	
	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		DomainRegionsManager.detectAbandoning(player.getLocation(), player.getName());
	}

}
