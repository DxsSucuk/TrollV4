package de.presti.trollv4.config;

import de.presti.trollv4.main.Main;

import java.util.ArrayList;
import java.util.HashMap;

public class Items {

	public static HashMap<String, String> names = new HashMap<>();
	public static ArrayList<String> path = new ArrayList<>();

	public static void addItem(String lang, String ph, String name) {
		ph = ph.toLowerCase();
		lang = lang.toLowerCase();
		name = name.replaceAll("§", "&");

		if (!path.contains(ph)) {
			path.add(ph);
		}

		if (!names.containsKey(lang + "." + ph)) {
			names.put(lang + "." + ph, name);
		}
	}

	public static String getItem(String ph) {
		String name = "Couldnt find " + ph + " !";

		if (names.containsKey(getItemLang() + "." + ph)) {
			name = names.get(getItemLang() + "." + ph);
		}

		name = name.replaceAll("&", "§");

		return name;
	}

	public static String getItemFromChoice(String lang, String ph) {
		String name = "Couldnt find " + ph + " !";
		lang = lang.toLowerCase();

		if (names.containsKey(lang + "." + ph)) {
			name = names.get(lang + "." + ph);
		}

		name = name.replaceAll("&", "§");

		return name;
	}

	public static String getItemFromChoiceRaw(String lang, String ph) {
		String name = "Couldnt find " + ph + " !";
		lang = lang.toLowerCase();

		if (names.containsKey(lang + "." + ph)) {
			name = names.get(lang + "." + ph);
		}

		return name;
	}

	public static String getItemLang() {
		return Main.instance.config.config.getBoolean("Custom-Item-Name") ? "custome" : "default";
	}

	public Items() {
		loadDefault();
		loadCustome();
	}

	public static void loadDefault() {
		addItem("default", "gui.trolls.freeze", "§bUn/Freeze");
		addItem("default", "gui.trolls.fakeop", "§7FakeOP");
		addItem("default", "gui.trolls.crash", "§cCrash");
		addItem("default", "gui.trolls.startcontrol", "§aStartControl");
		addItem("default", "gui.trolls.spam", "§cSpam");
		addItem("default", "gui.trolls.mlg", "§cMLG");
		addItem("default", "gui.trolls.rocket", "§cRocket");
		addItem("default", "gui.trolls.hackuser", "§8Hack User");
		addItem("default", "gui.trolls.strike", "§9Strike");
		addItem("default", "gui.trolls.demo", "§6Demo");
		addItem("default", "gui.trolls.explode", "§cExplode");
		addItem("default", "gui.trolls.fakehack", "§aFakeHack");
		addItem("default", "gui.trolls.anticheat", "§bAntiCheat");
		addItem("default", "gui.trolls.lagging", "§c§kd§cL§kd§ca§kd§cg§kd§cg§kd§ci§kd§cn§kd§cg§c§kd");
		addItem("default", "gui.trolls.arrest", "§cARREST");
		addItem("default", "gui.trolls.rotateplayer", "§bRotate Player");
		addItem("default", "gui.trolls.randomteleport", "§cRandom Teleport");
		addItem("default", "gui.trolls.tnttrace", "§cTnT Trace");
		addItem("default", "gui.trolls.webtrap", "§fWeb §8Trap");
		addItem("default", "gui.trolls.wtf", "§WTF");
		addItem("default", "gui.trolls.lsd", "§cL§5S§bD");
		addItem("default", "gui.trolls.guardian", "§1Guardian");
		addItem("default", "gui.trolls.arrowspam", "§3Arrow Spam");
		addItem("default", "gui.trolls.herobrine", "§9Herobrine");
		addItem("default", "gui.trolls.tornado", "§7Tornado");
		addItem("default", "gui.trolls.fakeinv", "§cFake§7Inv");
		addItem("default", "gui.trolls.noinvforyou", "§cNo §7Inv §2for §cYOU");
		addItem("default", "gui.trolls.slipperyhands", "§aSlippery §7Hands");
		addItem("default", "gui.trolls.tntworld", "§cTnT World");
		addItem("default", "gui.trolls.rickroll", "§bRickRoll");
		addItem("default", "gui.trolls.dontstopjumping", "§cDont Stop §2Jumping");
		addItem("default", "gui.trolls.deaf", "§7Deaf");
		addItem("default", "gui.trolls.confused", "§aConfused");
		addItem("default", "gui.trolls.anvils", "§7Falling Anvils");
		addItem("default", "gui.trolls.cows", "§cMad Cows");
		addItem("default", "gui.trolls.giorno", "§6Giorno Giovanna");
		addItem("default", "gui.trolls.johncena", "§2Everything is JOHN CENA");
		addItem("default", "gui.trolls.spookyworld", "§6Spooky §eWorld §2;D");
		addItem("default", "gui.trolls.endcredits", "§dEnd Credits");
		addItem("default", "gui.servertrolls.tpall", "§6Tpall");
		addItem("default", "gui.servertrolls.hackmessage", "§8Hack Message");
		addItem("default", "gui.servertrolls.fakeleave", "§2Fakeleave");
		addItem("default", "gui.mlgs.cobweb", "§cCobweb MLG");
		addItem("default", "gui.mlgs.water", "§bWater MLG");
		addItem("default", "gui.mlgs.lava", "§6Lava MLG");
		addItem("default", "gui.mlgs.slime", "§aSlime Block MLG");
		addItem("default", "gui.items.diamondsword", "§cOP§8-§bDiamond§8-§rSWORD");
		addItem("default", "gui.items.diamondprotectionchest", "§cOP§8-§bDiamond§8-§3Protection§8-§rCHESTPLATE");
		addItem("default", "gui.items.diamondthornschest", "§cOP§8-§bDiamond§8-§3Thorns§8-§rCHESTPLATE-§32");
		addItem("default", "gui.items.diamondpickaxe", "§cOP§8-§bDiamond§8-§rPICKAXE");
		addItem("default", "gui.items.onehitbow", "§cOP§8-§rBOW");
		addItem("default", "gui.items.woodenhoe", "§cOP§8-§6Wood§8-§rHOE");
		addItem("default", "gui.items.woodensword", "§cOP§8-§6Wood§8-§rSWORD");
		addItem("default", "gui.items.bow.tnt", "§4TNTBow");
		addItem("default", "gui.items.bow.lava", "§eLava-Arrows");
		addItem("default", "gui.items.bow.lightning", "§bLightningBow");
		addItem("default", "gui.items.bow.creeper", "§2CreeperBow");
		addItem("default", "gui.items.bow.bedrock", "§0BedrockBow");
		addItem("default", "gui.items.minigun", "§4MiniGun");
		addItem("default", "gui.items.fireball", "§4FireBall");
		addItem("default", "gui.control.stopcontrol", "§cStopControl");
	}

	public static void loadCustome() {
		if (Main.instance.config.getItemFile().exists()) {
			for (String paths : path) {
				if (Main.instance.config.itemConfig.get(paths) != null) {
					addItem("custome", paths, Main.instance.config.itemConfig.getString(paths));
				} else {
					addItem("custome", paths, "Couldnt find " + paths + " in the items.yml!");
				}
			}
		}
	}

	public static void clearAll() {
		path.clear();
		names.clear();
	}

}