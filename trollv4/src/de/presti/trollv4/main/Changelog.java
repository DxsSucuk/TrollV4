package de.presti.trollv4.main;

import org.bukkit.entity.Player;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 13.07.2019 / 19:30:24											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Changelog {
	
	public static void sendlog(Player p) {
		log(p);
	}
	
	public static void log(Player p) {
		p.sendMessage(Data.prefix + "Changelog:");
		p.sendMessage(Data.prefix + "Version » " + Data.version);
		p.sendMessage(Data.cp + "Added ServerSoftware Detection!");
		p.sendMessage(Data.cp + "Added Demoscreen 1.16 Support!");
		p.sendMessage(Data.cp + "Everything is John Cena Troll!");
		p.sendMessage(Data.cp + "Giorno Giovani Troll!");
		p.sendMessage(Data.cp + "Raining Anvil!");
		p.sendMessage(Data.cp + "Minecraft EndCredits!");
		p.sendMessage(Data.cp + "Hostile Cow!");
		p.sendMessage(Data.cu + "Removed Changelog < 4.4.0");
		p.sendMessage(Data.prefix + "Version » 4.4.7");
		p.sendMessage(Data.cp + "Added Language Singapore (by the Community)");
		p.sendMessage(Data.cp + "TnT World is now ASync!");
		p.sendMessage(Data.cu + "Fixed Performance Problems with AutoUpdate");
		p.sendMessage(Data.cu + "Added more Warnings!");
		p.sendMessage(Data.prefix + "Version » 4.4.6");
		p.sendMessage(Data.cu + "Big Error Fix!");
		p.sendMessage(Data.prefix + "Version » 4.4.5");
		p.sendMessage(Data.cu + "Hotfix");
		p.sendMessage(Data.prefix + "Version » 4.4.4");
		p.sendMessage(Data.cp + "AutoUpdater");
		p.sendMessage(Data.cp + "Added an TabCompleter");
		p.sendMessage(Data.cp + "Config GUI");
		p.sendMessage(Data.cu + "Some API Trolls got an Documentation");
		p.sendMessage(Data.prefix + "Version » 4.4.3");
		p.sendMessage(Data.cp + "Added Async Heads");
		p.sendMessage(Data.cp + "Notification if i join the Server");
		p.sendMessage(Data.cp + "Added an Warning to some Trolls");
		p.sendMessage(Data.cp + "TnTTrace tnt spawn delay can be changed");
		p.sendMessage(Data.prefix + "Version » 4.4.2");
		p.sendMessage(Data.cu + "Hotfix!");
		p.sendMessage(Data.prefix + "Version » 4.4.1");
		p.sendMessage(Data.cu + "Fix for Cracked Server in GUI");
		p.sendMessage(Data.prefix + "Version » 4.4.0");
		p.sendMessage(Data.cp + "Russian Language");
		p.sendMessage(Data.cu + "Rickroll Error fix");
	}
	
	public static void sendCredits(Player p) {
		credits(p);
	}
	
	public static void credits(Player p) {
			p.sendMessage(Data.prefix + "§3Devs§7:");
			p.sendMessage(Data.prefix + "Presti(Prestigemaster62)");
			p.sendMessage(Data.prefix + "§3Code Support§7:");
			p.sendMessage(Data.prefix + "RyTheFirst");
			p.sendMessage(Data.prefix + "CryptoMorin");
			p.sendMessage(Data.prefix + "§3Ideas§7:");
			p.sendMessage(Data.prefix + "Garkolym");
			p.sendMessage(Data.prefix + "CrashDezz(CrashedTroll)");
			p.sendMessage(Data.prefix + "Minesuchtiiii(TrollBoss)");
			p.sendMessage(Data.prefix + "§3THANKS TO§7:");
			p.sendMessage(Data.prefix + "Lara");
			p.sendMessage(Data.prefix + "David");
			p.sendMessage(Data.prefix + "Sam");
			p.sendMessage(Data.prefix + "Alex");
			p.sendMessage(Data.prefix + "and " + p.getName());	
	}

}
