package de.presti.trollv4.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.presti.trollv4.utils.ArrayUtils;


/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 19.10.2018 / 20:23:50												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Quit implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
	    if (ArrayUtils.vanish.contains(p))
	    {
	      ArrayUtils.vanish.remove(p);
	      for (Player all : Bukkit.getOnlinePlayers()) {
	        all.showPlayer(p);
	      }
	    }
	    if(ArrayUtils.freeze.contains(p)) {
	    	p.setWalkSpeed(0.2F);
            p.setFlySpeed(0.1F);
	    	ArrayUtils.freeze.remove(p);
	    }
	    
	    if(ArrayUtils.herobrine.contains(p)) {
	    	ArrayUtils.herobrine.remove(p);
	    }
	    
	    if(ArrayUtils.userbowspam.contains(p)) {
	    	ArrayUtils.arrowspam.get(p).cancel();
	    	ArrayUtils.userbowspam.remove(p);
	    }
	    
	    if(ArrayUtils.hackuser.containsKey(p)) {
	    	ArrayUtils.hackuser.get(p).cancel();
	    }
	    
	    if(ArrayUtils.tntp.contains(p)) {
	    	ArrayUtils.tntp.remove(p);
	    }
	    
	    if(ArrayUtils.rotateplayer.contains(p)) {
	    	ArrayUtils.rotateplayer.remove(p);
	    }
	}

}
