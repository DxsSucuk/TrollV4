package de.presti.trollv4.utils.server;

import de.presti.trollv4.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.WorldType;

public class WorldCreator {

    @SuppressWarnings("deprecation")
    public static void createWorld(String name) {
        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
            Bukkit.createWorld(new org.bukkit.WorldCreator(name));
            try {
                Bukkit.getWorld(name).setGameRuleValue("doDaylightCycle", "false");
                Bukkit.getWorld(name).setGameRuleValue("doMobSpawning", "false");
            } catch (Exception ex) {
            }
        });
    }

    public static void createWorld(String name, WorldType type) {
        Bukkit.getScheduler().runTask(Main.getPlugin(), () -> {
            org.bukkit.WorldCreator wc = new org.bukkit.WorldCreator(name);
            if (type != null) wc.type(type);
            Bukkit.createWorld(wc);
        });
    }
}
