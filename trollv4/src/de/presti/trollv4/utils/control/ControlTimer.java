package de.presti.trollv4.utils.control;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.main.Main;


public class ControlTimer extends BukkitRunnable{
	
	Player c;
	Player v;
	Main plugin;
	ControlTimer(Player controller, Player victim, Main pl){
		c=controller;
		v=victim;
		plugin=pl;
	}
	
	public void run(){
		if(c != null && v != null){
			Main.stopControlling(v, c);
		}
		
		this.cancel();
	}
}