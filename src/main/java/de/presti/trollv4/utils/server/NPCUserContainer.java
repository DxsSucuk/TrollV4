package de.presti.trollv4.utils.server;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.juliarn.npclib.api.Npc;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import org.bukkit.entity.Player;

public class NPCUserContainer {

	Player p;
	ArrayList<Npc> npcs = new ArrayList<Npc>();
	
	public NPCUserContainer(Player user) {
		p = user;
	}
	
	public void addNPC(Npc np) {
		npcs.add(np);
	}
	
	public ArrayList<Npc> getNPCs() {
		return npcs;
	}
	
	public void removeNPC(Npc np) {
		npcs.remove(np);
	}

	public HashMap<Runnable, WrappedTask> tasks = new HashMap<>();
	
}
