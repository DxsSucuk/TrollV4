package de.presti.trollv4.invs;

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
		List<String> lorer = new ArrayList();
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
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE);
		List<String> lore = new ArrayList();
		ItemMeta m = glass.getItemMeta();
		
		m.setDisplayName("§8§kddd §c§lLoading... §8§kddd");
		lore.clear();
		lore.add("§8§kddd §c§lStill Loading... §8§kddd");
		m.setLore(lore);
		glass.setItemMeta(m);
		
		return glass;
	}
	
	public static ItemStack createItem(Material mat, int amount, String displayname, String lore)
	  {
	    List<String> list = new ArrayList();
	    ItemStack item = new ItemStack(mat, amount);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(displayname);
	    if (lore != null)
	    {
	      list.add(lore);
	      meta.setLore(list);
	    }
	    item.setItemMeta(meta);
	    return item;
	  }
	
	public void openPlayerInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§2Player Troll Menu");
		if(Config.getconfig().getBoolean("Animations")) {
			ArrayUtils.anim.put(p, new BukkitRunnable() {
				int countdown = 15;
				@Override
				public void run() {
					
					if(countdown == 0) {
						inv.clear();
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ZOMBIE_INFECT, 1F, 1F);
						}
						inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()), "§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));
						if(inv.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase("§2Youre Trolling §cnull")) {
						inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()), "§cERROR PLS REOPEN INV"));
						} else {
							inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()), "§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));	
						}
						inv.setItem(10, SetItems.buildItem("§bUn/Freeze", Material.ICE));
						inv.setItem(11, SetItems.buildItem("§7FakeOP", Material.GOLDEN_APPLE));
						inv.setItem(12, SetItems.buildItem("§cCrash", Material.PAPER));
						inv.setItem(13, SetItems.buildItemCodes("§aStartControl", Material.WOOL, 1, (short)13));
						inv.setItem(14, SetItems.buildItem("§cSpam", Material.ARROW));
						inv.setItem(15, SetItems.buildItem("§cMLG", Material.WATER_BUCKET));
						inv.setItem(16, SetItems.buildItem("§cRocket", Material.FIREWORK));
						inv.setItem(19, SetItems.buildItem("§8Hack User", Material.NETHER_STAR));
						inv.setItem(20, SetItems.buildItem("§9Strike", Material.BAKED_POTATO));
						inv.setItem(21, SetItems.buildItem("§6Demo", Material.BEDROCK));
						inv.setItem(22, SetItems.buildItem("§cExplode", Material.TNT));
						inv.setItem(23, SetItems.buildItem("§aFakeHack", Material.DIAMOND_SWORD));
						inv.setItem(24, SetItems.buildItem("§bAntiCheat", Material.IRON_AXE));
						inv.setItem(25, SetItems.buildItem("§c§kd§cL§kd§ca§kd§cg§kd§cg§kd§ci§kd§cn§kd§cg§c§kd", Material.GRASS));
						inv.setItem(28, SetItems.buildItem("§cARREST", Material.BEDROCK)); 
						inv.setItem(29, SetItems.buildItem("§bRotate Player", Material.COOKIE));
						inv.setItem(30, SetItems.buildItem("§cRandom Teleport", Material.COMMAND));
						inv.setItem(31, SetItems.buildItem("§cTnT Trace", Material.DIAMOND_BOOTS));
						inv.setItem(32, SetItems.buildItem("§fWeb §8Trap", Material.WEB));
						inv.setItem(33, SetItems.buildItem("§cWTF", Material.RECORD_11));
						inv.setItem(34, SetItems.buildItem("§cL§5S§bD", Material.RED_MUSHROOM));
						inv.setItem(37, SetItems.buildItem("§1Guardian", Material.BLAZE_ROD));
				//		inv.setItem(38, SetItems.buildItem("§7EndScreen", Material.DRAGON_EGG));
						Bukkit.getScheduler().cancelTask(ArrayUtils.anim.get(p).getTaskId());
						return;
					}
					inv.setItem(22, ad());
					if(countdown == 15) {
						inv.setItem(0, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(1, Glass());
					}
					if(countdown == 14) {
						inv.setItem(2, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(3, Glass());
					}
					if(countdown == 13) {
						inv.setItem(4, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(5, Glass());
					}
					if(countdown == 12) {
						inv.setItem(6, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(7, Glass());
					}
					if(countdown == 11) {
						inv.setItem(8, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(17, Glass());
					}
					if(countdown == 10) {
						inv.setItem(26, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(35, Glass());
					}
					if(countdown == 9) {
					    inv.setItem(44, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(53, Glass());
					}
					if(countdown == 8) {
						inv.setItem(52, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(51, Glass());
					}
					if(countdown == 7) {
						inv.setItem(50, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(49, Glass());
					}
					if(countdown == 6) {
						inv.setItem(48, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(47, Glass());
					}
					if(countdown == 5) {
						inv.setItem(46, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(45, Glass());

					}
					if(countdown == 4) {
						inv.setItem(36, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(27, Glass());
					}
					if(countdown == 3) {
						inv.setItem(18, Glass());
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.ARROW_HIT, 1F, 1F);
						}
						inv.setItem(9, Glass());
					}
					if(countdown == 2) {
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
						}
					}
					if(countdown == 1) {
						if(Main.version.equalsIgnoreCase("v1_8_R3") || Main.version.equalsIgnoreCase("v1_9_R2")) {
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
						}
					}
					if(countdown <= 0) {
						Bukkit.getScheduler().cancelTask(ArrayUtils.anim.get(p).getTaskId());
					}
					countdown--;
				}
			});
			ArrayUtils.anim.get(p).runTaskTimer(Main.instance, 0L, 20L);
			
		} else {
			inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()), "§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));
			if(inv.getItem(0).getItemMeta().getDisplayName().equalsIgnoreCase("§2Youre Trolling §cnull")) {
			inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()), "§cERROR PLS REOPEN INV"));
			} else {
				inv.setItem(0, SetItems.buildSkull(ArrayUtils.trolling.get(p.getName()), "§2Youre Trolling §c" + ArrayUtils.trolling.get(p.getName())));	
			}
			inv.setItem(10, SetItems.buildItem("§bUn/Freeze", Material.ICE));
			inv.setItem(11, SetItems.buildItem("§7FakeOP", Material.GOLDEN_APPLE));
			inv.setItem(12, SetItems.buildItem("§cCrash", Material.PAPER));
			inv.setItem(13, SetItems.buildItemCodes("§aStartControl", Material.WOOL, 1, (short)13));
			inv.setItem(14, SetItems.buildItem("§cSpam", Material.ARROW));
			inv.setItem(15, SetItems.buildItem("§cMLG", Material.WATER_BUCKET));
			inv.setItem(16, SetItems.buildItem("§cRocket", Material.FIREWORK));
			inv.setItem(19, SetItems.buildItem("§8Hack User", Material.NETHER_STAR));
			inv.setItem(20, SetItems.buildItem("§9Strike", Material.BAKED_POTATO));
			inv.setItem(21, SetItems.buildItem("§6Demo", Material.BEDROCK));
			inv.setItem(22, SetItems.buildItem("§cExplode", Material.TNT));
			inv.setItem(23, SetItems.buildItem("§aFakeHack", Material.DIAMOND_SWORD));
			inv.setItem(24, SetItems.buildItem("§bAntiCheat", Material.IRON_AXE));
			inv.setItem(25, SetItems.buildItem("§c§kd§cL§kd§ca§kd§cg§kd§cg§kd§ci§kd§cn§kd§cg§c§kd", Material.GRASS));
			inv.setItem(28, SetItems.buildItem("§cARREST", Material.IRON_FENCE));
			inv.setItem(29, SetItems.buildItem("§bRotate Player", Material.COOKIE));
			inv.setItem(30, SetItems.buildItem("§cRandom Teleport", Material.COMMAND));
			inv.setItem(31, SetItems.buildItem("§cTnT Trace", Material.DIAMOND_BOOTS));
			inv.setItem(32, SetItems.buildItem("§fWeb §8Trap", Material.WEB));
			inv.setItem(33, SetItems.buildItem("§cWTF", Material.RECORD_11));
			inv.setItem(34, SetItems.buildItem("§cL§5S§bD", Material.RED_MUSHROOM));
			inv.setItem(37, SetItems.buildItem("§1Guardian", Material.BLAZE_ROD));
	//		inv.setItem(38, SetItems.buildItem("§7EndScreen", Material.DRAGON_EGG));
		}
		
		p.openInventory(inv);
	}
	public void choicePlayer(Player p) {
		Inventory cpinv = Bukkit.createInventory(null, 9 * 6, "§2Player Choice Menu");
		for(Player all : Bukkit.getOnlinePlayers()) {
		cpinv.addItem(SetItems.buildSkull(all.getName(), "§2" + all.getName()));
		}
		
		p.openInventory(cpinv);
	}
	public void openServerInv(Player p) {
		Inventory inv2 = Bukkit.createInventory(null, 9 * 6, "§2Server Troll Menu");
		inv2.setItem(10, SetItems.buildItem("§6Tpall", Material.ENDER_PEARL));
		inv2.setItem(11, SetItems.buildItem("§8Hack Message", Material.NETHER_STAR));
		inv2.setItem(12, SetItems.buildItem("§2Fakeleave", Material.DARK_OAK_DOOR_ITEM));
		
		
		p.openInventory(inv2);
	}
	public void openMLGchoiceInv(Player p) {
		Inventory inv4 = Bukkit.createInventory(null, 9, "§2Which MLG?");
		inv4.setItem(1, SetItems.buildItem("§cCobweb MLG", Material.WEB));
		inv4.setItem(3, SetItems.buildItem("§bWater MLG", Material.WATER_BUCKET));
		inv4.setItem(5, SetItems.buildItem("§6Lava MLG", Material.LAVA_BUCKET));
		inv4.setItem(7, SetItems.buildItem("§aSlime Block MLG", Material.SLIME_BLOCK));
		inv4.setItem(8, SetItems.buildItem("§cZurück", Material.BARRIER));
		
		p.openInventory(inv4);
	}
	public void openItemInv(Player p) {
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta imeta = item.getItemMeta();
		imeta.setDisplayName("§cOP§8-§bDiamond§8-§rSWORD");
		imeta.addEnchant(Enchantment.DAMAGE_ALL, 1000, true);
		imeta.addEnchant(Enchantment.FIRE_ASPECT, 1000, true);
		item.setItemMeta(imeta);
		item.setAmount(1);
		
		ItemStack item2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta imeta2 = item2.getItemMeta();
		imeta2.setDisplayName("§cOP§8-§bDiamond§8-§3Protection§8-§rCHESTPLATE");
		imeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1000, true);
		item2.setItemMeta(imeta2);
		item2.setAmount(1);
		
		ItemStack item3 = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta imeta3 = item3.getItemMeta();
		imeta3.setDisplayName("§cOP§8-§bDiamond§8-§3Thorns§8-§rCHESTPLATE-§32");
		imeta3.addEnchant(Enchantment.THORNS, 1000, true);
		item3.setItemMeta(imeta3);
		item3.setAmount(1);
		
		ItemStack item4 = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta imeta4 = item4.getItemMeta();
        imeta4.setDisplayName("§cOP§8-§bDiamond§8-§rPICKAXE");
        imeta4.addEnchant(Enchantment.DIG_SPEED, 1000, true);
        item4.setItemMeta(imeta4);
        item4.setAmount(1);
          
        ItemStack item5 = new ItemStack(Material.BOW);
        ItemMeta imeta5 = item5.getItemMeta();
        imeta5.setDisplayName("§cOP§8-§rBOW");
        imeta5.addEnchant(Enchantment.ARROW_INFINITE, 1000, true);
        imeta5.addEnchant(Enchantment.ARROW_KNOCKBACK, 1000, true);
        imeta5.addEnchant(Enchantment.ARROW_DAMAGE, 1000, true);
        item5.setItemMeta(imeta5);
        item5.setAmount(1);
        
        ItemStack item6 = new ItemStack(Material.WOOD_HOE);
        ItemMeta imeta6 = item6.getItemMeta();
        imeta6.setDisplayName("§cOP§8-§6Wood§8-§rHOE");
        imeta6.addEnchant(Enchantment.DAMAGE_ALL, 1000, true);
		imeta6.addEnchant(Enchantment.FIRE_ASPECT, 1000, true);
        item6.setItemMeta(imeta6);
        item6.setAmount(1);
        
        ItemStack item7 = new ItemStack(Material.WOOD_SWORD);
        ItemMeta imeta7 = item7.getItemMeta();
        imeta7.setDisplayName("§cOP§8-§6Wood§8-§rSWORD");
        imeta7.addEnchant(Enchantment.DAMAGE_ALL, 1000, true);
		imeta7.addEnchant(Enchantment.FIRE_ASPECT, 1000, true);
        item7.setItemMeta(imeta7);
        item7.setAmount(1);
        
        ItemStack tntbow = createItem(Material.BOW, 1, "§4TNTBow", "§eTNT-Arrows");
        ItemStack lavabow = createItem(Material.BOW, 1, "§cLavaBow", "§eLava-Arrows");
        ItemStack strikebow = createItem(Material.BOW, 1, "§bBlitzBow", "§eBlitz-Arrows");
        ItemStack creeperbow = createItem(Material.BOW, 1, "§2CreeperBow", "§eCreeper-Arrows");
        ItemStack bedrockbow = createItem(Material.BOW, 1, "§0BedrockBow", "§eBedrock-Arrows");
        
        ItemStack minigun = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = minigun.getItemMeta();
        meta.setDisplayName("§4MiniGun");
        minigun.setItemMeta(meta);
        
        ItemStack fireball = new ItemStack(Material.STICK);
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
		
		
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
		p.openInventory(inv3);
	}
	

}
