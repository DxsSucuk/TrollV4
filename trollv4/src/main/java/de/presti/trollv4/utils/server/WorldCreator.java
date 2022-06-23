package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;

public class WorldCreator {

	@SuppressWarnings("deprecation")
	public static void createWorld(String name) {
		Bukkit.createWorld(new org.bukkit.WorldCreator(name));
		try {
			Bukkit.getWorld(name).setGameRuleValue("doDaylightCycle", "false");
			Bukkit.getWorld(name).setGameRuleValue("doMobSpawning", "false");
		} catch (Exception ex) {
		}
	}

	public static void createWorld(String name, GenTyps gen) {
		org.bukkit.WorldCreator wc = new org.bukkit.WorldCreator(name);

		Bukkit.createWorld(wc);
	}

	public enum GenTyps {

		FLAT, NORMAL, NETHER, END

	}

}
