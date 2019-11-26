package de.presti.trollv4.utils;

import org.bukkit.entity.Player;

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
public class Dsv1_8_R3 {
	public static void DemoScreen1_8(Player player) {
		((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)player).getHandle().playerConnection.sendPacket(new net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange(5, 0.0F));
	}

}
