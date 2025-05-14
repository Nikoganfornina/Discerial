package org.example.discerial;

import javafx.application.Application;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import org.example.discerial.Controladores.Preguntas.PreguntasFilosofia;
import org.example.discerial.DAO.ICategoriaImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Usuarios;
import org.example.discerial.Util.SessionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainApp extends Application {

    private static Stage primaryStage;
    private MediaPlayer backgroundMusicPlayer;
    private List<Media> canciones = new ArrayList<>();
    private IusuariosImpl usuarioDAO = new IusuariosImpl();  // Asegúrate de tener el DAO bien configurado

    @Override
    public void start(Stage stage) throws Exception {
        seedCategorias();

        // 2) A continuación si quieres sembrar preguntas de filosofía:
        PreguntasFilosofia.crearPreguntasFilosofia();

        primaryStage = stage;
        SessionManager.setMainStage(primaryStage);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Dlogo.png")));

        // Verificar si hay sesión activa al iniciar la aplicación
        verificarSesionActiva();

        // Registrar el evento de cierre de la ventana
        primaryStage.setOnCloseRequest(event -> {
            event.consume();  // Evita que la ventana se cierre inmediatamente
            mostrarConfirmacionCierre();  // Mostrar la confirmación antes de cerrar
        });

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

    private void verificarSesionActiva() {
        // Verifica si hay sesión activa
        Usuarios usuarioActivo = usuarioDAO.currentUser();
        if (usuarioActivo != null) {
            System.out.println("Sesión activa encontrada");
            // Cerrar sesión automáticamente al iniciar la aplicación si ya hay sesión activa
            cerrarSesion(usuarioActivo.getId());
        } else {
            System.out.println("No hay sesión activa.");
        }
    }

    private void cerrarSesion(int userId) {
        // Llamar al DAO para cerrar sesión
        Usuarios usuario = usuarioDAO.cerrarSesion(userId);
        if (usuario != null) {
            System.out.println("Sesión cerrada correctamente.");
        } else {
            System.out.println("Hubo un problema al cerrar la sesión.");
        }
    }

    private void mostrarConfirmacionCierre() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cierre");
        alert.setHeaderText("¿Seguro que quieres cerrar la aplicación?");
        alert.setContentText("Si cierras la aplicación, la sesión será cerrada automáticamente.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Obtener el usuario actual y cerrar sesión
                Usuarios usuarioActivo = usuarioDAO.currentUser();
                if (usuarioActivo != null) {
                    cerrarSesion(usuarioActivo.getId());
                }
                primaryStage.close();  // Cerrar la ventana después de la confirmación
            }
        });
    }

    private void seedCategorias() {
        ICategoriaImpl dao = new ICategoriaImpl();
        List<String> nombres = List.of(
                "Historia", "Geografía", "Literatura", "Filosofía", "Biología"
        );

        for (String nombre : nombres) {
            if (dao.findByNombre(nombre).isEmpty()) {
                dao.save(new Categoria(nombre));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
