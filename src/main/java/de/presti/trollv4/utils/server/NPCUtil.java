package de.presti.trollv4.utils.server;

import com.github.juliarn.npclib.api.Npc;
import com.github.juliarn.npclib.api.Platform;
import com.github.juliarn.npclib.api.profile.Profile;
import com.github.juliarn.npclib.api.profile.ProfileProperty;
import com.github.juliarn.npclib.api.profile.ProfileResolver;
import com.github.juliarn.npclib.api.protocol.enums.EntityAnimation;
import com.github.juliarn.npclib.api.protocol.enums.ItemSlot;
import com.github.juliarn.npclib.bukkit.BukkitPlatform;
import com.github.juliarn.npclib.bukkit.BukkitWorldAccessor;
import com.github.juliarn.npclib.bukkit.util.BukkitPlatformUtil;
import com.github.juliarn.npclib.common.CommonNpcTracker;
import com.github.juliarn.npclib.common.npc.CommonNpcBuilder;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import de.presti.trollv4.api.PlayerInfo;
import de.presti.trollv4.logging.Logger;
import io.sentry.Sentry;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.cryptomorin.xseries.particles.XParticle;

import de.presti.trollv4.main.TrollV4;
import de.presti.trollv4.utils.player.ArrayUtils;

import java.util.*;

public class NPCUtil {

    public static Platform<World, Player, ItemStack, Plugin> platform = BukkitPlatform.bukkitNpcPlatformBuilder()
            .extension(TrollV4.getInstance())
            .debug(true)
            .worldAccessor(BukkitWorldAccessor.nameBasedAccessor())
            .npcTracker(CommonNpcTracker.newNpcTracker())
            .build();

    public static Npc createNPC(String name, Location loc, Location lookat, ItemStack item, Player player) {
        ArrayList<Player> list = new ArrayList<>();
        list.add(player);
        return createNPC(name, loc, lookat, item, list);
    }

    public static Npc createNPC(String name, Location loc, Location lookat, ItemStack item, List<Player> viewers) {
        UUID uuid = UUID.fromString(PlayerInfo.getUUID(name));
        return createNPC(name, uuid, loc, lookat, item, viewers);
    }

    public static Npc createNPC(UUID uuid, Location loc, Location lookat, ItemStack item, List<Player> viewers) {
        String name = PlayerInfo.getName(uuid.toString());
        return createNPC(name, uuid, loc, lookat, item, viewers);
    }

    public static Npc createNPC(String name, UUID uuid, Location loc, Location lookat, ItemStack item, List<Player> viewers) {
        try {
            return createNPC(ProfileResolver.mojang().resolveProfile(Profile.unresolved(uuid)).get(), loc, lookat, item, viewers);
        } catch (Exception ignore) {
            return createNPC(Profile.resolved(name, uuid), loc, lookat, item, viewers);
        }
    }

    public static Npc createNPC(Profile.Resolved profile, Location loc, Location lookat, ItemStack item, List<Player> viewers) {

        if (profile == null) {
            Logger.info("No Profile given!");
            return null;
        }

        try {
            Npc.Builder commonNpcBuilder = new CommonNpcBuilder<>(platform)
                    .position(BukkitPlatformUtil.positionFromBukkitLegacy(loc))
                    .profile(profile.withName("§7"));

            if (ServerInfo.is(8) && lookat != null) {
                commonNpcBuilder.flag(Npc.LOOK_AT_PLAYER, true);
            }

            Npc npc = commonNpcBuilder.buildAndTrack();

            if (lookat != null)
                npc.lookAt(BukkitPlatformUtil.positionFromBukkitLegacy(lookat));

            for (Player player : viewers) {
                if (!npc.trackedPlayers().contains(player))
                    npc.trackPlayer(player);
            }

            if (item != null)
                npc.changeItem(ItemSlot.MAIN_HAND, item);

            return npc;
        } catch (Exception exception) {
            Sentry.captureException(exception);
            TrollV4.getInstance().getLogger().warning("Received an error in NPCUtil: " + exception.getMessage());
        }

        return null;
    }

    public static void createGiorno(Player p, Player t, Location loc, Location lookat, ItemStack item) {

        Set<ProfileProperty> property = new HashSet<>();

        property.add(ProfileProperty.property("textures",
                "ewogICJ0aW1lc3RhbXAiIDogMTYwNjc2ODkyMDIyNiwKICAicHJvZmlsZUlkIiA6ICJjMWE3ZjlkZjgyYTU0NjZmOGQ2YjdkYTk3OTA4NGY0OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTa2lkU25pcGU5NzIyMTMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODg2Zjk1NzFmNGFiMWEyNzBhOTcyN2UzMjg1M2YwYWY2YWI4MWQ0YTY1NzU0M2Q3ODdjYjA3NGUzNzQ3MWQzYiIKICAgIH0KICB9Cn0=",
                "O2Yl4kzJwU5wsUEFdiQyaGS9NyrL9eF8UgRN+QpypbCIYVaIJi39qK9B3+wcS2OXUGzkd0Rkv1sJGUiEZhhn0e9TzC1oLGqg8UhSZR3RYcv7SwkOT1E7oo5uL6sUR0JaOrUlFUQN8OHYsrOU7GGn0sa4Zn0GAczMbHd1j5RaVyMYaIGnhMfYaXcI7Kj1M7gq7W16a3kRWZfu0Y7AAm+ZA3um3rGtN09W4trciqTa50aD4aXGJFFENTKMPdOkkDeKJzi4M3FYB+haTLascsL0awU3fnO6vXiDFwLhI4BiT2ThxFlkBN3eBZmtsHvQdqvHC3RpfQkx1dXbuIqW9dmyInTBUdF+MWAObDGHiMV3/CcL+4m1MJC6ziIxS5TnVrC+OJE4ALsW9QaPjIPaURj2tywQZjrUwi1lq8fKxxf8H1ZgOYgjXRCe18Rx5qqwzV2yDGMmQ+C/OMP/RWXV5mKsTDBn1DhomWb075mZgta8RTNRsaaAQ0pd2aulyDfTUhDsWUoHijZ08U1w2W2OWtalNTOXkWPk6mdX9cXxWscSd6mRYntmq3L66woRvkjZDZ3T65jN+BP05vLkTEsJgBTdj8VD1/QeQ3qwdbq9ApDFQKVfk0kp+37uMv+dzVsFtsrak0ZDSikMuSvpU37mOxl/emlKSmug5yd9aeHPonlvZWY="));


        Profile.Resolved profile = Profile.resolved("random", UUID.randomUUID(), property);

        ArrayList<Player> list = new ArrayList<>();
        list.add(p);
        list.add(t);

        Npc giorno = createNPC(profile, loc, lookat, item, list);
        giorno.forceTrackPlayer(t);

        NPCUserContainer container = new NPCUserContainer(p);
        container.addNPC(giorno);

        ArrayUtils.jojo.put(p, container);
    }

