package ru.nanit.cdrop.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.inventory.ItemStack;
import ru.nanit.cdrop.data.Drop;
import ru.nanit.cdrop.storage.DropStorage;

public class BlockBreakListener implements Listener {
	
	private DropStorage storage;
	private Random random = new Random();

	public BlockBreakListener(DropStorage storage){
		this.storage = storage;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Material material = block.getType();

		List<Drop> dropList = storage.getBlockDrop(material);
		
		if(dropList == null) { return; }
		
		for(Drop drop : dropList) {
			if(drop.getPermission() != null) {
				if(!player.hasPermission(drop.getPermission())) {
					continue;
				}
			}
			
			byte chance = (byte) random.nextInt(100);
			
			if(chance <= drop.getChance()) {
				int count = drop.getMinCount() + random.nextInt(drop.getMaxCount() - drop.getMinCount() + 1);
				ItemStack item = new ItemStack(drop.getMaterial(), (short) count);

				event.setCancelled(true);
				block.setType(Material.AIR);
				block.getWorld().dropItemNaturally(block.getLocation(), item);
			}
		}
	}
	
}
