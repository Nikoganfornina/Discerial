package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static org.example.discerial.MainApp.switchScene;

public class InicioSesionController {

    @FXML
    private TextField SesionCorreo;
    @FXML
    private TextField SesionContrasena;




    public void handleClick(MouseEvent event) throws Exception {
        switchScene("/org/example/discerial/Registro_View.fxml");
    }
    public void initialize() {
        SesionCorreo.setFocusTraversable(false);
        SesionContrasena.setFocusTraversable(false);
    }
}
