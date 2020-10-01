package de.presti.trollv4.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 05.02.2019 / 17:40:26												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Minigun implements Listener {
	
	 @EventHandler
	  public void onPlayerIronAxeClick(PlayerInteractEvent e)
	  {
	    try
	    {
	      Player p = e.getPlayer();
	      
	      ItemStack ironaxe = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
	      ItemMeta meta = ironaxe.getItemMeta();
	      meta.setDisplayName("§4MiniGun");
	      ironaxe.setItemMeta(meta);
	      if (((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) | e.getAction().equals(Action.RIGHT_CLICK_AIR))) && 
	        (p.getItemInHand().getItemMeta().getDisplayName().equals("§4MiniGun")) && 
	        (p.hasPermission("troll.minigun") || p.hasPermission("troll.*")))
	      {
	        p.launchProjectile(Arrow.class);
	        p.launchProjectile(Snowball.class);
	        p.playSound(p.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 1.0F, 1.0F);
	      }
	    }
	    catch (Exception localException) {}
	  }

}
