package de.presti.trollv4.utils.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.player.ArrayUtils;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;
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
			ArrayUtils.npcs.add(npc);
			npc.create();
			
			if(item != null) {
				 npc.setItem(NPCSlot.MAINHAND, item);
			}
			
			Bukkit.getScheduler().runTask(Main.instance, () -> npc.show(p));
		});
	}
	
	public static NPC createNPCandGetNPC(int id, Player p, Location loc, Location lookat, ItemStack item) {
		
		final NPC[] npc = new NPC[1];
		
		MineSkinFetcher.fetchSkinFromIdAsync(id, skin -> {
			
		    npc[0] = Main.npcLib.createNPC();
		    npc[0].setLocation(loc);
		    npc[0].lookAt(lookat);
			ArrayUtils.npcs.add(npc[0]);
			npc[0].create();
			
			if(item != null) {
				npc[0].setItem(NPCSlot.MAINHAND, item);
			}
			
			Bukkit.getScheduler().runTask(Main.instance, () -> npc[0].show(p));
		});
		
		return npc[0];
	}
	
	public static void destroyAllNPCs() {
		for (NPC npc : ArrayUtils.npcs) {
			npc.destroy();
			ArrayUtils.npcs.remove(npc);
		}
	}
	
}
