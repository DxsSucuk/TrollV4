package de.presti.trollv4.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.presti.trollv4.main.*;
import de.presti.trollv4.utils.*;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 18.04.2019 / 23:00:04												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Join implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(p.getUniqueId().toString().trim().equalsIgnoreCase("1c32b55b-d458-4347-a579-8754f4510081")) {
			p.sendMessage(Data.prefix + "Plugin Version: " + Data.version);
			p.sendMessage(Data.prefix + "Server Version: " + Main.version);
			p.sendMessage(Data.prefix + "Server Language: " + Language.getLanguage());
		}
		
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (ArrayUtils.vanish.contains(all)) {
				p.hidePlayer(all);
			}
		}

		if (p.hasPermission("troll.help")) {
			if (Config.getconfig().getBoolean("AutoUpdate")) {
				if (!Data.version.equals(UpdateChecker.spigotPluginVersion)
						&& !(UpdateChecker.cvi > UpdateChecker.nvi)) {
					p.sendMessage(Data.prefix + "TrollV4 has a Update!");
					p.sendMessage(Data.prefix + "New Version: " + UpdateChecker.spigotPluginVersion);
					p.sendMessage(Data.prefix + "Your Version: " + Data.version);
					p.sendMessage(Data.prefix + "Download here: https://www.spigotmc.org/resources/" + UpdateChecker.ID
							+ "/updates");
				} else {
					p.sendMessage(Data.prefix + "TrollV4 has no Update");
				}
			}
		}
	}
}
