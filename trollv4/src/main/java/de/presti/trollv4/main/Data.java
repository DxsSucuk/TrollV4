package de.presti.trollv4.main;

import de.presti.trollv4.config.Config;

public class Data {
	
	public static String prefix = "§8│ §2Troll§cV4 §8» §2 ";
    public static String version = Main.instance.getDescription().getVersion();
    public static String cp = "§7[§a+§7]§2";
    public static String cm = "§7[§c-§7]§2";
    public static String cu = "§7[§8=§7]§2";
    public static boolean async = Config.getconfig().getBoolean("ASync");
	
}
