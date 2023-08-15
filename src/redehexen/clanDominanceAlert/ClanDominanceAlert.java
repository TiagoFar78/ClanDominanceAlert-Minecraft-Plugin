package redehexen.clanDominanceAlert;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import redehexen.clanDominanceAlert.commands.ClanDominanceAlertCommand;

public class ClanDominanceAlert extends JavaPlugin {
	
	public static final String SET_REGION_PERMISSION = "TF_ClanDominanceAlert.SetPosition";
	public static final String RELOAD_PERMISSION = "TF_ClanDominanceAlert.Reload";
	
	@Override
	public void onEnable() {		
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		getCommand("clandominancealert").setExecutor(new ClanDominanceAlertCommand());
	}
	
	public static YamlConfiguration getYamlConfiguration() {
		return YamlConfiguration.loadConfiguration(configFile());
	}
	
	private static File configFile() {
		return new File(getClanDominanceAlert().getDataFolder(), "config.yml");
	}
	
	public static ClanDominanceAlert getClanDominanceAlert() {
		return (ClanDominanceAlert)Bukkit.getServer().getPluginManager().getPlugin("TF_ClanDominanceAlert");
	}
	
	public static void saveConfiguration(YamlConfiguration config) {
		File configFile = configFile();
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
