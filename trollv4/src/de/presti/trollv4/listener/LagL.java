package de.presti.trollv4.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import com.cryptomorin.xseries.XMaterial;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.ArrayUtils;

public class LagL implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (ArrayUtils.lagging.contains(player)) {
			Material material = e.getBlockPlaced().getType();
			Location location = e.getBlockPlaced().getLocation();
			location.getWorld().getBlockAt(location).setType(XMaterial.AIR.parseMaterial());
			Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
				@Override
				public void run() {
					location.getWorld().getBlockAt(location).setType(material);
				}
			}, 34);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (ArrayUtils.lagging.contains(player)) {
			Material drops = e.getBlock().getType();
			e.setCancelled(true);
			Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
				@Override
				public void run() {
					e.getBlock().getLocation().getWorld().getBlockAt(e.getBlock().getLocation())
							.setType(XMaterial.AIR.parseMaterial());
					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(drops));
				}
			}, 31);
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (ArrayUtils.lagging.contains(p)) {
			if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getY() != e.getTo().getY()
					|| e.getFrom().getZ() != e.getTo().getZ()) {
				if (new Random().nextInt(100) < 3) {
					p.teleport(e.getFrom());
				}
			}
		}
	}

/*	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (ArrayUtils.lagging.contains(p)) {
			new BukkitRunnable() {

				@Override
				public void run() {
					p.chat(e.getMessage());
					cancel();
				}
			}.runTaskLater(Main.instance, (new Random().nextInt(5) * 20L));
			e.setCancelled(true);
		}
	} */
	
	//Very Buggy currently!

}
