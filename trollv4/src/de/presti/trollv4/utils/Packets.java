package de.presti.trollv4.utils;

import org.bukkit.entity.Player;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 15.02.2019 / 19:24:49												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Packets {
	public static Class<?> getNMSClass(String name)
	  {
	    String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	    try
	    {
	      return Class.forName("net.minecraft.server." + version + "." + name);
	    }
	    catch (ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }
	  
	  public static void sendPacket(Player player, Object packet)
	  {
	    try
	    {
	      Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
	      Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
	      playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	  }
}
