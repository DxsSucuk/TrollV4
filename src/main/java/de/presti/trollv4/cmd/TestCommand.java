package de.presti.trollv4.cmd;

import de.presti.trollv4.api.TrollV4API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender.hasPermission("troll.*")) {
				TrollV4API.sendGameStateChange((Player) sender, 11, 1);
				sender.sendMessage("Pog");
			}
		}
		return false;
	}

}
