package de.presti.trollv4.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.presti.trollv4.api.PlayMusic;
import de.presti.trollv4.api.TrollV4API;
import de.presti.trollv4.cmd.TrollCommand;
import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.language.LanguageService;
import de.presti.trollv4.invs.InvManager;
import de.presti.trollv4.invs.InvSaver;
import de.presti.trollv4.invs.SetItems;
import de.presti.trollv4.main.Changelog;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.crossversion.DemoScreen;
import de.presti.trollv4.utils.crossversion.HS;
import de.presti.trollv4.utils.crossversion.Titles;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.player.LocationUtil;
import de.presti.trollv4.utils.plugin.RandomUtility;
import de.presti.trollv4.utils.server.NPCUtil;
import de.presti.trollv4.utils.server.ServerInfo;
import io.sentry.Sentry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import com.cryptomorin.xseries.*;

public class GuiListener implements Listener {
    public int taskID;
    public int taskID2;

    // Troll Item Gui

    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        try {
            Player p = (Player) e.getWhoClicked();

            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == XMaterial.AIR.parseMaterial()) return;

            if (e.getView().getTitle().equalsIgnoreCase("§2Item Troll Menu")) {
                e.setCancelled(true);
                if (p.hasPermission("troll.items")) {

                    if (e.getCurrentItem().getItemMeta() == null) {
                        return;
                    }

                    if (e.getCurrentItem().getType() == XMaterial.AIR.parseMaterial()) {
                        return;
                    }

                    ItemStack item = new ItemStack(XMaterial.DIAMOND_SWORD.parseMaterial());
                    ItemMeta imeta = item.getItemMeta();
                    imeta.setDisplayName(Items.getItem("gui.items.diamondsword"));
                    imeta.addEnchant(XEnchantment.SHARPNESS.getEnchant(), 1000, true);
                    imeta.addEnchant(XEnchantment.FIRE_ASPECT.getEnchant(), 1000, true);
                    item.setItemMeta(imeta);
                    item.setAmount(1);

                    ItemStack item2 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
                    ItemMeta imeta2 = item2.getItemMeta();
                    imeta2.setDisplayName(Items.getItem("gui.items.diamondprotectionchest"));
                    imeta2.addEnchant(XEnchantment.PROTECTION.getEnchant(), 1000, true);
                    item2.setItemMeta(imeta2);
                    item2.setAmount(1);

                    ItemStack item3 = new ItemStack(XMaterial.DIAMOND_CHESTPLATE.parseMaterial());
                    ItemMeta imeta3 = item3.getItemMeta();
                    imeta3.setDisplayName(Items.getItem("gui.items.diamondthornschest"));
                    imeta3.addEnchant(XEnchantment.THORNS.getEnchant(), 1000, true);
                    item3.setItemMeta(imeta3);
                    item3.setAmount(1);

                    ItemStack item4 = new ItemStack(XMaterial.DIAMOND_PICKAXE.parseMaterial());
                    ItemMeta imeta4 = item4.getItemMeta();
                    imeta4.setDisplayName(Items.getItem("gui.items.diamondpickaxe"));
                    imeta4.addEnchant(XEnchantment.EFFICIENCY.getEnchant(), 1000, true);
                    item4.setItemMeta(imeta4);
                    item4.setAmount(1);

                    ItemStack item5 = new ItemStack(XMaterial.BOW.parseMaterial());
                    ItemMeta imeta5 = item5.getItemMeta();
                    imeta5.setDisplayName(Items.getItem("gui.items.onehitbow"));
                    imeta5.addEnchant(XEnchantment.INFINITY.getEnchant(), 1000, true);
                    imeta5.addEnchant(XEnchantment.PUNCH.getEnchant(), 1000, true);
                    imeta5.addEnchant(XEnchantment.POWER.getEnchant(), 1000, true);
                    item5.setItemMeta(imeta5);
                    item5.setAmount(1);

                    ItemStack item6 = new ItemStack(XMaterial.WOODEN_HOE.parseMaterial());
                    ItemMeta imeta6 = item6.getItemMeta();
                    imeta6.setDisplayName(Items.getItem("gui.items.woodenhoe"));
                    imeta6.addEnchant(XEnchantment.SHARPNESS.getEnchant(), 1000, true);
                    imeta6.addEnchant(XEnchantment.FIRE_ASPECT.getEnchant(), 1000, true);
                    item6.setItemMeta(imeta6);
                    item6.setAmount(1);

                    ItemStack item7 = new ItemStack(XMaterial.WOODEN_SWORD.parseMaterial());
                    ItemMeta imeta7 = item7.getItemMeta();
                    imeta7.setDisplayName(Items.getItem("gui.items.woodensword"));
                    imeta7.addEnchant(XEnchantment.SHARPNESS.getEnchant(), 1000, true);
                    imeta7.addEnchant(XEnchantment.FIRE_ASPECT.getEnchant(), 1000, true);
                    item7.setItemMeta(imeta7);
                    item7.setAmount(1);

                    ItemStack tntbow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
                            Items.getItem("gui.items.bow.tnt"), "§eTNT-Arrows");
                    ItemStack lavabow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
                            Items.getItem("gui.items.bow.lava"), "§eLava-Arrows");
                    ItemStack strikebow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
                            Items.getItem("gui.items.bow.lightning"), "§eLightning-Arrows");
                    ItemStack creeperbow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
                            Items.getItem("gui.items.bow.creeper"), "§eCreeper-Arrows");
                    ItemStack bedrockbow = InvManager.createItem(XMaterial.BOW.parseMaterial(), 1,
                            Items.getItem("gui.items.bow.bedrock"), "§eBedrock-Arrows");

                    ItemStack minigun = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
                    ItemMeta meta = minigun.getItemMeta();
                    meta.setDisplayName(Items.getItem("gui.items.minigun"));
                    minigun.setItemMeta(meta);

                    ItemStack fireball = new ItemStack(XMaterial.STICK.parseMaterial());
                    ItemMeta fmeta = fireball.getItemMeta();
                    fmeta.setDisplayName(Items.getItem("gui.items.fireball"));
                    fireball.setItemMeta(fmeta);

                    if (e.getCurrentItem().getItemMeta().equals(item.getItemMeta())) {
                        p.getInventory().addItem(item);
                    } else if (e.getCurrentItem().getItemMeta().equals(item2.getItemMeta())) {
                        p.getInventory().addItem(item2);
                    } else if (e.getCurrentItem().getItemMeta().equals(item3.getItemMeta())) {
                        p.getInventory().addItem(item3);
                    } else if (e.getCurrentItem().getItemMeta().equals(item4.getItemMeta())) {
                        p.getInventory().addItem(item4);
                    } else if (e.getCurrentItem().getItemMeta().equals(item5.getItemMeta())) {
                        p.getInventory().addItem(item5);
                    } else if (e.getCurrentItem().getItemMeta().equals(item6.getItemMeta())) {
                        p.getInventory().addItem(item6);
                    } else if (e.getCurrentItem().getItemMeta().equals(item7.getItemMeta())) {
                        p.getInventory().addItem(item7);
                    } else if (e.getCurrentItem().getItemMeta().equals(tntbow.getItemMeta())) {
                        p.getInventory().addItem(tntbow);
                    } else if (e.getCurrentItem().getItemMeta().equals(lavabow.getItemMeta())) {
                        p.getInventory().addItem(lavabow);
                    } else if (e.getCurrentItem().getItemMeta().equals(strikebow.getItemMeta())) {
                        p.getInventory().addItem(strikebow);
                    } else if (e.getCurrentItem().getItemMeta().equals(creeperbow.getItemMeta())) {
                        p.getInventory().addItem(creeperbow);
                    } else if (e.getCurrentItem().getItemMeta().equals(bedrockbow.getItemMeta())) {
                        p.getInventory().addItem(bedrockbow);
                    } else if (e.getCurrentItem().getItemMeta().equals(fireball.getItemMeta())) {
                        p.getInventory().addItem(fireball);
                    } else if (e.getCurrentItem().getItemMeta().equals(minigun.getItemMeta())) {
                        p.getInventory().addItem(minigun);
                    }

                    p.playSound(p.getLocation(), XSound.ENTITY_PLAYER_LEVELUP.parseSound(), 3.0F, 2.0F);
                }
            }
        } catch (Exception e1) {
            Sentry.captureException(e1);
            e1.printStackTrace();
        }
    }

    // Troll Rest Guis

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onTrollGuiClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try {
            if (e.getView().getTitle().equalsIgnoreCase("§2Player Choice Menu")) {
                e.getResult();
                e.setResult(Result.DENY);
                if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {
                    if (e.getCurrentItem().getType() == XMaterial.PLAYER_HEAD.parseItem().getType()) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (e.getCurrentItem().getItemMeta().getDisplayName().replace("§2", "")
                                    .equalsIgnoreCase(all.getName())) {
                                if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                                    ArrayUtils.trolling.put(p.getName(), all.getName());
                                    e.getView().close();
                                    InvManager.openPlayerInv(p);
                                } else {
                                    p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                                    e.getView().close();
                                }
                            }
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bNext Page")) {
                        int page = Integer.parseInt(
                                e.getInventory().getItem(45).getItemMeta().getDisplayName().replaceAll("§cPage: ", ""));

                        ItemStack pagei = XMaterial.RED_STAINED_GLASS_PANE.parseItem();
                        ItemMeta pagem = pagei.getItemMeta();
                        pagem.setDisplayName("§cPage: " + page + 1);
                        pagei.setItemMeta(pagem);

                        ItemStack pagen = XMaterial.BLUE_STAINED_GLASS_PANE.parseItem();
                        ItemMeta pagenm = pagen.getItemMeta();
                        pagenm.setDisplayName("§bNext Page");

                        pagen.setItemMeta(pagenm);

                        e.getInventory().clear();

                        ItemStack gl = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();
                        ItemMeta glm = gl.getItemMeta();
                        glm.setDisplayName("§7");
                        gl.setItemMeta(glm);

                        e.getInventory().setItem(46, gl);
                        e.getInventory().setItem(47, gl);
                        e.getInventory().setItem(48, gl);
                        e.getInventory().setItem(49, gl);
                        e.getInventory().setItem(51, gl);
                        e.getInventory().setItem(52, gl);
                        e.getInventory().setItem(53, gl);

                        if (Bukkit.getOnlinePlayers().size() > (45 * (page))) {

                            ArrayList<ItemStack> items = new ArrayList<ItemStack>();

                            e.getInventory().setItem(45, pagei);

                            if (Data.async) {
                                Main.getInstance().getFoliaLib().getScheduler().runAsync(x -> {
                                    int i = 0;
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (i > (45 * page)) {
                                            items.add(SetItems.buildSkull(all.getName(), "§2" + all.getName(), false));
                                        }
                                        i++;
                                    }

                                    Main.getInstance().getFoliaLib().getScheduler().runNextTick(y -> {
                                        for (ItemStack is : items) {
                                            e.getInventory().addItem(is);
                                        }
                                    });
                                });
                            } else {
                                int i = 0;
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    if (i > (45 * page)) {
                                        e.getInventory()
                                                .addItem(SetItems.buildSkull(all.getName(), "§2" + all.getName(), false));
                                    }
                                    i++;
                                }
                            }
                        }

                        if (Bukkit.getOnlinePlayers().size() > (45 * (page + 1))) {
                            e.getInventory().setItem(50, pagen);
                        } else {
                            pagenm.setDisplayName("§cNo Next Page");
                            pagen.setItemMeta(pagenm);
                            e.getInventory().setItem(50, pagen);
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cConfiguration")) {
                        if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                            e.getView().close();
                            InvManager.openConfigInv(p);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                            e.getView().close();
                        }
                    }
                }
            } else if (e.getView().getTitle().equalsIgnoreCase("§cTroll Config Menu")) {
                e.getResult();
                e.setResult(Result.DENY);

                if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBack")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        InvManager.choicePlayer(p);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cReload Config")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        Main.reloadConfigurations();
                        p.sendMessage(Data.prefix + "§cReloaded!");
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase("§cCustom§7-§2Item§7-§2Name")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        Config.config.set("Custom-Item-Name", !Config.config.getBoolean("Custom-Item-Name"));
                        Config.config.save(Config.getFile());

                        e.getInventory().setItem(0,
                                SetItems.buildItem("§cCustom§7-§2Item§7-§2Name", XMaterial.PAPER,
                                        new String[]{"§cCurrent Value:",
                                                (Config.config.getBoolean("Custom-Item-Name") ? "§ayes" : "§cno")}));

                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bUpdateChecker")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        Config.config.set("UpdateChecker", !Config.config.getBoolean("UpdateChecker"));
                        Config.config.save(Config.getFile());

                        e.getInventory().setItem(1,
                                SetItems.buildItem("§bUpdateChecker", XMaterial.CLOCK,
                                        new String[]{"§cCurrent Value:",
                                                (Config.config.getBoolean("UpdateChecker") ? "§ayes" : "§cno")}));

                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAuto§bUpdate")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        Config.config.set("AutoUpdate", !Config.config.getBoolean("AutoUpdate"));
                        Config.config.save(Config.getFile());

                        e.getInventory().setItem(2,
                                SetItems.buildItem("§cAuto§bUpdate", XMaterial.CAULDRON,
                                        new String[]{"§cCurrent Value:",
                                                (Config.config.getBoolean("AutoUpdate") ? "§ayes" : "§cno")}));

                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Animations")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        Config.config.set("Animations", !Config.config.getBoolean("Animations"));
                        Config.config.save(Config.getFile());

                        e.getInventory().setItem(3,
                                SetItems.buildItem("§2Animations", XMaterial.GLASS_PANE,
                                        new String[]{"§cCurrent Value:",
                                                (Config.config.getBoolean("Animations") ? "§ayes" : "§cno")}));

                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aASync")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        Config.config.set("ASync", !Config.config.getBoolean("ASync"));
                        Config.config.save(Config.getFile());

                        e.getInventory().setItem(4, SetItems.buildItem("§aASync", XMaterial.PLAYER_HEAD, new String[]{
                                "§cCurrent Value:", (Config.config.getBoolean("ASync") ? "§ayes" : "§cno")}));

                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase("§2Community§7-§csurprise")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        Config.config.set("Community-surprise", !Config.config.getBoolean("Community-surprise"));
                        Config.config.save(Config.getFile());

                        e.getInventory().setItem(5,
                                SetItems.buildItem("§2Community§7-§csurprise", XMaterial.CAKE,
                                        new String[]{"§cCurrent Value:",
                                                (Config.config.getBoolean("Community-surprise") ? "§ayes" : "§cno")}));

                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }

            } else if (e.getView().getTitle().equalsIgnoreCase("§2Which MLG?")) {
                e.getResult();
                e.setResult(Result.DENY);
                if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBack")) {
                    if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        InvManager.openPlayerInv(p);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }

                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.mlgs.water"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.mlg") || p.hasPermission("troll*")) {
                        e.getView().close();
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.mlg", t));
                            e.getView().close();
                            t.setAllowFlight(true);
                            t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                            t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                            t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                            ItemStack bucket = new ItemStack(XMaterial.WATER_BUCKET.parseItem());
                            ItemMeta meta = bucket.getItemMeta();
                            meta.setDisplayName("§cMLG§8-§bBucket");
                            bucket.setItemMeta(meta);
                            t.getInventory().addItem(new ItemStack[]{bucket});
                            Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
                            t.setAllowFlight(false);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.mlgs.lava"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.mlg", t));
                            t.setAllowFlight(true);
                            t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                            t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                            t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                            ItemStack bucket = new ItemStack(XMaterial.LAVA_BUCKET.parseItem());
                            ItemMeta meta = bucket.getItemMeta();
                            meta.setDisplayName("§cMLG§8-§bBucket");
                            bucket.setItemMeta(meta);
                            t.getInventory().addItem(new ItemStack[]{bucket});
                            Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
                            t.setAllowFlight(false);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.mlgs.cobweb"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.mlg", t));
                            t.setAllowFlight(true);
                            t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                            t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                            t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                            ItemStack web = new ItemStack(XMaterial.COBWEB.parseItem());
                            ItemMeta webm = web.getItemMeta();
                            webm.setDisplayName("§cMLG§8-§bWeb");
                            web.setItemMeta(webm);
                            t.getInventory().addItem(new ItemStack[]{web});
                            Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
                            t.setAllowFlight(false);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.mlgs.slime"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.mlg", t));
                            t.setAllowFlight(true);
                            t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                            t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                            t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F, 25.0F);
                            ItemStack slime = new ItemStack(XMaterial.SLIME_BLOCK.parseItem());
                            ItemMeta slimem = slime.getItemMeta();
                            slimem.setDisplayName("§cMLG§8-§aSlime");
                            slime.setItemMeta(slimem);
                            t.getInventory().addItem(new ItemStack[]{slime});
                            Titles.send(t, 1, 10, 1, "§2MAKE A §cMLG", "");
                            t.setAllowFlight(false);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
            } else if (e.getView().getTitle().equalsIgnoreCase("§2Player Troll Menu")) {
                e.getResult();
                e.setResult(Result.DENY);
                if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cReselect")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        ArrayUtils.trolling.remove(p.getName());
                        e.getView().close();
                        InvManager.choicePlayer(p);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cNext Page")) {
                    if (p.hasPermission("troll.player") || p.hasPermission("troll.*")) {
                        InvManager.setPageTwoTrolls(e.getClickedInventory(), p);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.explode"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.explode") || p.hasPermission("troll.*")) {
                        if (t != null) {
                            t.playSound(t.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 100.0F,
                                    200.0F);
                            t.getWorld().createExplosion(t.getLocation(), 3.0F);
                            t.setHealth(0.0D);
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.explode", t));
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.fakehack"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.fakehack") || p.hasPermission("troll.*")) {
                        if (t != null) {
                            if (ArrayUtils.fc.contains(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.fakehack.off", t));
                                e.getView().close();
                                t.setWalkSpeed(0.2F);
                                t.setAllowFlight(false);
                                ArrayUtils.fc.remove(t);
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.fakehack.on", t));
                                e.getView().close();
                                ArrayUtils.fc.add(t);

                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.demo"))) {

                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.demo") || p.hasPermission("troll.*")) {
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.demo", t));
                            e.getView().close();
                            DemoScreen.showDemoScreen(t);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.strike"))) {
                    if (p.hasPermission("troll.strike") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.strike", t));
                            e.getView().close();
                            t.getLocation().getWorld().strikeLightning(t.getLocation());
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.hackuser"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.hackuser") || p.hasPermission("troll.*")) {
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.hackuser", t));
                            e.getView().close();
                            if (ArrayUtils.hackuser.containsKey(t)) {
                                ArrayUtils.hackuser.get(t).cancel();
                                ArrayUtils.hackuser.remove(t);
                            }

                            ArrayUtils.hackuser.put(t, Main.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(t, new Runnable() {
                                int countdown = Config.config.getInt("trolls.hack.time");
                                @Override
                                public void run() {
                                    if (countdown <= 0) {
                                        HS.Hack(t);
                                        Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + RandomUtility.getRandomID());
                                        ArrayUtils.hackuser.get(t).cancel();

                                        return;
                                    }
                                    HS.Hack2(t);
                                    Titles.send(t, 1, 20, 1, "§cHacking in " + countdown,
                                            "§4" + RandomUtility.getRandomID());
                                    t.damage(0.1D);
                                    countdown--;
                                }
                            }, 0, 20));
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.rocket"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.rocket") || p.hasPermission("troll.*")) {
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.rocket", t));
                            e.getView().close();
                            t.setAllowFlight(true);
                            t.setVelocity(t.getLocation().getDirection().multiply(0.5D).setY(3.8D));
                            t.playSound(t.getLocation(), XSound.ENTITY_PLAYER_BURP.parseSound(), 100.0F, 25.0F);
                            t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_GROWL.parseSound(), 100.0F,
                                    25.0F);
                            t.setAllowFlight(false);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }

                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.mlg"))) {
                    if (p.hasPermission("troll.mlg") || p.hasPermission("troll.*")) {
                        InvManager.openMLGchoiceInv(p);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.spam"))) {
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.spam") || p.hasPermission("troll.*")) {
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.spam", t));
                            e.getView().close();
                            Main.getInstance().getFoliaLib().getScheduler().runAtEntity(t, x -> {
                                for (int i = 0; i < 1000; i++) {
                                    t.sendMessage("§cREEEEEEEEEEEEEEEEEEEEEEE!");
                                }
                            });
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.startcontrol"))) {

                    if (Bukkit.getPluginManager().getPlugin("LibsDisguises") == null) {
                        p.sendMessage(Data.prefix + "LibsDisguises is not installed!");
                        e.getView().close();
                        return;
                    }
                    Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                    if (p.hasPermission("troll.control") || p.hasPermission("troll.*")) {
                        if (!p.hasMetadata("C_H")) {
                            if (t != null) {
                                if (!t.hasMetadata("C_P")) {
                                    if (t != p) {
                                        if (!(t.hasPermission("troll.control"))) {
                                            Main.startControlling(t, p);
                                        } else {
                                            p.sendMessage(Data.prefix
                                                    + LanguageService.getDefault("gui.startcontrol.cantcontrol", t));
                                            e.getView().close();
                                        }
                                    } else {
                                        p.sendMessage(
                                                Data.prefix + LanguageService.getDefault("gui.startcontrol.yourself", t));
                                        e.getView().close();
                                    }
                                } else {
                                    p.sendMessage(
                                            Data.prefix + LanguageService.getDefault("gui.startcontrol.iscontroled", t));
                                    e.getView().close();
                                }
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.startcontrol.alreadyc", t));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.fakeop"))) {
                    if (p.hasPermission("troll.fakeop") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.fakeop.default", t));
                            e.getView().close();
                            t.sendMessage(LanguageService.getDefault("gui.fakeop.opm", t));
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.crash"))) {
                    if (p.hasPermission("troll.crash") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.crash.default", t));
                            e.getView().close();
                            t.kickPlayer(LanguageService.getDefault("gui.crash.message"));
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.freeze"))) {
                    if (p.hasPermission("troll.freeze") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.freeze.contains(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.freeze.off", t));
                                e.getView().close();
                                t.setWalkSpeed(0.2F);
                                t.setFlySpeed(0.1F);
                                ArrayUtils.freeze.remove(t);
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.freeze.on", t));
                                e.getView().close();
                                t.setWalkSpeed(0F);
                                t.setFlySpeed(0F);
                                ArrayUtils.freeze.add(t);
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.anticheat"))) {
                    if (p.hasPermission("troll.ac") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.anticheat.default", t));
                            t.sendMessage(LanguageService.getDefault("gui.anticheat.detected"));
                            t.teleport(new Location(t.getWorld(), t.getLocation().getX(),
                                    t.getLocation().getY() + 2, t.getLocation().getZ()));
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.lagging"))
                        || e.getCurrentItem().getType() == XMaterial.SHORT_GRASS.parseMaterial()) {
                    if (p.hasPermission("troll.lag") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (!ArrayUtils.lagging.contains(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.lag.on", t));
                                ArrayUtils.lagging.add(t);
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.lag.off", t));
                                ArrayUtils.lagging.remove(t);
                            }
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.arrest"))) {
                    if (p.hasPermission("troll.arrest") || p.hasPermission("troll.*")) {

                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            ArrayUtils.arrest.put(p, t.getLocation());
                            t.teleport(ArrayUtils.arrest.get(p));
                            // Y
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() - 1, t.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 2, t.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            // X
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY(), t.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY(), t.getLocation().getZ()))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            // Z
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY(), t.getLocation().getZ() + 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY(), t.getLocation().getZ() - 1))
                                    .setType(XMaterial.BEDROCK.parseMaterial());

                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.arrest", t));
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.rotateplayer"))) {
                    if (p.hasPermission("troll.rotate") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.rotateplayer.contains(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.rotate.off", t));
                                ArrayUtils.rotateplayer.remove(t);
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.rotate.on", t));
                                new TrollCommand().rop(t);
                                ArrayUtils.rotateplayer.add(t);
                            }
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.randomteleport"))) {
                    if (p.hasPermission("troll.randomtp") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.randomtp.contains(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.rndmtp.off", t));
                                ArrayUtils.randomtp.remove(t);
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.rndmtp.on", t));
                                ArrayUtils.randomtp.add(t);
                                new TrollCommand().rtp(t);
                            }
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.tnttrace"))) {
                    if (p.hasPermission("troll.tnttrace") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            InvManager.openConfirmationInv(p, e.getCurrentItem());
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.wtf"))) {
                    if (p.hasPermission("troll.wtf") || p.hasPermission("troll.*")) {

                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {

                            t.addPotionEffect(
                                    new PotionEffect(XPotion.BLINDNESS.getPotionEffectType(), 200, 5, true));
                            t.addPotionEffect(new PotionEffect(XPotion.NAUSEA.getPotionEffectType(), 200, 2));
                            t.addPotionEffect(
                                    new PotionEffect(XPotion.SLOWNESS.getPotionEffectType(), 200, 10, true));

                            ArrayUtils.wtf.put(p, new BukkitRunnable() {
                                int countdown = 4;

                                @Override
                                public void run() {

                                    if (countdown == 0) {
                                        t.teleport(t.getLocation());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.GLASS.parseMaterial());
                                        t.chat("Help me Pls im stucked ;-; I dont know where im pls help!!!");
                                        Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
                                        return;
                                    }

                                    if (countdown == 4) {
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() - 1, t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() - 1, t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() - 1, t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() - 1, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() - 1, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.teleport(t.getLocation());
                                    }

                                    if (countdown == 3) {
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY(), t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY(), t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY(), t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY(), t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY(), t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY(), t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY(), t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY(), t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() + 1, t.getLocation().getZ()))
                                                .setType(XMaterial.GLASS.parseMaterial());
                                        t.teleport(t.getLocation());
                                    }

                                    if (countdown == 2) {
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.GLASS.parseMaterial());
                                        t.teleport(t.getLocation());
                                    }

                                    if (countdown == 1) {
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() + 2, t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() + 2, t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() + 2, t.getLocation().getZ()))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                                        t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                                        t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
                                                .setType(XMaterial.BEDROCK.parseMaterial());
                                        t.getWorld()
                                                .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                                        t.getLocation().getY() + 1, t.getLocation().getZ()))
                                                .setType(XMaterial.GLASS.parseMaterial());
                                        t.teleport(t.getLocation());
                                    }

                                    if (countdown <= 0) {
                                        Bukkit.getScheduler().cancelTask(ArrayUtils.wtf.get(p).getTaskId());
                                    }
                                    countdown--;
                                }
                            });
                            ArrayUtils.wtf.get(p).runTaskTimer(Main.getInstance(), 0L, 20L);

                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.wtf", t));
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.webtrap"))) {
                    if (p.hasPermission("troll.webtrap") || p.hasPermission("troll.*")) {

                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.webtrap", t));
                            e.getView().close();

                            // Oben
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 2, t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY() + 2, t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY() + 2, t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY() + 2, t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY() + 2, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            // Mitte
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 1, t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY() + 1, t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY() + 1, t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY() + 1, t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY() + 1, t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            // Unten
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY(), t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY(), t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY(), t.getLocation().getZ()))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY(), t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY(), t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY(), t.getLocation().getZ() + 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX(),
                                            t.getLocation().getY(), t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() + 1,
                                            t.getLocation().getY(), t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());
                            t.getWorld()
                                    .getBlockAt(new Location(t.getWorld(), t.getLocation().getX() - 1,
                                            t.getLocation().getY(), t.getLocation().getZ() - 1))
                                    .setType(XMaterial.COBWEB.parseMaterial());

                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.lsd"))) {
                    if (p.hasPermission("troll.lsd") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            TrollV4API.LSD(t);
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.lsd", t));
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.guardian"))) {
                    if (p.hasPermission("troll.guardian") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            TrollV4API.GuardinShow(t, false);
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.guardian", t));
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.herobrine"))) {
                    if (p.hasPermission("troll.herobrine") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.herobrine.contains(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.herobrine.off", t));
                                e.getView().close();
                                ArrayUtils.herobrine.remove(t);
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.herobrine.on", t));
                                e.getView().close();
                                ArrayUtils.herobrine.add(t);
                            }
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.arrowspam"))) {
                    if (p.hasPermission("troll.arrowspam") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.userbowspam.contains(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.arrowspam.off", t));
                                e.getView().close();
                                ArrayUtils.arrowspam.get(t).cancel();
                                ArrayUtils.userbowspam.remove(t);
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.arrowspam.on", t));
                                e.getView().close();
                                ArrayUtils.userbowspam.add(t);
                                ArrayUtils.arrowspam.put(t, new BukkitRunnable() {

                                    @Override
                                    public void run() {

                                        Location loc = t.getLocation().clone();

                                        Arrow arrow = (Arrow) t.getWorld()
                                                .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                                        Main.getInstance().getRandom(5, 10),
                                                        Main.getInstance().getRandom(-10, 10)), Arrow.class);
                                        Location aloc = arrow.getLocation();
                                        Vector angle = new Vector(loc.getX() - aloc.getX(),
                                                loc.getY() - aloc.getBlockY(), loc.getZ() - aloc.getBlockZ());
                                        arrow.setVelocity(angle.normalize().multiply(2.0D));

                                        Arrow arrow2 = (Arrow) t.getWorld()
                                                .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                                        Main.getInstance().getRandom(5, 10),
                                                        Main.getInstance().getRandom(-10, 10)), Arrow.class);
                                        Location aloc2 = arrow2.getLocation();
                                        Vector angle2 = new Vector(loc.getX() - aloc2.getX(),
                                                loc.getY() - aloc2.getBlockY(), loc.getZ() - aloc2.getBlockZ());
                                        arrow2.setVelocity(angle2.normalize().multiply(2.0D));

                                        Arrow arrow3 = (Arrow) t.getWorld()
                                                .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                                        Main.getInstance().getRandom(5, 10),
                                                        Main.getInstance().getRandom(-10, 10)), Arrow.class);
                                        Location aloc3 = arrow3.getLocation();
                                        Vector angle3 = new Vector(loc.getX() - aloc3.getX(),
                                                loc.getY() - aloc3.getBlockY(), loc.getZ() - aloc3.getBlockZ());
                                        arrow3.setVelocity(angle3.normalize().multiply(2.0D));

                                        Arrow arrow4 = (Arrow) t.getWorld()
                                                .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                                        Main.getInstance().getRandom(5, 10),
                                                        Main.getInstance().getRandom(-10, 10)), Arrow.class);
                                        Location aloc4 = arrow4.getLocation();
                                        Vector angle4 = new Vector(loc.getX() - aloc4.getX(),
                                                loc.getY() - aloc4.getBlockY(), loc.getZ() - aloc4.getBlockZ());
                                        arrow4.setVelocity(angle4.normalize().multiply(2.0D));

                                        Arrow arrow5 = (Arrow) t.getWorld()
                                                .spawn(loc.clone().add(Main.getInstance().getRandom(-10, 10),
                                                        Main.getInstance().getRandom(5, 10),
                                                        Main.getInstance().getRandom(-10, 10)), Arrow.class);
                                        Location aloc5 = arrow5.getLocation();
                                        Vector angle5 = new Vector(loc.getX() - aloc5.getX(),
                                                loc.getY() - aloc5.getBlockY(), loc.getZ() - aloc5.getBlockZ());
                                        arrow5.setVelocity(angle5.normalize().multiply(2.0D));

                                    }
                                });
                                ArrayUtils.arrowspam.get(t).runTaskTimer(Main.getInstance(), 0L, 10L);
                            }
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.tornado"))) {
                    if (p.hasPermission("troll.tornado") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            InvManager.openConfirmationInv(p, e.getCurrentItem());
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.fakeinv"))) {
                    if (p.hasPermission("troll.fakeinv") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.fakeinv.containsKey(t)) {
                                InvSaver is = ArrayUtils.fakeinv.get(t);

                                t.getInventory().setArmorContents(is.getArmor());
                                t.getInventory().setExtraContents(is.getExtracont());
                                t.getInventory().setContents(is.getContent());

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.fakeinv.cancel", t));
                                e.getView().close();

                                ArrayUtils.fakeinv.remove(t);
                            } else {
                                InvSaver is;

                                if (ServerInfo.belowOrEqual(8)) {
                                    is = new InvSaver(t.getName(), t.getInventory().getContents(),
                                            t.getInventory().getArmorContents());
                                } else {
                                    is = new InvSaver(t.getName(), t.getInventory().getContents(),
                                            t.getInventory().getExtraContents(),
                                            t.getInventory().getArmorContents());
                                }

                                t.getInventory().clear();

                                ArrayUtils.fakeinv.put(t, is);

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.fakeinv.default", t));
                                e.getView().close();

                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        if (ArrayUtils.fakeinv.containsKey(t)) {
                                            InvSaver is = ArrayUtils.fakeinv.get(t);
                                            t.getInventory().setArmorContents(is.getArmor());
                                            if (ServerInfo.above(8)) {
                                                t.getInventory().setExtraContents(is.getExtracont());
                                            }
                                            t.getInventory().setContents(is.getContent());
                                            ArrayUtils.fakeinv.remove(t);
                                        }
                                        cancel();
                                    }
                                }.runTaskLater(Main.getInstance(), (Config.config.getInt("trolls.fakeinv.time") * 20L));

                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.noinvforyou"))) {
                    if (p.hasPermission("troll.fakeinv") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {

                            if (ArrayUtils.noinv.contains(t)) {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.noinv.off", t));
                                e.getView().close();

                                ArrayUtils.noinv.remove(t);
                            } else {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.noinv.on", t));
                                e.getView().close();

                                if (t.getOpenInventory() != null) {
                                    t.getOpenInventory().close();
                                }

                                ArrayUtils.noinv.add(t);
                            }

                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.slipperyhands"))) {
                    if (p.hasPermission("troll.slipperyhands") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {

                            if (ArrayUtils.noitem.contains(t)) {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.slipperyhands.off", t));
                                e.getView().close();

                                ArrayUtils.noitem.remove(t);
                            } else {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.slipperyhands.on", t));
                                e.getView().close();

                                if (t.getItemInHand() != null
                                        && t.getItemInHand().getType() != XMaterial.AIR.parseMaterial()) {
                                    t.getWorld().dropItemNaturally(t.getLocation(), t.getItemInHand())
                                            .setPickupDelay(20);
                                }
                                t.getInventory().setItem(t.getInventory().getHeldItemSlot(), null);

                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        Player sheesh = t;
                                        if (ArrayUtils.noitem.contains(sheesh)) {
                                            if (sheesh.getItemInHand() != null && sheesh.getItemInHand()
                                                    .getType() != XMaterial.AIR.parseMaterial()) {
                                                sheesh.getWorld().dropItemNaturally(sheesh.getLocation(),
                                                        sheesh.getItemInHand()).setPickupDelay(20);
                                                sheesh.getInventory()
                                                        .setItem(sheesh.getInventory().getHeldItemSlot(), null);
                                            }
                                        } else {
                                            cancel();
                                        }
                                    }
                                }.runTaskTimer(Main.getInstance(), 0L,
                                        (Config.config.getInt("trolls.slipperyhands.time") * 20L));

                                ArrayUtils.noitem.add(t);
                            }

                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.tntworld"))) {
                    if (p.hasPermission("troll.tntworld") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    Location oldl = t.getLocation();
                                    for (int x = 0; x < 200; x++) {
                                        for (int y = 0; y < 30; y++) {
                                            for (int z = 0; z < 200; z++) {
                                                if (new Location(oldl.getWorld(), oldl.getBlockX() - 100 + x,
                                                        oldl.getBlockY() - 7 + y, oldl.getBlockZ() - 100 + z)
                                                        .getBlock()
                                                        .getType() != XMaterial.AIR.parseMaterial()) {
                                                    Location l = new Location(oldl.getWorld(),
                                                            oldl.getBlockX() - 100 + x, oldl.getBlockY() - 7 + y,
                                                            oldl.getBlockZ() - 100 + z);
                                                    t.sendBlockChange(l, XMaterial.TNT.parseMaterial(), (byte) 0);
                                                }
                                            }
                                        }
                                    }
                                }
                            }.runTaskAsynchronously(Main.getInstance());
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.tntworld.default", t));
                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.rickroll"))) {
                    if (p.hasPermission("troll.rickroll") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (!PlayMusic.isPlaying(t)) {
                                PlayMusic.play(t, "plugins/TrollV4/rick.nbs");
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.rickroll.default", t));
                                e.getView().close();
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.rickroll.ishearing", t));
                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.dontstopjumping"))) {
                    if (p.hasPermission("troll.dontstopjumping") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.jumping.containsKey(t)) {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.dontstopjumping.off", t));

                                ArrayUtils.jumping.get(t).cancel();
                                ArrayUtils.jumping.remove(t);
                                e.getView().close();
                            } else {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.dontstopjumping.on", t));

                                ArrayUtils.jumping.put(t, Main.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(t, () -> {
                                    if (t.isOnGround()) {
                                        t.setVelocity(new Vector(0.0, 0.45, 0.0));
                                    }
                                }, 0, 20));

                                e.getView().close();

                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.deaf"))) {
                    if (p.hasPermission("troll.deaf") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.deaf.contains(t)) {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.deaf.off", t));

                                ArrayUtils.deaf.remove(t);
                                e.getView().close();
                            } else {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.deaf.on", t));

                                ArrayUtils.deaf.add(t);
                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.confused"))) {
                    if (p.hasPermission("troll.confused") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.confus.contains(t)) {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.confused.off", t));

                                ArrayUtils.confus.remove(t);
                                e.getView().close();
                            } else {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.confused.on", t));

                                ArrayUtils.confus.add(t);
                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.anvils"))) {
                    if (p.hasPermission("troll.anvils") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (ArrayUtils.anvils.containsKey(t)) {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.anvils.off", t));

                                ArrayUtils.anvils.get(t).cancel();
                                ArrayUtils.anvils.remove(t);
                                e.getView().close();
                            } else {

                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.anvils.on", t));

                                ArrayUtils.anvils.put(t, Main.getInstance().getFoliaLib().getScheduler().runAtEntityTimer(t, () -> {
                                    Location oldl = t.getLocation().add(0, 10, 0);
                                    Location randoml = LocationUtil.getLocFromRad(oldl, 5, 5);

                                    Main.getInstance().getFoliaLib().getScheduler().runAtLocation(randoml, y -> randoml.getWorld().spawnFallingBlock(randoml,
                                            XMaterial.DAMAGED_ANVIL.parseMaterial(), (byte) 0));
                                }, 20L,(Config.config.getInt("trolls.anvils.time") * 20L)));


                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.cows"))) {
                    if (p.hasPermission("troll.cows") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {

                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.cows", t));

                            Entity ent = t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.COW);
                            LivingEntity lent = (LivingEntity) t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.SILVERFISH);

                            lent.setPassenger(ent);

                            Entity ent2 = t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.COW);
                            LivingEntity lent2 = (LivingEntity) t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.SILVERFISH);

                            lent2.setPassenger(ent2);

                            Entity ent3 = t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.COW);
                            LivingEntity lent3 = (LivingEntity) t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.SILVERFISH);

                            lent3.setPassenger(ent3);

                            Entity ent4 = t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.COW);
                            LivingEntity lent4 = (LivingEntity) t.getWorld().spawnEntity(
                                    t.getLocation().add(t.getLocation().getDirection().multiply(2)),
                                    EntityType.SILVERFISH);

                            lent4.setPassenger(ent4);

                            e.getView().close();
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.giorno"))) {

                    if (p.hasPermission("troll.giorno") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (!ArrayUtils.jojo.containsKey(t)) {
                                PlayMusic.play(t, "plugins/TrollV4/giorno.nbs");
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.giorno.on", t));

                                Main.getInstance().getFoliaLib().getScheduler().runAtLocationLater(t.getLocation(), x -> {
                                    Location front = t.getLocation()
                                            .add(t.getLocation().getDirection().multiply(3));
                                    Location front2 = t.getLocation()
                                            .add(t.getLocation().getDirection().multiply(2));

                                    Main.getInstance().getFoliaLib().getScheduler().runAtEntityLater(t, y -> {
                                        NPCUtil.createGiorno(p, t, front, t.getLocation(),
                                                new ItemStack(XMaterial.ARROW.parseMaterial()));

                                        t.sendMessage("§6I Giorno Giovanna have a Dream!");

                                        Main.getInstance().getFoliaLib().getScheduler().runAtEntityLater(t, z -> NPCUtil.createGoldenWind(p, t, front2, t.getLocation(), null), 60L);
                                    }, 20L);
                                }, 10L);

                                e.getView().close();
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.giorno.off", t));

                                PlayMusic.stop(t);

                                Main.getInstance().getFoliaLib().getScheduler().runAtEntityLater(t, x -> NPCUtil.destroyNPCsFromPlayer(t), 20L);

                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.endcredits"))) {

                    if (p.hasPermission("troll.giorno") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.endcredits", t));
                            TrollV4API.EndGame(t);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.spookyworld"))) {

                    if (p.hasPermission("troll.spooky") || p.hasPermission("troll.*")) {
                        if (Bukkit.getWorld("SpookyWorld") == null) {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("gui.spooky.world"));
                            return;
                        }
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (!ArrayUtils.spooky.containsKey(t)) {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.spooky.on", t));
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.spooky.off", t));
                            }
                            TrollV4API.SpookyWorld(t);
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.loading"))) {
                    if (p.hasPermission("troll.loading") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            InvManager.openConfirmationInv(p, e.getCurrentItem());
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.trolls.vomit"))) {
                    if (p.hasPermission("troll.vomit") || p.hasPermission("troll.*")) {
                        Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                        if (t != null) {
                            if (!ArrayUtils.vomit.contains(t)) {
                                Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

                                    List<Item> toBeDelete = new ArrayList<>();

                                    @Override
                                    public void run() {
                                        if (!toBeDelete.isEmpty()) {
                                            for (Item item : toBeDelete) {
                                                item.remove();
                                            }
                                        }

                                        if (ArrayUtils.vomit.contains(t)) {
                                            ItemStack[] vomitItems = new ItemStack[]{SetItems.buildItem("§6VOMIT", XMaterial.BLACK_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.BLUE_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.BROWN_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.CYAN_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.GRAY_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.GREEN_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.LIME_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.MAGENTA_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.ORANGE_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.PINK_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.PURPLE_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.RED_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.WHITE_WOOL),
                                                    SetItems.buildItem("§6VOMIT", XMaterial.YELLOW_WOOL)};

                                            for (ItemStack item : vomitItems) {
                                                Item drop = t.getWorld().dropItem(t.getEyeLocation(), item);
                                                drop.setVelocity(t.getEyeLocation().getDirection().normalize().multiply(0.3D));
                                                toBeDelete.add(drop);
                                            }
                                        }
                                    }
                                }, 5L, 5L);
                                ArrayUtils.vomit.add(t);
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.vomit.on", t));
                            } else {
                                ArrayUtils.vomit.remove(t);
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.vomit.off", t));
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                            e.getView().close();
                        }
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
            } else if (e.getView().getTitle().equalsIgnoreCase("§2Server Troll Menu")) {
                e.getResult();
                e.setResult(Result.DENY);
                if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.servertrolls.tpall"))) {
                    if (p.hasPermission("troll.tpall") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.teleport(p.getLocation());
                        }
                        p.sendMessage(Data.prefix + LanguageService.getDefault("gui.tpall"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.servertrolls.fakeleave"))) {
                    if (p.hasPermission("troll.fakeleave") || p.hasPermission("troll.*")) {
                        e.getView().close();
                        Bukkit.broadcastMessage(LanguageService.getDefault("gui.fakeleave.message", p));
                        p.sendMessage(Data.prefix + LanguageService.getDefault("gui.fakeleave.default"));
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName()
                        .equalsIgnoreCase(Items.getItem("gui.servertrolls.hackmessage"))) {
                    if (p.hasPermission("troll.hackmessage") || p.hasPermission("troll.*")) {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("gui.hackserver"));

                        e.getView().close();

                        taskID2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                            int countdown = Config.config.getInt("trolls.hack.time");

                            @Override
                            public void run() {
                                if (countdown <= 0) {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        HS.Hack(all);
                                        Titles.send(all, 1, 20, 1, "§cHACKED", "§4" + RandomUtility.getRandomID());
                                        Bukkit.getScheduler().cancelTask(taskID2);
                                    }
                                    return;
                                }
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    HS.Hack2(all);
                                    Titles.send(all, 1, 20, 1, "§cHacking in " + countdown, "§4" + RandomUtility.getRandomID());
                                    all.damage(0.1D);
                                    countdown--;
                                }
                            }
                        }, 0, 20);
                    } else {
                        p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                        e.getView().close();
                    }
                }
            } else if (e.getView().getTitle().equalsIgnoreCase("§2Change§clog")) {
                e.setCancelled(true);
                e.getResult();
                e.setResult(Result.DENY);

                if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

                if (e.getCurrentItem().getType() == XMaterial.WRITABLE_BOOK.parseMaterial()) {
                    e.getView().close();
                    Changelog.credits((Player) e.getWhoClicked());
                }
            } else if (e.getView().getTitle().equalsIgnoreCase("§3Credits")) {
                e.setCancelled(true);
                e.getResult();
                e.setResult(Result.DENY);
            } else if (e.getView().getTitle().equalsIgnoreCase("§2Troll Confirmation Menu")) {
                e.setCancelled(true);
                e.getResult();
                e.setResult(Result.DENY);

                if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) return;

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cI changed my mind!")) {
                    e.getView().close();
                    return;
                }

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aI understand!")) {
                    ItemStack trollItem = e.getView().getItem(13);

                    if (trollItem == null || !trollItem.hasItemMeta() || !trollItem.getItemMeta().hasDisplayName())
                        return;

                    if (trollItem.getItemMeta().getDisplayName().equalsIgnoreCase(Items.getItem("gui.trolls.loading"))) {
                        if (p.hasPermission("troll.loading") || p.hasPermission("troll.*")) {
                            Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                            if (t != null) {
                                TrollV4API.InfiniteLoading(t);
                                p.sendMessage(Data.prefix + LanguageService.getDefault("gui.loading", t));
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                            e.getView().close();
                        }
                    } else if (trollItem.getItemMeta().getDisplayName().equalsIgnoreCase(Items.getItem("gui.trolls.tnttrace"))) {
                        if (p.hasPermission("troll.tnttrace") || p.hasPermission("troll.*")) {
                            Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                            if (t != null) {
                                if (ArrayUtils.tntp.contains(t)) {
                                    p.sendMessage(Data.prefix + LanguageService.getDefault("gui.tnttrace.off", t));
                                    ArrayUtils.tntp.remove(t);
                                } else {
                                    p.sendMessage(Data.prefix + LanguageService.getDefault("gui.tnttrace.on", t));
                                    new TrollCommand().spawnTnTAtPlayer(t);
                                    ArrayUtils.tntp.add(t);
                                }
                                e.getView().close();
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                            e.getView().close();
                        }
                    } else if (trollItem.getItemMeta().getDisplayName().equalsIgnoreCase(Items.getItem("gui.trolls.tornado"))) {
                        if (p.hasPermission("troll.tornado") || p.hasPermission("troll.*")) {
                            Player t = Bukkit.getPlayer(ArrayUtils.trolling.get(p.getName()));
                            if (t != null) {
                                if (ArrayUtils.tornado.contains(t)) {
                                    p.sendMessage(Data.prefix + LanguageService.getDefault("gui.tornado.off", t));
                                    e.getView().close();
                                    ArrayUtils.tornador.get(t).cancel();
                                    ArrayUtils.tornado.remove(t);
                                } else {
                                    p.sendMessage(Data.prefix + LanguageService.getDefault("gui.tornado.on", t));
                                    e.getView().close();
                                    ArrayUtils.tornado.add(t);
                                    ArrayUtils.tornador.put(t, Main.getInstance().getFoliaLib().getScheduler().runAtLocationTimer(t.getLocation(), new Runnable() {

                                        Random r = new Random();

                                        int rix = r.nextBoolean() ? -1 : 1;
                                        int riz = r.nextBoolean() ? -1 : 1;

                                        @Override
                                        public void run() {
                                            Location location1 = t.getLocation();
                                            FallingBlock o;
                                            Location location3 = new Location(location1.getWorld(), location1.getX(),
                                                    location1.getY() - 1, location1.getZ());

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX(),
                                                    location1.getY() - 1, location1.getZ() - 1);

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX() - 1,
                                                    location1.getY() - 1, location1.getZ() - 1);

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX() + 1,
                                                    location1.getY() - 1, location1.getZ() + 1);

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX() - 1,
                                                    location1.getY() - 1, location1.getZ() + 1);

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX() + 1,
                                                    location1.getY() - 1, location1.getZ() - 1);

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX() - 1,
                                                    location1.getY() - 1, location1.getZ());

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX() + 1,
                                                    location1.getY() - 1, location1.getZ());

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) / 4.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX(),
                                                    location1.getY() - 1, location1.getZ() + 1);

                                            if ((location3 != null) && location3.getBlock() != null && location3
                                                    .getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                                                o = location3.getWorld().spawnFallingBlock(
                                                        location3.getBlock().getLocation(),
                                                        location3.getBlock().getType(), (byte) 0);
                                                location3.getBlock().setType(XMaterial.AIR.parseMaterial());
                                                o.setFallDistance(0.0F);
                                                o.setVelocity(new Vector(
                                                        r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                        0.6D + r.nextInt(2) * 2.5D,
                                                        r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            }

                                            location3 = new Location(location1.getWorld(), location1.getX(),
                                                    location1.getY() - 1, location1.getZ());

                                            location3.getBlock().setType(XMaterial.STONE.parseMaterial());
                                            o = location1.getWorld().spawnFallingBlock(
                                                    location3.getBlock().getLocation(), location3.getBlock().getType(),
                                                    (byte) 0);
                                            o.setFallDistance(0.0F);
                                            o.setVelocity(new Vector(
                                                    r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                    0.6D + r.nextInt(2) * 2.5D,
                                                    r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            location3.getBlock().setType(XMaterial.AIR.parseMaterial());

                                            location3 = new Location(location1.getWorld(), location1.getX(),
                                                    location1.getY() - 1, location1.getZ() + 1);

                                            location3.getBlock().setType(XMaterial.STONE.parseMaterial());
                                            o = location1.getWorld().spawnFallingBlock(
                                                    location3.getBlock().getLocation(), location3.getBlock().getType(),
                                                    (byte) 0);
                                            o.setFallDistance(0.0F);
                                            o.setVelocity(new Vector(
                                                    r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                    0.6D + r.nextInt(2) * 2.5D,
                                                    r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            location3.getBlock().setType(XMaterial.AIR.parseMaterial());

                                            location3 = new Location(location1.getWorld(), location1.getX(),
                                                    location1.getY() - 1, location1.getZ() - 1);

                                            location3.getBlock().setType(XMaterial.STONE.parseMaterial());
                                            o = location1.getWorld().spawnFallingBlock(
                                                    location3.getBlock().getLocation(), location3.getBlock().getType(),
                                                    (byte) 0);
                                            o.setFallDistance(0.0F);
                                            o.setVelocity(new Vector(
                                                    r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                    0.6D + r.nextInt(2) * 2.5D,
                                                    r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            location3.getBlock().setType(XMaterial.AIR.parseMaterial());

                                            location3 = new Location(location1.getWorld(), location1.getX() + 1,
                                                    location1.getY() - 1, location1.getZ());

                                            location3.getBlock().setType(XMaterial.STONE.parseMaterial());
                                            o = location1.getWorld().spawnFallingBlock(
                                                    location3.getBlock().getLocation(), location3.getBlock().getType(),
                                                    (byte) 0);
                                            o.setFallDistance(0.0F);
                                            o.setVelocity(new Vector(
                                                    r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                    0.6D + r.nextInt(2) * 2.5D,
                                                    r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            location3.getBlock().setType(XMaterial.AIR.parseMaterial());

                                            location3 = new Location(location1.getWorld(), location1.getX() - 1,
                                                    location1.getY() - 1, location1.getZ());

                                            location3.getBlock().setType(XMaterial.STONE.parseMaterial());
                                            o = location1.getWorld().spawnFallingBlock(
                                                    location3.getBlock().getLocation(), location3.getBlock().getType(),
                                                    (byte) 0);
                                            o.setFallDistance(0.0F);
                                            o.setVelocity(new Vector(
                                                    r.nextBoolean() ? (rix * (0.25D + (r.nextInt(3) / 5))) : 0.0D,
                                                    0.6D + r.nextInt(2) * 2.5D,
                                                    r.nextBoolean() ? (riz * (0.25D + (r.nextInt(3) / 5))) : 0.0D));
                                            location3.getBlock().setType(XMaterial.AIR.parseMaterial());

                                            Location location2 = new Location(location1.getWorld(), location1.getX(),
                                                    location1.getY() + 3.0D, location1.getZ(),
                                                    location1.getYaw() + 15.0F, location1.getPitch());
                                            t.teleport(location2);

                                        }
                                    }, 0, 8));
                                }
                                e.getView().close();
                            } else {
                                p.sendMessage(Data.prefix + LanguageService.getDefault("noonline"));
                                e.getView().close();
                            }
                        } else {
                            p.sendMessage(Data.prefix + LanguageService.getDefault("nopermission"));
                            e.getView().close();
                        }
                    }
                }
            }
        } catch (Exception e2) {
            Sentry.captureException(e2);
            e2.printStackTrace();
        }
    }

}
