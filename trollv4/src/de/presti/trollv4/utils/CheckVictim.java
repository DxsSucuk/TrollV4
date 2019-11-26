package de.presti.trollv4.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckVictim extends BukkitRunnable{
	
	Player v;
	Player c;
	public CheckVictim(Player victim, Player controller){
		v=victim;
		c=controller;
	}
	
	public void run(){
		if(v != null && c != null){
			if(v.hasMetadata("C_P")){
				if(c.hasMetadata("C_H")){
					if(v.getWorld().getName().equalsIgnoreCase(c.getWorld().getName())){
						if(v.getLocation().distance(c.getLocation()) > 15){
							v.teleport(c);
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							Titles.sendTitle(v, 20, 20, 60, "§cZuweit entfernt", "");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							Titles.sendTitle(v, 20, 20, 60, "§4TOO FAR AWAY", "");
							} else {
							Titles.sendTitle(v, 20, 20, 60, "§4TOO FAR AWAY", "");	
							}
						}
					}else{
						v.teleport(c);
						v.setGameMode(GameMode.SPECTATOR);
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						Titles.sendTitle(v, 20, 20, 60, "§cWelt wurde geweschelt!", "");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						Titles.sendTitle(v, 20, 20, 60, "§4SWITCHED WORLDS", "");	
						} else {
						Titles.sendTitle(v, 20, 20, 60, "§4SWITCHED WORLDS", "");		
						}
					}
				}else{
					this.cancel();
				}
			}else{
				this.cancel();
			}
		}else{
			this.cancel();
		}
	}
}
