package de.presti.trollv4.api;

import java.lang.reflect.InvocationTargetException;
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
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.config.Language;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.ArrayUtils;
import de.presti.trollv4.utils.crossversion.HS;
import de.presti.trollv4.utils.crossversion.Titles;

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

	public static void startControl(Player user, Player Victim) {
		Main.startControlling(user, Victim);
	}

	public static void stopControl(Player user, Player Victim) {
		Main.stopControlling(user, Victim);
	}

	public static void DemoScreen(Player Victim) {
		sendGameStateChange(Victim, 5, 0);
	}

	public static void Herobrine(Player victim) {
		if (ArrayUtils.herobrine.contains(victim)) {
			ArrayUtils.herobrine.remove(victim);
		} else {
			ArrayUtils.herobrine.add(victim);
		}
	}

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

	public static void Explode(Player victim) {
		if (victim != null) {
			victim.playSound(victim.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 100.0F, 200.0F);
			victim.getWorld().createExplosion(victim.getLocation(), 3.0F);
			victim.setHealth(0.0D);
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void FakeHack(Player Victim) {
		if (Victim != null) {
			if (ArrayUtils.fc.contains(Victim)) {
				Victim.setWalkSpeed(0.2F);
				Victim.setAllowFlight(false);
				ArrayUtils.fc.remove(Victim);
			} else {
				ArrayUtils.fc.add(Victim);

			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Strike(Player Victim) {
		if (Victim != null) {
			Victim.getLocation().getWorld().strikeLightning(Victim.getLocation());
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Rocket(Player Victim) {
		if (Victim != null) {
			Victim.setAllowFlight(true);
			Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
			Victim.playSound(Victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
			Victim.playSound(Victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
			Victim.setAllowFlight(false);
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Spam(Player Victim) {
		if (Victim != null) {
			for (int i = 0; i < 500; i++) {
				Victim.sendMessage("§cDont Cheat!");
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void FakeOP(Player Victim) {
		if (Victim != null) {
			Victim.sendMessage(Language.getMessage("gui.fakeop.opm", Victim));
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Crash(Player Victim) {
		if (Victim != null) {
			Victim.kickPlayer(Language.getMessage("gui.crash.message"));
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Freeze(Player Victim) {
		if (Victim != null) {
			if (ArrayUtils.freeze.contains(Victim)) {
				Victim.setWalkSpeed(0.2F);
				Victim.setFlySpeed(0.1F);
				ArrayUtils.freeze.remove(Victim);
			} else {
				Victim.setWalkSpeed(0F);
				Victim.setFlySpeed(0F);
				ArrayUtils.freeze.add(Victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void AntiCheat(Player Victim) {
		if (Victim != null) {
			Victim.sendMessage(Language.getMessage("gui.anticheat.detected"));
			Victim.teleport(new Location(Victim.getWorld(), Victim.getLocation().getX(),
					Victim.getLocation().getY() + 2, Victim.getLocation().getZ()));
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Lagging(Player Victim) {
		if (Victim != null) {
			if (!ArrayUtils.lagging.contains(Victim)) {
				ArrayUtils.lagging.add(Victim);
			} else {
				ArrayUtils.lagging.remove(Victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Arrest(Player Victim) {
		if (Victim != null) {
			Victim.teleport(Victim.getLocation());
			// Y
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() - 1, Victim.getLocation().getZ()))
					.setType(XMaterial.BEDROCK.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ()))
					.setType(XMaterial.BEDROCK.parseMaterial());
			// X
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ()))
					.setType(XMaterial.BEDROCK.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ()))
					.setType(XMaterial.BEDROCK.parseMaterial());
			// Z
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY(), Victim.getLocation().getZ() + 1))
					.setType(XMaterial.BEDROCK.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY(), Victim.getLocation().getZ() - 1))
					.setType(XMaterial.BEDROCK.parseMaterial());

		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void Rotate(Player Victim) {
		if (Victim != null) {
			if (ArrayUtils.rotateplayer.contains(Victim)) {
				ArrayUtils.rotateplayer.remove(Victim);
			} else {
				new Haupt().rop(Victim);
				ArrayUtils.rotateplayer.add(Victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void RandomTP(Player Victim) {
		if (Victim != null) {
			if (ArrayUtils.randomtp.contains(Victim)) {
				ArrayUtils.randomtp.remove(Victim);
			} else {
				new Haupt().rtp(Victim);
				ArrayUtils.randomtp.add(Victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void TnTTrace(Player Victim) {
		if (Victim != null) {
			if (ArrayUtils.tntp.contains(Victim)) {
				ArrayUtils.tntp.remove(Victim);
			} else {
				new Haupt().spawntntatplayer(Victim);
				ArrayUtils.tntp.add(Victim);
			}
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void WTF(Player Victim) {
		if (Victim != null) {

			Victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 5, true));
			Victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
			Victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 10, true));

			ArrayUtils.wtf.put(Victim, new BukkitRunnable() {
				int countdown = 4;

				@Override
				public void run() {

					if (countdown == 0) {
						Victim.teleport(Victim.getLocation());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.GLASS.parseMaterial());
						Victim.chat("Help me Pls im stucked ;-; I dont know where im pls help!!!");
						Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(Victim).getTaskId());
						return;
					}

					if (countdown == 4) {
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() - 1, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.teleport(Victim.getLocation());
					}

					if (countdown == 3) {
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY(), Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY(), Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY(), Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY(), Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY(), Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY(), Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY(), Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY(), Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ()))
								.setType(XMaterial.GLASS.parseMaterial());
						Victim.teleport(Victim.getLocation());
					}

					if (countdown == 2) {
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.GLASS.parseMaterial());
						Victim.teleport(Victim.getLocation());
					}

					if (countdown == 1) {
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ()))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1))
								.setType(XMaterial.BEDROCK.parseMaterial());
						Victim.getWorld()
								.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
										Victim.getLocation().getY() + 1, Victim.getLocation().getZ()))
								.setType(XMaterial.GLASS.parseMaterial());
						Victim.teleport(Victim.getLocation());
					}

					if (countdown <= 0) {
						Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(Victim).getTaskId());
					}
					countdown--;
				}
			});
			ArrayUtils.wtf.get(Victim).runTaskTimer(Main.instance, 0L, 20L);
		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void WebTrap(Player Victim) {
		if (Victim != null) {

			// Oben
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			// Mitte
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			// Unten
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY(), Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ()))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY(), Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ() + 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(),
							Victim.getLocation().getY(), Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());
			Victim.getWorld()
					.getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1,
							Victim.getLocation().getY(), Victim.getLocation().getZ() - 1))
					.setType(XMaterial.COBWEB.parseMaterial());

		} else {
			System.out.println(Language.getMessage("noonline"));
		}
	}

	public static void MLG(Player Victim, String mlg) {
		if (mlg.equalsIgnoreCase("slime")) {
			if (Victim != null) {
				Victim.setAllowFlight(true);
				Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
				ItemStack slime = new ItemStack(XMaterial.SLIME_BLOCK.parseMaterial());
				ItemMeta slimem = slime.getItemMeta();
				slimem.setDisplayName("§cMLG§8-§aSlime");
				slime.setItemMeta(slimem);
				Victim.getInventory().addItem(new ItemStack[] { slime });
				Titles.send(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				Victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
			}
		} else if (mlg.equalsIgnoreCase("lava")) {
			if (Victim != null) {
				Victim.setAllowFlight(true);
				Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
				ItemStack bucket = new ItemStack(XMaterial.LAVA_BUCKET.parseMaterial());
				ItemMeta meta = bucket.getItemMeta();
				meta.setDisplayName("§cMLG§8-§bBucket");
				bucket.setItemMeta(meta);
				Victim.getInventory().addItem(new ItemStack[] { bucket });
				Titles.send(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				Victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
			}
		} else if (mlg.equalsIgnoreCase("cobweb")) {
			if (Victim != null) {
				Victim.setAllowFlight(true);
				Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
				ItemStack web = new ItemStack(XMaterial.COBWEB.parseMaterial());
				ItemMeta webm = web.getItemMeta();
				webm.setDisplayName("§cMLG§8-§bWeb");
				web.setItemMeta(webm);
				Victim.getInventory().addItem(new ItemStack[] { web });
				Titles.send(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				Victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
			}
		} else if (mlg.equalsIgnoreCase("water")) {
			if (Victim != null) {
				Victim.setAllowFlight(true);
				Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
				Victim.playSound(Victim.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
				ItemStack bucket = new ItemStack(XMaterial.WATER_BUCKET.parseMaterial());
				ItemMeta meta = bucket.getItemMeta();
				meta.setDisplayName("§cMLG§8-§bBucket");
				bucket.setItemMeta(meta);
				Victim.getInventory().addItem(new ItemStack[] { bucket });
				Titles.send(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
				Victim.setAllowFlight(false);
			} else {
				System.out.println(Language.getMessage("noonline"));
			}
		} else {
			System.out.println(mlg + " does not exist!");
			System.out.println("Existing: Lava, Water, Cobweb, Slime");
		}
	}

	public static void LSD(Player Victim) {
		Packets.sendPacket(Victim, 7, 15);
	}

	public static void GuardinShow(Player Victim, boolean packet) {
		if (packet) {
			sendGameStateChange(Victim, 10, 0);
		} else {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
					"particle mobappearance " + Victim.getLocation().getBlockX() + " "
							+ Victim.getLocation().getBlockY() + " " + Victim.getLocation().getBlockZ() + " 1 1 1 1");
		}
	}

	public static void EndGame(Player Victim) {
		sendGameStateChange(Victim, 4, 1);
	}

	public static void sendGameStateChange(Player Victim, int type, float state) {
		try {
			Object entityPlayer = Victim.getClass().getMethod("getHandle").invoke(Victim);
			Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
			Object packet = Packets.getNMSClass("PacketPlayOutGameStateChange").getConstructor(int.class, float.class)
					.newInstance(type, state);

			playerConnection.getClass().getMethod("sendPacket", Packets.getNMSClass("Packet")).invoke(playerConnection,
					packet);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException | NoSuchFieldException | InstantiationException e) {
			System.out.println("Your Server Version isnt Supporting this Packet! (PacketPlayOutGameStateChange)");
		}
	}

}
