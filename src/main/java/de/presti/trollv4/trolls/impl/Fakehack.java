package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.trolls.Troll;
import de.presti.trollv4.util.player.ArrayUtils;
import org.bukkit.entity.Player;

public class Fakehack extends Troll {

    public Fakehack() {
        super("fakehack", Items.getItem("gui.trolls.fakehack"), "Let a Player seem like a Hacker!", "troll.fakehack", XMaterial.DIAMOND_SWORD);
    }

    @Override
    public void onCall(Player p, Player t) {
        if (ArrayUtils.fc.contains(t)) {
            p.sendMessage(Data.prefix + Language.getMessage(this, "off", t));
            p.getOpenInventory().close();
            t.setWalkSpeed(0.2F);
            t.setAllowFlight(false);
            ArrayUtils.fc.remove(t);
        } else {
            p.sendMessage(Data.prefix + Language.getMessage(this,"on", t));
            p.getOpenInventory().close();
            ArrayUtils.fc.add(t);
        }
    }
}
