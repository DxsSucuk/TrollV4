package de.presti.trollv4.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import de.presti.trollv4.api.TrollV4API;
import de.presti.trollv4.main.Main;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2018											    *
*	Erstellt: 24.04.2019 / 12:01:55												    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
public class DemoScreen {
	
	public static void DemoScreen(Player player) {
		TrollV4API.DemoScreen(player);
	}

}
