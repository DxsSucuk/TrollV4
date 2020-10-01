package de.presti.trollv4.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 13.12.2019 / 22:51:38											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Controls {

	public Controls() {
		Main.control = this;
	}

	public void stopControlling(Player v, Player c) {
		// Call ONLY when both players are online
		c.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 1));

		v.removeMetadata("C_P", Main.getPlugin());
		v.setGameMode(GameMode.SURVIVAL);

		c.removeMetadata("C_H", Main.getPlugin());
		DisguiseAPI.undisguiseToAll(c);

		// Give victim their "new" inventory
		v.getInventory().setContents(c.getInventory().getContents());
		v.getInventory().setArmorContents(c.getInventory().getArmorContents());

		// Give controller the original inventory back
		c.getInventory().setContents(Main.inventory.get(c.getName()));
		c.getInventory().setArmorContents(Main.armor.get(c.getName()));
		Main.inventory.remove(c.getName());
		Main.armor.remove(c.getName());

		v.teleport(c);
		c.sendMessage(Data.prefix + Language.getMessage("gui.stopcontrol.stop", v));
	}

	public void startControlling(Player v, Player c) {
		v.setMetadata("C_P", new FixedMetadataValue(Main.getPlugin(), c.getName()));
		c.setMetadata("C_H", new FixedMetadataValue(Main.getPlugin(), v.getName()));

		Main.inventory.put(c.getName(), c.getInventory().getContents());
		Main.armor.put(c.getName(), c.getInventory().getArmorContents());
		c.getInventory().setContents(v.getInventory().getContents());
		c.getInventory().setArmorContents(v.getInventory().getArmorContents());

		c.teleport(v);
		v.setGameMode(GameMode.SPECTATOR);

		PlayerDisguise disV = null;
		try {
			disV = new PlayerDisguise(v.getName());
			DisguiseAPI.disguiseToAll(c, disV);
		} catch (Exception e) {
		}

		// Start a handling task
		CheckVictim check = new CheckVictim(v, c);
		check.runTaskTimer(Main.getPlugin(), 0L, 20L);
		c.sendMessage(Data.prefix + Language.getMessage("gui.startcontrol.start", v));
	}

}
