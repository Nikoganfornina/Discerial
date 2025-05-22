package org.example.discerial.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Usuarios;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SessionManager {

    private static Stage mainStage;

    public static void switchScene(String fxmlPath) throws IOException {
        if (mainStage == null) {
            throw new IllegalStateException("Error: mainStage no configurado.");
        }

        // Cargar y mostrar la nueva escena
        Parent root = FXMLLoader.load(SessionManager.class.getResource(fxmlPath));
        mainStage.setScene(new Scene(root));
    }

    public static void setMainStage(Stage primaryStage) {
        mainStage = primaryStage;
    }


}