package de.presti.trollv4.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.presti.trollv4.utils.ArrayUtils;


/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 17.10.2018 / 23:58:18												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Fakehacks implements Listener {
	@EventHandler
	  public void onMove(PlayerMoveEvent playerMoveEvent)
	  {
	    Player player = playerMoveEvent.getPlayer();
	    if (ArrayUtils.fc.contains(player))
	    {
	      player.setAllowFlight(true);
	      player.setVelocity(player.getLocation().getDirection().setZ(0.1D).setX(0.1D));
	      player.setVelocity(player.getLocation().getDirection().setZ(-0.1D).setX(-0.1D));
	      player.setVelocity(player.getLocation().getDirection().setY(-9));
	      player.setAllowFlight(false);
	      player.setWalkSpeed(1.0E-6F);
	    }
	  }
	}

