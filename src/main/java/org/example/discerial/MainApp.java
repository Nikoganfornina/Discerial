package org.example.discerial;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.discerial.Util.LoadingManager;
import org.example.discerial.Util.SessionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MainApp extends Application {
    private static Stage primaryStage;
    private MediaPlayer backgroundMusicPlayer;
    private List<Media> canciones = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        SessionManager.setMainStage(primaryStage);

        // 1. Mostrar splash screen
        mostrarSplashScreen();
    }

    private void mostrarSplashScreen() {
        Media media = new Media(getClass().getResource("/DiscerialPresentacion.mp4").toExternalForm());
        MediaPlayer splashPlayer = new MediaPlayer(media);
        MediaView splashView = new MediaView(splashPlayer);

        StackPane splashPane = new StackPane(splashView);
        splashPane.setPrefSize(1500, 1000);

        Scene splashScene = new Scene(splashPane);
        primaryStage.setScene(splashScene);
        primaryStage.show();

        splashPlayer.setOnEndOfMedia(() -> {
            cargarEscenaPrincipal();
            inicializarMusica();
        });
        splashPlayer.play();
    }

    private void cargarEscenaPrincipal() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/discerial/MainApp_View.fxml"));
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);

            LoadingManager.inicializar(primaryStage); // Inicializar después de cargar la escena

        } catch (IOException e) {
            System.err.println("Error cargando escena principal: " + e.getMessage());
        }
    }

    private void inicializarMusica() {
        cargarCanciones();
        reproducirCancionAleatoria();
    }

    private void cargarCanciones() {
        try {
            for (int i = 1; i <= 10; i++) {
                String path = getClass().getResource("/songs/TestSong" + i + ".mp3").toExternalForm();
                canciones.add(new Media(path));
            }
            Collections.shuffle(canciones);
        } catch (Exception e) {
            System.err.println("Error cargando canciones: " + e.getMessage());
        }
    }

    private void reproducirCancionAleatoria() {
        if (!canciones.isEmpty()) {
            backgroundMusicPlayer = new MediaPlayer(canciones.get(0));
            backgroundMusicPlayer.setVolume(0.3);
            backgroundMusicPlayer.play();

            backgroundMusicPlayer.setOnEndOfMedia(() -> {
                Collections.shuffle(canciones);
                reproducirCancionAleatoria();
            });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}