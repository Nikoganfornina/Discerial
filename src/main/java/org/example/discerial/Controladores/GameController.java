package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Usuarios;

import java.util.List;

public class GameController {

    @FXML private Label lblCategoria;
    @FXML private Label lblPregunta;
    @FXML private ImageView imgPregunta;
    @FXML private Label lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4;
    @FXML private Button btnAnterior, btnSiguiente, btnVolver;
    @FXML private Label lblTimer;

    private List<Pregunta> preguntas;
    private int currentIndex = 0;
    private Timeline timer;
    private int timeRemaining = 20;

    private IusuariosImpl usuarioDao = new IusuariosImpl();
    private Usuarios usuarioActual;

    @FXML
    public void initialize() {
        // Carga usuario activo
        usuarioActual = usuarioDao.currentUser();

        // Carga preguntas
        IPregunta dao = new IPreguntaImpl();
        preguntas = dao.findAll();

        setupTimer();
        showQuestion(0);
    }

    private void setupTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            lblTimer.setText("Tiempo: " + timeRemaining + "s");
            if (timeRemaining <= 0) {
                timer.stop();
                // tiempo expirado → cuenta como errónea
                usuarioDao.incrementErroneas(usuarioActual.getId());
                highlightCorrect();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    private void resetTimer() {
        timer.stop();
        timeRemaining = 20;
        lblTimer.setText("Tiempo: " + timeRemaining + "s");
        timer.playFromStart();
    }

    private void showQuestion(int index) {
        if (index < 0 || index >= preguntas.size()) return;
        currentIndex = index;
        Pregunta p = preguntas.get(index);
        lblCategoria.setText(p.getCategoria().getNombre());
        lblPregunta.setText(p.getPregunta());
        if (p.getImagen() != null && !p.getImagen().isEmpty()) {
            imgPregunta.setImage(new Image(p.getImagen()));
        } else {
            imgPregunta.setImage(null);
        }
        lblOpcion1.setText(p.getRespuestaCorrecta());
        lblOpcion2.setText(p.getRespuesta2());
        lblOpcion3.setText(p.getRespuesta3());
        lblOpcion4.setText(p.getRespuesta4());
        clearStyles();
        resetTimer();
    }

    private void clearStyles() {
        lblOpcion1.setStyle("");
        lblOpcion2.setStyle("");
        lblOpcion3.setStyle("");
        lblOpcion4.setStyle("");
    }

    private void highlightCorrect() {
        String correcta = preguntas.get(currentIndex).getRespuestaCorrecta();
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            if (lbl.getText().equals(correcta)) {
                lbl.setStyle("-fx-border-color: green; -fx-border-width: 3;");
            }
        }
    }

    @FXML
    private void handleAnterior(javafx.event.ActionEvent e) {
        timer.stop();
        showQuestion(currentIndex - 1);
    }

    @FXML
    private void handleSiguiente(javafx.event.ActionEvent e) {
        timer.stop();
        showQuestion(currentIndex + 1);
    }

    @FXML
    private void handleOpcion(MouseEvent e) {
        timer.stop();
        Label clicked = (Label)e.getSource();
        String correcta = preguntas.get(currentIndex).getRespuestaCorrecta();
        if (clicked.getText().equals(correcta)) {
            usuarioDao.incrementAcertadas(usuarioActual.getId());
        } else {
            usuarioDao.incrementErroneas(usuarioActual.getId());
            clicked.setStyle("-fx-border-color: red; -fx-border-width: 3;");
        }
        highlightCorrect();
    }

    @FXML
    private void handleVolver() throws Exception {
        org.example.discerial.Util.SessionManager
                .switchScene("/org/example/discerial/Tabula_view.fxml");
    }
}
