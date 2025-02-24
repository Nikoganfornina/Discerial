package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    public void cerrarSesion() {
        IusuariosImpl dao = new IusuariosImpl();

        Usuarios usuario = SessionManager.getCurrentUser(); // Guardamos el usuario antes de cerrar sesión

        if (usuario != null) {
            usuario.setSessionActive(false); // Desactivamos la sesión
            dao.update(usuario); // Guardamos en la base de datos

            SessionManager.cerrarSesion(); // Ahora sí, cerramos la sesión
        }

        try {
            switchScene("/org/example/discerial/InicioSesion_View.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
