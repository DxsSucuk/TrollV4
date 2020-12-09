package de.presti.trollv4.utils.server;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import net.jitse.npclib.api.NPC;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2020											    *
*	Erstellt: 06.12.2020 / 01:24:54											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
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
