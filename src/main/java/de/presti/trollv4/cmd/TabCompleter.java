package de.presti.trollv4.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2020											    *
*	Erstellt: 07.11.2020 / 16:37:04											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
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
