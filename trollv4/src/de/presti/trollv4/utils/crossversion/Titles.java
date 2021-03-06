package de.presti.trollv4.utils.crossversion;

import java.lang.reflect.Constructor;
import org.bukkit.entity.Player;

import de.presti.trollv4.api.Packets;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 15.02.2019 / 19:23:51												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Titles {

	  public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
	  {
	    send(player, fadeIn, stay, fadeOut, title, subtitle);
	  }
	  
	  public static void send(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
	  {
	    try
	    {
	      Object chat = Packets.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
	      Constructor<?> Constructor = Packets.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Packets.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
	      Object timePacket = Constructor.newInstance(new Object[] { Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null), chat, fadeIn, stay, fadeOut });
	      Constructor = Packets.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Packets.getNMSClass("IChatBaseComponent") });
	      Object titlePacket = Constructor.newInstance(new Object[] { Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chat });
	      chat = Packets.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
	      Object subtitlePacket = Constructor.newInstance(new Object[] { Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chat });
	      
	      Packets.sendPacket(player, timePacket);
	      Packets.sendPacket(player, titlePacket);
	      Packets.sendPacket(player, subtitlePacket);
	    }
	    catch (Exception e)
	    {
		    e.printStackTrace();
	    }
	  }
}
