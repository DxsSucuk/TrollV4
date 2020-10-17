package de.presti.trollv4.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import net.md_5.bungee.api.ChatColor;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 22.04.2019 / 19:55:46												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class UpdateChecker {

	public final JavaPlugin javaPlugin;
	public final String localPluginVersion;
	public String spigotPluginVersion;

	public static final int ID = 67318;
	public static String ERR_MSG = "&cUpdate checker failed!";
	public static final long CHECK_INTERVAL = 12_000; // In ticks.
	public int nvi;
	public int cvi;

	public UpdateChecker(final JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
		this.localPluginVersion = Data.version;
		Main.instance.update = this;
		ERR_MSG = "&cUpdate checker failed!";
	}

	public void checkForUpdate() {
		new BukkitRunnable() {
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
						Bukkit.getServer().getConsoleSender()
								.sendMessage(ChatColor.translateAlternateColorCodes('&', ERR_MSG));
						e.printStackTrace();
						cancel();
						return;
					}

					try {
					
					cvi = Integer.parseInt(((localPluginVersion).replaceAll(".", "")));
					nvi = Integer.parseInt(((spigotPluginVersion).replaceAll(".", "")));
					} catch(Exception ex) {
						
					}
					
					if (localPluginVersion.equals(spigotPluginVersion) || cvi > nvi) {
						Main.instance.logg.info(ChatColor.translateAlternateColorCodes('&', "&4TrollV4 Has no Update"));
					} else {
						Bukkit.getServer().getConsoleSender()
								.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6TrollV4 has a Update!"));
						Bukkit.getServer().getConsoleSender().sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&6New Version: " + spigotPluginVersion));
						Bukkit.getServer().getConsoleSender().sendMessage(
								ChatColor.translateAlternateColorCodes('&', "&6Your Version: " + Data.version));
						Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&6Download here: https://www.spigotmc.org/resources/" + ID + "/updates"));
					}
					cancel(); // Cancel the runnable as an update has been found.
				});
			}
		}.runTaskTimer(javaPlugin, 60, CHECK_INTERVAL);
	}
}
