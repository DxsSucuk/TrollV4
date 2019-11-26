package de.presti.trollv4.listener;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

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
public class Bows
  implements Listener
{
  @EventHandler
  public void onInteract(InventoryClickEvent e)
  {
    try
    {
      Player p = (Player)e.getWhoClicked();
      ItemStack tntbow = new ItemStack(Material.BOW);
      ItemMeta meta = tntbow.getItemMeta();
      meta.setDisplayName("§4TNTBow");
      tntbow.setItemMeta(meta);
      
      ItemStack lavabow = new ItemStack(Material.BOW);
      ItemMeta meta1 = lavabow.getItemMeta();
      meta1.setDisplayName("§cLavaBow");
      lavabow.setItemMeta(meta1);
      
      ItemStack blitzbow = new ItemStack(Material.BOW);
      ItemMeta meta2 = blitzbow.getItemMeta();
      meta2.setDisplayName("§bBlitzBow");
      blitzbow.setItemMeta(meta2);
      
      ItemStack creeperbow = new ItemStack(Material.BOW);
      ItemMeta meta3 = creeperbow.getItemMeta();
      meta3.setDisplayName("§2CreeperBow");
      creeperbow.setItemMeta(meta3);
      
      ItemStack bedrockbow = new ItemStack(Material.BOW);
      ItemMeta meta4 = bedrockbow.getItemMeta();
      meta4.setDisplayName("§0BedrockBow");
      bedrockbow.setItemMeta(meta4);
      
      ItemStack fireball = new ItemStack(Material.STICK);
      ItemMeta fmeta = fireball.getItemMeta();
      fmeta.setDisplayName("§4FireBall");
      fireball.setItemMeta(fmeta);
      
      ItemStack minigun = new ItemStack(Material.IRON_AXE);
      ItemMeta mmeta = minigun.getItemMeta();
      mmeta.setDisplayName("§4MiniGun");
      minigun.setItemMeta(mmeta);
      
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
      if (e.getView().getTitle().equalsIgnoreCase("§2Item Troll Menu"))
      {
        e.setCancelled(true);
        if (p.hasPermission("troll.bows")) {
          if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4TNTBow"))
          {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { tntbow });
            p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4FireBall")) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { fireball });
            p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4MiniGun")) {
        	p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
        	p.getInventory().addItem(new ItemStack[] { minigun });
        	p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cOP§8-§bDiamond§8-§rSWORD")) {
          	p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
          	p.getInventory().addItem(new ItemStack[] { item });
          	p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cOP§8-§bDiamond§8-§3Protection§8-§rCHESTPLATE")) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { item2 });
            p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cOP§8-§bDiamond§8-§3Thorns§8-§rCHESTPLATE-§32")) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { item3 });
            p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(item4.getItemMeta().getDisplayName())) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { item4 });
            p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(item5.getItemMeta().getDisplayName())) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { item5 });
            p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(item6.getItemMeta().getDisplayName())) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { item6 });
            p.closeInventory();
          }
          else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(item7.getItemMeta().getDisplayName())) {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { item7 });
            p.closeInventory();
          }
          else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cLavaBow"))
          {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { lavabow });
            p.closeInventory();
          }
          else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bBlitzBow"))
          {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { blitzbow });
            p.closeInventory();
          } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2CreeperBow"))
          {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { creeperbow });
            p.closeInventory();
          } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§0BedrockBow"))
          {
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 3.0F, 2.0F);
            p.getInventory().addItem(new ItemStack[] { bedrockbow });
            p.closeInventory();
          }
        }
      }
    }
    catch (Exception localException) {}
  }
  
  @EventHandler
  public void onArrowHit(ProjectileHitEvent e)
  {
    try
    {
      if (((e.getEntity() instanceof Arrow)) && 
        ((e.getEntity().getShooter() instanceof Player)))
      {
        Player shooter = (Player)e.getEntity().getShooter();
        World world = e.getEntity().getWorld();
        Location loc = e.getEntity().getLocation();
        if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§4TNTBow")) {
          if (shooter.hasPermission("troll.bows")) {
            world.createExplosion(loc, 2.0F);
          }
        }
        if (shooter.getItemInHand().getItemMeta().getDisplayName().equals("§cLavaBow")) {
          if (shooter.hasPermission("troll.bows")) {
            world.getBlockAt(loc).setType(Material.LAVA);
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
              world.getBlockAt(loc).setType(Material.BEDROCK);
            }
          }
      }
    }
    catch (Exception localException) {}
  }
}

