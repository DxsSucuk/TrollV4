package de.presti.trollv4.trolls;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class Troll {

    String name, displayName, description, permission;
    XMaterial material;

    public Troll(String name, String displayName, String description, String permission, XMaterial material) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.permission = permission;
        this.material = material;
    }

    public abstract void onCall(Player p, Player t);

    public ItemStack getAsItemStack() {
        ItemStack is = new ItemStack(getMaterial().parseItem());
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(getDisplayName());
        meta.setLore(Arrays.asList(getDescription()));
        is.setItemMeta(meta);

        return is;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public XMaterial getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Troll(name=" + getName()
                + ", displayname=" + getDisplayName()
                + ", description=" + getDescription()
                + ", permission=" + getPermission()
                + ", xmaterial=" + getMaterial().toString();
    }
}
