package de.presti.trollv4.api;

import java.util.Random;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.github.juliarn.npclib.api.Npc;
import com.github.juliarn.npclib.api.Position;
import com.github.juliarn.npclib.bukkit.util.BukkitPlatformUtil;
import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.logging.Logger;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.crossversion.HS;
import de.presti.trollv4.utils.crossversion.Titles;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.player.LocationUtil;
import de.presti.trollv4.utils.plugin.RandomUtility;
import de.presti.trollv4.utils.server.NPCUserContainer;
import de.presti.trollv4.utils.server.NPCUtil;
import de.presti.trollv4.utils.server.ServerInfo;
import io.sentry.Sentry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XPotion;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.cmd.Haupt;

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
        Main.startControlling(user, victim);
    }

    /**
     * Stops the control of a user. This will stop the control of a player.
     *
     * @param user   the player that should release the victim.
     * @param victim the player that should be released.
     * @since 4.4.4
     */
    public static void stopControl(Player user, Player victim) {
        Main.stopControlling(user, victim);
    }

    /**
     * Shows an player the Minecraft Demoscreen. Currently this only works with 1.8.
     *
     * @param victim the player that should be controlled.
     * @since 4.4.4
     */
    public static void DemoScreen(Player victim) {
        sendGameStateChange(victim, 5, 0, false);
    }

    /**
     * Lets an player be Herobrine. This spwanes Silvefishes and Thunder bolts
     * around him.
     *
     * @param victim the player thats should be herobrine.
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
     * This spawnes Arrows around an player. Just Vibing in here.
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
            ArrayUtils.arrowspam.put(victim, new BukkitRunnable() {

                @Override
                public void run() {

                    Location loc = victim.getLocation().clone();

                    Arrow arrow = (Arrow) victim.getWorld()
                            .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                            Main.getInstance().getRandom(5, 10), Main.getInstance().getRandom(-10, 10)),
                                    Arrow.class);
                    Location aloc = arrow.getLocation();
                    Vector angle = new Vector(loc.getX() - aloc.getX(), loc.getY() - aloc.getBlockY(),
                            loc.getZ() - aloc.getBlockZ());
                    arrow.setVelocity(angle.normalize().multiply(2.0D));

                    Arrow arrow2 = (Arrow) victim.getWorld()
                            .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                            Main.getInstance().getRandom(5, 10), Main.getInstance().getRandom(-10, 10)),
                                    Arrow.class);
                    Location aloc2 = arrow2.getLocation();
                    Vector angle2 = new Vector(loc.getX() - aloc2.getX(), loc.getY() - aloc2.getBlockY(),
                            loc.getZ() - aloc2.getBlockZ());
                    arrow2.setVelocity(angle2.normalize().multiply(2.0D));

                    Arrow arrow3 = (Arrow) victim.getWorld()
                            .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                            Main.getInstance().getRandom(5, 10), Main.getInstance().getRandom(-10, 10)),
                                    Arrow.class);
                    Location aloc3 = arrow3.getLocation();
                    Vector angle3 = new Vector(loc.getX() - aloc3.getX(), loc.getY() - aloc3.getBlockY(),
                            loc.getZ() - aloc3.getBlockZ());
                    arrow3.setVelocity(angle3.normalize().multiply(2.0D));

                    Arrow arrow4 = (Arrow) victim.getWorld()
                            .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                            Main.getInstance().getRandom(5, 10), Main.getInstance().getRandom(-10, 10)),
                                    Arrow.class);
                    Location aloc4 = arrow4.getLocation();
                    Vector angle4 = new Vector(loc.getX() - aloc4.getX(), loc.getY() - aloc4.getBlockY(),
                            loc.getZ() - aloc4.getBlockZ());
                    arrow4.setVelocity(angle4.normalize().multiply(2.0D));

                    Arrow arrow5 = (Arrow) victim.getWorld()
                            .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                            Main.getInstance().getRandom(5, 10), Main.getInstance().getRandom(-10, 10)),
                                    Arrow.class);
                    Location aloc5 = arrow5.getLocation();
                    Vector angle5 = new Vector(loc.getX() - aloc5.getX(), loc.getY() - aloc5.getBlockY(),
                            loc.getZ() - aloc5.getBlockZ());
                    arrow5.setVelocity(angle5.normalize().multiply(2.0D));

                }
            }).runTaskTimer(Main.getInstance(), 0, 10);
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
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            victim.sendMessage(Language.getMessage("gui.fakeop.opm", victim));
        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            victim.kickPlayer(Language.getMessage("gui.crash.message"));
        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
        }
    }

    /**
     * Teleport the Player to the same position he is and that simulates a SetBack
     * and it sends a "AntiCheat Detection" Message.
     *
     * @param victim that should get fake detected.
     * @since 4.4.4
     */
    public static void AntiCheat(Player victim) {
        if (victim != null) {
            victim.sendMessage(Language.getMessage("gui.anticheat.detected"));
            victim.teleport(new Location(victim.getWorld(), victim.getLocation().getX(),
                    victim.getLocation().getY() + 2, victim.getLocation().getZ()));
        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
        }
    }

    /**
     * Spawn a Bedrock Jail around the Player
     * and dont let him escape.
     *
     * @param victim that should be arrested.
     * @since 4.4.4
     */
    public static void Arrest(Player victim) {
        if (victim != null) {
            victim.teleport(victim.getLocation());
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

        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
        }
    }

    /**
     * Permantly Rotate a Player
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
                new Haupt().rop(victim);
                ArrayUtils.rotateplayer.add(victim);
            }
        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
                new Haupt().rtp(victim);
                ArrayUtils.randomtp.add(victim);
            }
        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
                new Haupt().spawnTnTAtPlayer(victim);
                ArrayUtils.tntp.add(victim);
            }
        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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

            ArrayUtils.wtf.put(victim, new BukkitRunnable() {
                int countdown = 4;

                @Override
                public void run() {

                    if (countdown == 0) {
                        victim.teleport(victim.getLocation());
                        victim.getWorld()
                                .getBlockAt(new Location(victim.getWorld(), victim.getLocation().getX(),
                                        victim.getLocation().getY() + 1, victim.getLocation().getZ() + 1))
                                .setType(XMaterial.GLASS.parseMaterial());
                        victim.chat("Help me Pls im stucked ;-; I dont know where im pls help!!!");
                        Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(victim).getTaskId());
                        return;
                    }

                    if (countdown == 4) {
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
                        victim.teleport(victim.getLocation());
                    }

                    if (countdown == 3) {
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
                        victim.teleport(victim.getLocation());
                    }

                    if (countdown == 2) {
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
                        victim.teleport(victim.getLocation());
                    }

                    if (countdown == 1) {
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
                        victim.teleport(victim.getLocation());
                    }

                    if (countdown <= 0) {
                        Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(victim).getTaskId());
                    }
                    countdown--;
                }
            });
            ArrayUtils.wtf.get(victim).runTaskTimer(Main.getInstance(), 0L, 20L);
        } else {
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
            Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
                Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
                Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
                Main.getInstance().getLogger().info(Language.getMessage("noonline"));
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
                Main.getInstance().getLogger().info(Language.getMessage("noonline"));
            }
        } else {
            Main.getInstance().getLogger().info(mlg + " does not exist!");
            Main.getInstance().getLogger().info("Existing: Lava, Water, Cobweb, Slime");
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
            if (!ServerInfo.supportOldPackets() && !forceProtocolLib) {
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
            Main.getInstance().getLogger().warning("Your Server isn't supporting this Packet! (PacketPlayOutGameStateChange)");
            Main.getInstance().getLogger().warning("Return Exception: " + e.getMessage());
        }
    }

    /**
     * Creates an Empty World with Random Creppy NPCs and Noises. If the world exist
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

            new BukkitRunnable() {

                @Override
                public void run() {
                    String spookyWorldName = Config.getconfig().getString("trolls.spookyWorld.name");
                    if (Bukkit.getWorld(spookyWorldName) != null) {
                        victim.teleport(Bukkit.getWorld(spookyWorldName).getSpawnLocation());

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            victim.hidePlayer(all);
                        }

                        Bukkit.getWorld(spookyWorldName).setTime(15000);
                        Bukkit.getWorld(spookyWorldName).setThundering(true);
                        Bukkit.getWorld(spookyWorldName).setThunderDuration((60 * 10) * 20);

                        NPCUserContainer container = new NPCUserContainer(victim);

                        for (int x = 0; x < 30; x++) {
                            Npc npc = NPCUtil.createNPC(getRandomSkinName(), LocationUtil.getLocFromRad(victim.getLocation(), 20, 5, 20, ((new Random().nextInt(1)) == 0), false, ((new Random().nextInt(1)) == 0)),
                                    victim.getLocation(), null, victim);

                            container.addNPC(npc);

                            new BukkitRunnable() {

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
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(Main.getInstance(), 20, 20);

                        }

                        ArrayUtils.spooky.put(victim, container);
                        victim.addPotionEffect(XPotion.BLINDNESS.buildPotionEffect(1000000, 3));
                        victim.addPotionEffect(XPotion.SLOW.buildPotionEffect(1000000, 3));
                    } else {
                        Logger.info(Language.getMessage("gui.spooky.world"));
                    }
                }
            }.runTaskLater(Main.getInstance(), 40L);
        } else {

            for (Npc npc : ArrayUtils.spooky.get(victim).getNPCs()) {
                npc.unlink();
            }

            victim.removePotionEffect(XPotion.BLINDNESS.getPotionEffectType());
            victim.removePotionEffect(XPotion.SLOW.getPotionEffectType());

            for (Player all : Bukkit.getOnlinePlayers()) {
                victim.showPlayer(all);
            }

            if (ArrayUtils.spookylast.containsKey(victim)) {
                victim.teleport(ArrayUtils.spookylast.get(victim));
                ArrayUtils.spookylast.remove(victim);
            }

            ArrayUtils.spooky.remove(victim);

        }
    }

    /**
     * Get a Random Creppy Skin ID
     * by random users.
     *
     * @return int it returns a SkinID from MineSkin.
     * @since 4.4.4
     */
    public static String getRandomSkinName() {

        String[] ids = new String[]{"notenough", "FKJJJJJJJJJJJJJJ", "My_Te", PlayerInfo.getName("1c32b55bd4584347a5798754f4510081")};

        return ids[new Random().nextInt(ids.length)];
    }

}
