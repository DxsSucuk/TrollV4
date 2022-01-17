package de.presti.trollv4.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.presti.trollv4.main.Data;

public class Config {

	public Config() {
		initConfig();
		initItemName();
		initLanguageFile();
	}

	public FileConfiguration config;
	public FileConfiguration langugeConfig;
	public FileConfiguration itemConfig;
	
	public void initConfig() {
		config = getConfig();
		if(!getConfigFile().exists()) {
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
			config.addDefault("trolls.hack.time", 15);
			config.addDefault("trolls.fakeinv.time", 5);
			config.addDefault("trolls.slipperyhands.time", 1);
			config.addDefault("trolls.tnttrace.spawndelay", 2);
			
			try {
				config.save(getConfigFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
			
		}
	}
	
	public void createFirstConfigWithValue(String lang, boolean cin, boolean uc, boolean au, boolean anim, boolean async, boolean cs, int th, int tf, int ts, int tt) {
		config = getConfig();
		if(!getConfigFile().exists()) {
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
			
			try {
				config.save(getConfigFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
		}
			
	}
	
	public void initLanguageFile() {
		langugeConfig = getLanguageConfig();
		if(!getLanguageFile().exists()) {
			langugeConfig.options().copyDefaults(true);
			langugeConfig.options().copyHeader(true);
			langugeConfig.options().header(
					"###############################\n" + 
					"#                             #\n" + 
					"# TrollV" + Data.version + " by Presti       #\n" + 
					"# Custome Message File        #\n" + 
					"#                             #\n" + 
					"###############################");
			
			for(String s : Language.lang) {
				langugeConfig.addDefault(s, Language.getMessageFromLanguageRaw("us", s));
			}
			
			try {
				langugeConfig.save(getLanguageFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
			
		}
	}
	
	public void initItemName() {
		itemConfig = getItemConfig();
		if(!getItemFile().exists()) {
			itemConfig.options().copyDefaults(true);
			itemConfig.options().copyHeader(true);
			itemConfig.options().header(
					"###############################\n" + 
					"#                             #\n" + 
					"# TrollV" + Data.version + " by Presti       #\n" + 
					"# Custome ItemName File       #\n" + 
					"#                             #\n" + 
					"###############################");
			
			for(String s : Items.path) {
				itemConfig.addDefault(s, Items.getItemFromChoiceRaw("default", s));
			}
			
			try {
				itemConfig.save(getItemFile());
            } catch (IOException e) {
			    e.printStackTrace();
            }
			
		}
	}
	
	public FileConfiguration getConfig() {
		return YamlConfiguration.loadConfiguration(getConfigFile());
	}
	
	public FileConfiguration getLanguageConfig() {
		return YamlConfiguration.loadConfiguration(getLanguageFile());
	}
	public FileConfiguration getItemConfig() {
		return YamlConfiguration.loadConfiguration(getItemFile());
	}
	
	public File getConfigFile() {
		return new File("plugins/TrollV4", "config.yml");
	}
	
	public File getLanguageFile() {
		return new File("plugins/TrollV4", "messages.yml");
	}
	
	public File getItemFile() {
		return new File("plugins/TrollV4", "items.yml");
	}
}