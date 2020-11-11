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
		loadRussian();
		loadSingapore();
		loadCustom();
	}

	public static HashMap<String, String> messages = new HashMap<>();
	public static ArrayList<String> lang = new ArrayList<>();

	public static String getMessage(String path) {
		String m = "Couldnt find " + path + "! (Regenerate message.yml)";

		if (messages.containsKey(getLanguage() + "." + path)) {
			m = messages.get(getLanguage() + "." + path).replace("[VERSION]", Data.version);
		}

		m = m.replaceAll("&", "§");

		return m;
	}

	public static String getMessage(String path, Player t) {
		String m = "Couldnt find " + path + "! (Regenerate message.yml)";

		if (messages.containsKey(getLanguage() + "." + path)) {
			m = messages.get(getLanguage() + "." + path).replace("[PLAYER]", t.getName());
		}

		m = m.replaceAll("&", "§");

		return m;
	}

	public static String getMessageFromLanguage(String lang, String path) {
		String m = "Couldnt find " + lang + "." + path + "! (Regenerate message.yml)";

		if (messages.containsKey(lang + "." + path)) {
			m = messages.get(lang + "." + path);
		}

		m = m.replaceAll("&", "§");

		return m;
	}

	public static String getMessageFromLanguageRaw(String lang, String path) {
		String m = "Couldnt find " + lang + "." + path + "! (Regenerate message.yml)";

		if (messages.containsKey(lang + "." + path)) {
			m = messages.get(lang + "." + path);
		}

		return m;
	}

	public static void addMessage(String language, String path, String message) {
		path = path.toLowerCase();
		message = message.replaceAll("§", "&");

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
		addMessage("de", "command.troll.help.line.2", "Troll Modus §aAktiviert!");
		addMessage("de", "command.troll.help.line.3", "Troll Vanish §aAktiviert!");
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
		addMessage("de", "gui.fakehack.off", "Du hast den Spieler §c[PLAYER] §2gezwungen aufzuhören!");
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
		addMessage("de", "gui.freeze.on", "Du hast §c[PLAYER] §2gefreezt!");
		addMessage("de", "gui.freeze.off", "Du hast §c[PLAYER] §2unfreezt!");
		addMessage("de", "gui.anticheat.default", "Das Anticheat fängt nun an §c[PLAYER] §2zu bemerken!");
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
		addMessage("de", "gui.fakeinv.default",
				"Der Spieler §c[PLAYER] §2hat nun für 5 Sekunden sein Inventar verloren!");
		addMessage("de", "gui.fakeinv.cancel", "Der Spieler §c[PLAYER] §2hat nun sein Inventar wieder bekommen!");
		addMessage("de", "gui.noinv.on", "Der Spieler §c[PLAYER] §2kann nun keine Inventare mehr öffnen!");
		addMessage("de", "gui.noinv.off", "Der Spieler §c[PLAYER] §2kann nun wieder Inventare öffnen!");
		addMessage("de", "gui.slipperyhands.on", "Der Spieler §c[PLAYER] §2hat seine Hände mit Butter vollgeschmiert!");
		addMessage("de", "gui.slipperyhands.off", "Der Spieler §c[PLAYER] §2hat die Butter runter gewaschen!");
		addMessage("de", "gui.tntworld.default",
				"Der Spieler §c[PLAYER] §2sieht nun in einem Radius von 75 blöcken nur TNT!");
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
		addMessage("us", "command.troll.help.line.2", "Troll Mode §aActive!");
		addMessage("us", "command.troll.help.line.3", "Troll Vanish §aActive!");
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

	public void loadRussian() {
		addMessage("ru", "nopermission", "У вас нет прав на эту команду!");
		addMessage("ru", "noonline", "Этот игрок не в сети!");
		addMessage("ru", "command.troll.default", "Открылось меню троллей!");
		addMessage("ru", "command.troll.help.line.1", "TrollV4 от Presti");
		addMessage("ru", "command.troll.help.line.2", "Режим тролля §aАктивирован!");
		addMessage("ru", "command.troll.help.line.3", "Исчезновение троллей §aВключено!");
		addMessage("ru", "command.troll.help.line.4", "§4Команды:");
		addMessage("ru", "command.troll.help.line.5", "§4Player Troll GUI §8- §c/troll");
		addMessage("ru", "command.troll.help.line.6", "§4Server Troll GUI §8- §c/troll server");
		addMessage("ru", "command.troll.help.line.7", "§4Item Troll GUI §8- §c/troll items");
		addMessage("ru", "command.troll.help.line.8", "§4Troll Vanish §8- §c/troll v");
		addMessage("ru", "command.troll.help.line.9", "§4Игровой режим §8- §c/troll gm");
		addMessage("ru", "command.troll.help.line.10", "§4Летать §8- §c/troll fly");
		addMessage("ru", "command.troll.help.line.11", "§4Журнал изменений §8- §c/troll changelog");
		addMessage("ru", "command.troll.help.line.12", "§4Кредиты §8- §c/troll credits");
		addMessage("ru", "command.troll.gm", "Вы сейчас в игровом режиме 1!");
		addMessage("ru", "command.troll.fly.on", "Теперь ты можешь летать!");
		addMessage("ru", "command.troll.fly.off", "Ты больше не можешь летать!");
		addMessage("ru", "command.troll.vanish.on", "Вы сейчас в Исчезновении!");
		addMessage("ru", "command.troll.vanish.off", "Вы покинули Исчезновение!");
		addMessage("ru", "command.troll.items", "Инвентарь тролля предметов открыт!");
		addMessage("ru", "command.troll.server", "Инвентаризация серверных троллей открыта!");
		addMessage("ru", "command.troll.notfound.line.1", "§8>");
		addMessage("ru", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("ru", "command.troll.notfound.line.3", "Все команды /troll help");
		addMessage("ru", "command.troll.notfound.line.4", "TrollV4 от");
		addMessage("ru", "command.troll.notfound.line.5", "Presti");
		addMessage("ru", "command.troll.notfound.line.6", "§8>");
		addMessage("ru", "command.troll.wrongargs", "Пожалуйста, используйте неизвестные аргументы /troll help");
		addMessage("ru", "command.troll.noplayer", "Ты не геймер!");
		addMessage("ru", "gui.mlg", "Вы позволяете игроку §c[PLAYER] §2выполнить §cMLG§2!");
		addMessage("ru", "gui.explode", "Вы заставили игрока §c[PLAYER] §2взорваться!");
		addMessage("ru", "gui.fakehack.on", "У вас есть §c[PLAYER]§2, чтобы обмануть!");
		addMessage("ru", "gui.fakehack.off", "Вы заставили игрока §c[PLAYER]§2 покинуть игру!");
		addMessage("ru", "gui.demo", "Игрок §c[PLAYER] §2теперь видит экран демонстрации!");
		addMessage("ru", "gui.strike", "Вы поразили игрока §c[PLAYER] §2молнией!");
		addMessage("ru", "gui.hackuser", "Начинается чит-процесс!");
		addMessage("ru", "gui.rocket", "Вы позволяете игроку §c[PLAYER] §2летать, как ракета!");
		addMessage("ru", "gui.spam", "Вы полностью заспамили игрока §c[PLAYER]§2!");
		addMessage("ru", "gui.startcontrol.start", "Теперь вы контролируете §c[PLAYER]§2!");
		addMessage("ru", "gui.startcontrol.cantcontrol", "Вы не можете управлять этим игроком!");
		addMessage("ru", "gui.startcontrol.yourself", "Ты не можешь себя контролировать!");
		addMessage("ru", "gui.startcontrol.iscontroled", "Игрок уже находится под контролем!");
		addMessage("ru", "gui.startcontrol.alreadyc", "Вы уже контролируете кого-то другого!");
		addMessage("ru", "gui.stopcontrol.stop", "Теперь вы больше не контролируете §c[PLAYER]§2!");
		addMessage("ru", "gui.stopcontrol.nocontrol", "Вы никого не контролируете!");
		addMessage("ru", "gui.fakeop.default", "У вас есть §c[PLAYER]§2Fakeoped!");
		addMessage("ru", "gui.fakeop.opm", "§7§o[Server: [PLAYER] теперь оператор]");
		addMessage("ru", "gui.crash.default", "Вы привели к сбою игрока §c[PLAYER]§2!");
		addMessage("ru", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("ru", "gui.freeze.on", "Вы заморозили §c[PLAYER]§2!");
		addMessage("ru", "gui.freeze.off", "Вы разморозили §c[PLAYER]§2!");
		addMessage("ru", "gui.anticheat.default", "Античит начинает замечать §c[PLAYER]§2!");
		addMessage("ru", "gui.anticheat.detected",
				"§7 [§cPrestige§7-§cAntiCheat§7] §2 прекратить прослушивание §ccheaten (SPEED) §7 [§1VL§7 / §620§7]");
		addMessage("ru", "gui.lag.on", "Игрок §c[PLAYER] §2начинает отставать!");
		addMessage("ru", "gui.lag.off", "Игрок §c[PLAYER] §2перестает отставать!");
		addMessage("ru", "gui.arrest", "Игрок §c[PLAYER] §2в ловушке!");
		addMessage("ru", "gui.rotate.on", "Игрок §c[PLAYER] §2теперь вращается!");
		addMessage("ru", "gui.rotate.off", "Игрок §c[PLAYER] §2больше не поворачивается!");
		addMessage("ru", "gui.rndmtp.on", "Игрок §c[PLAYER] §2телепортируется по воздуху!");
		addMessage("ru", "gui.rndmtp.off", "Игрок §c[PLAYER] §2больше не телепортируется по воздуху!");
		addMessage("ru", "gui.tnttrace.on", "TnT теперь появляется за игроком §c[PLAYER]§2!");
		addMessage("ru", "gui.tnttrace.off", "TnT больше не появляется за игроком §c[PLAYER]§2!");
		addMessage("ru", "gui.wtf", "Игрок §c[PLAYER] §2сходит с ума!");
		addMessage("ru", "gui.webtrap", "Игрок §c[PLAYER] §2теперь заперт в паутине!");
		addMessage("ru", "gui.lsd", "Игрок §c[PLAYER] §2видит мир по-другому!");
		addMessage("ru", "gui.guardian", "Игрок §c[ИГРОК] §2теперь имеет эффект Стража!");
		addMessage("ru", "gui.herobrine.on", "Игрок §c[PLAYER] §2теперь Херобрин!");
		addMessage("ru", "gui.herobrine.off", "Игрок §c[PLAYER] §2больше не Herobrine!");
		addMessage("ru", "gui.arrowspam.on", "Игрок §c[PLAYER] §2теперь преследуется стрелами!");
		addMessage("ru", "gui.arrowspam.off", "Игрок §c[PLAYER] §2больше не преследуется стрелами!");
		addMessage("ru", "gui.tornado.on", "Игрок §c[PLAYER] §2теперь преследуется торнадо!");
		addMessage("ru", "gui.tornado.off", "Игрок §c[PLAYER] §2больше не преследуется торнадо!");
		addMessage("ru", "gui.fakeinv.default", "Игрок §c[PLAYER] §2потерял инвентарь на 5 секунд!");
		addMessage("ru", "gui.fakeinv.cancel", "Игрок §c[PLAYER] §2вернулся в свой инвентарь!");
		addMessage("ru", "gui.noinv.on", "Игрок §c[PLAYER] §2больше не может открывать инвентарь!");
		addMessage("ru", "gui.noinv.off", "Игрок §c[PLAYER] §2теперь снова может открывать инвентарь!");
		addMessage("ru", "gui.slipperyhands.on", "У игрока §c[PLAYER] §2руки покрыты маслом!");
		addMessage("ru", "gui.slipperyhands.off", "Игрок §c[PLAYER] §2запил масло!");
		addMessage("ru", "gui.tntworld.default", "Игрок §c[PLAYER] §2теперь видит только TNT в радиусе 75 блоков!");
		addMessage("ru", "gui.rickroll.default", "Игрок §c[PLAYER] §2теперь катился по ролику!");
		addMessage("ru", "gui.rickroll.ishearing", "Der Spieler §c[PLAYER] §2wird schon gerickrolled!");
		addMessage("ru", "gui.dontstopjumping.on", "Игрок §c[PLAYER] §2уже бросает броски!");
		addMessage("ru", "gui.dontstopjumping.off", "Игрок §c[PLAYER] §2теперь перестает прыгать!");
		addMessage("ru", "gui.deaf.on", "Игрок §c[PLAYER] §2больше ничего не слышит!");
		addMessage("ru", "gui.deaf.off", "Игрок §c[PLAYER] §2снова что-то слышит!");
		addMessage("ru", "gui.confused.on", "Игрок §c[PLAYER] §2сбит с толку!");
		addMessage("ru", "gui.confused.off", "Игрок §c[PLAYER] §2снова в здравом уме!");
		addMessage("ru", "gui.tpall", "Все игроки были телепортированы к вам!");
		addMessage("ru", "gui.fakeleave.default", "В чат отправлено сообщение FakeLeave!");
		addMessage("ru", "gui.fakeleave.message", "§6[PLAYER] left the Game.");
		addMessage("ru", "gui.hackserver", "Начинается чит-процесс!");
		addMessage("ru", "control.world", "§cМир изменился!");
		addMessage("ru", "control.distance", "§cСлишком далеко!");
	}
	
	public void loadSingapore() {
		addMessage("sg", "nopermission", "Anda tidak memiliki izin untuk perintah ini!");
		addMessage("sg", "noonline", "Pemain ini tidak online!");
		addMessage("sg", "command.troll.default", "Menu Troll telah dibuka!");
		addMessage("sg", "command.troll.help.line.1", "TrollV4 by Presti");
		addMessage("sg", "command.troll.help.line.2", "Troll Mode §aActive!");
		addMessage("sg", "command.troll.help.line.3", "Troll Vanish §aActive!");
		addMessage("sg", "command.troll.help.line.4", "§4Commands:");
		addMessage("sg", "command.troll.help.line.5", "§4Player Troll GUI §8- §c/troll");
		addMessage("sg", "command.troll.help.line.6", "§4Server Troll GUI §8- §c/troll server");
		addMessage("sg", "command.troll.help.line.7", "§4Item Troll GUI §8- §c/troll items");
		addMessage("sg", "command.troll.help.line.8", "§4Troll Vanish §8- §c/troll v");
		addMessage("sg", "command.troll.help.line.9", "§4Gamemode §8- §c/troll gm");
		addMessage("sg", "command.troll.help.line.10", "§4Fly §8- §c/troll fly");
		addMessage("sg", "command.troll.help.line.11", "§4Changelog §8- §c/troll changelog");
		addMessage("sg", "command.troll.help.line.12", "§4Credits §8- §c/troll credits");
		addMessage("sg", "command.troll.gm", "Anda sekarang berada di Gamemode 1!");
		addMessage("sg", "command.troll.fly.on", "Kamu bisa terbang sekarang!");
		addMessage("sg", "command.troll.fly.off", "Kamu sudah tidak bisa terbang lagi!");
		addMessage("sg", "command.troll.vanish.on", "sekarang kamu lagi vanish!");
		addMessage("sg", "command.troll.vanish.off", "kamu tidak vanish lagi!");
		addMessage("sg", "command.troll.items", "Item Troll Inventory dibuka!");
		addMessage("sg", "command.troll.server", "Server Troll Inventory dibuka!");
		addMessage("sg", "command.troll.notfound.line.1", "§8>");
		addMessage("sg", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("sg", "command.troll.notfound.line.3", "Semua Perintah /troll help");
		addMessage("sg", "command.troll.notfound.line.4", "TrollV4 by");
		addMessage("sg", "command.troll.notfound.line.5", "Presti");
		addMessage("sg", "command.troll.notfound.line.6", "§8>");
		addMessage("sg", "command.troll.wrongargs", "Argumen yang tidak diketahui, harap gunakan /troll help");
		addMessage("sg", "command.troll.noplayer", "Anda bukan seorang Player!");
		addMessage("sg", "gui.mlg", "kamu membuat &c[PLAYER] &2melakukan &cMLG&2!");
		addMessage("sg", "gui.explode", "kamu membuat &c[PLAYER] &2meledak!");
		addMessage("sg", "gui.fakehack.on", "kamu membuat &c[PLAYER] &2hack!");
		addMessage("sg", "gui.fakehack.off", "kamu memaksa &c[PLAYER] &2untuk berhenti!");
		addMessage("sg", "gui.demo", "player &c[PLAYER] &2sekarang lagi melihat layar demo!");
		addMessage("sg", "gui.strike", "kamu memukul &c[PLAYER] &2dengan petir!");
		addMessage("sg", "gui.hackuser", "proses hack sedang dimulai!");
		addMessage("sg", "gui.rocket", "kamu membuat &c[PLAYER] &2terbang seperti rocket!");
		addMessage("sg", "gui.spam", "kamu telah spam &c[PLAYER]&2!");
		addMessage("sg", "gui.startcontrol.start", "kamu telah mengendalikan &c[PLAYER]&2!");
		addMessage("sg", "gui.startcontrol.cantcontrol", "Kamu tidak bisa mengendalikan dia!");
		addMessage("sg", "gui.startcontrol.yourself", "kamu tidak bisa mengontrol diri sendiri!");
		addMessage("sg", "gui.startcontrol.iscontroled", "player ini sudah di control!");
		addMessage("sg", "gui.startcontrol.alreadyc", "kamu sudah mengontrol orang lain!");
		addMessage("sg", "gui.stopcontrol.stop", "kamu berhenti mengontol &c[PLAYER]&2!");
		addMessage("sg", "gui.stopcontrol.nocontrol", "kamu tidak mengontrol siapa siapa!");
		addMessage("sg", "gui.fakeop.default", "kamu fake op kan &c[PLAYER]&2!");
		addMessage("sg", "gui.fakeop.opm", "§7§o[Server: Opped [PLAYER]]");
		addMessage("sg", "gui.crash.default", "kamu membuat &c[PLAYER] &2Crash!");
		addMessage("sg", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("sg", "gui.freeze.on", "kamu membekukan &c[PLAYER]&2!");
		addMessage("sg", "gui.freeze.off", "kamu mencairkan &c[PLAYER]&2!");
		addMessage("sg", "gui.anticheat.default", "anticheat sudah di nyalakan &c[PLAYER]&2!");
		addMessage("sg", "gui.anticheat.detected",
				"§7[§cPrestige§7-§cAntiCheat§7] §2Stop §cHacking(SPEED) §7[§1VL§7/§620§7]");
		addMessage("sg", "gui.lag.on", "player &c[PLAYER] &2sekarang lagging!");
		addMessage("sg", "gui.lag.off", "player &c[PLAYER] &2berhenti lagging!");
		addMessage("sg", "gui.arrest", "player &c[PLAYER] &2tertangkap!");
		addMessage("sg", "gui.rotate.on", "player &c[PLAYER] &2berputar!");
		addMessage("sg", "gui.rotate.off", "player &c[PLAYER] &2berhenti berputar!");
		addMessage("sg", "gui.rndmtp.on", "player &c[PLAYER] &2mulai berpindah!");
		addMessage("sg", "gui.rndmtp.off", "player &c[PLAYER] &2sudah berhenti berpindah!");
		addMessage("sg", "gui.tnttrace.on", "dibelakang &c[PLAYER] &2sudah muncul banyak tnt!");
		addMessage("sg", "gui.tnttrace.off", "dibelakang &c[PLAYER] &2tnt nya sudah tidak muncul lagi!");
		addMessage("sg", "gui.wtf", "Player &c[PLAYER] &2menjadi gila!");
		addMessage("sg", "gui.webtrap", "player &c[PLAYER] &2sekarang stuck di cobweb!");
		addMessage("sg", "gui.lsd", "player &c[PLAYER] &2sekarang melihat dunia lebih berbeda!");
		addMessage("sg", "gui.guardian", "player &c[PLAYER] &2sekarang mempunyai efek guardian!");
		addMessage("sg", "gui.herobrine.on", "The User &c[PLAYER] &2sekarang jadi herobrine!");
		addMessage("sg", "gui.herobrine.off", "The User &c[PLAYER] &2tidak jadi herobrine lagi!");
		addMessage("sg", "gui.arrowspam.on", "The User &c[PLAYER] &2telah di spam anak panah!");
		addMessage("sg", "gui.arrowspam.off", "The User &c[PLAYER] &2sudah tidak di spam anak panah lagi!");
		addMessage("sg", "gui.tornado.on", "The User &c[PLAYER] &2di kejar tornado!");
		addMessage("sg", "gui.tornado.off", "The User &c[PLAYER] &2sudah tidak di kejar tornado!");
		addMessage("sg", "gui.fakeinv.default", "The User &c[PLAYER] &2kehilangan inventory nya untuk 5 detik!");
		addMessage("sg", "gui.fakeinv.cancel", "The User &c[PLAYER] &2dapat inventory nya kembali!");
		addMessage("sg", "gui.noinv.on", "The User &c[PLAYER] &2tidak bisa buka inventory lagi!");
		addMessage("sg", "gui.noinv.off", "The User &c[PLAYER] &2sudah bisa buka inventory!");
		addMessage("sg", "gui.slipperyhands.on", "The User &c[PLAYER] &2tangan nya di oleskan mentega!");
		addMessage("sg", "gui.slipperyhands.off", "The User &c[PLAYER] &2tangan nya sudah di bersihkan!");
		addMessage("sg", "gui.tntworld.default", "The User &c[PLAYER] &2hanya melihat tnt di world!");
		addMessage("sg", "gui.rickroll.default", "The User &c[PLAYER] &2mendengar sebuah lagu!");
		addMessage("sg", "gui.rickroll.ishearing", "The User &c[PLAYER] &2sudah mendengar sebuah lagu!");
		addMessage("sg", "gui.dontstopjumping.on", "The User &c[PLAYER] &2tidak bisa berhenti melompat!");
		addMessage("sg", "gui.dontstopjumping.off", "The User &c[PLAYER] &2berhenti lompat!");
		addMessage("sg", "gui.deaf.on", "The User &c[PLAYER] &2tidak bisa mendengar apa apa lagi!");
		addMessage("sg", "gui.deaf.off", "The User &c[PLAYER] &2sudah bisa mendengar!");
		addMessage("sg", "gui.confused.on", "The User &c[PLAYER] &2sekarang pusing!");
		addMessage("sg", "gui.confused.off", "The User &c[PLAYER] &2sudah tidak pusing!");
		addMessage("sg", "gui.tpall", "semua player teleport ke kamu!");
		addMessage("sg", "gui.fakeleave.default", "fake message sudah di jabarkan di chat!");
		addMessage("sg", "gui.fakeleave.message", "§6[PLAYER] left the Game.");
		addMessage("sg", "gui.hackserver", "proses hack di mulai!");
		addMessage("sg", "control.world", "&4Mengganti dunia");
		addMessage("sg", "control.distance", "&4TERLALU JAUH");
	}

	public void loadCustom() {
		if (Config.getFile2().exists()) {
			for (String s : lang) {
				if (Config.getconfig2().get(s) != null) {
					addMessage("custome", s, Config.getconfig2().getString(s));
					addMessage("custom", s, Config.getconfig2().getString(s));
				} else {
					addMessage("custome", s, "Couldnt find " + s + " in the message.yml");
					addMessage("custom", s, "Couldnt find " + s + " in the message.yml");
				}
			}
		}
	}

	public static void clearAll() {
		lang.clear();
		messages.clear();
	}

}
