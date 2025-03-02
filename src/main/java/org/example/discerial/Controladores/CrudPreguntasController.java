package org.example.discerial.Controladores;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Categoria;

import java.util.List;

public class CrudPreguntasController {

    @FXML
    private TableView<Pregunta> preguntaTable;
    @FXML
    private TableColumn<Pregunta, Integer> colId;
    @FXML
    private TableColumn<Pregunta, String> colCategoria;
    @FXML
    private TableColumn<Pregunta, String> colPregunta;
    @FXML
    private TableColumn<Pregunta, String> colTipo;

    @FXML
    private ComboBox<Categoria> categoriaComboBox;
    @FXML
    private ComboBox<String> tipoComboBox;

    @FXML
    private TextField preguntaField;
    @FXML
    private TextField respuestaCorrectaField;
    @FXML
    private TextField respuesta2Field;
    @FXML
    private TextField respuesta3Field;
    @FXML
    private TextField respuesta4Field;
    @FXML
    private TextField imagenField;

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;

    private ObservableList<Pregunta> preguntaList;
    private IPregunta preguntaDAO;
    private Pregunta selectedPregunta;

    @FXML
    public void initialize() {
        preguntaDAO = new IPreguntaImpl();
        // Configuración de las columnas del TableView
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria().getNombre()));
        colPregunta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPregunta()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo()));

        loadPreguntas();
        loadCategorias();
        loadTipos();
    }

    private void loadPreguntas() {
        List<Pregunta> list = preguntaDAO.findAll();
        preguntaList = FXCollections.observableArrayList(list);
        preguntaTable.setItems(preguntaList);
    }

    private void loadCategorias() {
        // Cargar categorías predefinidas
        ObservableList<Categoria> categorias = FXCollections.observableArrayList();
        categorias.add(new Categoria("Historia"));
        categorias.add(new Categoria("Filosofía"));
        categorias.add(new Categoria("Literatura"));
        categorias.add(new Categoria("Biología"));
        // Si deseas agregar la categoría "Mixta", podrías hacerlo aquí (opcional)
        categoriaComboBox.setItems(categorias);
    }

    private void loadTipos() {
        ObservableList<String> tipos = FXCollections.observableArrayList("multiple", "vf");
        tipoComboBox.setItems(tipos);
    }

    @FXML
    private void handleNuevo(ActionEvent event) {
        clearForm();
        selectedPregunta = null;
    }

    @FXML
    private void handleEditar(ActionEvent event) {
        selectedPregunta = preguntaTable.getSelectionModel().getSelectedItem();
        if (selectedPregunta != null) {
            // Cargar los datos de la pregunta seleccionada en el formulario
            categoriaComboBox.setValue(selectedPregunta.getCategoria());
            tipoComboBox.setValue(selectedPregunta.getTipo());
            preguntaField.setText(selectedPregunta.getPregunta());
            respuestaCorrectaField.setText(selectedPregunta.getRespuestaCorrecta());
            respuesta2Field.setText(selectedPregunta.getRespuesta2());
            respuesta3Field.setText(selectedPregunta.getRespuesta3());
            respuesta4Field.setText(selectedPregunta.getRespuesta4());
            imagenField.setText(selectedPregunta.getImagen());
        }
    }

    @FXML
    private void handleEliminar(ActionEvent event) {
        selectedPregunta = preguntaTable.getSelectionModel().getSelectedItem();
        if (selectedPregunta != null) {
            preguntaDAO.deleteById(selectedPregunta.getId());
            loadPreguntas();
            clearForm();
        }
    }

    @FXML
    private void handleGuardar(ActionEvent event) {
        Categoria categoria = categoriaComboBox.getValue();
        String tipo = tipoComboBox.getValue();
        String preguntaText = preguntaField.getText();
        String respCorrecta = respuestaCorrectaField.getText();
        String resp2 = respuesta2Field.getText();
        String resp3 = respuesta3Field.getText();
        String resp4 = respuesta4Field.getText();
        String imagen = imagenField.getText();

        // Validación mínima de campos obligatorios
        if (categoria == null || tipo == null || preguntaText.isEmpty() || respCorrecta.isEmpty()) {
            showAlert("Error", "Por favor, rellena los campos obligatorios.");
            return;
        }

        if ("vf".equals(tipo)) {
            // Para Verdadero/Falso se usan solo dos respuestas
            resp3 = null;
            resp4 = null;
        }

        if (selectedPregunta == null) {
            // Crear una nueva pregunta
            Pregunta nueva = new Pregunta(categoria, preguntaText, respCorrecta, resp2, resp3, resp4, imagen, tipo);
            preguntaDAO.save(nueva);
        } else {
            // Actualizar la pregunta existente
            selectedPregunta.setCategoria(categoria);
            selectedPregunta.setTipo(tipo);
            selectedPregunta.setPregunta(preguntaText);
            selectedPregunta.setRespuestaCorrecta(respCorrecta);
            selectedPregunta.setRespuesta2(resp2);
            selectedPregunta.setRespuesta3(resp3);
            selectedPregunta.setRespuesta4(resp4);
            selectedPregunta.setImagen(imagen);
            preguntaDAO.update(selectedPregunta);
        }
        loadPreguntas();
        clearForm();
    }

    @FXML
    private void handleCancelar(ActionEvent event) {
        clearForm();
    }

    private void clearForm() {
        categoriaComboBox.setValue(null);
        tipoComboBox.setValue(null);
        preguntaField.clear();
        respuestaCorrectaField.clear();
        respuesta2Field.clear();
        respuesta3Field.clear();
        respuesta4Field.clear();
        imagenField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
