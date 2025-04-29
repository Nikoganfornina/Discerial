package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Pregunta;

import java.util.List;

import static org.example.discerial.Util.SessionManager.switchScene;

public class VistaPreguntaController {

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

    private List<Pregunta> listaPreguntas;
    private int indiceActual = 0;

    @FXML
    public void initialize() {
        // 1) Cargar todas las preguntas desde la BD
        listaPreguntas = new IPreguntaImpl().findAll();

        // 2) Mostrar la primera
        mostrarPregunta();

        // 3) Configurar habilitación de botones
        btnAnterior.setDisable(true);
        btnSiguiente.setDisable(listaPreguntas.size() <= 1);
    }

    private void mostrarPregunta() {
        if (listaPreguntas.isEmpty()) return;

        Pregunta p = listaPreguntas.get(indiceActual);

        // Categoría y enunciado
        lblCategoria.setText("Categoría: " + p.getCategoria().getNombre());
        lblPregunta.setText(p.getPregunta());

        // Imagen (si existe URL válida)
        if (p.getImagen() != null && !p.getImagen().isBlank()) {
            try {
                imgPregunta.setImage(new Image(p.getImagen()));
                imgPregunta.setVisible(true);
            } catch (Exception e) {
                imgPregunta.setVisible(false);
            }
        } else {
            imgPregunta.setVisible(false);
        }

        // Opciones
        String[] opciones = new String[] {
                p.getRespuestaCorrecta(),
                p.getRespuesta2(),
                p.getRespuesta3(),
                p.getRespuesta4()
        };
        Label[] labels = new Label[] {lblOpcion1, lblOpcion2, lblOpcion3, lblOpcion4};

        String correcta = p.getRespuestaCorrecta();
        for (int i = 0; i < labels.length; i++) {
            String txt = opciones[i];
            labels[i].setText(txt != null ? txt : "");
            // Resetear estilo
            labels[i].setStyle("-fx-padding:10; -fx-border-width:2; -fx-border-color: transparent;");
            // Si coincide con la respuesta correcta, borde verde
            if (txt != null && txt.equals(correcta)) {
                labels[i].setStyle("-fx-padding:10; -fx-border-width:2; -fx-border-color: green;");
            }
        }
    }

    @FXML
    private void handleAnterior() {
        if (indiceActual > 0) {
            indiceActual--;
            mostrarPregunta();
            btnSiguiente.setDisable(false);
            if (indiceActual == 0) {
                btnAnterior.setDisable(true);
            }
        }
    }

    @FXML
    private void handleSiguiente() {
        if (indiceActual < listaPreguntas.size() - 1) {
            indiceActual++;
            mostrarPregunta();
            btnAnterior.setDisable(false);
            if (indiceActual == listaPreguntas.size() - 1) {
                btnSiguiente.setDisable(true);
            }
        }
    }

    @FXML
    private void handleVolver() throws Exception {
        // Vuelve a la vista CRUD sin cambiar tamaño
        switchScene("/org/example/discerial/CrudPreguntas.fxml");
    }
}
