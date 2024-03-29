package de.presti.trollv4.utils.player;

import java.util.Random;

import org.bukkit.Location;

public class LocationUtil {

	public static Location getLocFromRad(Location oldl, int r) {
		Location loc = null;
		
		int x = new Random().nextInt(r) - new Random().nextInt(r);
		int y = new Random().nextInt(r) - new Random().nextInt(r);
		int z = new Random().nextInt(r) - new Random().nextInt(r);
		
		
		boolean xb = ((new Random().nextInt(1)) == 0);
		boolean yb = ((new Random().nextInt(1)) == 0);
		boolean zb = ((new Random().nextInt(1)) == 0);
		
		loc = new Location(oldl.getWorld(), oldl.getX() + (xb ? -(x) : +(x)),  oldl.getY() + (yb ? -(y) : +(y)),  oldl.getZ() + (zb ? -(z) : +(z)));
		
		return loc;
	}
	
	public static Location getLocFromRad(Location oldl, int xr, int yr, int zr) {
		Location loc = null;
		
		int x = new Random().nextInt(xr) - new Random().nextInt(xr);
		int y = new Random().nextInt(yr) - new Random().nextInt(yr);
		int z = new Random().nextInt(zr) - new Random().nextInt(zr);
		
		
		boolean xb = ((new Random().nextInt(1)) == 0);
		boolean yb = ((new Random().nextInt(1)) == 0);
		boolean zb = ((new Random().nextInt(1)) == 0);
		
		loc = new Location(oldl.getWorld(), oldl.getX() + (xb ? -(x) : +(x)),  oldl.getY() + (yb ? -(y) : +(y)),  oldl.getZ() + (zb ? -(z) : +(z)));
		
		return loc;
	}
	
	public static Location getLocFromRad(Location oldl, int xr, int yr, int zr, boolean xb, boolean yb, boolean zb) {
		Location loc = null;
		
		int x = new Random().nextInt(xr) - new Random().nextInt(xr);
		int y = new Random().nextInt(yr) - new Random().nextInt(yr);
		int z = new Random().nextInt(zr) - new Random().nextInt(zr);
		
		loc = new Location(oldl.getWorld(), oldl.getX() + (xb ? -(x) : +(x)),  oldl.getY() + (yb ? -(y) : +(y)),  oldl.getZ() + (zb ? -(z) : +(z)));
		
		return loc;
	}
	
	public static Location getLocFromRad(Location oldl, int xr, int zr) {
		Location loc = null;
		
		int x = new Random().nextInt(xr) - new Random().nextInt(xr);
		int z = new Random().nextInt(zr) - new Random().nextInt(zr);
		
		
		boolean xb = ((new Random().nextInt(1)) == 0);
		boolean zb = ((new Random().nextInt(1)) == 0);
		
		loc = new Location(oldl.getWorld(), oldl.getX() + (xb ? -(x) : +(x)),  oldl.getY(),  oldl.getZ() + (zb ? -(z) : +(z)));
		
		return loc;
	}
	
}
