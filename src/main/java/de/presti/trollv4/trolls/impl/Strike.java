package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.trolls.Troll;
import org.bukkit.entity.Player;

public class Strike extends Troll {

    public Strike() {
        super("strike", Items.getItem("gui.trolls.strike"), "Strike a Player with a Lightning Bolt!", "troll.strike", XMaterial.BEACON);
    }

    @Override
    public void onCall(Player p, Player t) {
        p.sendMessage(Data.prefix + Language.getMessage(this, t));
        p.getOpenInventory().close();
        if (t.getLocation().getWorld() != null) {
            t.getLocation().getWorld().strikeLightning(t.getLocation());
        }
    }
}
