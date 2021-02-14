package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.server.versions.ServerVersions;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2020											    *
*	Erstellt: 29.11.2020 / 11:11:45											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class ServerInfo {

	public static ServerVersions v;
	
	public static String getMcVersion() {
		return Bukkit.getVersion().split("MC:")[1].replaceAll(" ", "").replaceAll("\\)", "");
	}
	
	public static void checkForServerSoftware() {
		boolean found = false;
		
		try {
			if(Class.forName("net.pl3x.purpur.PurpurConfig") != null && !found) {
				v = ServerVersions.PURPUR;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		
		try {
			if(Class.forName("com.tuinity.tuinity.config.TuinityConfig") != null && !found) {
				v = ServerVersions.TUINITY;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		try {
			if(Class.forName("dev.cobblesword.nachospigot.NachoConfig") != null && !found) {
				v = ServerVersions.NACHO;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		try {
			if((Class.forName("co.aikar.util.JSONUtil") != null || Class.forName("com.destroystokyo.paper.PaperConfig") != null) && !found) {
				v = ServerVersions.PAPER;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		try {
			if(Class.forName("org.spigotmc.SpigotConfig") != null && !found) {
				v = ServerVersions.SPIGOT;
				found = true;
			}
		} catch (ClassNotFoundException e) {
		}
		
		if(!found) {
			v = ServerVersions.BUKKIT;
		}
		
	}
	
	public static String getServerSoftware() {
		return v.name();
	}
	
	public static ServerVersions getServerSoftwareEnum() {
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
}
