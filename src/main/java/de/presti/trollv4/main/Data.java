package de.presti.trollv4.main;

import de.presti.trollv4.config.Config;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 11.05.2019 / 18:18:06												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Data {
	
	public static String prefix = "§8│ §2Troll§cV4 §8» §2 ";
    public static String version = Main.instance.getDescription().getVersion();
    public static String cp = "§7[§a+§7]§2";
    public static String cm = "§7[§c-§7]§2";
    public static String cu = "§7[§8=§7]§2";
    public static boolean async = Config.getconfig().getBoolean("ASync");
	
}
