package org.example.discerial.Util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Random;
import java.util.function.Consumer;

public class MusicManager {


    private static MusicManager instance;

    private MediaPlayer ambientPlayer;
    private MediaPlayer actionPlayer;
    private MediaPlayer effectPlayer;

    private final Random random = new Random();

    private double volume = 1.0;
    private boolean muted = false;

    private int usuarioId;

    // Ajustes cacheados para evitar múltiples consultas en un solo método
    private boolean musicaActivada = true;
    private boolean efectosActivados = true;
    private double nivelMusica = 1.0;



    public MusicManager() {

    }

    public static synchronized MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }


    public void setUsuarioId(int id) {
        this.usuarioId = id;
    }



    // ───────────────────────
    // MUSIC CONTROL
    // ───────────────────────

    public void playAmbientMusic() {
        if (!musicaActivada) return;
        stopMusic(); // Solo para ambient y action
        int songNumber = random.nextInt(12) + 1;
        playMusic("/Songs/TestSong" + songNumber + ".mp3", true, player -> ambientPlayer = player);
    }

    public void playActionMusic() {
        if (!musicaActivada) return;
        stopMusic();
        int songNumber = random.nextInt(8) + 1;
        playMusic("/Songs/GameSongs/gameSong" + songNumber + ".mp3", true, player -> {
            actionPlayer = player;
            player.setVolume(0.7); // 70% de volumen, o podrías usar volume * 0.7
        });
    }

    public void playRandomSoundEffect() {
        if (!efectosActivados) return;
        stopEffect();
        try {
            int randomIndex = random.nextInt(3) + 1;
            String soundFile = "/Sounds/sound" + randomIndex + ".mp3";
            playMusic(soundFile, false, player -> effectPlayer = player);
        } catch (Exception e) {
            System.err.println("Error al reproducir efecto de sonido aleatorio: " + e.getMessage());
        }
    }

    public void playRandomSoundWin() {
        if (!efectosActivados) return;
        stopEffect();

        String soundFile = "/Sounds/win.mp3";
        playMusic(soundFile, false, player -> effectPlayer = player);
    }
    public void playRandomSoundfail() {
        if (!efectosActivados) return;
        stopEffect();

        String soundFile = "/Sounds/fail.mp3";
        playMusic(soundFile, false, player -> effectPlayer = player);
    }

    public void stopAll() {
        stopMusic();
        stopEffect();
    }

    public void stopMusic() {
        disposePlayer(ambientPlayer);
        ambientPlayer = null;

        disposePlayer(actionPlayer);
        actionPlayer = null;
    }

    public void stopEffect() {
        disposePlayer(effectPlayer);
        effectPlayer = null;
    }

    // ───────────────────────
    // CORE REPRODUCCIÓN
    // ───────────────────────

    private void playMusic(String path, boolean loop, Consumer<MediaPlayer> config) {
        try {
            URL resource = getClass().getResource(path);
            if (resource == null) throw new Exception("Recurso no encontrado: " + path);

            Media media = new Media(resource.toString());
            MediaPlayer player = new MediaPlayer(media);

            player.setVolume(muted ? 0.0 : volume);
            if (loop) player.setCycleCount(MediaPlayer.INDEFINITE);

            if (config != null) config.accept(player);

            player.setOnError(() -> System.err.println("Error de reproducción: " + player.getError()));
            player.play();
        } catch (Exception e) {
            System.err.println("Error al reproducir música: " + e.getMessage());
        }
    }

    private void disposePlayer(MediaPlayer player) {
        if (player != null) {
            player.stop();
            player.dispose();
        }
    }

    // ───────────────────────
    // CONTROL DE VOLUMEN / MUTE
    // ───────────────────────

    public void setVolume(double newVolume) {
        volume = Math.max(0.0, Math.min(1.0, newVolume)); // Clamp 0.0–1.0
        applyVolume();
    }

    public void muteAll() {
        muted = true;
        applyVolume();
    }

    public void unmuteAll() {
        muted = false;
        applyVolume();
    }

    private void applyVolume() {
        double vol = muted ? 0.0 : volume;

        if (ambientPlayer != null) ambientPlayer.setVolume(vol);
        if (actionPlayer != null) actionPlayer.setVolume(vol);
        if (effectPlayer != null) effectPlayer.setVolume(vol);
    }
}