package de.presti.trollv4.util.server;

import org.bukkit.Bukkit;

import de.presti.trollv4.main.Main;

public class ServerInfo {

	public static ServerVersion v;
	
	public static String getMcVersion() {
		return Bukkit.getVersion().split("MC:")[1].replaceAll(" ", "").replaceAll("\\)", "");
	}
	
	public static void checkForServerSoftware() {
		boolean found = false;
		
		try {
			if(Class.forName("net.pl3x.purpur.PurpurConfig") != null && !found) {
				v = ServerVersion.PURPUR;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		
		try {
			if(Class.forName("com.tuinity.tuinity.config.TuinityConfig") != null && !found) {
				v = ServerVersion.TUINITY;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		try {
			if(Class.forName("dev.cobblesword.nachospigot.NachoConfig") != null && !found) {
				v = ServerVersion.NACHO;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		try {
			if((Class.forName("co.aikar.util.JSONUtil") != null || Class.forName("com.destroystokyo.paper.PaperConfig") != null) && !found) {
				v = ServerVersion.PAPER;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		try {
			if(Class.forName("org.spigotmc.SpigotConfig") != null && !found) {
				v = ServerVersion.SPIGOT;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		if(!found) {
			v = ServerVersion.BUKKIT;
		}
		
	}
	
	public static String getServerSoftware() {
		return v.name();
	}
	
	public static ServerVersion getServerSoftwareEnum() {
		return v;
	}
	
	public static String getNMSVersion() {
		return Main.version;
	}
	
	public static boolean supportOldPackets() {
		return !is116() && !is117();
	}
	
	public static boolean is18() {
		return getNMSVersion().startsWith("v1_8");
	}
	
	public static boolean is19() {
		return getNMSVersion().startsWith("v1_9");
	}
	
	public static boolean is10() {
		return getNMSVersion().startsWith("v1_10");
	}

	public static boolean is11() {
		return getNMSVersion().startsWith("v1_10");
	}
	
	public static boolean is12() {
		return getNMSVersion().startsWith("v1_12");
	}
	
	public static boolean is113() {
		return getNMSVersion().startsWith("v1_13");
	}
	
	public static boolean is114() {
		return getNMSVersion().startsWith("v1_14");
	}
	
	public static boolean is115() {
		return getNMSVersion().startsWith("v1_15");
	}
	
	public static boolean is116() {
		return getNMSVersion().startsWith("v1_16");
	}
	
	public static boolean is117() {
		return getNMSVersion().startsWith("v1_17");
	}

	public enum ServerVersion {
		BUKKIT, SPIGOT, PAPER, TACO, TUINITY, PURPUR, NACHO
	}
}