package de.presti.trollv4.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.ArrayUtils;
import de.presti.trollv4.utils.Config;
import de.presti.trollv4.utils.Controls;
import de.presti.trollv4.utils.Hsv1_8_R3;
import de.presti.trollv4.utils.Hsv1_9_R2;
import de.presti.trollv4.utils.Titles;

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
		Controls.startControlling(user, Victim);
	}
	
	public static void stopControl(Player user, Player Victim) {
		Controls.stopControlling(user, Victim);
	}
	
	public static void DemoScreen(Player Victim) {
		de.presti.trollv4.utils.DemoScreen.DemoScreen(Victim);
	}
	
	public static void Hack(Player victim) {
		  if(victim != null) {
			  taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
       				int countdown = 15;
       				@Override
       				public void run() {
       					if(countdown <= 0) {
       						if(Main.version.equals("v1_8_R3")) {
       							Hsv1_8_R3.Hack(victim);
       						} else if(Main.version.equals("v1_9_R2")) {
       							Hsv1_9_R2.Hack(victim);
       						} else {
								if(Config.cfg.getBoolean("Unsupport")) {
           							Hsv1_9_R2.Hack(victim);
								} else {
                  			   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
             						System.out.println(Data.prefix + "Bitte benutze v1_8_R3 oder v1_9_R2! ODER Aktiviere Unsupport!");
             					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
             						System.out.println(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
             					} else {
             						System.out.println(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
             					}  
							}
                  		   }
       			            Titles.send(victim, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
       			            Bukkit.getScheduler().cancelTask(taskID);
       					    
       					    return;
       					}
   							if(Main.version.equals("v1_8_R3")) {
       							Hsv1_8_R3.Hack2(victim);
       						} else if(Main.version.equals("v1_9_R2")) {
       							Hsv1_9_R2.Hack2(victim);
       						} else {
								if(Config.cfg.getBoolean("Unsupport")) {
           							Hsv1_9_R2.Hack2(victim);
								} else {
                  			   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
             						System.out.println(Data.prefix + "Bitte benutze v1_8_R3 oder v1_9_R2! ODER Aktiviere Unsupport!");
             					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
             						System.out.println(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
             					} else {
             						System.out.println(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
             					}  
							}  
                  		   }
       		            Titles.sendTitle(victim, 1, 20, 1, "§cHacking in " + countdown, "§4" + Main.getRandomID());
       		            victim.damage(0.1D);
       				    countdown--;
       				}
       			}, 0, 20);
		  }else {
				if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
				} else {
				System.out.println(Data.notonus);
				}
			}
	}
	
	public static void Explode(Player victim) {
		  if(victim != null) {
			if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
				victim.playSound(victim.getLocation(), Sound.FIREWORK_LAUNCH, 100.0F, 200.0F);
			  }
            victim.getWorld().createExplosion(victim.getLocation(), 3.0F);
            victim.setHealth(0.0D);
		  }else {
				if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
				} else {
				System.out.println(Data.notonus);
				}
			}
	}
	
	public static void FakeHack(Player Victim) {
		  if(Victim != null) {
			  if (ArrayUtils.fc.contains(Victim))
              {
			  Victim.setWalkSpeed(0.2F);
			  Victim.setAllowFlight(false);
               ArrayUtils.fc.remove(Victim);
              } else {
	            ArrayUtils.fc.add(Victim);
                
              }
		  }else {
				if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
				} else {
				System.out.println(Data.notonus);
				}
			}
	}
	
	public static void Strike(Player Victim) {
		  if(Victim != null) {
			  Victim.getLocation().getWorld().strikeLightning(Victim.getLocation());
		  }else {
				if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
				} else {
				System.out.println(Data.notonus);
				}
			}
	}
	
	public static void Rocket(Player Victim) {
		  if(Victim != null) {
			  Victim.setAllowFlight(true);
			  Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
      		if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
      		  Victim.playSound(Victim.getLocation(), Sound.BURP, 100.0F, 25.0F);
      		  Victim.playSound(Victim.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
      		}
      		  Victim.setAllowFlight(false);
		  }else {
				if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
				} else {
				System.out.println(Data.notonus);
				}
			}
	}
	
	public static void Spam(Player Victim) {
		  if(Victim != null) {
				for (int i = 0; i < 500; i++) {
					Victim.sendMessage("§cDont Cheat!");
				}
		  }else {
				if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
				} else {
				System.out.println(Data.notonus);
				}
			}
	}
	
	public static void FakeOP(Player Victim) {
		if(Victim != null) {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				Victim.sendMessage("§7§o[Server: " + Victim.getName() + " wurde zum Operator ernannt]");
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				Victim.sendMessage("§7§o[Server: Opped " + Victim.getName() + "§7§o]");
				} else {
				Victim.sendMessage("§7§o[Server: Opped " + Victim.getName() + "§7§o]");	
				}
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void Crash(Player Victim) {
		if(Victim != null) {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
			Victim.kickPlayer(Config.getconfig2().getString("Message.Player.Crash"));
			} else {
			Victim.kickPlayer("java.net.ConnectException: Connection timed out: No further information");
			}
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void Freeze(Player Victim) {
		if(Victim != null) {
			if (ArrayUtils.freeze.contains(Victim))
              {
				Victim.setWalkSpeed(0.2F);
	            Victim.setFlySpeed(0.1F);
	            ArrayUtils.freeze.remove(Victim);
              }
              else
              {
                Victim.setWalkSpeed(0F);
                Victim.setFlySpeed(0F);
                ArrayUtils.freeze.add(Victim);
              }
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void AntiCheat(Player Victim) {
		if(Victim != null) {
			Victim.sendMessage("§7[§cPrestige§7-§cAntiCheat§7] §2Stop §cHacking §7[§1VL§7/§6130.234§7]");
			Victim.teleport(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ()));
	} else {
		if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			System.out.println(Data.noton);
		} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			System.out.println(Data.notonus);
		} else {
			System.out.println(Data.notonus);
		}
	}
	}
	
	public static void Lagging(Player Victim) {
		if(Victim != null) {
			if(!ArrayUtils.lagging.contains(Victim)) {
				ArrayUtils.lagging.add(Victim);
			  } else {
				ArrayUtils.lagging.remove(Victim);
			  }
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void Arrest(Player Victim) {
		if(Victim != null) {
			Victim.teleport(Victim.getLocation());
			//Y
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() - 1, Victim.getLocation().getZ())).setType(Material.BEDROCK);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ())).setType(Material.BEDROCK);
			//X
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY(), Victim.getLocation().getZ())).setType(Material.BEDROCK);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY(), Victim.getLocation().getZ())).setType(Material.BEDROCK);
			//Z
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY(), Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY(), Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
			
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void Rotate(Player Victim) {
		if(Victim != null) {
			if(ArrayUtils.rotateplayer.contains(Victim)) {
				ArrayUtils.rotateplayer.remove(Victim);
			} else {
				new Haupt().rop(Victim);
				ArrayUtils.rotateplayer.add(Victim);
			}
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void RandomTP(Player Victim) {
		if(Victim != null) {
			if(ArrayUtils.randomtp.contains(Victim)) {
				ArrayUtils.randomtp.remove(Victim);
			} else {
				new Haupt().rtp(Victim);
				ArrayUtils.randomtp.add(Victim);
			}
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void TnTTrace(Player Victim) {
		if(Victim != null) {
			if(ArrayUtils.tntp.contains(Victim)) {
				ArrayUtils.tntp.remove(Victim);
			} else {
				new Haupt().spawntntatplayer(Victim);
				ArrayUtils.tntp.add(Victim);
			}
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void WTF(Player Victim) {
		if(Victim != null) {
			
			Victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 5, true));
			Victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
			Victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 10, true));
			
			ArrayUtils.wtf.put(Victim, new BukkitRunnable() {
				int countdown = 4;
				@Override
				public void run() {
					
					if(countdown == 0) {
						Victim.teleport(Victim.getLocation());
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1)).setType(Material.GLASS);
						Victim.chat("Help me Pls im stucked ;-; I dont know where im pls help!!!");
						Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(Victim).getTaskId());
						return;
					}
				
					if(countdown == 4) {
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() - 1, Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() - 1, Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() - 1, Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() - 1, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() - 1, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() - 1, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() - 1, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() - 1, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() - 1, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.teleport(Victim.getLocation());
					}
					
					if(countdown == 3) {
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY(), Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY(), Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY(), Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY(), Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY(), Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY(), Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY(), Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY(), Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ())).setType(Material.GLASS);
						Victim.teleport(Victim.getLocation());
					}
					
					if(countdown == 2) {
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1)).setType(Material.GLASS);
						Victim.teleport(Victim.getLocation());
					}
					
					if(countdown == 1) {
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ())).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1)).setType(Material.BEDROCK);
						Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ())).setType(Material.GLASS);
						Victim.teleport(Victim.getLocation());
					}
					
					if(countdown <= 0) {
						Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(Victim).getTaskId());
					}
					countdown--;
				}
			});
			ArrayUtils.wtf.get(Victim).runTaskTimer(Main.instance, 0L, 20L);
		} else {
			if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				System.out.println(Data.noton);
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				System.out.println(Data.notonus);
			} else {
				System.out.println(Data.notonus);
			}
		}
	}
	
	public static void WebTrap(Player Victim) {
		if(Victim != null) {
	 
			
			//Oben
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 2, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			//Mitte
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 1, Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY() + 1, Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			//Unten
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY(), Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY(), Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY(), Victim.getLocation().getZ())).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY(), Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY(), Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY(), Victim.getLocation().getZ() + 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX(), Victim.getLocation().getY(), Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() + 1, Victim.getLocation().getY(), Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			Victim.getWorld().getBlockAt(new Location(Victim.getWorld(), Victim.getLocation().getX() - 1, Victim.getLocation().getY(), Victim.getLocation().getZ() - 1)).setType(Material.WEB);
			
	} else {
		if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			System.out.println(Data.noton);
		} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			System.out.println(Data.notonus);
		} else {
			System.out.println(Data.notonus);
		}
	}
	}
	
	public static void MLG(Player Victim, String mlg) {
		if(mlg.equalsIgnoreCase("slime")) {
			if(Victim != null) {
				        Victim.setAllowFlight(true);
				        Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                		if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
                		  Victim.playSound(Victim.getLocation(), Sound.BURP, 100.0F, 25.0F);
			              Victim.playSound(Victim.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
                		}
			              ItemStack slime = new ItemStack(Material.SLIME_BLOCK);
			              ItemMeta slimem = slime.getItemMeta();
			              slimem.setDisplayName("§cMLG§8-§aSlime");
			              slime.setItemMeta(slimem);
			              Victim.getInventory().addItem(new ItemStack[] { slime });
			              Titles.sendTitle(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
			              Victim.setAllowFlight(false);
			  }else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					System.out.println(Data.noton);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					System.out.println(Data.notonus);
					} else {
					System.out.println(Data.notonus);
					}
			  }
		} else if(mlg.equalsIgnoreCase("lava")) {
			if(Victim != null) {
				Victim.setAllowFlight(true);
				Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                        if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
                        	Victim.playSound(Victim.getLocation(), Sound.BURP, 100.0F, 25.0F);
                        	Victim.playSound(Victim.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
	                  		}
			              ItemStack bucket = new ItemStack(Material.LAVA_BUCKET);
			              ItemMeta meta = bucket.getItemMeta();
			              meta.setDisplayName("§cMLG§8-§bBucket");
			              bucket.setItemMeta(meta);
			              Victim.getInventory().addItem(new ItemStack[] { bucket });
			              Titles.sendTitle(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
			              Victim.setAllowFlight(false);
			  }else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					System.out.println(Data.noton);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					System.out.println(Data.notonus);
					} else {
					System.out.println(Data.notonus);
					}
			  }
		} else if(mlg.equalsIgnoreCase("cobweb")) {
			if(Victim != null) {
				Victim.setAllowFlight(true);
				Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                        if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
                        	Victim.playSound(Victim.getLocation(), Sound.BURP, 100.0F, 25.0F);
                        	Victim.playSound(Victim.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
	                  		}
			              ItemStack web = new ItemStack(Material.WEB);
			              ItemMeta webm = web.getItemMeta();
			              webm.setDisplayName("§cMLG§8-§bWeb");
			              web.setItemMeta(webm);
			              Victim.getInventory().addItem(new ItemStack[] { web });
			              Titles.sendTitle(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
			              Victim.setAllowFlight(false);
			  }else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					System.out.println(Data.noton);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					System.out.println(Data.notonus);
					} else {
					System.out.println(Data.notonus);
					}
			  }
		} else if(mlg.equalsIgnoreCase("water")) {
			if(Victim != null) {
				Victim.setAllowFlight(true);
				Victim.setVelocity(Victim.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                        if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
                        	Victim.playSound(Victim.getLocation(), Sound.BURP, 100.0F, 25.0F);
                        	Victim.playSound(Victim.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
	                  		}
			              ItemStack bucket = new ItemStack(Material.WATER_BUCKET);
			              ItemMeta meta = bucket.getItemMeta();
			              meta.setDisplayName("§cMLG§8-§bBucket");
			              bucket.setItemMeta(meta);
			              Victim.getInventory().addItem(new ItemStack[] { bucket });
			              Titles.sendTitle(Victim, 1, 10, 1, "§2MAKE A §cMLG", "");
			              Victim.setAllowFlight(false);
			  }else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					System.out.println(Data.noton);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					System.out.println(Data.notonus);
					} else {
					System.out.println(Data.notonus);
					}
			  }
		} else {
			System.out.println(mlg + " does not exist!");
			System.out.println("Existing: Lava, Water, Cobweb, Slime");
		}
	}

}
