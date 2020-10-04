package de.presti.trollv4.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.invs.InvSaver;
import de.presti.trollv4.main.*;
import de.presti.trollv4.utils.*;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 18.04.2019 / 23:00:04												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Event implements Listener {

	// OVERALL

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (p.getUniqueId().toString().trim().equalsIgnoreCase("1c32b55b-d458-4347-a579-8754f4510081")) {
			p.sendMessage(Data.prefix + "Plugin Version: " + Data.version);
			p.sendMessage(Data.prefix + "Server Version: " + Main.version);
			p.sendMessage(Data.prefix + "Server Language: " + Language.getLanguage());
		}

		for (Player all : Bukkit.getOnlinePlayers()) {
			if (ArrayUtils.vanish.contains(all)) {
				p.hidePlayer(all);
			}
		}

		if (p.hasPermission("troll.help")) {
			if (Config.getconfig().getBoolean("AutoUpdate")) {
				if (!Data.version.equals(UpdateChecker.spigotPluginVersion)
						&& !(UpdateChecker.cvi > UpdateChecker.nvi)) {
					p.sendMessage(Data.prefix + "TrollV4 has a Update!");
					p.sendMessage(Data.prefix + "New Version: " + UpdateChecker.spigotPluginVersion);
					p.sendMessage(Data.prefix + "Your Version: " + Data.version);
					p.sendMessage(Data.prefix + "Download here: https://www.spigotmc.org/resources/" + UpdateChecker.ID
							+ "/updates");
				} else {
					p.sendMessage(Data.prefix + "TrollV4 has no Update");
				}
			}
		}
	}

	// OVERALL

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (ArrayUtils.vanish.contains(p)) {
			ArrayUtils.vanish.remove(p);
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.showPlayer(p);
			}
		}
		if (ArrayUtils.freeze.contains(p)) {
			p.setWalkSpeed(0.2F);
			p.setFlySpeed(0.1F);
			ArrayUtils.freeze.remove(p);
		}

		if (ArrayUtils.herobrine.contains(p)) {
			ArrayUtils.herobrine.remove(p);
		}

		if (ArrayUtils.userbowspam.contains(p)) {
			ArrayUtils.arrowspam.get(p).cancel();
			ArrayUtils.userbowspam.remove(p);
		}

		if (ArrayUtils.hackuser.containsKey(p)) {
			ArrayUtils.hackuser.get(p).cancel();
		}

		if (ArrayUtils.tntp.contains(p)) {
			ArrayUtils.tntp.remove(p);
		}

		if (ArrayUtils.rotateplayer.contains(p)) {
			ArrayUtils.rotateplayer.remove(p);
		}

		if (ArrayUtils.fakeinv.containsKey(p)) {
			InvSaver is = ArrayUtils.fakeinv.get(p);

			p.getInventory().setArmorContents(is.getArmor());
			p.getInventory().setContents(is.getContent());
			p.getInventory().setExtraContents(is.getExtracont());

			ArrayUtils.fakeinv.remove(p);
		}
	}

	// NoInv

	@EventHandler
	public void onInvetoryOpen(InventoryOpenEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player p = (Player) e.getPlayer();
			if (ArrayUtils.noinv.contains(p)) {
				e.setCancelled(true);
				p.getOpenInventory().close();
			}
		}
	}

	// LAG

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

	// LAG

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

	// FAKEHACKS FREEZE HEROBRINE LAG

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (ArrayUtils.freeze.contains(p)) {
			p.teleport(e.getFrom());
		}

		if (ArrayUtils.fc.contains(p)) {
			p.setAllowFlight(true);
			p.setVelocity(p.getLocation().getDirection().setZ(0.1D).setX(0.1D));
			p.setVelocity(p.getLocation().getDirection().setZ(-0.1D).setX(-0.1D));
			p.setVelocity(p.getLocation().getDirection().setY(-9));
			p.setAllowFlight(false);
			p.setWalkSpeed(1.0E-6F);
		}

		if (ArrayUtils.herobrine.contains(p)) {
			if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY()
					|| e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
				e.getFrom().getWorld().strikeLightning(e.getFrom());
				e.getFrom().getWorld().spawnEntity(LocationUtil.getLocFromRad(e.getFrom(), 10), EntityType.SILVERFISH);
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.playSound(e.getFrom(), XSound.ENTITY_ZOMBIE_VILLAGER_CURE.parseSound(), 0.05F, 0.05F);
				}
			}
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
			}
		}

		if (ArrayUtils.lagging.contains(p)) {
			if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY()
					|| e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
				if (new Random().nextInt(100) < 3) {
					p.teleport(e.getFrom());
				}
			}
		}
	}

	// ARROWSPAM

	@EventHandler
	public static void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();

		if (ArrayUtils.userbowspam.contains(p)) {
			e.setDeathMessage(null);
			ArrayUtils.arrowspam.get(p).cancel();
			ArrayUtils.userbowspam.remove(p);
		}
	}

	// FIREBALL and MINIGUN

	@EventHandler
	public void onPlayerStickClick(PlayerInteractEvent e) {
		try {
			Player p = e.getPlayer();

			ItemStack fireball = new ItemStack(XMaterial.STICK.parseMaterial());
			ItemMeta meta = fireball.getItemMeta();
			meta.setDisplayName("§4FireBall");
			fireball.setItemMeta(meta);

			ItemStack ironaxe = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
			ItemMeta meta2 = ironaxe.getItemMeta();
			meta2.setDisplayName("§4MiniGun");
			ironaxe.setItemMeta(meta2);

			if (((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) | e.getAction().equals(Action.RIGHT_CLICK_AIR)))
					&& (p.getItemInHand().getItemMeta().getDisplayName().equals("§4MiniGun"))
					&& (p.hasPermission("troll.minigun") || p.hasPermission("troll.*"))) {
				p.launchProjectile(Arrow.class);
				p.launchProjectile(Snowball.class);
				p.playSound(p.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 1.0F, 1.0F);
			}

			if (p.getItemInHand() != null) {
				if (((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)))
						&& (p.getItemInHand().getItemMeta().getDisplayName().equals("§4FireBall"))
						&& (p.hasPermission("troll.fireball") || p.hasPermission("troll.*"))) {
					p.launchProjectile(Fireball.class);
					p.playSound(p.getLocation(), XSound.ENTITY_GENERIC_EXPLODE.parseSound(), 1.0F, 1.0F);
				}
			}
		} catch (Exception e1) {
			// e1.printStackTrace();
		}
	}

	// BOW ITEMS

	@EventHandler
	public void onArrowHit(ProjectileHitEvent e) {
		try {
			if (((e.getEntity() instanceof Arrow)) && ((e.getEntity().getShooter() instanceof Player))) {
				Player shooter = (Player) e.getEntity().getShooter();
				World world = e.getEntity().getWorld();
				Location loc = e.getEntity().getLocation();
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§4TNTBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.createExplosion(loc, 2.0F);
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§cLavaBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.getBlockAt(loc).setType(XMaterial.LAVA.parseMaterial());
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§bBlitzBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.strikeLightning(loc);
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§2CreeperBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.spawnEntity(loc, EntityType.CREEPER);
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§0BedrockBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.getBlockAt(loc).setType(XMaterial.BEDROCK.parseMaterial());
					}
				}
			}
		} catch (Exception e1) {
			System.out.println("Couldnt Spawn! Pls Report: " + e1.getMessage());
		}
	}
}
