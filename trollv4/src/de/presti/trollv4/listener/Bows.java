package de.presti.trollv4.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;

import de.presti.trollv4.utils.Config;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 04.02.2019 / 18:03:20												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Bows implements Listener {
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

	@EventHandler
	public void onArrowHit(ProjectileHitEvent e) {
		try {
			if (((e.getEntity() instanceof Arrow)) && ((e.getEntity().getShooter() instanceof Player))) {
				Player shooter = (Player) e.getEntity().getShooter();
				World world = e.getEntity().getWorld();
				Location loc = e.getEntity().getLocation();
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§4TNTBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.createExplosion(loc, 2.0F);
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§cLavaBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.getBlockAt(loc).setType(XMaterial.LAVA.parseMaterial());
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§bBlitzBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.strikeLightning(loc);
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§2CreeperBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.spawnEntity(loc, EntityType.CREEPER);
					}
				}
				if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§0BedrockBow")) {
					if (shooter.hasPermission("troll.bows")) {
						world.getBlockAt(loc).setType(XMaterial.BEDROCK.parseMaterial());
					}
				}
			}
		} catch (Exception e1) {
			System.out.println("Couldnt Spawn! Pls Report: " + e1.getMessage());
		}
	}
}
