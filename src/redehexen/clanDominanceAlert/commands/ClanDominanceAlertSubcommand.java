package redehexen.clanDominanceAlert.commands;

import org.bukkit.command.CommandSender;

public interface ClanDominanceAlertSubcommand {

	void execute(CommandSender sender, String cmdlabel, String[] args);

}
