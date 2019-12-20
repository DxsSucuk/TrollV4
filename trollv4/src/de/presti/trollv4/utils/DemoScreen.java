package de.presti.trollv4.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import de.presti.trollv4.main.Main;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 24.04.2019 / 12:01:55												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class DemoScreen {
	
	public static void DemoScreen(Player player) {
		try {
            Class<?> craftPlayerClass = Packets.getNMSClass("CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);
            Object packet;
            Class<?> packetPlayGameStateChangeClass = Packets.getNMSClass("PacketPlayOutGameStateChange");
            Class<?> packetClass = Packets.getNMSClass("Packet");
            packet = packetPlayGameStateChangeClass.getConstructor(int.class, float.class).newInstance(5, 0);
            Method craftPlayerHandleMethod = craftPlayerClass.getDeclaredMethod("getHandle");
            Object craftPlayerHandle = craftPlayerHandleMethod.invoke(craftPlayer);
            Field playerConnectionField = craftPlayerHandle.getClass().getDeclaredField("playerConnection");
            Object playerConnection = playerConnectionField.get(craftPlayerHandle);
            Method sendPacketMethod = playerConnection.getClass().getDeclaredMethod("sendPacket", packetClass);
            sendPacketMethod.invoke(playerConnection, packet);
		} catch (Exception ex) {
			Main.instance.getLogger().warning(ex.getMessage());
		}
	}

}
