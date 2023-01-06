package de.presti.trollv4.utils.plugin;

import de.presti.trollv4.logging.Logger;
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
        Main.instance.update = this;
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
                        e.printStackTrace();
                        cancel();
                        return;
                    }

                    if (localPluginVersion.equals(spigotPluginVersion)) {
                        Logger.info("TrollV4 Has no Update");
                    } else {
                        Logger.warning("TrollV4 has a Update!");
                        Logger.warning("New Version: " + spigotPluginVersion);
                        Logger.warning("Your Version: " + Data.version);
                        Logger
                                .warning("Download here: https://www.spigotmc.org/resources/" + ID + "/updates");
                    }
                    cancel(); // Cancel the runnable as an update has been found.
                });
            }
        }.runTaskTimer(javaPlugin, 20, CHECK_INTERVAL);
    }
}
