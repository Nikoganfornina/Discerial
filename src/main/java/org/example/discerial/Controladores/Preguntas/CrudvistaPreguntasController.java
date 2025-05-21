package org.example.discerial.Controladores.Preguntas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Pregunta;

import java.util.List;

public class CrudvistaPreguntasController {

    @FXML private Label lblCategoria;
    @FXML private Label lblPregunta;
    @FXML private ImageView imgPregunta;
    @FXML private Label lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4;
    @FXML private VBox vboxOpciones;
    @FXML private HBox hboxNav;

    private List<Pregunta> listaPreguntas;
    private int indiceActual = 0;
    private Pregunta preguntaActual; // Solo guarda la pregunta actual

    // Método para inicializar con una pregunta específica
    public void initData(Pregunta pregunta) {
        this.preguntaActual = pregunta;
        mostrarPregunta(); // Muestra la pregunta recibida
        hboxNav.setVisible(false); // Oculta navegación si solo es para ver una
    }

    @FXML
    public void initialize() {
        listaPreguntas = new IPreguntaImpl().findAll();

        if (listaPreguntas != null && !listaPreguntas.isEmpty()) {
            mostrarPregunta();
            hboxNav.setVisible(true); // Asegura que los botones se vean
        } else {
            lblCategoria.setText("No hay preguntas disponibles.");
            lblPregunta.setText("");
            hboxNav.setVisible(false);
        }
    }

    private void mostrarPregunta() {
        if (preguntaActual == null) return;

        lblCategoria.setText("Categoría: " + preguntaActual.getCategoria().getNombre());
        lblPregunta.setText(preguntaActual.getPregunta());

        // Cargar imagen
        try {
            if (preguntaActual.getImagen() != null && !preguntaActual.getImagen().isBlank()) {
                imgPregunta.setImage(new Image("file:" + preguntaActual.getImagen()));
                imgPregunta.setVisible(true);
            } else {
                imgPregunta.setVisible(false);
            }
        } catch (Exception e) {
            imgPregunta.setVisible(false);
        }

        // Mostrar opciones según el tipo
        if ("multiple".equals(preguntaActual.getTipo())) {
            lblOpcion1.setText("A) " + preguntaActual.getRespuestaCorrecta());
            lblOpcion2.setText("B) " + preguntaActual.getRespuesta2());
            lblOpcion3.setText("C) " + preguntaActual.getRespuesta3());
            lblOpcion4.setText("D) " + preguntaActual.getRespuesta4());
        } else if ("vf".equals(preguntaActual.getTipo())) {
            lblOpcion1.setText("✔ Verdadero: " + preguntaActual.getRespuestaCorrecta());
            lblOpcion2.setText("✖ Falso: " + preguntaActual.getRespuesta2());
            lblOpcion3.setVisible(false);
            lblOpcion4.setVisible(false);
        }
    }






    @FXML
    private void handleAnterior() {
        if (indiceActual > 0) {
            indiceActual--;
            mostrarPregunta();
        }
    }

    @FXML
    private void handleSiguiente() {
        if (indiceActual < listaPreguntas.size() - 1) {
            indiceActual++;
            mostrarPregunta();
        }
    }
}
