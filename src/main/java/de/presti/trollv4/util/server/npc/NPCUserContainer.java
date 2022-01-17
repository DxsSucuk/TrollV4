package de.presti.trollv4.util.server.npc;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import net.jitse.npclib.api.NPC;

public class NPCUserContainer {

	Player p;
	ArrayList<NPC> npcs = new ArrayList<NPC>();
	
	public NPCUserContainer(Player user) {
		p = user;
	}
	
	public void addNPC(NPC np) {
		npcs.add(np);
	}
	
	public ArrayList<NPC> getNPCs() {
		return npcs;
	}
	
	public void removeNPC(NPC np) {
		npcs.remove(np);
	}
	
}