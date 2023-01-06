package de.presti.trollv4.utils.server;

import com.github.juliarn.npclib.api.Npc;
import com.github.juliarn.npclib.api.Platform;
import com.github.juliarn.npclib.api.profile.Profile;
import com.github.juliarn.npclib.api.protocol.enums.ItemSlot;
import com.github.juliarn.npclib.bukkit.BukkitPlatform;
import com.github.juliarn.npclib.bukkit.BukkitWorldAccessor;
import com.github.juliarn.npclib.bukkit.util.BukkitPlatformUtil;
import com.github.juliarn.npclib.common.CommonNpcTracker;
import com.github.juliarn.npclib.common.npc.CommonNpcBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.cryptomorin.xseries.particles.XParticle;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.player.ArrayUtils;

import java.util.UUID;

public class NPCUtil {

    public static Platform<World, Player, ItemStack, Plugin> platform = BukkitPlatform.bukkitNpcPlatformBuilder()
            .extension(Main.instance)
            .debug(true)
            .worldAccessor(BukkitWorldAccessor.nameBasedAccessor())
            .npcTracker(CommonNpcTracker.newNpcTracker())
            .build();

    public static Npc createNPC(Location loc, Location lookat, ItemStack item) {

        Npc npc = new CommonNpcBuilder<>(platform)
                .position(BukkitPlatformUtil.positionFromBukkitLegacy(loc))
                .profile(Profile.resolved("name", UUID.randomUUID()))
                .buildAndTrack();

        npc.lookAt(BukkitPlatformUtil.positionFromBukkitLegacy(lookat));

        if (item != null)
            npc.changeItem(ItemSlot.MAIN_HAND, item);


        // TODO:: add skin.
        return npc;
    }

    public static void createGiorno(int id, Player p, Location loc, Location lookat, ItemStack item) {
        ArrayUtils.jojo.put(p, new Npc[]{createNPC(loc, lookat, item), null});
    }

    public static void createGoldenWind(int id, Player p, Location loc, Location lookat, ItemStack item) {

        Npc npc = new CommonNpcBuilder<>(platform)
                .position(BukkitPlatformUtil.positionFromBukkitLegacy(loc))
                .profile(Profile.resolved("name", UUID.randomUUID()))
                .buildAndTrack();

        npc.lookAt(BukkitPlatformUtil.positionFromBukkitLegacy(lookat));

        if (item != null)
            npc.changeItem(ItemSlot.MAIN_HAND, item);

        Npc[] npcs = ArrayUtils.jojo.get(p);
        npcs[1] = npc;

        ArrayUtils.jojo.put(p, npcs);

        new BukkitRunnable() {

            @Override
            public void run() {
                if (ArrayUtils.jojo.containsKey(p)) {
                    if (npc != null) {

                        if (!Main.version.startsWith("v1_8")) {
                            // TODO:: hitting animation off hand.
                            p.spawnParticle(XParticle.getParticle("CRIT"), p.getLocation(), 3);
                        }

                        if (p != null) {
                            if (!p.isDead()) {
                                p.damage(0.1D);
                            }
                        }

                    }
                } else {
                    cancel();
                    npc.unlink();
                }
            }
        }.runTaskTimer(Main.instance, 20L, 10L);

        new BukkitRunnable() {

            @Override
            public void run() {
                if (ArrayUtils.jojo.containsKey(p)) {
                    if (npc != null) {

                        // TODO:: hitting animation main hand.

                        if (p != null) {
                            if (!p.isDead()) {
                                p.damage(0.1D);
                                p.spawnParticle(XParticle.getParticle("CRIT"), p.getLocation(), 3);
                            }
                        }

                    }
                } else {
                    cancel();
                    npc.unlink();
                }
            }
        }.runTaskTimer(Main.instance, 25L, 10L);
    }
    public static void destroyNPCsFromPlayer(Player p) {
        if (ArrayUtils.jojo.containsKey(p)) {
            for (Npc npc : ArrayUtils.jojo.get(p)) {
                npc.unlink();
            }
            ArrayUtils.jojo.remove(p);
        }
    }

}
