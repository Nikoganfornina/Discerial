package org.example.discerial.Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.discerial.MainApp.switchScene;

public class RegistroController {

    @FXML private TextField RegistroNombre;

    @FXML public TextField RegistroNickname;

    @FXML private TextField RegistroCorreo;

    @FXML private  TextField  RegistroContrasena ;

    @FXML public TextField RegistroConfirmarContrasena;

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


}