    public static void createGoldenWind(Player p, Player t, Location loc, Location lookat, ItemStack item) {

        Set<ProfileProperty> property = new HashSet<>();

        property.add(ProfileProperty.property("textures",
                "ewogICJ0aW1lc3RhbXAiIDogMTYwNjc2ODg3OTA5MCwKICAicHJvZmlsZUlkIiA6ICI3ZGEyYWIzYTkzY2E0OGVlODMwNDhhZmMzYjgwZTY4ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHb2xkYXBmZWwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQxZWUyYjYwYTlkODFlYTgyZWIyNTBjNzEyYjNhNmJhYmVkMDNkMjFkNmY4MTg3MDBjNzBlNDM4OGU2YTUxOCIKICAgIH0KICB9Cn0=",
                "bagjIfZeP2KVjDnzLFZZDlhkJwQJlXcMtZBIjpKnBlgusmckNQzV6vZZmYVU4axmyCWkzCUgf5RcmVKQNkFHnhcWDFhw6juHIVjjB5Q2l+XZ4krrIcXiV00rc2vmWJEyZr3qikcGBKzUByOteqXYLj9Z/d+c40Ag7AKaU1fgsFPbJQz0nhQMxTeLwH8QnVOCoJANK9VryLJ2G7jlVemtfUmdMRdb7VKu7OfwTUoFlxW3WZofX3hVnlnTa8+/HtIyaKKJc7FJccIA9dqT/KLGYQwgT/FVpK4ZFJ+hdbB/3asQYiK51nkFSysQ41kuieNDTUM4hn1vyLVLLX/DMrnK7HIM/aHto+yIt6JowZyaVGhNPxuncx/s0U9mx8KtJNSv0qhDVSQS6TWx/AaJOIm28w1vtz9/ZS4uy5uQbMI9/j7z8eEPBlgqLJh0IhiaKNsI2k8lHaD9eNr2INdphits9DW2qdmFuz/jd0j4NywiLw/PcYn6km2PQkpsKvUQ5DBHA/ZZU0Yh3m8gGXJFtfxuJrNz3YIsmqf/9eptSmT2hfot+dpkwhxlqkg8yCba/uSDU9IzYhhDJRi+233slSXoN6QnRz5GRvJlrSwqqUlWSf94z44t/+2zLmzXrSLcipBIBWWslOgBPAlmebtWfCT1Xk9Prqu/RDzzK0NlMkO3VWI="));

        Profile.Resolved profile = Profile.resolved("random", UUID.randomUUID(), property);

        ArrayList<Player> list = new ArrayList<>();
        list.add(p);
        list.add(t);

        Npc npc = createNPC(profile, loc, lookat, item, list);
        if (npc == null) return;

        npc.forceTrackPlayer(t);

        NPCUserContainer container = ArrayUtils.jojo.get(p);
        container.npcs.set(1, npc);

        Runnable runnable;
        WrappedTask task = TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(t, runnable = new Runnable() {
            @Override
            public void run() {
                if (ArrayUtils.jojo.containsKey(t)) {
                    if (ServerInfo.above(8)) {
                        npc.playAnimation(EntityAnimation.SWING_OFF_HAND).scheduleForTracked();
                        t.spawnParticle(XParticle.CRIT.get(), t.getLocation(), 3);
                    }

                    if (!t.isDead()) {
                        t.damage(0.1D);
                    }
                } else {
                    container.tasks.get(this).cancel();
                    npc.unlink();
                }
            }
        }, 20, 10);

        Runnable runnable2;
        WrappedTask task2 = TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(t, runnable2 = new Runnable() {
            @Override
            public void run() {
                if (ArrayUtils.jojo.containsKey(t)) {
                    npc.playAnimation(EntityAnimation.SWING_MAIN_ARM).scheduleForTracked();

                    if (t.isOnline() && !t.isDead()) {
                        t.damage(0.1D);
                        t.spawnParticle(XParticle.CRIT.get(), t.getLocation(), 3);
                    }
                } else {
                    container.tasks.get(this).cancel();
                    npc.unlink();
                }
            }
        }, 25, 10);

        container.tasks.put(runnable, task);
        container.tasks.put(runnable2, task2);

        ArrayUtils.jojo.put(t, container);
    }

    public static void destroyNPCsFromPlayer(Player p) {
        if (ArrayUtils.jojo.containsKey(p)) {
            for (Npc npc : ArrayUtils.jojo.get(p).npcs) {
                npc.unlink();
            }
            ArrayUtils.jojo.remove(p);
        }
    }

}
