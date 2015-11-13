package tk.justramon.bukkitpl.rulesquiz.core;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import tk.justramon.bukkitpl.rulesquiz.commands.CommandSwitch;

public class RulesQuiz extends JavaPlugin
{
	public static RulesQuiz plugin;

	public void onEnable()
	{
		// Getting plugin instance
		plugin = this;
		// Registering ListenersClass
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		// Checking if data files exist
		Data.check(this);
		// Copy config.yml to the plugins's folder
		this.saveDefaultConfig();
	}

	public void onDisable()
	{
		// Removing plugin instance
		plugin = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// Check to make sure commands are sent from players.
		Player p = null;

		if (sender instanceof Player)
			p = (Player) sender;
		else
		{
			// Sends messages to console to let know that only players can execute RQ cmds
			Messages.sendConsoleMessage("A RulesQuiz command was sent from an unsupported sender.");
			Messages.sendConsoleMessage("Please only execute RulesQuiz's commands in-game.");
			return true;
		}
		
		// Command check
		if (cmd.getName().equalsIgnoreCase("rulesquiz"))
		{
			CommandSwitch.exe(p, args);
			return true;
		}
		return false;
	}
}
