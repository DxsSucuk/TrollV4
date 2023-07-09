package de.presti.trollv4.utils.crossversion;

import org.bukkit.entity.Player;

public class Titles {
    public static void sendTitle(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        send(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    public static void send(Player player, int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        try {
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        } catch (Exception exception) {
            com.cryptomorin.xseries.messages.Titles.sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
        }
    }
}
