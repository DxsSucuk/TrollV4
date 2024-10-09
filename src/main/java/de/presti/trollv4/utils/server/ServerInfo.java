package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.server.versions.ServerSoftware;

public class ServerInfo {

    public static ServerSoftware serverSoftware = ServerSoftware.UNKNOWN;
    public static int versionId;

    public static String getMcVersion() {
        return Bukkit.getVersion().split("MC:")[1].replaceAll(" ", "").replaceAll("\\)", "");
    }

    public static void checkForServerSoftware() {

        String version = Bukkit.getVersion().split("\\(MC")[0].toLowerCase();
        String serverName = Bukkit.getServer().getName().toLowerCase();
        for (ServerSoftware sv : ServerSoftware.values()) {
            String name = sv.name().toLowerCase();
            if (version.contains(sv.name().toLowerCase()) || serverName.contains(name)) {
                serverSoftware = sv;
                break;
            }
        }
    }

    public static String getServerSoftware() {
        return getServerSoftwareEnum().name();
    }

    public static ServerSoftware getServerSoftwareEnum() {
        return serverSoftware;
    }

    public static String getNMSVersion() {
        return Bukkit.getVersion();
    }

    public static int getVersionId() {
        if (versionId == 0) {
            String[] split = getMcVersion().split("\\.");
            String version = split[1];
            if (version.split("\\.").length > 1) {
                version = version.split("\\.")[0];
            }

            versionId = Integer.parseInt(version);
        }

        return versionId;
    }

    public static boolean supportOldPackets() {
        return getVersionId() < 16;
    }

    public static boolean is(int version) {
        return getVersionId() == version;
    }

    public static boolean belowOrEqual(int version) {
        return getVersionId() <= version;
    }

    public static boolean aboveOrEqual(int version) {
        return getVersionId() >= version;
    }

    public static boolean below(int version) {
        return getVersionId() < version;
    }

    public static boolean above(int version) {
        return getVersionId() > version;
    }
}
