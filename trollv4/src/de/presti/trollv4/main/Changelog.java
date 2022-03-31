package de.presti.trollv4.main;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;

import de.presti.trollv4.api.PlayerInfo;
import de.presti.trollv4.invs.SetItems;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 13.07.2019 / 19:30:24											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Changelog {

	public static void sendlog(Player p) {
		log(p);
	}

	public static void logGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§2Change§clog");

		ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
		ItemMeta glm = gl.getItemMeta();
		glm.setDisplayName("§7");
		gl.setItemMeta(glm);

		for (int i = 0; i < (inv.getSize()); i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == XMaterial.AIR.parseMaterial()) {
				inv.setItem(i, gl);
			}
		}

		inv.setItem(10, SetItems.buildItem("§2Version » §c4.4.0", XMaterial.PAPER,
				new String[] { Data.cp + "Russian Language", Data.cu + "Rickroll Error fix" }));

		inv.setItem(11, SetItems.buildItem("§2Version » §c4.4.1", XMaterial.PAPER,
				new String[] { Data.cu + "Fix for Cracked Server in GUI" }));

		inv.setItem(12,
				SetItems.buildItem("§2Version » §c4.4.2", XMaterial.PAPER, new String[] { Data.cu + "Hotfix!" }));

		inv.setItem(13,
				SetItems.buildItem("§2Version » §c4.4.3", XMaterial.PAPER,
						new String[] { Data.cp + "Added Async Heads", Data.cp + "Notification if i join the Server",
								Data.cp + "Added an Warning to some Trolls",
								Data.cp + "TnTTrace tnt spawn delay can be changed" }));

		inv.setItem(14,
				SetItems.buildItem("§2Version » §c4.4.4", XMaterial.PAPER,
						new String[] { Data.cp + "AutoUpdater", Data.cp + "Added an TabCompleter",
								Data.cp + "Config GUI", Data.cu + "Some API Trolls got an Documentation" }));

		inv.setItem(15,
				SetItems.buildItem("§2Version » §c4.4.5", XMaterial.PAPER, new String[] { Data.cu + "Hotfix!" }));

		inv.setItem(16, SetItems.buildItem("§2Version » §c4.4.6", XMaterial.PAPER,
				new String[] { Data.cu + "Big Error Fix!" }));

		inv.setItem(19,
				SetItems.buildItem("§2Version » §c4.4.7", XMaterial.PAPER, new String[] {
						Data.cp + "Added Language Singapore (by the Community)", Data.cp + "TnT World is now ASync!",
						Data.cu + "Fixed Performance Problems with AutoUpdate", Data.cu + "Added more Warnings!" }));

		inv.setItem(20,
				SetItems.buildItem("§2Version » §c4.4.8", XMaterial.PAPER,
						new String[] { Data.cp + "New Changelog Menu!", Data.cp + "Added ServerSoftware Detection!",
								Data.cp + "Added Demoscreen 1.16 Support!", Data.cp + "Spooky World!",
								Data.cp + "API Doc Finished!", Data.cp + "Giorno Giovani Troll!",
								Data.cp + "Raining Anvil!", Data.cp + "Minecraft EndCredits!", Data.cp + "Hostile Cow!",
								Data.cm + "Removed Changelog < 4.4.0" }));
		inv.setItem(21,
				SetItems.buildItem("§2Version » §c4.4.9", XMaterial.PAPER, new String[] { Data.cu + "Hotfix!" }));

		inv.setItem(22,
				SetItems.buildItem("§2Version » §c4.5.0", XMaterial.PAPER,
						new String[] { Data.cp + "Added Spanish!", "Added new CreditsGUI",
								Data.cu + "Fixed LanguageManager", Data.cu + "Fixed a Random Error",
								Data.cm + "BigUpdate delayed to 4.5.5 or 4.6.0" }));
		
		inv.setItem(23,
				SetItems.buildItem("§2Version » §c4.5.1", XMaterial.PAPER,
						new String[] { Data.cp + "Added PurpurClip detection", Data.cp + "Added /troll [Player]" }));
		
		inv.setItem(24,
				SetItems.buildItem("§2Version » §c4.5.2", XMaterial.PAPER,
						new String[] { Data.cu + "Fixed /troll error!" }));
		
		inv.setItem(25,
				SetItems.buildItem("§2Version » §c4.5.3", XMaterial.PAPER,
						new String[] { Data.cu + "Fixed many disconnecting Problems with the Trolls!" }));

		inv.setItem(28,
				SetItems.buildItem("§2Version » §c4.5.4", XMaterial.PAPER,
						new String[] { Data.cp + "Added 1.17 and 1.18 support!" }));

		inv.setItem(29,
				SetItems.buildItem("§2Version » §c4.5.5", XMaterial.PAPER,
						new String[] { Data.cu + "Fixed Titles!", Data.cu + "Fixed Changelog!", Data.cu + "Fixed Credits!" }));

		inv.setItem(53, SetItems.buildItem("§3Credits", XMaterial.WRITABLE_BOOK));

		p.openInventory(inv);
	}

	public static void log(Player p) {
		logGUI(p);
	}

	public static void sendCredits(Player p) {
		credits(p);
	}

	public static void credits(Player p) {
		creditsGUI(p);
	}

	public static void creditsGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§3Credits");

		ItemStack blue = XMaterial.CYAN_STAINED_GLASS_PANE.parseItem();
		ItemMeta bluem = blue.getItemMeta();
		bluem.setDisplayName("§b");
		blue.setItemMeta(bluem);

		ItemStack darkblue = XMaterial.BLUE_STAINED_GLASS_PANE.parseItem();
		ItemMeta darkbluem = darkblue.getItemMeta();
		darkbluem.setDisplayName("§3");
		darkblue.setItemMeta(darkbluem);

		ItemStack darkgreen = XMaterial.GREEN_STAINED_GLASS_PANE.parseItem();
		ItemMeta darkgreenm = darkgreen.getItemMeta();
		darkgreenm.setDisplayName("§2");
		darkgreen.setItemMeta(darkgreenm);

		for (int i = 0; i <= 8; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == XMaterial.AIR.parseMaterial()) {
				if (i == 0) {
					ItemStack bcopy = blue.clone();
					ItemMeta newm = bluem.clone();
					newm.setDisplayName("§bDevs§7:");
					bcopy.setItemMeta(newm);
					inv.setItem(i, bcopy);
				} else {
					inv.setItem(i, blue);
				}
			}
		}

		for (int i = 18; i <= 26; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == XMaterial.AIR.parseMaterial()) {
				if (i == 18) {
					ItemStack bcopy = darkblue.clone();
					ItemMeta newm = darkbluem.clone();
					newm.setDisplayName("§3Ideas and Code Support§7:");
					bcopy.setItemMeta(newm);
					inv.setItem(i, bcopy);
				} else {
					inv.setItem(i, darkblue);
				}
			}
		}

		for (int i = 36; i <= 45; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == XMaterial.AIR.parseMaterial()) {
				if (i == 36) {
					ItemStack bcopy = darkgreen.clone();
					ItemMeta newm = darkgreenm.clone();
					newm.setDisplayName("§2Special Thanks to my friends§7:");
					bcopy.setItemMeta(newm);
					inv.setItem(i, bcopy);
				} else {
					inv.setItem(i, darkgreen);
				}
			}
		}

		inv.setItem(45, darkgreen);
		inv.setItem(27, darkblue);
		inv.setItem(9, blue);

		ItemStack skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("1c32b55bd4584347a5798754f4510081")));
		SkullMeta skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§bPresti §8(§2" + Main.prestiname + "§8)");
		skull.setItemMeta(skullm);

		inv.setItem(13, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("0c44ffe63efc4c01a430e1104945abfd")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§bDavid §8(§2" + PlayerInfo.getName("0c44ffe63efc4c01a430e1104945abfd") + "§8)");
		skull.setItemMeta(skullm);

		inv.setItem(14, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("d05d95d592ed45cd9320ee2e2e491d78")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§3RyTheFirst §8(§3Code Support§8)");
		skull.setItemMeta(skullm);

		inv.setItem(29, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("964e966639cd474493134ef0bf430635")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§3CryptoMorin §8(§3Code Support§8)");
		skull.setItemMeta(skullm);

		inv.setItem(30, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("fdeb5f5ec4984d5380b325e430513db2")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§3Garkolym §8(§3Ideas§8)");
		skull.setItemMeta(skullm);

		inv.setItem(31, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("f81a5c1be0a74a359181a1b3ce527340")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§3CrashedLife §8(§3Ideas§8)");
		skull.setItemMeta(skullm);

		inv.setItem(32, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("b864637e34734abb9f76d07689a0309e")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§3Minesuchtiiii §8(§3Ideas§8)");
		skull.setItemMeta(skullm);

		inv.setItem(33, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("13fe75ebf9b74e23a44ee3ed342bdec2")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§2" + PlayerInfo.getName("13fe75ebf9b74e23a44ee3ed342bdec2") + " §8(§c<3§8)");
		skull.setItemMeta(skullm);

		inv.setItem(48, skull);

		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("0c44ffe63efc4c01a430e1104945abfd")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§2" + PlayerInfo.getName("0c44ffe63efc4c01a430e1104945abfd") + " §8(§c<3§8)");
		skull.setItemMeta(skullm);

		inv.setItem(49, skull);
		skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("4be8a4181bc341368119a6cbb64aa2ad")));
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§2" + PlayerInfo.getName("4be8a4181bc341368119a6cbb64aa2ad") + " §8(§c<3§8)");
		skull.setItemMeta(skullm);

		inv.setItem(50, skull);

		skull = XMaterial.PLAYER_HEAD.parseItem();
		skullm = (SkullMeta) skull.getItemMeta();
		skullm.setDisplayName("§2" + p.getName() + " §8(§c<3§8)");
		skullm.setOwner(p.getName());
		skull.setItemMeta(skullm);

		inv.setItem(51, skull);

		inv.setItem(17, blue);
		inv.setItem(35, darkblue);
		inv.setItem(53, darkgreen);

		ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
		ItemMeta glm = gl.getItemMeta();
		glm.setDisplayName("§7");
		gl.setItemMeta(glm);

		for (int i = 0; i < (inv.getSize()); i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == XMaterial.AIR.parseMaterial()) {
				inv.setItem(i, gl);
			}
		}

		p.openInventory(inv);
	}

}
