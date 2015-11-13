package tk.justramon.bukkitpl.rulesquiz.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Messages
{
	// Making a variable for easier use of plugin instance
	static Plugin pl = RulesQuiz.plugin;
	
	// Sends a message to the console with header
	public static void sendConsoleMessage( String msg)
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "[" + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + pl.getDescription().getName() + ChatColor.BOLD + "" + ChatColor.YELLOW +"] " + ChatColor.RESET + msg);
	}
	
	// Sends a message to a player with header
	public static void sendPlayerMessage( Player p, String msg)
	{
		p.sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "[" + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + pl.getDescription().getName() + ChatColor.BOLD + "" + ChatColor.YELLOW +"] " + ChatColor.RESET + msg);
	}
	
	// Mesage that gets sent to a player when the command they type is unsupported.
	public static void nonExistingCmd(Player p)
	{
		sendPlayerMessage(p, ChatColor.RED + "Sorry, but the command you tried to execute does not exist.");
	}
	
	// Help Menu lines.
	// TODO: Add help menu here
	public static void sendHelpMenu(Player p)
	{
		p.sendMessage(ChatColor.BLUE + "----" + ChatColor.BOLD + "" + ChatColor.YELLOW + "[" + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + pl.getDescription().getName() + ChatColor.BOLD + "" + ChatColor.YELLOW +"]" + ChatColor.BLUE + "----");
	}
}
