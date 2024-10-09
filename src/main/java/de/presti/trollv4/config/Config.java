package de.presti.trollv4.config;

import java.io.File;
import java.io.IOException;

import de.presti.trollv4.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.presti.trollv4.main.Data;

public class Config {

	public static FileConfiguration config;
	public static FileConfiguration itemFileConfig;
	
	public static void init() {
		config = getConfig();
		if(!getFile().exists()) {
			config.options().copyDefaults(true);
			config.options().copyHeader(true);
			config.options().header("###############################\n" +
					"#                             #\n" + 
					"# TrollV4 by Presti           #\n" + 
					"# Configuration File " + Data.version + "    #\n" + 
					"#                             #\n" + 
					"###############################");
			config.addDefault("Plugin-Version", Data.version);
			config.addDefault("Language", "US");
			config.addDefault("Custom-Item-Name", false);
			config.addDefault("UpdateChecker", true);
			config.addDefault("AutoUpdate", false);
			config.addDefault("Animations", false);
			config.addDefault("ASync", false);
			config.addDefault("Community-surprise", false);
			config.addDefault("DevJoinMessage", true);
			config.addDefault("downloader.protocollib", true);
			config.addDefault("downloader.libsdisguises", true);
			config.addDefault("downloader.noteblockapi", true);
			config.addDefault("trolls.hack.time", 15);
			config.addDefault("trolls.fakeinv.time", 5);
			config.addDefault("trolls.slipperyhands.time", 1);
			config.addDefault("trolls.tnttrace.spawndelay", 2);
			config.addDefault("trolls.spookyWorld.generate", false);
			config.addDefault("trolls.spookyWorld.name", "SpookyWorld");

			try {
                config.save(getFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }

			generateItemFile();
		}
	}

	// TODO:: awful code create a migration system instead.
	public static void createFirstConfigWithValue(String lang, boolean cin, boolean uc, boolean au, boolean anim, boolean async, boolean cs, int th, int tf, int ts, int tt) {
		config = getConfig();
		if(!getFile().exists()) {
			config.options().copyDefaults(true);
			config.options().copyHeader(true);
			config.options().header("###############################\n" +
					"#                             #\n" + 
					"# TrollV4 by Presti           #\n" + 
					"# Configuration File " + Data.version + "    #\n" + 
					"#                             #\n" + 
					"###############################");
			
			config.addDefault("Plugin-Version", Data.version);
			config.addDefault("Language", lang);
			config.addDefault("Custom-Item-Name", cin);
			config.addDefault("UpdateChecker", uc);
			config.addDefault("AutoUpdate", au);
			config.addDefault("Animations", anim);
			config.addDefault("ASync", async);
			config.addDefault("Community-surprise", cs);
			config.addDefault("trolls.hack.time", th);
			config.addDefault("trolls.fakeinv.time", tf);
			config.addDefault("trolls.slipperyhands.time", ts);
			config.addDefault("trolls.tnttrace.spawndelay", tt);
			config.addDefault("trolls.spookyWorld.generate", false);
			config.addDefault("trolls.spookyWorld.name", "SpookyWorld");
			config.addDefault("downloader.protocollib", false);
			config.addDefault("downloader.libsdisguises", false);
			config.addDefault("downloader.noteblockapi", true);

			try {
                config.save(getFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
		}
			
	}

	//TODO:: why not include in the language file? Its basically just a localization string?
	public static void generateItemFile() {
		itemFileConfig = getItemFileConfig();
		if(!getItemFile().exists()) {
			itemFileConfig.options().copyDefaults(true);
			itemFileConfig.options().copyHeader(true);
			itemFileConfig.options().header(
					"###############################\n" + 
					"#                             #\n" + 
					"# TrollV" + Data.version + " by Presti       #\n" + 
					"# Custom ItemName File        #\n" +
					"#                             #\n" + 
					"###############################");
			
			for(String s : Items.path) {
				itemFileConfig.addDefault(s, Items.getItemFromChoiceRaw("default", s));
			}
			
			try {
				itemFileConfig.save(getItemFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
			
		}
	}

	// TODO:: use this to migrate the config instead of recreating it with input values? Also please fix my grammar shit I was 14 or 15 when I made this code.
	// TODO:: this code is horrendous please kill my 14 year old self.
	public static void updateConfig() {
		if (Config.config.getString("Plugin-Version") == null) {
			Config.getFile().delete();
			init();

			Logger.info("Config broken recreating!");
		} else {

			if (!Config.config.getString("Plugin-Version").equalsIgnoreCase(Data.version)) {

				double confv = Double.parseDouble((Config.config.getString("Plugin-Version").replace("4.", "")));

				double pluginv = Double.parseDouble((Data.version.replace("4.", "")));

				if (confv > pluginv) {
					Logger.warning("Your Config is newer than the Plugin Version!");
				} else {

					Logger.info("Updating Config!");

					String l = getConfig().get("Language") != null ? getConfig().getString("Language") : "en_us";
					boolean cin = (Config.getConfig().get("Custom-Item-Name") != null && Config.getConfig().getBoolean("Custom-Item-Name"));
					boolean uc = (Config.getConfig().get("AutoUpdate") != null && Config.getConfig().getBoolean("AutoUpdate"));
					boolean autoup = (Config.getConfig().get("UpdateChecker") == null || Config.getConfig().getBoolean("UpdateChecker"));
					boolean anim = (Config.getConfig().get("Animations") != null && Config.getConfig().getBoolean("Animations"));
					boolean async = (Config.getConfig().get("ASync") != null && Config.getConfig().getBoolean("ASync"));
					boolean cs = (Config.getConfig().get("Community-surprise") != null && Config.getConfig().getBoolean("Community-surprise"));
					int hack = (Config.getConfig().get("trolls.hack.time") != null ? Config.getConfig().getInt("trolls.hack.time") : 15);
					int fakeinv = (Config.getConfig().get("trolls.fakeinv.time") != null ? Config.getConfig().getInt("trolls.fakeinv.time") : 5);
					int hands = (Config.getConfig().get("trolls.slipperyhands.time") != null ? Config.getConfig().getInt("trolls.slipperyhands.time") : 1);

					int tnttrace = (Config.getConfig().get("trolls.tnttrace.spawndelay") != null ? Config.getConfig().getInt("trolls.tnttrace.spawndelay") : 2);

					if (Config.config.getString("Plugin-Version").equalsIgnoreCase("4.3.8")) {
						cs = true;
					}

					Config.getFile().delete();

					Config.createFirstConfigWithValue(l.toLowerCase(), cin, uc, autoup, anim, async, cs, hack, fakeinv, hands, tnttrace);
					Logger.info("Config updated!");
				}
			}
		}
	}

	public static FileConfiguration getConfig() {
		if (config != null) return config;
		return YamlConfiguration.loadConfiguration(getFile());
	}

	public static FileConfiguration getItemFileConfig() {
		if (itemFileConfig != null) return itemFileConfig;
		return YamlConfiguration.loadConfiguration(getItemFile());
	}
	
	public static File getFile() {
		return new File("plugins/TrollV4", "config.yml");
	}
	
	public static File getItemFile() {
		return new File("plugins/TrollV4", "items.yml");
	}
}
