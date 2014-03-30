package me.codermusgrove.growcontrol;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GrowControl extends JavaPlugin implements Listener {

	private boolean grow, wheat, potato, carrot, netherwart, melon, pumpkin, vine, sugarcane, trees, mushrooms, treesbm, mushroomsbm;
	private String[] list = { "grow", "wheat-grow", "potato-grow", "carrot-grow", "netherwart-grow", "melon-grow", "pumpkin-grow", "vine-grow",
			"sugarcane-grow", "tree-grow", "mushroom-grow", "tree-bonemeal", "mushroom-bonemeal" };
	private TreeType[] treetype = { TreeType.TREE, TreeType.BIG_TREE, TreeType.ACACIA, TreeType.BIRCH, TreeType.DARK_OAK, TreeType.JUNGLE,
			TreeType.JUNGLE_BUSH, TreeType.MEGA_REDWOOD, TreeType.REDWOOD, TreeType.SMALL_JUNGLE, TreeType.SWAMP, TreeType.TALL_BIRCH,
			TreeType.TALL_REDWOOD };
	private TreeType[] mushroomType = { TreeType.BROWN_MUSHROOM, TreeType.RED_MUSHROOM };

	@Override
	public void onEnable() {
		saveDefaultConfig();
		loadValues();
		getServer().getPluginManager().registerEvents(this, this);
	}

	private void loadValues() {
		for (int i = 0; i < list.length; i++) {
			if (getConfig().getString(list[i]) == null) {
				getConfig().set(list[i], true);
				continue;
			}
			if (getConfig().getString(list[i]).equals("")) {
				getConfig().set(list[i], true);
				continue;
			}
		}
		saveConfig();
		grow = getConfig().getBoolean(list[0]);
		wheat = getConfig().getBoolean(list[1]);
		potato = getConfig().getBoolean(list[2]);
		carrot = getConfig().getBoolean(list[3]);
		netherwart = getConfig().getBoolean(list[4]);
		melon = getConfig().getBoolean(list[5]);
		pumpkin = getConfig().getBoolean(list[6]);
		vine = getConfig().getBoolean(list[7]);
		sugarcane = getConfig().getBoolean(list[8]);
		trees = getConfig().getBoolean(list[9]);
		mushrooms = getConfig().getBoolean(list[10]);
		treesbm = getConfig().getBoolean(list[11]);
		mushroomsbm = getConfig().getBoolean(list[12]);
	}

	@EventHandler
	public void onBlockGrow(BlockGrowEvent e) {
		Block b = e.getBlock();
		Material t = b.getType();
		if (!grow) return;
		if (t == Material.CROPS && !wheat) e.setCancelled(true);
		if (t == Material.POTATO && !potato) e.setCancelled(true);
		if (t == Material.CARROT && !carrot) e.setCancelled(true);
		if (t == Material.NETHER_STALK && !netherwart) e.setCancelled(true);
		if ((t == Material.MELON_STEM || t == Material.MELON_BLOCK) && !melon) e.setCancelled(true);
		if ((t == Material.PUMPKIN_STEM || t == Material.PUMPKIN) && !pumpkin) e.setCancelled(true);
		if (t == Material.VINE && !vine) e.setCancelled(true);
		if (t == Material.SUGAR_CANE_BLOCK && !sugarcane) e.setCancelled(true);
	}

	@EventHandler
	public void onStructureGrow(StructureGrowEvent e) {
		TreeType t = e.getSpecies();
		Player p = e.getPlayer();
		if (Arrays.asList(treetype).contains(t) && !trees) {
			if (p == null) e.setCancelled(true);
			else if (p != null && !treesbm) e.setCancelled(true);
		}
		if (Arrays.asList(mushroomType).contains(t) && !mushrooms) {
			if (p == null) e.setCancelled(true);
			else if (p != null && !mushroomsbm) e.setCancelled(true);
		}
	}
}
