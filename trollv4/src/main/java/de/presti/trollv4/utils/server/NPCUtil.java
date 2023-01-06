package de.presti.trollv4.utils.server;

import com.github.juliarn.npclib.api.Npc;
import com.github.juliarn.npclib.api.Platform;
import com.github.juliarn.npclib.api.profile.Profile;
import com.github.juliarn.npclib.api.profile.ProfileProperty;
import com.github.juliarn.npclib.api.protocol.enums.EntityAnimation;
import com.github.juliarn.npclib.api.protocol.enums.ItemSlot;
import com.github.juliarn.npclib.bukkit.BukkitPlatform;
import com.github.juliarn.npclib.bukkit.BukkitWorldAccessor;
import com.github.juliarn.npclib.bukkit.util.BukkitPlatformUtil;
import com.github.juliarn.npclib.common.CommonNpcTracker;
import com.github.juliarn.npclib.common.npc.CommonNpcBuilder;
import de.presti.trollv4.api.PlayerInfo;
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

    public static Npc createNPC(String name, Location loc, Location lookat, ItemStack item) {
        UUID uuid = UUID.fromString(PlayerInfo.getUUID(name));
        return createNPC(name, uuid, loc, lookat, item);
    }

    public static Npc createNPC(UUID uuid, Location loc, Location lookat, ItemStack item) {
        String name = PlayerInfo.getName(uuid.toString());
        return createNPC(name, uuid, loc, lookat, item);
    }

    public static Npc createNPC(String name, UUID uuid, Location loc, Location lookat, ItemStack item) {
        return createNPC(Profile.resolved(name, uuid), loc, lookat, item);
    }

    public static Npc createNPC(Profile profile, Location loc, Location lookat, ItemStack item) {

        Npc.Builder commonNpcBuilder = new CommonNpcBuilder<>(platform)
                .position(BukkitPlatformUtil.positionFromBukkitLegacy(loc));

        if (profile != null) {
            commonNpcBuilder.profile(profile);
        }

        Npc npc = commonNpcBuilder.buildAndTrack();

        npc.lookAt(BukkitPlatformUtil.positionFromBukkitLegacy(lookat));

        if (item != null)
            npc.changeItem(ItemSlot.MAIN_HAND, item);


        return npc;
    }

    public static void createGiorno(Player p, Location loc, Location lookat, ItemStack item) {
        Profile profile = Profile.unresolved("giorno");

        profile.properties().add(ProfileProperty.property("texture",
                "ewogICJ0aW1lc3RhbXAiIDogMTYwNjc2ODkyMDIyNiwKICAicHJvZmlsZUlkIiA6ICJjMWE3ZjlkZjgyYTU0NjZmOGQ2YjdkYTk3OTA4NGY0OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTa2lkU25pcGU5NzIyMTMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODg2Zjk1NzFmNGFiMWEyNzBhOTcyN2UzMjg1M2YwYWY2YWI4MWQ0YTY1NzU0M2Q3ODdjYjA3NGUzNzQ3MWQzYiIKICAgIH0KICB9Cn0=",
                "O2Yl4kzJwU5wsUEFdiQyaGS9NyrL9eF8UgRN+QpypbCIYVaIJi39qK9B3+wcS2OXUGzkd0Rkv1sJGUiEZhhn0e9TzC1oLGqg8UhSZR3RYcv7SwkOT1E7oo5uL6sUR0JaOrUlFUQN8OHYsrOU7GGn0sa4Zn0GAczMbHd1j5RaVyMYaIGnhMfYaXcI7Kj1M7gq7W16a3kRWZfu0Y7AAm+ZA3um3rGtN09W4trciqTa50aD4aXGJFFENTKMPdOkkDeKJzi4M3FYB+haTLascsL0awU3fnO6vXiDFwLhI4BiT2ThxFlkBN3eBZmtsHvQdqvHC3RpfQkx1dXbuIqW9dmyInTBUdF+MWAObDGHiMV3/CcL+4m1MJC6ziIxS5TnVrC+OJE4ALsW9QaPjIPaURj2tywQZjrUwi1lq8fKxxf8H1ZgOYgjXRCe18Rx5qqwzV2yDGMmQ+C/OMP/RWXV5mKsTDBn1DhomWb075mZgta8RTNRsaaAQ0pd2aulyDfTUhDsWUoHijZ08U1w2W2OWtalNTOXkWPk6mdX9cXxWscSd6mRYntmq3L66woRvkjZDZ3T65jN+BP05vLkTEsJgBTdj8VD1/QeQ3qwdbq9ApDFQKVfk0kp+37uMv+dzVsFtsrak0ZDSikMuSvpU37mOxl/emlKSmug5yd9aeHPonlvZWY="));

        ArrayUtils.jojo.put(p, new Npc[]{createNPC(profile, loc, lookat, item), null});
    }

    public static void createGoldenWind(Player p, Location loc, Location lookat, ItemStack item) {

        Profile profile = Profile.unresolved("goldenwind");

        profile.properties().add(ProfileProperty.property("texture",
                "ewogICJ0aW1lc3RhbXAiIDogMTYwNjc2ODg3OTA5MCwKICAicHJvZmlsZUlkIiA6ICI3ZGEyYWIzYTkzY2E0OGVlODMwNDhhZmMzYjgwZTY4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHb2xkYXBmZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQxZWUyYjYwYTlkODFlYTgyZWIyNTBjNzEyYjNhNmJhYmVkMDNkMjFkNmY4MTg3MDBjNzBlNDM4OGU2YTUxOCIKICAgIH0KICB9Cn0=",
                "bagjIfZeP2KVjDnzLFZZDlhkJwQJlXcMtZBIjpKnBlgusmckNQzV6vZZmYVU4axmyCWkzCUgf5RcmVKQNkFHnhcWDFhw6juHIVjjB5Q2l+XZ4krrIcXiV00rc2vmWJEyZr3qikcGBKzUByOteqXYLj9Z/d+c40Ag7AKaU1fgsFPbJQz0nhQMxTeLwH8QnVOCoJANK9VryLJ2G7jlVemtfUmdMRdb7VKu7OfwTUoFlxW3WZofX3hVnlnTa8+/HtIyaKKJc7FJccIA9dqT/KLGYQwgT/FVpK4ZFJ+hdbB/3asQYiK51nkFSysQ41kuieNDTUM4hn1vyLVLLX/DMrnK7HIM/aHto+yIt6JowZyaVGhNPxuncx/s0U9mx8KtJNSv0qhDVSQS6TWx/AaJOIm28w1vtz9/ZS4uy5uQbMI9/j7z8eEPBlgqLJh0IhiaKNsI2k8lHaD9eNr2INdphits9DW2qdmFuz/jd0j4NywiLw/PcYn6km2PQkpsKvUQ5DBHA/ZZU0Yh3m8gGXJFtfxuJrNz3YIsmqf/9eptSmT2hfot+dpkwhxlqkg8yCba/uSDU9IzYhhDJRi+233slSXoN6QnRz5GRvJlrSwqqUlWSf94z44t/+2zLmzXrSLcipBIBWWslOgBPAlmebtWfCT1Xk9Prqu/RDzzK0NlMkO3VWI="));

        Npc npc = createNPC(profile, loc, lookat, item);

        Npc[] npcs = ArrayUtils.jojo.get(p);
        npcs[1] = npc;

        ArrayUtils.jojo.put(p, npcs);

        new BukkitRunnable() {

            @Override
            public void run() {
                if (ArrayUtils.jojo.containsKey(p)) {
                    if (npc != null) {

                        if (!ServerInfo.is18()) {
                            npc.platform().packetFactory().createAnimationPacket(EntityAnimation.SWING_OFF_HAND).scheduleForTracked(npc);
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

                        npc.platform().packetFactory().createAnimationPacket(EntityAnimation.SWING_MAIN_ARM).scheduleForTracked(npc);

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
