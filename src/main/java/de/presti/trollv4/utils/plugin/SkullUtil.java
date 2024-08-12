package de.presti.trollv4.utils.plugin;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.profiles.builder.XSkull;
import com.cryptomorin.xseries.profiles.objects.Profileable;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class SkullUtil {

    public static ItemStack getSkull(UUID uuid) {
        if (uuid == null) {
            return XMaterial.PLAYER_HEAD.parseItem();
        }
        try {
            return XSkull.createItem().profile(Profileable.of(uuid)).apply();
        } catch (Exception e) {
            return XMaterial.PLAYER_HEAD.parseItem();
        }
    }

}
