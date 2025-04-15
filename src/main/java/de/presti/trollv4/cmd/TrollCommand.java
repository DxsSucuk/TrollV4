package de.presti.trollv4.cmd;

import com.tcoded.folialib.wrapper.task.WrappedTask;
import de.presti.trollv4.config.language.LanguageService;
import de.presti.trollv4.invs.InvManager;
import de.presti.trollv4.main.Changelog;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.TrollV4;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.player.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import com.cryptomorin.xseries.XSound;

import java.util.HashMap;

// TODO:: Clean this entire mess up.

public class TrollCommand implements CommandExecutor {

    HashMap<Player, WrappedTask> ropTasks = new HashMap<>();
    HashMap<Player, WrappedTask> rtpTasks = new HashMap<>();
    HashMap<Player, WrappedTask> tntSpawnTasks = new HashMap<>();

    public void rop(final Player p) {
        WrappedTask task = TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(p, new Runnable() {
            @Override
            public void run() {
                if (ArrayUtils.rotateplayer.contains(p)) {
                    TrollV4.getInstance().getFoliaLib().getScheduler()
                            .teleportAsync(p, new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw() + 2.0F, p.getLocation().getPitch()));
                } else {
                    TrollV4.getInstance().getFoliaLib().getScheduler().cancelTask(ropTasks.get(p));
                }
            }
        }, 0L, 2L);

        ropTasks.put(p, task);
    }

    public void rtp(final Player p) {
        WrappedTask task = TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(p, new Runnable() {
            @Override
            public void run() {
                if (ArrayUtils.randomtp.contains(p)) {
                    TrollV4.getInstance().getFoliaLib().getScheduler()
                            .teleportAsync(p, LocationUtil.getLocFromRad(p.getLocation(), 10, 5, 10));
                } else {
                    TrollV4.getInstance().getFoliaLib().getScheduler().cancelTask(rtpTasks.get(p));
                }
            }
        }, 0L, 2L);

        rtpTasks.put(p, task);
    }

    public void spawnTnTAtPlayer(final Player p) {
        WrappedTask task = TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(p, new Runnable() {
            @Override
            public void run() {
                if (ArrayUtils.tntp.contains(p)) {
                    TrollV4.getInstance().getFoliaLib().getScheduler()
                            .teleportAsync(p, LocationUtil.getLocFromRad(p.getLocation(), 10, 5, 10));
                    TrollV4.getInstance().getFoliaLib().getScheduler().runAtLocation(p.getLocation(), x -> {
                        TNTPrimed tnt = p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
                        tnt.setCustomName("§cExplode");
                    });
                } else {
                    TrollV4.getInstance().getFoliaLib().getScheduler().cancelTask(tntSpawnTasks.get(p));
                }
            }
        }, 0L, 2L);

        tntSpawnTasks.put(p, task);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                    InvManager.choicePlayer(p);
                    p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.default"));
                } else {
                    p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    if (p.hasPermission("troll.help") || p.hasPermission("troll.*")) {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.1"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.2"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.3"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.4"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.5"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.6"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.7"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.8"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.9"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.10"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.11"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.help.line.12"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (args[0].equalsIgnoreCase("changelog")) {
                    if (p.hasPermission("troll.log") || p.hasPermission("troll.*")) {
                        Changelog.sendlog(p);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (args[0].equalsIgnoreCase("credits")) {
                    if (p.hasPermission("troll.log") || p.hasPermission("troll.*")) {
                        Changelog.sendCredits(p);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (args[0].equalsIgnoreCase("gm")) {
                    if (p.hasPermission("troll.gm") || p.hasPermission("troll.*")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.gm"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (args[0].equalsIgnoreCase("fly")) {
                    if (p.hasPermission("troll.fly") || p.hasPermission("troll.*")) {
                        if (p.getAllowFlight()) {
                            p.setAllowFlight(false);
                            p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.fly.off"));
                        } else {
                            p.setAllowFlight(true);
                            p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.fly.on"));
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("vanish")) {
                    if (p.hasPermission("troll.vanish") || p.hasPermission("troll.*")) {
                        p.playSound(p.getLocation(), XSound.ENTITY_ENDERMAN_TELEPORT.parseSound(), 300.0F, 20.0F);
                        if (ArrayUtils.vanish.contains(p)) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.showPlayer(p);
                            }
                            p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.vanish.off"));
                            ArrayUtils.vanish.remove(p);
                        } else {
                            p.playSound(p.getLocation(), XSound.ENTITY_ENDERMAN_TELEPORT.parseSound(), 300.0F, 20.0F);
                            p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.vanish.on"));
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
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (args[0].equalsIgnoreCase("items")) {
                    if (p.hasPermission("troll.items") || p.hasPermission("troll.*")) {
                        InvManager.openItemInv(p);
                        p.playSound(p.getLocation(), XSound.BLOCK_CHEST_OPEN.parseSound(), 3, 1);
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.items"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (args[0].equalsIgnoreCase("server")) {
                    if (p.hasPermission("troll.server") || p.hasPermission("troll.*")) {
                        InvManager.openServerInv(p);
                        p.playSound(p.getLocation(), XSound.BLOCK_CHEST_OPEN.parseSound(), 3, 1);
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.server"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else if (Bukkit.getPlayer(args[0]) != null) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(args[0]);
                        InvManager.openTrollInv(p, t);
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.default"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                } else {
                    if (p.hasPermission("troll.help") || p.hasPermission("troll.*")) {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.notfound.line.1"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.notfound.line.2"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.notfound.line.3"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.notfound.line.4"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.notfound.line.5"));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.notfound.line.6"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                    }
                }
            } else {
                p.sendMessage(Data.prefix + LanguageService.getDefault("command.troll.wrongargs"));
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(Data.prefix + LanguageService.getDefault("command.troll.noplayer"));
        }
        return false;
    }

}
