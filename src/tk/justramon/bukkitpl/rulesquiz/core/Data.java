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

public class Data
{
	public static void check(Plugin pl)
	{
		// Stuff to gen the file and folder
		File data = new File(pl.getDataFolder(), "data");
		File playerStorage = new File(pl.getDataFolder(), "data" + File.separator + "playerStorage" + ".yml");
		File warning = new File (pl.getDataFolder(), "data" + File.separator + "WARNING" + ".txt");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(playerStorage);

		// Data folder existing check
		if(!data.exists())
			data.mkdirs();

		// playerStorage.yml existing check
		if(!playerStorage.exists())
			try
		{
				playerStorage.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}


		// WARNING.txt existing check
		if(!warning.exists())
		{
			try
			{
				warning.createNewFile();
				Files.write("Warning: The files in here are soleley for storage. If you change it wrong, the plugin might break.", warning, StandardCharsets.UTF_8);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
