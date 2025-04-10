package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.discerial.Util.SessionManager.switchScene;

public class VistaPreguntaController {

    @FXML
    private Label lblCategoria;

    @FXML
    private Label lblPregunta;

    @FXML
    private ImageView imgPregunta;

    @FXML
    private Label lblRespuestaCorrecta;

    @FXML
    private Button btnVolver;

    @FXML
    private void handleVolver() throws IOException {
        switchScene("/org/example/discerial/CrudPreguntas.fxml");

    }


    // Métodos adicionales para manejar "Anterior" y "Siguiente" si es necesario
    @FXML
    private void handleAnterior() {
        // Lógica para la navegación a la pregunta anterior
    }

    @FXML
    private void handleSiguiente() {
        // Lógica para la navegación a la siguiente pregunta
    }
}
