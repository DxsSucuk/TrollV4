package de.presti.trollv4.utils.server;

import de.presti.trollv4.main.TrollV4;
import org.bukkit.Bukkit;
import org.bukkit.WorldType;

public class WorldCreator {

    @SuppressWarnings("deprecation")
    public static void createWorld(String name) {
        Bukkit.getScheduler().runTask(TrollV4.getInstance(), () -> {
            Bukkit.createWorld(new org.bukkit.WorldCreator(name));
            try {
                Bukkit.getWorld(name).setGameRuleValue("doDaylightCycle", "false");
                Bukkit.getWorld(name).setGameRuleValue("doMobSpawning", "false");
            } catch (Exception ignore) {
            }
        });
    }

    public static void createWorld(String name, WorldType type) {
        Bukkit.getScheduler().runTask(TrollV4.getInstance(), () -> {
            org.bukkit.WorldCreator wc = new org.bukkit.WorldCreator(name);
            if (type != null) wc.type(type);
            Bukkit.createWorld(wc);
        });
    }
}
