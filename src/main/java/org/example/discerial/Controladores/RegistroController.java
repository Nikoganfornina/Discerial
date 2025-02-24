package org.example.discerial.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.discerial.DAO.Iusuarios;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.discerial.Util.SessionManager.switchScene;


public class RegistroController {

    @FXML
    public  TextField RegistroNombre , RegistroNickname , RegistroCorreo , RegistroContrasena , RegistroConfirmarContrasena;

    public void handleClick(MouseEvent event) throws Exception {
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

                // Guarda el usuario activo en memoria (para usar en otras vistas)
                SessionManager.setCurrentUser(usuarioGuardado);

                // Cambia de pantalla
                switchScene("/org/example/discerial/Tabula_view.fxml");
            }
        } catch (Exception e) {
            new Alert(AlertType.ERROR, "Error: " + e.getMessage()).showAndWait();
        }

    }


}

