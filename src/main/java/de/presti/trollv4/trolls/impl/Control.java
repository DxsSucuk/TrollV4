package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.trolls.Troll;
import org.bukkit.entity.Player;

public class Control extends Troll {

    public Control() {
        super("control", Items.getItem("gui.trolls.startcontrol"), "Control another Player!", "troll.control", XMaterial.LEVER);
    }

    @Override
    public void onCall(Player p, Player t) {
        if (!p.hasMetadata("C_H")) {
            if (t != null) {
                if (!t.hasMetadata("C_P")) {
                    if (t != p) {
                        if (!(t.hasPermission("troll.control"))) {
                            Main.control.startControlling(t, p);
                        } else {
                            p.sendMessage(Data.prefix
                                    + Language.getMessage(this,"start.failed", t));
                            p.getOpenInventory().close();
                        }
                    } else {
                        p.sendMessage(
                                Data.prefix + Language.getMessage(this,"start.yourself", t));
                        p.getOpenInventory().close();
                    }
                } else {
                    p.sendMessage(
                            Data.prefix + Language.getMessage(this,"start.iscontroled", t));
                    p.getOpenInventory().close();
                }
            } else {
                p.sendMessage(Data.prefix + Language.getMessage("noonline"));
                p.getOpenInventory().close();
            }
        } else {
            p.sendMessage(Data.prefix + Language.getMessage("gui.startcontrol.alreadyc", t));
            p.getOpenInventory().close();
        }
    }
}
