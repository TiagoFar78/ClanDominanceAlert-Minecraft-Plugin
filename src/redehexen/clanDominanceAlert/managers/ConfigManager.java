package redehexen.clanDominanceAlert.managers;

import org.bukkit.configuration.file.YamlConfiguration;

import redehexen.clanDominanceAlert.ClanDominanceAlert;

public class ConfigManager {
	
	private static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	public static void reload() {
		instance = new ConfigManager();
	}
	
	private int _dominationAlertDelay;
	
	private String _dominatingMessageSingular;
	private String _dominatingMessagePlural;
	private String _positionSetMessage;
	private String _reloadedMessage;
	
	private String _notAllowedMessage;
	private String _wrongIndexMessage;
	private String _mustBePlayerMessage;
	
	private String _setPositionUsageMessage;
	private String _reloadUsageMessage;
	
	private ConfigManager() {
		YamlConfiguration config = ClanDominanceAlert.getYamlConfiguration();
		
		_dominationAlertDelay = config.getInt("DominationAlertDelay");
		
		_dominatingMessageSingular = config.getString("Messages.Warnings.DominatingMessageSingular").replace("&", "�");
		_dominatingMessagePlural = config.getString("Messages.Warnings.DominatingMessagePlural").replace("&", "�");
		_positionSetMessage = config.getString("Messages.Warnings.PositionSet").replace("&", "�");
		_reloadedMessage = config.getString("Messages.Warnings.Reloaded").replace("&", "�");
		
		_notAllowedMessage = config.getString("Messages.Errors.NotAllowed").replace("&", "�");
		_wrongIndexMessage = config.getString("Messages.Errors.WrongIndex").replace("&", "�");
		_mustBePlayerMessage = config.getString("Messages.Errors.MustBePlayer").replace("&", "�");
		
		_setPositionUsageMessage = config.getString("Messages.Usage.SetPosition").replace("&", "�");
		_reloadUsageMessage = config.getString("Messages.Usage.Reload").replace("&", "�");
	}
	
	public int getDominationAlertDelay() {
        return _dominationAlertDelay;
    }

    public String getDominatingMessageSingular() {
        return _dominatingMessageSingular;
    }

    public String getDominatingMessagePlural() {
        return _dominatingMessagePlural;
    }
	
	public String getPositionSetMessage() {
        return _positionSetMessage;
    }

    public String getReloadedMessage() {
        return _reloadedMessage;
    }
    
    public String getNotAllowedMessage() {
        return _notAllowedMessage;
    }

    public String getWrongIndexMessage() {
        return _wrongIndexMessage;
    }

    public String getMustBePlayerMessage() {
        return _mustBePlayerMessage;
    }

    public String getSetPositionUsageMessage() {
        return _setPositionUsageMessage;
    }

    public String getReloadUsageMessage() {
        return _reloadUsageMessage;
    }

}
