package de.presti.trollv4.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.presti.trollv4.main.Main;


/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 24.04.2019 / 12:15:27												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Hsv1_8_R3 {
	public static void Hack(Player player) {
		player.playSound(player.getLocation(), Sound.ENDERDRAGON_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.BAT_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.WITHER_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.ENDERMAN_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.BLAZE_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.GHAST_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.ZOMBIE_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.DONKEY_DEATH, 1.0F, 1.0F);
		player.playSound(player.getLocation(), Sound.HORSE_DEATH, 1.0F, 1.0F);
		player.getLocation().getWorld().createExplosion(player.getLocation().getX(), player.getLocation().getY(), 
		player.getLocation().getZ(), 2.0F, false, false);
	}
	public static void Hack2(Player player) {
        player.damage(0.1D);
        player.playSound(player.getLocation(), Sound.HORSE_DEATH, 1.0F, 1.0F);
        player.sendMessage("§c" + Main.getRandomID());
	}

}
