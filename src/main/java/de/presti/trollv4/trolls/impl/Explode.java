package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.trolls.Troll;
import org.bukkit.entity.Player;

public class Explode extends Troll {

    public Explode() {
        super("explode", Items.getItem("gui.trolls.explode"), "§7Let a Player explode!", "troll.explode", XMaterial.TNT);
    }

    @Override
    public void onCall(Player p, Player t) {
        t.playSound(t.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 100.0F,
                200.0F);
        t.getWorld().createExplosion(t.getLocation(), 3.0F);
        t.setHealth(0.0D);
        p.sendMessage(Data.prefix + Language.getMessage(this, t));
        p.getOpenInventory().close();
    }
}
