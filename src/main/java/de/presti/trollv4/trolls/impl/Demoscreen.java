package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.trolls.Troll;
import de.presti.trollv4.util.server.packets.DemoScreen;
import org.bukkit.entity.Player;

public class Demoscreen extends Troll {

    public Demoscreen() {
        super("demoscreen", Items.getItem("gui.trolls.demo"), "Show a Demoscreen to a Player!", "troll.demo", XMaterial.SPRUCE_DOOR);
    }

    @Override
    public void onCall(Player p, Player t) {
        p.sendMessage(Data.prefix + Language.getMessage(this, t));
        p.getOpenInventory().close();
        DemoScreen.showDemoScreen(t);
    }
}
