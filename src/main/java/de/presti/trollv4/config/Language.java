package de.presti.trollv4.config;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

import de.presti.trollv4.main.Data;

public class Language {

	public static HashMap<String, String> messages = new HashMap<>();
	public static ArrayList<String> lang = new ArrayList<>();

	public static void loadAll() {
		loadGerman();
		loadEnglish();
		loadRussian();
		loadIndo();
		loadSpanish();
		loadCustom();
	}

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

	private static void loadGerman() {
		addMessage("de", "nopermission", "Du hast keine Rechte für diesen Command!");
		addMessage("de", "noonline", "Dieser Spieler ist nicht online!");
		addMessage("de", "command.troll.default", "Troll Menu wurde geoeffnet!");
		addMessage("de", "command.troll.help.line.1", "TrollV4 by Presti");
		addMessage("de", "command.troll.help.line.2", "Troll Modus &aAktiviert!");
		addMessage("de", "command.troll.help.line.3", "Troll Vanish &aAktiviert!");
		addMessage("de", "command.troll.help.line.4", "&4Commands:");
		addMessage("de", "command.troll.help.line.5", "&4Player Troll GUI &8- &c/troll");
		addMessage("de", "command.troll.help.line.6", "&4Server Troll GUI &8- &c/troll server");
		addMessage("de", "command.troll.help.line.7", "&4Item Troll GUI &8- &c/troll items");
		addMessage("de", "command.troll.help.line.8", "&4Troll Vanish &8- &c/troll v");
		addMessage("de", "command.troll.help.line.9", "&4Gamemode &8- &c/troll gm");
		addMessage("de", "command.troll.help.line.10", "&4Fly &8- &c/troll fly");
		addMessage("de", "command.troll.help.line.11", "&4Changelog &8- &c/troll changelog");
		addMessage("de", "command.troll.help.line.12", "&4Credits &8- &c/troll credits");
		addMessage("de", "command.troll.gm", "Du bist nun im Gamemode 1!");
		addMessage("de", "command.troll.fly.on", "Du kannst nun Fliegen!");
		addMessage("de", "command.troll.fly.off", "Du kannst nun nicht mehr Fliegen!");
		addMessage("de", "command.troll.vanish.on", "Du bist nun im Vanish!");
		addMessage("de", "command.troll.vanish.off", "Du hast den Vanish verlassen!");
		addMessage("de", "command.troll.items", "Item Troll Inv wurde geoeffnet!");
		addMessage("de", "command.troll.server", "Server Troll Inv wurde geoeffnet!");
		addMessage("de", "command.troll.notfound.line.1", "&8>");
		addMessage("de", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("de", "command.troll.notfound.line.3", "Alle Commands /troll help");
		addMessage("de", "command.troll.notfound.line.4", "TrollV4 von");
		addMessage("de", "command.troll.notfound.line.5", "Presti");
		addMessage("de", "command.troll.notfound.line.6", "&8>");
		addMessage("de", "command.troll.wrongargs", "Unbekannte Argumente bitte benutze /troll help");
		addMessage("de", "command.troll.noplayer", "Du bist kein Spieler!");
		addMessage("de", "gui.mlg", "Du hast den Spieler &c[PLAYER] &2einen &cMLG &2machen lassen!");
		addMessage("de", "gui.explode", "Du hast den Spieler &c[PLAYER] &2zum explodieren gebracht!");
		addMessage("de", "gui.fakehack.on", "Du hast &c[PLAYER] &2zum hacken gebracht!");
		addMessage("de", "gui.fakehack.off", "Du hast den Spieler &c[PLAYER] &2gezwungen aufzuhören!");
		addMessage("de", "gui.demo", "Der Spieler &c[PLAYER] &2sieht nun den Demoscreen!");
		addMessage("de", "gui.strike", "Du hast den Spieler &c[PLAYER] &2mit einem Blitz getroffen!");
		addMessage("de", "gui.hackuser", "Der Hackvorgang startet!");
		addMessage("de", "gui.rocket", "Du hast den Spieler &c[PLAYER] &2wie eine Rakete Fliegen lassen!");
		addMessage("de", "gui.spam", "Du hast den Spieler &c[PLAYER] &2voll gespamt!");
		addMessage("de", "gui.startcontrol.start", "Du kontrollierst nun &c[PLAYER]&2!");
		addMessage("de", "gui.startcontrol.cantcontrol", "Du kannst diesen Spieler nicht kontrollieren!");
		addMessage("de", "gui.startcontrol.yourself", "Du kannst nicht dich selbst kontrollieren!");
		addMessage("de", "gui.startcontrol.iscontroled", "Der Spieler wird schon kontrolliert!");
		addMessage("de", "gui.startcontrol.alreadyc", "Du kontrollierst schon jemanden anderen!");
		addMessage("de", "gui.stopcontrol.stop", "Du kontrollierst nun &c[PLAYER] &2nicht mehr!");
		addMessage("de", "gui.stopcontrol.nocontrol", "Du kontrollierst niemanden!");
		addMessage("de", "gui.fakeop.default", "Du hast &c[PLAYER] &2Fakeoped!");
		addMessage("de", "gui.fakeop.opm", "&7&o[Server: [PLAYER] wurde zum Operator ernannt]");
		addMessage("de", "gui.crash.default", "Du hast den Spieler &c[PLAYER] &2Crashen lassen!");
		addMessage("de", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("de", "gui.freeze.on", "Du hast &c[PLAYER] &2gefreezt!");
		addMessage("de", "gui.freeze.off", "Du hast &c[PLAYER] &2unfreezt!");
		addMessage("de", "gui.anticheat.default", "Das Anticheat fängt nun an &c[PLAYER] &2zu bemerken!");
		addMessage("de", "gui.anticheat.detected",
				"&7[&cPrestige&7-&cAntiCheat&7] &2Stop &cHacking(SPEED) &7[&1VL&7/&620&7]");
		addMessage("de", "gui.lag.on", "Der Spieler &c[PLAYER] &2fängt an zu laggen!");
		addMessage("de", "gui.lag.off", "Der Spieler &c[PLAYER] &2hört auf zu laggen!");
		addMessage("de", "gui.arrest", "Der Spieler &c[PLAYER] &2ist nun gefangen!");
		addMessage("de", "gui.rotate.on", "Der Spieler &c[PLAYER] &2dreht sich nun!");
		addMessage("de", "gui.rotate.off", "Der Spieler &c[PLAYER] &2dreht sich nun nicht mehr!");
		addMessage("de", "gui.rndmtp.on", "Der Spieler &c[PLAYER] &2wird durch die Luft teleportiert!");
		addMessage("de", "gui.rndmtp.off", "Der Spieler &c[PLAYER] &2wird nicht mehr durch die Luft teleportiert!");
		addMessage("de", "gui.tnttrace.on", "Hinter dem Spieler &c[PLAYER] &2wird nun TnT gespawnt!");
		addMessage("de", "gui.tnttrace.off", "Hinter dem Spieler &c[PLAYER] &2wird nun nicht mehr TnT gespawnt!");
		addMessage("de", "gui.wtf", "Der Spieler &c[PLAYER] &2wird verrückt!");
		addMessage("de", "gui.webtrap", "Der Spieler &c[PLAYER] &2ist nun in Cobweb gefangen!");
		addMessage("de", "gui.lsd", "Der Spieler &c[PLAYER] &2sieht nun die Welt anders!");
		addMessage("de", "gui.guardian", "Der Spieler &c[PLAYER] &2hat nun den Guardian effekt!");
		addMessage("de", "gui.herobrine.on", "Der Spieler &c[PLAYER] &2ist nun Herobrine!");
		addMessage("de", "gui.herobrine.off", "Der Spieler &c[PLAYER] &2ist nun nicht mehr Herobrine!");
		addMessage("de", "gui.arrowspam.on", "Der Spieler &c[PLAYER] &2wird nun von Pfeilen gejagt!");
		addMessage("de", "gui.arrowspam.off", "Der Spieler &c[PLAYER] &2wird nun nicht mehr von Pfeilen gejagt!");
		addMessage("de", "gui.tornado.on", "Der Spieler &c[PLAYER] &2wird nun von einem Tornado verfolgt!");
		addMessage("de", "gui.tornado.off", "Der Spieler &c[PLAYER] &2wird nun nicht mehr von einem Tornado verfolgt!");
		addMessage("de", "gui.fakeinv.default",
				"Der Spieler &c[PLAYER] &2hat nun für 5 Sekunden sein Inventar verloren!");
		addMessage("de", "gui.fakeinv.cancel", "Der Spieler &c[PLAYER] &2hat nun sein Inventar wieder bekommen!");
		addMessage("de", "gui.noinv.on", "Der Spieler &c[PLAYER] &2kann nun keine Inventare mehr öffnen!");
		addMessage("de", "gui.noinv.off", "Der Spieler &c[PLAYER] &2kann nun wieder Inventare öffnen!");
		addMessage("de", "gui.slipperyhands.on", "Der Spieler &c[PLAYER] &2hat seine Hände mit Butter vollgeschmiert!");
		addMessage("de", "gui.slipperyhands.off", "Der Spieler &c[PLAYER] &2hat die Butter runter gewaschen!");
		addMessage("de", "gui.tntworld.default",
				"Der Spieler &c[PLAYER] &2sieht nun in einem Radius von 75 blöcken nur TNT!");
		addMessage("de", "gui.rickroll.default", "Der Spieler &c[PLAYER] &2wurde nun gerickrolled!");
		addMessage("de", "gui.rickroll.ishearing", "Der Spieler &c[PLAYER] &2wird schon gerickrolled!");
		addMessage("de", "gui.dontstopjumping.on", "Der Spieler &c[PLAYER] &2kann nicht aufhören zu springen!");
		addMessage("de", "gui.dontstopjumping.off", "Der Spieler &c[PLAYER] &2hört nun auf zu springen!");
		addMessage("de", "gui.deaf.on", "Der Spieler &c[PLAYER] &2hört nichts mehr!");
		addMessage("de", "gui.deaf.off", "Der Spieler &c[PLAYER] &2hört wieder etwas!");
		addMessage("de", "gui.confused.on", "Der Spieler &c[PLAYER] &2ist nun verwirrt!");
		addMessage("de", "gui.confused.off", "Der Spieler &c[PLAYER] &2ist nun wieder bei Sinnen!");
		addMessage("de", "gui.anvils.on", "Es fallen nun Anvils auf den Spieler &c[PLAYER]&2!");
		addMessage("de", "gui.anvils.off", "Es fallen nun keine weiteren Anvils auf den Spieler &c[PLAYER]&2!");
		addMessage("de", "gui.cows", "Aggressive Kühe greifen nun &c[PLAYER] &2an!");
		addMessage("de", "gui.giorno.on", "Giorno Giovanna greift nun &c[PLAYER] &2an!");
		addMessage("de", "gui.giorno.off", "Giorno Giovanna lässt nun &c[PLAYER] &2in Ruhe!");
		addMessage("de", "gui.spooky.on", "Der Spieler &c[PLAYER] &2ist nun im Schattenreich!");
		addMessage("de", "gui.spooky.off", "Der Spieler &c[PLAYER] &2ist nun nicht mehr im Schattenreich!");
		addMessage("de", "gui.spooky.world", "Die Welt \"SpookyWorld\" existiert nicht!");
		addMessage("de", "gui.endcredits", "Der Spieler &c[PLAYER] &2sieht nun die End Credits!");
		addMessage("de", "gui.tpall", "Alle Spieler wurden zu dir teleportiert");
		addMessage("de", "gui.fakeleave.default", "Eine FakeLeave Message wurde in den Chat geschickt!");
		addMessage("de", "gui.fakeleave.message", "&6[PLAYER] left the Game.");
		addMessage("de", "gui.hackserver", "Der Hackvorgang startet!");
		addMessage("de", "gui.loading", "Der Spieler &c[PLAYER] &2sieht nun den Ladebildschirm bis er neu startet!");
		addMessage("de", "gui.vomit.on", "Der Spieler &c[PLAYER] &2kann nicht mehr aufhören zu kotzen!");
		addMessage("de", "gui.vomit.off", "Der Spieler &c[PLAYER] &2kann hat aufgehört zu kotzen!");
		addMessage("de", "control.world", "&cWelt wurde geweschelt!");
		addMessage("de", "control.distance", "&cZuweit entfernt");
	}

	private static void loadEnglish() {
		addMessage("us", "nopermission", "You do not have the permission for this command!");
		addMessage("us", "noonline", "This Player is not online!");
		addMessage("us", "command.troll.default", "Troll Menu has been opened!");
		addMessage("us", "command.troll.help.line.1", "TrollV4 by Presti");
		addMessage("us", "command.troll.help.line.2", "Troll Mode &aActive!");
		addMessage("us", "command.troll.help.line.3", "Troll Vanish &aActive!");
		addMessage("us", "command.troll.help.line.4", "&4Commands:");
		addMessage("us", "command.troll.help.line.5", "&4Player Troll GUI &8- &c/troll");
		addMessage("us", "command.troll.help.line.6", "&4Server Troll GUI &8- &c/troll server");
		addMessage("us", "command.troll.help.line.7", "&4Item Troll GUI &8- &c/troll items");
		addMessage("us", "command.troll.help.line.8", "&4Troll Vanish &8- &c/troll v");
		addMessage("us", "command.troll.help.line.9", "&4Gamemode &8- &c/troll gm");
		addMessage("us", "command.troll.help.line.10", "&4Fly &8- &c/troll fly");
		addMessage("us", "command.troll.help.line.11", "&4Changelog &8- &c/troll changelog");
		addMessage("us", "command.troll.help.line.12", "&4Credits &8- &c/troll credits");
		addMessage("us", "command.troll.gm", "Youre now in Gamemode 1!");
		addMessage("us", "command.troll.fly.on", "You can fly now!");
		addMessage("us", "command.troll.fly.off", "You cant fly anymore!");
		addMessage("us", "command.troll.vanish.on", "Youre now in Vanish!");
		addMessage("us", "command.troll.vanish.off", "Youre not longer in Vanish!");
		addMessage("us", "command.troll.items", "Item Troll Inv was opened!");
		addMessage("us", "command.troll.server", "Server Troll Inv was opened!");
		addMessage("us", "command.troll.notfound.line.1", "&8>");
		addMessage("us", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("us", "command.troll.notfound.line.3", "All Commands /troll help");
		addMessage("us", "command.troll.notfound.line.4", "TrollV4 by");
		addMessage("us", "command.troll.notfound.line.5", "Presti");
		addMessage("us", "command.troll.notfound.line.6", "&8>");
		addMessage("us", "command.troll.wrongargs", "Unknown arguments please use /troll help");
		addMessage("us", "command.troll.noplayer", "Youre not a Player!");
		addMessage("us", "gui.mlg", "You let 玩家 &c[PLAYER] &2do an &cMLG&2!");
		addMessage("us", "gui.explode", "You made 玩家 &c[PLAYER] &2explode!");
		addMessage("us", "gui.fakehack.on", "You made 玩家 &c[PLAYER] &2hack!");
		addMessage("us", "gui.fakehack.off", "You forced 玩家 &c[PLAYER] &2to stop!");
		addMessage("us", "gui.demo", "玩家 &c[PLAYER] &2is now seeing a Demo screen!");
		addMessage("us", "gui.strike", "You hit 玩家 &c[PLAYER] &2with a lightning bolt!");
		addMessage("us", "gui.hackuser", "The hacking process starts!");
		addMessage("us", "gui.rocket", "You made 玩家 &c[PLAYER] &2fly like a rocket!");
		addMessage("us", "gui.spam", "You've completely blundered 玩家 &c[PLAYER]&2!");
		addMessage("us", "gui.startcontrol.start", "Youre Controlling &c[PLAYER]&2!");
		addMessage("us", "gui.startcontrol.cantcontrol", "You cant control this Player!");
		addMessage("us", "gui.startcontrol.yourself", "You cant control yourself!");
		addMessage("us", "gui.startcontrol.iscontroled", "玩家 is already controlled!");
		addMessage("us", "gui.startcontrol.alreadyc", "You already control someone else!");
		addMessage("us", "gui.stopcontrol.stop", "You stopped Controlling &c[PLAYER]&2!");
		addMessage("us", "gui.stopcontrol.nocontrol", "You control nobody!");
		addMessage("us", "gui.fakeop.default", "You Fakeopped &c[PLAYER]&2!");
		addMessage("us", "gui.fakeop.opm", "&7&o[Server: Opped [PLAYER]]");
		addMessage("us", "gui.crash.default", "You let 玩家 &c[PLAYER] &2Crash!");
		addMessage("us", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("us", "gui.freeze.on", "You freezed 玩家 &c[PLAYER]&2!");
		addMessage("us", "gui.freeze.off", "You unfreezed 玩家 &c[PLAYER]&2!");
		addMessage("us", "gui.anticheat.default", "The Anticheat started detecting &c[PLAYER]&2!");
		addMessage("us", "gui.anticheat.detected",
				"&7[&cPrestige&7-&cAntiCheat&7] &2Stop &cHacking(SPEED) &7[&1VL&7/&620&7]");
		addMessage("us", "gui.lag.on", "玩家 &c[PLAYER] &2is now lagging!");
		addMessage("us", "gui.lag.off", "玩家 &c[PLAYER] &2stopped lagging");
		addMessage("us", "gui.arrest", "玩家 &c[PLAYER] &2is Arrested!");
		addMessage("us", "gui.rotate.on", "玩家 &c[PLAYER] &2started rotating!");
		addMessage("us", "gui.rotate.off", "玩家 &c[PLAYER] &2stopped rotating!");
		addMessage("us", "gui.rndmtp.on", "玩家 &c[PLAYER] &2starts to be teleported through the air!");
		addMessage("us", "gui.rndmtp.off", "玩家 &c[PLAYER] &2stops to be teleported through the air!");
		addMessage("us", "gui.tnttrace.on", "Behind 玩家 &c[PLAYER] &2now a trace of TnT spawned!");
		addMessage("us", "gui.tnttrace.off", "Behind 玩家 &c[PLAYER] &2no trace of TnT is spawned anymore!");
		addMessage("us", "gui.wtf", "玩家 &c[PLAYER] &2is going crazy!");
		addMessage("us", "gui.webtrap", "玩家 &c[PLAYER] &2is now stucked in Cobweb!");
		addMessage("us", "gui.lsd", "玩家 &c[PLAYER] &2is now seeing the World in another Perspectiv!");
		addMessage("us", "gui.guardian", "玩家 &c[PLAYER] &2now has the Guardian effect!");
		addMessage("us", "gui.herobrine.on", "玩家 &c[PLAYER] &2is now Herobrine!");
		addMessage("us", "gui.herobrine.off", "玩家 &c[PLAYER] &2isnt anymore Herobrine!");
		addMessage("us", "gui.arrowspam.on", "玩家 &c[PLAYER] &2is now getting spammend by Arrows!");
		addMessage("us", "gui.arrowspam.off", "玩家 &c[PLAYER] &2is now not getting spammend by Arrows anymore!");
		addMessage("us", "gui.tornado.on", "玩家 &c[PLAYER] &2is now getting chased by a tornado!");
		addMessage("us", "gui.tornado.off", "玩家 &c[PLAYER] &2is no longer pursued by a tornado!");
		addMessage("us", "gui.fakeinv.default", "玩家 &c[PLAYER] &2lost his Inventory for 5 Seconds!");
		addMessage("us", "gui.fakeinv.cancel", "玩家 &c[PLAYER] &2got his Inventory back!");
		addMessage("us", "gui.noinv.on", "玩家 &c[PLAYER] &2cant open any Inventory anymore!");
		addMessage("us", "gui.noinv.off", "玩家 &c[PLAYER] &2can open any Inventory again!");
		addMessage("us", "gui.slipperyhands.on", "玩家 &c[PLAYER] &2played with some butter!");
		addMessage("us", "gui.slipperyhands.off", "玩家 &c[PLAYER] &2cleaned his hands!");
		addMessage("us", "gui.tntworld.default", "玩家 &c[PLAYER] &2now sees only TnT in his World!");
		addMessage("us", "gui.rickroll.default", "玩家 &c[PLAYER] &2is getting rickrolled!");
		addMessage("us", "gui.rickroll.ishearing", "玩家 &c[PLAYER] &2is already getting rickrolled!");
		addMessage("us", "gui.dontstopjumping.on", "玩家 &c[PLAYER] &2cant stop jumping!");
		addMessage("us", "gui.dontstopjumping.off", "玩家 &c[PLAYER] &2has stopped jumping!");
		addMessage("us", "gui.deaf.on", "玩家 &c[PLAYER] &2cant hear anything anymore!");
		addMessage("us", "gui.deaf.off", "玩家 &c[PLAYER] &2can hear something again!");
		addMessage("us", "gui.confused.on", "玩家 &c[PLAYER] &2is now confused!");
		addMessage("us", "gui.confused.off", "玩家 &c[PLAYER] &2got some milk!");
		addMessage("us", "gui.anvils.on", "Anvils now fall on player &c[PLAYER]&2!");
		addMessage("us", "gui.anvils.off", "Anvils no longer fall on 玩家 &c[PLAYER]&2!");
		addMessage("us", "gui.cows", "Aggressive cows are now attacking &c[PLAYER]&2!");
		addMessage("us", "gui.giorno.on", "Giorno Giovanna now attacks &c[PLAYER]&2!");
		addMessage("us", "gui.giorno.off", "Giorno Giovanna now leaves &c[PLAYER] &2alone!");
		addMessage("us", "gui.spooky.on", "玩家 &c[PLAYER] &2is now in the Shadow Realm!");
		addMessage("us", "gui.spooky.off", "玩家 &c[PLAYER] &2is no longer in the Shadow realm!");
		addMessage("us", "gui.spooky.world", "There is no world called \"SpookyWorld\"!");
		addMessage("us", "gui.endcredits", "玩家 &c[PLAYER] &2now sees the end credits!");
		addMessage("us", "gui.tpall", "All players were teleported to you!");
		addMessage("us", "gui.fakeleave.default", "A Fake Message has been Posted in the Chat!");
		addMessage("us", "gui.fakeleave.message", "&6[PLAYER] left the Game.");
		addMessage("us", "gui.hackserver", "The hacking process starts!");
		addMessage("us", "gui.loading", "玩家 &c[PLAYER] &2is now seeing the loading screen till they restart!");
		addMessage("us", "gui.vomit.on", "玩家 &c[PLAYER] &2can't stop vomiting!");
		addMessage("us", "gui.vomit.off", "玩家 &c[PLAYER] &2stopped vomiting!");
		addMessage("us", "control.world", "&4SWITCHED WORLDS");
		addMessage("us", "control.distance", "&4TOO FAR AWAY");
	}

	private static void loadRussian() {
		addMessage("ru", "nopermission", "У вас нет прав на эту команду!");
		addMessage("ru", "noonline", "Этот игрок не в сети!");
		addMessage("ru", "command.troll.default", "Открылось меню троллей!");
		addMessage("ru", "command.troll.help.line.1", "TrollV4 от Presti");
		addMessage("ru", "command.troll.help.line.2", "Режим тролля &aАктивирован!");
		addMessage("ru", "command.troll.help.line.3", "Исчезновение троллей &aВключено!");
		addMessage("ru", "command.troll.help.line.4", "&4Команды:");
		addMessage("ru", "command.troll.help.line.5", "&4Player Troll GUI &8- &c/troll");
		addMessage("ru", "command.troll.help.line.6", "&4Server Troll GUI &8- &c/troll server");
		addMessage("ru", "command.troll.help.line.7", "&4Item Troll GUI &8- &c/troll items");
		addMessage("ru", "command.troll.help.line.8", "&4Troll Vanish &8- &c/troll v");
		addMessage("ru", "command.troll.help.line.9", "&4Игровой режим &8- &c/troll gm");
		addMessage("ru", "command.troll.help.line.10", "&4Летать &8- &c/troll fly");
		addMessage("ru", "command.troll.help.line.11", "&4Журнал изменений &8- &c/troll changelog");
		addMessage("ru", "command.troll.help.line.12", "&4Кредиты &8- &c/troll credits");
		addMessage("ru", "command.troll.gm", "Вы сейчас в игровом режиме 1!");
		addMessage("ru", "command.troll.fly.on", "Теперь ты можешь летать!");
		addMessage("ru", "command.troll.fly.off", "Ты больше не можешь летать!");
		addMessage("ru", "command.troll.vanish.on", "Вы сейчас в Исчезновении!");
		addMessage("ru", "command.troll.vanish.off", "Вы покинули Исчезновение!");
		addMessage("ru", "command.troll.items", "Инвентарь тролля предметов открыт!");
		addMessage("ru", "command.troll.server", "Инвентаризация серверных троллей открыта!");
		addMessage("ru", "command.troll.notfound.line.1", "&8>");
		addMessage("ru", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("ru", "command.troll.notfound.line.3", "Все команды /troll help");
		addMessage("ru", "command.troll.notfound.line.4", "TrollV4 от");
		addMessage("ru", "command.troll.notfound.line.5", "Presti");
		addMessage("ru", "command.troll.notfound.line.6", "&8>");
		addMessage("ru", "command.troll.wrongargs", "Пожалуйста, используйте неизвестные аргументы /troll help");
		addMessage("ru", "command.troll.noplayer", "Ты не геймер!");
		addMessage("ru", "gui.mlg", "Вы позволяете игроку &c[PLAYER] &2выполнить &cMLG&2!");
		addMessage("ru", "gui.explode", "Вы заставили игрока &c[PLAYER] &2взорваться!");
		addMessage("ru", "gui.fakehack.on", "У вас есть &c[PLAYER]&2, чтобы обмануть!");
		addMessage("ru", "gui.fakehack.off", "Вы заставили игрока &c[PLAYER]&2 покинуть игру!");
		addMessage("ru", "gui.demo", "Игрок &c[PLAYER] &2теперь видит экран демонстрации!");
		addMessage("ru", "gui.strike", "Вы поразили игрока &c[PLAYER] &2молнией!");
		addMessage("ru", "gui.hackuser", "Начинается чит-процесс!");
		addMessage("ru", "gui.rocket", "Вы позволяете игроку &c[PLAYER] &2летать, как ракета!");
		addMessage("ru", "gui.spam", "Вы полностью заспамили игрока &c[PLAYER]&2!");
		addMessage("ru", "gui.startcontrol.start", "Теперь вы контролируете &c[PLAYER]&2!");
		addMessage("ru", "gui.startcontrol.cantcontrol", "Вы не можете управлять этим игроком!");
		addMessage("ru", "gui.startcontrol.yourself", "Ты не можешь себя контролировать!");
		addMessage("ru", "gui.startcontrol.iscontroled", "Игрок уже находится под контролем!");
		addMessage("ru", "gui.startcontrol.alreadyc", "Вы уже контролируете кого-то другого!");
		addMessage("ru", "gui.stopcontrol.stop", "Теперь вы больше не контролируете &c[PLAYER]&2!");
		addMessage("ru", "gui.stopcontrol.nocontrol", "Вы никого не контролируете!");
		addMessage("ru", "gui.fakeop.default", "У вас есть &c[PLAYER]&2Fakeoped!");
		addMessage("ru", "gui.fakeop.opm", "&7&o[Server: [PLAYER] теперь оператор]");
		addMessage("ru", "gui.crash.default", "Вы привели к сбою игрока &c[PLAYER]&2!");
		addMessage("ru", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("ru", "gui.freeze.on", "Вы заморозили &c[PLAYER]&2!");
		addMessage("ru", "gui.freeze.off", "Вы разморозили &c[PLAYER]&2!");
		addMessage("ru", "gui.anticheat.default", "Античит начинает замечать &c[PLAYER]&2!");
		addMessage("ru", "gui.anticheat.detected",
				"&7 [&cPrestige&7-&cAntiCheat&7] &2 прекратить прослушивание &ccheaten (SPEED) &7 [&1VL&7 / &620&7]");
		addMessage("ru", "gui.lag.on", "Игрок &c[PLAYER] &2начинает отставать!");
		addMessage("ru", "gui.lag.off", "Игрок &c[PLAYER] &2перестает отставать!");
		addMessage("ru", "gui.arrest", "Игрок &c[PLAYER] &2в ловушке!");
		addMessage("ru", "gui.rotate.on", "Игрок &c[PLAYER] &2теперь вращается!");
		addMessage("ru", "gui.rotate.off", "Игрок &c[PLAYER] &2больше не поворачивается!");
		addMessage("ru", "gui.rndmtp.on", "Игрок &c[PLAYER] &2телепортируется по воздуху!");
		addMessage("ru", "gui.rndmtp.off", "Игрок &c[PLAYER] &2больше не телепортируется по воздуху!");
		addMessage("ru", "gui.tnttrace.on", "TnT теперь появляется за игроком &c[PLAYER]&2!");
		addMessage("ru", "gui.tnttrace.off", "TnT больше не появляется за игроком &c[PLAYER]&2!");
		addMessage("ru", "gui.wtf", "Игрок &c[PLAYER] &2сходит с ума!");
		addMessage("ru", "gui.webtrap", "Игрок &c[PLAYER] &2теперь заперт в паутине!");
		addMessage("ru", "gui.lsd", "Игрок &c[PLAYER] &2видит мир по-другому!");
		addMessage("ru", "gui.guardian", "Игрок &c[PLAYER] &2теперь имеет эффект Стража!");
		addMessage("ru", "gui.herobrine.on", "Игрок &c[PLAYER] &2теперь Херобрин!");
		addMessage("ru", "gui.herobrine.off", "Игрок &c[PLAYER] &2больше не Herobrine!");
		addMessage("ru", "gui.arrowspam.on", "Игрок &c[PLAYER] &2теперь преследуется стрелами!");
		addMessage("ru", "gui.arrowspam.off", "Игрок &c[PLAYER] &2больше не преследуется стрелами!");
		addMessage("ru", "gui.tornado.on", "Игрок &c[PLAYER] &2теперь преследуется торнадо!");
		addMessage("ru", "gui.tornado.off", "Игрок &c[PLAYER] &2больше не преследуется торнадо!");
		addMessage("ru", "gui.fakeinv.default", "Игрок &c[PLAYER] &2потерял инвентарь на 5 секунд!");
		addMessage("ru", "gui.fakeinv.cancel", "Игрок &c[PLAYER] &2вернулся в свой инвентарь!");
		addMessage("ru", "gui.noinv.on", "Игрок &c[PLAYER] &2больше не может открывать инвентарь!");
		addMessage("ru", "gui.noinv.off", "Игрок &c[PLAYER] &2теперь снова может открывать инвентарь!");
		addMessage("ru", "gui.slipperyhands.on", "У игрока &c[PLAYER] &2руки покрыты маслом!");
		addMessage("ru", "gui.slipperyhands.off", "Игрок &c[PLAYER] &2запил масло!");
		addMessage("ru", "gui.tntworld.default", "Игрок &c[PLAYER] &2теперь видит только TNT в радиусе 75 блоков!");
		addMessage("ru", "gui.rickroll.default", "Игрок &c[PLAYER] &2теперь катился по ролику!");
		addMessage("ru", "gui.rickroll.ishearing", "Der Spieler &c[PLAYER] &2wird schon gerickrolled!");
		addMessage("ru", "gui.dontstopjumping.on", "Игрок &c[PLAYER] &2уже бросает броски!");
		addMessage("ru", "gui.dontstopjumping.off", "Игрок &c[PLAYER] &2теперь перестает прыгать!");
		addMessage("ru", "gui.deaf.on", "Игрок &c[PLAYER] &2больше ничего не слышит!");
		addMessage("ru", "gui.deaf.off", "Игрок &c[PLAYER] &2снова что-то слышит!");
		addMessage("ru", "gui.confused.on", "Игрок &c[PLAYER] &2сбит с толку!");
		addMessage("ru", "gui.confused.off", "Игрок &c[PLAYER] &2снова в здравом уме!");
		addMessage("ru", "gui.anvils.on", "Наковальни теперь будут падать на игрока &c[PLAYER]&2!");
		addMessage("ru", "gui.anvils.off", "Больше нет наковальни, падающей на игрока &c[PLAYER]&2!");
		addMessage("ru", "gui.cows", "Агрессивные коровы теперь атакуют &c[PLAYER]&2!");
		addMessage("ru", "gui.giorno.on", "Джорно Джованна теперь атакует &c[PLAYER]&2!");
		addMessage("ru", "gui.giorno.off", "Джорно Джованна теперь оставляет игрока &c[PLAYER] &2в покое!");
		addMessage("ru", "gui.spooky.on", "Игрок &c[PLAYER] &2теперь находится в царстве теней!");
		addMessage("ru", "gui.spooky.off", "Игрок &c[PLAYER] &2больше не находится в царстве теней!");
		addMessage("ru", "gui.spooky.world", "Не существует мира под названием \"SpookyWorld\"!");
		addMessage("ru", "gui.endcredits", "Игрок &c[PLAYER] &2теперь может видеть кредиты!");
		addMessage("ru", "gui.tpall", "Все игроки были телепортированы к вам!");
		addMessage("ru", "gui.fakeleave.default", "В чат отправлено сообщение FakeLeave!");
		addMessage("ru", "gui.fakeleave.message", "&6[PLAYER] left the Game.");
		addMessage("ru", "gui.hackserver", "Начинается чит-процесс!");
		addMessage("ru", "gui.loading", "Игрок &c[PLAYER] &2 теперь видит экран загрузки до перезапуска!");
		addMessage("ru", "gui.vomit.on", "Игрок &c[PLAYER] &2 не может остановить рвоту!");
		addMessage("ru", "gui.vomit.off", "Игрок &c[PLAYER] &2остановил рвоту!");
		addMessage("ru", "control.world", "&cМир изменился!");
		addMessage("ru", "control.distance", "&cСлишком далеко!");
	}
	
	private static void loadIndo() {
		addMessage("indo", "nopermission", "Anda tidak memiliki izin untuk perintah ini!");
		addMessage("indo", "noonline", "Pemain ini tidak online!");
		addMessage("indo", "command.troll.default", "Menu Troll telah dibuka!");
		addMessage("indo", "command.troll.help.line.1", "TrollV4 by Presti");
		addMessage("indo", "command.troll.help.line.2", "Troll Mode &aActive!");
		addMessage("indo", "command.troll.help.line.3", "Troll Vanish &aActive!");
		addMessage("indo", "command.troll.help.line.4", "&4Commands:");
		addMessage("indo", "command.troll.help.line.5", "&4Player Troll GUI &8- &c/troll");
		addMessage("indo", "command.troll.help.line.6", "&4Server Troll GUI &8- &c/troll server");
		addMessage("indo", "command.troll.help.line.7", "&4Item Troll GUI &8- &c/troll items");
		addMessage("indo", "command.troll.help.line.8", "&4Troll Vanish &8- &c/troll v");
		addMessage("indo", "command.troll.help.line.9", "&4Gamemode &8- &c/troll gm");
		addMessage("indo", "command.troll.help.line.10", "&4Fly &8- &c/troll fly");
		addMessage("indo", "command.troll.help.line.11", "&4Changelog &8- &c/troll changelog");
		addMessage("indo", "command.troll.help.line.12", "&4Credits &8- &c/troll credits");
		addMessage("indo", "command.troll.gm", "Anda sekarang berada di Gamemode 1!");
		addMessage("indo", "command.troll.fly.on", "Kamu bisa terbang sekarang!");
		addMessage("indo", "command.troll.fly.off", "Kamu sudah tidak bisa terbang lagi!");
		addMessage("indo", "command.troll.vanish.on", "sekarang kamu lagi vanish!");
		addMessage("indo", "command.troll.vanish.off", "kamu tidak vanish lagi!");
		addMessage("indo", "command.troll.items", "Item Troll Inventory dibuka!");
		addMessage("indo", "command.troll.server", "Server Troll Inventory dibuka!");
		addMessage("indo", "command.troll.notfound.line.1", "&8>");
		addMessage("indo", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("indo", "command.troll.notfound.line.3", "Semua Perintah /troll help");
		addMessage("indo", "command.troll.notfound.line.4", "TrollV4 by");
		addMessage("indo", "command.troll.notfound.line.5", "Presti");
		addMessage("indo", "command.troll.notfound.line.6", "&8>");
		addMessage("indo", "command.troll.wrongargs", "Argumen yang tidak diketahui, harap gunakan /troll help");
		addMessage("indo", "command.troll.noplayer", "Anda bukan seorang Player!");
		addMessage("indo", "gui.mlg", "kamu membuat &c[PLAYER] &2melakukan &cMLG&2!");
		addMessage("indo", "gui.explode", "kamu membuat &c[PLAYER] &2meledak!");
		addMessage("indo", "gui.fakehack.on", "kamu membuat &c[PLAYER] &2hack!");
		addMessage("indo", "gui.fakehack.off", "kamu memaksa &c[PLAYER] &2untuk berhenti!");
		addMessage("indo", "gui.demo", "player &c[PLAYER] &2sekarang lagi melihat layar demo!");
		addMessage("indo", "gui.strike", "kamu memukul &c[PLAYER] &2dengan petir!");
		addMessage("indo", "gui.hackuser", "proses hack sedang dimulai!");
		addMessage("indo", "gui.rocket", "kamu membuat &c[PLAYER] &2terbang seperti rocket!");
		addMessage("indo", "gui.spam", "kamu telah spam &c[PLAYER]&2!");
		addMessage("indo", "gui.startcontrol.start", "kamu telah mengendalikan &c[PLAYER]&2!");
		addMessage("indo", "gui.startcontrol.cantcontrol", "Kamu tidak bisa mengendalikan dia!");
		addMessage("indo", "gui.startcontrol.yourself", "kamu tidak bisa mengontrol diri sendiri!");
		addMessage("indo", "gui.startcontrol.iscontroled", "player ini sudah di control!");
		addMessage("indo", "gui.startcontrol.alreadyc", "kamu sudah mengontrol orang lain!");
		addMessage("indo", "gui.stopcontrol.stop", "kamu berhenti mengontol &c[PLAYER]&2!");
		addMessage("indo", "gui.stopcontrol.nocontrol", "kamu tidak mengontrol siapa siapa!");
		addMessage("indo", "gui.fakeop.default", "kamu fake op kan &c[PLAYER]&2!");
		addMessage("indo", "gui.fakeop.opm", "&7&o[Server: Opped [PLAYER]]");
		addMessage("indo", "gui.crash.default", "kamu membuat &c[PLAYER] &2Crash!");
		addMessage("indo", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("indo", "gui.freeze.on", "kamu membekukan &c[PLAYER]&2!");
		addMessage("indo", "gui.freeze.off", "kamu mencairkan &c[PLAYER]&2!");
		addMessage("indo", "gui.anticheat.default", "anticheat sudah di nyalakan &c[PLAYER]&2!");
		addMessage("indo", "gui.anticheat.detected",
				"&7[&cPrestige&7-&cAntiCheat&7] &2Stop &cHacking(SPEED) &7[&1VL&7/&620&7]");
		addMessage("indo", "gui.lag.on", "player &c[PLAYER] &2sekarang lagging!");
		addMessage("indo", "gui.lag.off", "player &c[PLAYER] &2berhenti lagging!");
		addMessage("indo", "gui.arrest", "player &c[PLAYER] &2tertangkap!");
		addMessage("indo", "gui.rotate.on", "player &c[PLAYER] &2berputar!");
		addMessage("indo", "gui.rotate.off", "player &c[PLAYER] &2berhenti berputar!");
		addMessage("indo", "gui.rndmtp.on", "player &c[PLAYER] &2mulai berpindah!");
		addMessage("indo", "gui.rndmtp.off", "player &c[PLAYER] &2sudah berhenti berpindah!");
		addMessage("indo", "gui.tnttrace.on", "dibelakang &c[PLAYER] &2sudah muncul banyak tnt!");
		addMessage("indo", "gui.tnttrace.off", "dibelakang &c[PLAYER] &2tnt nya sudah tidak muncul lagi!");
		addMessage("indo", "gui.wtf", "Player &c[PLAYER] &2menjadi gila!");
		addMessage("indo", "gui.webtrap", "player &c[PLAYER] &2sekarang stuck di cobweb!");
		addMessage("indo", "gui.lsd", "player &c[PLAYER] &2sekarang melihat dunia lebih berbeda!");
		addMessage("indo", "gui.guardian", "player &c[PLAYER] &2sekarang mempunyai efek guardian!");
		addMessage("indo", "gui.herobrine.on", "玩家 &c[PLAYER] &2sekarang jadi herobrine!");
		addMessage("indo", "gui.herobrine.off", "玩家 &c[PLAYER] &2tidak jadi herobrine lagi!");
		addMessage("indo", "gui.arrowspam.on", "玩家 &c[PLAYER] &2telah di spam anak panah!");
		addMessage("indo", "gui.arrowspam.off", "玩家 &c[PLAYER] &2sudah tidak di spam anak panah lagi!");
		addMessage("indo", "gui.tornado.on", "玩家 &c[PLAYER] &2di kejar tornado!");
		addMessage("indo", "gui.tornado.off", "玩家 &c[PLAYER] &2sudah tidak di kejar tornado!");
		addMessage("indo", "gui.fakeinv.default", "玩家 &c[PLAYER] &2kehilangan inventory nya untuk 5 detik!");
		addMessage("indo", "gui.fakeinv.cancel", "玩家 &c[PLAYER] &2dapat inventory nya kembali!");
		addMessage("indo", "gui.noinv.on", "玩家 &c[PLAYER] &2tidak bisa buka inventory lagi!");
		addMessage("indo", "gui.noinv.off", "玩家 &c[PLAYER] &2sudah bisa buka inventory!");
		addMessage("indo", "gui.slipperyhands.on", "玩家 &c[PLAYER] &2tangan nya di oleskan mentega!");
		addMessage("indo", "gui.slipperyhands.off", "玩家 &c[PLAYER] &2tangan nya sudah di bersihkan!");
		addMessage("indo", "gui.tntworld.default", "玩家 &c[PLAYER] &2hanya melihat tnt di world!");
		addMessage("indo", "gui.rickroll.default", "玩家 &c[PLAYER] &2mendengar sebuah lagu!");
		addMessage("indo", "gui.rickroll.ishearing", "玩家 &c[PLAYER] &2sudah mendengar sebuah lagu!");
		addMessage("indo", "gui.dontstopjumping.on", "玩家 &c[PLAYER] &2tidak bisa berhenti melompat!");
		addMessage("indo", "gui.dontstopjumping.off", "玩家 &c[PLAYER] &2berhenti lompat!");
		addMessage("indo", "gui.deaf.on", "玩家 &c[PLAYER] &2tidak bisa mendengar apa apa lagi!");
		addMessage("indo", "gui.deaf.off", "玩家 &c[PLAYER] &2sudah bisa mendengar!");
		addMessage("indo", "gui.confused.on", "玩家 &c[PLAYER] &2sekarang pusing!");
		addMessage("indo", "gui.confused.off", "玩家 &c[PLAYER] &2sudah tidak pusing!");
		addMessage("indo", "gui.anvils.on", "Landasan sekarang jatuh pada pemain &c[PLAYER]&2!");
		addMessage("indo", "gui.anvils.off", "Landasan tidak lagi jatuh pada pemain &c[PLAYER]&2!");
		addMessage("indo", "gui.cows", "Sapi agresif sekarang menyerang &c[PLAYER]&2!");
		addMessage("indo", "gui.giorno.on", "Giorno Giovanna sekarang menyerang &c[PLAYER]&2!");
		addMessage("indo", "gui.giorno.off", "Giorno Giovanna sekarang pergi &c[PLAYER] &2sendirian!");
		addMessage("indo", "gui.spooky.on", "Pemain &c[PLAYER] &2sekarang ada di Alam Bayangan!");
		addMessage("indo", "gui.spooky.off", "Pemain &c[PLAYER] &2tidak lagi berada di alam Bayangan!");
		addMessage("indo", "gui.spooky.world", "Tidak ada dunia yang disebut \"SpookyWorld\"!");
		addMessage("indo", "gui.endcredits", "Pemain &c[PLAYER] &2sekarang melihat kredit akhir!");
		addMessage("indo", "gui.tpall", "semua player teleport ke kamu!");
		addMessage("indo", "gui.fakeleave.default", "fake message sudah di jabarkan di chat!");
		addMessage("indo", "gui.fakeleave.message", "&6[PLAYER] left the Game.");
		addMessage("indo", "gui.hackserver", "proses hack di mulai!");
		addMessage("indo", "gui.loading", "Pemain &c[PLAYER] &2 sekarang melihat layar pemuatan hingga mereka memulai ulang!");
		addMessage("indo", "gui.vomit.on", "Pemain &c[PLAYER] &2tidak bisa berhenti muntah!");
		addMessage("indo", "gui.vomit.off", "Pemain &c[PLAYER] &2berhenti muntah!");
		addMessage("indo", "control.world", "&4Mengganti dunia");
		addMessage("indo", "control.distance", "&4TERLALU JAUH");
	}
	
	private static void loadSpanish() {
		addMessage("es", "nopermission", "¡No tienes permiso para este comando!");
		addMessage("es", "noonline", "¡Este jugador no está conectado!");
		addMessage("es", "command.troll.default", "¡El Menú Troll ha sido abierto!");
		addMessage("es", "command.troll.help.line.1", "TrollV4 de Presti");
		addMessage("es", "command.troll.help.line.2", "Modo troll &aActivado!!");
		addMessage("es", "command.troll.help.line.3", "Troll Desaparecer &aActivado!");
		addMessage("es", "command.troll.help.line.4", "&4Comandos:");
		addMessage("es", "command.troll.help.line.5", "&4Jugador Troll GUI &8- &c/troll");
		addMessage("es", "command.troll.help.line.6", "&4Servidor GUI Troll &8- &c/servidor troll");
		addMessage("es", "command.troll.help.line.7", "&4Objeto GUI troll &8- &c/objetos troll");
		addMessage("es", "command.troll.help.line.8", "&4Troll Vanish &8- &c/troll v");
		addMessage("es", "command.troll.help.line.9", "&4Gamemode &8- &c/troll gm");
		addMessage("es", "command.troll.help.line.10", "&4Volar &8- &c/vuelo troll");
		addMessage("es", "command.troll.help.line.11", "&4Registro de cambios &8- &c/troll changelog");
		addMessage("es", "command.troll.help.line.12", "&4Créditos &8- &c/troll credits");
		addMessage("es", "command.troll.gm", "¡Ahora en Gamemode 1!");
		addMessage("es", "command.troll.fly.on", "¡Ya puedes volar!");
		addMessage("es", "command.troll.fly.off", "¡No puedes volar más!");
		addMessage("es", "command.troll.vanish.on", "¡Ya está en Vanish!");
		addMessage("es", "command.troll.vanish.off", "¡Tu ya no está en Vanish!");
		addMessage("es", "command.troll.items", "¡Artículo Troll Inv fue abierto!");
		addMessage("es", "command.troll.server", "Server Troll Inv fue abierto!");
		addMessage("es", "command.troll.notfound.line.1", "&8>");
		addMessage("es", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("es", "command.troll.notfound.line.3", "Todos los comandos /troll help");
		addMessage("es", "command.troll.notfound.line.4", "TrollV4 por");
		addMessage("es", "command.troll.notfound.line.5", "Presti");
		addMessage("es", "command.troll.notfound.line.6", "&8>");
		addMessage("es", "command.troll.wrongargs", "Argumentos desconocidos, por favor usa /troll");
		addMessage("es", "command.troll.noplayer", "No es jugador!");
		addMessage("es", "gui.mlg", "¡Deja al jugador &c[PLAYER] &2hacer un &cMLG&2!!");
		addMessage("es", "gui.explode", "Has hecho al jugador &c[PLAYER] &2explode!");
		addMessage("es", "gui.fakehack.on", "Has hecho al jugador &c[PLAYER] &2hack!");
		addMessage("es", "gui.fakehack.off", "¡Has forzado al jugador &c[PLAYER] &2a parar!");
		addMessage("es", "gui.demo", "¡El jugador &c[PLAYER] &2está viendo una pantalla de demostración!");
		addMessage("es", "gui.strike", "¡Has golpeado el jugador &c[PLAYER] &2con un relámpago!");
		addMessage("es", "gui.hackeser", "¡El proceso de piratería comienza ahora!");
		addMessage("es", "gui.rocket", "Hiciste al jugador &c[PLAYER] &2volar como un bolsillo!");
		addMessage("es", "gui.spam", "¡Has golpeado completamente al jugador &c[PLAYER]&2!");
		addMessage("es", "gui.startcontrol.start", "Controlar su &c[PLAYER]&2!");
		addMessage("es", "gui.startcontrol.cantcontrol", "¡No puedes controlar a este jugador!");
		addMessage("es", "gui.startcontrol.yourself", "¡No puedes controlarte a ti mismo!");
		addMessage("es", "gui.startcontrol.iscontroled", "¡El jugador ya está controlado!");
		addMessage("es", "gui.startcontrol.alreadyc", "¡Ya controlas a otra persona!");
		addMessage("es", "gui.stopcontrol.stop", "¡Has dejado de controlar &c[PLAYER]&2!");
		addMessage("es", "gui.stopcontrol.nocontrol", "¡No controlas nada!");
		addMessage("es", "gui.fakeop.default", "¡Usted falsificó &c[PLAYER]&2!");
		addMessage("es", "gui.fakeop.opm", "&7&o[Servidor: Apareció [PLAYER]]");
		addMessage("es", "gui.crash.default", "¡Deja que el jugador &c[PLAYER] &2bloquee!");
		addMessage("es", "gui.crash.message",
				"java.net.ConnectException: Conexión agotada: No hay más información");
		addMessage("es", "gui.freeze.on", "Has congelado al jugador &c[PLAYER]&2!");
		addMessage("es", "gui.freeze.off", "Has descongelado al jugador &c[PLAYER]&2!");
		addMessage("es", "gui.anticheat.default", "¡El Anticheat ha empezado a detectar &c[PLAYER]&2!");
		addMessage("es", "gui.anticheat.detected",
				"&7[&cPrestige&7-&cAntiCheat&7] &2Detectar &cHacking(SPEED) &7[&1VL&7/&620&7]");
		addMessage("es", "gui.lag.on", "¡El usuario &c[PLAYER] &2se ha retrasado!");
		addMessage("es", "gui.lag.off", "El usuario &c[PLAYER] &2ha dejado de retrasarse");
		addMessage("es", "gui.arrest", "El usuario &c[PLAYER] &2está detenido!");
		addMessage("es", "gui.rotate.on", "¡El usuario &c[PLAYER] &2ha empezado a girar!");
		addMessage("es", "gui.rotate.off", "¡El usuario &c[PLAYER] &2ha dejado de girar!");
		addMessage("es", "gui.rndmtp.on", "¡El usuario &c[PLAYER] &2empieza a ser teletransportado por el aire!");
		addMessage("es", "gui.rndmtp.off", "¡El usuario &c[PLAYER] &2para ser teletransportado por el aire!");
		addMessage("es", "gui.tnttrace.on", "¡Entra al usuario &c[PLAYER] &2ahora un rastro de TnT aparecido!");
		addMessage("es", "gui.tnttrace.off", "¡Contra el usuario &c[PLAYER] &2no se ha generado ningún rastro de TnT!");
		addMessage("es", "gui.wtf", "¡El usuario &c[PLAYER] &2se está volviendo loco!");
		addMessage("es", "gui.webtrap", "¡El usuario &c[PLAYER] &2está atascado en Cobweb!");
		addMessage("es", "gui.lsd", "¡El usuario &c[PLAYER] &2está viendo el mundo en otro perspectivo!");
		addMessage("es", "gui.guardian", "¡El usuario &c[PLAYER] &2ahora tiene el efecto Guardiano!");
		addMessage("es", "gui.herobrine.on", "¡El usuario &c[PLAYER] &2es ahora Herobrine!");
		addMessage("es", "gui.herobrine.off", "¡El usuario &c[PLAYER] &2ya no es Herobrine!");
		addMessage("es", "gui.arrowspam.on", "¡El usuario &c[PLAYER] &2ahora está recibiendo spammend por flechas!");
		addMessage("es", "gui.arrowspam.off", "¡El usuario &c[PLAYER] &2ya no está recibiendo spammend por Flechas!");
		addMessage("es", "gui.tornado.on", "¡El usuario &c[PLAYER] &2está siendo perseguido por un tornado!");
		addMessage("es", "gui.tornado.off", "El usuario &c[PLAYER] &2ya no es perseguido por un tornado!");
		addMessage("es", "gui.fakeinv.default", "¡El usuario &c[PLAYER] &2perdió su inventario durante 5 segundos!");
		addMessage("es", "gui.fakeinv.cancel", "¡El usuario &c[PLAYER] &2recuperó su inventario!");
		addMessage("es", "gui.noinv.on", "¡El usuario &c[PLAYER] &2¡Ya no puedo abrir ningún Inventario!");
		addMessage("es", "gui.noinv.off", "¡El usuario &c[PLAYER] &2puede volver a abrir cualquier inventario!");
		addMessage("es", "gui.slipperyhands.on", "¡El usuario &c[PLAYER] &2jugó con algo de mantequilla!");
		addMessage("es", "gui.slipperyhands.off", "¡El usuario &c[PLAYER] &2limpió sus manos!");
		addMessage("es", "gui.tntworld.default", "¡El usuario &c[PLAYER] &2ahora solo ve TnT en su mundo!");
		addMessage("es", "gui.rickroll.default", "¡El usuario &c[PLAYER] &2se está haciendo rickrolling!");
		addMessage("es", "gui.rickroll.ishearing", "¡El usuario &c[PLAYER] &2ya se está rickrollando!");
		addMessage("es", "gui.dontstopjumping.on", "¡El usuario &c[PLAYER] &2no puedo dejar de saltar!");
		addMessage("es", "gui.dontstopjumping.off", "¡El usuario &c[PLAYER] &2ha dejado de saltar!");
		addMessage("es", "gui.deaf.on", "¡El usuario &c[PLAYER] &2ya no puedo oír nada!");
		addMessage("es", "gui.deaf.off", "¡El usuario &c[PLAYER] &2puede escuchar algo de nuevo!");
		addMessage("es", "gui.confesed.on", "¡El usuario &c[PLAYER] &2está ahora confundido!");
		addMessage("es", "gui.confesed.off", "¡El usuario &c[PLAYER] &2obtuvo algo de leche!");
		addMessage("es", "gui.anvils.on", "¡Los yunques ahora caen en jugador &c[PLAYER]&2!");
		addMessage("es", "gui.anvils.off", "¡Los yunques ya no caen en el jugador &c[PLAYER]&2!");
		addMessage("es", "gui.cows", "Ahora las vacas agresivas atacan &c[PLAYER]&2!");
		addMessage("es", "gui.giorno.on", "Giorno Giovanna ahora ataca &c[PLAYER]&2!");
		addMessage("es", "gui.giorno.off", "Giorno Giovanna ahora deja &c[PLAYER] &2solo!");
		addMessage("es", "gui.spooky.on", "¡El jugador &c[PLAYER] &2está ahora en el Reino Sombría!");
		addMessage("es", "gui.spooky.off", "¡El jugador &c[PLAYER] &2ya no está en el reino Sombría!");
		addMessage("es", "gui.spooky.world", "No hay ningún mundo llamado \"SpookyWorld\"!");
		addMessage("es", "gui.endcredits", "¡El jugador &c[PLAYER] &2ahora ve los créditos finales!");
		addMessage("es", "gui.tpall", "¡Todos los jugadores fueron teletransportados a ti!");
		addMessage("es", "gui.fakeleave.default", "¡Se ha publicado un mensaje falso en el chat!");
		addMessage("es", "gui.fakeleave.message", "&6[PLAYER] ha salido del juego.");
		addMessage("es", "gui.hackserver", "¡El proceso de piratería comienza ahora!");
		addMessage("es", "gui.loading", "¡El jugador &c[PLAYER] &2está viendo ahora la pantalla de carga hasta que reinicie!");
		addMessage("es", "gui.vomit.on", "El jugador &c[PLAYER] &2¡no puede parar de vomitar!");
		addMessage("es", "gui.vomit.off", "¡El jugador &c[PLAYER] &2dejó de vomitar!");
		addMessage("es", "control.world", "&4MUNDO CHED");
		addMessage("es", "control.distance", "&4DEMASIADO LEJOS");
	}

		private static void loadSimplified_Chinese() {
		addMessage("zh_CN", "nopermission", "你没有权限执行此指令!");
		addMessage("zh_CN", "noonline", "该玩家不在线!");
		addMessage("zh_CN", "command.troll.default", "已打开整蛊菜单!");
		addMessage("zh_CN", "command.troll.help.line.1", "TrollV4 的作者是 Presti");
		addMessage("zh_CN", "command.troll.help.line.2", "整蛊模式 &a已开启!");
		addMessage("zh_CN", "command.troll.help.line.3", "隐身整蛊 &a已开启!");
		addMessage("zh_CN", "command.troll.help.line.4", "&4指令:");
		addMessage("zh_CN", "command.troll.help.line.5", "&4玩家整蛊 &8- &c/troll");
		addMessage("zh_CN", "command.troll.help.line.6", "&4服务器整蛊 &8- &c/troll server");
		addMessage("zh_CN", "command.troll.help.line.7", "&4整蛊物品 &8- &c/troll items");
		addMessage("zh_CN", "command.troll.help.line.8", "&4隐身整蛊 &8- &c/troll v");
		addMessage("zh_CN", "command.troll.help.line.9", "&4创造模式 &8- &c/troll gm");
		addMessage("zh_CN", "command.troll.help.line.10", "&4飞行 &8- &c/troll fly");
		addMessage("zh_CN", "command.troll.help.line.11", "&4更新记录 &8- &c/troll changelog");
		addMessage("zh_CN", "command.troll.help.line.12", "&4整蛊点数 &8- &c/troll credits");
		addMessage("zh_CN", "command.troll.gm", "你现在是创造模式了!");
		addMessage("zh_CN", "command.troll.fly.on", "你现在可以飞行了!");
		addMessage("zh_CN", "command.troll.fly.off", "你不能再飞行了!");
		addMessage("zh_CN", "command.troll.vanish.on", "你隐身了!");
		addMessage("zh_CN", "command.troll.vanish.off", "你不再隐身了!");
		addMessage("zh_CN", "command.troll.items", "已开启整蛊物品菜单!");
		addMessage("zh_CN", "command.troll.server", "全服整蛊菜单已开启!");
		addMessage("zh_CN", "command.troll.notfound.line.1", "&8>");
		addMessage("zh_CN", "command.troll.notfound.line.2", "Troll V4 [VERSION]");
		addMessage("zh_CN", "command.troll.notfound.line.3", "查询所有指令 /troll help");
		addMessage("zh_CN", "command.troll.notfound.line.4", "TrollV4 的作者为");
		addMessage("zh_CN", "command.troll.notfound.line.5", "Presti");
		addMessage("zh_CN", "command.troll.notfound.line.6", "&8>");
		addMessage("zh_CN", "command.troll.wrongargs", "未知指令，请使用 /troll help");
		addMessage("zh_CN", "command.troll.noplayer", "你并不是玩家!");
		addMessage("zh_CN", "gui.mlg", "你整蛊了玩家 &c[PLAYER] &2整蛊内容 &cMLG&2!");
		addMessage("zh_CN", "gui.explode", "你令玩家 &c[PLAYER] &2爆炸了!");
		addMessage("zh_CN", "gui.fakehack.on", "你为 &c[PLAYER] &2开启了外挂!");
		addMessage("zh_CN", "gui.fakehack.off", "你停止了 &c[PLAYER] &2的外挂!");
		addMessage("zh_CN", "gui.demo", "玩家 &c[PLAYER] &2现在正在观赏试玩界面!");
		addMessage("zh_CN", "gui.strike", "玩家 &c[PLAYER] &2遭雷劈了!");
		addMessage("zh_CN", "gui.hackuser", "黑客程序启动!");
		addMessage("zh_CN", "gui.rocket", "你令玩家 &c[PLAYER] &2使用烟花飞了起来!");
		addMessage("zh_CN", "gui.spam", "你完全令 &c[PLAYER]&2 糊涂了!");
		addMessage("zh_CN", "gui.startcontrol.start", "你开始控制 &c[PLAYER]&2!");
		addMessage("zh_CN", "gui.startcontrol.cantcontrol", "你无法控制这个玩家!");
		addMessage("zh_CN", "gui.startcontrol.yourself", "你不能控制你自己!");
		addMessage("zh_CN", "gui.startcontrol.iscontroled", "玩家已经被控制了!");
		addMessage("zh_CN", "gui.startcontrol.alreadyc", "你已经在控制其他人了!");
		addMessage("zh_CN", "gui.stopcontrol.stop", "你停止了对 &c[PLAYER]&2 的控制!");
		addMessage("zh_CN", "gui.stopcontrol.nocontrol", "你没有控制任何人!");
		addMessage("zh_CN", "gui.fakeop.default", "你给与了 &c[PLAYER]&2 虚假的管理员权限!");
		addMessage("zh_CN", "gui.fakeop.opm", "&7&o[Server: Opped [PLAYER]]");
		addMessage("zh_CN", "gui.crash.default", "你令玩家 &c[PLAYER] &2崩溃了!");
		addMessage("zh_CN", "gui.crash.message",
				"java.net.ConnectException: Connection timed out: No further information");
		addMessage("zh_CN", "gui.freeze.on", "你冻结了 &c[PLAYER]&2!");
		addMessage("zh_CN", "gui.freeze.off", "你解除了 &c[PLAYER]&2 的冻结!");
		addMessage("zh_CN", "gui.anticheat.default", "反作弊程序开始监测 &c[PLAYER]&2!");
		addMessage("zh_CN", "gui.anticheat.detected",
				"&7[&cPrestige&7-&cAntiCheat&7] &2Stop &cHacking(SPEED) &7[&1VL&7/&620&7]");
		addMessage("zh_CN", "gui.lag.on", "玩家 &c[PLAYER] &2开始卡了!");
		addMessage("zh_CN", "gui.lag.off", "玩家 &c[PLAYER] &2突然不卡了");
		addMessage("zh_CN", "gui.arrest", "玩家 &c[PLAYER] &2被逮捕了!");
		addMessage("zh_CN", "gui.rotate.on", "玩家 &c[PLAYER] &2开始旋转!");
		addMessage("zh_CN", "gui.rotate.off", "玩家 &c[PLAYER] &2停止了旋转!");
		addMessage("zh_CN", "gui.rndmtp.on", "玩家 &c[PLAYER] &2开始随机传送!");
		addMessage("zh_CN", "gui.rndmtp.off", "玩家 &c[PLAYER] &2停止了随机传送!");
		addMessage("zh_CN", "gui.tnttrace.on", "=玩家 &c[PLAYER] &2所在的地方将生成TNT!");
		addMessage("zh_CN", "gui.tnttrace.off", "玩家 &c[PLAYER] &2所在的未知将不会生成TNT!");
		addMessage("zh_CN", "gui.wtf", "玩家 &c[PLAYER] &2要疯掉啦!");
		addMessage("zh_CN", "gui.webtrap", "玩家 &c[PLAYER] &2被困在了蜘蛛网中!");
		addMessage("zh_CN", "gui.lsd", "玩家 &c[PLAYER] &2正在从另一个角度看世界!");
		addMessage("zh_CN", "gui.guardian", "玩家 &c[PLAYER] &2拥有了守卫者的效果!");
		addMessage("zh_CN", "gui.herobrine.on", "玩家 &c[PLAYER] &2身边出现了Him!");
		addMessage("zh_CN", "gui.herobrine.off", "玩家 &c[PLAYER] &2身边空荡荡的!");
		addMessage("zh_CN", "gui.arrowspam.on", "玩家 &c[PLAYER] &2被箭雨袭击了!");
		addMessage("zh_CN", "gui.arrowspam.off", "玩家 &c[PLAYER] &2身边的箭雨停止了袭击!");
		addMessage("zh_CN", "gui.tornado.on", "玩家 &c[PLAYER] &2正在被龙卷风追赶!");
		addMessage("zh_CN", "gui.tornado.off", "玩家 &c[PLAYER] &2身边的龙卷风渐渐平息!");
		addMessage("zh_CN", "gui.fakeinv.default", "玩家 &c[PLAYER] &2在这5秒内失去了所有的物品!");
		addMessage("zh_CN", "gui.fakeinv.cancel", "玩家 &c[PLAYER] &2的物品回来啦!");
		addMessage("zh_CN", "gui.noinv.on", "玩家 &c[PLAYER] &2将无法打开任何容器!");
		addMessage("zh_CN", "gui.noinv.off", "玩家 &c[PLAYER] &2现在又可以打开容器啦!");
		addMessage("zh_CN", "gui.slipperyhands.on", "玩家 &c[PLAYER] &2觉得手中很烫!");
		addMessage("zh_CN", "gui.slipperyhands.off", "玩家 &c[PLAYER] &2丢弃了手中的物品!");
		addMessage("zh_CN", "gui.tntworld.default", "玩家 &c[PLAYER] &2现在的世界中只剩下了TNT!");
		addMessage("zh_CN", "gui.rickroll.default", "玩家 &c[PLAYER] &2被骗了!");
		addMessage("zh_CN", "gui.rickroll.ishearing", "玩家 &c[PLAYER] &2已经在被骗了!");
		addMessage("zh_CN", "gui.dontstopjumping.on", "玩家 &c[PLAYER] &的脚不受控制的起跳!");
		addMessage("zh_CN", "gui.dontstopjumping.off", "玩家 &c[PLAYER] &2的脚恢复了控制!");
		addMessage("zh_CN", "gui.deaf.on", "玩家 &c[PLAYER] &2听不见了!");
		addMessage("zh_CN", "gui.deaf.off", "玩家 &c[PLAYER] &2的耳朵恢复了声音!");
		addMessage("zh_CN", "gui.confused.on", "玩家 &c[PLAYER] &2变得很混乱!");
		addMessage("zh_CN", "gui.confused.off", "玩家 &c[PLAYER] &2获得了一些牛奶!");
		addMessage("zh_CN", "gui.anvils.on", "铁砧雨在 &c[PLAYER]&2 的头上落下!");
		addMessage("zh_CN", "gui.anvils.off", "&c[PLAYER]&2 身边的铁砧雨停下了!");
		addMessage("zh_CN", "gui.cows", "疯狂的奶牛正在攻击 &c[PLAYER]&2!");
		addMessage("zh_CN", "gui.giorno.on", "Giorno Giovanna 正在攻击 &c[PLAYER]&2!");
		addMessage("zh_CN", "gui.giorno.off", "Giorno Giovanna 离开了 &c[PLAYER] &2!");
		addMessage("zh_CN", "gui.spooky.on", "玩家 &c[PLAYER] &2现在在光影的世界!");
		addMessage("zh_CN", "gui.spooky.off", "玩家 &c[PLAYER] &2离开了光影的世界!");
		addMessage("zh_CN", "gui.spooky.world", "没有叫做 \"SpookyWorld\" 的世界!");
		addMessage("zh_CN", "gui.endcredits", "玩家 &c[PLAYER] &2正在查看终末之诗!");
		addMessage("zh_CN", "gui.tpall", "所有玩家被传送到了你的身边!");
		addMessage("zh_CN", "gui.fakeleave.default", "一个假的离开消息被发送在了公屏上!");
		addMessage("zh_CN", "gui.fakeleave.message", "&6[PLAYER] 悄悄离开了.");
		addMessage("zh_CN", "gui.hackserver", "黑客程序启动!");
		addMessage("zh_CN", "gui.loading", "玩家 &c[PLAYER] &2被卡在了加载界面，此界面将会持续到他的游戏重启!");
		addMessage("zh_CN", "gui.vomit.on", "玩家 &c[PLAYER] &2呕吐不止!");
		addMessage("zh_CN", "gui.vomit.off", "玩家 &c[PLAYER] &2停止了呕吐!");
		addMessage("zh_CN", "control.world", "&4切换世界");
		addMessage("zh_CN", "control.distance", "&4太远啦");
	}


	private static void loadCustom() {
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
