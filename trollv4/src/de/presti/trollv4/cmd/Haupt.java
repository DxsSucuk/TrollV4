package de.presti.trollv4.cmd;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

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
		    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
		        {
		          public void run()
		          {
		            if (ArrayUtils.rotateplayer.contains(p)) {
		              p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw() + 2.0F, p.getLocation().getPitch()));
		            }
		          }
		        },0L, 2L);
		  }
	
	  public void rtp(final Player p) {
		    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
		        {
		          public void run()
		          {
		            if (ArrayUtils.randomtp.contains(p)) {
		              
		              Random r = new Random();
		              int richtungX = r.nextInt(10);
		              int richtungZ = r.nextInt(10);
		              p.teleport(new Location(p.getWorld(), p.getLocation().getX() - richtungX, p.getLocation().getY(), p.getLocation().getZ() - richtungZ));
		            } 
		          }
		        },0L, 5L);
		  }
	  
	  public void spawntntatplayer(final Player p) {
		    Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
		        {
		          public void run()
		          {
		            if (ArrayUtils.tntp.contains(p)) {
		              
		              TNTPrimed tnt = (TNTPrimed)p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
		              tnt.setCustomName("§cExplode");
		            } 
		          }
		        },0L, 2L);
		  }
	  
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
					new InvManager().choicePlayer(p);
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.prefix + "Troll Menu wurde geoeffnet!");
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.prefix + "Troll Menu has been opened!");
					} else {
						p.sendMessage(Data.prefix + "Troll Menu has been opened!");
					}
				} else {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.noperm);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.nopermus);
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
						p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
					} else {
						p.sendMessage(Data.nopermus);
					}
				}
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("help")) {
					if(p.hasPermission("troll.help") || p.hasPermission("troll.*")) {
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.prefix + "TrollV4 by Presti");
						p.sendMessage(Data.prefix + "Troll Modus §aAktiviert");
						p.sendMessage(Data.prefix + "Troll Vanish §aAktiviert");
						p.sendMessage(Data.prefix + "§4Commands:");
						p.sendMessage(Data.prefix + "§4Player Troll GUI §8- §c/troll");
						p.sendMessage(Data.prefix + "§4Server Troll GUI §8- §c/troll server");
						p.sendMessage(Data.prefix + "§4Item Troll GUI §8- §c/troll items");
						p.sendMessage(Data.prefix + "§4Troll Vanish §8- §c/troll v");
						p.sendMessage(Data.prefix + "§4Gamemode §8- §c/troll gm");
						p.sendMessage(Data.prefix + "§4Fly §8- §c/troll fly");
						p.sendMessage(Data.prefix + "§4Register §8- §c/troll register");
						p.sendMessage(Data.prefix + "§4Changelog §8- §c/troll changelog");
						p.sendMessage(Data.prefix + "§4Credits §8- §c/troll credits");
						p.setGameMode(GameMode.CREATIVE);
						if(ArrayUtils.vanish.contains(p)) {
							
						} else {
							ArrayUtils.vanish.add(p);
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(all.hasPermission("troll.vanish")) {
									all.showPlayer(p);
								} else {
								all.hidePlayer(p);
								}
							}
						}
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.prefix + "TrollV4 by Presti");
						p.sendMessage(Data.prefix + "Troll Modus §aActive");
						p.sendMessage(Data.prefix + "Troll Vanish §aActive");
						p.sendMessage(Data.prefix + "§4Commands:");
						p.sendMessage(Data.prefix + "§4Player Troll GUI §8- §c/troll");
						p.sendMessage(Data.prefix + "§4Server Troll GUI §8- §c/troll server");
						p.sendMessage(Data.prefix + "§4Item Troll GUI §8- §c/troll items");
						p.sendMessage(Data.prefix + "§4Troll Vanish §8- §c/troll v");
						p.sendMessage(Data.prefix + "§4Gamemode §8- §c/troll gm");
						p.sendMessage(Data.prefix + "§4Fly §8- §c/troll fly");
						p.sendMessage(Data.prefix + "§4Register §8- §c/troll register");
						p.sendMessage(Data.prefix + "§4Changelog §8- §c/troll changelog");
						p.sendMessage(Data.prefix + "§4Credits §8- §c/troll credits");
						p.setGameMode(GameMode.CREATIVE);
						if(ArrayUtils.vanish.contains(p)) {
							
						} else {
							ArrayUtils.vanish.add(p);
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(all.hasPermission("troll.vanish")) {
									all.showPlayer(p);
								} else {
								all.hidePlayer(p);
								}
							}
						}
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.1").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.2").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.3").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.4").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.5").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.6").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.7").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.8").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.9").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.10").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						p.sendMessage(Config.getconfig2().getString("Message.Main.Line.11").replace("[PREFIX]", Data.prefix).replace("&", "§"));
                        p.setGameMode(GameMode.CREATIVE);
						if(ArrayUtils.vanish.contains(p)) {
							
						} else {
							ArrayUtils.vanish.add(p);
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(all.hasPermission("troll.vanish")) {
									all.showPlayer(p);
								} else {
								all.hidePlayer(p);
								}
							}
						}
					} else {
						p.sendMessage(Data.prefix + "TrollV4 by Presti");
						p.sendMessage(Data.prefix + "Troll Modus §aActive");
						p.sendMessage(Data.prefix + "Troll Vanish §aActive");
						p.sendMessage(Data.prefix + "§4Commands:");
						p.sendMessage(Data.prefix + "§4Player Troll GUI §8- §c/troll");
						p.sendMessage(Data.prefix + "§4Server Troll GUI §8- §c/troll server");
						p.sendMessage(Data.prefix + "§4Item Troll GUI §8- §c/troll items");
						p.sendMessage(Data.prefix + "§4Troll Vanish §8- §c/troll v");
						p.sendMessage(Data.prefix + "§4Gamemode §8- §c/troll gm");
						p.sendMessage(Data.prefix + "§4Fly §8- §c/troll fly");
						p.sendMessage(Data.prefix + "§4Register §8- §c/troll register");
						p.sendMessage(Data.prefix + "§4Changelog §8- §c/troll changelog");
						p.sendMessage(Data.prefix + "§4Credits §8- §c/troll credits");
						p.setGameMode(GameMode.CREATIVE);
						if(ArrayUtils.vanish.contains(p)) {
							
						} else {
							ArrayUtils.vanish.add(p);
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(all.hasPermission("troll.vanish")) {
									all.showPlayer(p);
								} else {
								all.hidePlayer(p);
								}
							}
						}
					}
					 } else {
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
								p.sendMessage(Data.noperm);
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
								p.sendMessage(Data.nopermus);
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
								p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
							} else {
								p.sendMessage(Data.nopermus);
							}
						}
				} else if(args[0].equalsIgnoreCase("changelog")) {
					if(p.hasPermission("troll.log") || p.hasPermission("troll.*")) {
						Changelog.sendlog(p);
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(args[0].equalsIgnoreCase("credits")) {
					if(p.hasPermission("troll.log") || p.hasPermission("troll.*")) {
						Changelog.sendCredits(p);
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(args[0].equalsIgnoreCase("register")) {
					if(p.hasPermission("troll.register") || p.hasPermission("troll.*")) {
						   p.sendMessage(Data.prefix + "Troll Plugin V3 by Presti");
						   p.sendMessage(Data.prefix + "Test Server Registration: https://dxssucuk.jimdofree.com/tests");
						   p.sendMessage(Data.prefix + "Server Registration: https://dxssucuk.jimdofree.com/server");
						   p.sendMessage(Data.prefix + "Beta Tester Registration: https://dxssucuk.jimdofree.com/betat");
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(args[0].equalsIgnoreCase("gm")) {
					if(p.hasPermission("troll.gm") || p.hasPermission("troll.*")) {
						p.setGameMode(GameMode.CREATIVE);
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.prefix + "Du bist nun im Gamemode 1!");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.prefix + "Youre now in Gamemode 1!");
							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.GamemodeCommand").replace("[PREFIX]", Data.prefix).replace("&", "§"));
							} else {
							p.sendMessage(Data.prefix + "Youre now in Gamemode 1!");	
							}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(args[0].equalsIgnoreCase("fly")) {
					if(p.hasPermission("troll.fly") || p.hasPermission("troll.*")) {
						if(p.getAllowFlight() == true) {
							p.setAllowFlight(false);
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
	   						    p.sendMessage(Data.prefix + "Du kannst nun nicht mehr Fliegen!");
	   							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
	   							p.sendMessage(Data.prefix + "You can not fly anymore!");
	   							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
	   							p.sendMessage(Config.getconfig2().getString("Message.Fly.Quit").replace("[PREFIX]", Data.prefix).replace("&", "§"));
	   							} else {
	   							p.sendMessage(Data.prefix + "You can not fly anymore!");	
	   							}
						} else {
							p.setAllowFlight(true);
							if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
   	   						    p.sendMessage(Data.prefix + "Du kannst nun Fliegen!");
   	   							} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
   	   							p.sendMessage(Data.prefix + "You can fly now!");
   	   						    } else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
   								p.sendMessage(Config.getconfig2().getString("Message.Fly.Join").replace("[PREFIX]", Data.prefix).replace("&", "§"));
   	   							} else {
   	   							p.sendMessage(Data.prefix + "You can fly now!");	
   	   							}
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(args[0].equalsIgnoreCase("v") || args[0].equalsIgnoreCase("vanish")) {
					if(p.hasPermission("troll.vanish") || p.hasPermission("troll.*")) {
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 300.0F, 20.0F);
					if(ArrayUtils.vanish.contains(p)) {
						for(Player all : Bukkit.getOnlinePlayers()) {
							all.showPlayer(p);
						}
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			                p.sendMessage(Data.prefix + "Du hast den Vanish verlassen!");
			                } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			               	p.sendMessage(Data.prefix + "Youre not longer in Vanish!");
			                } else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.Vanish.Quit").replace("[PREFIX]", Data.prefix).replace("&", "§"));
			                } else {
			                p.sendMessage(Data.prefix + "Youre not longer in Vanish!");
			                }
						ArrayUtils.vanish.remove(p);
					} else {
						p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 300.0F, 20.0F);
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.prefix + "Du bist nun im Vanish!");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.prefix + "Youre now in Vanish!");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.Vanish.Join").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.prefix + "Youre now in Vanish!");
						}
						for(Player all : Bukkit.getOnlinePlayers()) {
							if(all.hasPermission("troll.vanish")) {
								all.showPlayer(p);
							} else {
							all.hidePlayer(p);
							}
						}
						ArrayUtils.vanish.add(p);
					}
				   } else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(args[0].equalsIgnoreCase("items")) {
					if(p.hasPermission("troll.items") || p.hasPermission("troll.*")) {
						new InvManager().openItemInv(p);
						p.playSound(p.getLocation(), Sound.CHEST_OPEN, 3, 1);
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						p.sendMessage(Data.prefix + "Item Troll Inv wurde ge§ffnet!");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						p.sendMessage(Data.prefix + "Item Troll Inv was opened!");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
						p.sendMessage(Config.getconfig2().getString("Message.ItemTrollCommand").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
				    	p.sendMessage(Data.prefix + "Item Troll Inv was opened!");	
						}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else if(args[0].equalsIgnoreCase("server")) {
					if(p.hasPermission("troll.server") || p.hasPermission("troll.*")) {
					new InvManager().openServerInv(p);
					p.playSound(p.getLocation(), Sound.CHEST_OPEN, 3, 1);
					if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
					p.sendMessage(Data.prefix + "Server Troll Inv wurde ge§ffnet!");
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					p.sendMessage(Data.prefix + "Server Troll Inv was opened!");
					} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
					p.sendMessage(Config.getconfig2().getString("Message.ServerTrollCommand").replace("[PREFIX]", Data.prefix).replace("&", "§"));
					} else {
			    	p.sendMessage(Data.prefix + "Server Troll Inv was opened!");	
					}
					} else {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage(Data.noperm);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage(Data.nopermus);
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
						} else {
							p.sendMessage(Data.nopermus);
						}
					}
				} else {
					if(p.hasPermission("troll.help") || p.hasPermission("troll.*")) {
						if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
							p.sendMessage("§8§");
							p.sendMessage(Data.prefix + "TrollV4 " + Data.version);
							p.sendMessage(Data.prefix + "Alle Commands /troll help");
							p.sendMessage(Data.prefix + "TrollV4 von");
							p.sendMessage(Data.prefix + "Presti");
							p.sendMessage("§8§");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
							p.sendMessage("§8»");
							p.sendMessage(Data.prefix + "TrollV4 " + Data.version);
							p.sendMessage(Data.prefix + "All Commands /troll help");
							p.sendMessage(Data.prefix + "TrollV4 by");
							p.sendMessage(Data.prefix + "Presti");
							p.sendMessage("§8»");
						} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
							p.sendMessage(Config.getconfig2().getString("Message.Help.Line.1").replace("[PREFIX]", Data.prefix).replace("&", "§").replace("[VERSION]", Data.version));
							p.sendMessage(Config.getconfig2().getString("Message.Help.Line.2").replace("[PREFIX]", Data.prefix).replace("&", "§").replace("[VERSION]", Data.version));
							p.sendMessage(Config.getconfig2().getString("Message.Help.Line.3").replace("[PREFIX]", Data.prefix).replace("&", "§").replace("[VERSION]", Data.version));
							p.sendMessage(Config.getconfig2().getString("Message.Help.Line.4").replace("[PREFIX]", Data.prefix).replace("&", "§").replace("[VERSION]", Data.version));
							p.sendMessage(Config.getconfig2().getString("Message.Help.Line.5").replace("[PREFIX]", Data.prefix).replace("&", "§").replace("[VERSION]", Data.version));
							p.sendMessage(Config.getconfig2().getString("Message.Help.Line.6").replace("[PREFIX]", Data.prefix).replace("&", "§").replace("[VERSION]", Data.version));
						} else {
							p.sendMessage("§8»");
							p.sendMessage(Data.prefix + "TrollV4 " + Data.version);
							p.sendMessage(Data.prefix + "All Commands /troll help");
							p.sendMessage(Data.prefix + "TrollV4 by");
							p.sendMessage(Data.prefix + "Presti");
							p.sendMessage("§8»");
						}
						 } else {
								if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
									p.sendMessage(Data.noperm);
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
									p.sendMessage(Data.nopermus);
								} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
									p.sendMessage(Config.getconfig2().getString("Message.NoPerms").replace("[PREFIX]", Data.prefix).replace("&", "§"));
								} else {
									p.sendMessage(Data.nopermus);
								}
							}
				}
			} else {
				if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			        p.sendMessage(Data.prefix + "Unbekannte Argumente bitte benutze /troll help");
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
					p.sendMessage(Data.prefix + "Unknown arguments please use /troll help");
				} else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
					p.sendMessage(Config.getconfig2().getString("Message.ArgumetsError").replace("[PREFIX]", Data.prefix).replace("&", "§"));
				} else {
					p.sendMessage(Data.prefix + "Unknown arguments please use /troll help");		
				}
			}
		} else {
		  if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			sender.sendMessage(Data.noplayer);
		  } else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			sender.sendMessage(Data.noplayerus);  
		  } else if(Config.getconfig().getString("Language").equalsIgnoreCase("CUSTOME")) {
			sender.sendMessage(Config.getconfig2().getString("Message.Console").replace("[PREFIX]", Data.prefix).replace("&", "§"));
		  } else {
		    sender.sendMessage(Data.noplayerus);
		  }
		}
		return false;
	}

}
