package de.presti.trollv4.listener;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.hash.HashingInputStream;

import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.invs.*;
import de.presti.trollv4.main.*;
import de.presti.trollv4.utils.*;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 11.05.2019 / 23:33:04												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich gesch§tzt.			*
*	Das Urheberrecht liegt, soweit nicht ausdr§cklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielf§ltigung, Verbreitung, Vermietung, Verleihung,			*
*	§ffentlichen Zug§nglichmachung oder anderer Nutzung							*
*	bedarf der ausdr§cklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class GuiListener implements Listener {
	public int taskID;
	public int taskID2;
	
	@EventHandler
	public void onTrollGuiClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
	    if(e.getView().getTitle().equalsIgnoreCase("§2Player Choice Menu")) {
	    	e.getResult();
	    	e.setResult(Result.DENY);
	    	for(Player all : Bukkit.getOnlinePlayers()) {
	    	if(e.getCurrentItem().getItemMeta().getDisplayName().replace("§2", "").equalsIgnoreCase(all.getName())) {
	    		if(p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
	    			ArrayUtils.trolling.put(p.getName(), all.getName());
	    			e.getView().close();
	    			new InvManager().openPlayerInv(p);
	    		} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
					e.getView().close();
				}
	    	}
	     }
	    } else if(e.getView().getTitle().equalsIgnoreCase("§2Which MLG?")) {
			e.getResult();
			e.setResult(Result.DENY);
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cZurück")) {
					if(p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
					new InvManager().openPlayerInv(p);
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bWater MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
				   if(p.hasPermission("troll.mlg") || p.hasPermission("troll*")) {
					   e.getView().close();
					if(t != null) {
						  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2einen §cMLG §2machen lassen!");
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");
								} else {
							    p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");	
								}
								  t.setAllowFlight(true);
		                          t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
		                          if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						              t.playSound(t.getLocation(), Sound.BURP, 100.0F, 25.0F);
						              t.playSound(t.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
			                  		}
					              ItemStack bucket = new ItemStack(Material.WATER_BUCKET);
					              ItemMeta meta = bucket.getItemMeta();
					              meta.setDisplayName("§cMLG§8-§bBucket");
					              bucket.setItemMeta(meta);
					              t.getInventory().addItem(new ItemStack[] { bucket });
					              Titles.sendTitle(t, 1, 10, 1, "§2MAKE A §cMLG", "");
					              t.setAllowFlight(false);
					  }else {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
							} else {
							p.sendMessage(Data.notonus);
							}
						}
				   } else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Lava MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if(t != null) {
							  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
									p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2einen §cMLG §2machen lassen!");
									} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
									p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");
									} else {
								    p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");	
									}
									  t.setAllowFlight(true);
			                          t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
			                          if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
							              t.playSound(t.getLocation(), Sound.BURP, 100.0F, 25.0F);
							              t.playSound(t.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
				                  		}
						              ItemStack bucket = new ItemStack(Material.LAVA_BUCKET);
						              ItemMeta meta = bucket.getItemMeta();
						              meta.setDisplayName("§cMLG§8-§bBucket");
						              bucket.setItemMeta(meta);
						              t.getInventory().addItem(new ItemStack[] { bucket });
						              Titles.sendTitle(t, 1, 10, 1, "§2MAKE A §cMLG", "");
						              t.setAllowFlight(false);
						  }else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.noton);
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.notonus);
								} else {
								p.sendMessage(Data.notonus);
								}
						  }
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cCobweb MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if(t != null) {
							  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
									p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2einen §cMLG §2machen lassen!");
									} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
									p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");
									} else {
								    p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");	
									}
									  t.setAllowFlight(true);
			                          t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
			                          if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
							              t.playSound(t.getLocation(), Sound.BURP, 100.0F, 25.0F);
							              t.playSound(t.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
				                  		}
						              ItemStack web = new ItemStack(Material.WEB);
						              ItemMeta webm = web.getItemMeta();
						              webm.setDisplayName("§cMLG§8-§bWeb");
						              web.setItemMeta(webm);
						              t.getInventory().addItem(new ItemStack[] { web });
						              Titles.sendTitle(t, 1, 10, 1, "§2MAKE A §cMLG", "");
						              t.setAllowFlight(false);
						  }else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.noton);
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.notonus);
								} else {
								p.sendMessage(Data.notonus);
								}
						  }
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSlime Block MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if(t != null) {
							  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
									p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2einen §cMLG §2machen lassen!");
									} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
									p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");
									} else {
								    p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2do an §cMLG§2!");	
									}
									  t.setAllowFlight(true);
			                          t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
			                  		if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						              t.playSound(t.getLocation(), Sound.BURP, 100.0F, 25.0F);
						              t.playSound(t.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
			                  		}
						              ItemStack slime = new ItemStack(Material.SLIME_BLOCK);
						              ItemMeta slimem = slime.getItemMeta();
						              slimem.setDisplayName("§cMLG§8-§aSlime");
						              slime.setItemMeta(slimem);
						              t.getInventory().addItem(new ItemStack[] { slime });
						              Titles.sendTitle(t, 1, 10, 1, "§2MAKE A §cMLG", "");
						              t.setAllowFlight(false);
						  }else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.noton);
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.notonus);
								} else {
								p.sendMessage(Data.notonus);
								}
						  }
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
		}
		if(e.getView().getTitle().equalsIgnoreCase("§2Player Troll Menu")) {
			e.getResult();
			e.setResult(Result.DENY);
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cExplode")) {
			  Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
			  if(p.hasPermission("troll.explode") || p.hasPermission("troll.*")) {
				  if(t != null) {
					if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
					  t.playSound(t.getLocation(), Sound.FIREWORK_LAUNCH, 100.0F, 200.0F);
					  }
		              t.getWorld().createExplosion(t.getLocation(), 3.0F);
		              t.setHealth(0.0D);
		              if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
		              p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2zum explodieren gebracht!");
		              } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
		              p.sendMessage(Data.prefix + "You made the Player §c" + t.getName() + " §2explode!");
		              } else {
		           	  p.sendMessage(Data.prefix + "You made the Player §c" + t.getName() + " §2explode!");	  
		              }
				  }else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.notonus);
						} else {
						p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
			  } else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aFakeHack")) {
			  Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
			  if(p.hasPermission("troll.fakehack") || p.hasPermission("troll.*")) {
				  if(t != null) {
					  if (ArrayUtils.fc.contains(t))
		              {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				        p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2gezwungen aufzuhöhren!");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.prefix + "You forced the Player §c" + t.getName() + " §2to stop!");	
						} else {
						p.sendMessage(Data.prefix + "You forced the Player §c" + t.getName() + " §2to stop!");		
						}
		               t.setWalkSpeed(0.2F);
		               t.setAllowFlight(false);
		               ArrayUtils.fc.remove(t);
		              } else {
		                if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
		            	p.sendMessage(Data.prefix + "Du hast §c" + t.getName() + " §2zum hacken gebracht!");
		                } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
		               	p.sendMessage(Data.prefix + "You made the Player §c" + t.getName() + " §2hack!");	
		                } else {
		               	p.sendMessage(Data.prefix + "You made the Player §c" + t.getName() + " §2hack!");	
		                }
			            ArrayUtils.fc.add(t);
		                
		              }
				  }else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.notonus);
						} else {
						p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
			  } else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Demo")) {
			  Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
			  if(p.hasPermission("troll.demo") || p.hasPermission("troll.*")) {
				  if(t != null) {
					  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2sieht nun den Demo screen!");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.prefix + "The Player §c" + t.getName() + " §2is now seeing a Demo screen!");		
							} else {
				         	p.sendMessage(Data.prefix + "The Player §c" + t.getName() + " §2is now seeing a Demo screen!");	
							}
							DemoScreen.DemoScreen(t);
				  }else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.notonus);
						} else {
						p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
			  } else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Strike")) {
			  if(p.hasPermission("troll.strike") || p.hasPermission("troll.*")) {
				  Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
				  if(t != null) {
					  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2mit einem Blitz getroffen!");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.prefix + "You hit the Player §c" + t.getName() + " §2with a lightning bolt!");
							} else {
							p.sendMessage(Data.prefix + "You hit the Player §c" + t.getName() + " §2with a lightning bolt!");
							}
							t.getLocation().getWorld().strikeLightning(t.getLocation());
				  }else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.notonus);
						} else {
						p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
			  } else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Hack User")) {
			  Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
			  if(p.hasPermission("troll.hackuser") || p.hasPermission("troll.*")) {
				  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
           		   p.sendMessage(Data.prefix + "Der Hackvorgang startet");
           		   } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
           		   p.sendMessage(Data.prefix + "The hacking process starts");
           		   } else {
           		   p.sendMessage(Data.prefix + "The hacking process starts");   
           		   }
				  if(t != null) {
					  taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
	           				int countdown = 15;
	           				@Override
	           				public void run() {
	           					if(countdown <= 0) {
	           						if(Main.version.equals("v1_8_R3")) {
	           							Hsv1_8_R3.Hack(t);
	           						} else if(Main.version.equals("v1_9_R2")) {
	           							Hsv1_9_R2.Hack(t);
	           						} else {
	    								if(Config.cfg.getBoolean("Unsupport")) {
		           							Hsv1_9_R2.Hack(t);
	    								} else {
	                      			   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
	                 						p.sendMessage(Data.prefix + "Bitte benutze v1_8_R3 oder v1_9_R2! ODER Aktiviere Unsupport!");
	                 					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
	                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
	                 					} else {
	                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
	                 					}  
	    							}
	                      		   }
	           			            Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
	           			            Bukkit.getScheduler().cancelTask(taskID);
	           					    
	           					    return;
	           					}
	       							if(Main.version.equals("v1_8_R3")) {
	           							Hsv1_8_R3.Hack2(t);
	           						} else if(Main.version.equals("v1_9_R2")) {
	           							Hsv1_9_R2.Hack2(t);
	           						} else {
	    								if(Config.cfg.getBoolean("Unsupport")) {
		           							Hsv1_9_R2.Hack2(t);
	    								} else {
	                      			   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
	                 						p.sendMessage(Data.prefix + "Bitte benutze v1_8_R3 oder v1_9_R2! ODER Aktiviere Unsupport!");
	                 					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
	                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
	                 					} else {
	                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
	                 					}  
	    							}  
	                      		   }
	           		            Titles.sendTitle(t, 1, 20, 1, "§cHacking in " + countdown, "§4" + Main.getRandomID());
	           		            t.damage(0.1D);
	           				    countdown--;
	           				}
	           			}, 0, 20);
				  }else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.notonus);
						} else {
						p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
			  } else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRocket")) {
			  Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
			  if(p.hasPermission("troll.rocket") || p.hasPermission("troll.*")) {
				  if(t != null) {
					  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.prefix + "Du hast dem Spieler §c" + t.getName() + " §2wie eine Rakete Fliegen lassen!");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						    p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2fly like a rocket!");
							} else {
					   	    p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2fly like a rocket!");	
							}
					  t.setAllowFlight(true);
                      t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
              		if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
		              t.playSound(t.getLocation(), Sound.BURP, 100.0F, 25.0F);
		              t.playSound(t.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0F, 25.0F);
              		}
		              t.setAllowFlight(false);
				  }else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.notonus);
						} else {
						p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
			  } else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
			  
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMLG")) {
			  if(p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
				  new InvManager().openMLGchoiceInv(p);
			  }else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					p.sendMessage(Data.nopermus);
					} else {
					p.sendMessage(Data.nopermus);
					}
				}
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSpam")) {
				Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
			  if(p.hasPermission("troll.spam") || p.hasPermission("troll.*")) {
				  if(t != null) {
				  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2voll gespamt");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.prefix + "You've completely blundered the Player §c" + t.getName());
						} else {
						p.sendMessage(Data.prefix + "You've completely blundered the Player §c" + t.getName());	
						}
						for (int i = 0; i < 500; i++) {
							t.sendMessage("§cDont Cheat!");
						}
				  }else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.notonus);
						} else {
						p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
			  } else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
		  }
		  if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aStartControl")) {
				Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
				if(p.hasPermission("troll.control") || p.hasPermission("troll.*")) {
					if(!p.hasMetadata("C_H")){
						if(t != null){
							if(!t.hasMetadata("C_P")){
								if(t != p){
									if(!(t.hasPermission("troll.control"))) {
											Main.instance.startControlling(t, p);
									} else {
										if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
										p.sendMessage(Data.prefix + "Du kannst diesen Spieler nicht kontrollieren!");
										} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
										p.sendMessage(Data.prefix + "You cant control this Player!");
										} else {
										p.sendMessage(Data.prefix + "You cant control this Player!");	
										}
									}
								}else {
									if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
									p.sendMessage(Data.prefix + "Du kannst nicht dich selbst kontrollieren!");
									} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
									p.sendMessage(Data.prefix + "You cant control yourself!");	
									} else {
									p.sendMessage(Data.prefix + "You cant control yourself!");	
									}
							      }
							}else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.prefix + "Der Spieler wird schon kontrolliert!");
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.prefix + "The player is already controlled!");
								} else {
								p.sendMessage(Data.prefix + "The player is already controlled!");	
								}
							}
						}else {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
							} else {
							p.sendMessage(Data.notonus);
							}
							e.getView().close();
						}
					}else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.prefix + "Du kontrollierst schon jemanden anderen!");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.prefix + "You already control someone else!");
						} else {
							p.sendMessage(Data.prefix + "You already control someone else!");
						}
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7FakeOP")) {
					if(p.hasPermission("troll.fakeop") || p.hasPermission("troll.*")) {
					  Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if(t != null) {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.prefix + "Du hast §c" + t.getName() + " §2Fakeoped!");
								t.sendMessage("§7§o[Server: " + t.getName() + " wurde zum Operator ernannt]");
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						    	p.sendMessage(Data.prefix + "You Fakeoped §c" + t.getName() + "!");	
						    	t.sendMessage("§7§o[Server: Opped " + t.getName() + "§7§o]");
								} else {
								p.sendMessage(Data.prefix + "You Fakeopped §c" + t.getName() + "!");	
							    t.sendMessage("§7§o[Server: Opped " + t.getName() + "§7§o]");	
								}
						} else {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.noton);
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.notonus);
							} else {
								p.sendMessage(Data.notonus);
							}
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
						e.getView().close();
					}
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cCrash")) {
					if(p.hasPermission("troll.crash") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.prefix + "Du hast den Spieler §c" + t.getName() + " §2Crashen lassen");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						    p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2Crash");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.Crash").replace("[PREFIX]", Data.prefix).replace("&", "§").replace("[USER]", t.getName()));
							} else {
							p.sendMessage(Data.prefix + "You let the Player §c" + t.getName() + " §2Crash");	
							}
						if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
						t.kickPlayer(Config.getconfig2().getString("Message.Player.Crash"));
						} else {
						t.kickPlayer("java.net.ConnectException: Connection timed out: No further information");
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bUn/Freeze")) {
					if(p.hasPermission("troll.freeze") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
						if (ArrayUtils.freeze.contains(t))
			              {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				                p.sendMessage(Data.prefix + "Du hast §c" + t.getName() + " §2unfreezt");
				               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				            	p.sendMessage(Data.prefix + "You unfreezed the Player §c" + t.getName());   
				               } else {
				                p.sendMessage(Data.prefix + "You unfreezed the Player §c" + t.getName());      
				               }
				            t.setWalkSpeed(0.2F);
				            t.setFlySpeed(0.1F);
				            ArrayUtils.freeze.remove(t);
			              }
			              else
			              {
			               if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			                p.sendMessage(Data.prefix + "Du hast §c" + t.getName() + " §2gefreezt");
			               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			            	p.sendMessage(Data.prefix + "You freezed the Player §c" + t.getName());   
			               } else {
			                p.sendMessage(Data.prefix + "You freezed the Player §c" + t.getName());      
			               }
			                t.setWalkSpeed(0F);
			                t.setFlySpeed(0F);
			                ArrayUtils.freeze.add(t);
			              }
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bAntiCheat")) {
					if(p.hasPermission("troll.ac") || p.hasPermission("troll.*")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if(t != null) {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					                p.sendMessage(Data.prefix + "Das Anticheat fängt nun an §c" + t.getName() + " §2zu bemerken!");
					               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            	p.sendMessage(Data.prefix + "The Anticheat started detecting §c" + t.getName());   
					               } else {
					                p.sendMessage(Data.prefix + "The Anticheat started detecting §c" + t.getName());      
					               }
								t.sendMessage("§7[§cPrestige§7-§cAntiCheat§7] §2Stop §cHacking §7[§1VL§7/§6130.234§7]");
								t.teleport(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ()));
								e.getView().close();
						} else {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.noton);
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.notonus);
							} else {
								p.sendMessage(Data.notonus);
							}
							e.getView().close();
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§kd§cL§kd§ca§kd§cg§kd§cg§kd§ci§kd§cn§kd§cg§c§kd")) {
				if(p.hasPermission("troll.lag") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
						if(!ArrayUtils.lagging.contains(t)) {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2fängt an zu laggen!");
				               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is now lagging");   
				               } else {
				                p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is now lagging");      
				               }
							ArrayUtils.lagging.add(t);
						  } else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2hört auf zu laggen!");
					               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2stopped lagging");   
					               } else {
					                p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2stopped lagging");      
					               }
								ArrayUtils.lagging.remove(t);
						  }
						e.getView().close();
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
					e.getView().close();
			 }
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cARREST")) {
				if(p.hasPermission("troll.arrest") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					ArrayUtils.arrest.put(p, t.getLocation());
					if(t != null) {
						t.teleport(ArrayUtils.arrest.get(p));
						//Y
						t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() - 1, t.getLocation().getZ())).setType(Material.BEDROCK);
						t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ())).setType(Material.BEDROCK);
						//X
						t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY(), t.getLocation().getZ())).setType(Material.BEDROCK);
						t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY(), t.getLocation().getZ())).setType(Material.BEDROCK);
						//Z
						t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
						t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
					
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2ist nun gefangen!");
			               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is Arrested");   
			               } else {
			                p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is Arrested");      
			               }
						
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
			 }
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bRotate Player")) {
				if(p.hasPermission("troll.rotate") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
						if(ArrayUtils.rotateplayer.contains(t)) {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2dreht sich nun nicht mehr!");
				               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2stopped rotating");   
				               } else {
				                p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2stopped rotating");      
				               }
							ArrayUtils.rotateplayer.remove(t);
						} else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2dreht sich nun!");
					               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2started rotating");   
					               } else {
					                p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2started rotating");      
					               }
								new Haupt().rop(t);
							ArrayUtils.rotateplayer.add(t);
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
					
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRandom Teleport")) {
				if(p.hasPermission("troll.randomtp") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
						if(ArrayUtils.randomtp.contains(t)) {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2wird nicht mehr durch die Luft teleportiert!");
				               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
				            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2stops to be teleported through the air!");   
				               } else {
				                p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2stops to be teleported through the air!");      
				               }
							ArrayUtils.randomtp.remove(t);
						} else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2wird durch die Luft teleportiert!");
					               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2starts to be teleported through the air!");   
					               } else {
					                p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2starts to be teleported through the air!");      
					               }
								new Haupt().rtp(t);
							ArrayUtils.randomtp.add(t);
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
					
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTnT Trace")) {
				if(p.hasPermission("troll.tnttrain") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
						if(ArrayUtils.tntp.contains(t)) {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				                p.sendMessage(Data.prefix + "Hinter dem Spieler §c" + t.getName() + " §2wird nun nicht mehr TnT gespawnt!");
				               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            p.sendMessage(Data.prefix + "Behind the User §c" + t.getName() + " §2no trace of TnT is spawned anymore!");   
				               } else {
					            p.sendMessage(Data.prefix + "Behind the User §c" + t.getName() + " §2no trace of TnT is spawned anymore!");       
				               }
							ArrayUtils.tntp.remove(t);
						} else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					                p.sendMessage(Data.prefix + "Hinter dem Spieler §c" + t.getName() + " §2wird nun TnT gespawnt!");
					               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            	p.sendMessage(Data.prefix + "Behind the User §c" + t.getName() + " §2now a trace of TnT spawned!");   
					               } else {
						            p.sendMessage(Data.prefix + "Behind the User §c" + t.getName() + " §2now a trace of TnT spawned!");   
					               }
								new Haupt().spawntntatplayer(t);
							ArrayUtils.tntp.add(t);
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
					
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cWTF")) {
				if(p.hasPermission("troll.wtf") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
						
						t.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 5, true));
						t.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
						t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 10, true));
						
						ArrayUtils.wtf.put(p, new BukkitRunnable() {
							int countdown = 4;
							@Override
							public void run() {
								
								if(countdown == 0) {
									t.teleport(t.getLocation());
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 1, t.getLocation().getZ() + 1)).setType(Material.GLASS);
									t.chat("Help me Pls im stucked ;-; I dont know where im pls help!!!");
									Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
									return;
								}
							
								if(countdown == 4) {
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() - 1, t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() - 1, t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() - 1, t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() - 1, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() - 1, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() - 1, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() - 1, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() - 1, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() - 1, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.teleport(t.getLocation());
								}
								
								if(countdown == 3) {
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY(), t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY(), t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY(), t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY(), t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY(), t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY(), t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 1, t.getLocation().getZ())).setType(Material.GLASS);
									t.teleport(t.getLocation());
								}
								
								if(countdown == 2) {
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 1, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 1, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 1, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 1, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 1, t.getLocation().getZ() - 1)).setType(Material.GLASS);
									t.teleport(t.getLocation());
								}
								
								if(countdown == 1) {
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 2, t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 2, t.getLocation().getZ())).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 2, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 2, t.getLocation().getZ() + 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 2, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 2, t.getLocation().getZ() - 1)).setType(Material.BEDROCK);
									t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 1, t.getLocation().getZ())).setType(Material.GLASS);
									t.teleport(t.getLocation());
								}
								
								if(countdown <= 0) {
									Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
								}
								countdown--;
							}
						});
						ArrayUtils.wtf.get(p).runTaskTimer(Main.instance, 0L, 20L);
						
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2wird verrückt!");
				               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is going crazy!");   
				               } else {
				            	   p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is going crazy!");          
				               }
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
					
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fWeb §8Trap")) {
				if(p.hasPermission("troll.webtrap") || p.hasPermission("troll.*")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if(t != null) {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
				                p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() + " §2ist nun in Cobweb gefangen!");
				               } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					            p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is now stucked in Cobweb!");   
				               } else {
				            	p.sendMessage(Data.prefix + "The User §c" + t.getName() + " §2is now stucked in Cobweb!");       
				               }
					 
							
							//Oben
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 2, t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 2, t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 2, t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 2, t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 2, t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 2, t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 2, t.getLocation().getZ() - 1)).setType(Material.WEB);
							//Mitte
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 1, t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 1, t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 1, t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 1, t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 1, t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 1, t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 1, t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY() + 1, t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY() + 1, t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY() + 1, t.getLocation().getZ() - 1)).setType(Material.WEB);
							//Unten
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY(), t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY(), t.getLocation().getZ())).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY(), t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY(), t.getLocation().getZ() + 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX(), t.getLocation().getY(), t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1, t.getLocation().getY(), t.getLocation().getZ() - 1)).setType(Material.WEB);
							t.getWorld().getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1, t.getLocation().getY(), t.getLocation().getZ() - 1)).setType(Material.WEB);
							
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noton);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.notonus);
						} else {
							p.sendMessage(Data.notonus);
						}
						e.getView().close();
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else {
						p.sendMessage(Data.nopermus);
					}
					
				}
			}
		} else if(e.getView().getTitle().equalsIgnoreCase("§2Server Troll Menu")) {
			e.getResult();
			e.setResult(Result.DENY);
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Tpall")) {
					if(p.hasPermission("troll.tpall") || p.hasPermission("troll.*")) {
						for(Player all : Bukkit.getOnlinePlayers()) {
							all.teleport(p.getLocation());
						}
	            		   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
	            		   p.sendMessage(Data.prefix + "Alle Spieler wurden zu dir teleportiert");
	            		   } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
	            		   p.sendMessage(Data.prefix + "All players were teleported to you!");   
	            		   } else {
	            		  p.sendMessage(Data.prefix + "All players were teleported to you!");    
	            		   }
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Fakeleave")) {
					if(p.hasPermission("troll.fakeleave") || p.hasPermission("troll.*")) {
						Bukkit.broadcastMessage(Config.getconfig2().getString("Message.FakeLeave").replace("&", "§").replace("[USER]", p.getName()));
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.prefix + "Eine Fake Leave Message wurde in den Chat geschickt!");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.prefix + "A Fake Message has been Posted in the Chat!");
							} else {
							p.sendMessage(Data.prefix + "A Fake Message has been Posted in the Chat!");
							}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Hack Message")) {
					if(p.hasPermission("troll.hackmessage") || p.hasPermission("troll.*")) {
						   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
		            		   p.sendMessage(Data.prefix + "Der Hackvorgang startet");
		            		   } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
		            		   p.sendMessage(Data.prefix + "The hacking process starts");
		            		   } else {
		            		   p.sendMessage(Data.prefix + "The hacking process starts");   
		            		   }
					taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
           				int countdown = 15;
           				@Override
           				public void run() {
           					if(countdown <= 0) {
           						for(Player all : Bukkit.getOnlinePlayers()) {
           						if(Main.version.equals("v1_8_R3")) {
           							Hsv1_8_R3.Hack(all);
           						} else if(Main.version.equals("v1_9_R2")) {
           							Hsv1_9_R2.Hack(all);
           						} else {
    								if(Config.cfg.getBoolean("Unsupport")) {
	           							Hsv1_9_R2.Hack(all);
    								} else {
                      			   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
                 						p.sendMessage(Data.prefix + "Bitte benutze v1_8_R3 oder v1_9_R2! ODER Aktiviere Unsupport!");
                 					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
                 					} else {
                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
                 					}  
    							}  
                      		   }
           			            Titles.send(all, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
           			            Bukkit.getScheduler().cancelTask(taskID2);
               					}
           					    return;
           				 }
        					for(Player all : Bukkit.getOnlinePlayers()) {
       							if(Main.version.equals("v1_8_R3")) {
           							Hsv1_8_R3.Hack2(all);
           						} else if(Main.version.equals("v1_9_R2")) {
           							Hsv1_9_R2.Hack2(all);
           						} else {
    								if(Config.cfg.getBoolean("Unsupport")) {
	           							Hsv1_9_R2.Hack2(all);
    								} else {
                      			   if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
                 						p.sendMessage(Data.prefix + "Bitte benutze v1_8_R3 oder v1_9_R2! ODER Aktiviere Unsupport!");
                 					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
                 					} else {
                 						p.sendMessage(Data.prefix + "Pls use v1_8_R3 or v1_9_R2! OR Activat Unsupport!");
                 					}  
    							}  
                      		   }
           		            Titles.sendTitle(all, 1, 20, 1, "§cHacking in " + countdown, "§4" + Main.getRandomID());
           		            all.damage(0.1D);
           				    countdown--;
           				}
           			  }
           			}, 0, 20);
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				}
		}
	}

}
