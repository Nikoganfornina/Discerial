package org.example.discerial.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.discerial.entities.Usuarios;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

public class SessionManager {

    private static Usuarios currentUser;
    private static Stage mainStage;
    private static LocalDateTime inicioSesion; // Guardamos la hora de inicio

    public static void setCurrentUser(Usuarios user) {
        currentUser = user;
        if (user != null) {
            iniciarSesion(); // Registrar inicio de sesión
        }
    }

    public static Usuarios getCurrentUser() {
        return currentUser;
    }

    public static void cerrarSesion() {
        if (currentUser != null) {
            calcularTiempoJugado();
        }
        currentUser = null;
    }

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void switchScene(String fxmlPath) throws IOException {
        if (mainStage == null) {
            throw new IllegalStateException("El mainStage no ha sido inicializado. Llama a SessionManager.setMainStage(stage) en el método start.");
        }
        FXMLLoader loader = new FXMLLoader(SessionManager.class.getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    // Inicia sesión y guarda la hora actual
    private static void iniciarSesion() {
        inicioSesion = LocalDateTime.now();
        currentUser.setSessionActive(true);
    }

    // Calcula el tiempo jugado y lo suma al usuario
    private static void calcularTiempoJugado() {
        if (inicioSesion != null && currentUser != null) {
            long segundosJugados = Duration.between(inicioSesion, LocalDateTime.now()).getSeconds();
            currentUser.setHorasJugadas(currentUser.getHorasJugadas() + segundosJugados);
            currentUser.setSessionActive(false);
            inicioSesion = null;
        }
    }
}