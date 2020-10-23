package de.presti.trollv4.api;

import java.io.File;

import org.bukkit.entity.Player;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.EntitySongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;

import de.presti.trollv4.main.Main;

public class PlayMusic {

	public static void play(Player p, String PATH) {
		if (new File(PATH).exists()) {
			Song song = NBSDecoder.parse(new File(PATH));

			EntitySongPlayer esp = new EntitySongPlayer(song);

			esp.setEntity(p);
			esp.setDistance(16);
			esp.addPlayer(p);
			esp.setPlaying(true);
		} else {
			Main.logger.error("Couldnt find rick.nbs");
			if (!new File("plugins/TrollV4/rick.nbs").exists()) {
				Main.logger.info("Downloading Rick.nbs!");
				Main.download("https://trollv4.000webhostapp.com/download/uni/rick.nbs", "plugins/TrollV4/rick.nbs");
				if (new File("plugins/TrollV4/rick.nbs").exists()) {
					Main.logger.info("Downloaded Rick.nbs!");
				} else {
					Main.logger.info("Couldnt download Rick.nbs!");
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	public static boolean isPlaying(Player p) {
		return NoteBlockAPI.getAPI().isReceivingSong(p);
	}

	public static void stop(Player p) {
		if (isPlaying(p)) {
			NoteBlockAPI.stopPlaying(p);
		}
	}

}
