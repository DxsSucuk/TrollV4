package de.presti.trollv4.api;

import java.util.Random;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.github.juliarn.npclib.api.Npc;
import com.github.juliarn.npclib.api.Position;
import com.github.juliarn.npclib.bukkit.util.BukkitPlatformUtil;
import com.tcoded.folialib.wrapper.task.WrappedTask;
import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.language.LanguageService;
import de.presti.trollv4.main.TrollV4;
import de.presti.trollv4.utils.crossversion.HS;
import de.presti.trollv4.utils.crossversion.Titles;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.player.LocationUtil;
import de.presti.trollv4.utils.plugin.RandomUtility;
import de.presti.trollv4.utils.server.NPCUserContainer;
import de.presti.trollv4.utils.server.NPCUtil;
import de.presti.trollv4.utils.server.ServerInfo;
import de.presti.trollv4.utils.server.versions.ServerSoftware;
import io.sentry.Sentry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XPotion;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.cmd.TrollCommand;

public class TrollV4API {

    private static int taskID;

    /**
     * Lets a player control another player. This will be stopped after the player
     * leaves or the control stops.
     *
     * @param user   the player that should be in control.
     * @param victim the player that should be controlled.
     * @since 4.4.4
     */
    public static void startControl(Player user, Player victim) {
        TrollV4.startControlling(user, victim);
    }

    /**
     * Stops the control of a user. This will stop the control of a player.
     *
     * @param user   the player that should release the victim.
     * @param victim the player that should be released.
     * @since 4.4.4
     */
    public static void stopControl(Player user, Player victim) {
        TrollV4.stopControlling(user, victim);
    }

    /**
     * Shows a player the Minecraft Demoscreen. Currently, this only works with 1.8.
     *
     * @param victim the player that should be controlled.
     * @since 4.4.4
     */
    public static void DemoScreen(Player victim) {
        sendGameStateChange(victim, 5, 0, false);
    }

    /**
     * Lets a player be Herobrine. This spawns Silverfishes and Thunderbolts
     * around him.
     *
     * @param victim the player that's should be herobrine.
     * @since 4.4.4
     */
    public static void Herobrine(Player victim) {
        if (ArrayUtils.herobrine.contains(victim)) {
            ArrayUtils.herobrine.remove(victim);
        } else {
            ArrayUtils.herobrine.add(victim);
        }
    }

