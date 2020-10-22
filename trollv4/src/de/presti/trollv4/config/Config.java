package de.presti.trollv4.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.presti.trollv4.main.Data;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 18.04.2019 / 19:35:22												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
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
			cfg.addDefault("AutoUpdate", true);
			cfg.addDefault("Animations", false);
			cfg.addDefault("Community-surprise", true);
			cfg.addDefault("trolls.hack.time", 15);
			cfg.addDefault("trolls.fakeinv.time", 5);
			cfg.addDefault("trolls.slipperyhands.time", 1);
			
			try {
                cfg.save(getFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
			
		}
	}
	
	public static void createFirstConfigWithValue(String lang, boolean cin, boolean au, boolean anim, boolean cs, int th, int tf, int ts) {
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
			cfg.addDefault("AutoUpdate", au);
			cfg.addDefault("Animations", anim);
			cfg.addDefault("Community-surprise", cs);
			cfg.addDefault("trolls.hack.time", th);
			cfg.addDefault("trolls.fakeinv.time", tf);
			cfg.addDefault("trolls.slipperyhands.time", ts);
			
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
