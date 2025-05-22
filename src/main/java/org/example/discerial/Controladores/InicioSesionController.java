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
    public void iniciarSesion() throws Exception {
        IusuariosImpl dao = new IusuariosImpl();
        String correo = SesionCorreo.getText();
        String contrasena = SesionContrasena.getText();


        try {
            Usuarios usuario = dao.login(correo, contrasena);
            if (usuario != null) {
                musicManager.playRandomSoundEffect();

                // Iniciar el timer con las horas acumuladas del usuario activo

                // Cambiar la vista
                switchScene("/org/example/discerial/Tabula_view.fxml");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Credenciales inválidas. Por favor, inténtalo de nuevo.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error en la autenticación: " + e.getMessage());
            alert.showAndWait();
        }
    }


}