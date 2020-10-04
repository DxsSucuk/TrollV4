package de.presti.trollv4.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.cryptomorin.xseries.XMaterial;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;

public class CommandGUI implements Listener {
	String cmd;
	Player c;
	Player v;
	public Inventory gui;
	Main plugin;

	public CommandGUI(String command, Player cont, Player vict, Main cl) {
		cmd = command;
		c = cont;
		v = vict;
		plugin = cl;
		if (Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			gui = Bukkit.createInventory(null, 9, "§8§ki §8Command Ausführen §ki");

			ItemStack rat = new ItemStack(XMaterial.BARRIER.parseItem());
			ItemMeta im = rat.getItemMeta();
			im.setDisplayName("§c§lAls " + v.getName() + " ausführen");
			List<String> lore = new ArrayList<String>();
			lore.add("§7Als Kontrollierter Spieler ausführen");
			lore.add("§7Command: §6" + cmd);
			im.setLore(lore);
			rat.setItemMeta(im);

			ItemStack stop = new ItemStack(XMaterial.RED_WOOL.parseItem());
			ItemMeta stopm = stop.getItemMeta();
			stopm.setDisplayName("§cStopControl");
			stop.setItemMeta(stopm);

			ItemStack raa = new ItemStack(XMaterial.ARROW.parseItem());
			ItemMeta im2 = raa.getItemMeta();
			im2.setDisplayName("§c§lAls " + c.getName() + " ausführen");
			List<String> lore2 = new ArrayList<String>();
			lore2.add("§7Als du selbst ausführen");
			lore2.add("§7Command: §6" + cmd);
			im2.setLore(lore2);
			raa.setItemMeta(im2);

			gui.setItem(2, rat);
			gui.setItem(6, raa);
			gui.setItem(8, stop);

			c.openInventory(gui);
		} else if (Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			gui = Bukkit.createInventory(null, 9, "§8§ki §8Run Command §ki");

			ItemStack rat = new ItemStack(XMaterial.BARRIER.parseItem());
			ItemMeta im = rat.getItemMeta();
			im.setDisplayName("§c§lRun as " + v.getName());
			List<String> lore = new ArrayList<String>();
			lore.add("§7Runs the command as your target");
			lore.add("§7Command: §6" + cmd);
			im.setLore(lore);
			rat.setItemMeta(im);

			ItemStack stop = new ItemStack(XMaterial.RED_WOOL.parseItem());
			ItemMeta stopm = stop.getItemMeta();
			stopm.setDisplayName("§cStopControl");
			stop.setItemMeta(stopm);

			ItemStack raa = new ItemStack(XMaterial.ARROW.parseItem());
			ItemMeta im2 = raa.getItemMeta();
			im2.setDisplayName("§c§lRun as " + c.getName());
			List<String> lore2 = new ArrayList<String>();
			lore2.add("§7Run the command as yourself");
			lore2.add("§7Command: §6" + cmd);
			im2.setLore(lore2);
			raa.setItemMeta(im2);

			gui.setItem(2, rat);
			gui.setItem(6, raa);
			gui.setItem(8, stop);

			c.openInventory(gui);
		} else {
			gui = Bukkit.createInventory(null, 9, "§8§ki §8Run Command §ki");

			ItemStack rat = new ItemStack(XMaterial.BARRIER.parseItem());
			ItemMeta im = rat.getItemMeta();
			im.setDisplayName("§c§lRun as " + v.getName());
			List<String> lore = new ArrayList<String>();
			lore.add("§7Runs the command as your target");
			lore.add("§7Command: §6" + cmd);
			im.setLore(lore);
			rat.setItemMeta(im);

			ItemStack stop = new ItemStack(XMaterial.RED_WOOL.parseItem());
			ItemMeta stopm = stop.getItemMeta();
			stopm.setDisplayName("§cStopControl");
			stop.setItemMeta(stopm);

			ItemStack raa = new ItemStack(XMaterial.ARROW.parseItem());
			ItemMeta im2 = raa.getItemMeta();
			im2.setDisplayName("§c§lRun as " + c.getName());
			List<String> lore2 = new ArrayList<String>();
			lore2.add("§7Run the command as yourself");
			lore2.add("§7Command: §6" + cmd);
			im2.setLore(lore2);
			raa.setItemMeta(im2);

			gui.setItem(2, rat);
			gui.setItem(6, raa);
			gui.setItem(8, stop);

			c.openInventory(gui);
		}
	}

	@EventHandler
	public void clickGUI(InventoryClickEvent e) {
		try {
			if (e.getInventory().equals(gui) || e.getView().getTitle().equalsIgnoreCase((Language.getLanguage().equalsIgnoreCase("de") ? "§8§ki §8Command Ausführen §ki" : "§8§ki §8Run Command §ki"))) {
				Player p = (Player) e.getWhoClicked();
				p.closeInventory();

				if (e.getCurrentItem() != null) {
					if (e.getCurrentItem().getType() == XMaterial.WHITE_WOOL.parseMaterial()) {
						if (p.hasPermission("troll.control") || p.hasPermission("troll.*")) {
							if (p.hasMetadata("C_H")) {
								Player victim = Bukkit.getPlayer(p.getMetadata("C_H").get(0).asString());
								Main.stopControlling(victim, p);
							} else {
								p.sendMessage(Data.prefix + Language.getMessage("gui.stopcontrol.nocontrol"));
							}
						} else {
							p.sendMessage(Data.prefix + Language.getMessage("nopermission"));
							e.getView().close();
						}
					}
					if (e.getCurrentItem().getType() == Material.BARRIER) {
						v.setMetadata("C_CMD", new FixedMetadataValue(plugin, true));
						v.chat(cmd);
					} else {
						if (e.getCurrentItem().getType() == Material.ARROW) {
							c.setMetadata("C_CMD", new FixedMetadataValue(plugin, true));
							c.chat(cmd);
						}
					}
				}
			}
		} catch (Exception e2) {

		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getInventory().equals(gui) || e.getView().getTitle().equalsIgnoreCase((Language.getLanguage().equalsIgnoreCase("de") ? "§8§ki §8Command Ausführen §ki" : "§8§ki §8Run Command §ki"))) {
			InventoryClickEvent.getHandlerList().unregister(this);
			InventoryCloseEvent.getHandlerList().unregister(this);
		}
	}
}