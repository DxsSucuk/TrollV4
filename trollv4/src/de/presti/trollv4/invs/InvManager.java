package de.presti.trollv4.invs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.ArrayUtils;
import de.presti.trollv4.utils.Config;
import de.presti.trollv4.utils.SetItems;

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
		ItemStack red = new ItemStack(Material.REDSTONE);
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

	public void openPlayerInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§2Player Troll Menu");
		if (Config.getconfig().getBoolean("Animations")) {
			ArrayUtils.anim.put(p, new BukkitRunnable() {
				int countdown = 15;

				@Override
				public void run() {

					if (countdown == 0) {
						inv.clear();
						p.playSound(p.getLocation(), XSound.ENTITY_ZOMBIE_INFECT.parseSound(), 1F, 1F);
						try {
							inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
									"§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (inv.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase("§2Youre Trolling §cnull")) {
							try {
								inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
										"§cERROR PLS REOPEN INV"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							try {
								inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
										"§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						inv.setItem(10, SetItems.buildItem("§bUn/Freeze", XMaterial.ICE.parseMaterial()));
						inv.setItem(11, SetItems.buildItem("§7FakeOP", XMaterial.GOLDEN_APPLE.parseMaterial()));
						inv.setItem(12, SetItems.buildItem("§cCrash", XMaterial.PAPER.parseMaterial()));
						inv.setItem(13, SetItems.buildItem("§aStartControl", XMaterial.GREEN_WOOL.parseMaterial()));
						inv.setItem(14, SetItems.buildItem("§cSpam", XMaterial.ARROW.parseMaterial()));
						inv.setItem(15, SetItems.buildItem("§cMLG", XMaterial.WATER_BUCKET.parseMaterial()));
						inv.setItem(16, SetItems.buildItem("§cRocket", XMaterial.FIREWORK_ROCKET.parseMaterial()));
						inv.setItem(19, SetItems.buildItem("§8Hack User", XMaterial.NETHER_STAR.parseMaterial()));
						inv.setItem(20, SetItems.buildItem("§9Strike", XMaterial.BAKED_POTATO.parseMaterial()));
						inv.setItem(21, SetItems.buildItem("§6Demo", XMaterial.BEDROCK.parseMaterial()));
						inv.setItem(22, SetItems.buildItem("§cExplode", XMaterial.TNT.parseMaterial()));
						inv.setItem(23, SetItems.buildItem("§aFakeHack", XMaterial.DIAMOND_SWORD.parseMaterial()));
						inv.setItem(24, SetItems.buildItem("§bAntiCheat", XMaterial.IRON_AXE.parseMaterial()));
						inv.setItem(25, SetItems.buildItem("§c§kd§cL§kd§ca§kd§cg§kd§cg§kd§ci§kd§cn§kd§cg§c§kd",
								XMaterial.GRASS.parseMaterial()));
						inv.setItem(28, SetItems.buildItem("§cARREST", XMaterial.BEDROCK.parseMaterial()));
						inv.setItem(29, SetItems.buildItem("§bRotate Player", XMaterial.COOKIE.parseMaterial()));
						inv.setItem(30,
								SetItems.buildItem("§cRandom Teleport", XMaterial.COMMAND_BLOCK.parseMaterial()));
						inv.setItem(31, SetItems.buildItem("§cTnT Trace", XMaterial.DIAMOND_BOOTS.parseMaterial()));
						inv.setItem(32, SetItems.buildItem("§fWeb §8Trap", XMaterial.COBWEB.parseMaterial()));
						inv.setItem(33, SetItems.buildItem("§cWTF", XMaterial.MUSIC_DISC_11.parseMaterial()));
						inv.setItem(34, SetItems.buildItem("§cL§5S§bD", XMaterial.RED_MUSHROOM.parseMaterial()));
						inv.setItem(37, SetItems.buildItem("§1Guardian", XMaterial.BLAZE_ROD.parseMaterial()));
						inv.setItem(38, SetItems.buildItem("§3Arrow Spam", XMaterial.BOW.parseMaterial()));
						try {
							inv.setItem(39, SetItems.buildSkull("Herobrine", "§9Herobrine"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						inv.setItem(40, SetItems.buildItem("§7Tornado", XMaterial.WHITE_WOOL.parseMaterial()));
						// inv.setItem(38, SetItems.buildItem("§7EndScreen", Material.DRAGON_EGG));
						Bukkit.getScheduler().cancelTask(ArrayUtils.anim.get(p).getTaskId());
						return;
					}
					inv.setItem(22, ad());
					if (countdown == 15) {
						inv.setItem(0, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(1, Glass());
					}
					if (countdown == 14) {
						inv.setItem(2, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(3, Glass());
					}
					if (countdown == 13) {
						inv.setItem(4, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(5, Glass());
					}
					if (countdown == 12) {
						inv.setItem(6, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(7, Glass());
					}
					if (countdown == 11) {
						inv.setItem(8, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(17, Glass());
					}
					if (countdown == 10) {
						inv.setItem(26, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(35, Glass());
					}
					if (countdown == 9) {
						inv.setItem(44, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(53, Glass());
					}
					if (countdown == 8) {
						inv.setItem(52, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(51, Glass());
					}
					if (countdown == 7) {
						inv.setItem(50, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(49, Glass());
					}
					if (countdown == 6) {
						inv.setItem(48, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(47, Glass());
					}
					if (countdown == 5) {
						inv.setItem(46, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(45, Glass());

					}
					if (countdown == 4) {
						inv.setItem(36, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(27, Glass());
					}
					if (countdown == 3) {
						inv.setItem(18, Glass());
						p.playSound(p.getLocation(), XSound.ENTITY_ARROW_HIT.parseSound(), 1F, 1F);
						inv.setItem(9, Glass());
					}
					if (countdown == 2) {
						p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 1F, 1F);
					}
					if (countdown == 1) {
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
			try {
				inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
						"§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (inv.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase("§2Youre Trolling §cnull")) {
				try {
					inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()), "§cERROR PLS REOPEN INV"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()),
							"§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			inv.setItem(10, SetItems.buildItem("§bUn/Freeze", XMaterial.ICE.parseMaterial()));
			inv.setItem(11, SetItems.buildItem("§7FakeOP", XMaterial.GOLDEN_APPLE.parseMaterial()));
			inv.setItem(12, SetItems.buildItem("§cCrash", XMaterial.PAPER.parseMaterial()));
			inv.setItem(13, SetItems.buildItem("§aStartControl", XMaterial.GREEN_WOOL.parseMaterial()));
			inv.setItem(14, SetItems.buildItem("§cSpam", XMaterial.ARROW.parseMaterial()));
			inv.setItem(15, SetItems.buildItem("§cMLG", XMaterial.WATER_BUCKET.parseMaterial()));
			inv.setItem(16, SetItems.buildItem("§cRocket", XMaterial.FIREWORK_ROCKET.parseMaterial()));
			inv.setItem(19, SetItems.buildItem("§8Hack User", XMaterial.NETHER_STAR.parseMaterial()));
			inv.setItem(20, SetItems.buildItem("§9Strike", XMaterial.BAKED_POTATO.parseMaterial()));
			inv.setItem(21, SetItems.buildItem("§6Demo", XMaterial.BEDROCK.parseMaterial()));
			inv.setItem(22, SetItems.buildItem("§cExplode", XMaterial.TNT.parseMaterial()));
			inv.setItem(23, SetItems.buildItem("§aFakeHack", XMaterial.DIAMOND_SWORD.parseMaterial()));
			inv.setItem(24, SetItems.buildItem("§bAntiCheat", XMaterial.IRON_AXE.parseMaterial()));
			inv.setItem(25, SetItems.buildItem("§c§kd§cL§kd§ca§kd§cg§kd§cg§kd§ci§kd§cn§kd§cg§c§kd",
					XMaterial.GRASS.parseMaterial()));
			inv.setItem(28, SetItems.buildItem("§cARREST", XMaterial.BEDROCK.parseMaterial()));
			inv.setItem(29, SetItems.buildItem("§bRotate Player", XMaterial.COOKIE.parseMaterial()));
			inv.setItem(30, SetItems.buildItem("§cRandom Teleport", XMaterial.COMMAND_BLOCK.parseMaterial()));
			inv.setItem(31, SetItems.buildItem("§cTnT Trace", XMaterial.DIAMOND_BOOTS.parseMaterial()));
			inv.setItem(32, SetItems.buildItem("§fWeb §8Trap", XMaterial.COBWEB.parseMaterial()));
			inv.setItem(33, SetItems.buildItem("§cWTF", XMaterial.MUSIC_DISC_11.parseMaterial()));
			inv.setItem(34, SetItems.buildItem("§cL§5S§bD", XMaterial.RED_MUSHROOM.parseMaterial()));
			inv.setItem(37, SetItems.buildItem("§1Guardian", XMaterial.BLAZE_ROD.parseMaterial()));
			inv.setItem(38, SetItems.buildItem("§3Arrow Spam", XMaterial.BOW.parseMaterial()));
			try {
				inv.setItem(39, SetItems.buildSkull("Herobrine", "§9Herobrine"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			inv.setItem(40, SetItems.buildItem("§7Tornado", XMaterial.WHITE_WOOL.parseMaterial()));
		}

		p.openInventory(inv);
	}

	public void choicePlayer(Player p) {
		Inventory cpinv = Bukkit.createInventory(null, 9 * 6, "§2Player Choice Menu");
		for (Player all : Bukkit.getOnlinePlayers()) {
			try {
				cpinv.addItem(SetItems.buildSkull(all.getName(), "§2" + all.getName()));
			} catch (IllegalArgumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		p.openInventory(cpinv);
	}

	public void openServerInv(Player p) {
		Inventory inv2 = Bukkit.createInventory(null, 9 * 6, "§2Server Troll Menu");
		inv2.setItem(10, SetItems.buildItem("§6Tpall", XMaterial.ENDER_PEARL.parseMaterial()));
		inv2.setItem(11, SetItems.buildItem("§8Hack Message", XMaterial.NETHER_STAR.parseMaterial()));
		inv2.setItem(12, SetItems.buildItem("§2Fakeleave", XMaterial.DARK_OAK_DOOR.parseMaterial()));

		p.openInventory(inv2);
	}

	public void openMLGchoiceInv(Player p) {
		Inventory inv4 = Bukkit.createInventory(null, 9, "§2Which MLG?");
		inv4.setItem(1, SetItems.buildItem("§cCobweb MLG", XMaterial.COBWEB.parseMaterial()));
		inv4.setItem(3, SetItems.buildItem("§bWater MLG", XMaterial.WATER_BUCKET.parseMaterial()));
		inv4.setItem(5, SetItems.buildItem("§6Lava MLG", XMaterial.LAVA_BUCKET.parseMaterial()));
		inv4.setItem(7, SetItems.buildItem("§aSlime Block MLG", XMaterial.SLIME_BLOCK.parseMaterial()));
		inv4.setItem(8, SetItems.buildItem("§cBack", XMaterial.BARRIER.parseMaterial()));

		p.openInventory(inv4);
	}

	public void openItemInv(Player p) {
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

		ItemStack tntbow = createItem(XMaterial.BOW.parseMaterial(), 1, "§4TNTBow", "§eTNT-Arrows");
		ItemStack lavabow = createItem(XMaterial.BOW.parseMaterial(), 1, "§cLavaBow", "§eLava-Arrows");
		ItemStack strikebow = createItem(XMaterial.BOW.parseMaterial(), 1, "§bBlitzBow", "§eBlitz-Arrows");
		ItemStack creeperbow = createItem(XMaterial.BOW.parseMaterial(), 1, "§2CreeperBow", "§eCreeper-Arrows");
		ItemStack bedrockbow = createItem(XMaterial.BOW.parseMaterial(), 1, "§0BedrockBow", "§eBedrock-Arrows");

		ItemStack minigun = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
		ItemMeta meta = minigun.getItemMeta();
		meta.setDisplayName("§4MiniGun");
		minigun.setItemMeta(meta);

		ItemStack fireball = new ItemStack(XMaterial.STICK.parseMaterial());
		ItemMeta fmeta = fireball.getItemMeta();
		fmeta.setDisplayName("§4FireBall");
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
