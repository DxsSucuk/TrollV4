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

	public static void stopControlling(Player v, Player c){
		//Call ONLY when both players are online
		c.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10, 1));
		
		v.removeMetadata("C_P", Main.instance);
		v.setGameMode(GameMode.SURVIVAL);
				
		c.removeMetadata("C_H", Main.instance);
		DisguiseAPI.undisguiseToAll(c);
			
		//Give victim their "new" inventory
		v.getInventory().setContents(c.getInventory().getContents());
		v.getInventory().setArmorContents(c.getInventory().getArmorContents());
			
		//Give controller the original inventory back
		c.getInventory().setContents(Main.inventory.get(c.getName()));
		c.getInventory().setArmorContents(Main.armor.get(c.getName()));
		Main.inventory.remove(c.getName());
		Main.armor.remove(c.getName());
			
		v.teleport(c);
		if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
		c.sendMessage(Data.prefix + "Du kontrollierst nun §c"+v.getName() + " §2nicht mehr");
		} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
		c.sendMessage(Data.prefix + "You §cdeactivated §2Control Mode with §c"+v.getName());	
		} else {
		c.sendMessage(Data.prefix + "You §cdeactivated §2Control Mode with §c"+v.getName());		
		}
	}
	
	public static void startControlling(Player v, Player c){
		v.setMetadata("C_P", new FixedMetadataValue(Main.instance,c.getName()));
		c.setMetadata("C_H", new FixedMetadataValue(Main.instance,v.getName()));
		
		Main.instance.inventory.put(c.getName(), c.getInventory().getContents());
		Main.instance.armor.put(c.getName(), c.getInventory().getArmorContents());
		c.getInventory().setContents(v.getInventory().getContents());
		c.getInventory().setArmorContents(v.getInventory().getArmorContents());
		
		c.teleport(v);
		v.setGameMode(GameMode.SPECTATOR);
		
		PlayerDisguise disV = new PlayerDisguise(v.getName());
		DisguiseAPI.disguiseToAll(c, disV);

		//Start a handling task
		new CheckVictim(v, c).runTaskTimer(Main.instance, 100, 100);
		if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
		c.sendMessage(Data.prefix + "Du Kontrollierst nun §c" +v.getName());
		} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
		c.sendMessage(Data.prefix + "You §aactivated §2Control Mode with §c"+v.getName());	
		} else {
		c.sendMessage(Data.prefix + "You §aactivated §2Control Mode with §c"+v.getName());		
		}
		
	}
	
}
