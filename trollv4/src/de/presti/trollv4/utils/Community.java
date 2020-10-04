package de.presti.trollv4.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
	    System.out.println("Cloud Connection Succesful!");
	    output = new PrintStream(client.getOutputStream());
	    output.println("Server:" + Bukkit.getIp() + ":" + Bukkit.getPort() + ":" + Main.version + "-" + Data.version);
	    output.close();
	    client.close();
	    
	  }
	  

}