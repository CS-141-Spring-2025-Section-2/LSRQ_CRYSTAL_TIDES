package _main_;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;

public class SoundManager {
	////////////////////////////////////////////////////////////
    private Clip clip;
    private String currentFile;
	////////////////////////////////////////////////////////////
    public void setFile(String filePath) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            // Load audio from the external "res" folder
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                throw new IOException("File not found: " + filePath);
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            currentFile = filePath;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
	////////////////////////////////////////////////////////////
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
	////////////////////////////////////////////////////////////
    public void loop() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
	////////////////////////////////////////////////////////////
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
	////////////////////////////////////////////////////////////
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
	////////////////////////////////////////////////////////////
    public boolean isCurrentTrack(String filePath) {
        return currentFile != null && currentFile.equals(filePath);
    }
	////////////////////////////////////////////////////////////
    public void playSoundEffect(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                throw new IOException("File not found: " + filePath);
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip soundEffect = AudioSystem.getClip();
            soundEffect.open(audioStream);
            soundEffect.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
	////////////////////////////////////////////////////////////
    public void playMusic(String path) {
        setFile(path);
        loop();
    }
	////////////////////////////////////////////////////////////
    public void stopMusic() {
        stop();
    }
	////////////////////////////////////////////////////////////
    public void changeMusic(String path) {
        stopMusic();
        playMusic(path);
    }
	////////////////////////////////////////////////////////////
}
