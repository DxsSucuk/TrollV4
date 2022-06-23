package de.presti.trollv4.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TabCompleter implements org.bukkit.command.TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().startsWith("troll")) {
			if(sender instanceof Player) {
				
				ArrayList<String> list = new ArrayList<String>();
				
				list.add(" ");
				list.add("help");
				list.add("server");
				list.add("items");
				list.add("v");
				list.add("gm");
				list.add("fly");
				list.add("changelog");
				list.add("credits");
				
				return list;
			}
		}
		return null;
	}

}
