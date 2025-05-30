package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.Util.SessionTimer;
import org.example.discerial.entities.Usuarios;


import static org.example.discerial.Util.SessionManager.switchScene;


public class RegistroController {

    MusicManager musicManager = MusicManager.getInstance();
    @FXML
    public  TextField RegistroNombre , RegistroNickname , RegistroCorreo , RegistroContrasena , RegistroConfirmarContrasena;

    public void handleClick(MouseEvent event) throws Exception {
        musicManager.playRandomSoundEffect();

        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void initialize() {
        RegistroNombre.setFocusTraversable(false);
        RegistroCorreo.setFocusTraversable(false);
        RegistroContrasena.setFocusTraversable(false);
        RegistroNickname.setFocusTraversable(false);
        RegistroConfirmarContrasena.setFocusTraversable(false);
    }
    private void limpiarCampos() {
        RegistroNombre.clear();
        RegistroNickname.clear();
        RegistroCorreo.clear();
        RegistroContrasena.clear();
        RegistroConfirmarContrasena.clear();
    }

    public  void AgregarUsuario() throws Exception {

        IusuariosImpl dao = new IusuariosImpl();

        String nombre = RegistroNombre.getText();
        String nickname = RegistroNickname.getText();
        String correo = RegistroCorreo.getText();
        String contrasena = RegistroContrasena.getText();
        String confirmarContrasena = RegistroConfirmarContrasena.getText();

        if(!contrasena.equals(confirmarContrasena)){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Las contrasenas no coinciden");
            alert.showAndWait();
            return;
        }

        Usuarios usuario = new Usuarios(nombre, nickname, correo, contrasena, 0, 0, "hombre2.jpg" ,false , 0); ;


        try {
            // Guarda el usuario en la BD (esto generará su ID automáticamente)
            Usuarios usuarioGuardado = dao.save(usuario);

            if (usuarioGuardado != null) {
                // Activa la sesión en la base de datos
                dao.activateUser(usuarioGuardado.getId());

                // Actualiza el objeto local para reflejar el estado activo
                usuarioGuardado.setSessionActive(true);

                musicManager.playRandomSoundEffect();

                SessionTimer.getInstance().start(); // Iniciar el timer

                // Cambia de pantalla
                switchScene("/org/example/discerial/Tabula_view.fxml");
            }
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }

    }


}

