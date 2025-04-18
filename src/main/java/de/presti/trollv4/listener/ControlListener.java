package de.presti.trollv4.listener;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.TrollV4;
import de.presti.trollv4.utils.control.CommandGUI;
import de.presti.trollv4.utils.player.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.libraryaddict.disguise.DisguiseAPI;

@SuppressWarnings("deprecation")
public class ControlListener implements Listener {

	TrollV4 plugin;

	public ControlListener(TrollV4 c) {
		plugin = c;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.hasMetadata("C_P")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (p.hasMetadata("C_P")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (p.hasMetadata("C_P")) {
			if (p.hasMetadata("C_Chat")) {
				p.removeMetadata("C_Chat", plugin);
			} else {
				e.setCancelled(true);
			}
		} else {
			if (p.hasMetadata("C_H")) {
				e.setCancelled(true);
				Player v = Bukkit.getPlayer(p.getMetadata("C_H").get(0).asString());
				v.setMetadata("C_Chat", new FixedMetadataValue(plugin, true));
				v.chat(e.getMessage());
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = e.getEntity();

			if (e.getEntity().getKiller() instanceof Player) {
				if (e.getEntity().getKiller().hasMetadata("C_H")) {
					Player v = Bukkit.getPlayer(e.getEntity().getKiller().getMetadata("C_H").get(0).asString());
					e.setDeathMessage(e.getDeathMessage().replaceAll(e.getEntity().getKiller().getName(), v.getName()));
				}
			} else {
				if (p.hasMetadata("C_H")) {
					Player v = Bukkit.getPlayer(p.getMetadata("C_H").get(0).asString());
					e.setDeathMessage(e.getDeathMessage().replaceAll(p.getName(), v.getName()));
				}
			}

		} else {
			if (e.getEntity().getKiller() instanceof Player) {
				if (e.getEntity().getKiller().hasMetadata("C_H")) {
					Player v = Bukkit.getPlayer(e.getEntity().getKiller().getMetadata("C_H").get(0).asString());
					e.setDeathMessage(e.getDeathMessage().replaceAll(e.getEntity().getKiller().getName(), v.getName()));
				}
			}
		}
	}

	@EventHandler
	public void onPlayerRunCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (p.hasMetadata("C_P")) {
			if (!p.hasMetadata("C_CMD")) {
				e.setCancelled(true);
			} else {
				// Run command as normal and remove command-sending permissions
				p.removeMetadata("C_CMD", plugin);
			}
		} else {
			if (p.hasMetadata("C_H")) {
				if (!p.hasMetadata("C_CMD")) {
					e.setCancelled(true);
					Player v = Bukkit.getPlayer(p.getMetadata("C_H").get(0).asString());
					Bukkit.getServer().getPluginManager().registerEvents(new CommandGUI(e.getMessage(), p, v, plugin),
							plugin);
				} else {
					// Run command as normal and remove command-sending permissions
					p.removeMetadata("C_CMD", plugin);
				}
			}
		}
	}

	@EventHandler
	public void onPlayerGetKicked(PlayerKickEvent e) {
		Player p = e.getPlayer();
		if (p.hasMetadata("C_P")) {
			Player c = Bukkit.getPlayer(p.getMetadata("C_P").get(0).asString());
			
			c.sendMessage(Data.prefix + p.getName() + " disconnected");

			p.removeMetadata("C_P", plugin);
			c.removeMetadata("C_H", plugin);

			// Give the victim their inventory back
			p.getInventory().setContents(c.getInventory().getContents());
			p.getInventory().setArmorContents(c.getInventory().getArmorContents());

			c.getInventory().setContents(ArrayUtils.inventory.get(c.getName()));
			c.getInventory().setArmorContents(ArrayUtils.armor.get(c.getName()));
			ArrayUtils.inventory.remove(c.getName());
			ArrayUtils.armor.remove(c.getName());

			DisguiseAPI.undisguiseToAll(c);
			p.setGameMode(GameMode.SURVIVAL);
		} else {
			if (p.hasMetadata("C_H")) {
				Player v = Bukkit.getPlayer(p.getMetadata("C_H").get(0).asString());
				
				v.removeMetadata("C_P", plugin);
				p.removeMetadata("C_H", plugin);

				// Give the victim their inventory back
				v.getInventory().setContents(p.getInventory().getContents());
				v.getInventory().setArmorContents(p.getInventory().getArmorContents());

				p.getInventory().setContents(ArrayUtils.inventory.get(p.getName()));
				p.getInventory().setArmorContents(ArrayUtils.armor.get(p.getName()));
				ArrayUtils.inventory.remove(p.getName());
				ArrayUtils.armor.remove(p.getName());

				DisguiseAPI.undisguiseToAll(p);
				v.setGameMode(GameMode.SURVIVAL);
			}
		}
	}

	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (p.hasMetadata("C_P")) {
			Player c = Bukkit.getPlayer(p.getMetadata("C_P").get(0).asString());
			
			c.sendMessage(Data.prefix + p.getName() + " disconnected");
			
			p.removeMetadata("C_P", plugin);
			c.removeMetadata("C_H", plugin);

			// Give the victim their inventory back
			p.getInventory().setContents(c.getInventory().getContents());
			p.getInventory().setArmorContents(c.getInventory().getArmorContents());

			c.getInventory().setContents(ArrayUtils.inventory.get(c.getName()));
			c.getInventory().setArmorContents(ArrayUtils.armor.get(c.getName()));
			ArrayUtils.inventory.remove(c.getName());
			ArrayUtils.armor.remove(c.getName());

			DisguiseAPI.undisguiseToAll(c);
			p.setGameMode(GameMode.SURVIVAL);
		} else {
			if (p.hasMetadata("C_H")) {
				Player v = Bukkit.getPlayer(p.getMetadata("C_H").get(0).asString());

				v.removeMetadata("C_P", plugin);
				p.removeMetadata("C_H", plugin);

				// Give the victim their inventory back
				v.getInventory().setContents(p.getInventory().getContents());
				v.getInventory().setArmorContents(p.getInventory().getArmorContents());

				p.getInventory().setContents(ArrayUtils.inventory.get(p.getName()));
				p.getInventory().setArmorContents(ArrayUtils.armor.get(p.getName()));
				ArrayUtils.inventory.remove(p.getName());
				ArrayUtils.armor.remove(p.getName());

				DisguiseAPI.undisguiseToAll(p);
				v.setGameMode(GameMode.SURVIVAL);
			}
		}
	}
}
