package de.presti.trollv4.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import de.presti.trollv4.main.Main;
import de.presti.trollv4.utils.server.ServerInfo;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedAttribute;
import com.comphenix.protocol.wrappers.EnumWrappers.NativeGameMode;
import com.comphenix.protocol.wrappers.MinecraftKey;

public class Packets {

	private final static Map<String, Class<?>> ocbClasses = new HashMap<>();
	private final static Map<Class<?>, Map<String, Method>> cachedMethods = new HashMap<>();

	public static Object getConnection(Player player) throws SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		Class<?> ocbPlayer = Packets.getOCBClass("entity.CraftPlayer");
		Method getHandle = Packets.getMethod(ocbPlayer, "getHandle");
		Object nmsPlayer = getHandle.invoke(player);
		Field conField = nmsPlayer.getClass().getField("playerConnection");
		Object con = conField.get(nmsPlayer);
		return con;
	}

	public static boolean sendPacket(Player player, int packetNum, float number) {
		try {
			Class<?> packetClass = Packets.getNMSClass("PacketPlayOutGameStateChange");
			Constructor<?> packetConstructor = packetClass.getConstructor(int.class, float.class);
			Object packet = packetConstructor.newInstance(packetNum, number);
			Method sendPacket = Packets.getNMSClass("PlayerConnection").getMethod("sendPacket",
					Packets.getNMSClass("Packet"));
			sendPacket.invoke(getConnection(player), packet);
		} catch (Exception e) {
			System.out.println("Your Server Version isnt Supporting this Packet! (" + packetNum + ")");
			return false;
		}
		return true;
	}
	
	public static boolean sendPacket(Player player) {
		
		if(!ServerInfo.supportOldPackets())
			return false;
		
		PacketContainer respawn = new PacketContainer(PacketType.Play.Server.RESPAWN);
		
		respawn.getDimensions().write(0, player.getWorld().getEnvironment().getId());
		respawn.getLongs().write(0, (long)player.getWorld().getDifficulty().getValue());
		respawn.getGameModes().write(0, NativeGameMode.fromBukkit(player.getGameMode()));
		respawn.getWorldTypeModifier().write(0, player.getWorld().getWorldType());
		
		try {
			ProtocolLibrary.getProtocolManager().sendServerPacket(player, respawn);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static Class<?> getNMSClass(String name) {
		String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().substring(org.bukkit.Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1, org.bukkit.Bukkit.getServer().getClass().getPackage().getName().length());
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			System.out.println("Your Server Version isnt Supporting this Packet! (" + name + ")");
		}
		return null;
	}

	public static void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") })
					.invoke(playerConnection, new Object[] { packet });
		} catch (Exception e) {
			System.out.println("Your Server Version isnt Supporting this Packet! (" + packet + ")");
		}
	}

	public static Method getMethod(Class<?> clazz, String methodName, Class<?>... params) {
		if (!cachedMethods.containsKey(clazz))
			cachedMethods.put(clazz, new HashMap<String, Method>());

		Map<String, Method> methods = cachedMethods.get(clazz);

		if (methods.containsKey(methodName))
			return methods.get(methodName);

		try {
			Method method = clazz.getMethod(methodName, params);
			methods.put(methodName, method);
			cachedMethods.put(clazz, methods);
			return method;
		} catch (Throwable e) {
			e.printStackTrace();
			methods.put(methodName, null);
			cachedMethods.put(clazz, methods);
			return null;
		}
	}

	public static Class<?> getOCBClass(String localPackage) {

		if (ocbClasses.containsKey(localPackage))
			return ocbClasses.get(localPackage);

		String declaration = "org.bukkit.craftbukkit." + Main.version + "." + localPackage;
		Class<?> clazz;

		try {
			clazz = Class.forName(declaration);
		} catch (Throwable e) {
			System.out.println("Your Server Version isnt Supporting this Packet! (" + localPackage + ")");
			return ocbClasses.put(localPackage, null);
		}

		ocbClasses.put(localPackage, clazz);
		return clazz;
	}
}
