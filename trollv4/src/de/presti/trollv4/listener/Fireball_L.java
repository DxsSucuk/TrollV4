package de.presti.trollv4.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.presti.trollv4.main.Main;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 07.02.2019 / 00:50:13												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Fireball_L implements Listener {
	@EventHandler
	  public void onPlayerStickClick(PlayerInteractEvent e)
	  {
	    try
	    {
	      Player p = e.getPlayer();
	      
	      ItemStack fireball = new ItemStack(Material.STICK);
	      ItemMeta meta = fireball.getItemMeta();
	      meta.setDisplayName("§4FireBall");
	      fireball.setItemMeta(meta);
	      if (((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) | e.getAction().equals(Action.RIGHT_CLICK_AIR))) && (p.getItemInHand().getItemMeta().getDisplayName().equals("§4FireBall")) && (p.hasPermission("troll.fireball") || p.hasPermission("troll.*"))) {
	        p.launchProjectile(Fireball.class);
	        p.playSound(p.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
	      }
	    }
	    catch (Exception e1) {
	    	e1.printStackTrace();
	    }
	  }
}
