package de.presti.trollv4.utils.crossversion;

import java.lang.reflect.Constructor;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.presti.trollv4.main.Main;
import org.bukkit.entity.Player;

import de.presti.trollv4.api.Packets;

public class Titles {

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        send(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    public static void send(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        try {
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        } catch (Exception exception) {
            Main.logger.error("We couldn't send a Title with the default player.sendTitle methode, trying Packets now!");
            try {
                Object chat = Packets.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke(null, new Object[]{"{\"text\":\"" + title + "\"}"});
                Constructor<?> Constructor = Packets.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Packets.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE});
                Object timePacket = Constructor.newInstance(new Object[]{Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null), chat, fadeIn, stay, fadeOut});
                Constructor = Packets.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[]{Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Packets.getNMSClass("IChatBaseComponent")});
                Object titlePacket = Constructor.newInstance(new Object[]{Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chat});
                chat = Packets.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[]{String.class}).invoke(null, new Object[]{"{\"text\":\"" + subtitle + "\"}"});
                Object subtitlePacket = Constructor.newInstance(new Object[]{Packets.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chat});

                Packets.sendPacket(player, timePacket);
                Packets.sendPacket(player, titlePacket);
                Packets.sendPacket(player, subtitlePacket);
            } catch (Exception exception1) {
                Main.logger.error("We couldn't send a Title over the player.sendTitle methode, and neither did the Packets work.");
                exception.printStackTrace();
                exception1.printStackTrace();
            }
        }
    }
}
