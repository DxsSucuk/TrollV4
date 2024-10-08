package de.presti.trollv4.utils.control;

import de.presti.trollv4.config.language.LanguageService;
import de.presti.trollv4.utils.crossversion.Titles;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckVictim extends BukkitRunnable {
	
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
							Titles.send(v, 20, 20, 60, LanguageService.getDefault("control.distance"), "");
						}
					}else{
						v.teleport(c);
						v.setGameMode(GameMode.SPECTATOR);
						Titles.send(v, 20, 20, 60, LanguageService.getDefault("control.world"), "");		
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
