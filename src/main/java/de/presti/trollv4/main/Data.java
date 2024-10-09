package de.presti.trollv4.main;

import de.presti.trollv4.config.Config;

public class Data {
	
	public static String prefix = "§8│ §2Troll§cV4 §8» §2 ";
    public static String version = Main.getInstance().getDescription().getVersion();
    public static String changelogAdd = "§7[§a+§7]§2";
    public static String changelogRemove = "§7[§c-§7]§2";
    public static String changelogChange = "§7[§8=§7]§2";
    public static boolean async = Config.getConfig().getBoolean("ASync");
	
}
