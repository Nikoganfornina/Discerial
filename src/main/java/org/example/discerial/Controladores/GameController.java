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
import org.example.discerial.DAO.*;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.EstadoUsuario;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;
import java.util.List;

public class GameController {

    // Componentes FXML (deben coincidir EXACTAMENTE con los nombres del archivo .fxml)
    @FXML private Label lblCategoria;
    @FXML private Label lblPregunta;
    @FXML private ImageView imgPregunta;
    @FXML private Label lblOpcion1;
    @FXML private Label lblOpcion2;
    @FXML private Label lblOpcion3;
    @FXML private Label lblOpcion4;
    @FXML private Button btnAnterior;
    @FXML private Button btnSiguiente;
    @FXML private Button btnVolver;
    @FXML private Label lblTimer;
    @FXML private Label lblCuentaAtras;

    private List<Pregunta> preguntas;
    private int currentIndex = 0;
    private Timeline gameTimer;
    private int timeRemaining = 20;
    private final MusicManager musicManager = MusicManager.getInstance();
    private final IusuariosImpl usuarioDao = new IusuariosImpl();
    private Usuarios usuarioActual;

    @FXML
    public void initialize() {
        configurarLabelCuentaAtras();
    }

    private void configurarLabelCuentaAtras() {
        lblCuentaAtras.setVisible(false);
        lblCuentaAtras.setStyle("-fx-font-size: 150px; -fx-text-fill: #00FF00; -fx-font-weight: bold;");
        lblCuentaAtras.setLayoutX(600);
        lblCuentaAtras.setLayoutY(150);
    }

    public void initData(int categoria_id) {
        usuarioActual = usuarioDao.currentUser();
        cargarPreguntas(categoria_id);
        musicManager.stopAll();
        iniciarCuentaRegresiva();
    }

    private void cargarPreguntas(int categoriaId) {
        IPreguntaImpl preguntaDao = new IPreguntaImpl();
        preguntas = preguntaDao.findByCategoria(categoriaId);
    }

    private void iniciarCuentaRegresiva() {
        lblCuentaAtras.setVisible(true);
        final int[] segundos = {5};

        Timeline cuentaAtras = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (segundos[0] > 0) {
                actualizarCuentaRegresiva(segundos[0]);
                musicManager.playSoundEffect("/Sounds/readysound.mp3"); // Ruta relativa
                segundos[0]--;
            } else {
                mostrarInicioJuego();
                musicManager.playSoundEffect("/Sounds/startsound.mp3"); // Ruta relativa
                iniciarFaseAccion();
            }
        }));
        cuentaAtras.setCycleCount(6);
        cuentaAtras.play();
    }

    private void navegarPreguntas(int direccion) {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        int nuevoIndice = currentIndex + direccion;
        if (nuevoIndice >= 0 && nuevoIndice < preguntas.size()) {
            mostrarPregunta(nuevoIndice);
        }
    }
    private void actualizarCuentaRegresiva(int segundo) {
        lblCuentaAtras.setText(String.valueOf(segundo));
    }

    private void mostrarInicioJuego() {
        lblCuentaAtras.setText("¡YA!");
        Timeline retraso = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            lblCuentaAtras.setVisible(false);
        }));
        retraso.play();
    }


    private void configurarTemporizador() {
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            actualizarTemporizador();
            if (timeRemaining <= 0) manejarTiempoAgotado();
        }));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();
    }

    private void actualizarTemporizador() {
        timeRemaining--;
        lblTimer.setText("Tiempo: " + timeRemaining + "s");
    }

    private void manejarTiempoAgotado() {
        gameTimer.stop();
        usuarioDao.incrementErroneas(usuarioActual.getId());
        resaltarRespuestaCorrecta();
    }

    private void resaltarRespuestaCorrecta() {
        String correcta = preguntas.get(currentIndex).getRespuestaCorrecta();
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            lbl.setStyle(lbl.getText().equals(correcta)
                    ? "-fx-border-color: green; -fx-border-width: 3;"
                    : "");
        }
    }

    private void mostrarPregunta(int index) {
        if (index < 0 || index >= preguntas.size()) return;
        currentIndex = index;
        Pregunta p = preguntas.get(currentIndex);

        lblCategoria.setText(p.getCategoria().getNombre());
        lblPregunta.setText(p.getPregunta());
        imgPregunta.setImage((p.getImagen() != null && !p.getImagen().isEmpty())
                ? new Image(p.getImagen())
                : null);

        lblOpcion1.setText(p.getRespuestaCorrecta());
        lblOpcion2.setText(p.getRespuesta2());
        lblOpcion3.setText(p.getRespuesta3());
        lblOpcion4.setText(p.getRespuesta4());

        reiniciarEstilos();
        reiniciarTemporizador();
    }

    private void reiniciarEstilos() {
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            lbl.setStyle("");
        }
    }

    private void reiniciarTemporizador() {
        gameTimer.stop();
        timeRemaining = 20;
        lblTimer.setText("Tiempo: " + timeRemaining + "s");
        gameTimer.playFromStart();
    }

    // Métodos manejadores de eventos (NOMBRES CORREGIDOS PARA COINCIDIR CON FXML)
    @FXML
    private void handleVolver() throws IOException {
        musicManager.stopAll();
        musicManager.playAmbientMusic();
        SessionManager.switchScene("/org/example/discerial/Tabula_view.fxml");
    }

    @FXML
    private void handleAnterior() {
        navegarPreguntas(-1);
    }

    @FXML
    private void handleSiguiente() {
        navegarPreguntas(1);
    }

    private void iniciarFaseAccion() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        musicManager.playActionMusic();
        configurarTemporizador();
        mostrarPregunta(0);
    }



    @FXML
    private void handleOpcion(MouseEvent event) {
        gameTimer.stop();
        Label opcionSeleccionada = (Label) event.getSource();
        Pregunta preguntaActual = preguntas.get(currentIndex);
        boolean esCorrecta = opcionSeleccionada.getText().equals(preguntaActual.getRespuestaCorrecta());

        if (!esCorrecta) {
            opcionSeleccionada.setStyle("-fx-border-color: red; -fx-border-width: 3;");
        }
        resaltarRespuestaCorrecta();

        if (usuarioActual != null) {
            guardarResultadoUsuario(preguntaActual, esCorrecta);
        }
    }

    private void guardarResultadoUsuario(Pregunta pregunta, boolean resultado) {
        new IEstadoUsuarioImpl().save(new EstadoUsuario(usuarioActual, pregunta, resultado));
    }
}