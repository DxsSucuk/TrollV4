package de.presti.trollv4.main;

import de.presti.trollv4.cmd.Delete;
import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.cmd.TabCompleter;
import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.listener.Event;
import de.presti.trollv4.listener.GuiListener;
import de.presti.trollv4.listener.ControlListener;
import de.presti.trollv4.logging.Logger;
import de.presti.trollv4.utils.control.Controls;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.plugin.Metrics;
import de.presti.trollv4.utils.plugin.PluginUtil;
import de.presti.trollv4.utils.plugin.UpdateChecker;
import de.presti.trollv4.utils.server.NPCUtil;
import de.presti.trollv4.utils.server.ServerInfo;
import de.presti.trollv4.utils.server.WorldCreator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main extends JavaPlugin {
    public static Main instance;
    public static Logger logger = new Logger();
    public UpdateChecker update;
    public static Controls control;
    public static String version;

    public void onEnable() {

        instance = this;
        version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        ArrayUtils.armor = new HashMap<>();
        ArrayUtils.inventory = new HashMap<>();
        ArrayUtils.cd = new ArrayList<>();

        ServerInfo.checkForServerSoftware();

        boolean need = ((Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) || (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) || (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) || (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") == null) || (!new File("plugins/TrollV4/giorno.nbs").exists()) || (!new File("plugins/TrollV4/rick.nbs").exists()));

        if (need) {
            logger.info("---------->");
            logger.info("We have detected that there is at least one missing needed File, we will now start to download it.");
            downloadAll();

            logger.info("---------->");

            try {
                if (Bukkit.getPluginManager().getPlugin("LibsDisguises") != null && Bukkit.getPluginManager().getPlugin("LibsDisguises").isEnabled()) {
                    new Controls();
                } else {

                    logger.info(" ");
                    logger.info("LibsDisguises is not installed or not enabled,");
                    logger.info("please install it and restart the server.");
                    logger.info(" ");
                    logger.info("---------->");
                }
            } catch (Exception ignore) {
            }

            try {
                if (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") != null && Bukkit.getPluginManager().getPlugin("NPCLibPlugin").isEnabled()) {
                    NPCUtil.init();
                } else {
                    logger.error("Please install the NPCLibPlugin for NPC support.");
                }
            } catch (Exception ignore) {
            }
        }

        new Language();
        new Items();
        new Config().init();
        new Config().init2();
        new Config().init3();

        try {
            Metrics metrics = new Metrics(this, 4690);
            metrics.addCustomChart(new Metrics.SimplePie("used_language", () -> Config.cfg.getString("Language")));
        } catch (Exception e) {
            logger.error("Error Main Metrics Custom Chart: " + e.getMessage());
        }

        Language.clearAll();
        Items.clearAll();
        new Language();
        new Items();
        updateConfig();

        if (Config.getconfig().getBoolean("UpdateChecker")) {
            update = new UpdateChecker(this);
            update.checkForUpdate();
        }

        startUp();
    }

    public void onDisable() {
        Language.clearAll();
        Items.clearAll();
    }

    public static void reloadConfigurations() {
        Language.clearAll();
        Items.clearAll();
        new Language();
        new Items();
        new Config().init();
        new Config().init2();
        new Config().init3();
        Language.clearAll();
        Items.clearAll();
        new Language();
        new Items();
    }

    public static void registerCommands() {
        instance.getCommand("troll").setExecutor(new Haupt());
        instance.getCommand("troll").setTabCompleter(new TabCompleter());
        instance.getCommand("delete").setExecutor(new Delete());
    }

    public void downloadAll() {

        if (!new File("plugins/TrollV4/rick.nbs").exists()) {
            logger.info("Downloading Rick.nbs!");
            download("https://cdn.azura.best/download/trollv4/uni/rick.nbs", "plugins/TrollV4/rick.nbs");
        }

        if (!new File("plugins/TrollV4/giorno.nbs").exists()) {
            logger.info("Downloading Giorno.nbs!");
            download("https://cdn.azura.best/download/trollv4/uni/giorno.nbs", "plugins/TrollV4/giorno.nbs");
        }

        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
            logger.info("Downloading ProtocolLib!");
            download("https://cdn.azura.best/download/trollv4/uni/ProtocolLib.jar", "plugins/ProtocolLib.jar");
        }

        if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) {
            logger.info("Downloading NoteBlockAPI!");
            download("https://cdn.azura.best/download/trollv4/uni/NoteBlockAPI.jar", "plugins/NoteBlockAPI.jar");
        }

        if (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") == null) {
            logger.info("Downloading NPCLib!");
            download("https://cdn.azura.best/download/trollv4/uni/npclib.jar", "plugins/npclib.jar");
        }

        if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
            logger.info("Downloading LibsDisguises!");
            if (version.toLowerCase().startsWith("v1_8")) {
                download("https://cdn.azura.best/download/trollv4/1-8/LibsDisguises.jar", "plugins/LibsDisguises.jar");
            } else {
                download("https://cdn.azura.best/download/trollv4/1-12-x/LibsDisguises.jar", "plugins/LibsDisguises.jar");
            }
        }

        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
            if (new File("plugins/ProtocolLib.jar").exists()) {
                logger.info("Trying to load ProtocolLib!");
                try {
                    PluginUtil.loadPlugin("ProtocolLib");
                    logger.info("Loaded ProtocolLib!");
                } catch (Exception e) {
                    logger.error("Couldn't load ProtocolLib!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") == null) {

            if (new File("plugins/npclib.jar").exists()) {
                logger.info("Trying to load NPCLib!");
                try {
                    PluginUtil.loadPlugin("npclib");
                    logger.info("Loaded NPCLib!");
                } catch (Exception e) {
                    logger.error("Couldn't load NPCLib!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
            if (new File("plugins/LibsDisguises.jar").exists()) {
                logger.info("Trying to load LibsDisguises!");
                try {
                    PluginUtil.loadPlugin("LibsDisguises");
                    logger.info("Loaded LibsDisguises!");
                } catch (Exception e) {
                    logger.error("Couldn't load LibsDisguises!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) {
            if (new File("plugins/NoteBlockAPI.jar").exists()) {
                logger.info("Trying to load NoteBlockAPI!");
                try {
                    PluginUtil.loadPlugin("NoteBlockAPI");
                    logger.info("Loaded NoteBlockAPI!");
                } catch (Exception e) {
                    logger.error("Couldn't load NoteBlockAPI!");
                }
            }
        }
    }

    public void updateConfig() {
        if (Config.cfg.getString("Plugin-Version") == null) {
            Config.getFile().delete();
            new Config().init();

            logger.info("Config broken recreating!");

        } else {

            if (!Config.cfg.getString("Plugin-Version").equalsIgnoreCase(Data.version)) {

                double confv = Double.parseDouble((Config.cfg.getString("Plugin-Version").replace("4.", "")));

                double pluginv = Double.parseDouble((Data.version.replace("4.", "")));

                if (confv > pluginv) {

                    logger.warning("Your Config is newer than the Plugin Version!");

                } else {

                    logger.info("Updating Config!");

                    Language.getLanguage();
                    String l = Language.getLanguage();
                    boolean cin = (Config.getconfig().get("Custom-Item-Name") != null && Config.getconfig().getBoolean("Custom-Item-Name"));
                    boolean uc = (Config.getconfig().get("AutoUpdate") != null && Config.getconfig().getBoolean("AutoUpdate"));
                    boolean autoup = (Config.getconfig().get("UpdateChecker") == null || Config.getconfig().getBoolean("UpdateChecker"));
                    boolean anim = (Config.getconfig().get("Animations") != null && Config.getconfig().getBoolean("Animations"));
                    boolean async = (Config.getconfig().get("ASync") != null && Config.getconfig().getBoolean("ASync"));
                    boolean cs = (Config.getconfig().get("Community-surprise") != null && Config.getconfig().getBoolean("Community-surprise"));
                    int hack = (Config.getconfig().get("trolls.hack.time") != null ? Config.getconfig().getInt("trolls.hack.time") : 15);
                    int fakeinv = (Config.getconfig().get("trolls.fakeinv.time") != null ? Config.getconfig().getInt("trolls.fakeinv.time") : 5);
                    int hands = (Config.getconfig().get("trolls.slipperyhands.time") != null ? Config.getconfig().getInt("trolls.slipperyhands.time") : 1);

                    int tnttrace = (Config.getconfig().get("trolls.tnttrace.spawndelay") != null ? Config.getconfig().getInt("trolls.tnttrace.spawndelay") : 2);

                    if (Config.cfg.getString("Plugin-Version").equalsIgnoreCase("4.3.8")) {
                        cs = true;
                    }

                    Config.getFile().delete();

                    Config.createFirstConfigWithValue((l.toUpperCase()), cin, uc, autoup, anim, async, cs, hack, fakeinv, hands, tnttrace);
                    logger.info("Config updated!");
                }
            }
        }
    }

    public static void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ControlListener(instance), instance);
        Bukkit.getPluginManager().registerEvents(new Event(), instance);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), instance);
    }

    public static void sendPluginMessage() {
        logger.info("-----------------------------------");
        logger.info("TrollV" + Data.version + " by Presti");
        logger.info("In case of errors please report:");
        logger.info("Email: presti@presti.me");
        logger.info("YouTube: Not Memerinoto");
        logger.info("Otherwise have fun!");
        logger.info("------------------------------------");
        logger.info("Plugin Version: " + Data.version);
        logger.info("Server Version: " + version + " - " + ServerInfo.getMcVersion());
        logger.info("Server Software: " + ServerInfo.getServerSoftware());
    }

    public static void startUp() {
        sendPluginMessage();
        registerListeners();
        registerCommands();

        if (Config.getconfig().getBoolean("Community-surprise")) {
            logger.info("Community Surprise is enabled!\n" + "This means your Server address will be shared with us!\n" + "If you do not want this, please disable it in the config!");
            try {
                URLConnection con = new URL("https://cdn.azura.best/trollv4/community").openConnection();
                con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0 Port/" + Bukkit.getPort());
                con.getInputStream();
            } catch (Exception exception) {
                if (exception instanceof FileNotFoundException) {
                    logger.info("Connection with the Server was successful!\n" +
                            "This means your Server address will be shared with us!\n" +
                            "If you do not want this, please disable it in the config!\n");
                } else {
                    logger.warning("Couldn't send a Request to the Community Server!\n" +
                            "This means your Server address will not be shared with us!\n" +
                            "If you do not want this, please disable it in the config!\n" +
                            "Error: " + exception.getMessage());
                }
            }
        } else {
            logger.info("Community Surprise is disabled!");
        }

        if (Bukkit.getWorld("SpookyWorld") == null) {
            WorldCreator.createWorld("SpookyWorld");
        }
    }

    public int getRandom(int lower, int upper) {
        return new Random().nextInt(upper - lower + 1) + lower;
    }

    public static String getRandomID() {
        StringBuilder str = new StringBuilder();
        int lastrandom = 0;
        for (int i = 0; i < 9; i++) {
            Random r = new Random();
            int rand = r.nextInt(9);
            while (rand == lastrandom) {
                rand = r.nextInt(9);
            }
            lastrandom = rand;
            str.append(rand);
        }
        return str.toString();
    }

    public static Main getPlugin() {
        return instance;
    }

    public static void startControlling(Player v, Player c) {
        control.startControlling(v, c);
    }

    public static void stopControlling(Player v, Player c) {
        control.stopControlling(v, c);
    }

    public static boolean download(String url, String FileName) {
        try {
            URLConnection con = new URL(url).openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            InputStream in = con.getInputStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(in);
            FileOutputStream fileOutputStream = new FileOutputStream(FileName);
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fileChannel.close();
            fileOutputStream.close();
            readableByteChannel.close();
            in.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
