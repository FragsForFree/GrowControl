package me.codermusgrove.growcontrol;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GrowControl extends JavaPlugin implements Listener {

	private boolean wheat, potato, carrot, netherwart, melon, pumpkin, vine, sugarcane;
	private String[] list = { "wheat-grow", "potato-grow", "carrot-grow", "netherwart-grow", "melon-grow", "pumpkin-grow", "vine-grow",
			"sugarcane-grow" };

	@Override
	public void onEnable() {
		saveDefaultConfig();
		loadValues();
		getServer().getPluginManager().registerEvents(this, this);
	}

	private void loadValues() {
		FileConfiguration config = getConfig();
		wheat = config.getBoolean("wheat-grow", true);
		potato = config.getBoolean("potato-grow", true);
		carrot = config.getBoolean("carrot-grow", true);
		netherwart = config.getBoolean("netherwart-grow", true);
		melon = config.getBoolean("melon-grow", true);
		pumpkin = config.getBoolean("pumpkin-grow", true);
		vine = config.getBoolean("vine-grow", true);
		sugarcane = config.getBoolean("sugarcane-grow", true);

		for (int i = 0; i < list.length; i++) {
			if (config.getString(list[i]) == null) config.set(list[i], true);
		}
	}

	@EventHandler
	public void onBlockGrow(BlockGrowEvent e) {
		Block b = e.getBlock();
		Material t = b.getType();
		if (t == Material.CROPS && !wheat) e.setCancelled(true);
		if (t == Material.POTATO && !potato) e.setCancelled(true);
		if (t == Material.CARROT && !carrot) e.setCancelled(true);
		if (t == Material.NETHER_STALK && !netherwart) e.setCancelled(true);
		if ((t == Material.MELON_STEM || t == Material.MELON_BLOCK) && !melon) e.setCancelled(true);
		if ((t == Material.PUMPKIN_STEM || t == Material.PUMPKIN) && !pumpkin) e.setCancelled(true);
		if (t == Material.VINE && !vine) e.setCancelled(true);
		if (t == Material.SUGAR_CANE_BLOCK && !sugarcane) e.setCancelled(true);
	}
}
