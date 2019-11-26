package de.presti.trollv4.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.ArrayUtils;

public class LagL implements Listener{

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();
        if(ArrayUtils.lagging.contains(player)){
            Material material = e.getBlockPlaced().getType();
            Location location = e.getBlockPlaced().getLocation();
            Bukkit.getWorld(location.getWorld().getName()).getBlockAt(location).setType(Material.AIR);
            Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
                @Override
                public void run() {
                    Bukkit.getWorld(location.getWorld().getName()).getBlockAt(location).setType(material);
                }
            }, 34);
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if(ArrayUtils.lagging.contains(player)){
            Material drops = e.getBlock().getType();
            e.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
                @Override
                public void run() {
                    Bukkit.getWorld(e.getBlock().getLocation().getWorld().getName()).getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
                    Bukkit.getWorld(e.getBlock().getLocation().getWorld().getName()).dropItem(e.getBlock().getLocation(), new ItemStack(drops));
                }
            }, 31);
        }
    }

}
