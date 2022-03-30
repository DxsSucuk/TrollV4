package de.presti.trollv4.api;

import java.io.File;

import org.bukkit.entity.Player;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.EntitySongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;

import de.presti.trollv4.main.Main;

public class PlayMusic {

	/**
	 * Plays Musik(.nbs) with NoteBlocks.
	 * 
	 * @param p the player that should hear the song.
	 * @param PATH the full file path.
	 * @since 4.4.4
	 * 
	 */
	public static void play(Player p, String PATH) {
		if (new File(PATH).exists()) {
			Song song = NBSDecoder.parse(new File(PATH));

			EntitySongPlayer esp = new EntitySongPlayer(song);

			esp.setEntity(p);
			esp.setDistance(16);
			esp.addPlayer(p);
			esp.setPlaying(true);
		} else {
			if (!new File("plugins/TrollV4/rick.nbs").exists()) {
				Main.logger.info("Downloading Rick.nbs!");
				Main.download("https://cdn.azura.best/download/trollv4/uni/rick.nbs", "plugins/TrollV4/rick.nbs");
				if (new File("plugins/TrollV4/rick.nbs").exists()) {
					Main.logger.info("Downloaded Rick.nbs!");
				} else {
					Main.logger.info("Couldnt download Rick.nbs!");
				}
			} else if (!new File("plugins/TrollV4/giorno.nbs").exists()) {
				Main.logger.info("Downloading Giorno.nbs!");
				Main.download("https://cdn.azura.best/download/trollv4/uni/giorno.nbs", "plugins/TrollV4/giorno.nbs");
				if (new File("plugins/TrollV4/giorno.nbs").exists()) {
					Main.logger.info("Downloaded Giorno.nbs!");
				} else {
					Main.logger.info("Couldnt download Giorno.nbs!");
				}
			}
		}
	}

	/**
	 * Check if the NoteBlockAPI plays Music to an User.
	 * 
	 * @param p the player that should be checked.
	 * @return boolean
	 * @since 4.4.4
	 * 
	 */
	@SuppressWarnings("static-access")
	public static boolean isPlaying(Player p) {
		return NoteBlockAPI.getAPI().isReceivingSong(p);
	}

	/**
	 * Stops every Music that is playing by the NoteBlockAPI.
	 * 
	 * @param p the player that shouldnt hear any music anymore.
	 * @since 4.4.4
	 * 
	 */
	public static void stop(Player p) {
		if (isPlaying(p)) {
			NoteBlockAPI.stopPlaying(p);
		}
	}

}
