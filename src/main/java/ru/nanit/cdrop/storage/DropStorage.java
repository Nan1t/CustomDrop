package ru.nanit.cdrop.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import ru.nanit.cdrop.data.Drop;

public class DropStorage {

	private Logger logger;
	private YamlConfiguration conf;
	
	private Map<Material, List<Drop>> drops = new HashMap<>();
	
	public List<Drop> getBlockDrop(Material mat){
		return drops.get(mat);
	}
	
	public DropStorage(Configuration conf, Logger logger) {
		this.conf = conf.get();
		this.logger = logger;
		parseConfig();
	}

	public void reloadDrop(){
		drops.clear();
		parseConfig();
	}
	
	private void parseConfig() {
		ConfigurationSection main = conf.getConfigurationSection("blocks");
		Set<String> blocks = main.getKeys(false);
		
		for(String blockName : blocks) {
			ConfigurationSection blockSection = main.getConfigurationSection(blockName);

			List<Drop> dropList = new ArrayList<>();
			Material mat = Material.getMaterial(blockSection.getString("id"));

			if(mat == null){
				logger.severe("Failed to load block with material " + blockSection.getString("id"));
				continue;
			}

			Set<String> drops = blockSection.getConfigurationSection("drop").getKeys(false);
			
			for(String dropName : drops) {
				ConfigurationSection dropSection = blockSection.getConfigurationSection("drop." + dropName);

				Material material = Material.getMaterial(dropSection.getString("id"));
				byte chance = (byte) dropSection.getInt("chance");
				int countMin = dropSection.getInt("count_min");
				int countMax = dropSection.getInt("count_max");
				String permission = dropSection.getString("permission");

				if(material != null){
					dropList.add(new Drop(material, permission, chance, countMin, countMax));
				} else {
					logger.severe("Failed to load drop with material '" + dropSection.getString("id") + "'");
				}
			}
			
			this.drops.put(mat, dropList);
			logger.info("Loaded data for block " + mat + " (" + dropList.size() + " items)");
		}
	}
}
