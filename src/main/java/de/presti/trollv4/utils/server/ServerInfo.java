package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.server.versions.ServerVersions;

public class ServerInfo {

	public static ServerVersions v;
	public static int versionId;
	
	public static String getMcVersion() {
		return Bukkit.getVersion().split("MC:")[1].replaceAll(" ", "").replaceAll("\\)", "");
	}
	
	public static void checkForServerSoftware() {
		boolean found = false;

		String version = Bukkit.getVersion().split("\\(MC")[0].toLowerCase();

		for (ServerVersions sv : ServerVersions.values()) {
			if (version.contains(sv.name().toLowerCase())) {
				v = sv;
				found = true;
				break;
			}
		}
		
		if(!found) {
			v = ServerVersions.UNKNOWN;
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
		if (versionId == 0) {
			String[] split = getNMSVersion().split("v1_");
			String version = split[1];
			if (version.split("_").length > 1) {
				version = version.split("_")[0];
			}

			versionId = Integer.parseInt(version);
		}

		return versionId < 16;
	}
	
	public static boolean is18() {
		return getNMSVersion().startsWith("v1_8");
	}
	
	public static boolean is19() {
		return getNMSVersion().startsWith("v1_9");
	}
	
	public static boolean is110() {
		return getNMSVersion().startsWith("v1_10");
	}

	public static boolean is111() {
		return getNMSVersion().startsWith("v1_10");
	}
	
	public static boolean is112() {
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

	public static boolean is118() { return getNMSVersion().startsWith("v1_18"); }

	public static boolean is119() { return getNMSVersion().startsWith("v1_19"); }

	public static boolean is120() { return getNMSVersion().startsWith("v1_20"); }
}
