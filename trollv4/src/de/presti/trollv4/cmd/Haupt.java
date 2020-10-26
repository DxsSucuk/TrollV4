package de.presti.trollv4.cmd;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.invs.*;
import de.presti.trollv4.main.*;
import de.presti.trollv4.utils.*;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 11.05.2019 / 19:14:10												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich gesch§tzt.			*
*	Das Urheberrecht liegt, soweit nicht ausdr§cklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielf§ltigung, Verbreitung, Vermietung, Verleihung,			*
*	§ffentlichen Zug§nglichmachung oder anderer Nutzung							*
*	bedarf der ausdr§cklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Haupt implements CommandExecutor {

	public void rop(final Player p) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			public void run() {
				if (ArrayUtils.rotateplayer.contains(p)) {
					p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(),
							p.getLocation().getZ(), p.getLocation().getYaw() + 2.0F, p.getLocation().getPitch()));
				}
			}
		}, 0L, 2L);
	}

	public void rtp(final Player p) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			public void run() {
				if (ArrayUtils.randomtp.contains(p)) {
					
					p.teleport(LocationUtil.getLocFromRad(p.getLocation(), 10, 5, 10));
				}
			}
		}, 0L, 5L);
	}

	public void spawntntatplayer(final Player p) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			public void run() {
				if (ArrayUtils.tntp.contains(p)) {

					TNTPrimed tnt = (TNTPrimed) p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
					tnt.setCustomName("§cExplode");
				}
			}
		}, 0L, (long)(Config.cfg.getInt("trolls.tnttrace.spawndelay")));
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
					new InvManager().choicePlayer(p);
					p.sendMessage(Data.prefix + Language.getMessage("command.troll.default"));
				} else {
					p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
				}
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					if (p.hasPermission("troll.help") || p.hasPermission("troll.*")) {
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.1"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.2"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.3"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.4"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.5"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.6"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.7"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.8"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.9"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.10"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.11"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.help.line.12"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else if (args[0].equalsIgnoreCase("changelog")) {
					if (p.hasPermission("troll.log") || p.hasPermission("troll.*")) {
						Changelog.sendlog(p);
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else if (args[0].equalsIgnoreCase("credits")) {
					if (p.hasPermission("troll.log") || p.hasPermission("troll.*")) {
						Changelog.sendCredits(p);
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else if (args[0].equalsIgnoreCase("gm")) {
					if (p.hasPermission("troll.gm") || p.hasPermission("troll.*")) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.gm"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else if (args[0].equalsIgnoreCase("fly")) {
					if (p.hasPermission("troll.fly") || p.hasPermission("troll.*")) {
						if (p.getAllowFlight()) {
							p.setAllowFlight(false);
							p.sendMessage(Data.prefix + Language.getMessage("command.troll.fly.off"));
						} else {
							p.setAllowFlight(true);
							p.sendMessage(Data.prefix + Language.getMessage("command.troll.fly.on"));
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else if (args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("vanish")) {
					if (p.hasPermission("troll.vanish") || p.hasPermission("troll.*")) {
						p.playSound(p.getLocation(), XSound.ENTITY_ENDERMAN_TELEPORT.parseSound(), 300.0F, 20.0F);
						if (ArrayUtils.vanish.contains(p)) {
							for (Player all : Bukkit.getOnlinePlayers()) {
								all.showPlayer(p);
							}
							p.sendMessage(Data.prefix + Language.getMessage("command.troll.vanish.off"));
							ArrayUtils.vanish.remove(p);
						} else {
							p.playSound(p.getLocation(), XSound.ENTITY_ENDERMAN_TELEPORT.parseSound(), 300.0F, 20.0F);
							p.sendMessage(Data.prefix + Language.getMessage("command.troll.vanish.on"));
							for (Player all : Bukkit.getOnlinePlayers()) {
								if (all.hasPermission("troll.vanish")) {
									all.showPlayer(p);
								} else {
									all.hidePlayer(p);
								}
							}
							ArrayUtils.vanish.add(p);
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else if (args[0].equalsIgnoreCase("items")) {
					if (p.hasPermission("troll.items") || p.hasPermission("troll.*")) {
						new InvManager().openItemInv(p);
						p.playSound(p.getLocation(), XSound.BLOCK_CHEST_OPEN.parseSound(), 3, 1);
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.items"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else if (args[0].equalsIgnoreCase("server")) {
					if (p.hasPermission("troll.server") || p.hasPermission("troll.*")) {
						new InvManager().openServerInv(p);
						p.playSound(p.getLocation(), XSound.BLOCK_CHEST_OPEN.parseSound(), 3, 1);
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.server"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				} else {
					if (p.hasPermission("troll.help") || p.hasPermission("troll.*")) {
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.notfound.line.1"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.notfound.line.2"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.notfound.line.3"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.notfound.line.4"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.notfound.line.5"));
						p.sendMessage(Data.prefix + Language.getMessage("command.troll.notfound.line.6"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				}
			} else {
				p.sendMessage(Data.prefix + Language.getMessage("command.troll.wrongargs"));
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(Data.prefix + Language.getMessage("command.troll.noplayer"));
		}
		return false;
	}

}
