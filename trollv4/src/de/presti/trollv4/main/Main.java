package de.presti.trollv4.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import de.presti.trollv4.cmd.*;
import de.presti.trollv4.config.*;
import de.presti.trollv4.listener.*;
import de.presti.trollv4.logging.*;
import de.presti.trollv4.utils.*;
import de.presti.trollv4.utils.control.*;
import de.presti.trollv4.utils.player.*;
import de.presti.trollv4.utils.plugin.*;
import de.presti.trollv4.utils.server.NPCUtil;
import de.presti.trollv4.utils.server.ServerInfo;
import net.jitse.npclib.NPCLib;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 11.05.2019 / 18:17:20												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich gesch§tzt.			*
*	Das Urheberrecht liegt, soweit nicht ausdr§cklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielf§ltigung, Verbreitung, Vermietung, Verleihung,			*
*	§ffentlichen Zug§nglichmachung oder anderer Nutzung							*
*	bedarf der ausdr§cklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Main extends JavaPlugin {
	public static Main instance;
	public static Logger logger = new Logger();
	public UpdateChecker update;
	public static Controls control;
	public static String version;

	public void onEnable() {

		instance = this;
		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		ArrayUtils.armor = new HashMap<String, ItemStack[]>();
		ArrayUtils.inventory = new HashMap<String, ItemStack[]>();
		ArrayUtils.cd = new ArrayList<String>();
		
		ServerInfo.checkForServerSoftware();
		
		boolean need = ((Bukkit.getPluginManager().getPlugin("ProtocolLib") == null)
				|| (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null)
				|| (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) || (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") == null)
				|| (!new File("plugins/TrollV4/rick.nbs").exists()));

		downloadAll();

		if (Bukkit.getPluginManager().getPlugin("LibsDisguises") != null) {
			new Controls();
		} else {

			logger.info("---------->");
			logger.info(" ");
			logger.info("Pls restart the Server");
			logger.info("Because of the Libs Plugin");
			logger.info(" ");
		}

		if (need)
			logger.info("---------->");

		if (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") != null) {
			NPCUtil.init();
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

		Startup();
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

	public static void CMD() {
		instance.getCommand("troll").setExecutor(new Haupt());
		instance.getCommand("troll").setTabCompleter(new TabCompleter());
		instance.getCommand("delete").setExecutor(new Delete());
	}

	public void downloadAll() {
		boolean need = ((Bukkit.getPluginManager().getPlugin("ProtocolLib") == null)
				|| (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null)
				|| (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null)
				|| (!new File("plugins/TrollV4/rick.nbs").exists()));

		if (need)
			logger.info("---------->");

		if (!new File("plugins/TrollV4/rick.nbs").exists()) {
			logger.info("Downloading Rick.nbs!");
			download("https://trollv4.000webhostapp.com/download/uni/rick.nbs", "plugins/TrollV4/rick.nbs");
		}

		if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
			logger.info("Downloading ProtocolLib!");
			download("https://trollv4.000webhostapp.com/download/uni/ProtocolLib.jar", "plugins/ProtocolLib.jar");
		}

		if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) {
			logger.info("Downloading NoteBlockAPI!");
			download("https://trollv4.000webhostapp.com/download/uni/NoteBlockAPI.jar", "plugins/NoteBlockAPI.jar");
		}
		
		if (Bukkit.getPluginManager().getPlugin("NPCLibPlugin") == null) {
			logger.info("Downloading NPCLib!");
			download("https://trollv4.000webhostapp.com/download/uni/npclib.jar", "plugins/npclib.jar");
		}

		if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
			logger.info("Downloading LibsDisguises!");
			if (version.toLowerCase().startsWith("v1_8")) {
				download("https://trollv4.000webhostapp.com/download/1-8/LibsDisguises.jar",
						"plugins/LibsDisguises.jar");
			} else {
				download("https://trollv4.000webhostapp.com/download/1-12-x/LibsDisguises.jar",
						"plugins/LibsDisguises.jar");
			}
		}

		if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
			if (new File("plugins/ProtocolLib.jar").exists()) {
				logger.info("Trying to load ProtocolLib!");
				try {
					PluginUtil.loadPlugin("ProtocolLib");
					logger.info("Loaded ProtocolLib!");
				} catch (Exception e) {
					logger.error("Coudlnt load ProtocolLib!");
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
					logger.error("Coudlnt load NPCLib!");
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
					logger.error("Coudlnt load LibsDisguises!");
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
					logger.error("Coudlnt load NoteBlockAPI!");
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

				double confv = Double.valueOf((Config.cfg.getString("Plugin-Version").replace("4.", "")));

				double pluginv = Double.valueOf((Data.version.replace("4.", "")));

				if (confv > pluginv) {

					logger.warning("Your Config is newer than the Plugin Version!");

				} else {

					logger.info("Updating Config!");

					String l = (Language.getLanguage() != null ? Language.getLanguage() : "US");
					boolean cin = (Config.getconfig().get("Custom-Item-Name") != null
							? Config.getconfig().getBoolean("Custom-Item-Name")
							: false);
					boolean uc = (Config.getconfig().get("AutoUpdate") != null
							? Config.getconfig().getBoolean("AutoUpdate")
							: false);
					boolean autoup = (Config.getconfig().get("UpdateChecker") != null
							? Config.getconfig().getBoolean("UpdateChecker")
							: true);
					boolean anim = (Config.getconfig().get("Animations") != null
							? Config.getconfig().getBoolean("Animations")
							: false);
					boolean async = (Config.getconfig().get("ASync") != null ? Config.getconfig().getBoolean("ASync")
							: false);
					boolean cs = (Config.getconfig().get("Community-surprise") != null
							? Config.getconfig().getBoolean("Community-surprise")
							: true);
					int hack = (Config.getconfig().get("trolls.hack.time") != null
							? Config.getconfig().getInt("trolls.hack.time")
							: 15);
					int fakeinv = (Config.getconfig().get("trolls.fakeinv.time") != null
							? Config.getconfig().getInt("trolls.fakeinv.time")
							: 5);
					int hands = (Config.getconfig().get("trolls.slipperyhands.time") != null
							? Config.getconfig().getInt("trolls.slipperyhands.time")
							: 1);

					int tnttrace = (Config.getconfig().get("trolls.tnttrace.spawndelay") != null
							? Config.getconfig().getInt("trolls.tnttrace.spawndelay")
							: 2);

					if (Config.cfg.getString("Plugin-Version").equalsIgnoreCase("4.3.8")) {
						cs = true;
					}

					Config.getFile().delete();

					Config.createFirstConfigWithValue((l.toUpperCase()), cin, uc, autoup, anim, async, cs, hack,
							fakeinv, hands, tnttrace);
					logger.info("Config updated!");
				}
			}
		}
	}

	public static void Listener() {
		Bukkit.getPluginManager().registerEvents(new iListener(instance), instance);
		Bukkit.getPluginManager().registerEvents(new Event(), instance);
		Bukkit.getPluginManager().registerEvents(new GuiListener(), instance);
	}

	public static void Message() {
		logger.info("-----------------------------------");
		logger.info("TrollV" + Data.version + " by Presti");
		logger.info("In case of errors please report");
		logger.info("Skype: DxsSucuk@hotmail.com");
		logger.info("YouTube: Not Memerinoto");
		logger.info("Instagram: Memerinoto");
		logger.info("Otherwise have fun");
		logger.info("------------------------------------");
		logger.info("Online Changelog: " + instance.getDescription().getWebsite());
		logger.info("Plugin Version: " + Data.version);
		logger.info("Server Version: " + version + " - " + ServerInfo.getMcVersion());
		logger.info("Server Software: " + ServerInfo.getServerSoftware());
	}

	public static void Startup() {
		Message();
		Listener();
		CMD();

		if (Config.getconfig().getBoolean("Community-surprise")) {
			Community.host = "trollv4.dev-presti.tk";
			Community.port = 6918;
			try {
				Community.run();
			} catch (IOException e) {
				logger.info("Error at connecting to the Cloud!");
			}
		}
	}

	public int getRandom(int lower, int upper) {
		Random r = new Random();
		int toreturn = r.nextInt(upper - lower + 1) + lower;

		return toreturn;
	}

	public static String getRandomID() {
		String str = "";
		int lastrandom = 0;
		for (int i = 0; i < 9; i++) {
			Random r = new Random();
			int rand = r.nextInt(9);
			while (rand == lastrandom) {
				rand = r.nextInt(9);
			}
			lastrandom = rand;
			str = str + rand;
		}
		return str;
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
			con.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
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

	public static void updater(String newerversion) {

		double spigotver = Double.valueOf(newerversion.replace("4.", ""));

		double pluginv = Double.valueOf((Data.version.replace("4.", "")));

		if (spigotver > pluginv) {
			File f = new File("plugins/TrollV4Updater.jar");

			if (!f.exists()) {
				if (download("https://trollv4.000webhostapp.com/download/uni/TrollV4Updater.jar",
						"plugins/TrollV4Updater.jar")) {
					logger.info("Downlaoded the updater!");
					new BukkitRunnable() {
						@Override
						public void run() {
							try {
								Plugin pl = Bukkit.getPluginManager().loadPlugin(f);
								pl.onLoad();

								Bukkit.getPluginManager().enablePlugin(pl);
							} catch (Exception e) {
								logger.info("Failed to Load the Updater!");
								logger.info("Error: " + e.getMessage());
							}
						}
					}.runTaskLater(getPlugin(), 20L);
				}
			}
		}
	}

}
