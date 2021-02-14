package de.presti.trollv4.logging;

import de.presti.trollv4.main.Main;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 19.10.2020 / 22:52:14											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class Logger {

	public void log(Levels l, String msg) {
		if(l == Levels.INFO) {
			info(msg);
		} else if(l == Levels.WARNING) {
			warning(msg);
		} else if(l == Levels.ERROR) {
			error(msg);
		}
	}
	
	public void info(String msg) {
		Main.instance.getLogger().info(msg);
	}
	
	public void warning(String msg) {
		Main.instance.getLogger().warning(msg);
	}
	
	public void error(String msg) {
		Main.instance.getLogger().severe(msg);
	}
}
