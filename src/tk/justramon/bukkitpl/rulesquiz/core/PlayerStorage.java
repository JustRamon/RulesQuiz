package tk.justramon.bukkitpl.rulesquiz.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerStorage
{
	public static boolean check(Plugin pl, Player p)
	{
		// Stuff to gen the file and folder
		File folder = new File(pl.getDataFolder(), "data");
		File f = new File(pl.getDataFolder(), "data" + File.separator + "playerStorage" +".yml");

		if(!folder.exists())
			folder.mkdirs();

		if(!f.exists())
			try
		{
				f.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		// Stuff for the actual list inside
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		List<String> done = new ArrayList<String>();

		// If the list doesn't exist, create it. Else do a check if the player is done.
		if(yaml.get("done") == null)
			yaml.set("done", done);
		
		if(!yaml.get("done").toString().contains(p.getUniqueId().toString()))
			return false;
		else
			return true;
}
}
