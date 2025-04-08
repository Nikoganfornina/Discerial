package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.example.discerial.DAO.IusuariosImpl;
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
        MostrarNombreUsuario();
    }

    private void MostrarNombreUsuario() {
        IusuariosImpl dao = new IusuariosImpl();
        Usuarios user = dao.currentUser();
        if (user != null && user.getNombre() != null && !user.getNombre().isEmpty()) {
            String nombre = user.getNombre();
            String nombreFormateado = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
            usuarioNombre.setText(nombreFormateado);
        } else {
            usuarioNombre.setText("Sin usuario activo");
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

    public void BotoncerrarSesion() throws IOException {
        // Crear instancia del DAO
        IusuariosImpl usuarioDAO = new IusuariosImpl();
        Usuarios usuarioActivo = usuarioDAO.currentUser();

        if (usuarioActivo != null) {
            String nombreUsuario = usuarioActivo.getNombre();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cerrar sesión");
            alert.setHeaderText(null);
            alert.setContentText("¿Deseas cerrar sesión de la cuenta: " + nombreUsuario + "?");

            ButtonType buttonTypeYes = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeYes) {
                Usuarios usuarioActualizado = usuarioDAO.cerrarSesion(usuarioActivo.getId());

                if (usuarioActualizado != null && !usuarioActualizado.isSessionActive()) {
                    switchScene("/org/example/discerial/MainApp_View.fxml");
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("No se pudo cerrar la sesión correctamente.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Información");
            infoAlert.setContentText("No hay ningún usuario conectado.");
            infoAlert.showAndWait();
            switchScene("/org/example/discerial/MainApp_View.fxml");
        }
    }

}






