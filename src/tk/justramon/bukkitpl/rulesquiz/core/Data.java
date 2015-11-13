package tk.justramon.bukkitpl.rulesquiz.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;


public class Data
{
	public static void check(Plugin pl)
	{
		// Stuff to gen the file and folder
		File data = new File(pl.getDataFolder(), "data");
		File playerStorage = new File(pl.getDataFolder(), "data" + File.separator + "playerStorage" + ".yml");
		File warning = new File(pl.getDataFolder(), "data" + File.separator + "WARNING" + ".txt");

		// Data folder existing check
		if(!data.exists())
			data.mkdirs();

		// playerStorage.yml existing check
		if(!playerStorage.exists())
		{
			try
			{
				playerStorage.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(playerStorage).set("done", Arrays.asList("This is only here to prevent this list being null."));
		}


		// WARNING.txt existing check
		if(!warning.exists())
		{
			try
			{
				warning.createNewFile();
				FileUtils.writeStringToFile(warning, "Warning: The files in here are soleley for storage. If you change it wrong, the plugin might break.", StandardCharsets.UTF_8);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
