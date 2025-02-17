package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static org.example.discerial.MainApp.switchScene;

public class RegistroController {

    @FXML
    private TextField RegistroNombre;

    @FXML
    private TextField RegistroCorreo;

    @FXML
    private  TextField  RegistroContrasena ;

    @FXML
    private TextField RegistroFecha;

    public void handleClick(MouseEvent event) throws Exception {
        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void initialize() {
        RegistroNombre.setFocusTraversable(false);
        RegistroCorreo.setFocusTraversable(false);
        RegistroContrasena.setFocusTraversable(false);
        RegistroFecha.setFocusTraversable(false);
    }
}
