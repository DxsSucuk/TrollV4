package de.presti.trollv4.utils.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.invs.InvSaver;
import de.presti.trollv4.utils.server.NPCUserContainer;
import de.presti.trollv4.utils.server.NPCUtil;
import net.jitse.npclib.api.NPC;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 17.10.2018 / 23:55:02												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class ArrayUtils {

	// ArrayLists
	public static ArrayList<Player> vanish = new ArrayList<>();
	public static ArrayList<Player> fc = new ArrayList<>();
	public static ArrayList<Player> freeze = new ArrayList<>();
	public static ArrayList<Player> lagging = new ArrayList<>();
	public static ArrayList<Player> rotateplayer = new ArrayList<>();
	public static ArrayList<Player> randomtp = new ArrayList<>();
	public static ArrayList<Player> tntp = new ArrayList<>();
	public static ArrayList<Player> herobrine = new ArrayList<>();
	public static ArrayList<Player> userbowspam = new ArrayList<>();
	public static ArrayList<Player> tornado = new ArrayList<>();
	public static ArrayList<Player> noinv = new ArrayList<>();
	public static ArrayList<Player> noitem = new ArrayList<>();
	public static ArrayList<Player> deaf = new ArrayList<>();
	public static ArrayList<Player> confus = new ArrayList<>();

	// HashMaps
	public static HashMap<String, String> trolling = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> anim = new HashMap<>();
	public static HashMap<Player, Location> arrest = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> wtf = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> hackuser = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> arrowspam = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> tornador = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> anvils = new HashMap<>();
	public static HashMap<Player, BukkitRunnable> jumping = new HashMap<>();
	public static HashMap<Player, InvSaver> fakeinv = new HashMap<>();
	public static HashMap<Player, NPC> jojo = new HashMap<>();
	public static HashMap<Player, NPC> jojo2 = new HashMap<>();
	public static HashMap<Player, NPCUserContainer> spooky = new HashMap<>();
	public static HashMap<Player, Location> spookylast = new HashMap<>();

	// Controlls
	public static HashMap<String, ItemStack[]> inventory;
	public static HashMap<String, ItemStack[]> armor;
	public static List<String> cd;

	@SuppressWarnings("deprecation")
	public static void removeFromAll(Player p) {
		if (vanish.contains(p)) {
			vanish.remove(p);
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.showPlayer(p);
			}
		}

		if (spooky.containsKey(p)) {

			if (spookylast.containsKey(p)) {
				try {
					p.teleport(spookylast.get(p));
				} catch (Exception ex) {}
				
				spookylast.remove(p);
			}
			
			spooky.remove(p);
		}

		if (freeze.contains(p)) {
			p.setWalkSpeed(0.2F);
			p.setFlySpeed(0.1F);
			freeze.remove(p);
		}

		if (herobrine.contains(p)) {
			herobrine.remove(p);
		}

		if (userbowspam.contains(p)) {
			arrowspam.get(p).cancel();
			userbowspam.remove(p);
		}

		if (hackuser.containsKey(p)) {
			hackuser.get(p).cancel();
		}

		if (tntp.contains(p)) {
			tntp.remove(p);
		}

		if (rotateplayer.contains(p)) {
			rotateplayer.remove(p);
		}

		if (fakeinv.containsKey(p)) {
			InvSaver is = fakeinv.get(p);

			p.getInventory().setArmorContents(is.getArmor());
			p.getInventory().setContents(is.getContent());
			p.getInventory().setExtraContents(is.getExtracont());

			fakeinv.remove(p);
		}

		if (jojo.containsKey(p)) {
			NPCUtil.destroyNPCsFromPlayer(p);
		}

		if (fc.contains(p)) {
			fc.remove(p);
		}

		if (lagging.contains(p)) {
			lagging.remove(p);
		}

		if (randomtp.contains(p)) {
			randomtp.remove(p);
		}

		if (noinv.contains(p)) {
			noinv.remove(p);
		}

		if (deaf.contains(p)) {
			deaf.remove(p);
		}

		if (confus.contains(p)) {
			confus.remove(p);
		}

		if (randomtp.contains(p)) {
			randomtp.remove(p);
		}

		if (tornado.contains(p)) {
			tornador.get(p).cancel();
			tornado.remove(p);
			tornador.remove(p);
		}
		
		if(anvils.containsKey(p)) {
			anvils.get(p).cancel();
			anvils.remove(p);
		}
	}

}
