package de.presti.trollv4.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import org.bukkit.Bukkit;

import de.presti.trollv4.main.Data;
import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.server.ServerInfo;


public class Community {

	  public static String host;
	  public static int port;
	  public static Socket client = null;
	  public static PrintStream output = null;

	  public static void run() throws UnknownHostException, IOException {
	    client = new Socket(host, port);
	    client.setSoTimeout(5000);
	    System.out.println("Cloud Connection Succesful!");
	    output = new PrintStream(client.getOutputStream());
	    output.println("Server:" + Bukkit.getPort() + "+" + ServerInfo.getMcVersion() + "-" + Data.version);
	    output.close();
	    client.close();
	    
	  }
	  

}
