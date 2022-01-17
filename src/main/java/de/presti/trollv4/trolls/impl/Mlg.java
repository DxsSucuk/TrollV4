package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.inventory.InventoryManager;
import de.presti.trollv4.trolls.Troll;
import org.bukkit.entity.Player;

public class Mlg extends Troll {

    public Mlg() {
        super("mlg", Items.getItem("gui.trolls.mlg"), "Let the Player try a MLG", "troll.mlg", XMaterial.WATER_BUCKET);
    }

    @Override
    public void onCall(Player p, Player t) {
        new InventoryManager().openMLGMenu(p);
    }
}
