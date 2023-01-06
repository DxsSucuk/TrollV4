package de.presti.trollv4.logging;

import de.presti.trollv4.main.Main;

public class Logger {

	public static void log(Levels l, String msg) {
		if(l == Levels.INFO) {
			info(msg);
		} else if(l == Levels.WARNING) {
			warning(msg);
		} else if(l == Levels.ERROR) {
			error(msg);
		}
	}
	
	public static void info(String msg) {
		Main.instance.getLogger().info(msg);
	}
	
	public static void warning(String msg) {
		Main.instance.getLogger().warning(msg);
	}
	
	public static void error(String msg) {
		Main.instance.getLogger().severe(msg);
	}
}
