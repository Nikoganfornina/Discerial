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

    public void BotoncerrarSesion() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setContentText("Se guardará tu progreso.");

        ButtonType buttonTypeYes = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            // Crear instancia del DAO
            IusuariosImpl usuarioDAO = new IusuariosImpl();

            // Obtener el usuario que tiene la sesión activa
            Usuarios usuarioActivo = usuarioDAO.currentUser();

            if (usuarioActivo != null) {
                // Llamamos al método cerrarSesion pasándole el id del usuario activo
                Usuarios usuarioActualizado = usuarioDAO.cerrarSesion(usuarioActivo.getId());

                if (usuarioActualizado != null && !usuarioActualizado.isSessionActive()) {
                    // Si se ha actualizado correctamente, cambiamos de escena
                    switchScene("/org/example/discerial/MainApp_View.fxml");
                } else {
                    // En caso de error al actualizar, mostramos un error
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("No se pudo cerrar la sesión correctamente.");
                    errorAlert.showAndWait();
                }
            } else {
                // Si no hay un usuario activo, podemos notificarlo o simplemente cambiar de escena
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                infoAlert.setTitle("Información");
                infoAlert.setContentText("No hay ningún usuario conectado.");
                infoAlert.showAndWait();
                switchScene("/org/example/discerial/MainApp_View.fxml");
            }
        }
    }

}




