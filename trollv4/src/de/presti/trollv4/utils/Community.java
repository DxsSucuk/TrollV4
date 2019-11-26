package de.presti.trollv4.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.bukkit.Bukkit;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;


public class Community {

	  public static String host;
	  public static int port;
	  public static Socket client = null;
	  public static PrintStream output = null;

	  public static void run() throws UnknownHostException, IOException {
	    client = new Socket(host, port);
	    System.out.println("Verbindung zur Cloud erfolgreich!"); //Verbindung bei MC Start aufbauen ok
	    output = new PrintStream(client.getOutputStream());
	    output.println("Server:" + Bukkit.getIp() + ":" + Bukkit.getPort() + ":" + Main.version + "-" + Data.version);
	    output.close();
	    client.close();
	    
	  }
	  

}
