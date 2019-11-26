package de.presti.trollv4.utils;

import org.bukkit.entity.Player;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 24.04.2019 / 12:00:59												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Dsv1_9_R2 {
	public static void DemoScreen(Player player) {
		((org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer)player).getHandle().playerConnection.sendPacket(new net.minecraft.server.v1_9_R2.PacketPlayOutGameStateChange(5, 0.0F));
	}

}
