package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.trolls.Troll;
import de.presti.trollv4.util.FileUtil;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Spam extends Troll {

    public Spam() {
        super("spam", Items.getItem("gui.trolls.spam"), "Spam the Chat of the Victim", "troll.spam", XMaterial.PAPER);
    }

    @Override
    public void onCall(Player p, Player t) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    t.sendMessage("§c" + FileUtil.getRandomID(20));
                }
            }
        }.runTaskAsynchronously(Main.instance);

        p.sendMessage(Language.getMessage(this, t));
        p.getOpenInventory().close();
    }
}
