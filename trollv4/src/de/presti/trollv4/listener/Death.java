package de.presti.trollv4.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.presti.trollv4.utils.ArrayUtils;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 08.02.2020 / 14:56:42											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Death implements Listener {
	
	@EventHandler
	public static void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		if(ArrayUtils.userbowspam.contains(p)) {
			e.setDeathMessage(null);
			ArrayUtils.arrowspam.get(p).cancel();
			ArrayUtils.userbowspam.remove(p);
		}
	}

}
