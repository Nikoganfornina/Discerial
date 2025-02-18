package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static org.example.discerial.MainApp.switchScene;

public class InicioSesionController {

    @FXML
    private TextField SesionCorreo;
    @FXML
    private TextField SesionContrasena;
    @FXML
    private Button BotonIngresar;

    @FXML
    public void initialize() {
        SesionCorreo.setFocusTraversable(false);
        SesionContrasena.setFocusTraversable(false);
        BotonIngresar.setDisable(true);

        SesionCorreo.textProperty().addListener((observable, oldValue, newValue) -> validarCampos());
        SesionContrasena.textProperty().addListener((observable, oldValue, newValue) -> validarCampos());
    }

    private void validarCampos() {
        boolean camposLlenos = !SesionCorreo.getText().trim().isEmpty() && !SesionContrasena.getText().trim().isEmpty();
        BotonIngresar.setDisable(!camposLlenos);
    }

    @FXML
    public void handleClickIngresar(MouseEvent event) throws Exception {
        switchScene("/org/example/discerial/Tabula_view.fxml");
    }

    @FXML
    public void handleClick(MouseEvent event) throws Exception {
        switchScene("/org/example/discerial/Registro_View.fxml");
    }
}
