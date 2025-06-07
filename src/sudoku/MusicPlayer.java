package sudoku;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	private static Clip clip;
	
	public MusicPlayer() {
	}
	public static void playSong(String path) {
		
		File musicPath = new File(path);
		if(musicPath.exists()) {
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audio);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				if(path.equals(Song.ERRORSOUND)) {
					gainControl.setValue(-20.0f);
				}
				if(path.equals(Song.SOUNDSTACK)) {
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					gainControl.setValue(-10.0f);
				}
				else {
					clip.start();
				
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void stopSong() {
		clip.stop();
		clip.close();
	}
	public static void restartSong() {
		MusicPlayer.stopSong();
		//MusicPlayer.playSong(Song.SOUNDSTACK);
	}
}
