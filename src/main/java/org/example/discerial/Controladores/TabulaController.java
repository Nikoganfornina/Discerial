package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;
import java.util.Optional;

import static org.example.discerial.Util.SessionManager.switchScene;


public class TabulaController {

    @FXML
    private Pane contenedorFXML;

    @FXML
    private ImageView usuarioImagen;

    @FXML
    private Label usuarioNombre;

    @FXML
    public void initialize() {
        // Si el usuario está activo, mostrar sus datos
        Usuarios currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            // Muestra el nombre del usuario
            usuarioNombre.setText(currentUser.getNombre());

            // Cargar la imagen del usuario.
            String imagePath = "/Images/IconosPerfil/" + currentUser.getImagen();
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                usuarioImagen.setImage(image);

                // Aplicar borde redondeado
                Circle clip = new Circle(usuarioImagen.getFitWidth() / 2, usuarioImagen.getFitHeight() / 2, usuarioImagen.getFitWidth() / 2);
                usuarioImagen.setClip(clip);

                // Agregar borde blanco o del color que quieras
                usuarioImagen.setStyle("-fx-border-color: white; -fx-border-width: 3px; -fx-border-radius: 50%;");
            } catch (Exception e) {
                System.err.println("No se pudo cargar la imagen: " + imagePath);
            }
        }
    }


    @FXML
    private void FxmlHomoPanel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/Panels/HomoPanel_view.fxml"));
            Parent nuevoContenido = loader.load();

            // Limpiar el contenedor antes de añadir el nuevo contenido
            contenedorFXML.getChildren().clear();
            contenedorFXML.getChildren().add(nuevoContenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BotoncerrarSesion() {
        Usuarios usuario = SessionManager.getCurrentUser();

        if (usuario != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrar Sesión");
            alert.setHeaderText("¿Salir de la sesión de " + usuario.getNombre() + "?");
            alert.setContentText("Se guardará tu progreso.");

            ButtonType buttonTypeYes = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {
                if (result.get() == buttonTypeYes) {
                    SessionManager.cerrarSesion(); // Cierra la sesión

                } else {
                    // No hacer nada
                    System.out.println("Acción cancelada.");
                }
            }
        } else {
            // No hacer nada
        }
    }

}
