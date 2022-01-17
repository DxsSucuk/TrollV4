package de.presti.trollv4.trolls.impl;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.cryptomorin.xseries.XMaterial;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.trolls.Troll;
import org.bukkit.entity.Player;

public class Fakeop extends Troll {

    public Fakeop() {
        super("fakeop", Items.getItem("gui.trolls.fakeop"), "Send a Fakeop Message to your Target", "troll.fakeop", XMaterial.GOLDEN_APPLE);
    }

    @Override
    public void onCall(Player p, Player t) {
        // TODO add actual command
    }
}
