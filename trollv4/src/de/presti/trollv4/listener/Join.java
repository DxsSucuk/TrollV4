package de.presti.trollv4.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.presti.trollv4.main.*;
import de.presti.trollv4.utils.*;


/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 18.04.2019 / 23:00:04												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Join implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
	if (p.hasPermission("troll.help")) {
	  if(Config.getconfig().getBoolean("AutoUpdate")) {
       if (!Data.version.equals(Main.instance.update.spigotPluginVersion)) {
    	if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
    		p.sendMessage(Data.prefix + "TrollV4 hat ein Update!");
        	p.sendMessage(Data.prefix + "Neuere Version: " + Main.instance.update.spigotPluginVersion); 
			p.sendMessage(Data.prefix + "Deine Version: " + Data.version);
			p.sendMessage(Data.prefix + "Lade es hier Herunter: https://www.spigotmc.org/resources/" + Main.instance.update.ID + "/updates");
		} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			p.sendMessage(Data.prefix + "TrollV4 have a Update!");
			p.sendMessage(Data.prefix + "New Version: " + Main.instance.update.spigotPluginVersion); 
			p.sendMessage(Data.prefix + "Your Version: " + Data.version);
			p.sendMessage(Data.prefix + "Download here: https://www.spigotmc.org/resources/" + Main.instance.update.ID + "/updates");
		} else {
			p.sendMessage(Data.prefix + "TrollV4 have a Update!");
			p.sendMessage(Data.prefix + "New Version: " + Main.instance.update.spigotPluginVersion); 
			p.sendMessage(Data.prefix + "Your Version: " + Data.version);
			p.sendMessage(Data.prefix + "Download here: https://www.spigotmc.org/resources/" + Main.instance.update.ID + "/updates");
		}
    } else {
    	if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
    		p.sendMessage(Data.prefix + "TrollV4 hat keine Updates");
		} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			p.sendMessage(Data.prefix + "TrollV4 Have no Update");
		} else {
			p.sendMessage(Data.prefix + "TrollV4 Have no Update");	
		}
      }
    }
   }
  }
}
