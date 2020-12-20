package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2020											    *
*	Erstellt: 01.12.2020 / 16:35:14											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class WorldCreator {

	@SuppressWarnings("deprecation")
	public static void createWorld(String name) {
		Bukkit.createWorld(new org.bukkit.WorldCreator(name));
		Bukkit.getWorld(name).setGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE.getName(), "false");
		Bukkit.getWorld(name).setGameRuleValue(GameRule.DO_MOB_SPAWNING.getName(), "false");
	}
	
	public static void createWorld(String name, GenTyps gen) {
		org.bukkit.WorldCreator wc = new org.bukkit.WorldCreator(name);
		
		Bukkit.createWorld(wc);
	}

	public enum GenTyps {

		FLAT, NORMAL, NETHER, END
		
	}
	
}
