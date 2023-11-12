package de.presti.trollv4.invs;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;

import de.presti.trollv4.api.PlayerInfo;

public class SetItems {

    public static ItemStack buildSkull(String p, String name, boolean forceOnlineLookup) {
        ArrayList<String> lore = new ArrayList<String>();

        UUID uuid = null;

        Player player = Bukkit.getPlayer(p);

        if (forceOnlineLookup) {
            try {
                String infoUUID = PlayerInfo.getUUID(p);
                if (infoUUID != null)
                    uuid = UUID.fromString(infoUUID);
            } catch (Exception ignore) {
            }
        } else {
            if (player != null) {
                uuid = player.getUniqueId();
            }
        }

        ItemStack skull = uuid == null ? new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial())
                : SkullUtils.getSkull(uuid);

        ItemMeta skullm = skull.getItemMeta();

        if (player != null) {
            if (player.isOp()) {
                lore.add("§cThis User has OP!");
            }

            if (player.hasPermission("troll.player")) {
                lore.add("§cThis User can acces the Troll Gui!");
            }

            skullm.setLore(lore);
        }

        skullm.setDisplayName(name);
        skull.setItemMeta(skullm);
        return skull;
    }

    public static ItemStack buildItem(String name, Material m) {
        return buildItem(name, new ItemStack(m));
    }

    public static ItemStack buildItem(String name, ItemStack item) {
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName(name);
        item.setItemMeta(itemm);
        return item;
    }

    public static ItemStack buildItem(String name, XMaterial m) {
        return buildItem(name, m.parseMaterial());
    }

    public static ItemStack buildItem(String name, XMaterial m, String[] lores) {
        return buildItem(name, m.parseMaterial(), lores);
    }

    public static ItemStack buildItem(String name, Material m, String[] lores) {
        return buildItem(name, new ItemStack(m), lores);
    }

    public static ItemStack buildItem(String name, ItemStack item, String[] lores) {
        ArrayList<String> lore = new ArrayList<String>();

        for (String s : lores) {
            lore.add(s);
        }

        ItemMeta itemm = item.getItemMeta();
        itemm.setLore(lore);
        itemm.setDisplayName(name);
        item.setItemMeta(itemm);
        return item;
    }

    public static ItemStack buildItem(String name, ItemStack item, String lore) {
        return buildItem(name, item, new String[]{lore});
    }

    public static ItemStack buildItem(String name, Material m, String lore) {
        return buildItem(name, new ItemStack(m), new String[]{lore});
    }

    public static ItemStack buildItem(String name, XMaterial m, String lore) {
        return buildItem(name, m.parseMaterial(), lore);
    }
}
