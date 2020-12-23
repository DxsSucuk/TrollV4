package de.presti.trollv4.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§2Change§clog/§3Credits");

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
		inv.setItem(21, SetItems.buildItem("§2Version » §c4.4.9", XMaterial.PAPER,
				new String[] { Data.cu + "Hotfix!" }));

		inv.setItem(50, SetItems.buildItem("§3Devs§7:", XMaterial.REDSTONE,
				new String[] { "§2Presti (" + Main.prestiname + ")", "§2David (" + PlayerInfo.getName("0c44ffe63efc4c01a430e1104945abfd") + ")" }));

		inv.setItem(51, SetItems.buildItem("§3Code Support§7:", XMaterial.WRITABLE_BOOK,
				new String[] { "§2RyTheFirst", "§2CryptoMorin" }));

		inv.setItem(52, SetItems.buildItem("§3Ideas§7:", XMaterial.TORCH,
				new String[] { "§2Garkolym", "§2CrashDezz(CrashedTroll)", "§2Minesuchtiiii(TrollBoss)" }));

		inv.setItem(53,
				SetItems.buildItem("§3Thanks to§7:", XMaterial.DIAMOND,
						new String[] { "§2" + PlayerInfo.getName("13fe75ebf9b74e23a44ee3ed342bdec2"),
								"§2" + PlayerInfo.getName("0c44ffe63efc4c01a430e1104945abfd"),
								"§2" + PlayerInfo.getName("4be8a4181bc341368119a6cbb64aa2ad"), "§2" + p.getName() }));

		p.openInventory(inv);
	}

	public static void log(Player p) {
		logGUI(p);
	}

	public static void sendCredits(Player p) {
		credits(p);
	}

	public static void credits(Player p) {
		log(p);
	}

}
