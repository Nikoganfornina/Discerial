package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.Util.SessionTimer;
import org.example.discerial.entities.Usuarios;


import java.io.IOException;

import static org.example.discerial.Util.SessionManager.switchScene;

public class InicioSesionController {

    @FXML
    private TextField SesionCorreo;

    MusicManager musicManager = MusicManager.getInstance();

    @FXML
    private TextField SesionContrasena;

    @FXML
    public void initialize() {
        SesionCorreo.setFocusTraversable(false);
        SesionContrasena.setFocusTraversable(false);
    }

    @FXML
    public void Registro() throws IOException {
        musicManager.playRandomSoundEffect();

        switchScene("/org/example/discerial/Registro_View.fxml");
    }

    // Método testable sin JavaFX
    public boolean autenticarUsuario(String correo, String contrasena, IusuariosImpl dao) throws Exception {
        Usuarios usuario = dao.login(correo, contrasena);
        return usuario != null;
    }



    @FXML
    public void iniciarSesion() {
        IusuariosImpl dao = new IusuariosImpl();
        String identificador = SesionCorreo.getText().trim(); // Campo ahora contiene nick o correo
        String contrasena = SesionContrasena.getText().trim();

        try {
            Usuarios usuario = dao.login(identificador, contrasena);
            if (usuario != null) {
                // Lógica exitosa
                SessionTimer.getInstance().start();
                switchScene("/org/example/discerial/Tabula_view.fxml");
            } else {
                showAlert("Error", "Credenciales incorrectas");
            }
        } catch (Exception e) {
            showAlert("Error", "Error: " + e.getMessage());
        }
    }

    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}