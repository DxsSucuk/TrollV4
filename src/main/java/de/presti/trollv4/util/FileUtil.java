package de.presti.trollv4.util;

import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.util.server.PluginUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Random;

public class FileUtil {

    public static boolean download(String url, String FileName) {
        try {
            URLConnection con = new URL(url).openConnection();
            con.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0 TrollV4/" + Data.version);
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
        } catch (IOException e) {
            return false;
        }
    }

    public void updateConfig() {
        if (Main.instance.config.config.getString("Plugin-Version") == null) {
            Main.instance.config.getConfigFile().delete();
            
            Main.instance.config.initConfig();

            Main.instance.logger.info("Config broken recreating!");

        } else {

            if (!Main.instance.config.config.getString("Plugin-Version").equalsIgnoreCase(Data.version)) {

                double confv = Double.valueOf((Main.instance.config.config.getString("Plugin-Version").replace("4.", "")));

                double pluginv = Double.valueOf((Data.version.replace("4.", "")));

                if (confv > pluginv) {

                    Main.instance.logger.warning("Your Config is newer than the Plugin Version!");

                } else {

                    Main.instance.logger.info("Updating Config!");

                    String l = (Language.getLanguage() != null ? Language.getLanguage() : "US");

                    boolean cin = (Main.instance.config.config.get("Custom-Item-Name") != null && Main.instance.config.config.getBoolean("Custom-Item-Name"));

                    boolean uc = (Main.instance.config.config.get("AutoUpdate") != null && Main.instance.config.config.getBoolean("AutoUpdate"));

                    boolean autoup = (Main.instance.config.config.get("UpdateChecker") == null || Main.instance.config.config.getBoolean("UpdateChecker"));

                    boolean anim = (Main.instance.config.config.get("Animations") != null && Main.instance.config.config.getBoolean("Animations"));

                    boolean async = (Main.instance.config.config.get("ASync") != null && Main.instance.config.config.getBoolean("ASync"));

                    boolean cs = (Main.instance.config.config.get("Community-surprise") != null && Main.instance.config.config.getBoolean("Community-surprise"));

                    int hack = (Main.instance.config.config.get("trolls.hack.time") != null
                            ? Main.instance.config.config.getInt("trolls.hack.time")
                            : 15);
                    int fakeinv = (Main.instance.config.config.get("trolls.fakeinv.time") != null
                            ? Main.instance.config.config.getInt("trolls.fakeinv.time")
                            : 5);
                    int hands = (Main.instance.config.config.get("trolls.slipperyhands.time") != null
                            ? Main.instance.config.config.getInt("trolls.slipperyhands.time")
                            : 1);

                    int tnttrace = (Main.instance.config.config.get("trolls.tnttrace.spawndelay") != null
                            ? Main.instance.config.config.getInt("trolls.tnttrace.spawndelay")
                            : 2);

                    if (Main.instance.config.config.getString("Plugin-Version").equalsIgnoreCase("4.3.8")) {
                        cs = true;
                    }

                    Main.instance.config.getConfigFile().delete();

                    Main.instance.config.createFirstConfigWithValue((l.toUpperCase()), cin, uc, autoup, anim, async, cs, hack,
                            fakeinv, hands, tnttrace);

                    Main.instance.logger.info("Config updated!");
                }
            }
        }
    }

