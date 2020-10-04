package de.presti.trollv4.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import de.presti.trollv4.utils.*;
import de.presti.trollv4.listener.*;
import de.presti.trollv4.cmd.*;

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
	public static Main plugin;
	public static Main instance;
	public static HashMap<String, ItemStack[]> inventory;
	public static HashMap<String, ItemStack[]> armor;
	public static List<String> cd;
	public Logger logg = Logger.getLogger("Minecraft");
	public UpdateChecker update;
	public static Controls control;
	public static String version;

	// Update Config
	public static String Lang;
	public static boolean autoup;
	public static boolean anim;
	public static boolean unsup;
	public static boolean cmsup;

	public void onEnable() {

		instance = this;
		plugin = this;
		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		armor = new HashMap<String, ItemStack[]>();
		inventory = new HashMap<String, ItemStack[]>();
		cd = new ArrayList<String>();

		if (!version.equals("v1_8_R3") && !version.equals("v1_9_R2") && !Config.getconfig().getBoolean("Unsupport")) {

			new Language();
			new Config().init();
			new Config().init2();
			Language.clearAll();

			Bukkit.getConsoleSender().sendMessage("§6>");
			Bukkit.getConsoleSender().sendMessage("§6TrollV4 is mainly Developed for 1.8 and 1.9!");
			Bukkit.getConsoleSender().sendMessage("§6Pls set Unsupport to True in the Config!");
			Bukkit.getConsoleSender().sendMessage("§6Incase of Errors!");
			Bukkit.getConsoleSender().sendMessage("§6Discord: 平和#0240");
			Bukkit.getConsoleSender().sendMessage("§6>");
			setEnabled(false);

		} else {
			if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null
					|| Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
				Bukkit.getConsoleSender().sendMessage("§6--------------------");
				Bukkit.getConsoleSender().sendMessage("§6PLS INSTALL!");
				Bukkit.getConsoleSender().sendMessage("§6ProtocolLib");
				Bukkit.getConsoleSender().sendMessage("§6and");
				Bukkit.getConsoleSender().sendMessage("§6LibsDisguises");
				Bukkit.getConsoleSender().sendMessage("§6PLS INSTALL!");
				Bukkit.getConsoleSender().sendMessage("§6--------------------");
				this.setEnabled(false);
			}
			new Controls();
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
	}

	public void onDisable() {
		Language.clearAll();
	}

	public static void CMD() {
		instance.getCommand("troll").setExecutor(new Haupt());
	}

	public void updateConfig() {
		if (Config.cfg.getString("Plugin-Version") == null) {
			Config.getFile().delete();
			new Config().init();
		} else {
			if (Config.cfg.getString("Plugin-Version").equalsIgnoreCase(Data.version)) {

			} else {
				
				String oldl = Language.getLanguage();
				
				Config.getFile().delete();
				new Config().init();
				Config.cfg.set("language", oldl);
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
		System.out.println("TrollV4 by Presti");
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
		if (Config.getconfig().getBoolean("Community-surprise")) {
			Community.host = "servertrollv4.dev-presti.tk";
			Community.port = 187;
			try {
				Community.run();
			} catch (IOException e) {
				System.out.println("Error at connecting to the Cloud!");
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

	public static String test() {
		String str = "";
		int lastrandom = 0;
		for (int i = 0; i < 1; i++) {
			Random r = new Random();
			int rand = r.nextInt(1);
			while (rand == lastrandom) {
				rand = r.nextInt(1);
			}
			lastrandom = rand;
			str = str + rand;
		}
		return str;
	}

	public static Main getPlugin() {
		return plugin;
	}

	public static void startControlling(Player v, Player c) {
		control.startControlling(v, c);
	}

	public static void stopControlling(Player v, Player c) {
		control.stopControlling(v, c);
	}

}
