package org.example.discerial;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.discerial.DAO.ICategoriaImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.Util.SessionTimer;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;
import java.util.List;

import static org.example.discerial.Controladores.Preguntas.PreguntasBiologia.crearPreguntasBiologia;
import static org.example.discerial.Controladores.Preguntas.PreguntasFilosofia.crearPreguntasFilosofia;
import static org.example.discerial.Controladores.Preguntas.PreguntasHistoria.crearPreguntasHistoria;
import static org.example.discerial.Controladores.Preguntas.PreguntasLiteratura.crearPreguntasLiteratura;


public class MainApp extends Application {

    private static Stage primaryStage;
    private final IusuariosImpl usuarioDAO = new IusuariosImpl();
    private MediaPlayer splashPlayer;
    private final MusicManager musicManager = MusicManager.getInstance();

    @Override
    public void start(Stage stage) throws Exception {
        seedCategorias();

        crearPreguntasBiologia();
        crearPreguntasFilosofia();
        crearPreguntasHistoria();
        crearPreguntasLiteratura();
        SessionManager.ajustarAutoIncrementPreguntas();


        primaryStage = stage;
        SessionManager.setMainStage(primaryStage);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Dlogo.png")));

        verificarSesionActiva();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            mostrarConfirmacionCierre();
        });

        mostrarSplashScreen();



    }

    private void mostrarSplashScreen() {
        try {
            Media media = new Media(getClass().getResource("/DiscerialPresentacion.mp4").toExternalForm());
            splashPlayer = new MediaPlayer(media);
            MediaView splashView = new MediaView(splashPlayer);

            StackPane splashPane = new StackPane(splashView);
            splashPane.setPrefSize(1500, 1000);

            Scene splashScene = new Scene(splashPane);
            primaryStage.setScene(splashScene);
            primaryStage.show();

            splashPlayer.setOnEndOfMedia(() -> {
                splashPlayer.stop();
                splashPlayer.dispose();
                cargarEscenaPrincipal();
                musicManager.playAmbientMusic(); // Música después del splash
            });

            splashPlayer.setOnError(() -> {
                System.err.println("Error en splash video");
                cargarEscenaPrincipal();
                musicManager.playAmbientMusic();
            });

            splashPlayer.play();
        } catch (Exception e) {
            System.err.println("Error al cargar splash: " + e.getMessage());
            cargarEscenaPrincipal();
            musicManager.playAmbientMusic();
        }
    }

    private void cargarEscenaPrincipal() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/discerial/MainApp_View.fxml"));
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            mostrarErrorFatal("Error cargando escena principal: " + e.getMessage());
        }
    }

    private void verificarSesionActiva() {
        Usuarios usuarioActivo = usuarioDAO.currentUser();
        if (usuarioActivo != null) {
            cerrarSesion(usuarioActivo.getId());
        }
    }

    private void cerrarSesion(int userId) {
        usuarioDAO.cerrarSesion(userId);
    }

    private void mostrarConfirmacionCierre() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cierre");
        alert.setHeaderText("¿Seguro que quieres cerrar la aplicación?");
        alert.setContentText("Se cerrarán todas las conexiones activas.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    SessionTimer.getInstance().stop();

                    // Cerrar conexiones de Hibernate primero
                    HibernateUtil.shutdown();

                    // Detener música
                    musicManager.stopAll();

                    // Destruir componentes de JavaFX
                    Platform.exit();

                    // Forzar cierre de JVM
                    System.exit(0);

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,
                            "Error crítico al cerrar: " + e.getMessage()).show();
                }
            }
        });
    }

    private void seedCategorias() {
        ICategoriaImpl dao = new ICategoriaImpl();
        List.of("Historia", "Literatura", "Filosofía", "Biología")
                .forEach(nombre -> {
                    if (dao.findByNombre(nombre).isEmpty()) {
                        dao.save(new Categoria(nombre));
                    }
                });
    }

    private void mostrarErrorFatal(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error fatal");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
        Platform.exit();
    }


    public static void main(String[] args) {
        launch(args);
    }
}