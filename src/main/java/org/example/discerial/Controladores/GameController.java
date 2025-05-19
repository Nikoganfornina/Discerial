package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.example.discerial.DAO.IEstadoUsuarioImpl;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.EstadoUsuario;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Usuarios;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    @FXML private Label lblCategoria, lblPregunta, lblTimer;
    @FXML private ImageView imgPregunta;
    @FXML private Label lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4;
    @FXML private Button btnAnterior, btnSiguiente, btnVolver;

    private List<Pregunta> preguntas;
    private int currentIndex = 0;
    private Timeline timer;
    private int timeRemaining = 20;

    private final IusuariosImpl usuarioDao = new IusuariosImpl();
    private Usuarios usuarioActual;

    public void initData(int categoria_id) {
        usuarioActual = usuarioDao.currentUser();
        preguntas = new IPreguntaImpl().findByCategoria(categoria_id);
        setupTimer();
        showQuestion(0);
    }

    private void setupTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            lblTimer.setText("Tiempo: " + timeRemaining + "s");
            if (timeRemaining <= 0) {
                timer.stop();
                // equivalente a fallo por tiempo
                saveAttempt(false);
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
        if (p.getImagen() != null && !p.getImagen().isBlank())
            imgPregunta.setImage(new Image(p.getImagen()));
        else
            imgPregunta.setImage(null);

        // 1) Lista de pares texto / si es correcta
        class Opcion { String text; boolean correct;
            Opcion(String t, boolean c){text=t;correct=c;} }
        List<Opcion> opciones = List.of(
                        new Opcion(p.getRespuestaCorrecta(), true),
                        new Opcion(p.getRespuesta2(), false),
                        new Opcion(p.getRespuesta3(), false),
                        new Opcion(p.getRespuesta4(), false)
                ).stream()
                .filter(o -> o.text != null && !o.text.isBlank())
                .collect(Collectors.toList());

        // 2) Barajar
        Collections.shuffle(opciones);

        // 3) Asignar a Labels
        Label[] labels = {lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4};
        for (int i = 0; i < labels.length; i++) {
            Label lbl = labels[i];
            if (i < opciones.size()) {
                Opcion op = opciones.get(i);
                lbl.setText(op.text);
                lbl.setUserData(op.correct);
                lbl.setVisible(true);
                lbl.setStyle("-fx-padding:10; -fx-border-width:2; -fx-border-color:transparent;");
            } else {
                lbl.setVisible(false);
                lbl.setText("");
                lbl.setUserData(false);
            }
        }

        clearStyles();
        resetTimer();
    }

    private void clearStyles() {
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4))
            lbl.setStyle("-fx-padding:10; -fx-border-width:2; -fx-border-color:transparent;");
    }

    private void highlightCorrect() {
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            if (Boolean.TRUE.equals(lbl.getUserData())) {
                lbl.setStyle("-fx-padding:10; -fx-border-width:3; -fx-border-color:green;");
            }
        }
    }

    @FXML private void handleAnterior() {
        timer.stop();
        if (currentIndex > 0) {
            showQuestion(currentIndex - 1);
            btnSiguiente.setDisable(false);
            btnAnterior.setDisable(currentIndex - 1 == 0);
        }
    }

    @FXML private void handleSiguiente() {
        timer.stop();
        if (currentIndex < preguntas.size() - 1) {
            showQuestion(currentIndex + 1);
            btnAnterior.setDisable(false);
            btnSiguiente.setDisable(currentIndex + 1 == preguntas.size() - 1);
        }
    }

    @FXML private void handleOpcion(MouseEvent e) {
        timer.stop();
        Label clicked = (Label) e.getSource();
        boolean resultado = Boolean.TRUE.equals(clicked.getUserData());
        if (!resultado) {
            clicked.setStyle("-fx-padding:10; -fx-border-width:3; -fx-border-color:red;");
        }
        highlightCorrect();
        saveAttempt(resultado);
    }

    private void saveAttempt(boolean acertada) {
        // sube a BD tanto aciertos como fallos
        Pregunta p = preguntas.get(currentIndex);
        Usuarios u = usuarioDao.currentUser();
        if (u != null) {
            new IEstadoUsuarioImpl().save(new EstadoUsuario(u, p, acertada));
            if (acertada) usuarioDao.incrementAcertadas(u.getId());
            else          usuarioDao.incrementErroneas (u.getId());
        }
    }

    @FXML private void handleVolver() throws Exception {
        SessionManager.switchScene("/org/example/discerial/Tabula_view.fxml");
    }
}