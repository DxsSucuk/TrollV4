package de.presti.trollv4.config;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import de.presti.trollv4.main.Data;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 20.05.2020 / 22:17:20											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Language {

	public Language() {
		loadGerman();
		loadEnglish();
		loadCustom();
	}

	public static HashMap<String, String> messages = new HashMap<>();
	public static ArrayList<String> lang = new ArrayList<>();

	public static String getMessage(String path) {
		String m = "Couldnt find " + path + "! (Regenerate message.yml)";

		if (messages.containsKey(getLanguage() + "." + path)) {
			m = messages.get(getLanguage() + "." + path).replace("[VERSION]", Data.version);
		}

		return m;
	}

	public static String getMessage(String path, Player t) {
		String m = "Couldnt find " + path + "! (Regenerate message.yml)";

		if (messages.containsKey(getLanguage() + "." + path)) {
			m = messages.get(getLanguage() + "." + path).replace("[PLAYER]", t.getName());
		}

		return m;
	}

	public static String getMessageFromLanguage(String lang, String path) {
		String m = "Couldnt find " + lang + "." +  path + "! (Regenerate message.yml)";

		if (messages.containsKey(lang + "." + path)) {
			m = messages.get(lang + "." + path);
		}
		
		return m;
	}
	
	public static void addMessage(String language, String path, String message) {
		path = path.toLowerCase();
		if (!lang.contains(path)) {
			lang.add(path);
		}
		if (!messages.containsKey(language.toLowerCase() + "." + path)) {
			messages.put(language.toLowerCase() + "." + path, message);
		}
	}

	public static String getLanguage() {
		return Config.getconfig().getString("Language").toLowerCase();
	}

	public void loadGerman() {
		addMessage("de", "nopermission", "Du hast keine Rechte für diesen Command!");
		addMessage("de", "noonline", "Dieser Spieler ist nicht online!");
		addMessage("de", "command.troll.default", "Troll Menu wurde geoeffnet!");
		addMessage("de", "command.troll.help.line.1", "TrollV4 by Presti");
		addMessage("de", "command.troll.help.line.2", "Troll Modus §aAktiviert");
		addMessage("de", "command.troll.help.line.3", "Troll Vanish §aAktiviert");
		addMessage("de", "command.troll.help.line.4", "§4Commands:");
		addMessage("de", "command.troll.help.line.5", "§4Player Troll GUI §8- §c/troll");
		addMessage("de", "command.troll.help.line.6", "§4Server Troll GUI §8- §c/troll server");
		addMessage("de", "command.troll.help.line.7", "§4Item Troll GUI §8- §c/troll items");
		addMessage("de", "command.troll.help.line.8", "§4Troll Vanish §8- §c/troll v");
		addMessage("de", "command.troll.help.line.9", "§4Gamemode §8- §c/troll gm");
		addMessage("de", "command.troll.help.line.10", "§4Fly §8- §c/troll fly");
		addMessage("de", "command.troll.help.line.11", "§4Changelog §8- §c/troll changelog");
		addMessage("de", "command.troll.help.line.12", "§4Credits §8- §c/troll credits");
		addMessage("de", "command.troll.gm", "Du bist nun im Gamemode 1!");
		addMessage("de", "command.troll.fly.on", "Du kannst nun Fliegen!");
		addMessage("de", "command.troll.fly.off", "Du kannst nun nicht mehr Fliegen!");
		addMessage("de", "command.troll.vanish.on", "Du bist nun im Vanish!");
		addMessage("de", "command.troll.vanish.off", "Du hast den Vanish verlassen!");
		addMessage("de", "command.troll.items", "Item Troll Inv wurde geoeffnet!");
		addMessage("de", "command.troll.server", "Server Troll Inv wurde geoeffnet!");
		addMessage("de", "command.troll.notfound.line.1", "§8>");
		addMessage("de", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("de", "command.troll.notfound.line.3", "Alle Commands /troll help");
		addMessage("de", "command.troll.notfound.line.4", "TrollV4 von");
		addMessage("de", "command.troll.notfound.line.5", "Presti");
		addMessage("de", "command.troll.notfound.line.6", "§8>");
		addMessage("de", "command.troll.wrongargs", "Unbekannte Argumente bitte benutze /troll help");
		addMessage("de", "command.troll.noplayer", "Du bist kein Spieler!");
		addMessage("de", "gui.mlg", "Du hast den Spieler §c[PLAYER] §2einen §cMLG §2machen lassen!");
		addMessage("de", "gui.explode", "Du hast den Spieler §c[PLAYER] §2zum explodieren gebracht!");
		addMessage("de", "gui.fakehack.on", "Du hast §c[PLAYER] §2zum hacken gebracht!");
		addMessage("de", "gui.fakehack.off", "Du hast den Spieler §c[PLAYER] §2gezwungen aufzuhöhren!");
		addMessage("de", "gui.demo", "Der Spieler §c[PLAYER] §2sieht nun den Demoscreen!");
		addMessage("de", "gui.strike", "Du hast den Spieler §c[PLAYER] §2mit einem Blitz getroffen!");
		addMessage("de", "gui.hackuser", "Der Hackvorgang startet!");
		addMessage("de", "gui.rocket", "Du hast den Spieler §c[PLAYER] §2wie eine Rakete Fliegen lassen!");
		addMessage("de", "gui.spam", "Du hast den Spieler §c[PLAYER] §2voll gespamt!");
		addMessage("de", "gui.startcontrol.start", "Du kontrollierst nun §c[PLAYER]§2!");
		addMessage("de", "gui.startcontrol.cantcontrol", "Du kannst diesen Spieler nicht kontrollieren!");
		addMessage("de", "gui.startcontrol.yourself", "Du kannst nicht dich selbst kontrollieren!");
		addMessage("de", "gui.startcontrol.iscontroled", "Der Spieler wird schon kontrolliert!");
		addMessage("de", "gui.startcontrol.alreadyc", "Du kontrollierst schon jemanden anderen!");
		addMessage("de", "gui.stopcontrol.stop", "Du kontrollierst nun §c[PLAYER] §2nicht mehr!");
		addMessage("de", "gui.stopcontrol.nocontrol", "Du kontrollierst niemanden!");
		addMessage("de", "gui.fakeop.default", "Du hast §c[PLAYER] §2Fakeoped!");
		addMessage("de", "gui.fakeop.opm", "§7§o[Server: [PLAYER] wurde zum Operator ernannt]");
		addMessage("de", "gui.crash.default", "Du hast den Spieler §c[PLAYER] §2Crashen lassen!");
		addMessage("de", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("de", "gui.freeze.on", "Du hast §c[PLAYER] §2gefreezt");
		addMessage("de", "gui.freeze.off", "Du hast §c[PLAYER] §2unfreezt");
		addMessage("de", "gui.anticheat.default", "Das Anticheat fängt nun an §c[PLAYER] §2ui bemerken!");
		addMessage("de", "gui.anticheat.detected",
				"§7[§cPrestige§7-§cAntiCheat§7] §2Stop §cHacking(SPEED) §7[§1VL§7/§620§7]");
		addMessage("de", "gui.lag.on", "Der Spieler §c[PLAYER] §2fängt an zu laggen!");
		addMessage("de", "gui.lag.off", "Der Spieler §c[PLAYER] §2hört auf zu laggen!");
		addMessage("de", "gui.arrest", "Der Spieler §c[PLAYER] §2ist nun gefangen!");
		addMessage("de", "gui.rotate.on", "Der Spieler §c[PLAYER] §2dreht sich nun!");
		addMessage("de", "gui.rotate.off", "Der Spieler §c[PLAYER] §2dreht sich nun nicht mehr!");
		addMessage("de", "gui.rndmtp.on", "Der Spieler §c[PLAYER] §2wird durch die Luft teleportiert!");
		addMessage("de", "gui.rndmtp.off", "Der Spieler §c[PLAYER] §2wird nicht mehr durch die Luft teleportiert!");
		addMessage("de", "gui.tnttrace.on", "Hinter dem Spieler §c[PLAYER] §2wird nun TnT gespawnt!");
		addMessage("de", "gui.tnttrace.off", "Hinter dem Spieler §c[PLAYER] §2wird nun nicht mehr TnT gespawnt!");
		addMessage("de", "gui.wtf", "Der Spieler §c[PLAYER] §2wird verrückt!");
		addMessage("de", "gui.webtrap", "Der Spieler §c[PLAYER] §2ist nun in Cobweb gefangen!");
		addMessage("de", "gui.lsd", "Der Spieler §c[PLAYER] §2sieht nun die Welt anders!");
		addMessage("de", "gui.guardian", "Der Spieler §c[PLAYER] §2hat nun den Guardian effekt!");
		addMessage("de", "gui.herobrine.on", "Der Spieler §c[PLAYER] §2ist nun Herobrine!");
		addMessage("de", "gui.herobrine.off", "Der Spieler §c[PLAYER] §2ist nun nicht mehr Herobrine!");
		addMessage("de", "gui.arrowspam.on", "Der Spieler §c[PLAYER] §2wird nun von Pfeilen gejagt!");
		addMessage("de", "gui.arrowspam.off", "Der Spieler §c[PLAYER] §2wird nun nicht mehr von Pfeilen gejagt!");
		addMessage("de", "gui.tornado.on", "Der Spieler §c[PLAYER] §2wird nun von einem Tornado verfolgt!");
		addMessage("de", "gui.tornado.off", "Der Spieler §c[PLAYER] §2wird nun nicht mehr von einem Tornado verfolgt!");
		addMessage("de", "gui.fakeinv.default", "Der Spieler §c[PLAYER] §2hat nun für 5 Sekunden sein Inventar verloren!");
		addMessage("de", "gui.fakeinv.cancel", "Der Spieler §c[PLAYER] §2hat nun sein Inventar wieder bekommen!");
		addMessage("de", "gui.noinv.on", "Der Spieler §c[PLAYER] §2kann nun keine Inventare mehr öffnen!");
		addMessage("de", "gui.noinv.off", "Der Spieler §c[PLAYER] §2kann nun wieder Inventare öffnen!");
		addMessage("de", "gui.slipperyhands.on", "Der Spieler §c[PLAYER] §2hat seine Hände mit Butter vollgeschmiert!");
		addMessage("de", "gui.slipperyhands.off", "Der Spieler §c[PLAYER] §2hat die Butter runter gewaschen!");
		addMessage("de", "gui.tntworld.default", "Der Spieler §c[PLAYER] §2sieht nun in einem Radius von 75 blöcken nur TNT!");
		addMessage("de", "gui.rickroll.default", "Der Spieler §c[PLAYER] §2wurde nun gerickrolled!");
		addMessage("de", "gui.rickroll.ishearing", "Der Spieler §c[PLAYER] §2wird schon gerickrolled!");
		addMessage("de", "gui.dontstopjumping.on", "Der Spieler §c[PLAYER] §2kann nicht aufhören zu springen!");
		addMessage("de", "gui.dontstopjumping.off", "Der Spieler §c[PLAYER] §2hört nun auf zu springen!");
		addMessage("de", "gui.deaf.on", "Der Spieler §c[PLAYER] §2hört nichts mehr!");
		addMessage("de", "gui.deaf.off", "Der Spieler §c[PLAYER] §2hört wieder etwas!");
		addMessage("de", "gui.confused.on", "Der Spieler §c[PLAYER] §2ist nun verwirrt!");
		addMessage("de", "gui.confused.off", "Der Spieler §c[PLAYER] §2ist nun wieder bei Sinnen!");
		addMessage("de", "gui.tpall", "Alle Spieler wurden zu dir teleportiert");
		addMessage("de", "gui.fakeleave.default", "Eine FakeLeave Message wurde in den Chat geschickt!");
		addMessage("de", "gui.fakeleave.message", "§6[PLAYER] left the Game.");
		addMessage("de", "gui.hackserver", "Der Hackvorgang startet!");
		addMessage("de", "control.world", "§cWelt wurde geweschelt!");
		addMessage("de", "control.distance", "§cZuweit entfernt");
	}

	public void loadEnglish() {
		addMessage("us", "nopermission", "You do not have the permission for this command!");
		addMessage("us", "noonline", "This Player is not online!");
		addMessage("us", "command.troll.default", "Troll Menu has been opened!");
		addMessage("us", "command.troll.help.line.1", "TrollV4 by Presti");
		addMessage("us", "command.troll.help.line.2", "Troll Mode §aActive");
		addMessage("us", "command.troll.help.line.3", "Troll Vanish §aActive");
		addMessage("us", "command.troll.help.line.4", "§4Commands:");
		addMessage("us", "command.troll.help.line.5", "§4Player Troll GUI §8- §c/troll");
		addMessage("us", "command.troll.help.line.6", "§4Server Troll GUI §8- §c/troll server");
		addMessage("us", "command.troll.help.line.7", "§4Item Troll GUI §8- §c/troll items");
		addMessage("us", "command.troll.help.line.8", "§4Troll Vanish §8- §c/troll v");
		addMessage("us", "command.troll.help.line.9", "§4Gamemode §8- §c/troll gm");
		addMessage("us", "command.troll.help.line.10", "§4Fly §8- §c/troll fly");
		addMessage("us", "command.troll.help.line.11", "§4Changelog §8- §c/troll changelog");
		addMessage("us", "command.troll.help.line.12", "§4Credits §8- §c/troll credits");
		addMessage("us", "command.troll.gm", "Youre now in Gamemode 1!");
		addMessage("us", "command.troll.fly.on", "You can fly now!");
		addMessage("us", "command.troll.fly.off", "You cant fly anymore!");
		addMessage("us", "command.troll.vanish.on", "Youre now in Vanish!");
		addMessage("us", "command.troll.vanish.off", "Youre not longer in Vanish!");
		addMessage("us", "command.troll.items", "Item Troll Inv was opened!");
		addMessage("us", "command.troll.server", "Server Troll Inv was opened!");
		addMessage("us", "command.troll.notfound.line.1", "§8>");
		addMessage("us", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("us", "command.troll.notfound.line.3", "All Commands /troll help");
		addMessage("us", "command.troll.notfound.line.4", "TrollV4 by");
		addMessage("us", "command.troll.notfound.line.5", "Presti");
		addMessage("us", "command.troll.notfound.line.6", "§8>");
		addMessage("us", "command.troll.wrongargs", "Unknown arguments please use /troll help");
		addMessage("us", "command.troll.noplayer", "Youre not a Player!");
		addMessage("us", "gui.mlg", "You let the Player §c[PLAYER] §2do an §cMLG§2!");
		addMessage("us", "gui.explode", "You made the Player §c[PLAYER] §2explode!");
		addMessage("us", "gui.fakehack.on", "You made the Player §c[PLAYER] §2hack!");
		addMessage("us", "gui.fakehack.off", "You forced the Player §c[PLAYER] §2to stop!");
		addMessage("us", "gui.demo", "The Player §c[PLAYER] §2is now seeing a Demo screen!");
		addMessage("us", "gui.strike", "You hit the Player §c[PLAYER] §2with a lightning bolt!");
		addMessage("us", "gui.hackuser", "The hacking process starts!");
		addMessage("us", "gui.rocket", "You made the Player §c[PLAYER] §2fly like a rocket!");
		addMessage("us", "gui.spam", "You've completely blundered the Player §c[PLAYER]§2!");
		addMessage("us", "gui.startcontrol.start", "Youre Controlling §c[PLAYER]§2!");
		addMessage("us", "gui.startcontrol.cantcontrol", "You cant control this Player!");
		addMessage("us", "gui.startcontrol.yourself", "You cant control yourself!");
		addMessage("us", "gui.startcontrol.iscontroled", "The player is already controlled!");
		addMessage("us", "gui.startcontrol.alreadyc", "You already control someone else!");
		addMessage("us", "gui.stopcontrol.stop", "You stopped Controlling §c[PLAYER]§2!");
		addMessage("us", "gui.stopcontrol.nocontrol", "You control nobody!");
		addMessage("us", "gui.fakeop.default", "You Fakeopped §c[PLAYER]§2!");
		addMessage("us", "gui.fakeop.opm", "§7§o[Server: Opped [PLAYER]]");
		addMessage("us", "gui.crash.default", "You let the Player §c[PLAYER] §2Crash!");
		addMessage("us", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("us", "gui.freeze.on", "You freezed the Player §c[PLAYER]§2!");
		addMessage("us", "gui.freeze.off", "You unfreezed the Player §c[PLAYER]§2!");
		addMessage("us", "gui.anticheat.default", "The Anticheat started detecting §c[PLAYER]§2!");
		addMessage("us", "gui.anticheat.detected",
				"§7[§cPrestige§7-§cAntiCheat§7] §2Stop §cHacking(SPEED) §7[§1VL§7/§620§7]");
		addMessage("us", "gui.lag.on", "The User §c[PLAYER] §2is now lagging!");
		addMessage("us", "gui.lag.off", "The User §c[PLAYER] §2stopped lagging");
		addMessage("us", "gui.arrest", "The User §c[PLAYER] §2is Arrested!");
		addMessage("us", "gui.rotate.on", "The User §c[PLAYER] §2dreht sich nun!");
		addMessage("us", "gui.rotate.off", "The User §c[PLAYER] §2stopped rotating!");
		addMessage("us", "gui.rndmtp.on", "The User §c[PLAYER] §2starts to be teleported through the air!");
		addMessage("us", "gui.rndmtp.off", "The User §c[PLAYER] §2stops to be teleported through the air!");
		addMessage("us", "gui.tnttrace.on", "Behind the User §c[PLAYER] §2now a trace of TnT spawned!");
		addMessage("us", "gui.tnttrace.off", "Behind the User §c[PLAYER] §2no trace of TnT is spawned anymore!");
		addMessage("us", "gui.wtf", "The User §c[PLAYER] §2is going crazy!");
		addMessage("us", "gui.webtrap", "The User §c[PLAYER] §2is now stucked in Cobweb!");
		addMessage("us", "gui.lsd", "The User §c[PLAYER] §2is now seeing the World in another Perspectiv!");
		addMessage("us", "gui.guardian", "The User §c[PLAYER] §2now has the Guardian effect!");
		addMessage("us", "gui.herobrine.on", "The User §c[PLAYER] §2is now Herobrine!");
		addMessage("us", "gui.herobrine.off", "The User §c[PLAYER] §2isnt anymore Herobrine!");
		addMessage("us", "gui.arrowspam.on", "The User §c[PLAYER] §2is now getting spammend by Arrows!");
		addMessage("us", "gui.arrowspam.off", "The User §c[PLAYER] §2is now not getting spammend by Arrows anymore!");
		addMessage("us", "gui.tornado.on", "The User §c[PLAYER] §2is now getting chased by a tornado!");
		addMessage("us", "gui.tornado.off", "The User §c[PLAYER] §2is no longer pursued by a tornado!");
		addMessage("us", "gui.fakeinv.default", "The User §c[PLAYER] §2lost his Inventory for 5 Seconds!");
		addMessage("us", "gui.fakeinv.cancel", "The User §c[PLAYER] §2got his Inventory back!");
		addMessage("us", "gui.noinv.on", "The User §c[PLAYER] §2cant open any Inventory anymore!");
		addMessage("us", "gui.noinv.off", "The User §c[PLAYER] §2can open any Inventory again!");
		addMessage("us", "gui.slipperyhands.on", "The User §c[PLAYER] §2played with some butter!");
		addMessage("us", "gui.slipperyhands.off", "The User §c[PLAYER] §2cleaned his hands!");
		addMessage("us", "gui.tntworld.default", "The User §c[PLAYER] §2now sees only TnT in his World!");
		addMessage("us", "gui.rickroll.default", "The User §c[PLAYER] §2is getting rickrolled!");
		addMessage("us", "gui.rickroll.ishearing", "The User §c[PLAYER] §2is already getting rickrolled!");
		addMessage("us", "gui.dontstopjumping.on", "The User §c[PLAYER] §2cant stop jumping!");
		addMessage("us", "gui.dontstopjumping.off", "The User §c[PLAYER] §2has stopped jumping!");
		addMessage("us", "gui.deaf.on", "The User §c[PLAYER] §2cant hear anything anymore!");
		addMessage("us", "gui.deaf.off", "The User §c[PLAYER] §2can hear something again!");
		addMessage("us", "gui.confused.on", "The User §c[PLAYER] §2is now confused!");
		addMessage("us", "gui.confused.off", "The User §c[PLAYER] §2got some milk!");
		addMessage("us", "gui.tpall", "All players were teleported to you!");
		addMessage("us", "gui.fakeleave.default", "A Fake Message has been Posted in the Chat!");
		addMessage("us", "gui.fakeleave.message", "§6[PLAYER] left the Game.");
		addMessage("us", "gui.hackserver", "The hacking process starts!");
		addMessage("us", "control.world", "§4SWITCHED WORLDS");
		addMessage("us", "control.distance", "§4TOO FAR AWAY");
	}

	public void loadCustom() {
		for (String s : lang) {
			addMessage("custome", s, Config.getconfig2().getString(s));
			addMessage("custom", s, Config.getconfig2().getString(s));
		}
	}

	public static void clearAll() {
		lang.clear();
		messages.clear();
	}

}
