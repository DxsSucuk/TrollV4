package de.presti.trollv4.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XPotion;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.api.PlayMusic;
import de.presti.trollv4.api.TrollV4API;
import de.presti.trollv4.cmd.Haupt;
import de.presti.trollv4.invs.*;
import de.presti.trollv4.main.*;
import de.presti.trollv4.utils.*;

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

	// Troll Item Gui

	@EventHandler
	public void onInteract(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();

			ItemStack tntbow = new ItemStack(XMaterial.BOW.parseMaterial());
			ItemMeta meta = tntbow.getItemMeta();
			meta.setDisplayName("§4TNTBow");
			tntbow.setItemMeta(meta);

			ItemStack lavabow = new ItemStack(XMaterial.BOW.parseMaterial());
			ItemMeta meta1 = lavabow.getItemMeta();
			meta1.setDisplayName("§cLavaBow");
			lavabow.setItemMeta(meta1);

			ItemStack blitzbow = new ItemStack(XMaterial.BOW.parseMaterial());
			ItemMeta meta2 = blitzbow.getItemMeta();
			meta2.setDisplayName("§bBlitzBow");
			blitzbow.setItemMeta(meta2);

			ItemStack creeperbow = new ItemStack(XMaterial.BOW.parseMaterial());
			ItemMeta meta3 = creeperbow.getItemMeta();
			meta3.setDisplayName("§2CreeperBow");
			creeperbow.setItemMeta(meta3);

			ItemStack bedrockbow = new ItemStack(XMaterial.BOW.parseMaterial());
			ItemMeta meta4 = bedrockbow.getItemMeta();
			meta4.setDisplayName("§0BedrockBow");
			bedrockbow.setItemMeta(meta4);

			ItemStack fireball = new ItemStack(XMaterial.STICK.parseMaterial());
			ItemMeta fmeta = fireball.getItemMeta();
			fmeta.setDisplayName("§4FireBall");
			fireball.setItemMeta(fmeta);

			ItemStack minigun = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
			ItemMeta mmeta = minigun.getItemMeta();
			mmeta.setDisplayName("§4MiniGun");
			minigun.setItemMeta(mmeta);

			ItemStack item = new ItemStack(XMaterial.DIAMOND_SWORD.parseMaterial());
			ItemMeta imeta = item.getItemMeta();
			imeta.setDisplayName("§cOP§8-§bDiamond§8-§rSWORD");
			imeta.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
			imeta.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
			item.setItemMeta(imeta);
			item.setAmount(1);

			ItemStack item2 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
			ItemMeta imeta2 = item2.getItemMeta();
			imeta2.setDisplayName("§cOP§8-§bDiamond§8-§3Protection§8-§rCHESTPLATE");
			imeta2.addEnchant(XEnchantment.PROTECTION_ENVIRONMENTAL.parseEnchantment(), 1000, true);
			item2.setItemMeta(imeta2);
			item2.setAmount(1);

			ItemStack item3 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
			ItemMeta imeta3 = item3.getItemMeta();
			imeta3.setDisplayName("§cOP§8-§bDiamond§8-§3Thorns§8-§rCHESTPLATE-§32");
			imeta3.addEnchant(XEnchantment.THORNS.parseEnchantment(), 1000, true);
			item3.setItemMeta(imeta3);
			item3.setAmount(1);

			ItemStack item4 = new ItemStack(XMaterial.DIAMOND_PICKAXE.parseMaterial());
			ItemMeta imeta4 = item4.getItemMeta();
			imeta4.setDisplayName("§cOP§8-§bDiamond§8-§rPICKAXE");
			imeta4.addEnchant(XEnchantment.DIG_SPEED.parseEnchantment(), 1000, true);
			item4.setItemMeta(imeta4);
			item4.setAmount(1);

			ItemStack item5 = new ItemStack(XMaterial.BOW.parseMaterial());
			ItemMeta imeta5 = item5.getItemMeta();
			imeta5.setDisplayName("§cOP§8-§rBOW");
			imeta5.addEnchant(XEnchantment.ARROW_INFINITE.parseEnchantment(), 1000, true);
			imeta5.addEnchant(XEnchantment.ARROW_KNOCKBACK.parseEnchantment(), 1000, true);
			imeta5.addEnchant(XEnchantment.ARROW_DAMAGE.parseEnchantment(), 1000, true);
			item5.setItemMeta(imeta5);
			item5.setAmount(1);

			ItemStack item6 = new ItemStack(XMaterial.WOODEN_HOE.parseMaterial());
			ItemMeta imeta6 = item6.getItemMeta();
			imeta6.setDisplayName("§cOP§8-§6Wood§8-§rHOE");
			imeta6.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
			imeta6.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
			item6.setItemMeta(imeta6);
			item6.setAmount(1);

			ItemStack item7 = new ItemStack(XMaterial.WOODEN_SWORD.parseMaterial());
			ItemMeta imeta7 = item7.getItemMeta();
			imeta7.setDisplayName("§cOP§8-§6Wood§8-§rSWORD");
			imeta7.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
			imeta7.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
			item7.setItemMeta(imeta7);
			item7.setAmount(1);
			if (e.getView().getTitle().equalsIgnoreCase("§2Item Troll Menu")) {
				e.setCancelled(true);
				if (p.hasPermission("troll.bows")) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4TNTBow")) {
						p.getInventory().addItem(new ItemStack[] { tntbow });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4FireBall")) {

						p.getInventory().addItem(new ItemStack[] { fireball });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4MiniGun")) {

						p.getInventory().addItem(new ItemStack[] { minigun });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§cOP§8-§bDiamond§8-§rSWORD")) {

						p.getInventory().addItem(new ItemStack[] { item });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§cOP§8-§bDiamond§8-§3Protection§8-§rCHESTPLATE")) {

						p.getInventory().addItem(new ItemStack[] { item2 });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§cOP§8-§bDiamond§8-§3Thorns§8-§rCHESTPLATE-§32")) {

						p.getInventory().addItem(new ItemStack[] { item3 });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(item4.getItemMeta().getDisplayName())) {

						p.getInventory().addItem(new ItemStack[] { item4 });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(item5.getItemMeta().getDisplayName())) {

						p.getInventory().addItem(new ItemStack[] { item5 });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(item6.getItemMeta().getDisplayName())) {

						p.getInventory().addItem(new ItemStack[] { item6 });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase(item7.getItemMeta().getDisplayName())) {

						p.getInventory().addItem(new ItemStack[] { item7 });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cLavaBow")) {

						p.getInventory().addItem(new ItemStack[] { lavabow });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bBlitzBow")) {

						p.getInventory().addItem(new ItemStack[] { blitzbow });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2CreeperBow")) {

						p.getInventory().addItem(new ItemStack[] { creeperbow });
						p.closeInventory();
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§0BedrockBow")) {

						p.getInventory().addItem(new ItemStack[] { bedrockbow });
						p.closeInventory();
					}

					p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 3.0F, 2.0F);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// Troll Rest Guis

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTrollGuiClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		try {
			if (e.getView().getTitle().equalsIgnoreCase("§2Player Choice Menu")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
					if (e.getCurrentItem().getType() == XMaterial.PLAYER_HEAD.parseItem().getType()) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							if (e.getCurrentItem().getItemMeta().getDisplayName().replace("§2", "")
									.equalsIgnoreCase(all.getName())) {
								if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
									ArrayUtils.trolling.put(p.getName(), all.getName());
									e.getView().close();
									new InvManager().openPlayerInv(p);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
									e.getView().close();
								}
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bNext Page")) {
						int page = Integer.parseInt(
								e.getInventory().getItem(45).getItemMeta().getDisplayName().replaceAll("§cPage: ", ""));

						ItemStack pagei = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
						ItemMeta pagem = pagei.getItemMeta();
						pagem.setDisplayName("§cPage: " + page + 1);
						pagei.setItemMeta(pagem);

						ItemStack pagen = XMaterial.BLUE_STAINED_GLASS_PANE.parseItem();
						ItemMeta pagenm = pagen.getItemMeta();
						pagenm.setDisplayName("§bNext Page");

						pagen.setItemMeta(pagenm);

						e.getInventory().clear();

						ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
						ItemMeta glm = gl.getItemMeta();
						glm.setDisplayName("§7");
						gl.setItemMeta(glm);

						e.getInventory().setItem(46, gl);
						e.getInventory().setItem(47, gl);
						e.getInventory().setItem(48, gl);
						e.getInventory().setItem(49, gl);
						e.getInventory().setItem(51, gl);
						e.getInventory().setItem(52, gl);
						e.getInventory().setItem(53, gl);

						if (Bukkit.getOnlinePlayers().size() > (45 * (page))) {
							e.getInventory().setItem(45, pagei);
							int i = 0;
							for (Player all : Bukkit.getOnlinePlayers()) {
								if (i > (45 * page)) {
									e.getInventory().addItem(SetItems.buildSkull(all.getName(), "§2" + all.getName()));
								}
								i++;
							}
						}

						if (Bukkit.getOnlinePlayers().size() > (45 * (page + 1))) {
							e.getInventory().setItem(50, pagen);
						} else {
							pagenm.setDisplayName("§cNo Next Page");
							pagen.setItemMeta(pagenm);
							e.getInventory().setItem(50, pagen);
						}
					}
				}
			} else if (e.getView().getTitle().equalsIgnoreCase("§2Which MLG?")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBack")) {
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						new InvManager().openPlayerInv(p);
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bWater MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack bucket = new ItemStack(XMaterial.WATER_BUCKET.parseItem());
							ItemMeta meta = bucket.getItemMeta();
							meta.setDisplayName("§cMLG§8-§bBucket");
							bucket.setItemMeta(meta);
							t.getInventory().addItem(new ItemStack[] { bucket });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("noonline"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Lava MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack bucket = new ItemStack(XMaterial.LAVA_BUCKET.parseItem());
							ItemMeta meta = bucket.getItemMeta();
							meta.setDisplayName("§cMLG§8-§bBucket");
							bucket.setItemMeta(meta);
							t.getInventory().addItem(new ItemStack[] { bucket });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("noonline"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cCobweb MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack web = new ItemStack(XMaterial.COBWEB.parseItem());
							ItemMeta webm = web.getItemMeta();
							webm.setDisplayName("§cMLG§8-§bWeb");
							web.setItemMeta(webm);
							t.getInventory().addItem(new ItemStack[] { web });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSlime Block MLG")) {
					Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
					if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
						e.getView().close();
						if (t != null) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.mlg", t));
							t.setAllowFlight(true);
							t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
							t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
							t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
							ItemStack slime = new ItemStack(XMaterial.SLIME_BLOCK.parseItem());
							ItemMeta slimem = slime.getItemMeta();
							slimem.setDisplayName("§cMLG§8-§aSlime");
							slime.setItemMeta(slimem);
							t.getInventory().addItem(new ItemStack[] { slime });
							Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
							t.setAllowFlight(false);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("noonline"));
							e.getView().close();
						}
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
			}
			if (e.getView().getTitle().equalsIgnoreCase("§2Player Troll Menu")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem() == null && e.getCurrentItem().getItemMeta() == null) {
				} else {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRechoice")) {
						if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
							ArrayUtils.trolling.remove(p.getName());
							e.getView().close();
							new InvManager().choicePlayer(p);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cNext Page")) {
						if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
							InvManager.setPageTwoTrolls(e.getClickedInventory(), p);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cExplode")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.explode") || p.hasPermission("troll.*")) {
							if (t != null) {
								t.playSound(t.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 100.0F,
										200.0F);
								t.getWorld().createExplosion(t.getLocation(), 3.0F);
								t.setHealth(0.0D);
								p.sendMessage(Data.prefix + Language.getMessage("gui.explode", t));
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aFakeHack")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.fakehack") || p.hasPermission("troll.*")) {
							if (t != null) {
								if (ArrayUtils.fc.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.fakehack.off", t));
									t.setWalkSpeed(0.2F);
									t.setAllowFlight(false);
									ArrayUtils.fc.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.fakehack.on", t));
									ArrayUtils.fc.add(t);

								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Demo")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.demo") || p.hasPermission("troll.*")) {
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.demo", t));
								DemoScreen.showDemoScreen(t);
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Strike")) {
						if (p.hasPermission("troll.strike") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.strike", t));
								t.getLocation().getWorld().strikeLightning(t.getLocation());
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Hack User")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.hackuser") || p.hasPermission("troll.*")) {
							p.sendMessage(Data.prefix + Language.getMessage("gui.hackuser"));
							if (t != null) {
								if (ArrayUtils.hackuser.containsKey(t)) {
									ArrayUtils.hackuser.get(t).cancel();
									ArrayUtils.hackuser.remove(t);
									ArrayUtils.hackuser.put(t, new BukkitRunnable() {
										int countdown = Config.cfg.getInt("trolls.hack.time");

										@Override
										public void run() {
											if (countdown <= 0) {
												HS.Hack(t);
												Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
												ArrayUtils.hackuser.get(t).cancel();

												return;
											}
											HS.Hack2(t);
											Titles.send(t, 1, 20, 1, "§cHacking in " + countdown,
													"§4" + Main.getRandomID());
											t.damage(0.1D);
											countdown--;
										}

									});
									ArrayUtils.hackuser.get(t).runTaskTimer(Main.getPlugin(), 0, 20);

								} else {
									ArrayUtils.hackuser.put(t, new BukkitRunnable() {
										int countdown = Config.cfg.getInt("trolls.hack.time");

										@Override
										public void run() {
											if (countdown <= 0) {
												HS.Hack(t);
												Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
												ArrayUtils.hackuser.get(t).cancel();

												return;
											}
											HS.Hack2(t);
											Titles.send(t, 1, 20, 1, "§cHacking in " + countdown,
													"§4" + Main.getRandomID());
											t.damage(0.1D);
											countdown--;
										}

									});
									ArrayUtils.hackuser.get(t).runTaskTimer(Main.getPlugin(), 0, 20);
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRocket")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.rocket") || p.hasPermission("troll.*")) {
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.rocket", t));
								t.setAllowFlight(true);
								t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
								t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
								t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F,
										25.0F);
								t.setAllowFlight(false);
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}

					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cMLG")) {
						if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
							new InvManager().openMLGchoiceInv(p);
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSpam")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.spam") || p.hasPermission("troll.*")) {
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.spam", t));
								for (int i = 0; i < 500; i++) {
									t.sendMessage("§cREEEEEEEEEEEEEEEEEEEEEEE!");
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aStartControl")) {
						Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
						if (p.hasPermission("troll.control") || p.hasPermission("troll.*")) {
							if (!p.hasMetadata("C_H")) {
								if (t != null) {
									if (!t.hasMetadata("C_P")) {
										if (t != p) {
											if (!(t.hasPermission("troll.control"))) {
												Main.startControlling(t, p);
											} else {
												p.sendMessage(Data.prefix
														+ Language.getMessage("gui.startcontrol.cantcontrol", t));
											}
										} else {
											p.sendMessage(
													Data.prefix + Language.getMessage("gui.startcontrol.yourself", t));
										}
									} else {
										p.sendMessage(
												Data.prefix + Language.getMessage("gui.startcontrol.iscontroled", t));
									}
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("noonline"));
									e.getView().close();
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("gui.startcontrol.alreadyc", t));
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7FakeOP")) {
						if (p.hasPermission("troll.fakeop") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.fakeop.default", t));
								t.sendMessage(Language.getMessage("gui.fakeop.opm", t));
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cCrash")) {
						if (p.hasPermission("troll.crash") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.crash.default", t));
								t.kickPlayer(Language.getMessage("gui.crash.message"));
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bUn/Freeze")) {
						if (p.hasPermission("troll.freeze") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.freeze.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.freeze.off", t));
									t.setWalkSpeed(0.2F);
									t.setFlySpeed(0.1F);
									ArrayUtils.freeze.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.freeze.on", t));
									t.setWalkSpeed(0F);
									t.setFlySpeed(0F);
									ArrayUtils.freeze.add(t);
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bAntiCheat")) {
						if (p.hasPermission("troll.ac") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.anticheat.default", t));
								t.sendMessage(Language.getMessage("gui.anticheat.detected"));
								t.teleport(new Location(t.getWorld(), t.getLocation().getX(),
										t.getLocation().getY() + 2, t.getLocation().getZ()));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§c§kd§cL§kd§ca§kd§cg§kd§cg§kd§ci§kd§cn§kd§cg§c§kd")
							|| e.getCurrentItem().getType() == XMaterial.GRASS.parseMaterial()) {
						if (p.hasPermission("troll.lag") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (!ArrayUtils.lagging.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.lag.on", t));
									ArrayUtils.lagging.add(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.lag.off", t));
									ArrayUtils.lagging.remove(t);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cARREST")) {
						if (p.hasPermission("troll.arrest") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								ArrayUtils.arrest.put(p, t.getLocation());
								t.teleport(ArrayUtils.arrest.get(p));
								// Y
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() - 1, t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								// X
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.BEDROCK.parseMaterial());
								// Z
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.BEDROCK.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.BEDROCK.parseMaterial());

								p.sendMessage(Data.prefix + Language.getMessage("gui.arrest", t));

							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bRotate Player")) {
						if (p.hasPermission("troll.rotate") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.rotateplayer.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rotate.off", t));
									ArrayUtils.rotateplayer.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rotate.on", t));
									new Haupt().rop(t);
									ArrayUtils.rotateplayer.add(t);
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§cRandom Teleport")) {
						if (p.hasPermission("troll.randomtp") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.randomtp.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rndmtp.off", t));
									ArrayUtils.randomtp.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rndmtp.on", t));
									new Haupt().rtp(t);
									ArrayUtils.randomtp.add(t);
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTnT Trace")) {
						if (p.hasPermission("troll.tnttrain") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.tntp.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tnttrace.off", t));
									ArrayUtils.tntp.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tnttrace.on", t));
									new Haupt().spawntntatplayer(t);
									ArrayUtils.tntp.add(t);
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cWTF")) {
						if (p.hasPermission("troll.wtf") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {

								t.addPotionEffect(
										new PotionEffect(XPotion.BLINDNESS.parsePotionEffectType(), 200, 5, true));
								t.addPotionEffect(new PotionEffect(XPotion.CONFUSION.parsePotionEffectType(), 200, 2));
								t.addPotionEffect(
										new PotionEffect(XPotion.SLOW.parsePotionEffectType(), 200, 10, true));

								ArrayUtils.wtf.put(p, new BukkitRunnable() {
									int countdown = 4;

									@Override
									public void run() {

										if (countdown == 0) {
											t.teleport(t.getLocation());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.GLASS.parseMaterial());
											t.chat("Help me Pls im stucked ;-; I dont know where im pls help!!!");
											Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
											return;
										}

										if (countdown == 4) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() - 1, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() - 1, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() - 1, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown == 3) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY(), t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY(), t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY(), t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY(), t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY(), t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY(), t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY(), t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY(), t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 1, t.getLocation().getZ()))
													.setType(XMaterial.GLASS.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown == 2) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
													.setType(XMaterial.GLASS.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown == 1) {
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 2, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 2, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 2, t.getLocation().getZ()))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
															t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
													.setType(XMaterial.BEDROCK.parseMaterial());
											t.getWorld()
													.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
															t.getLocation().getY() + 1, t.getLocation().getZ()))
													.setType(XMaterial.GLASS.parseMaterial());
											t.teleport(t.getLocation());
										}

										if (countdown <= 0) {
											Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
										}
										countdown--;
									}
								});
								ArrayUtils.wtf.get(p).runTaskTimer(Main.instance, 0L, 20L);

								p.sendMessage(Data.prefix + Language.getMessage("gui.wtf", t));
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fWeb §8Trap")) {
						if (p.hasPermission("troll.webtrap") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								p.sendMessage(Data.prefix + Language.getMessage("gui.webtrap", t));

								// Oben
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 2, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								// Mitte
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 1, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 1, t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								// Unten
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ()))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ() + 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());
								t.getWorld()
										.getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
												t.getLocation().getY(), t.getLocation().getZ() - 1))
										.setType(XMaterial.COBWEB.parseMaterial());

							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cL§5S§bD")) {
						if (p.hasPermission("troll.lsd") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								TrollV4API.LSD(t);
								p.sendMessage(Data.prefix + Language.getMessage("gui.lsd", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§1Guardian")) {
						if (p.hasPermission("troll.lsd") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								TrollV4API.GuardinShow(t);
								p.sendMessage(Data.prefix + Language.getMessage("gui.guardian", t));
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
						/*
						 * } else if
						 * (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
						 * "§7EndScreen")) { if (p.hasPermission("troll.lsd") ||
						 * p.hasPermission("troll.*")) { Player t =
						 * Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName())); if (t != null) {
						 * TrollV4API.EndGame(t); if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						 * p.sendMessage(Data.prefix + "Der Spieler §c" + t.getName() +
						 * " §2sieht nun den EndScreen!"); } else if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						 * p.sendMessage(Data.prefix + "The User §c" + t.getName() +
						 * " §2now sees the EndScreen!"); } else { p.sendMessage(Data.prefix +
						 * "The User §c" + t.getName() + " §2now sees the EndScreen!"); }
						 * e.getView().close(); } else { if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						 * p.sendMessage(Data.noton); } else if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						 * p.sendMessage(Data.notonus); } else { p.sendMessage(Data.notonus); }
						 * e.getView().close(); } } else { if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
						 * p.sendMessage(Data.noperm); } else if
						 * (Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
						 * p.sendMessage(Data.nopermus); } else { p.sendMessage(Data.nopermus); }
						 * 
						 * }
						 */
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Herobrine")) {
						if (p.hasPermission("troll.herobrine") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.herobrine.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.herobrine.off", t));
									ArrayUtils.herobrine.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.herobrine.on", t));
									ArrayUtils.herobrine.add(t);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3Arrow Spam")) {
						if (p.hasPermission("troll.arrowspam") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.userbowspam.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.arrowspam.off", t));
									ArrayUtils.arrowspam.get(t).cancel();
									ArrayUtils.userbowspam.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.arrowspam.on", t));
									ArrayUtils.userbowspam.add(t);
									ArrayUtils.arrowspam.put(t, new BukkitRunnable() {

										@Override
										public void run() {

											Location loc = t.getLocation().clone();

											Arrow arrow = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc = arrow.getLocation();
											Vector angle = new Vector(loc.getX() - aloc.getX(),
													loc.getY() - aloc.getBlockY(), loc.getZ() - aloc.getBlockZ());
											arrow.setVelocity(angle.normalize().multiply(2.0D));

											Arrow arrow2 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc2 = arrow2.getLocation();
											Vector angle2 = new Vector(loc.getX() - aloc2.getX(),
													loc.getY() - aloc2.getBlockY(), loc.getZ() - aloc2.getBlockZ());
											arrow2.setVelocity(angle2.normalize().multiply(2.0D));

											Arrow arrow3 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc3 = arrow3.getLocation();
											Vector angle3 = new Vector(loc.getX() - aloc3.getX(),
													loc.getY() - aloc3.getBlockY(), loc.getZ() - aloc3.getBlockZ());
											arrow3.setVelocity(angle3.normalize().multiply(2.0D));

											Arrow arrow4 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc4 = arrow4.getLocation();
											Vector angle4 = new Vector(loc.getX() - aloc4.getX(),
													loc.getY() - aloc4.getBlockY(), loc.getZ() - aloc4.getBlockZ());
											arrow4.setVelocity(angle4.normalize().multiply(2.0D));

											Arrow arrow5 = (Arrow) t.getWorld()
													.spawn(loc.clone().add(Main.getPlugin().getRandom(-10, 10),
															Main.getPlugin().getRandom(5, 10),
															Main.getPlugin().getRandom(-10, 10)), Arrow.class);
											Location aloc5 = arrow5.getLocation();
											Vector angle5 = new Vector(loc.getX() - aloc5.getX(),
													loc.getY() - aloc5.getBlockY(), loc.getZ() - aloc5.getBlockZ());
											arrow5.setVelocity(angle5.normalize().multiply(2.0D));

										}
									});
									ArrayUtils.arrowspam.get(t).runTaskTimer(Main.getPlugin(), 0L, 10L);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Tornado")) {
						if (p.hasPermission("troll.tornado") || p.hasPermission("troll.*")) {

							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.tornado.contains(t)) {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tornado.off", t));
									ArrayUtils.tornador.get(t).cancel();
									ArrayUtils.tornado.remove(t);
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.tornado.on", t));
									ArrayUtils.tornado.add(t);
									ArrayUtils.tornador.put(t, new BukkitRunnable() {

										Random r = new Random();

										int rix = r.nextBoolean() ? -1 : 1;
										int riz = r.nextBoolean() ? -1 : 1;

										@Override
										public void run() {
											Location location1 = t.getLocation();
											FallingBlock o;
											Location location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ());

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() - 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ() - 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ() + 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ() + 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ() - 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ());

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ());

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) / 4.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() + 1);

											if ((location3 != null) && location3.getBlock() != null && location3
													.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
												o = location3.getWorld().spawnFallingBlock(
														location3.getBlock().getLocation(),
														location3.getBlock().getType(), (byte) 0);
												location3.getBlock().setType(XMaterial.AIR.parseMaterial());
												o.setFallDistance(0.0F);
												o.setVelocity(new Vector(
														r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
														0.6D + r.nextInt(2) * 2.5D,
														r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											}

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ());

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() + 1);

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() - 1, location1.getZ() - 1);

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX() + 1,
													location1.getY() - 1, location1.getZ());

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											location3 = new Location(location1.getWorld(), location1.getX() - 1,
													location1.getY() - 1, location1.getZ());

											location3.getBlock().setType(XMaterial.STONE.parseMaterial());
											o = location1.getWorld().spawnFallingBlock(
													location3.getBlock().getLocation(), location3.getBlock().getType(),
													(byte) 0);
											o.setFallDistance(0.0F);
											o.setVelocity(new Vector(
													r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
													0.6D + r.nextInt(2) * 2.5D,
													r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
											location3.getBlock().setType(XMaterial.AIR.parseMaterial());

											Location location2 = new Location(location1.getWorld(), location1.getX(),
													location1.getY() + 3.0D, location1.getZ(),
													location1.getYaw() + 15.0F, location1.getPitch());
											t.teleport(location2);

										}
									});
									ArrayUtils.tornador.get(t).runTaskTimer(Main.getPlugin(), 0, 8);
								}
								e.getView().close();
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cFake§7Inv")) {
						if (p.hasPermission("troll.fakeinv") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if (ArrayUtils.fakeinv.containsKey(t)) {
									InvSaver is = ArrayUtils.fakeinv.get(t);

									t.getInventory().setArmorContents(is.getArmor());
									t.getInventory().setExtraContents(is.getExtracont());
									t.getInventory().setContents(is.getContent());

									p.sendMessage(Data.prefix + Language.getMessage("gui.fakeinv.cancel", t));
									e.getView().close();

									ArrayUtils.fakeinv.remove(t);
								} else {
									InvSaver is = new InvSaver(t.getName(), t.getInventory().getContents(),
											t.getInventory().getExtraContents(), t.getInventory().getArmorContents());

									t.getInventory().clear();

									ArrayUtils.fakeinv.put(t, is);

									p.sendMessage(Data.prefix + Language.getMessage("gui.fakeinv.default", t));
									e.getView().close();

									new BukkitRunnable() {

										@Override
										public void run() {
											if (ArrayUtils.fakeinv.containsKey(t)) {
												InvSaver is = ArrayUtils.fakeinv.get(t);
												t.getInventory().setArmorContents(is.getArmor());
												t.getInventory().setExtraContents(is.getExtracont());
												t.getInventory().setContents(is.getContent());
												ArrayUtils.fakeinv.remove(t);
											}
											cancel();
										}
									}.runTaskLater(Main.instance, (Config.cfg.getInt("trolls.fakeinv.time") * 20L));

								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§cNo §7Inv §2for §cYOU")) {
						if (p.hasPermission("troll.fakeinv") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {

								if (ArrayUtils.noinv.contains(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.noinv.off", t));

									ArrayUtils.noinv.remove(t);
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.noinv.on", t));

									if (t.getOpenInventory() != null) {
										t.getOpenInventory().close();
									}

									ArrayUtils.noinv.add(t);
								}

							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§aSlippery §7Hands")) {
						if (p.hasPermission("troll.slipperyhands") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {

								if (ArrayUtils.noitem.contains(t)) {

									p.sendMessage(Data.prefix + Language.getMessage("gui.slipperyhands.off", t));

									ArrayUtils.noitem.remove(t);
								} else {

									p.sendMessage(Data.prefix + Language.getMessage("gui.slipperyhands.on", t));

									if (t.getItemInHand() != null
											&& t.getItemInHand().getType() != XMaterial.AIR.parseMaterial()) {
										t.getWorld().dropItemNaturally(t.getLocation(), t.getItemInHand())
												.setPickupDelay(20);
									}
									t.getInventory().setItem(t.getInventory().getHeldItemSlot(), null);

									new BukkitRunnable() {

										@Override
										public void run() {
											Player sheesh = t;
											if (ArrayUtils.noitem.contains(sheesh)) {
												if (sheesh.getItemInHand() != null && sheesh.getItemInHand()
														.getType() != XMaterial.AIR.parseMaterial()) {
													sheesh.getWorld().dropItemNaturally(sheesh.getLocation(),
															sheesh.getItemInHand()).setPickupDelay(20);
													sheesh.getInventory()
															.setItem(sheesh.getInventory().getHeldItemSlot(), null);
												}
											} else {
												cancel();
											}
										}
									}.runTaskTimer(Main.instance, 0L,
											(Config.cfg.getInt("trolls.slipperyhands.time") * 20L));

									ArrayUtils.noitem.add(t);
								}

							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTnT World")) {
						if (p.hasPermission("troll.tntworld") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								Location oldl = t.getLocation();
								for (int x = 0; x < 200; x++) {
									for (int y = 0; y < 30; y++) {
										for (int z = 0; z < 200; z++) {
											if (new Location(oldl.getWorld(), oldl.getBlockX() - 100 + x,
													oldl.getBlockY() - 7 + y, oldl.getBlockZ() - 100 + z).getBlock()
															.getType() != XMaterial.AIR.parseMaterial()) {
												t.sendBlockChange(
														new Location(oldl.getWorld(), oldl.getBlockX() - 100 + x,
																oldl.getBlockY() - 7 + y, oldl.getBlockZ() - 100 + z),
														XMaterial.TNT.parseMaterial().createBlockData());
											}
										}
									}
								}
								p.sendMessage(Data.prefix + Language.getMessage("gui.tntworld.default", t));
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bRickRoll")) {
						if (p.hasPermission("troll.rickroll") || p.hasPermission("troll.*")) {
							Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
							if (t != null) {
								if(!PlayMusic.isPlaying(t)) {
									PlayMusic.play(t, "plugins/TrollV4/rick.nbs");
									p.sendMessage(Data.prefix + Language.getMessage("gui.rickroll.default", t));
								} else {
									p.sendMessage(Data.prefix + Language.getMessage("gui.rickroll.ishearing", t));
								}
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("noonline"));
								e.getView().close();
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					}
				}
			} else if (e.getView().getTitle().equalsIgnoreCase("§2Server Troll Menu")) {
				e.getResult();
				e.setResult(Result.DENY);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Tpall")) {
					if (p.hasPermission("troll.tpall") || p.hasPermission("troll.*")) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							all.teleport(p.getLocation());
						}
						p.sendMessage(Data.prefix + Language.getMessage("gui.tpall"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Fakeleave")) {
					if (p.hasPermission("troll.fakeleave") || p.hasPermission("troll.*")) {
						Bukkit.broadcastMessage(Language.getMessage("gui.fakeleave.message", p));
						p.sendMessage(Data.prefix + Language.getMessage("gui.fakeleave.default"));
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Hack Message")) {
					if (p.hasPermission("troll.hackmessage") || p.hasPermission("troll.*")) {
						p.sendMessage(Data.prefix + Language.getMessage("gui.hackserver"));
						taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
							int countdown = Config.cfg.getInt("trolls.hack.time");

							@Override
							public void run() {
								if (countdown <= 0) {
									for (Player all : Bukkit.getOnlinePlayers()) {
										HS.Hack(all);
										Titles.send(all, 1, 20, 1, "§cHACKED", "§4" + Main.getRandomID());
										Bukkit.getScheduler().cancelTask(taskID2);
									}
									return;
								}
								for (Player all : Bukkit.getOnlinePlayers()) {
									HS.Hack2(all);
									Titles.send(all, 1, 20, 1, "§cHacking in " + countdown, "§4" + Main.getRandomID());
									all.damage(0.1D);
									countdown--;
								}
							}
						}, 0, 20);
					} else {
						p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
						e.getView().close();
					}
				}
			}
		} catch (Exception e2) {

		}
	}

}
