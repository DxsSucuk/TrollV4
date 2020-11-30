package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.player.ArrayUtils;
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

	public static void createNPC(int id, Player p, Location loc, Location lookat, ItemStack item) {
		
		MineSkinFetcher.fetchSkinFromIdAsync(id, skin -> {
			
			NPC npc = Main.npcLib.createNPC();
			npc.setLocation(loc);
			npc.lookAt(lookat);
			npc.setSkin(skin);
			
			if(item != null) {
				 npc.setItem(NPCSlot.MAINHAND, item);
			}
			
			ArrayUtils.npcs.add(npc);
			npc.create();
			
			Bukkit.getScheduler().runTask(Main.instance, () -> npc.show(p));
		});
	}
	
	public static void createGoldenWind(int id, Player p, Location loc, Location lookat, ItemStack item) {
		
		MineSkinFetcher.fetchSkinFromIdAsync(id, skin -> {
			
			NPC npc = Main.npcLib.createNPC();
			npc.setLocation(loc);
			npc.lookAt(lookat);
			npc.setSkin(skin);
			
			if(item != null) {
				 npc.setItem(NPCSlot.MAINHAND, item);
			}
			
			ArrayUtils.npcs.add(npc);
			npc.create();
			
			new BukkitRunnable() {

				@Override
				public void run() {
					if (ArrayUtils.isJojo) {
						if (npc != null) {
							npc.playAnimation(NPCAnimation.SWING_MAINHAND);
							npc.playAnimation(NPCAnimation.SWING_OFFHAND);
						}
					} else {
						cancel();
					}
				}
			}.runTaskTimer(Main.instance, 20L, 5L);
			
			Bukkit.getScheduler().runTask(Main.instance, () -> npc.show(p));
		});
	}
	
	public static void destroyAllNPCs() {
		for (NPC npc : ArrayUtils.npcs) {
			npc.destroy();
			ArrayUtils.npcs.remove(npc);
		}
	}
	
}
