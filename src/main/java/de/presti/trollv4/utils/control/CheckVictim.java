package de.presti.trollv4.utils.control;

import de.presti.trollv4.config.language.LanguageService;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.crossversion.Titles;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckVictim extends BukkitRunnable {

    Player victim;
    Player controller;

    public CheckVictim(Player victim, Player controller) {
        this.victim = victim;
        this.controller = controller;
    }

    public void run() {
        if (victim != null && controller != null) {
            if (victim.hasMetadata("C_P")) {
                if (controller.hasMetadata("C_H")) {
                    if (victim.getWorld().getName().equalsIgnoreCase(controller.getWorld().getName())) {
                        if (victim.getLocation().distance(controller.getLocation()) > 15) {
                            Main.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, controller.getLocation());
                            Main.getInstance().getFoliaLib().getScheduler().runAtEntity(victim, x-> {
                                Titles.send(victim, 20, 20, 60, LanguageService.getDefault("control.distance"), "");
                            });
                        }
                    } else {
                        Main.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, controller.getLocation());
                        Main.getInstance().getFoliaLib().getScheduler().runAtEntity(victim, x-> {
                            victim.setGameMode(GameMode.SPECTATOR);
                            Titles.send(victim, 20, 20, 60, LanguageService.getDefault("control.world"), "");
                        });
                    }
                } else {
                    this.cancel();
                }
            } else {
                this.cancel();
            }
        } else {
            this.cancel();
        }
    }
}
