package redehexen.clanDominanceAlert.commands;

import org.bukkit.command.CommandSender;

import redehexen.clanDominanceAlert.ClanDominanceAlert;
import redehexen.clanDominanceAlert.managers.ConfigManager;
import redehexen.clanDominanceAlert.managers.DomainRegionsManager;

public class ReloadSubcommand implements ClanDominanceAlertSubcommand {

	@Override
	public void execute(CommandSender sender, String cmdlabel, String[] args) {
		ConfigManager configManager = ConfigManager.getInstance();
		
		if (!sender.hasPermission(ClanDominanceAlert.SET_REGION_PERMISSION)) {
			sender.sendMessage(configManager.getNotAllowedMessage());
			return;
		}
		
		ConfigManager.reload();
		DomainRegionsManager.reload();
		
		sender.sendMessage(configManager.getReloadedMessage());
	}

}
