package de.presti.trollv4.api;

import de.presti.trollv4.main.TrollV4;
import io.sentry.Sentry;

public class Packets {

	public static Class<?> getNMSClass(String name) {
		String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().substring(org.bukkit.Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1, org.bukkit.Bukkit.getServer().getClass().getPackage().getName().length());
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			Sentry.captureException(e);
			TrollV4.getInstance().getLogger().warning("Your Server isn't supporting this Packet! (" + name + ")");
		}
		return null;
	}
}
