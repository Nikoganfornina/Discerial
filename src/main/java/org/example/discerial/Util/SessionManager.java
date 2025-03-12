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
    private static Usuarios currentUser;
    private static Stage mainStage;
    private static LocalDateTime inicioSesion;
    private static ScheduledExecutorService timer; // Hilo para el contador
    private static long tiempoAcumulado = 0; // Tiempo acumulado en segundos


    public static void setCurrentUser(Usuarios user) {
        currentUser = user;
        if (user != null) {
            inicioSesion = LocalDateTime.now(); // Iniciar contador
        }
    }

    public static Usuarios getCurrentUser() {
        return currentUser;
    }

    public static void setMainStage(Stage stage) {
        mainStage = stage; // Configurar al inicio
    }

    public static void switchScene(String fxmlPath) throws IOException {
        if (mainStage == null) {
            throw new IllegalStateException("Error: mainStage no configurado.");
        }

        // Cargar y mostrar la nueva escena
        Parent root = FXMLLoader.load(SessionManager.class.getResource(fxmlPath));
        mainStage.setScene(new Scene(root));
    }


    // Iniciar sesión y contador
    private static void iniciarSesion() {
        inicioSesion = LocalDateTime.now();
        currentUser.setSessionActive(true);
        iniciarContador();
    }

    // Iniciar el contador en segundo plano
    private static void iniciarContador() {
        System.out.println("Metodo comentado, arreglar contador de tiempo");
        /*
        timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(() -> {
            long segundosTranscurridos = Duration.between(inicioSesion, LocalDateTime.now()).getSeconds();
            tiempoAcumulado = segundosTranscurridos + currentUser.getHorasJugadas();

            // Actualizar consola
            System.out.printf("Tiempo jugado: %02d:%02d%n",
                    tiempoAcumulado / 3600, (tiempoAcumulado % 3600) / 60);

        }, 0, 1, TimeUnit.SECONDS); // Actualizar cada segundo

         */
    }

    // Cerrar sesión y guardar tiempo
    public static void cerrarSesion() {
        if (timer != null && !timer.isShutdown()) {
            timer.shutdown(); // Detener el contador
        }
        if (currentUser != null) {
            currentUser.setHorasJugadas(tiempoAcumulado);
            currentUser.setSessionActive(false);
            // Guardar en BD
            IusuariosImpl dao = new IusuariosImpl();
            dao.update(currentUser);
        }
        currentUser = null;
    }

    public static String getTiempoJugadoFormateado() {

        if (currentUser == null) return "00:00";

        long segundosTotales = currentUser.getHorasJugadas() +
                Duration.between(inicioSesion, LocalDateTime.now()).getSeconds();

        return String.format("%02d:%02d",
                segundosTotales / 3600,
                (segundosTotales % 3600) / 60
        );
    }
}