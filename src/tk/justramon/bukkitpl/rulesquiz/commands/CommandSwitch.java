package tk.justramon.bukkitpl.rulesquiz.commands;

import org.bukkit.entity.Player;

import tk.justramon.bukkitpl.rulesquiz.core.Messages;


public class CommandSwitch
{
	public static void exe(Player p, String[] args)
	{
		// Checking if it actually has args
		if(args.length > 0 && args[0] != null)
		{
			// Switch to find out what subcommand they're executing
			switch (args[0])
			{
			case "reset": Reset.exe(p); break;
			
			// Defaults to the non existing cmd msg\
			default: Messages.nonExistingCmd(p); break;
			}
		}
		// Cmd without args will open the help menu.
		else
			Messages.sendHelpMenu(p);
	}
}
