package de.presti.trollv4.api;

import java.io.File;

import org.bukkit.entity.Player;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.EntitySongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;

public class PlayMusic {
	
	public static void play(Player p, String PATH) {
		Song song = NBSDecoder.parse(new File(PATH));
		
		EntitySongPlayer esp = new EntitySongPlayer(song);
		
		esp.setEntity(p);
		esp.setDistance(16);
		esp.addPlayer(p);
		esp.setPlaying(true);
	}
	
	@SuppressWarnings("static-access")
	public static boolean isPlaying(Player p) {
		return NoteBlockAPI.getAPI().isReceivingSong(p);
	}
	
	public static void stop(Player p) {
		if(isPlaying(p)) {
			NoteBlockAPI.stopPlaying(p);
		}
	}
	
}
