// MusicManager.java
package org.example.discerial.Util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.Random;

public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer ambientPlayer;
    private MediaPlayer actionPlayer;
    private MediaPlayer effectPlayer;
    private final Random random = new Random();

    public MusicManager() {}

    public static synchronized MusicManager getInstance() {
        if (instance == null) instance = new MusicManager();
        return instance;
    }

    public  void playRandomSoundEffect() {

        stopEffect();
        try {
            int randomIndex = random.nextInt(3) + 1; // 1, 2 o 3
            String soundFile = "/Sounds/sound" + randomIndex + ".mp3";

            URL resource = getClass().getResource(soundFile);
            if (resource == null) throw new Exception("Sonido no encontrado: " + soundFile);

            Media media = new Media(resource.toString());
            effectPlayer = new MediaPlayer(media);
            effectPlayer.play();
        } catch (Exception e) {
            System.err.println("Error efecto sonido: " + e.getMessage());
        }
    }

    public void stopAll() {
        if (ambientPlayer != null) {
            ambientPlayer.stop();
            ambientPlayer.dispose();
            ambientPlayer = null;
        }
        if (actionPlayer != null) {
            actionPlayer.stop();
            actionPlayer.dispose();
            actionPlayer = null;
        }
        stopEffect();
    }



    public void playAmbientMusic() {
        stopAll();
        int songNumber = random.nextInt(10) + 1;
        playMusic("/Songs/TestSong" + songNumber + ".mp3", true, player -> {
            ambientPlayer = player;
            player.setCycleCount(MediaPlayer.INDEFINITE);
        });
    }

    public void playActionMusic() {
        stopAll();
        int songNumber = random.nextInt(8) + 1;
        playMusic("/Songs/GameSongs/gameSong" + songNumber + ".mp3", true, player -> {
            actionPlayer = player;
            player.setCycleCount(MediaPlayer.INDEFINITE);
        });
    }



    private void playMusic(String path, boolean loop, java.util.function.Consumer<MediaPlayer> config) {
        try {
            URL resource = getClass().getResource(path);
            if (resource == null) throw new Exception("Recurso no encontrado: " + path);

            Media media = new Media(resource.toString());
            MediaPlayer player = new MediaPlayer(media);
            if (loop) player.setCycleCount(MediaPlayer.INDEFINITE);
            if (config != null) config.accept(player);
            player.play();
        } catch (Exception e) {
            System.err.println("Error música: " + e.getMessage());
        }
    }



    public void stopEffect() {
        if (effectPlayer != null) { effectPlayer.stop(); effectPlayer.dispose(); }
    }
}