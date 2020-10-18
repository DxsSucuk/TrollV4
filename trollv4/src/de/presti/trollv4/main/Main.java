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
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.listener.Event;
import de.presti.trollv4.listener.GuiListener;
import de.presti.trollv4.listener.iListener;
import de.presti.trollv4.utils.ArrayUtils;
import de.presti.trollv4.utils.Metrics;
import de.presti.trollv4.utils.UpdateChecker;
import de.presti.trollv4.utils.control.Controls;

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
	public Logger logg = Logger.getLogger("Minecraft");
	public UpdateChecker update;
	public static Controls control;
	public static String version;

	public void onEnable() {

		instance = this;
		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		ArrayUtils.armor = new HashMap<String, ItemStack[]>();
		ArrayUtils.inventory = new HashMap<String, ItemStack[]>();
		ArrayUtils.cd = new ArrayList<String>();

		boolean need = (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null
				|| Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null
				|| Bukkit.getPluginManager().getPlugin("LibsDisguises") == null);
		downloadAll();

		if (Bukkit.getPluginManager().getPlugin("LibsDisguises") != null) {
			new Controls();
		} else {
			System.out.println("---------->");
			System.out.println(" ");
			System.out.println("Pls restart the Server");
			System.out.println("Because of the Libs Plugin");
			System.out.println(" ");
		}

		if (need)
			System.out.println("---------->");

		new Language();
		new Config().init();
		new Config().init2();

		try {
			Metrics metrics = new Metrics(this, 4690);
			metrics.addCustomChart(new Metrics.SimplePie("used_language", () -> Config.cfg.getString("Language")));
		} catch (Exception e) {
			System.out.println("Error Main Metrics Custom Chart: " + e.getMessage());
		}

		Language.clearAll();
		new Language();
		updateConfig();

		if (Config.getconfig().getBoolean("AutoUpdate")) {
			update = new UpdateChecker(this);
			update.checkForUpdate();
		}

		Startup();
	}

	public void onDisable() {
		Language.clearAll();
	}

	public static void CMD() {
		instance.getCommand("troll").setExecutor(new Haupt());
	}

	public void downloadAll() {
		boolean need = (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null
				|| Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null
				|| Bukkit.getPluginManager().getPlugin("LibsDisguises") == null);

		if (need)
			System.out.println("---------->");

		if (!new File("plugins/TrollV4/rick.nbs").exists()) {
			Bukkit.getConsoleSender().sendMessage("Downloading Rick.nbs!");
			download("https://trollv4.000webhostapp.com/download/uni/rick.nbs", "plugins/TrollV4/rick.nbs");
		}

		if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null) {
			Bukkit.getConsoleSender().sendMessage("Downloading ProtocolLib!");
			download("https://trollv4.000webhostapp.com/download/uni/ProtocolLib.jar", "plugins/ProtocolLib.jar");
		}

		if (Bukkit.getPluginManager().getPlugin("NoteBlockAPI") == null) {
			Bukkit.getConsoleSender().sendMessage("Downloading NoteBlockAPI!");
			download("https://trollv4.000webhostapp.com/download/uni/NoteBlockAPI.jar", "plugins/NoteBlockAPI.jar");
		}

		if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
			Bukkit.getConsoleSender().sendMessage("Downloading LibsDisguises!");
			if (version.toLowerCase().startsWith("v1_8")) {
				download("https://trollv4.000webhostapp.com/download/1-8/LibsDisguises.jar",
						"plugins/LibsDisguises.jar");
			} else {
				download("https://trollv4.000webhostapp.com/download/1-12-x/LibsDisguises.jar",
						"plugins/LibsDisguises.jar");
			}
		}
	}

	public void updateConfig() {
		if (Config.cfg.getString("Plugin-Version") == null) {
			Config.getFile().delete();
			new Config().init();
			
			System.out.println("Config broken recreating!");
			
		} else {
			if (!Config.cfg.getString("Plugin-Version").equalsIgnoreCase(Data.version)) {

				System.out.print("Updating Config!");
				
				String l = Language.getLanguage();
				boolean autoup = Config.getconfig().getBoolean("AutoUpdate");
				boolean anim = Config.getconfig().getBoolean("Animations");
				boolean cs = Config.getconfig().getBoolean("Community-surprise");
				int hack = Config.getconfig().getInt("trolls.hack.time");
				int fakeinv = Config.getconfig().getInt("trolls.fakeinv.time");
				int hands = Config.getconfig().getInt("trolls.slipperyhands.time");
				
				Config.getFile().delete();
				
				Config.createFirstConfigWithValue((l.toUpperCase()), autoup, anim, cs, hack, fakeinv, hands);
				System.out.print("Config updated!");
			}
			
		}
	}

	public static void Listener() {
		Bukkit.getPluginManager().registerEvents(new iListener(instance), instance);
		Bukkit.getPluginManager().registerEvents(new Event(), instance);
		Bukkit.getPluginManager().registerEvents(new GuiListener(), instance);
	}

	public static void Message() {
		System.out.println("-----------------------------------");
		System.out.println("TrollV" + Data.version + " by Presti");
		System.out.println("In case of errors please report");
		System.out.println("Skype: DxsSucuk@hotmail.com");
		System.out.println("YouTube: Not Memerinoto");
		System.out.println("Instagram: Memerinoto");
		System.out.println("Otherwise have fun");
		System.out.println("------------------------------------");
		System.out.println("Online Changelog: " + instance.getDescription().getWebsite());
		System.out.println("Plugin Version: " + Data.version);
		System.out.println("Server Version: " + version);
	}

	public static void Startup() {
		Message();
		Listener();
		CMD();
		/*
		 * if (Config.getconfig().getBoolean("Community-surprise")) { Community.host =
		 * "servertrollv4.dev-presti.tk"; Community.port = 187; try { Community.run(); }
		 * catch (IOException e) {
		 * System.out.println("Error at connecting to the Cloud!"); } }
		 */
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
			URL link = new URL(url);
			URLConnection con = link.openConnection();
			con.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = con.getInputStream();
			ReadableByteChannel readableByteChannel = Channels.newChannel(in);
			FileOutputStream fileOutputStream = new FileOutputStream(FileName);
			FileChannel fileChannel = fileOutputStream.getChannel();
			fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
			fileChannel.close();
			fileOutputStream.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
