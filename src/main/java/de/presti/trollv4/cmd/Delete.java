package de.presti.trollv4.cmd;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.utils.plugin.PluginUtil;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2020											    *
*	Erstellt: 06.11.2020 / 19:36:02											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Delete implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender.hasPermission("troll.*")) {
				sender.sendMessage(
						Data.prefix + "Hey this command isnt for users its only here to delete the updater plugin!");
			}
		} else if (sender instanceof ConsoleCommandSender) {
			if (sender.hasPermission("troll.*")) {
				File f = new File("plugins/TrollV4Updater.jar");
				if (f.exists()) {
					try {
						if (PluginUtil.isLoaded("TrollV4Updater")) {
							PluginUtil.unloadPlugin("TrollV4Updater");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					f.delete();
				}
			}
		}
		return false;
	}

}
