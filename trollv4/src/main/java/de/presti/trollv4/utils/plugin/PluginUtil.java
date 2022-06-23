package de.presti.trollv4.utils.plugin;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PluginUtil {

	public static void loadPlugin(String name) throws Exception {
		Plugin pl = null;
		if (new File("plugins/" + name + ".jar").exists()) {
			pl = Bukkit.getPluginManager().loadPlugin(new File("plugins/" + name + ".jar"));
			pl.onLoad();

			Bukkit.getPluginManager().enablePlugin(pl);
		} else {
			throw new Exception("File doesnt Exist!");
		}
	}

	public static void unloadPlugin(String name) throws Exception {
		Plugin pl = null;
		if (new File("plugins/" + name + ".jar").exists()) {
			pl = Bukkit.getPluginManager().getPlugin(name);

			Bukkit.getPluginManager().disablePlugin(pl);
		} else {
			throw new Exception("File doesnt Exist!");
		}
	}

	public static boolean isLoaded(String name) {
		Plugin pl = null;
		boolean b = false;
		if (new File("plugins/" + name + ".jar").exists()) {
			pl = Bukkit.getPluginManager().getPlugin(name);
			if (pl != null) {
				if (pl.isEnabled()) {
					b = true;
				} else {
					b = true;
				}
			} else {
				b = false;
			}
		} else {
			b = false;
		}
		return b;
	}

}
