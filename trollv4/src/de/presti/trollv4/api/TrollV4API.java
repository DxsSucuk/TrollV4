package de.presti.trollv4.api;

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
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.crossversion.HS;
import de.presti.trollv4.utils.crossversion.Titles;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.player.LocationUtil;
import de.presti.trollv4.utils.server.NPCUserContainer;
import de.presti.trollv4.utils.server.NPCUtil;
import de.presti.trollv4.utils.server.ServerInfo;
import de.presti.trollv4.utils.server.WorldCreator;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.skin.MineSkinFetcher;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 20.12.2019 / 21:06:16											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class TrollV4API {

	private static int taskID;

	/**
	 * Lets an player control an other player. This will be stopped after the player
	 * leaves or the control stops.
	 *
	 * @param user   the player thats should control.
	 * @param victim the player that should be controlled.
	 * @since 4.4.4
	 */
	public static void startControl(Player user, Player victim) {
		Main.startControlling(user, victim);
	}

	/**
	 * Stops the control of an user. This will stop the control of an player.
	 *
	 * @param user   the player thats should release the victim.
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
		sendGameStateChange(victim, 5, 0);
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
							.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
									Main.getPlugin().getRandom(5, 10), Main.getPlugin().getRandom(-10, 10)),
									Arrow.class);
					Location aloc = arrow.getLocation();
					Vector angle = new Vector(loc.getX() - aloc.getX(), loc.getY() - aloc.getBlockY(),
							loc.getZ() - aloc.getBlockZ());
					arrow.setVelocity(angle.normalize().multiply(2.0D));

					Arrow arrow2 = (Arrow) victim.getWorld()
							.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
									Main.getPlugin().getRandom(5, 10), Main.getPlugin().getRandom(-10, 10)),
									Arrow.class);
					Location aloc2 = arrow2.getLocation();
					Vector angle2 = new Vector(loc.getX() - aloc2.getX(), loc.getY() - aloc2.getBlockY(),
							loc.getZ() - aloc2.getBlockZ());
					arrow2.setVelocity(angle2.normalize().multiply(2.0D));

					Arrow arrow3 = (Arrow) victim.getWorld()
							.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
									Main.getPlugin().getRandom(5, 10), Main.getPlugin().getRandom(-10, 10)),
									Arrow.class);
					Location aloc3 = arrow3.getLocation();
					Vector angle3 = new Vector(loc.getX() - aloc3.getX(), loc.getY() - aloc3.getBlockY(),
							loc.getZ() - aloc3.getBlockZ());
					arrow3.setVelocity(angle3.normalize().multiply(2.0D));

					Arrow arrow4 = (Arrow) victim.getWorld()
							.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
									Main.getPlugin().getRandom(5, 10), Main.getPlugin().getRandom(-10, 10)),
									Arrow.class);
					Location aloc4 = arrow4.getLocation();
					Vector angle4 = new Vector(loc.getX() - aloc4.getX(), loc.getY() - aloc4.getBlockY(),
							loc.getZ() - aloc4.getBlockZ());
					arrow4.setVelocity(angle4.normalize().multiply(2.0D));

					Arrow arrow5 = (Arrow) victim.getWorld()
							.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
									Main.getPlugin().getRandom(5, 10), Main.getPlugin().getRandom(-10, 10)),
									Arrow.class);
					Location aloc5 = arrow5.getLocation();
					Vector angle5 = new Vector(loc.getX() - aloc5.getX(), loc.getY() - aloc5.getBlockY(),
							loc.getZ() - aloc5.getBlockZ());
					arrow5.setVelocity(angle5.normalize().multiply(2.0D));

				}
			}).runTaskTimer(Main.getPlugin(), 0, 10);
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
			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
				int countdown = 15;

				@Override
				public void run() {
					if (countdown <= 0) {
						HS.Hack(victim);
						Titles.send(victim, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
						Bukkit.getScheduler().cancelTask(taskID);

						return;
					}

					HS.Hack2(victim);
					Titles.send(victim, 1, 20, 1, "§cHacking in " + countdown, "§4" + Main.getRandomID());
					victim.damage(0.1D);
					countdown--;
				}
			}, 0, 20);
		} else {
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
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
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void AntiCheat(Player victim) {
		if (victim != null) {
			victim.sendMessage(Language.getMessage("gui.anticheat.detected"));
			victim.teleport(new Location(victim.getWorld(), victim.getLocation().getX(),
					victim.getLocation().getY() + 2, victim.getLocation().getZ()));
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Lagging(Player victim) {
		if (victim != null) {
			if (!ArrayUtils.lagging.contains(victim)) {
				ArrayUtils.lagging.add(victim);
			} else {
				ArrayUtils.lagging.remove(victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

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
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Rotate(Player victim) {
		if (victim != null) {
			if (ArrayUtils.rotateplayer.contains(victim)) {
				ArrayUtils.rotateplayer.remove(victim);
			} else {
				new Haupt().rop(victim);
				ArrayUtils.rotateplayer.add(victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void RandomTP(Player victim) {
		if (victim != null) {
			if (ArrayUtils.randomtp.contains(victim)) {
				ArrayUtils.randomtp.remove(victim);
			} else {
				new Haupt().rtp(victim);
				ArrayUtils.randomtp.add(victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void TnTTrace(Player victim) {
		if (victim != null) {
			if (ArrayUtils.tntp.contains(victim)) {
				ArrayUtils.tntp.remove(victim);
			} else {
				new Haupt().spawntntatplayer(victim);
				ArrayUtils.tntp.add(victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

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
			ArrayUtils.wtf.get(victim).runTaskTimer(Main.instance, 0L, 20L);
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

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
			System.out.println(Language.getMessage("noonline"));
		}
	}

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
				victim.getInventory().addItem(new ItemStack[] { slime });
				Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
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
				victim.getInventory().addItem(new ItemStack[] { bucket });
				Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
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
				victim.getInventory().addItem(new ItemStack[] { web });
				Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
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
				victim.getInventory().addItem(new ItemStack[] { bucket });
				Titles.send(victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
			}
		} else {
			System.out.println(mlg + " does not exist!");
			System.out.println("Existing: Lava, Water, Cobweb, Slime");
		}
	}

	public static void LSD(Player victim) {
		Packets.sendPacket(victim, 7, 15);
	}

	public static void GuardinShow(Player victim, boolean packet) {
		if (packet) {
			sendGameStateChange(victim, 10, 0);
		} else {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"particle mobappearance " + victim.getLocation().getBlockX() + " "
							+ victim.getLocation().getBlockY() + " " + victim.getLocation().getBlockZ() + " 1 1 1 1");

			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"particle minecraft:elder_guardian " + victim.getLocation().getBlockX() + " "
							+ victim.getLocation().getBlockY() + " " + victim.getLocation().getBlockZ());
		}
	}

	public static void EndGame(Player victim) {
		sendGameStateChange(victim, 4, 1);
	}

	public static void sendGameStateChange(Player victim, int type, float state) {
		try {

			Object entityPlayer = victim.getClass().getMethod("getHandle").invoke(victim);
			Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
			Object packet = null;

			if (ServerInfo.supportOldPackets()) {
				packet = Packets.getNMSClass("PacketPlayOutGameStateChange").getConstructor(int.class, float.class)
						.newInstance(type, state);
			} else {

				packet = Packets.getNMSClass("PacketPlayOutGameStateChange")
						.getConstructor(Packets.getNMSClass("PacketPlayOutGameStateChange$a"), float.class)
						.newInstance(Packets.getNMSClass("PacketPlayOutGameStateChange$a").getConstructor(int.class)
								.newInstance(type), state);
			}

			playerConnection.getClass().getMethod("sendPacket", Packets.getNMSClass("Packet")).invoke(playerConnection,
					packet);
		} catch (Exception e) {
			System.out.println("Your Server Version isnt Supporting this Packet! (PacketPlayOutGameStateChange)");
			System.out.println("Return Exception: " + e.getMessage());
			e.printStackTrace();
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
					if (Bukkit.getWorld("SpookyWorld") != null) {
						victim.teleport(Bukkit.getWorld("SpookyWorld").getSpawnLocation());

						for (Player all : Bukkit.getOnlinePlayers()) {
							victim.hidePlayer(all);
						}

						NPCUserContainer container = new NPCUserContainer(victim);

						for (int x = 0; x < 30; x++) {
							MineSkinFetcher.fetchSkinFromIdAsync(getRandomSkinID(), skin -> {

								NPC npc = NPCUtil.npcLib.createNPC();
								npc.setLocation(LocationUtil.getLocFromRad(victim.getLocation(), 20, 5, 20));
								npc.lookAt(victim.getLocation());
								npc.setSkin(skin);

								npc.create();

								Bukkit.getScheduler().runTask(Main.instance, () -> npc.show(victim));

								container.addNPC(npc);

								// Moving NPCs? YEEEEEEEEEEEEEEEEEEEEEEEEE BOI

								new BukkitRunnable() {

									@Override
									public void run() {
										if (ArrayUtils.spooky.containsKey(victim)) {
											float angle = victim.getEyeLocation().getDirection().angle(npc.getLocation()
													.toVector().subtract(victim.getEyeLocation().toVector()));
											if (angle > 0.5F) {
												npc.lookAt(victim.getLocation());
											}
										} else {
											cancel();
										}
									}
								}.runTaskTimer(Main.instance, 20, 20);

							});
						}

						ArrayUtils.spooky.put(victim, container);
						victim.addPotionEffect(XPotion.BLINDNESS.parsePotion(1000000, 5));
						victim.addPotionEffect(XPotion.SLOW.parsePotion(1000000, 10));
					} else {
						WorldCreator.createWorld("SpookyWorld");
						SpookyWorld(victim);
					}
				}
			}.runTaskLater(Main.instance, 40L);
		} else {

			for (NPC npc : ArrayUtils.spooky.get(victim).getNPCs()) {
				npc.destroy();
			}

			victim.removePotionEffect(XPotion.BLINDNESS.parsePotionEffectType());
			victim.removePotionEffect(XPotion.SLOW.parsePotionEffectType());

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

	public static int getRandomSkinID() {
		return 1831521135;
	}

}