    public static void updater(String newerversion) {

        double spigotver = Double.valueOf(newerversion.replaceAll("4.", ""));

        double pluginv = Double.valueOf((Data.version.replaceAll("4.", "")));

        if (spigotver > pluginv) {
            File f = new File("plugins/TrollV4Updater.jar");

            if (!f.exists()) {
                if (download("https://trollv4.000webhostapp.com/download/uni/TrollV4Updater.jar",
                        "plugins/TrollV4Updater.jar")) {
                    Main.instance.logger.info("Downlaoded the updater!");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            try {
                                Plugin pl = Bukkit.getPluginManager().loadPlugin(f);
                                pl.onLoad();

                                Bukkit.getPluginManager().enablePlugin(pl);
                            } catch (Exception e) {
                                Main.instance.logger.info("Failed to Load the Updater!");
                                Main.instance.logger.info("Error: " + e.getMessage());
                            }
                        }
                    }.runTaskLater(Main.instance, 20L);
                }
            }
        }
    }

    public void downloadAll() {
        boolean need = ((Bukkit.getPluginManager().getPlugin("ProtocolLib") == null)
                || (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null)
                || (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null)
                || (!new File("plugins/TrollV4/rick.nbs").exists()));

        if (need)
            Main.instance.logger.info("---------->");

        if (!new File("plugins/TrollV4/rick.nbs").exists()) {
            Main.instance.logger.info("Downloading Rick.nbs!");
            download("https://trollv4.000webhostapp.com/download/uni/rick.nbs", "plugins/TrollV4/rick.nbs");
        }

        if (!new File("plugins/TrollV4/giorno.nbs").exists()) {
            Main.instance.logger.info("Downloading Giorno.nbs!");
            download("https://trollv4.000webhostapp.com/download/uni/giorno.nbs", "plugins/TrollV4/giorno.nbs");
        }

        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
            Main.instance.logger.info("Downloading ProtocolLib!");
            download("https://trollv4.000webhostapp.com/download/uni/ProtocolLib.jar", "plugins/ProtocolLib.jar");
        }

        if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) {
            Main.instance.logger.info("Downloading NoteBlockAPI!");
            download("https://trollv4.000webhostapp.com/download/uni/NoteBlockAPI.jar", "plugins/NoteBlockAPI.jar");
        }

        if (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") == null) {
            Main.instance.logger.info("Downloading NPCLib!");
            download("https://trollv4.000webhostapp.com/download/uni/npclib.jar", "plugins/npclib.jar");
        }

        if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
            Main.instance.logger.info("Downloading LibsDisguises!");
            if (Main.instance.version.toLowerCase().startsWith("v1_8")) {
                download("https://trollv4.000webhostapp.com/download/1-8/LibsDisguises.jar",
                        "plugins/LibsDisguises.jar");
            } else {
                download("https://trollv4.000webhostapp.com/download/1-12-x/LibsDisguises.jar",
                        "plugins/LibsDisguises.jar");
            }
        }

        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
            if (new File("plugins/ProtocolLib.jar").exists()) {
                Main.instance.logger.info("Trying to load ProtocolLib!");
                try {
                    PluginUtil.loadPlugin("ProtocolLib");
                    Main.instance.logger.info("Loaded ProtocolLib!");
                } catch (Exception e) {
                    Main.instance.logger.error("Coudlnt load ProtocolLib!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") == null) {

            if (new File("plugins/npclib.jar").exists()) {
                Main.instance.logger.info("Trying to load NPCLib!");
                try {
                    PluginUtil.loadPlugin("npclib");
                    Main.instance.logger.info("Loaded NPCLib!");
                } catch (Exception e) {
                    Main.instance.logger.error("Coudlnt load NPCLib!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
            if (new File("plugins/LibsDisguises.jar").exists()) {
                Main.instance.logger.info("Trying to load LibsDisguises!");
                try {
                    PluginUtil.loadPlugin("LibsDisguises");
                    Main.instance.logger.info("Loaded LibsDisguises!");
                } catch (Exception e) {
                    Main.instance.logger.error("Coudlnt load LibsDisguises!");
                }
            }
        }

        if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) {
            if (new File("plugins/NoteBlockAPI.jar").exists()) {
                Main.instance.logger.info("Trying to load NoteBlockAPI!");
                try {
                    PluginUtil.loadPlugin("NoteBlockAPI");
                    Main.instance.logger.info("Loaded NoteBlockAPI!");
                } catch (Exception e) {
                    Main.instance.logger.error("Coudlnt load NoteBlockAPI!");
                }
            }
        }
    }

    public static String getRandomID(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            Random r = new Random();
            int rand = r.nextInt(9);
            str = str + rand;
        }
        return str;
    }


}
