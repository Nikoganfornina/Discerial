package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.discerial.DAO.IEstadoUsuarioImpl;
import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.entities.EstadoUsuario;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.discerial.Util.SessionManager.switchScene;

public class VistaPreguntaController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lblCategoria, lblPregunta, lblTimer;
    @FXML
    private ImageView imgPregunta;
    @FXML
    private Label lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4;
    @FXML
    private VBox vboxOpciones;
    @FXML
    private HBox hboxNav;
    @FXML
    private Button btnAnterior, btnSiguiente;
    @FXML
    private Label lblCountTest;




    private List<Pregunta> listaPreguntas;
    private int indiceActual = 0;

    private Timeline timer;
    private int timeRemaining = 20;

    private final IusuariosImpl usuarioDao = new IusuariosImpl();
    private Usuarios usuarioActual;

    // --- VIDEO ---
    private MediaPlayer goPlayer;
    private MediaView goMediaView;

    private final MusicManager musicManager = MusicManager.getInstance();


    @FXML
    public void initialize() {
        usuarioActual = usuarioDao.currentUser();
        musicManager.playAmbientMusic();
        setupTimer();

        // No cargar preguntas aquí porque no tienes categoria_id
        hboxNav.setVisible(false);
        mostrarCuentaAtras();
    }


    public void mostrarCuentaAtras() {
        try {
            Media media = new Media(getClass().getResource("/Go.mp4").toExternalForm());
            goPlayer = new MediaPlayer(media);
            goMediaView = new MediaView(goPlayer);

            goMediaView.setFitWidth(rootPane.getWidth());
            goMediaView.setFitHeight(rootPane.getHeight());
            goMediaView.setPreserveRatio(false);

            if (!rootPane.getChildren().contains(goMediaView)) {
                rootPane.getChildren().add(goMediaView);
            }
            goMediaView.toFront();

            rootPane.widthProperty().addListener((obs, oldVal, newVal) -> goMediaView.setFitWidth(newVal.doubleValue()));
            rootPane.heightProperty().addListener((obs, oldVal, newVal) -> goMediaView.setFitHeight(newVal.doubleValue()));

            musicManager.stopAll();

            goPlayer.setOnEndOfMedia(() -> {
                goPlayer.stop();
                goPlayer.dispose();
                rootPane.getChildren().remove(goMediaView);
                musicManager.playActionMusic();
                empezarJuegoDespuesVideo();
            });

            goPlayer.setOnError(() -> {
                System.err.println("Error en reproducción de Go.mp4: " + goPlayer.getError());
                rootPane.getChildren().remove(goMediaView);
                musicManager.playActionMusic();
                empezarJuegoDespuesVideo();
            });

            goPlayer.setRate(1.5);
            goPlayer.play();

        } catch (Exception e) {
            System.err.println("Error cargando video Go.mp4: " + e.getMessage());
            musicManager.playActionMusic();
            empezarJuegoDespuesVideo();
        }
    }

    private void actualizarContadorPregunta() {
        int total = listaPreguntas.size();
        int actual = indiceActual + 1; // +1 porque los índices empiezan en 0
        lblCountTest.setText(actual + "/" + total);
    }

    private void empezarJuegoDespuesVideo() {
        mostrarPregunta();
    }

    public void initData(int categoria_id) {
        IPreguntaImpl preguntaDAO = new IPreguntaImpl();

        List<Pregunta> preguntasCategoria = new IPreguntaImpl().findNoRespondidasPorCategoria(categoria_id, usuarioActual.getId());


        if (preguntasCategoria.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test terminado");
            alert.setHeaderText("¡Ya has respondido a todas las preguntas!");
            alert.setContentText("Has completado todas las preguntas de esta categoría. Puedes probar otra.");
            alert.showAndWait().ifPresent(response -> {
                try {
                    switchScene("/org/example/discerial/VistaCategorias.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return;
        }

        Collections.shuffle(preguntasCategoria);
        listaPreguntas = preguntasCategoria.size() > 10 ? preguntasCategoria.subList(0, 10) : new ArrayList<>(preguntasCategoria);
        indiceActual = 0;
        mostrarPregunta();
    }

    // Para categoría mixta (5)
    public void initData(List<Pregunta> preguntasMixtas) {
        listaPreguntas = preguntasMixtas;
        manejarListaPreguntas();
    }

    private void manejarListaPreguntas() {
        if (listaPreguntas.isEmpty()) {
            mostrarAlertaCategoriaCompletada();
            return;
        }

        Collections.shuffle(listaPreguntas);
        if (listaPreguntas.size() > 10) {
            listaPreguntas = listaPreguntas.subList(0, 10);
        }

        indiceActual = 0;
        mostrarPregunta();
    }

    private void mostrarAlertaCategoriaCompletada() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Test terminado");
        alert.setHeaderText("¡Ya has respondido a todas las preguntas!");
        alert.setContentText("Has completado todas las preguntas de esta categoría. Puedes probar otra.");
        alert.showAndWait().ifPresent(response -> {
            try {
                switchScene("/org/example/discerial/VistaCategorias.fxml");
            } catch (IOException e) {
                throw new RuntimeException("Error al cambiar de escena", e);
            }
        });
    }


    private void setupTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            lblTimer.setText("Tiempo: " + timeRemaining + "s");
            if (timeRemaining <= 0) {
                timer.stop();
                saveAttempt(false);
                highlightCorrect();
                hboxNav.setVisible(true);
                deshabilitarOpciones(true);
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

    private void mostrarPregunta() {
        if (listaPreguntas.isEmpty() || indiceActual < 0 || indiceActual >= listaPreguntas.size()) return;

        hboxNav.setVisible(false);
        deshabilitarOpciones(false);
        clearStyles();

        Pregunta p = listaPreguntas.get(indiceActual);

        lblCategoria.setText("Categoría: " + p.getCategoria().getNombre());
        lblPregunta.setText(p.getPregunta());


        // Generar opciones aleatorias
        class Opcion {
            String text;
            boolean correct;

            Opcion(String text, boolean correct) {
                this.text = text;
                this.correct = correct;
            }
        }

        List<Opcion> opciones = List.of(
                new Opcion(p.getRespuestaCorrecta(), true),
                new Opcion(p.getRespuesta2(), false),
                new Opcion(p.getRespuesta3(), false),
                new Opcion(p.getRespuesta4(), false)
        ).stream().filter(o -> o.text != null && !o.text.isBlank()).collect(Collectors.toList());

        Collections.shuffle(opciones);

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

        resetTimer();

        btnAnterior.setDisable(indiceActual == 0);
        btnSiguiente.setDisable(false);

        actualizarContadorPregunta();
    }


    private void clearStyles() {
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            lbl.setStyle("-fx-padding:10; -fx-border-width:2; -fx-border-color:transparent;");
            lbl.getStyleClass().removeAll("correcta", "incorrecta"); // Limpiar clases
        }
    }

    private void highlightCorrect() {
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            if (Boolean.TRUE.equals(lbl.getUserData())) {
                lbl.setStyle("-fx-padding:10; -fx-border-width:3; -fx-border-color:green;");
            }
        }
    }

    private void deshabilitarOpciones(boolean deshabilitar) {
        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            lbl.setDisable(deshabilitar);
        }
    }

    @FXML
    private void handleAnterior() {
        timer.stop();
        musicManager.playRandomSoundEffect();

        if (indiceActual > 0) {
            indiceActual--;
            mostrarPregunta();
        }
    }

    @FXML
    private void handleSiguiente() {
        timer.stop();
        musicManager.playRandomSoundEffect();


        // Cuando estamos en la última pregunta, en lugar de bloquear el botón,
        // vamos a la vista de resultados.
        if (indiceActual == listaPreguntas.size() - 1) {
            irAVistaResultados();
        } else {
            indiceActual++;
            mostrarPregunta();
        }
    }

    private void irAVistaResultados() {
        try {

            musicManager.playRandomSoundEffect();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/ResultadoTestView.fxml"));
            Parent root = loader.load();

            org.example.discerial.Controladores.Preguntas.ResultadoTestViewController controller = loader.getController();
            controller.initData(listaPreguntas); // Pasamos la lista de preguntas para resultados

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOpcion(MouseEvent e) {
        timer.stop();
        musicManager.playRandomSoundEffect();

        Label clicked = (Label) e.getSource();

        boolean acertada = Boolean.TRUE.equals(clicked.getUserData());

        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            lbl.getStyleClass().removeAll("correcta", "incorrecta");
        }

        if (acertada) {
            clicked.getStyleClass().add("correcta");
            musicManager.playRandomSoundWin();
        } else {
            clicked.getStyleClass().add("incorrecta");
            musicManager.playRandomSoundfail();

        }

        for (Label lbl : List.of(lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4)) {
            if (Boolean.TRUE.equals(lbl.getUserData()) && !lbl.equals(clicked)) {
                lbl.getStyleClass().add("correcta");
            }
        }

        saveAttempt(acertada);
        hboxNav.setVisible(true);
        deshabilitarOpciones(true);
    }

    private void saveAttempt(boolean acertada) {
        if (listaPreguntas.isEmpty() || indiceActual < 0 || indiceActual >= listaPreguntas.size()) return;

        Pregunta p = listaPreguntas.get(indiceActual);
        Usuarios u = usuarioDao.currentUser();
        if (u != null) {
            new IEstadoUsuarioImpl().save(new EstadoUsuario(u, p, acertada));
            if (acertada) usuarioDao.incrementAcertadas(u.getId());
            else usuarioDao.incrementErroneas(u.getId());
        }
    }

    public void handleVolver(ActionEvent actionEvent) {
        // Lógica para volver si es necesaria
    }




}