package de.presti.trollv4.main;


import de.presti.trollv4.config.Config;
import de.presti.trollv4.util.UpdateChecker;
import de.presti.trollv4.util.player.ControlUtil;
import de.presti.trollv4.util.server.Logger;
import de.presti.trollv4.util.server.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public Logger logger = new Logger();
    public UpdateChecker update;
    public Config config;
    public static ControlUtil control;
    public static String version;

    public void onEnable() {
        instance = this;
        version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        ServerInfo.checkForServerSoftware();
    }

    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }

}
