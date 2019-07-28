package ru.nanit.cdrop;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import ru.nanit.cdrop.commands.AdminCommands;
import ru.nanit.cdrop.storage.Configuration;
import ru.nanit.cdrop.storage.DropStorage;
import ru.nanit.cdrop.listeners.BlockBreakListener;

public class CustomDrop extends JavaPlugin {

	@Override
	public void onEnable() {
		try {
			Configuration config = new Configuration(this, "drop.yml");
			DropStorage storage = new DropStorage(config, getLogger());

			getServer().getPluginManager().registerEvents(new BlockBreakListener(storage), this);
			getCommand("customdrop").setExecutor(new AdminCommands(config, storage, getLogger()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
