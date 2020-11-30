package de.presti.trollv4.invs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.server.ServerInfo;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 11.05.2019 / 22:05:58												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich gesch§tzt.			*
*	Das Urheberrecht liegt, soweit nicht ausdr§cklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielf§ltigung, Verbreitung, Vermietung, Verleihung,			*
*	§ffentlichen Zug§nglichmachung oder anderer Nutzung							*
*	bedarf der ausdr§cklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class InvManager {
	public int taskID;

	public static ItemStack ad() {
		ItemStack red = new ItemStack(XMaterial.REDSTONE.parseMaterial());
		List<String> lorer = new ArrayList<String>();
		ItemMeta rm = red.getItemMeta();

		rm.setDisplayName("§cSubscribe to Me :o");
		lorer.clear();
		lorer.add("§rY§cT §8- §cNot Memerinoto");
		lorer.add("§5Insta §8- §cMemerinoto");
		rm.setLore(lorer);
		red.setItemMeta(rm);

		return red;
	}

	public static ItemStack info() {
		ItemStack red = new ItemStack(XMaterial.PAPER.parseMaterial());
		List<String> lorer = new ArrayList<String>();
		ItemMeta rm = red.getItemMeta();

		rm.setDisplayName("§cInfo");
		lorer.clear();
		lorer.add("§2Plugin Version: §c" + Data.version);
		lorer.add("§2Server Version: §c" + ServerInfo.getMcVersion());
		lorer.add("§2Server Packet Version: §c" + ServerInfo.getNMSVersion());
		lorer.add("§2Server Software: §c" + ServerInfo.getServerSoftware());
		rm.setLore(lorer);
		red.setItemMeta(rm);

		return red;
	}

	public static ItemStack Glass() {
		ItemStack glass = new ItemStack(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem());
		List<String> lore = new ArrayList<String>();
		ItemMeta m = glass.getItemMeta();

		m.setDisplayName("§8§kddd §c§lLoading... §8§kddd");
		lore.clear();
		lore.add("§8§kddd §c§lStill Loading... §8§kddd");
		m.setLore(lore);
		glass.setItemMeta(m);

		return glass;
	}

	public static ItemStack Glass2() {
		ItemStack glass = new ItemStack(XMaterial.BLUE_STAINED_GLASS_PANE.parseItem());
		List<String> lore = new ArrayList<String>();
		ItemMeta m = glass.getItemMeta();

		m.setDisplayName("§8§kddd §c§lLoading... §8§kddd");
		lore.clear();
		lore.add("§8§kddd §c§lStill Loading... §8§kddd");
		m.setLore(lore);
		glass.setItemMeta(m);

		return glass;
	}

	public static ItemStack Glass3() {
		ItemStack glass = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseItem());
		List<String> lore = new ArrayList<String>();
		ItemMeta m = glass.getItemMeta();

		m.setDisplayName("§8§kddd §c§lLoading... §8§kddd");
		lore.clear();
		lore.add("§8§kddd §c§lStill Loading... §8§kddd");
		m.setLore(lore);
		glass.setItemMeta(m);

		return glass;
	}

	public static ItemStack createItem(Material mat, int amount, String displayname, String lore) {
		List<String> list = new ArrayList<String>();
		ItemStack item = new ItemStack(mat, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		if (lore != null) {
			list.add(lore);
			meta.setLore(list);
		}
		item.setItemMeta(meta);
		return item;
	}

	public static void openConfigInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§cTroll Config Menu");

		ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
		ItemMeta glm = gl.getItemMeta();
		glm.setDisplayName("§7");
		gl.setItemMeta(glm);

		ItemStack back = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
		ItemMeta backm = back.getItemMeta();
		backm.setDisplayName("§cBack");
		back.setItemMeta(backm);

		inv.setItem(0, SetItems.buildItem("§cCustom§7-§2Item§7-§2Name", XMaterial.PAPER, new String[] { "§cCurrent Value:", (Config.cfg.getBoolean("Custom-Item-Name") ? "§ayes" : "§cno") }));
		inv.setItem(1, SetItems.buildItem("§bUpdateChecker", XMaterial.CLOCK, new String[] { "§cCurrent Value:", (Config.cfg.getBoolean("UpdateChecker") ? "§ayes" : "§cno") }));
		inv.setItem(2, SetItems.buildItem("§cAuto§bUpdate", XMaterial.CAULDRON, new String[] { "§cCurrent Value:", (Config.cfg.getBoolean("AutoUpdate") ? "§ayes" : "§cno") }));
		inv.setItem(3, SetItems.buildItem("§2Animations", XMaterial.GLASS_PANE, new String[] { "§cCurrent Value:", (Config.cfg.getBoolean("Animations") ? "§ayes" : "§cno") }));
		inv.setItem(4, SetItems.buildItem("§aASync", XMaterial.PLAYER_HEAD, new String[] { "§cCurrent Value:", (Config.cfg.getBoolean("ASync") ? "§ayes" : "§cno") }));
		inv.setItem(5, SetItems.buildItem("§2Community§7-§csurprise", XMaterial.CAKE, new String[] { "§cCurrent Value:", (Config.cfg.getBoolean("Community-surprise") ? "§ayes" : "§cno") }));
		inv.setItem(6, gl);
		inv.setItem(7, SetItems.buildItem("§cReload Config", XMaterial.REDSTONE.parseMaterial()));
		inv.setItem(8, back);

		p.openInventory(inv);

	}

	public void openPlayerInv(Player p) {
		ArrayList<ItemStack> items = new ArrayList<>();
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§2Player Troll Menu");
		if (Config.getconfig().getBoolean("Animations")) {
			ArrayUtils.anim.put(p, new BukkitRunnable() {
				int countdown = 15;

				@Override
				public void run() {

					if (countdown == 0) {
						inv.clear();
						p.playSound(p.getLocation(), XSound.ENTITY_ZOMBIE_INFECT.parseSound(), 1F, 1F);
						new BukkitRunnable() {

							@Override
							public void run() {
								items.add(SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
										"§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));

								new BukkitRunnable() {

									@Override
									public void run() {

										inv.setItem(0, items.get(0));

									}
								}.runTask(Main.instance);

							}
						}.runTaskAsynchronously(Main.instance);

						setPageOneTrolls(inv);

						Bukkit.getScheduler().cancelTask(ArrayUtils.anim.get(p).getTaskId());
						return;
					}

					inv.setItem(22, ad());
					inv.setItem(31, info());

					if (countdown == 15) {
						inv.setItem(0, Glass3());
						// inv.setItem(0, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(1, Glass());
						inv.setItem(53, Glass3());
					}
					if (countdown == 14) {
						// inv.setItem(2, Glass());
						inv.setItem(10, Glass3());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(3, Glass());
						inv.setItem(43, Glass3());
					}
					if (countdown == 13) {
						// inv.setItem(4, Glass());
						inv.setItem(20, Glass3());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(5, Glass());
						inv.setItem(33, Glass3());
					}
					if (countdown == 12) {
						// inv.setItem(6, Glass());
						inv.setItem(30, Glass3());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(7, Glass());
						inv.setItem(23, Glass3());
					}
					if (countdown == 11) {
						inv.setItem(8, Glass3());
						// inv.setItem(8, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(17, Glass());
						inv.setItem(45, Glass3());
					}
					if (countdown == 10) {
						inv.setItem(16, Glass3());
						// inv.setItem(26, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(35, Glass());
						inv.setItem(37, Glass3());
					}
					if (countdown == 9) {
						inv.setItem(24, Glass3());
						// inv.setItem(44, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(53, Glass());
						inv.setItem(29, Glass3());
					}
					if (countdown == 8) {
						inv.setItem(32, Glass3());
						// inv.setItem(52, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(51, Glass());
						inv.setItem(21, Glass3());
					}
					if (countdown == 7) {

						inv.setItem(1, Glass());
						inv.setItem(11, Glass2());
						inv.setItem(17, Glass());
						inv.setItem(52, Glass());
						inv.setItem(42, Glass2());
						inv.setItem(19, Glass2());
						inv.setItem(34, Glass2());
						inv.setItem(36, Glass());
						// inv.setItem(50, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(49, Glass());
					}
					if (countdown == 6) {

						inv.setItem(2, Glass());
						inv.setItem(12, Glass2());
						inv.setItem(26, Glass());
						inv.setItem(51, Glass());
						inv.setItem(28, Glass2());
						inv.setItem(41, Glass2());
						inv.setItem(25, Glass2());
						inv.setItem(27, Glass());
						// inv.setItem(48, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(47, Glass());
					}
					if (countdown == 5) {

						inv.setItem(3, Glass());
						inv.setItem(35, Glass());
						inv.setItem(50, Glass());
						inv.setItem(18, Glass());
						inv.setItem(13, Glass2());
						inv.setItem(40, Glass2());
						// inv.setItem(46, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(45, Glass());

					}
					if (countdown == 4) {

						inv.setItem(4, Glass());
						inv.setItem(44, Glass());
						inv.setItem(49, Glass());
						inv.setItem(9, Glass());
						inv.setItem(14, Glass2());
						inv.setItem(39, Glass2());

						// inv.setItem(36, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(27, Glass());
					}
					if (countdown == 3) {

						inv.setItem(5, Glass());
						inv.setItem(48, Glass());
						inv.setItem(15, Glass2());
						inv.setItem(38, Glass2());

						// inv.setItem(18, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						// inv.setItem(9, Glass());
					}
					if (countdown == 2) {

						inv.setItem(6, Glass());
						inv.setItem(47, Glass());

						p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 1F, 1F);
					}
					if (countdown == 1) {
						inv.setItem(7, Glass());
						inv.setItem(46, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 1F, 1F);
					}
					if (countdown <= 0) {
						Bukkit.getScheduler().cancelTask(ArrayUtils.anim.get(p).getTaskId());
					}
					countdown--;
				}
			});
			ArrayUtils.anim.get(p).runTaskTimer(Main.instance, 0L, 20L);

		} else {
			new BukkitRunnable() {

				@Override
				public void run() {
					items.add(SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
							"§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));

					new BukkitRunnable() {

						@Override
						public void run() {

							inv.setItem(0, items.get(0));

						}
					}.runTask(Main.instance);

				}
			}.runTaskAsynchronously(Main.instance);

			setPageOneTrolls(inv);

		}

		p.openInventory(inv);
	}

	public void setPageOneTrolls(Inventory inv) {
		inv.setItem(10, SetItems.buildItem(Items.getItem("gui.trolls.freeze"), XMaterial.ICE.parseMaterial()));
		inv.setItem(11, SetItems.buildItem(Items.getItem("gui.trolls.fakeop"), XMaterial.GOLDEN_APPLE.parseMaterial()));
		inv.setItem(12, SetItems.buildItem(Items.getItem("gui.trolls.crash"), XMaterial.PAPER.parseMaterial()));
		inv.setItem(13,
				SetItems.buildItem(Items.getItem("gui.trolls.startcontrol"), XMaterial.GREEN_WOOL));
		inv.setItem(14, SetItems.buildItem(Items.getItem("gui.trolls.spam"), XMaterial.ARROW.parseMaterial()));
		inv.setItem(15, SetItems.buildItem(Items.getItem("gui.trolls.mlg"), XMaterial.WATER_BUCKET.parseMaterial()));
		inv.setItem(16,
				SetItems.buildItem(Items.getItem("gui.trolls.rocket"), XMaterial.FIREWORK_ROCKET.parseMaterial()));
		inv.setItem(19,
				SetItems.buildItem(Items.getItem("gui.trolls.hackuser"), XMaterial.NETHER_STAR.parseMaterial()));
		inv.setItem(20, SetItems.buildItem(Items.getItem("gui.trolls.strike"), XMaterial.BAKED_POTATO.parseMaterial()));
		inv.setItem(21, SetItems.buildItem(Items.getItem("gui.trolls.demo"), XMaterial.BEDROCK.parseMaterial()));
		inv.setItem(22, SetItems.buildItem(Items.getItem("gui.trolls.explode"), XMaterial.TNT.parseMaterial(), "§cDestroys the Map!"));
		inv.setItem(23,
				SetItems.buildItem(Items.getItem("gui.trolls.fakehack"), XMaterial.DIAMOND_SWORD.parseMaterial()));
		inv.setItem(24, SetItems.buildItem(Items.getItem("gui.trolls.anticheat"), XMaterial.IRON_AXE.parseMaterial()));
		inv.setItem(25, SetItems.buildItem(Items.getItem("gui.trolls.lagging"), XMaterial.GRASS.parseMaterial()));
		inv.setItem(28, SetItems.buildItem(Items.getItem("gui.trolls.arrest"), XMaterial.BEDROCK.parseMaterial(), "§cReplaces Blocks!"));
		inv.setItem(29, SetItems.buildItem(Items.getItem("gui.trolls.rotateplayer"), XMaterial.COOKIE.parseMaterial()));
		inv.setItem(30, SetItems.buildItem(Items.getItem("gui.trolls.randomteleport"),
				XMaterial.COMMAND_BLOCK.parseMaterial()));
		inv.setItem(31,
				SetItems.buildItem(Items.getItem("gui.trolls.tnttrace"), XMaterial.DIAMOND_BOOTS.parseMaterial(), "§cDestroys the Map!"));
		inv.setItem(32, SetItems.buildItem(Items.getItem("gui.trolls.webtrap"), XMaterial.COBWEB.parseMaterial(), "§cReplaces Blocks!"));
		inv.setItem(33, SetItems.buildItem(Items.getItem("gui.trolls.wtf"), XMaterial.MUSIC_DISC_11.parseMaterial()));
		inv.setItem(34, SetItems.buildItem(Items.getItem("gui.trolls.lsd"), XMaterial.RED_MUSHROOM.parseMaterial(), "§cLag out the Game of the Player!"));
		inv.setItem(37, SetItems.buildItem(Items.getItem("gui.trolls.guardian"), XMaterial.BLAZE_ROD.parseMaterial()));
		inv.setItem(38, SetItems.buildItem(Items.getItem("gui.trolls.arrowspam"), XMaterial.BOW.parseMaterial()));
		inv.setItem(39, SetItems.buildSkull("Herobrine", Items.getItem("gui.trolls.herobrine")));
		inv.setItem(40, SetItems.buildItem(Items.getItem("gui.trolls.tornado"), XMaterial.WHITE_WOOL.parseMaterial(), "§cDestroys the Map!"));
		inv.setItem(41, SetItems.buildItem(Items.getItem("gui.trolls.fakeinv"), XMaterial.CHEST.parseMaterial()));
		inv.setItem(42,
				SetItems.buildItem(Items.getItem("gui.trolls.noinvforyou"), XMaterial.ENDER_CHEST.parseMaterial()));
		inv.setItem(43,
				SetItems.buildItem(Items.getItem("gui.trolls.slipperyhands"), XMaterial.SLIME_BALL.parseMaterial()));
		inv.setItem(45, SetItems.buildItem("§cNext Page", XMaterial.PAPER.parseMaterial()));
		setOverallTrollMenu(inv);
	}

	public static void setPageTwoTrolls(Inventory inv, Player p) {

		ArrayList<ItemStack> items = new ArrayList<ItemStack>();

		inv.clear();

		new BukkitRunnable() {

			@Override
			public void run() {
				items.add(SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
						"§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));

				new BukkitRunnable() {

					@Override
					public void run() {

						inv.setItem(0, items.get(0));

					}
				}.runTask(Main.instance);

			}
		}.runTaskAsynchronously(Main.instance);

		inv.setItem(10, SetItems.buildItem(Items.getItem("gui.trolls.tntworld"), XMaterial.TNT.parseMaterial()));
		inv.setItem(11, SetItems.buildItem(Items.getItem("gui.trolls.rickroll"), XMaterial.BRICK.parseMaterial(), "§cCant be stopped!"));
		inv.setItem(12, SetItems.buildItem(Items.getItem("gui.trolls.dontstopjumping"),
				XMaterial.LEATHER_BOOTS.parseMaterial()));
		inv.setItem(13, SetItems.buildItem(Items.getItem("gui.trolls.deaf"), XMaterial.LEATHER_HELMET.parseMaterial()));
		inv.setItem(14, SetItems.buildItem(Items.getItem("gui.trolls.confused"), XMaterial.POTION.parseMaterial()));
		inv.setItem(15, SetItems.buildItem(Items.getItem("gui.trolls.anvils"), XMaterial.ANVIL.parseMaterial(), "§cCould give Player Items!"));
		inv.setItem(16, SetItems.buildItem(Items.getItem("gui.trolls.cows"), XMaterial.COOKED_BEEF.parseMaterial()));
		inv.setItem(19, SetItems.buildItem(Items.getItem("gui.trolls.giorno"), XMaterial.ARROW.parseMaterial(), "§cIs this an JoJo reference?"));
		inv.setItem(20, SetItems.buildItem(Items.getItem("gui.trolls.johncena"), XMaterial.GREEN_DYE, "§cThis is going to come out in 4.4.9"));
		inv.setItem(21, SetItems.buildItem(Items.getItem("gui.trolls.spookyworld"), XMaterial.JACK_O_LANTERN, "§cThis is going to come out in 4.5.0"));
		inv.setItem(22, SetItems.buildItem(Items.getItem("gui.trolls.endcredits"), XMaterial.DRAGON_EGG.parseMaterial()));
		setOverallTrollMenu(inv);
	}

	public static void setOverallTrollMenu(Inventory inv) {
		ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
		ItemMeta glm = gl.getItemMeta();
		glm.setDisplayName("§7");
		gl.setItemMeta(glm);

		for (int i = 0; i < (inv.getSize()); i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == XMaterial.AIR.parseMaterial()) {
				inv.setItem(i, gl);
			}
		}

		ItemStack gl2 = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
		ItemMeta gl2m = gl2.getItemMeta();
		gl2m.setDisplayName("§cRechoice");
		gl2.setItemMeta(gl2m);

		inv.setItem(inv.getSize() - 1, gl2);
	}

	public void choicePlayer(Player p) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		Inventory cpinv = Bukkit.createInventory(null, 9 * 6, "§2Player Choice Menu");
		if (Data.async) {
			new BukkitRunnable() {

				@Override
				public void run() {
					int i = 0;
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (i != 45) {
							try {
								items.add(SetItems.buildSkull(all.getName(), "§2" + all.getName()));

							} catch (Exception e) {
								e.printStackTrace();
							}
							i++;
						}
					}

					new BukkitRunnable() {

						@Override
						public void run() {

							for (ItemStack it : items) {

								cpinv.addItem(it);

							}

						}
					}.runTask(Main.instance);

				}
			}.runTaskAsynchronously(Main.instance);
		} else {

			int i = 0;
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (i != 45) {
					try {
						cpinv.addItem(SetItems.buildSkull(all.getName(), "§2" + all.getName()));

					} catch (Exception e) {
						e.printStackTrace();
					}
					i++;
				}
			}
			
		}

		ItemStack page = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
		ItemMeta pagem = page.getItemMeta();
		pagem.setDisplayName("§cPage: 1");
		page.setItemMeta(pagem);

		ItemStack config = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
		ItemMeta configm = config.getItemMeta();
		configm.setDisplayName("§cConfiguration");
		config.setItemMeta(configm);

		ItemStack pagep = XMaterial.BLUE_STAINED_GLASS_PANE.parseItem();
		ItemMeta pagepm = pagep.getItemMeta();
		pagepm.setDisplayName("§bPrevious Page");
		pagep.setItemMeta(pagepm);

		ItemStack pagen = XMaterial.BLUE_STAINED_GLASS_PANE.parseItem();
		ItemMeta pagenm = pagen.getItemMeta();
		if (Bukkit.getOnlinePlayers().size() < 45) {
			pagenm.setDisplayName("§cNo Next Page");
		} else {
			pagenm.setDisplayName("§bNext Page");
		}
		pagen.setItemMeta(pagenm);

		ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
		ItemMeta glm = gl.getItemMeta();
		glm.setDisplayName("§7");
		gl.setItemMeta(glm);

		cpinv.setItem(45, page);
		cpinv.setItem(46, gl);
		cpinv.setItem(47, gl);
		cpinv.setItem(48, gl);
		cpinv.setItem(49, gl);
		cpinv.setItem(50, pagen);
		cpinv.setItem(51, gl);
		cpinv.setItem(52, gl);
		cpinv.setItem(53, config);

		p.openInventory(cpinv);
	}

	public void openServerInv(Player p) {
		Inventory inv2 = Bukkit.createInventory(null, 9 * 6, "§2Server Troll Menu");
		inv2.setItem(10,
				SetItems.buildItem(Items.getItem("gui.servertrolls.tpall"), XMaterial.ENDER_PEARL.parseMaterial()));
		inv2.setItem(11, SetItems.buildItem(Items.getItem("gui.servertrolls.hackmessage"),
				XMaterial.NETHER_STAR.parseMaterial()));
		inv2.setItem(12, SetItems.buildItem(Items.getItem("gui.servertrolls.fakeleave"),
				XMaterial.DARK_OAK_DOOR.parseMaterial()));

		p.openInventory(inv2);
	}

	public void openMLGchoiceInv(Player p) {
		Inventory inv4 = Bukkit.createInventory(null, 9, "§2Which MLG?");
		inv4.setItem(1, SetItems.buildItem(Items.getItem("gui.mlgs.cobweb"), XMaterial.COBWEB.parseMaterial()));
		inv4.setItem(3, SetItems.buildItem(Items.getItem("gui.mlgs.water"), XMaterial.WATER_BUCKET.parseMaterial()));
		inv4.setItem(5, SetItems.buildItem(Items.getItem("gui.mlgs.lava"), XMaterial.LAVA_BUCKET.parseMaterial()));
		inv4.setItem(7, SetItems.buildItem(Items.getItem("gui.mlgs.slime"), XMaterial.SLIME_BLOCK.parseMaterial()));
		inv4.setItem(8, SetItems.buildItem("§cBack", XMaterial.BARRIER.parseMaterial()));

		p.openInventory(inv4);
	}

	public void openItemInv(Player p) {
		ItemStack item = new ItemStack(XMaterial.DIAMOND_SWORD.parseMaterial());
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName(Items.getItem("gui.items.diamondsword"));
		imeta.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
		imeta.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
		item.setItemMeta(imeta);
		item.setAmount(1);

		ItemStack item2 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
		ItemMeta imeta2 = item2.getItemMeta();
		imeta2.setDisplayName(Items.getItem("gui.items.diamondprotectionchest"));
		imeta2.addEnchant(XEnchantment.PROTECTION_ENVIRONMENTAL.parseEnchantment(), 1000, true);
		item2.setItemMeta(imeta2);
		item2.setAmount(1);

		ItemStack item3 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
		ItemMeta imeta3 = item3.getItemMeta();
		imeta3.setDisplayName(Items.getItem("gui.items.diamondthornschest"));
		imeta3.addEnchant(XEnchantment.THORNS.parseEnchantment(), 1000, true);
		item3.setItemMeta(imeta3);
		item3.setAmount(1);

		ItemStack item4 = new ItemStack(XMaterial.DIAMOND_PICKAXE.parseMaterial());
		ItemMeta imeta4 = item4.getItemMeta();
		imeta4.setDisplayName(Items.getItem("gui.items.diamondpickaxe"));
		imeta4.addEnchant(XEnchantment.DIG_SPEED.parseEnchantment(), 1000, true);
		item4.setItemMeta(imeta4);
		item4.setAmount(1);

		ItemStack item5 = new ItemStack(XMaterial.BOW.parseMaterial());
		ItemMeta imeta5 = item5.getItemMeta();
		imeta5.setDisplayName(Items.getItem("gui.items.onehitbow"));
		imeta5.addEnchant(XEnchantment.ARROW_INFINITE.parseEnchantment(), 1000, true);
		imeta5.addEnchant(XEnchantment.ARROW_KNOCKBACK.parseEnchantment(), 1000, true);
		imeta5.addEnchant(XEnchantment.ARROW_DAMAGE.parseEnchantment(), 1000, true);
		item5.setItemMeta(imeta5);
		item5.setAmount(1);

		ItemStack item6 = new ItemStack(XMaterial.WOODEN_HOE.parseMaterial());
		ItemMeta imeta6 = item6.getItemMeta();
		imeta6.setDisplayName(Items.getItem("gui.items.woodenhoe"));
		imeta6.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
		imeta6.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
		item6.setItemMeta(imeta6);
		item6.setAmount(1);

		ItemStack item7 = new ItemStack(XMaterial.WOODEN_SWORD.parseMaterial());
		ItemMeta imeta7 = item7.getItemMeta();
		imeta7.setDisplayName(Items.getItem("gui.items.woodensword"));
		imeta7.addEnchant(XEnchantment.DAMAGE_ALL.parseEnchantment(), 1000, true);
		imeta7.addEnchant(XEnchantment.FIRE_ASPECT.parseEnchantment(), 1000, true);
		item7.setItemMeta(imeta7);
		item7.setAmount(1);

		ItemStack tntbow = createItem(XMaterial.BOW.parseMaterial(), 1, Items.getItem("gui.items.bow.tnt"),
				"§eTNT-Arrows");
		ItemStack lavabow = createItem(XMaterial.BOW.parseMaterial(), 1, Items.getItem("gui.items.bow.lava"),
				"§eLava-Arrows");
		ItemStack strikebow = createItem(XMaterial.BOW.parseMaterial(), 1, Items.getItem("gui.items.bow.lightning"),
				"§eLightning-Arrows");
		ItemStack creeperbow = createItem(XMaterial.BOW.parseMaterial(), 1, Items.getItem("gui.items.bow.creeper"),
				"§eCreeper-Arrows");
		ItemStack bedrockbow = createItem(XMaterial.BOW.parseMaterial(), 1, Items.getItem("gui.items.bow.bedrock"),
				"§eBedrock-Arrows");

		ItemStack minigun = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
		ItemMeta meta = minigun.getItemMeta();
		meta.setDisplayName(Items.getItem("gui.items.minigun"));
		minigun.setItemMeta(meta);

		ItemStack fireball = new ItemStack(XMaterial.STICK.parseMaterial());
		ItemMeta fmeta = fireball.getItemMeta();
		fmeta.setDisplayName(Items.getItem("gui.items.fireball"));
		fireball.setItemMeta(fmeta);

		Inventory inv3 = Bukkit.createInventory(null, 9 * 6, "§2Item Troll Menu");
		inv3.setItem(10, item);
		inv3.setItem(12, item2);
		inv3.setItem(14, item3);
		inv3.setItem(16, item4);
		inv3.setItem(18, item5);
		inv3.setItem(20, item6);
		inv3.setItem(22, item7);
		inv3.setItem(24, tntbow);
		inv3.setItem(26, lavabow);
		inv3.setItem(28, strikebow);
		inv3.setItem(30, creeperbow);
		inv3.setItem(32, bedrockbow);
		inv3.setItem(34, minigun);
		inv3.setItem(36, fireball);

		p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 3.0F, 2.0F);
		p.openInventory(inv3);
	}

}
