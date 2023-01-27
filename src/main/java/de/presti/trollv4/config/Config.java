package de.presti.trollv4.config;

import java.io.File;
import java.io.IOException;

import de.presti.trollv4.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.presti.trollv4.main.Data;

public class Config {

	public static FileConfiguration cfg;
	public static FileConfiguration cfg2;
	public static FileConfiguration cfg3;
	
	public void init() {
		cfg = getconfig();
		if(!getFile().exists()) {
			cfg.options().copyDefaults(true);
			cfg.options().copyHeader(true);
			cfg.options().header("###############################\n" + 
					"#                             #\n" + 
					"# TrollV4 by Presti           #\n" + 
					"# Configuration File " + Data.version + "    #\n" + 
					"#                             #\n" + 
					"###############################");
			cfg.addDefault("Plugin-Version", Data.version);
			cfg.addDefault("Language", "US");
			cfg.addDefault("Custom-Item-Name", false);
			cfg.addDefault("UpdateChecker", true);
			cfg.addDefault("AutoUpdate", false);
			cfg.addDefault("Animations", false);
			cfg.addDefault("ASync", false);
			cfg.addDefault("Community-surprise", false);
			cfg.addDefault("trolls.hack.time", 15);
			cfg.addDefault("trolls.fakeinv.time", 5);
			cfg.addDefault("trolls.slipperyhands.time", 1);
			cfg.addDefault("trolls.tnttrace.spawndelay", 2);
			
			try {
                cfg.save(getFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }

			init2();
			init3();
		}
	}
	
	public static void createFirstConfigWithValue(String lang, boolean cin, boolean uc, boolean au, boolean anim, boolean async, boolean cs, int th, int tf, int ts, int tt) {
		cfg = getconfig();
		if(!getFile().exists()) {
			cfg.options().copyDefaults(true);
			cfg.options().copyHeader(true);
			cfg.options().header("###############################\n" + 
					"#                             #\n" + 
					"# TrollV4 by Presti           #\n" + 
					"# Configuration File " + Data.version + "    #\n" + 
					"#                             #\n" + 
					"###############################");
			
			cfg.addDefault("Plugin-Version", Data.version);
			cfg.addDefault("Language", lang);
			cfg.addDefault("Custom-Item-Name", cin);
			cfg.addDefault("UpdateChecker", uc);
			cfg.addDefault("AutoUpdate", au);
			cfg.addDefault("Animations", anim);
			cfg.addDefault("ASync", async);
			cfg.addDefault("Community-surprise", cs);
			cfg.addDefault("trolls.hack.time", th);
			cfg.addDefault("trolls.fakeinv.time", tf);
			cfg.addDefault("trolls.slipperyhands.time", ts);
			cfg.addDefault("trolls.tnttrace.spawndelay", tt);
			
			try {
                cfg.save(getFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
		}
			
	}
	
	public void init2() {
		cfg2 = getconfig2();
		if(!getFile2().exists()) {
			cfg2.options().copyDefaults(true);
			cfg2.options().copyHeader(true);
			cfg2.options().header(
					"###############################\n" + 
					"#                             #\n" + 
					"# TrollV" + Data.version + " by Presti       #\n" + 
					"# Custome Message File        #\n" + 
					"#                             #\n" + 
					"###############################");
			
			for(String s : Language.lang) {
				cfg2.addDefault(s, Language.getMessageFromLanguageRaw("us", s));
			}
			
			try {
				cfg2.save(getFile2());
            } catch (IOException e) {
			    e.printStackTrace();
            }
			
		}
	}
	
	public void init3() {
		cfg3 = getconfig3();
		if(!getFile3().exists()) {
			cfg3.options().copyDefaults(true);
			cfg3.options().copyHeader(true);
			cfg3.options().header(
					"###############################\n" + 
					"#                             #\n" + 
					"# TrollV" + Data.version + " by Presti       #\n" + 
					"# Custome ItemName File       #\n" + 
					"#                             #\n" + 
					"###############################");
			
			for(String s : Items.path) {
				cfg3.addDefault(s, Items.getItemFromChoiceRaw("default", s));
			}
			
			try {
				cfg3.save(getFile3());
            } catch (IOException e) {
			    e.printStackTrace();
            }
			
		}
	}

	public void updateConfig() {
		if (Config.cfg.getString("Plugin-Version") == null) {
			Config.getFile().delete();
			new Config().init();

			Logger.info("Config broken recreating!");
		} else {

			if (!Config.cfg.getString("Plugin-Version").equalsIgnoreCase(Data.version)) {

				double confv = Double.parseDouble((Config.cfg.getString("Plugin-Version").replace("4.", "")));

				double pluginv = Double.parseDouble((Data.version.replace("4.", "")));

				if (confv > pluginv) {
					Logger.warning("Your Config is newer than the Plugin Version!");
				} else {

					Logger.info("Updating Config!");

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
					Logger.info("Config updated!");
				}
			}
		}
	}

	public static FileConfiguration getconfig() {
		return YamlConfiguration.loadConfiguration(getFile());
	}
	
	public static FileConfiguration getconfig2() {
		return YamlConfiguration.loadConfiguration(getFile2());
	}
	public static FileConfiguration getconfig3() {
		return YamlConfiguration.loadConfiguration(getFile3());
	}
	
	public static File getFile() {
		return new File("plugins/TrollV4", "config.yml");
	}
	
	public static File getFile2() {
		return new File("plugins/TrollV4", "messages.yml");
	}
	
	public static File getFile3() {
		return new File("plugins/TrollV4", "items.yml");
	}
}
