package tk.justramon.bukkitpl.rulesquiz.commands;

import org.bukkit.entity.Player;

import tk.justramon.bukkitpl.rulesquiz.core.Messages;


public class CommandSwitch
{
	public static void exe(Player p, String[] args)
	{
		if(args.length > 0 && args[0] != null)
		{
			switch (args[0])
			{
			case "bypass": Bypass.exe(p); break;
			case "reset": Reset.exe(p); break;
			default: Messages.nonExistingCmd(p); break;
			}
		}
		else
			Messages.sendHelpMenu(p);
	}
}
