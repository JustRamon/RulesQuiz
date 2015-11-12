package tk.justramon.bukkitpl.rulesquiz.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.io.Files;

public class PlayerStorage
{
	public static boolean check(Plugin pl, Player p) throws IOException
	{
		// Stuff to gen the file and folder
		File data = new File(pl.getDataFolder(), "data");
		File playerStorage = new File(pl.getDataFolder(), "data" + File.separator + "playerStorage" + ".yml");
		File warning = new File (pl.getDataFolder(), "data" + File.separator + "WARNING" + ".txt");
		final Charset ENCODING = StandardCharsets.UTF_8;
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerStorage);

		if(!data.exists())
			data.mkdirs();

		if(!playerStorage.exists())
				playerStorage.createNewFile();
			
		
		if(!warning.exists())
		{
				String warningtext = "Warning: The files in here are soleley for storage. If you change it wrong, the plugin might break.";
				warning.createNewFile();
				Files.write(warningtext, warning, ENCODING);
		}

		// Stuff for the actual list inside
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
