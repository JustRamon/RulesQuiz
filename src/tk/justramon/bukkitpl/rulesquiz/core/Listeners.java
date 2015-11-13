package tk.justramon.bukkitpl.rulesquiz.core;

import java.io.File;
import java.util.Arrays;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatColor;
import tk.justramon.bukkitpl.rulesquiz.quiz.Init;
public class Listeners implements Listener
{
	// Stuff to locate the playerStorage yml
	File playerStorage = new File(RulesQuiz.plugin.getDataFolder(), "data" + File.separator + "playerStorage" + ".yml");
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerStorage);

	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();

		// If the player hasn't done the quiz yet, launch the rulesquiz-quiz
		// Check to create "done" if needed.
		if(yaml.get("done") == null)
			yaml.set("done", Arrays.asList(""));
		else
		{
			if(!yaml.get("done").toString().contains(p.getUniqueId().toString()))
				Init.exe(p);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onMove(PlayerMoveEvent event)
	{
		Player p = event.getPlayer();

		// If the player hasn't done the quiz yet, do not let him move.
		// Check to create "done" if needed.
		if(yaml.get("done") == null)
			yaml.set("done", Arrays.asList(""));
		else
		{
			if(!yaml.get("done").toString().contains(p.getUniqueId().toString()) && !event.getFrom().toVector().equals(event.getTo().toVector()))
				p.teleport(p);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		if(!yaml.get("done").toString().contains(p.getUniqueId().toString()))
			event.setCancelled(true);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player p = event.getPlayer();

		// [DEBUG CODE] If the player hasn't done the quiz yet, do not let him move.
		//TODO: Change this to respect quiz inputs.
		if(!yaml.get("done").toString().contains(p.getUniqueId().toString()))
			Messages.sendPlayerMessage(p, ChatColor.RED + "You cannot chat during the rulesquiz.");
		event.setCancelled(true);
	}
}
