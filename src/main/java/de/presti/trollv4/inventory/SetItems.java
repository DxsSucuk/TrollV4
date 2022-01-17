package de.presti.trollv4.inventory;

import java.util.ArrayList;
import java.util.UUID;

import de.presti.trollv4.util.web.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;

public class SetItems {

	public static ItemStack buildSkull(String p, String name) {
		ArrayList<String> lore = new ArrayList<String>();

		String uuid = "";

		try {
			uuid = PlayerInfo.getUUID(p);
		} catch (Exception e) {
			uuid = "cracked";
		}

		if (uuid == null || uuid.isEmpty()) {
			uuid = "cracked";
		}

		ItemStack skull = (uuid.equalsIgnoreCase("cracked") ? new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial())
				: SkullUtils.getSkull(UUID.fromString(uuid)));
		ItemMeta skullm = skull.getItemMeta();

		if (Bukkit.getPlayer(p) != null) {
			if (Bukkit.getPlayer(p).isOp()) {
				lore.add("§cThis User has OP!");
			}

			if (Bukkit.getPlayer(p).hasPermission("troll.player")) {
				lore.add("§cThis User can acces the Troll Gui!");
			}

			skullm.setLore(lore);

		}

		skullm.setDisplayName(name);
		skull.setItemMeta(skullm);
		return skull;
	}

	public static ItemStack buildItem(String name, Material m) {
		ItemStack item = new ItemStack(m);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack buildItem(String name, XMaterial m) {
		ItemStack item = new ItemStack(m.parseItem());
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack buildItem(String name, XMaterial m, String[] lores) {
		ArrayList<String> lore = new ArrayList<String>();

		for (String s : lores) {
			lore.add(s);
		}

		ItemStack item = new ItemStack(m.parseItem());
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(lore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack buildItem(String name, Material m, String[] lores) {
		ArrayList<String> lore = new ArrayList<String>();

		for (String s : lores) {
			lore.add(s);
		}

		ItemStack item = new ItemStack(m);
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(lore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

	public static ItemStack buildItem(String name, Material m, String lores) {
		ArrayList<String> lore = new ArrayList<String>();

		lore.add(lores);

		ItemStack item = new ItemStack(m);
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(lore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}
	
	public static ItemStack buildItem(String name, XMaterial m, String lores) {
		ArrayList<String> lore = new ArrayList<String>();

		lore.add(lores);

		ItemStack item = new ItemStack(m.parseItem());
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(lore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack buildItemCodes(String name, Material m, int in, short sh) {
		ItemStack item = new ItemStack(m, in, sh);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

}