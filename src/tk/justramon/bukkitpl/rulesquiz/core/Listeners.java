package tk.justramon.bukkitpl.rulesquiz.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import net.md_5.bungee.api.ChatColor;
import tk.justramon.bukkitpl.rulesquiz.quiz.Init;

public class Listeners implements Listener
{
	// Stuff to locate the playerStorage yml
	private File playerStorage = new File(RulesQuiz.plugin.getDataFolder(), "data" + File.separator + "playerStorage.yml");
	private YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerStorage);

	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws IOException 
	{
		Player p = event.getPlayer();

		// If the player hasn't done the quiz yet, launch the rulesquiz-quiz
		// Check to create "done" if needed.
		if(yaml.get(p.getName() + ".done") == null)
			yaml.set(p.getName() + ".done", false);

		yaml.set(p.getName() + ".loc.world", p.getLocation().getWorld().getName());
		yaml.set(p.getName() + ".loc.x", p.getLocation().getX());
		yaml.set(p.getName() + ".loc.y", p.getLocation().getY());
		yaml.set(p.getName() + ".loc.z", p.getLocation().getZ());
		yaml.save(playerStorage);
		
		if(!yaml.getBoolean(p.getName() + ".done"))
			Init.exe(p);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onMove(PlayerMoveEvent event)
	{
		Player p = event.getPlayer();

		// If the player hasn't done the quiz yet, do not let him move.
		// Check to create "done" if needed.
		if(!yaml.getBoolean(p.getName() + ".done") && !event.getFrom().toVector().equals(event.getTo().toVector()))
		{
			Location l = new Location(Bukkit.getWorld(yaml.getString(p.getName() + ".loc.world")), yaml.getDouble(p.getName() + ".loc.x"), yaml.getDouble(p.getName() + ".loc.y"), yaml.getDouble(p.getName() + ".loc.z"));

			l.setPitch(p.getLocation().getPitch());
			l.setYaw(p.getLocation().getYaw());
			p.teleport(l);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player p = event.getPlayer();

		if(!yaml.getBoolean(p.getName() + ".done"))
			event.setCancelled(true);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player p = event.getPlayer();

		// [DEBUG CODE] If the player hasn't done the quiz yet, do not let him move.
		//TODO: Change this to respect quiz inputs.
		if(!yaml.getBoolean(p.getName() + ".done"))
		{
			Messages.sendPlayerMessage(p, ChatColor.RED + "You cannot chat during the rulesquiz.");
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		if(!yaml.getBoolean(event.getPlayer().getName() + ".done"))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event)
	{
		if(!yaml.getBoolean(event.getPlayer().getName() + ".done"))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		if(event.getDamager() instanceof Player)
		{
			if(!yaml.getBoolean(event.getDamager().getName() + ".done"))
				event.setCancelled(true);
		}
	}
}
