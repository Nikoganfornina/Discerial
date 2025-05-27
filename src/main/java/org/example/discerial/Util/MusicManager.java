package org.example.discerial.Util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.example.discerial.DAO.IAjustesUsuario;
import org.example.discerial.entities.AjustesUsuario;

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

    private IAjustesUsuario ajustesUsuarioDAO;
    private int usuarioId;

    // Ajustes cacheados para evitar múltiples consultas en un solo método
    private boolean musicaActivada = true;
    private boolean efectosActivados = true;
    private double nivelMusica = 1.0;

    public MusicManager(IAjustesUsuario ajustesUsuarioDAO, int usuarioId) {
        this.ajustesUsuarioDAO = ajustesUsuarioDAO;
        this.usuarioId = usuarioId;
    }

    public MusicManager() {

    }

    public static synchronized MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    public void setAjustesUsuarioDAO(IAjustesUsuario dao) {
        this.ajustesUsuarioDAO = dao;
    }

    public void setUsuarioId(int id) {
        this.usuarioId = id;
    }

    private void cargarAjustes() {
        try {
            AjustesUsuario ajustes = ajustesUsuarioDAO.getAjustesByUsuarioId(usuarioId);
            this.musicaActivada = ajustes.isMusicaActivada();
            this.efectosActivados = ajustes.isEfectosActivados();
            // Nivel música como volumen escalado 0.0-1.0
            this.nivelMusica = ajustes.getNivelMusica() / 100.0; // Asumiendo que nivel_musica es % 0-100
            this.volume = nivelMusica;
            this.muted = !musicaActivada;  // si la música no está activada, mutear
            applyVolume();
        } catch (Exception e) {
            System.err.println("Error cargando ajustes de música: " + e.getMessage());
            // En caso de error, dejar configuración por defecto:
            this.musicaActivada = true;
            this.efectosActivados = true;
            this.volume = 1.0;
            this.muted = false;
            applyVolume();
        }
    }

    // ───────────────────────
    // MUSIC CONTROL
    // ───────────────────────

    public void playAmbientMusic() {
        cargarAjustes();
        if (!musicaActivada) return;
        stopMusic(); // Solo para ambient y action
        int songNumber = random.nextInt(12) + 1;
        playMusic("/Songs/TestSong" + songNumber + ".mp3", true, player -> ambientPlayer = player);
    }

    public void playActionMusic() {
        cargarAjustes();
        if (!musicaActivada) return;
        stopMusic();
        int songNumber = random.nextInt(8) + 1;
        playMusic("/Songs/GameSongs/gameSong" + songNumber + ".mp3", true, player -> {
            actionPlayer = player;
            player.setVolume(0.7); // 70% de volumen, o podrías usar volume * 0.7
        });
    }

    public void playRandomSoundEffect() {
        cargarAjustes();
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
        cargarAjustes();
        if (!efectosActivados) return;
        stopEffect();

        String soundFile = "/Sounds/win.mp3";
        playMusic(soundFile, false, player -> effectPlayer = player);
    }
    public void playRandomSoundfail() {
        cargarAjustes();
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
