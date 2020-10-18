package de.presti.trollv4.utils.crossversion;

import org.bukkit.entity.Player;

import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.main.Main;


/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 24.04.2019 / 12:20:32												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class HS {
	public static void Hack(Player player) {
		player.playSound(player.getLocation(), XSound.ENTITY_ENDER_DRAGON_DEATH.parseSound(), 1.0F, 1.0F);
		player.playSound(player.getLocation(), XSound.ENTITY_BAT_DEATH.parseSound(), 1.0F, 1.0F);
		player.playSound(player.getLocation(), XSound.ENTITY_WITHER_DEATH.parseSound(), 1.0F, 1.0F);
		player.playSound(player.getLocation(), XSound.ENTITY_ENDERMAN_DEATH.parseSound(), 1.0F, 1.0F);
		player.playSound(player.getLocation(), XSound.ENTITY_BLAZE_DEATH.parseSound(), 1.0F, 1.0F);
		player.playSound(player.getLocation(), XSound.ENTITY_GHAST_DEATH.parseSound(), 1.0F, 1.0F);
		player.playSound(player.getLocation(), XSound.ENTITY_ZOMBIE_DEATH.parseSound(), 1.0F, 1.0F);
		player.getLocation().getWorld().createExplosion(player.getLocation().getX(), player.getLocation().getY(), 
		player.getLocation().getZ(), 2.0F, false, false);	
	}
	public static void Hack2(Player player) {
		player.damage(0.5D);
        player.playSound(player.getLocation(), XSound.ENTITY_HORSE_DEATH.parseSound(), 1.0F, 1.0F);
		player.sendMessage("§c" + Main.getRandomID());
	}

}
