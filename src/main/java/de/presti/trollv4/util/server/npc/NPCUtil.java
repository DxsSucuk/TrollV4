package de.presti.trollv4.util.server.npc;

import de.presti.trollv4.util.player.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.cryptomorin.xseries.particles.XParticle;

import de.presti.trollv4.main.Main;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.api.state.NPCAnimation;
import net.jitse.npclib.api.state.NPCSlot;

public class NPCUtil {

	public static NPCLib npcLib;

	public static void init() {
		npcLib = new NPCLib(Main.instance);
	}

	public static void createNPC(int id, Player p, Location loc, Location lookat, ItemStack item) {

		MineSkinFetcher.fetchSkinFromIdAsync(id, skin -> {

			NPC npc = npcLib.createNPC();
			npc.setLocation(loc);
			npc.lookAt(lookat);
			npc.setSkin(skin);

			if (item != null) {
				npc.setItem(NPCSlot.MAINHAND, item);
			}

			ArrayUtils.jojo.put(p, npc);
			npc.create();

			Bukkit.getScheduler().runTask(Main.instance, () -> npc.show(p));
		});
	}

	public static void createGoldenWind(int id, Player p, Location loc, Location lookat, ItemStack item) {

		MineSkinFetcher.fetchSkinFromIdAsync(id, skin -> {

			NPC npc = npcLib.createNPC();
			npc.setLocation(loc);
			npc.lookAt(lookat);
			npc.setSkin(skin);

			if (item != null) {
				npc.setItem(NPCSlot.MAINHAND, item);
			}

			ArrayUtils.jojo2.put(p, npc);
			npc.create();

			new BukkitRunnable() {

				@Override
				public void run() {
					if (ArrayUtils.jojo.containsKey(p) && ArrayUtils.jojo2.containsKey(p)) {
						if (npc != null) {

							if (!Main.version.startsWith("v1_8")) {
								npc.playAnimation(NPCAnimation.SWING_OFFHAND);
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
						npc.destroy();
					}
				}
			}.runTaskTimer(Main.instance, 20L, 10L);

			new BukkitRunnable() {

				@Override
				public void run() {
					if (ArrayUtils.jojo.containsKey(p) && ArrayUtils.jojo2.containsKey(p)) {
						if (npc != null) {

							npc.playAnimation(NPCAnimation.SWING_MAINHAND);

							if (p != null) {
								if (!p.isDead()) {
									p.damage(0.1D);
									p.spawnParticle(XParticle.getParticle("CRIT"), p.getLocation(), 3);
								}
							}

						}
					} else {
						cancel();
						npc.destroy();
					}
				}
			}.runTaskTimer(Main.instance, 25L, 10L);

			Bukkit.getScheduler().runTask(Main.instance, () -> npc.show(p));
		});
	}

	public static void teleportNPCToPlayer(NPC npc, Player p) {
		npc.destroy();
		npc.setLocation(p.getLocation());
		npc.create();
		npc.show(p);
	}

	public static void teleportNPCToLocation(NPC npc, Location t, Player p) {
		npc.destroy();
		npc.setLocation(t);
		npc.create();
		npc.show(p);
	}

	public static void teleportNPCToLocationWithLook(NPC npc, Location t, Location lookat, Player p) {
		npc.destroy();
		npc.setLocation(t);
		npc.lookAt(lookat);
		npc.create();
		npc.show(p);
	}

	public static void destroyNPCsFromPlayer(Player p) {
		if (ArrayUtils.jojo.containsKey(p)) {
			ArrayUtils.jojo.get(p).destroy();
			ArrayUtils.jojo.remove(p);
		}

		if (ArrayUtils.jojo2.containsKey(p)) {
			ArrayUtils.jojo2.get(p).destroy();
			ArrayUtils.jojo2.remove(p);
		}
	}

}