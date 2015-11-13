package tk.justramon.bukkitpl.rulesquiz.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		File playerStorage = new File(RulesQuiz.plugin.getDataFolder(), "data" + File.separator + "playerStorage" + ".yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerStorage);
	}
}
