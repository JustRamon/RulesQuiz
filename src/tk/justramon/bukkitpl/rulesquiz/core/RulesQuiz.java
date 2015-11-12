package tk.justramon.bukkitpl.rulesquiz.core;

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
		plugin = this;
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		this.saveDefaultConfig();
	}

	public void onDisable()
	{
		plugin = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p = null;

		if (sender instanceof Player)
			p = (Player) sender;
		else
		{
			Messages.sendConsoleMessage("A RulesQuiz command was sent from an unsupported sender.");
			Messages.sendConsoleMessage("Please only execute RulesQuiz's commands in-game.");
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("rulesquiz"))
		{
			CommandSwitch.exe(p, args);
			return true;
		}
		return false;
	}
}
