package de.presti.trollv4.utils.plugin;

import de.presti.trollv4.logging.Logger;
import io.sentry.Sentry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker {

    public final JavaPlugin javaPlugin;
    public final String localPluginVersion;
    public String spigotPluginVersion;

    public static final int ID = 67318;
    public static String ERR_MSG = "&cUpdate checker failed!";
    public static final long CHECK_INTERVAL = 12_000; // In ticks.

    public UpdateChecker(final JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.localPluginVersion = Data.version;
        Main.getInstance().updateChecker = this;
        ERR_MSG = "Update checker failed!";
    }

    public void checkForUpdate() {
        new BukkitRunnable() {
            @SuppressWarnings("static-access")
            @Override
            public void run() {
                Bukkit.getScheduler().runTaskAsynchronously(javaPlugin, () -> {
                    try {
                        final HttpsURLConnection connection = (HttpsURLConnection) new URL(
                                "https://api.spigotmc.org/legacy/update.php?resource=" + ID).openConnection();
                        connection.setRequestMethod("GET");
                        spigotPluginVersion = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                                .readLine();
                    } catch (final IOException e) {
                        Logger.error(ERR_MSG);
                        Sentry.captureException(e);
                        e.printStackTrace();
                        cancel();
                        return;
                    }

                    if (compareVersion(localPluginVersion, spigotPluginVersion)) {
                        Logger.warning("TrollV4 has a update!");
                        Logger.warning("New Version: " + spigotPluginVersion);
                        Logger.warning("Your Version: " + localPluginVersion);
                        Logger.warning("Download here: https://www.spigotmc.org/resources/" + ID + "/updates");
                    } else {
                        Logger.info("TrollV4 has no update");
                    }
                    cancel(); // Cancel the runnable as an update has been found.
                });
            }
        }.runTaskTimer(javaPlugin, 20, CHECK_INTERVAL);
    }

    // TODO:: make a utility class for this PLEASE FOR THE LOVE OF GOD.

    /**
     * Compare two strings based on the x.y.z version format with each other
     * @param versionA Base Version
     * @param versionB Version to compare to
     * @return The result of the comparison. True, if the versionB is higher | False, if the versionA is higher
     */
    public boolean compareVersion(String versionA, String versionB) {
        if (versionB == null) return false;
        if (versionA == null) return true;
        if (versionB.equals(versionA)) return false;

        String[] versionASplit = versionA.split("\\.");
        if (versionASplit.length != 3) return true;

        String[] versionBSplit = versionB.split("\\.");

        if (versionBSplit.length != 3) return false;

        int versionAMayor = Integer.parseInt(versionASplit[0]);
        int versionAMinor = Integer.parseInt(versionASplit[1]);
        int versionAPatch = Integer.parseInt(versionASplit[2]);

        int versionBMayor = Integer.parseInt(versionBSplit[0]);
        int versionBMinor = Integer.parseInt(versionBSplit[1]);
        int versionBPatch = Integer.parseInt(versionBSplit[2]);

        if (versionBMayor > versionAMayor) return true;
        if (versionBMayor == versionAMayor && versionBMinor > versionAMinor) return true;
        return versionBMayor == versionAMayor && versionBMinor == versionAMinor && versionBPatch > versionAPatch;
    }
}
