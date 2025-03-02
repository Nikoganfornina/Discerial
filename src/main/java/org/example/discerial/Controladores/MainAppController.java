package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import static org.example.discerial.Util.SessionManager.switchScene;

public class MainAppController {

    @FXML
    private AnchorPane root;

    public void switchToInicioSesion() throws Exception {
        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void irCrudPreguntas() throws Exception {
        switchScene("/org/example/discerial/CrudPreguntas.fxml");
    }

    @FXML
    public void initialize() {
        //Configurar Control + u para usar el ircrudPreguntas
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.U && event.isControlDown()) {
                try {
                    irCrudPreguntas();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}