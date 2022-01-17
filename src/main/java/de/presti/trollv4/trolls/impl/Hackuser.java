package de.presti.trollv4.trolls.impl;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import de.presti.trollv4.config.Items;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.trolls.Troll;
import de.presti.trollv4.util.FileUtil;
import de.presti.trollv4.util.player.ArrayUtils;
import de.presti.trollv4.util.server.packets.Titles;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Hackuser extends Troll {

    public Hackuser() {
        super("hackuser", Items.getItem("gui.trolls.hackuser"), "Let a Player think is going to be hacked!", "troll.hackuser", XMaterial.BEDROCK);
    }

    @Override
    public void onCall(Player p, Player t) {
        if (ArrayUtils.hackuser.containsKey(t)) {
            ArrayUtils.hackuser.get(t).cancel();
            ArrayUtils.hackuser.remove(t);
            ArrayUtils.hackuser.put(t, new BukkitRunnable() {
                int countdown = Main.instance.config.config.getInt("trolls.hack.time");

                @Override
                public void run() {
                    if (countdown <= 0) {
                        t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_BAT_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_WITHER_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_ENDERMAN_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_BLAZE_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_GHAST_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_ZOMBIE_DEATH.parseSound(), 1.0F, 1.0F);
                        t.getLocation().getWorld().createExplosion(t.getLocation().getX(), t.getLocation().getY(),
                                t.getLocation().getZ(), 2.0F, false, false);

                        Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + FileUtil.getRandomID(9));
                        ArrayUtils.hackuser.get(t).cancel();

                        return;
                    }

                    t.damage(0.5D);
                    t.playSound(t.getLocation(), XSound.ENTITY_HORSE_DEATH.parseSound(), 1.0F, 1.0F);
                    t.sendMessage("§c" + FileUtil.getRandomID(9));
                    Titles.send(t, 1, 20, 1, "§cHacking in " + countdown,
                            "§4" + FileUtil.getRandomID(9));
                    t.damage(0.1D);
                    countdown--;
                }

            });

        } else {
            ArrayUtils.hackuser.put(t, new BukkitRunnable() {
                int countdown = Main.instance.config.config.getInt("trolls.hack.time");

                @Override
                public void run() {
                    if (countdown <= 0) {
                        t.playSound(t.getLocation(), XSound.ENTITY_ENDER_DRAGON_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_BAT_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_WITHER_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_ENDERMAN_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_BLAZE_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_GHAST_DEATH.parseSound(), 1.0F, 1.0F);
                        t.playSound(t.getLocation(), XSound.ENTITY_ZOMBIE_DEATH.parseSound(), 1.0F, 1.0F);
                        t.getLocation().getWorld().createExplosion(t.getLocation().getX(), t.getLocation().getY(),
                                t.getLocation().getZ(), 2.0F, false, false);

                        Titles.send(t, 1, 20, 1, "§cHACKED", "§4" + FileUtil.getRandomID(9));
                        ArrayUtils.hackuser.get(t).cancel();

                        return;
                    }

                    t.damage(0.5D);
                    t.playSound(t.getLocation(), XSound.ENTITY_HORSE_DEATH.parseSound(), 1.0F, 1.0F);
                    t.sendMessage("§c" + FileUtil.getRandomID(9));

                    Titles.send(t, 1, 20, 1, "§cHacking in " + countdown,
                            "§4" + FileUtil.getRandomID(9));
                    t.damage(0.1D);
                    countdown--;
                }

            });
        }
        ArrayUtils.hackuser.get(t).runTaskTimer(Main.getInstance(), 0, 20);
    }
}
