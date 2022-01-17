package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.trolls.Troll;
import org.bukkit.entity.Player;

public class Rocket extends Troll {

    public Rocket() {
        super("rocket", Items.getItem("gui.trolls.rocket"), "See the Player fly like a Rocket!", "troll.rocket", XMaterial.FIREWORK_ROCKET);
    }

    @Override
    public void onCall(Player p, Player t) {
        p.sendMessage(Data.prefix + Language.getMessage(this, t));
        p.getOpenInventory().close();
        t.setAllowFlight(true);
        t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
        t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
        t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F,
                25.0F);
        t.setAllowFlight(false);
    }
}
