package de.presti.trollv4.listener;

import java.util.Random;

import de.presti.trollv4.config.Config;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.config.language.LanguageService;
import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.player.ArrayUtils;
import de.presti.trollv4.utils.player.LocationUtil;
import de.presti.trollv4.utils.plugin.UpdateChecker;
import de.presti.trollv4.utils.server.ServerInfo;
import io.sentry.Sentry;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;

public class Event implements Listener {

    //region OVERALL

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (p.getUniqueId().toString().replace("-", "").equalsIgnoreCase("1c32b55bd4584347a5798754f4510081") && Config.getConfig().getBoolean("DevJoinMessage")) {
            p.sendMessage(Data.prefix + "Plugin Version: " + Data.version);
            p.sendMessage(Data.prefix + "Server Version: " + ServerInfo.getNMSVersion() + " - " + ServerInfo.getMcVersion());
            p.sendMessage(Data.prefix + "Server Software: " + ServerInfo.getServerSoftware());
            p.sendMessage(Data.prefix + "Server Language: " + LanguageService.getLocale());

            for (Player ops : Bukkit.getOnlinePlayers()) {
                if (ops.hasPermission("troll.*") || ops.isOp()) {
                    ops.sendMessage(Data.prefix + "Hey the Dev of this Plugin has joined your Server! (" + p.getDisplayName() + ")");
                }
            }
        }

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (ArrayUtils.vanish.contains(all)) {
                p.hidePlayer(all);
            }
        }

        if (p.hasPermission("troll.help")) {
            if (Config.getConfig().getBoolean("UpdateChecker")) {
                if (!Data.version.equals(Main.getInstance().updateChecker.spigotPluginVersion)) {
                    p.sendMessage(Data.prefix + "TrollV4 has a update!");
                    p.sendMessage(Data.prefix + "New Version: " + Main.getInstance().updateChecker.spigotPluginVersion);
                    p.sendMessage(Data.prefix + "Your Version: " + Data.version);
                    p.sendMessage(Data.prefix + "Download here: https://www.spigotmc.org/resources/" + UpdateChecker.ID
                            + "/updates");
                } else {
                    p.sendMessage(Data.prefix + "TrollV4 has no update");
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        ArrayUtils.removeFromAll(p);
    }

    //endregion OVERALL

    // NoInv

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getPlayer() instanceof Player) {
            Player p = (Player) e.getPlayer();
            if (ArrayUtils.noinv.contains(p)) {
                e.setCancelled(true);
                p.getOpenInventory().close();
            }
        }
    }

    // DEAF CONFUSED
    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {
        if (ArrayUtils.confus.contains(e.getPlayer())) {
            String msg = e.getMessage().replaceAll("a", "").replaceAll("e", "").replaceAll("i", "").replaceAll("o", "")
                    .replaceAll("u", "").replaceAll("A", "").replaceAll("E", "").replaceAll("I", "").replaceAll("O", "")
                    .replaceAll("U", "");
            if (!msg.isEmpty()) {
                e.setMessage(msg);
            } else {
                e.setCancelled(true);
            }
        }

        for (Player all : e.getRecipients()) {
            if (ArrayUtils.deaf.contains(all)) {
                if (e.getRecipients().contains(all)) {
                    e.getRecipients().remove(all);
                }
            }
        }

    }

    // LAG

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (ArrayUtils.lagging.contains(player)) {
            Material material = e.getBlockPlaced().getType();
            Location location = e.getBlockPlaced().getLocation();
            location.getWorld().getBlockAt(location).setType(XMaterial.AIR.parseMaterial());
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    location.getWorld().getBlockAt(location).setType(material);
                }
            }, 34);
        }
    }

    // LAG

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (ArrayUtils.lagging.contains(player)) {
            Material drops = e.getBlock().getType();
            e.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                if (e.getBlock().getType() != XMaterial.AIR.parseMaterial()) {
                    e.getBlock().getLocation().getWorld().getBlockAt(e.getBlock().getLocation())
                            .setType(XMaterial.AIR.parseMaterial());
                    if (e.getPlayer().getGameMode() == GameMode.SURVIVAL)
                        e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(drops));
                }
            }, 31);
        }
    }

    // FAKEHACKS FREEZE HEROBRINE LAG

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (ArrayUtils.freeze.contains(p)) {
            p.teleport(e.getFrom());
        }

        if (ArrayUtils.fc.contains(p)) {
            p.setAllowFlight(true);
            p.setVelocity(p.getLocation().getDirection().setZ(0.1D).setX(0.1D));
            p.setVelocity(p.getLocation().getDirection().setZ(-0.1D).setX(-0.1D));
            p.setVelocity(p.getLocation().getDirection().setY(-9));
            p.setAllowFlight(false);
            p.setWalkSpeed(1.0E-6F);
        }

        if (ArrayUtils.herobrine.contains(p)) {
            if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY()
                    || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
                e.getFrom().getWorld().strikeLightning(e.getFrom());
                e.getFrom().getWorld().spawnEntity(LocationUtil.getLocFromRad(e.getFrom(), 10), EntityType.SILVERFISH);
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.playSound(e.getFrom(), XSound.ENTITY_ZOMBIE_VILLAGER_CURE.parseSound(), 0.05F, 0.05F);
                }
            }
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
            }
        }

        if (ArrayUtils.jojo.containsKey(p)) {
            p.teleport(e.getFrom());
        }

        if (ArrayUtils.lagging.contains(p)) {
            if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY()
                    || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
                if (new Random().nextInt(100) < 3) {
                    p.teleport(e.getFrom());
                }
            }
        }
    }

    // ARROWSPAM

    @EventHandler
    public static void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        if (ArrayUtils.userbowspam.contains(p)) {
            e.setDeathMessage(null);
            ArrayUtils.arrowspam.get(p).cancel();
            ArrayUtils.userbowspam.remove(p);
        }
    }

    // FIREBALL and MINIGUN

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerStickClick(PlayerInteractEvent e) {
        try {
            Player p = e.getPlayer();

            ItemStack fireball = new ItemStack(XMaterial.STICK.parseMaterial());
            ItemMeta meta = fireball.getItemMeta();
            meta.setDisplayName("§4FireBall");
            fireball.setItemMeta(meta);

            ItemStack ironaxe = new ItemStack(XMaterial.IRON_AXE.parseMaterial());
            ItemMeta meta2 = ironaxe.getItemMeta();
            meta2.setDisplayName("§4MiniGun");
            ironaxe.setItemMeta(meta2);

            if (((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) | e.getAction().equals(Action.RIGHT_CLICK_AIR)))
                    && (p.getItemInHand().getItemMeta().getDisplayName().equals("§4MiniGun"))
                    && (p.hasPermission("troll.minigun") || p.hasPermission("troll.*"))) {
                p.launchProjectile(Arrow.class);
                p.launchProjectile(Snowball.class);
                p.playSound(p.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_LAUNCH.parseSound(), 1.0F, 1.0F);
            }

            if (p.getItemInHand() != null) {
                if (((e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)))
                        && (p.getItemInHand().getItemMeta().getDisplayName().equals("§4FireBall"))
                        && (p.hasPermission("troll.fireball") || p.hasPermission("troll.*"))) {
                    p.launchProjectile(Fireball.class);
                    p.playSound(p.getLocation(), XSound.ENTITY_GENERIC_EXPLODE.parseSound(), 1.0F, 1.0F);
                }
            }
        } catch (Exception e1) {
            // e1.printStackTrace();
        }
    }

    // BOW ITEMS

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onArrowHit(ProjectileHitEvent e) {
        try {
            if (((e.getEntity() instanceof Arrow)) && ((e.getEntity().getShooter() instanceof Player))) {
                Player shooter = (Player) e.getEntity().getShooter();
                World world = e.getEntity().getWorld();
                Location loc = e.getEntity().getLocation();

                if (world == null) {
                    return;
                }

                if (loc == null) {
                    return;
                }

                if (shooter == null) {
                    return;
                }

                ItemStack itemInHand = shooter.getItemInHand();

                if (itemInHand == null) {
                    return;
                }

                ItemMeta itemMeta = itemInHand.getItemMeta();

                if (itemMeta == null) {
                    return;
                }

                if (itemMeta.getDisplayName() == null) {
                    return;
                }

                if (itemMeta.getDisplayName().equalsIgnoreCase(Items.getItem("gui.items.bow.tnt"))) {
                    if (shooter.hasPermission("troll.items")) {
                        world.createExplosion(loc, 2.0F);
                    }
                }
                if (itemMeta.getDisplayName().equalsIgnoreCase(Items.getItem("gui.items.bow.lava"))) {
                    if (shooter.hasPermission("troll.items")) {
                        world.getBlockAt(loc).setType(XMaterial.LAVA.parseMaterial());
                    }
                }
                if (itemMeta.getDisplayName().equalsIgnoreCase(Items.getItem("gui.items.bow.lightning"))) {
                    if (shooter.hasPermission("troll.items")) {
                        world.strikeLightning(loc);
                    }
                }
                if (itemMeta.getDisplayName().equalsIgnoreCase(Items.getItem("gui.items.bow.creeper"))) {
                    if (shooter.hasPermission("troll.items")) {
                        world.spawnEntity(loc, EntityType.CREEPER);
                    }
                }
                if (itemMeta.getDisplayName().equalsIgnoreCase(Items.getItem("gui.items.bow.bedrock"))) {
                    if (shooter.hasPermission("troll.items")) {
                        world.getBlockAt(loc).setType(XMaterial.BEDROCK.parseMaterial());
                    }
                }
            }
        } catch (Exception e1) {
            Sentry.captureException(e1);
            Main.getInstance().getLogger().severe("Couldn't Spawn! Please report: " + e1.getMessage());
        }
    }
}
