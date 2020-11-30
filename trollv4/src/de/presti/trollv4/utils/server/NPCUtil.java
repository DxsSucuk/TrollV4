package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.player.ArrayUtils;
import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;
import net.jitse.npclib.api.state.NPCAnimation;
import net.jitse.npclib.api.state.NPCSlot;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2020											    *
*	Erstellt: 30.11.2020 / 21:17:45											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
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
							npc.playAnimation(NPCAnimation.SWING_MAINHAND);
							
							if (!Main.version.startsWith("v1_8")) {
								npc.playAnimation(NPCAnimation.SWING_OFFHAND);
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
			}.runTaskTimer(Main.instance, 20L, 5L);

			Bukkit.getScheduler().runTask(Main.instance, () -> npc.show(p));
		});
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
