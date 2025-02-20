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
import org.example.discerial.entities.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.discerial.MainApp.switchScene;

public class RegistroController {

    @FXML
    public static TextField RegistroNombre;

    @FXML
    public static TextField RegistroNickname;

    @FXML
    public static TextField RegistroCorreo;

    @FXML
    public static TextField RegistroContrasena;

    @FXML
    public static TextField RegistroConfirmarContrasena;

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

    public static void AgregarUsuario(String Nombre, String Nickname, String Correo, String Contrasena) throws Exception {
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

        Usuarios usuario = new Usuarios(Nombre, Nickname, Correo, Contrasena, 0, 0, null);

        dao.save(usuario);

    }

}

