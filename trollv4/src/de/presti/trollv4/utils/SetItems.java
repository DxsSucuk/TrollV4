package de.presti.trollv4.utils;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.SkullUtils;

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
		ItemStack skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID(p)));
		ItemMeta skullm = skull.getItemMeta();
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
	
	public static ItemStack buildItemCodes(String name, Material m, int in, short sh) {
		ItemStack item = new ItemStack(m, in, sh);
		ItemMeta itemm = item.getItemMeta();
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		return item;
	}

}
