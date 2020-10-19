package de.presti.trollv4.utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 19.10.2020 / 23:25:06											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class PluginUtil {
	
	public static void loadPlugin(String name) throws Exception {
		Plugin pl = null;
		if(new File("plugins/" + name + ".jar").exists()) {
			pl = Bukkit.getPluginManager().loadPlugin(new File("plugins/" + name + ".jar"));
			pl.onLoad();
			
			Bukkit.getPluginManager().enablePlugin(pl);
		} else {
			throw new Exception("File doesnt Exist!");
		}
	}
	
	public static void unloadPlugin(String name) throws Exception {
		Plugin pl = null;
		if(new File("plugins/" + name + ".jar").exists()) {
			pl = Bukkit.getPluginManager().loadPlugin(new File("plugins/" + name + ".jar"));
			
			Bukkit.getPluginManager().disablePlugin(pl);
		} else {
			throw new Exception("File doesnt Exist!");
		}
	}

}
