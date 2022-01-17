package de.presti.trollv4.util.server.packets;

import org.bukkit.entity.Player;

public class DemoScreen {
	
	public static void showDemoScreen(Player player) {
		Packets.sendGameStateChange(player, 5, 0);
	}

}