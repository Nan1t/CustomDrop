package ru.nanit.cdrop.commands;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ru.nanit.cdrop.CustomDrop;

import net.md_5.bungee.api.ChatColor;
import ru.nanit.cdrop.storage.Configuration;
import ru.nanit.cdrop.storage.DropStorage;

public class AdminCommands implements CommandExecutor {
	
	private Logger logger;
	private Configuration conf;
	private DropStorage storage;

	public AdminCommands(Configuration conf, DropStorage storage, Logger logger) {
		this.conf = conf;
		this.logger = logger;
		this.storage = storage;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("reload")) {
				conf.reload();
				storage.reloadDrop();
				logger.info("CustomDrop plugin successfully reloaded");
				return true;
			}
		}
		
		sender.sendMessage("|=--------- [CustomDrop] ---------=|");
		sender.sendMessage("/csd reload - Reload all drop data");
		return true;
	}

}
