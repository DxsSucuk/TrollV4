package de.presti.trollv4.main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.presti.trollv4.invs.SetItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XMaterial;

import de.presti.trollv4.api.PlayerInfo;
import org.bukkit.scheduler.BukkitRunnable;

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
                new String[]{Data.changelogAdd + "Russian Language", Data.changelogChange + "Rickroll Error fix"}));

        inv.setItem(11, SetItems.buildItem("§2Version » §c4.4.1", XMaterial.PAPER,
                new String[]{Data.changelogChange + "Fix for Cracked Server in GUI"}));

        inv.setItem(12,
                SetItems.buildItem("§2Version » §c4.4.2", XMaterial.PAPER, new String[]{Data.changelogChange + "Hotfix!"}));

        inv.setItem(13,
                SetItems.buildItem("§2Version » §c4.4.3", XMaterial.PAPER,
                        new String[]{Data.changelogAdd + "Added Async Heads", Data.changelogAdd + "Notification if i join the Server",
                                Data.changelogAdd + "Added an Warning to some Trolls",
                                Data.changelogAdd + "TnTTrace tnt spawn delay can be changed"}));

        inv.setItem(14,
                SetItems.buildItem("§2Version » §c4.4.4", XMaterial.PAPER,
                        new String[]{Data.changelogAdd + "AutoUpdater", Data.changelogAdd + "Added an TabCompleter",
                                Data.changelogAdd + "Config GUI", Data.changelogChange + "Some API Trolls got an Documentation"}));

        inv.setItem(15,
                SetItems.buildItem("§2Version » §c4.4.5", XMaterial.PAPER, new String[]{Data.changelogChange + "Hotfix!"}));

        inv.setItem(16, SetItems.buildItem("§2Version » §c4.4.6", XMaterial.PAPER,
                new String[]{Data.changelogChange + "Big Error Fix!"}));

        inv.setItem(19,
                SetItems.buildItem("§2Version » §c4.4.7", XMaterial.PAPER, new String[]{
                        Data.changelogAdd + "Added Language Singapore (by the Community)", Data.changelogAdd + "TnT World is now ASync!",
                        Data.changelogChange + "Fixed Performance Problems with AutoUpdate", Data.changelogChange + "Added more Warnings!"}));

        inv.setItem(20,
                SetItems.buildItem("§2Version » §c4.4.8", XMaterial.PAPER,
                        new String[]{Data.changelogAdd + "New Changelog Menu!", Data.changelogAdd + "Added ServerSoftware Detection!",
                                Data.changelogAdd + "Added Demoscreen 1.16 Support!", Data.changelogAdd + "Spooky World!",
                                Data.changelogAdd + "API Doc Finished!", Data.changelogAdd + "Giorno Giovani Troll!",
                                Data.changelogAdd + "Raining Anvil!", Data.changelogAdd + "Minecraft EndCredits!", Data.changelogAdd + "Hostile Cow!",
                                Data.changelogRemove + "Removed Changelog < 4.4.0"}));
        inv.setItem(21,
                SetItems.buildItem("§2Version » §c4.4.9", XMaterial.PAPER, new String[]{Data.changelogChange + "Hotfix!"}));

        inv.setItem(22,
                SetItems.buildItem("§2Version » §c4.5.0", XMaterial.PAPER,
                        new String[]{Data.changelogAdd + "Added Spanish!", "Added new CreditsGUI",
                                Data.changelogChange + "Fixed LanguageManager", Data.changelogChange + "Fixed a Random Error",
                                Data.changelogRemove + "BigUpdate delayed to 4.5.5 or 4.6.0"}));

        inv.setItem(23,
                SetItems.buildItem("§2Version » §c4.5.1", XMaterial.PAPER,
                        new String[]{Data.changelogAdd + "Added PurpurClip detection", Data.changelogAdd + "Added /troll [Player]"}));

        inv.setItem(24,
                SetItems.buildItem("§2Version » §c4.5.2", XMaterial.PAPER,
                        new String[]{Data.changelogChange + "Fixed /troll error!"}));

        inv.setItem(25,
                SetItems.buildItem("§2Version » §c4.5.3", XMaterial.PAPER,
                        new String[]{Data.changelogChange + "Fixed many disconnecting Problems with the Trolls!"}));

        inv.setItem(28,
                SetItems.buildItem("§2Version » §c4.5.4", XMaterial.PAPER,
                        new String[]{Data.changelogAdd + "Added 1.17 and 1.18 support!"}));

        inv.setItem(29,
                SetItems.buildItem("§2Version » §c4.5.5", XMaterial.PAPER,
                        new String[]{Data.changelogChange + "Fixed Titles!", Data.changelogChange + "Fixed Changelog!", Data.changelogChange + "Fixed Credits!"}));

        inv.setItem(30,
                SetItems.buildItem("§2Version » §c4.5.6", XMaterial.PAPER,
                        new String[]{
                                Data.changelogChange + "Fixed World creation Issues with PaperMC!",
                                Data.changelogChange + "Fixed Startup Errors, when LibsDisguises is not present or loaded!",
                                Data.changelogChange + "Fixed Credits!"
                        }
                ));

        inv.setItem(31,
                SetItems.buildItem("§2Version » §c4.5.7", XMaterial.PAPER,
                        new String[]{
                                Data.changelogChange + "Fixed JAR!"
                        }
                ));

        inv.setItem(32,
                SetItems.buildItem("§2Version » §c4.5.8", XMaterial.PAPER,
                        new String[]{
                                Data.changelogAdd + "Full 1.19 support (Soon NPCs aswell!)",
                                Data.changelogChange + "Using official downloads instead of own mirror!",
                                Data.changelogChange + "Reworked internal code",
                                Data.changelogRemove + "Sadly broke NPCs and waiting for NPC-Lib to document their code."
                        }
                ));

        inv.setItem(34,
                SetItems.buildItem("§2Version » §c4.5.9", XMaterial.PAPER,
                        new String[]{
                                Data.changelogRemove + "Fixed a bug related to the Selection GUI."
                        }
                ));

        inv.setItem(37,
                SetItems.buildItem("§2Version » §c4.5.10", XMaterial.PAPER,
                        new String[]{
                                Data.changelogAdd + "Adding a config value for the spooky world generation."
                        }
                ));

        inv.setItem(38,
                SetItems.buildItem("§2Version » §c4.5.11", XMaterial.PAPER,
                        new String[]{
                                Data.changelogChange + "NPC based trolls should work on every version now."
                        }
                ));

        inv.setItem(40,
                SetItems.buildItem("§2Version » §c4.5.12 §a+ §c4.5.13", XMaterial.PAPER,
                        new String[]{
                                Data.changelogAdd + "Added a warning if the whitelist is on while having community-surprise active.",
                                Data.changelogAdd + "Option for custom spookyworld name.",
                                Data.changelogChange + "Spooky world will now ONLY be created on start."
                        }
                ));

        inv.setItem(41,
                SetItems.buildItem("§2Version » §c4.5.14", XMaterial.PAPER,
                        new String[]{
                                Data.changelogAdd + "Added confirmation menu for desturctiv trolls.",
                                Data.changelogAdd + "Added vomit troll.",
                                Data.changelogAdd + "Added infinite loading screen troll."
                        }
                ));

        inv.setItem(42,
                SetItems.buildItem("§2Version » §c4.5.15", XMaterial.PAPER,
                        new String[]{
                                Data.changelogAdd + "1.20 Support.",
                                Data.changelogChange + "A lot of code cleanup and dependency fixing."
                        }
                ));

        inv.setItem(43,
                SetItems.buildItem("§2Version » §c4.5.16", XMaterial.PAPER,
                        new String[]{
                                Data.changelogChange + "Bug fixing for cracked servers.",
                                Data.changelogChange + "Reducing console spamming."
                        }
                ));


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

        HashMap<Integer, ItemStack> items = new HashMap<>();

        new BukkitRunnable() {

            @Override
            public void run() {
                ItemStack skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("1c32b55bd4584347a5798754f4510081")));
                SkullMeta skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§bPresti §8(§2" + PlayerInfo.getName("1c32b55bd4584347a5798754f4510081") + "§8)");
                skull.setItemMeta(skullm);

                items.put(13, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("0c44ffe63efc4c01a430e1104945abfd")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§bDavid §8(§2" + PlayerInfo.getName("0c44ffe63efc4c01a430e1104945abfd") + "§8)");
                skull.setItemMeta(skullm);

                items.put(14, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("d05d95d592ed45cd9320ee2e2e491d78")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§3RyTheFirst §8(§3Code Support§8)");
                skull.setItemMeta(skullm);

                items.put(29, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("964e966639cd474493134ef0bf430635")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§3CryptoMorin §8(§3Code Support§8)");
                skull.setItemMeta(skullm);

                items.put(30, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("fdeb5f5ec4984d5380b325e430513db2")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§3Garkolym §8(§3Ideas§8)");
                skull.setItemMeta(skullm);

                items.put(31, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("f81a5c1be0a74a359181a1b3ce527340")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§3CrashedLife §8(§3Ideas§8)");
                skull.setItemMeta(skullm);

                items.put(32, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("b864637e34734abb9f76d07689a0309e")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§3Minesuchtiiii §8(§3Ideas§8)");
                skull.setItemMeta(skullm);

                items.put(33, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("4be8a4181bc341368119a6cbb64aa2ad")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§2" + PlayerInfo.getName("4be8a4181bc341368119a6cbb64aa2ad") + " §8(§c<3§8)");
                skull.setItemMeta(skullm);

                items.put(48, skull);

                skull = SkullUtils.getSkull(UUID.fromString(PlayerInfo.getUUID("4414a12b4a3e4914834647389dc00a24")));
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§2" + PlayerInfo.getName("4414a12b4a3e4914834647389dc00a24") + " §8(§c<3§8)");
                skull.setItemMeta(skullm);

                items.put(49, skull);
                skull = XMaterial.PLAYER_HEAD.parseItem();
                skullm = (SkullMeta) skull.getItemMeta();
                skullm.setDisplayName("§2" + p.getName() + " §8(§c<3§8)");
                skullm.setOwner(p.getName());
                skull.setItemMeta(skullm);

                items.put(51, skull);

                new BukkitRunnable() {

                    @Override
                    public void run() {

                        for (Map.Entry<Integer, ItemStack> it : items.entrySet()) {
                            inv.setItem(it.getKey(), it.getValue());
                        }

                    }
                }.runTask(Main.getInstance());
            }
        }.runTaskAsynchronously(Main.getInstance());

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
