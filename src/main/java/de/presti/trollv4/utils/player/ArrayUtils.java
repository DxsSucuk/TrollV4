package de.presti.trollv4.utils.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import de.presti.trollv4.utils.server.NPCUserContainer;
import de.presti.trollv4.utils.server.NPCUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.presti.trollv4.invs.InvSaver;

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

	public static ArrayList<Player> vomit = new ArrayList<>();

	// HashMaps
	public static ConcurrentHashMap<String, String> trolling = new ConcurrentHashMap<>();
	public static HashMap<Player, WrappedTask> anim = new HashMap<>();
	public static HashMap<Player, Location> arrest = new HashMap<>();
	public static HashMap<Player, WrappedTask> wtf = new HashMap<>();
	public static HashMap<Player, WrappedTask> hackuser = new HashMap<>();
	public static HashMap<Player, WrappedTask> arrowspam = new HashMap<>();
	public static HashMap<Player, WrappedTask> tornador = new HashMap<>();
	public static HashMap<Player, WrappedTask> anvils = new HashMap<>();
	public static HashMap<Player, WrappedTask> jumping = new HashMap<>();
	public static HashMap<Player, InvSaver> fakeinv = new HashMap<>();
	public static HashMap<Player, NPCUserContainer> jojo = new HashMap<>();
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
				} catch (Exception ignore) {}
				
				spookylast.remove(p);
			}
			
			spooky.remove(p);
		}

		if (freeze.contains(p)) {
			p.setWalkSpeed(0.2F);
			p.setFlySpeed(0.1F);
			freeze.remove(p);
		}

		herobrine.remove(p);

		if (userbowspam.contains(p)) {
			arrowspam.get(p).cancel();
			userbowspam.remove(p);
		}

		if (hackuser.containsKey(p)) {
			hackuser.get(p).cancel();
		}

		tntp.remove(p);

		rotateplayer.remove(p);

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

		fc.remove(p);

		lagging.remove(p);

		randomtp.remove(p);

		noinv.remove(p);

		deaf.remove(p);

		confus.remove(p);

		randomtp.remove(p);

		if (tornado.contains(p)) {
			tornador.get(p).cancel();
			tornado.remove(p);
			tornador.remove(p);
		}
		
		if(anvils.containsKey(p)) {
			anvils.get(p).cancel();
			anvils.remove(p);
		}

		if(jumping.containsKey(p)) {
			jumping.get(p).cancel();
			jumping.remove(p);
		}

		vomit.remove(p);
	}

}
