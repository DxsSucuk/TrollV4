package de.presti.trollv4.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.config.Config;
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
						Main.instance.logger.error(ERR_MSG);
						e.printStackTrace();
						cancel();
						return;
					}

					if (localPluginVersion.equals(spigotPluginVersion)) {
						Main.instance.logger.info("TrollV4 Has no Update");
					} else {
						Main.instance.logger.warning("TrollV4 has a Update!");
						if (!Main.instance.config.config.getBoolean("AutoUpdate")) {
							Main.instance.logger.warning("New Version: " + spigotPluginVersion);
							Main.instance.logger.warning("Your Version: " + Data.version);
							Main.instance.logger
									.warning("Download here: https://www.spigotmc.org/resources/" + ID + "/updates");
						} else {
							Main.instance.logger.info("AutoUpdate is enabled! Downloading it now!");
							FileUtil.updater(spigotPluginVersion);
						}
					}
					cancel(); // Cancel the runnable as an update has been found.
				});
			}
		}.runTaskTimer(javaPlugin, 20, CHECK_INTERVAL);
	}
}