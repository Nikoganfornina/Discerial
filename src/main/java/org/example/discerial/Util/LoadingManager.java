package org.example.discerial.Util;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;


public class LoadingManager {
    private static MediaPlayer mediaPlayer;
    private static MediaView mediaView;
    private static AnchorPane rootContainer;

    public static void inicializar(Stage stage) {
        try {
            rootContainer = (AnchorPane) stage.getScene().getRoot();
            String videoPath = LoadingManager.class.getResource("/Images/Animacion.mp4").toExternalForm();

            // Verificar existencia del recurso
            if (videoPath == null) {
                System.err.println("ERROR: Video no encontrado en resources/Images/");
                return;
            }

            Media media = new Media(videoPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // Configuración crítica del MediaView
            mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(300);
            mediaView.setFitHeight(300);

            // Posicionamiento absoluto centrado (para AnchorPane)
            AnchorPane.setTopAnchor(mediaView, 350.0);
            AnchorPane.setLeftAnchor(mediaView, 600.0);

            // Asegurar visibilidad y capa superior
            mediaView.setVisible(false);
            mediaView.toFront();

            rootContainer.getChildren().add(mediaView);

            // Manejo de errores del reproductor
            mediaPlayer.setOnError(() -> {
                System.err.println("Error en MediaPlayer: " + mediaPlayer.getError().getMessage());
            });

        } catch (Exception e) {
            System.err.println("ERROR en inicializar(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void showLoading() {
        System.out.println("Mostrando loading...");
        Platform.runLater(() -> {
            mediaView.setVisible(true);
            mediaView.toFront(); // Asegurar que está encima de todo
            mediaPlayer.play();
        });
    }

    public static void hideLoading() {
        System.out.println("Ocultando loading...");
        Platform.runLater(() -> {
            mediaView.setVisible(false);
            mediaPlayer.stop();
        });
    }
}
