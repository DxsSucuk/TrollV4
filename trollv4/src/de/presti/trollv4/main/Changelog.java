package de.presti.trollv4.main;

import org.bukkit.entity.Player;

import de.presti.trollv4.utils.Config;
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
		p.sendMessage(Data.prefix + "Version » 4.1.9");
		p.sendMessage(Data.cu + "Bug Fix");
		p.sendMessage(Data.prefix + "Version » 4.1.8");
		p.sendMessage(Data.cp + "1.10 are now going to be supported!");
		p.sendMessage(Data.cu + "1.10 and above support fixed!");
		p.sendMessage(Data.prefix + "Version » 4.1.7");
		p.sendMessage(Data.cu + "Community Surprise Fix");
		p.sendMessage(Data.prefix + "Version » 4.1.6");
		p.sendMessage(Data.cp + "Community Surprise System update!");
		p.sendMessage(Data.cu + "Pls Delete Config! For the new Config!");
		p.sendMessage(Data.cu + "Config Fixes!");
		p.sendMessage(Data.prefix + "Version » 4.1.5");
		p.sendMessage(Data.cu + "Error Fixes!");
		p.sendMessage(Data.prefix + "Version » 4.1.4");
		p.sendMessage(Data.cp + "Config Updater!");
		p.sendMessage(Data.cu + "Community Surprise Fix!");
		p.sendMessage(Data.prefix + "Version » 4.1.3");
		p.sendMessage(Data.cp + "Chance to deactivate Community Surprise!");
		p.sendMessage(Data.cu + "Fakeleave Fix!");
		p.sendMessage(Data.prefix + "Version » 4.1.2");
		p.sendMessage(Data.cp + "Community Update!");
		p.sendMessage(Data.cu + "UpdateChecker Fix!");
		p.sendMessage(Data.prefix + "Version » 4.1.1");
		p.sendMessage(Data.cu + "UpdateChecker Fix!");
		p.sendMessage(Data.cu + "Website Changelog");
		p.sendMessage(Data.prefix + "Version » 4.1.0");
		p.sendMessage(Data.cp + "Random Teleport");
		p.sendMessage(Data.cp + "TnT Spawn Troll");
		p.sendMessage(Data.cp + "Rotate Player");
		p.sendMessage(Data.prefix + "Version » 4.0.9");
		p.sendMessage(Data.cp + "Animation Lore added!");
		p.sendMessage(Data.cp + "New Update announcement (Weird World)");
		p.sendMessage(Data.cu + "Lagging Fixed!");
		p.sendMessage(Data.prefix + "Version » 4.0.8");
		p.sendMessage(Data.cp + "Arrest");
		p.sendMessage(Data.cp + "Lagging");
		p.sendMessage(Data.prefix + "Version » 4.0.7");
		p.sendMessage(Data.cu + "New Feature Fixed!");
		p.sendMessage(Data.prefix + "Version » 4.0.6");
		p.sendMessage(Data.cp + "AntiCheat option!");
		p.sendMessage(Data.cu + "Animations Bug Fix!");
		p.sendMessage(Data.cu + "Command Fix!");
		p.sendMessage(Data.cu + "Metrics Fix!");
		p.sendMessage(Data.prefix + "Version » 4.0.5");
		p.sendMessage(Data.cp + "Compatible with 1.10 - 1.14 (no support in case of errors!)");
		p.sendMessage(Data.cu + "New Metrics");
		p.sendMessage(Data.prefix + "Version » 4.0.4");
		p.sendMessage(Data.cu + "Bug Fixes");
		p.sendMessage(Data.cu + "Error Fixes");
		p.sendMessage(Data.cu + "Animation Fixes");
		p.sendMessage(Data.prefix + "Version » 4.0.3");
		p.sendMessage(Data.cp + "Plugin Metrics");
		p.sendMessage(Data.cp + "Animations");
		p.sendMessage(Data.cp + "Im now Activ :D");
		p.sendMessage(Data.cu + "Bug Fixes");
		p.sendMessage(Data.prefix + "Version » 4.0.2");
		p.sendMessage(Data.cp + "AutoUpdate can be Disabled");
		p.sendMessage(Data.cp + "Custome Messages");
		p.sendMessage(Data.cp + "More MLG Options");
		p.sendMessage(Data.cu + "Bug Fix (All Bugs i found)");
		p.sendMessage(Data.cu + "GUI More MLG Bug use Fix");
		p.sendMessage(Data.prefix + "Version » 4.0.1");
		p.sendMessage(Data.cu + "Update Checker");
		p.sendMessage(Data.prefix + "Version » 4.0.0");
		p.sendMessage(Data.cp + "GUI");
		p.sendMessage(Data.cu + "GUI Bug use Fix");
	}
	
	public static void sendCredits(Player p) {
		credits(p);
	}
	
	public static void credits(Player p) {
		if(Config.getconfig().getString("Language").equalsIgnoreCase("DE")) {
			p.sendMessage(Data.prefix + "§3Devs§7:");
			p.sendMessage(Data.prefix + "Presti(DxsSucuk)");
			p.sendMessage(Data.prefix + "David(Davidjordan2006)");
			p.sendMessage(Data.prefix + "§3Ideas§7:");
			p.sendMessage(Data.prefix + "CrashDezz");
			p.sendMessage(Data.prefix + "Vincelive");
			p.sendMessage(Data.prefix + "Garkolym");
			p.sendMessage(Data.prefix + "§3SPEZIALES DANKE AN§7:");
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
			p.sendMessage(Data.prefix + "und " + p.getName());	
			} else if(Config.getconfig().getString("Language").equalsIgnoreCase("US")) {
			p.sendMessage(Data.prefix + "§3Devs§7:");
			p.sendMessage(Data.prefix + "Presti(DxsSucuk)");
			p.sendMessage(Data.prefix + "David(Davidjordan2006)");
			p.sendMessage(Data.prefix + "§3Ideas§7:");
			p.sendMessage(Data.prefix + "CrashDezz");
			p.sendMessage(Data.prefix + "Vincelive");
			p.sendMessage(Data.prefix + "Garkolym");
			p.sendMessage(Data.prefix + "§3SPECIAL THANKS§7:");
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
			p.sendMessage(Data.prefix + "and " + p.getName());
			} else {
			p.sendMessage(Data.prefix + "§3Devs§7:");
			p.sendMessage(Data.prefix + "Presti(DxsSucuk)");
			p.sendMessage(Data.prefix + "David(Davidjordan2006)");
			p.sendMessage(Data.prefix + "§3Ideas§7:");
			p.sendMessage(Data.prefix + "CrashDezz");
			p.sendMessage(Data.prefix + "Vincelive");
			p.sendMessage(Data.prefix + "Garkolym");
			p.sendMessage(Data.prefix + "§3SPECIAL THANKS§7:");
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
			p.sendMessage(Data.prefix + "and " + p.getName());
		  }
	}

}
