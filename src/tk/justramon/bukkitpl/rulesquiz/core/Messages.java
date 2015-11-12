package tk.justramon.bukkitpl.rulesquiz.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Messages
{
	static Plugin pl = RulesQuiz.plugin;
	
	public static void sendConsoleMessage( String msg)
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "[" + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + pl.getDescription().getName() + ChatColor.BOLD + "" + ChatColor.YELLOW +"] " + ChatColor.RESET + msg);
	}
	
	public static void sendPlayerMessage( Player p, String msg)
	{
		p.sendMessage(ChatColor.BOLD + "" + ChatColor.YELLOW + "[" + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + pl.getDescription().getName() + ChatColor.BOLD + "" + ChatColor.YELLOW +"] " + ChatColor.RESET + msg);
	}
	
	public static void nonExistingCmd(Player p)
	{
		sendPlayerMessage(p, ChatColor.RED + "Sorry, but the command you tried to execute does not exist.");
	}
	
	public static void sendHelpMenu(Player p)
	{
		p.sendMessage(ChatColor.BLUE + "----" + ChatColor.BOLD + "" + ChatColor.YELLOW + "[" + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + pl.getDescription().getName() + ChatColor.BOLD + "" + ChatColor.YELLOW +"]" + ChatColor.BLUE + "----");
	}
}
