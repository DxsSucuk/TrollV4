package de.presti.trollv4.utils;

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
			cfg.addDefault("AutoUpdate", true);
			cfg.addDefault("Animations", true);
			cfg.addDefault("Unsupport", false);
			cfg.addDefault("Community-surprise", true);
			
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
			cfg2.options().header("###############################\n" + 
					"#                             #\n" + 
					"# TrollV4 by Presti           #\n" + 
					"# Custome Message File        #\n" + 
					"#                             #\n" + 
					"###############################");
			cfg2.addDefault("Message.FakeLeave", "&c[USER] &2is off");
			cfg2.addDefault("Message.Player.Crash", "java.net.ConnectException: Connection timed out: No further information");
			cfg2.addDefault("Message.Crash", "[PREFIX]You let the Player §c[USER] §2Crash");
			cfg2.addDefault("Message.Main.Line.1", "[PREFIX]TrollV4 by Presti");
			cfg2.addDefault("Message.Main.Line.2", "[PREFIX]Troll Modus &aActive");
			cfg2.addDefault("Message.Main.Line.3", "[PREFIX]Troll Vanish &aActive");
			cfg2.addDefault("Message.Main.Line.4", "[PREFIX]&4Commands:");
			cfg2.addDefault("Message.Main.Line.5", "[PREFIX]&4Player Troll GUI §8- §c/troll");
			cfg2.addDefault("Message.Main.Line.6", "[PREFIX]&4Server Troll GUI §8- §c/troll server");
			cfg2.addDefault("Message.Main.Line.7", "[PREFIX]&4Item Troll GUI §8- §c/troll items");
			cfg2.addDefault("Message.Main.Line.8", "[PREFIX]&4Troll Vanish §8- §c/troll v");
			cfg2.addDefault("Message.Main.Line.9", "[PREFIX]&4Gamemode §8- §c/troll gm");
			cfg2.addDefault("Message.Main.Line.10", "[PREFIX]&4Fly §8- §c/troll fly");
			cfg2.addDefault("Message.Main.Line.11", "[PREFIX]&4Register §8- §c/troll register");
			cfg2.addDefault("Message.Main.Line.11", "[PREFIX]&4Changelog §8- §c/troll changelog");
			cfg2.addDefault("Message.Main.Line.11", "[PREFIX]&4Credits §8- §c/troll credits");
			cfg2.addDefault("Message.NoPerms", "[PREFIX]You do not have the permission for this command!");
			cfg2.addDefault("Message.Console", "[PREFIX]Youre not a Player!");
			cfg2.addDefault("Message.ArgumetsError", "[PREFIX]Unknown arguments please use /troll help");
			cfg2.addDefault("Message.Help.Line.1", "&8»");
			cfg2.addDefault("Message.Help.Line.2", "[PREFIX]TrollV4 [VERSION]");
			cfg2.addDefault("Message.Help.Line.3", "[PREFIX]All Commands /troll help");
			cfg2.addDefault("Message.Help.Line.4", "[PREFIX]TrollV4 by");
			cfg2.addDefault("Message.Help.Line.5", "[PREFIX]Presti");
			cfg2.addDefault("Message.Help.Line.6", "&8»");
			cfg2.addDefault("Message.ServerTrollCommand", "[PREFIX]Server Troll Inv was opened!");
			cfg2.addDefault("Message.ItemTrollCommand", "[PREFIX]Item Troll Inv was opened!");
			cfg2.addDefault("Message.Vanish.Join", "[PREFIX]Youre now in Vanish!");
			cfg2.addDefault("Message.Vanish.Quit", "[PREFIX]Youre not longer in Vanish!");
			cfg2.addDefault("Message.Fly.Join", "[PREFIX]You can fly now!");
			cfg2.addDefault("Message.Fly.Quit", "[PREFIX]You can not fly anymore!");
			cfg2.addDefault("Message.GamemodeCommand", "[PREFIX]Youre now in Gamemode 1!");
			
			try {
				cfg2.save(getFile2());
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
	
	public static File getFile() {
		return new File("plugins/TrollV4", "config.yml");
	}
	
	public static File getFile2() {
		return new File("plugins/TrollV4", "messages.yml");
	}
}
