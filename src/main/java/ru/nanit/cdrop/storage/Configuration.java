package ru.nanit.cdrop.storage;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import ru.nanit.cdrop.CustomDrop;

public class Configuration {

	private Plugin plugin;
	private String assetName;
	private YamlConfiguration config;
	private File configDir;
	private File file;
	
	public YamlConfiguration get() {
		return config;
	}
	
	public Configuration(Plugin plugin, String assetName) throws IOException {
		this.plugin = plugin;
		this.assetName = assetName;
		this.configDir = plugin.getDataFolder();
		
		reload();
	}
	
	public void reload() {
		this.file = new File(configDir, assetName);
		
		if(!configDir.exists()) {
			configDir.mkdir();
		}
		
		if(!file.exists()) {
			plugin.saveResource(assetName, false);
		}
		
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