    /**
     * This spawns Arrows around a player. Just Vibing in here.
     *
     * @param victim that should get spammed.
     * @since 4.4.4
     */
    public static void ArrowSpam(Player victim) {
        if (ArrayUtils.userbowspam.contains(victim)) {
            ArrayUtils.arrowspam.get(victim).cancel();
            ArrayUtils.userbowspam.remove(victim);
        } else {
            ArrayUtils.userbowspam.add(victim);
            WrappedTask wrappedTask = TrollV4.getInstance().getFoliaLib().getScheduler().runTimer(new Runnable() {
                @Override
                public void run() {
                    Location playerLocation = victim.getLocation().clone();

                    for (int i = 0; i < 5; i++) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().runAtLocation(victim.getLocation(), x -> {
                            Arrow arrow = victim.getWorld()
                                    .spawn(playerLocation.clone().add(TrollV4.getInstance().getRandom(-10, 10),
                                                    TrollV4.getInstance().getRandom(5, 10), TrollV4.getInstance().getRandom(-10, 10)),
                                            Arrow.class);

                            TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntity(arrow, y -> {
                                Location arrowLocation = arrow.getLocation();
                                Vector angle = new Vector(playerLocation.getX() - arrowLocation.getX(), playerLocation.getY() - arrowLocation.getBlockY(),
                                        playerLocation.getZ() - arrowLocation.getBlockZ());
                                arrow.setVelocity(angle.normalize().multiply(2.0D));
                            });
                        });
                    }
                }
            }, 0, 10);
            ArrayUtils.arrowspam.put(victim, wrappedTask);
        }
    }

    /**
     * This "hacks" an player. Plays sound and send titles.
     *
     * @param victim that should get hacked.
     * @since 4.4.4
     */
    public static void Hack(Player victim) {
        if (victim != null) {
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(TrollV4.getInstance(), new Runnable() {
                int countdown = 15;

                @Override
                public void run() {
                    if (countdown <= 0) {
                        HS.Hack(victim);
                        Titles.send(victim, 1, 20, 1, "§cHACKED", "§4" + RandomUtility.getRandomID());
                        Bukkit.getScheduler().cancelTask(taskID);

                        return;
                    }

                    HS.Hack2(victim);
                    Titles.send(victim, 1, 20, 1, "§cHacking in " + countdown, "§4" + RandomUtility.getRandomID());
                    victim.damage(0.1D);
                    countdown--;
                }
            }, 0, 20);
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * This explodes the player. Spawns an explosion and set the health to 0.
     *
     * @param victim that should go boom.
     * @since 4.4.4
     */
    public static void Explode(Player victim) {
        if (victim != null) {
            victim.playSound(victim.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 100.0F, 200.0F);
            victim.getWorld().createExplosion(victim.getLocation(), 3.0F);
            victim.setHealth(0.0D);
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Lets others think that the player cheates. Lets an player fly in the air and
     * fall down.
     *
     * @param victim that should seem like an cheater.
     * @since 4.4.4
     */
    public static void FakeHack(Player victim) {
        if (victim != null) {
            if (ArrayUtils.fc.contains(victim)) {
                victim.setWalkSpeed(0.2F);
                victim.setAllowFlight(false);
                ArrayUtils.fc.remove(victim);
            } else {
                ArrayUtils.fc.add(victim);

            }
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * This spawns an thunder bolt on an player. Player go bzzzzzzzzz.
     *
     * @param victim that should get striked.
     * @since 4.4.4
     */
    public static void Strike(Player victim) {
        if (victim != null) {
            victim.getLocation().getWorld().strikeLightning(victim.getLocation());
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Lets an player fly in the air. Adds y-velocity to the player.
     *
     * @param victim that should fly like an rocket.
     * @since 4.4.4
     */
    public static void Rocket(Player victim) {
        if (victim != null) {
            victim.setAllowFlight(true);
            victim.setVelocity(victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
            victim.playSound(victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
            victim.playSound(victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
            victim.setAllowFlight(false);
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * This spams the chat of an player. Sends an message 500 times.
     *
     * @param victim that should get spammed.
     * @since 4.4.4
     */
    public static void Spam(Player victim) {
        if (victim != null) {
            for (int i = 0; i < 500; i++) {
                victim.sendMessage("§cDont Cheat!");
            }
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * This send an fakeop message to the player. jebaited.
     *
     * @param victim that should get fakeoped.
     * @since 4.4.4
     */
    public static void FakeOP(Player victim) {
        if (victim != null) {
            victim.sendMessage(LanguageService.getDefault("gui.fakeop.opm", victim));
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * This crashes an player. It kicks an player with a fake crashmessage.
     *
     * @param victim that should get kicked.
     * @since 4.4.4
     */
    public static void Crash(Player victim) {
        if (victim != null) {
            victim.kickPlayer(LanguageService.getDefault("gui.crash.message"));
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Freeze ma budda. This adds the player to an arraylist which teleports him
     * back everytime he moves.
     *
     * @param victim that should get freezed.
     * @since 4.4.4
     */
    public static void Freeze(Player victim) {
        if (victim != null) {
            if (ArrayUtils.freeze.contains(victim)) {
                victim.setWalkSpeed(0.2F);
                victim.setFlySpeed(0.1F);
                ArrayUtils.freeze.remove(victim);
            } else {
                victim.setWalkSpeed(0F);
                victim.setFlySpeed(0F);
                ArrayUtils.freeze.add(victim);
            }
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Teleport the Player to the same position he is and that simulates a SetBack,
     * and it sends a "AntiCheat Detection" Message.
     *
     * @param victim that should get fake detected.
     * @since 4.4.4
     */
    public static void AntiCheat(Player victim) {
        if (victim != null) {
            victim.sendMessage(LanguageService.getDefault("gui.anticheat.detected"));
            TrollV4.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, new Location(victim.getWorld(), victim.getLocation().getX(),
                    victim.getLocation().getY() + 2, victim.getLocation().getZ()));
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * This SetBacks the player sometimes and delays block placements and block breaks
     * delays the Itempickup too.
     *
     * @param victim that should lag.
     * @since 4.4.4
     */
    public static void Lagging(Player victim) {
        if (victim != null) {
            if (!ArrayUtils.lagging.contains(victim)) {
                ArrayUtils.lagging.add(victim);
            } else {
                ArrayUtils.lagging.remove(victim);
            }
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Spawn a Bedrock Jail around the Player
     * and don't let him escape.
     *
     * @param victim that should be arrested.
     * @since 4.4.4
     */
    public static void Arrest(Player victim) {
        if (victim != null) {
            TrollV4.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, victim.getLocation());
            TrollV4.getInstance().getFoliaLib().getScheduler().runAtLocation(victim.getLocation(), x -> {
                // Y
                victim.getWorld()
                        .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                victim.getLocation().getY() - 1, victim.getLocation().getZ()))
                        .setType(XMaterial.BEDROCK.parseMaterial());
                victim.getWorld()
                        .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                victim.getLocation().getY() + 2, victim.getLocation().getZ()))
                        .setType(XMaterial.BEDROCK.parseMaterial());
                // X
                victim.getWorld()
                        .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                victim.getLocation().getY(), victim.getLocation().getZ()))
                        .setType(XMaterial.BEDROCK.parseMaterial());
                victim.getWorld()
                        .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                victim.getLocation().getY(), victim.getLocation().getZ()))
                        .setType(XMaterial.BEDROCK.parseMaterial());
                // Z
                victim.getWorld()
                        .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                victim.getLocation().getY(), victim.getLocation().getZ() + 1))
                        .setType(XMaterial.BEDROCK.parseMaterial());
                victim.getWorld()
                        .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                victim.getLocation().getY(), victim.getLocation().getZ() - 1))
                        .setType(XMaterial.BEDROCK.parseMaterial());
            });

        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Permanently Rotate a Player
     * rotation seems legit.
     *
     * @param victim that should rotate.
     * @since 4.4.4
     */
    public static void Rotate(Player victim) {
        if (victim != null) {
            if (ArrayUtils.rotateplayer.contains(victim)) {
                ArrayUtils.rotateplayer.remove(victim);
            } else {
                new TrollCommand().rop(victim);
                ArrayUtils.rotateplayer.add(victim);
            }
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Randomly Teleport a Player around the world
     * the server could lag.
     *
     * @param victim that should get teleported.
     * @since 4.4.4
     */
    public static void RandomTP(Player victim) {
        if (victim != null) {
            if (ArrayUtils.randomtp.contains(victim)) {
                ArrayUtils.randomtp.remove(victim);
            } else {
                new TrollCommand().rtp(victim);
                ArrayUtils.randomtp.add(victim);
            }
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Spawns a TNT Trace behind the Player
     * timer can be adjusted in the config.
     *
     * @param victim the TNT Trace victim.
     * @since 4.4.4
     */
    public static void TnTTrace(Player victim) {
        if (victim != null) {
            if (ArrayUtils.tntp.contains(victim)) {
                ArrayUtils.tntp.remove(victim);
            } else {
                new TrollCommand().spawnTnTAtPlayer(victim);
                ArrayUtils.tntp.add(victim);
            }
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Same as Arrest
     * Lets the Player send weird messages.
     *
     * @param victim that should get WTFed.
     * @since 4.4.4
     */
    public static void WTF(Player victim) {
        if (victim != null) {

            victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 5, true));
            victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
            victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 10, true));

            WrappedTask task = TrollV4.getInstance().getFoliaLib().getScheduler().runTimer(new Runnable() {
                int countdown = 4;

                @Override
                public void run() {
                    if (countdown == 0) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, victim.getLocation());
                        victim.getWorld()
                                .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                        victim.getLocation().getY() + 1, victim.getLocation().getZ() + 1))
                                .setType(XMaterial.GLASS.parseMaterial());
                        TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntity(victim, x -> {
                            victim.chat("Help me pls Im stuck ;-; I dont know where Im pls help!!!");
                        });
                        TrollV4.getInstance().getFoliaLib().getScheduler().cancelTask(ArrayUtils.wtf.get(victim));
                        return;
                    }

                    if (countdown > 0) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, victim.getLocation());
                    }

                    if (countdown == 4) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().runAtLocation(victim.getLocation(), x -> {
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() - 1, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                        });
                    }

                    if (countdown == 3) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().runAtLocation(victim.getLocation(), x -> {
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY(), victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY(), victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY(), victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY(), victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY(), victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY(), victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY(), victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY(), victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() + 1, victim.getLocation().getZ()))
                                    .setType(XMaterial.GLASS.parseMaterial());
                        });
                    }

                    if (countdown == 2) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().runAtLocation(victim.getLocation(), x -> {
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() + 1, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() + 1, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() + 1, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() + 1, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY() + 1, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.GLASS.parseMaterial());
                        });
                    }

                    if (countdown == 1) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().runAtLocation(victim.getLocation(), x -> {
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() + 2, victim.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            victim.getWorld()
                                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                                            victim.getLocation().getY() + 1, victim.getLocation().getZ()))
                                    .setType(XMaterial.GLASS.parseMaterial());
                        });
                    }

                    if (countdown <= 0) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().cancelTask(ArrayUtils.wtf.get(victim));
                    }
                    countdown--;
                }
            }, 0, 20);

            ArrayUtils.wtf.put(victim, task);
        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Same as Arrested
     * but with Cobweb.
     *
     * @param victim that should get WebTraped.
     * @since 4.4.4
     */
    public static void WebTrap(Player victim) {
        if (victim != null) {

            // Oben
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 2, victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY() + 2, victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY() + 2, victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 2, victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 2, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY() + 2, victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY() + 2, victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 2, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY() + 2, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY() + 2, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            // Mitte
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 1, victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY() + 1, victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY() + 1, victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 1, victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 1, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY() + 1, victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY() + 1, victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY() + 1, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY() + 1, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY() + 1, victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            // Unten
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY(), victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY(), victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY(), victim.getLocation().getZ()))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY(), victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY(), victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY(), victim.getLocation().getZ() + 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                            victim.getLocation().getY(), victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() + 1,
                            victim.getLocation().getY(), victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());
            victim.getWorld()
                    .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX() - 1,
                            victim.getLocation().getY(), victim.getLocation().getZ() - 1))
                    .setType(XMaterial.COBWEB.parseMaterial());

        } else {
            TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
        }
    }

    /**
     * Let a player MLG
     * many mlgs like slime, lava, water, cobweb.
     *
     * @param victim that should mlg.
     * @param mlg    the mlg he should do
     * @since 4.4.4
     */
    public static void MLG(Player victim, String mlg) {
        if (mlg.equalsIgnoreCase("slime")) {
            if (victim != null) {
                victim.setAllowFlight(true);
                victim.setVelocity(victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                victim.playSound(victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                victim.playSound(victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                ItemStack slime = new ItemStack(XMaterial.SLIME_BLOCK.parseMaterial());
                ItemMeta slimem = slime.getItemMeta();
                slimem.setDisplayName("§cMLG§8-§aSlime");
                slime.setItemMeta(slimem);
                victim.getInventory().addItem(new ItemStack[]{slime});
                Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
                victim.setAllowFlight(false);
            } else {
                TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
            }
        } else if (mlg.equalsIgnoreCase("lava")) {
            if (victim != null) {
                victim.setAllowFlight(true);
                victim.setVelocity(victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                victim.playSound(victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                victim.playSound(victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                ItemStack bucket = new ItemStack(XMaterial.LAVA_BUCKET.parseMaterial());
                ItemMeta meta = bucket.getItemMeta();
                meta.setDisplayName("§cMLG§8-§bBucket");
                bucket.setItemMeta(meta);
                victim.getInventory().addItem(new ItemStack[]{bucket});
                Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
                victim.setAllowFlight(false);
            } else {
                TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
            }
        } else if (mlg.equalsIgnoreCase("cobweb")) {
            if (victim != null) {
                victim.setAllowFlight(true);
                victim.setVelocity(victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                victim.playSound(victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                victim.playSound(victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                ItemStack web = new ItemStack(XMaterial.COBWEB.parseMaterial());
                ItemMeta webm = web.getItemMeta();
                webm.setDisplayName("§cMLG§8-§bWeb");
                web.setItemMeta(webm);
                victim.getInventory().addItem(new ItemStack[]{web});
                Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
                victim.setAllowFlight(false);
            } else {
                TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
            }
        } else if (mlg.equalsIgnoreCase("water")) {
            if (victim != null) {
                victim.setAllowFlight(true);
                victim.setVelocity(victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                victim.playSound(victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                victim.playSound(victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                ItemStack bucket = new ItemStack(XMaterial.WATER_BUCKET.parseMaterial());
                ItemMeta meta = bucket.getItemMeta();
                meta.setDisplayName("§cMLG§8-§bBucket");
                bucket.setItemMeta(meta);
                victim.getInventory().addItem(new ItemStack[]{bucket});
                Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
                victim.setAllowFlight(false);
            } else {
                TrollV4.getInstance().getLogger().info(LanguageService.getDefault("noonline"));
            }
        } else {
            TrollV4.getInstance().getLogger().info(mlg + " does not exist!");
            TrollV4.getInstance().getLogger().info("Existing: Lava, Water, Cobweb, Slime");
        }
    }

    /**
     * Changes the world color to yellow
     * and the game start lagging.
     *
     * @param victim that should get some LSD.
     * @since 4.4.4
     */
    public static void LSD(Player victim) {
        sendGameStateChange(victim, 7, ServerInfo.belowOrEqual(19) ? 9 : 21, false);
    }

    /**
     * Spawns a Guardin effect on a Player
     * a bit scary if you ask me.
     *
     * @param victim that should see the effect.
     * @since 4.4.4
     */
    public static void GuardinShow(Player victim, boolean packet) {
        if (packet) {
            sendGameStateChange(victim, 10, 0, false);
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "particle mobappearance " + victim.getLocation().getBlockX() + " "
                            + victim.getLocation().getBlockY() + " " + victim.getLocation().getBlockZ() + " 1 1 1 1");

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "particle minecraft:elder_guardian " + victim.getLocation().getBlockX() + " "
                            + victim.getLocation().getBlockY() + " " + victim.getLocation().getBlockZ());
        }
    }

    /**
     * Lets the player see an infinite loading screen.
     *
     * @param victim that should see the loading screen.
     */
    public static void InfiniteLoading(Player victim) {
        sendGameStateChange(victim, 4, 0, false);
    }

    /**
     * Shows the EndCredits
     * simple.
     *
     * @param victim that should see the EndCredits.
     * @since 4.4.4
     */
    public static void EndGame(Player victim) {
        sendGameStateChange(victim, 4, 1, false);
    }

    /**
     * Send Custome GameStateChange Packets
     * used for demoscreen, lsd and others.
     *
     * @param victim that should get freezed.
     * @since 4.4.4
     */
    public static void sendGameStateChange(Player victim, int type, float state, boolean forceProtocolLib) {
        try {
            if (forceProtocolLib || !ServerInfo.supportOldPackets() || (ServerInfo.aboveOrEqual(21) && ServerInfo.getServerSoftwareEnum() == ServerSoftware.PAPER)) {
                final PacketContainer packet = new PacketContainer(PacketType.Play.Server.GAME_STATE_CHANGE);
                packet.getModifier().write(0,
                        PacketType.Play.Server.GAME_STATE_CHANGE.getPacketClass().getDeclaredFields()[type].get(null));
                packet.getFloat().write(0, state);
                ProtocolLibrary.getProtocolManager().sendServerPacket(victim, packet);
            } else {
                Object entityPlayer = victim.getClass().getMethod("getHandle").invoke(victim);
                Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

                Object packet = Packets.getNMSClass("PacketPlayOutGameStateChange").getConstructor(int.class, float.class)
                        .newInstance(type, state);

                playerConnection.getClass().getMethod("sendPacket", Packets.getNMSClass("Packet")).invoke(playerConnection,
                        packet);
            }
        } catch (Exception e) {
            Sentry.captureException(e);
            TrollV4.getInstance().getLogger().warning("Your Server isn't supporting this Packet! (PacketPlayOutGameStateChange)");
            TrollV4.getInstance().getLogger().warning("Return Exception: " + e.getMessage());
        }
    }

    /**
     * Creates an Empty World with Random Creepy NPCs and Noises. If the world exist
     * the player is going to be teleported.
     *
     * @param victim the player that should be scared.
     * @since 4.4.8
     */
    @SuppressWarnings("deprecation")
    public static void SpookyWorld(Player victim) {

        if (!ArrayUtils.spooky.containsKey(victim)) {

            if (!ArrayUtils.spookylast.containsKey(victim)) {
                ArrayUtils.spookylast.put(victim, victim.getLocation());
            }

            TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntityLater(victim, new Runnable() {
                @Override
                public void run() {
                    String spookyWorldName = Config.getConfig().getString("trolls.spookyWorld.name");
                    World world = Bukkit.getWorld(spookyWorldName);
                    if (world != null) {
                        TrollV4.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, Bukkit.getWorld(spookyWorldName).getSpawnLocation());

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            victim.hidePlayer(all);
                        }

                        TrollV4.getInstance().getFoliaLib().getScheduler().runNextTick(consumer -> {
                            world.setTime(15000);
                            world.setThundering(true);
                            world.setThunderDuration((60 * 10) * 20);

                            NPCUserContainer container = new NPCUserContainer(victim);

                            for (int x = 0; x < 30; x++) {
                                Npc npc = NPCUtil.createNPC(getRandomSkinName(), LocationUtil.getLocFromRad(world.getSpawnLocation(), 20, 5, 20, ((new Random().nextInt(1)) == 0), false, ((new Random().nextInt(1)) == 0)),
                                        world.getSpawnLocation(), null, victim);

                                container.addNPC(npc);
                                Runnable runnable;
                                WrappedTask task = TrollV4.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(victim, runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (ArrayUtils.spooky.containsKey(victim)) {
                                            Position position = npc.position();
                                            float angle = victim.getEyeLocation().getDirection()
                                                    .angle(new Vector(position.x(), position.y(), position.z())
                                                            .subtract(victim.getEyeLocation().toVector()));
                                            if (angle > 0.5F) {
                                                npc.lookAt(BukkitPlatformUtil.positionFromBukkitLegacy(victim.getLocation()));
                                            }
                                        } else {
                                            TrollV4.getInstance().getFoliaLib().getScheduler().cancelTask(container.tasks.get(this));
                                        }
                                    }
                                }, 20, 20);

                                container.tasks.put(runnable, task);
                            }

                            ArrayUtils.spooky.put(victim, container);
                            victim.addPotionEffect(XPotion.BLINDNESS.buildPotionEffect(1000000, 3));
                            victim.addPotionEffect(XPotion.SLOWNESS.buildPotionEffect(1000000, 3));
                        });
                    }
                }
            }, 40);
        } else {

            for (Npc npc : ArrayUtils.spooky.get(victim).getNPCs()) {
                npc.unlink();
            }

            victim.removePotionEffect(XPotion.BLINDNESS.getPotionEffectType());
            victim.removePotionEffect(XPotion.SLOWNESS.getPotionEffectType());

            for (Player all : Bukkit.getOnlinePlayers()) {
                victim.showPlayer(all);
            }

            if (ArrayUtils.spookylast.containsKey(victim)) {
                TrollV4.getInstance().getFoliaLib().getScheduler().teleportAsync(victim, ArrayUtils.spookylast.get(victim));
                ArrayUtils.spookylast.remove(victim);
            }

            ArrayUtils.spooky.remove(victim);

        }
    }

    /**
     * Get a Random Creepy Skin ID
     * by random users.
     *
     * @return int it returns a SkinID from MineSkin.
     * @since 4.4.4
     * @deprecated Use signature and texture instead of this.
     */
    @Deprecated
    public static String getRandomSkinName() {

        String[] ids = new String[]{"notenough", "FKJJJJJJJJJJJJJJ", "My_Te", PlayerInfo.getName("1c32b55bd4584347a5798754f4510081")};

        return ids[new Random().nextInt(ids.length)];
    }

}
