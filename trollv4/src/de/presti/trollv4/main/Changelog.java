package de.presti.trollv4.main;

import org.bukkit.entity.Player;

import de.presti.trollv4.utils.UpdateChecker;

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
		new UpdateChecker(Main.instance).checkForUpdate();
		p.sendMessage(Data.prefix + "Changelog:");
		p.sendMessage(Data.prefix + "Version » " + Data.version);
		p.sendMessage(Data.cp + "DontStopJumping");
		p.sendMessage(Data.cp + "Deaf");
		p.sendMessage(Data.cp + "Confused");
		p.sendMessage(Data.cu + "TnTWorld 1.8 fix");
		p.sendMessage(Data.cu + "Guardian 1.9-1.16 Support");
		p.sendMessage(Data.cu + "Config Updater Fixed");
		p.sendMessage(Data.prefix + "Version » 4.3.6");
		p.sendMessage(Data.cp + "AutoDependDownloader");
		p.sendMessage(Data.cu + "Code optimization");
		p.sendMessage(Data.prefix + "Version » 4.3.5");
		p.sendMessage(Data.cp + "Rick Roll");
		p.sendMessage(Data.cp + "TnT World");
		p.sendMessage(Data.cu + "SlipperHands Message Fix");
		p.sendMessage(Data.cu + "StopControl Fix");
		p.sendMessage(Data.prefix + "Version » 4.3.4");
		p.sendMessage(Data.cp + "No Inv");
		p.sendMessage(Data.cp + "Slippery Hands");
		p.sendMessage(Data.cu + "Gui Select Fixes");
		p.sendMessage(Data.cu + "Gui Informations");
		p.sendMessage(Data.cu + "Some Trolls got recoded");
		p.sendMessage(Data.prefix + "Version » 4.3.3");
		p.sendMessage(Data.cp + "1.16 Support");
		p.sendMessage(Data.cp + "Almost every Troll works with 1.9+!");
		p.sendMessage(Data.cu + "CrossVersion Errors");
		p.sendMessage(Data.prefix + "Version » 4.3.2");
		p.sendMessage(Data.cu + "Little Bug Fix");
		p.sendMessage(Data.prefix + "Version » 4.3.1");
		p.sendMessage(Data.cp + "Full Custome Language Support!");
		p.sendMessage(Data.cu + "Fixed some 1.15 errors");
		p.sendMessage(Data.prefix + "Version » 4.3.0");
		p.sendMessage(Data.cp + "Tornado");
		p.sendMessage(Data.cu + "Error Fixes!");
		p.sendMessage(Data.cu + "Working on 1.14 and 1.15 Support");
		p.sendMessage(Data.cu + "Bug Fixes!");
		p.sendMessage(Data.cu + "Cloud Connection Fix!");
		p.sendMessage(Data.prefix + "Version » 4.2.9");
		p.sendMessage(Data.cu + "Hotfix!");
		p.sendMessage(Data.prefix + "Version » 4.2.8");
		p.sendMessage(Data.cu + "Hotfix!");
		p.sendMessage(Data.prefix + "Version » 4.2.7");
		p.sendMessage(Data.cp + "Herobrine");
		p.sendMessage(Data.cp + "ArrowSpam");
		p.sendMessage(Data.cu + "Consol Spam Fixed");
		p.sendMessage(Data.cu + "Control Fix");
		p.sendMessage(Data.cu + "Cloud Connection");
		p.sendMessage(Data.prefix + "Version » 4.2.6");
		p.sendMessage(Data.cp + "LSD Troll Fixed");
		p.sendMessage(Data.cu + "TrollV4API Updated");
		p.sendMessage(Data.cm + "Changelog 4.2.0 > Removed");
		p.sendMessage(Data.cu + "Player Choice GUI Fix");
		p.sendMessage(Data.prefix + "Version » 4.2.5");
		p.sendMessage(Data.cp + "LSD");
		p.sendMessage(Data.cp + "Guardian");
		p.sendMessage(Data.cu + "API Update");
		p.sendMessage(Data.cu + "Bug Fixes");
		p.sendMessage(Data.cm + "AutoUpdater (Because of an StartLoop)");
		p.sendMessage(Data.prefix + "Version » 4.2.4");
		p.sendMessage(Data.cp + "TrollV4API");
		p.sendMessage(Data.prefix + "Version » 4.2.3");
		p.sendMessage(Data.cp + "AutoUpdater");
		p.sendMessage(Data.cu + "Code Cleaning");
		p.sendMessage(Data.cu + "Crash Fix");
		p.sendMessage(Data.prefix + "Version » 4.2.2");
		p.sendMessage(Data.cp + "SRC Release!");
		p.sendMessage(Data.cu + "Security Update");
		p.sendMessage(Data.cu + "Cloud Crash Fix");
		p.sendMessage(Data.prefix + "Version » 4.2.1");
		p.sendMessage(Data.cp + "Corporation with CrashedDevelopment");
		p.sendMessage(Data.cu + "Cloud FireWall Update");
		p.sendMessage(Data.cu + "Security Updates");
		p.sendMessage(Data.prefix + "Version » 4.2.0");
		p.sendMessage(Data.cp + "WebTrap");
		p.sendMessage(Data.cp + "§cWTF");
		p.sendMessage(Data.cu + "Animation Bug Fix!");
	}
	
	public static void sendCredits(Player p) {
		credits(p);
	}
	
	public static void credits(Player p) {
			p.sendMessage(Data.prefix + "§3Devs§7:");
			p.sendMessage(Data.prefix + "Presti(DxsSucuk)");
			p.sendMessage(Data.prefix + "David(Davidjordan2006)");
			p.sendMessage(Data.prefix + "§3Ideas§7:");
			p.sendMessage(Data.prefix + "CrashDezz");
			p.sendMessage(Data.prefix + "Vincelive");
			p.sendMessage(Data.prefix + "Garkolym");
			p.sendMessage(Data.prefix + "§3THANKS TO§7:");
			p.sendMessage(Data.prefix + "Lara");
			p.sendMessage(Data.prefix + "David");
			p.sendMessage(Data.prefix + "Corsin");
		    p.sendMessage(Data.prefix + "Maxi");
			p.sendMessage(Data.prefix + "Sam");
			p.sendMessage(Data.prefix + "Noah");
			p.sendMessage(Data.prefix + "Fabian");
			p.sendMessage(Data.prefix + "Vince");
			p.sendMessage(Data.prefix + "Husham");
			p.sendMessage(Data.prefix + "Bivieh");
			p.sendMessage(Data.prefix + "Robin");
			p.sendMessage(Data.prefix + "und " + p.getName());	
	}

}
