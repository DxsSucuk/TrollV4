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

        for (ServerSoftware sv : ServerSoftware.values()) {
            if (version.contains(sv.name().toLowerCase())) {
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
        return Main.getInstance().version;
    }

    public static int getVersionId() {
        if (versionId == 0) {
            String[] split = getNMSVersion().split("v1_");
            String version = split[1];
            if (version.split("_").length > 1) {
                version = version.split("_")[0];
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
