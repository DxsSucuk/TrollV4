package de.presti.trollv4.invs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.cryptomorin.xseries.SkullUtils;

import de.presti.trollv4.api.PlayerInfo;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright § Baris Arslan 2018											    *
*	Erstellt: 11.05.2019 / 22:08:48												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich gesch§tzt.			*
*	Das Urheberrecht liegt, soweit nicht ausdr§cklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielf§ltigung, Verbreitung, Vermietung, Verleihung,			*
*	§ffentlichen Zug§nglichmachung oder anderer Nutzung							*
*	bedarf der ausdr§cklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class SetItems {

	public static ItemStack buildSkull(String p, String name) throws IOException {
		ArrayList<String> lore = new ArrayList<String>();
		ItemStack skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID(p)));
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

	@SuppressWarnings("deprecation")
	public static ItemStack buildItemCodes(String name, Material m, int in, short sh) {
		ItemStack item = new ItemStack(m, in, sh);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

}
