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

            // Configuración crítica
            mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(1500); // Cubrir toda la pantalla
            mediaView.setFitHeight(1000);
            mediaView.setLayoutX(0);
            mediaView.setLayoutY(0);
            mediaView.setVisible(false);
            mediaView.setStyle("-fx-border-color: red; -fx-border-width: 5px;"); // Debug visual

            // Añadir al contenedor raíz
            rootContainer.getChildren().add(mediaView);

            // Manejo de errores
            mediaPlayer.setOnError(() -> {
                System.err.println("ERROR en MediaPlayer: " + mediaPlayer.getError().getMessage());
            });

        } catch (Exception e) {
            System.err.println("ERROR en inicializar(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void showLoading() {
        System.out.println("[DEBUG] Mostrando loading...");
        Platform.runLater(() -> {
            mediaView.setVisible(true);
            mediaView.toFront(); // Asegurar capa superior
            mediaPlayer.play();
        });
    }

    public static void hideLoading() {
        System.out.println("[DEBUG] Ocultando loading...");
        Platform.runLater(() -> {
            mediaView.setVisible(false);
            mediaPlayer.stop();
        });
    }
}