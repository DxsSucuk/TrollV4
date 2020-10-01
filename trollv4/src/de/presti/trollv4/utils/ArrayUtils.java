package de.presti.trollv4.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 17.10.2018 / 23:55:02												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class ArrayUtils {
	
	//ArrayLists
	public static ArrayList<Player> vanish = new ArrayList<>();
	public static ArrayList<Player> fc = new ArrayList<>();
	public static ArrayList<Player> freeze = new ArrayList<>();
	public static ArrayList<Player> fly = new ArrayList<>();
	public static ArrayList<Player> lagging = new ArrayList<>();
	public static ArrayList<Player> rotateplayer = new ArrayList<>();
	public static ArrayList<Player> randomtp = new ArrayList<>();
	public static ArrayList<Player> tntp = new ArrayList<>();
	public static ArrayList<Player> herobrine = new ArrayList<>();
	public static ArrayList<Player> userbowspam = new ArrayList<>();
	public static ArrayList<Player> tornado = new ArrayList<>();
	
	//HashMaps
    public static HashMap<String, String> trolling = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> anim = new HashMap<>();
	public static HashMap<Player, Location> arrest = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> wtf = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> hackuser = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> arrowspam = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> tornador = new HashMap<>();

}
