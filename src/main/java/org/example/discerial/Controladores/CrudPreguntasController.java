package org.example.discerial.Controladores;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.discerial.DAO.ICategoria;
import org.example.discerial.DAO.ICategoriaImpl;
import org.example.discerial.DAO.IPregunta;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Categoria;

import java.io.IOException;
import java.util.List;

import static org.example.discerial.Util.SessionManager.switchScene;

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
    private Button btnNuevo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnVer;      // Botón para ver detalles
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;

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
        // Instancia el DAO de Categoría
        ICategoria categoriaDao = new ICategoriaImpl();
        // Recupera todas las categorías (Historia, Geografía, etc.)
        List<Categoria> categorias = categoriaDao.findAll();
        // Asigna la lista al ComboBox
        categoriaComboBox.setItems(FXCollections.observableArrayList(categorias));
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
        } else {
            showAlert("Advertencia", "Seleccione una pregunta para editar.");
        }
    }

    @FXML
    private void handleEliminar(ActionEvent event) {
        selectedPregunta = preguntaTable.getSelectionModel().getSelectedItem();
        if (selectedPregunta != null) {
            preguntaDAO.deleteById(selectedPregunta.getId());
            loadPreguntas();
            clearForm();
        } else {
            showAlert("Advertencia", "Seleccione una pregunta para eliminar.");
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

    // Nuevo método para ver detalles en modo solo lectura
    @FXML
    private void handleVer(ActionEvent event) {
        selectedPregunta = preguntaTable.getSelectionModel().getSelectedItem();
        if (selectedPregunta != null) {
            StringBuilder detalles = new StringBuilder();
            detalles.append("ID: ").append(selectedPregunta.getId()).append("\n");
            detalles.append("Categoría: ").append(selectedPregunta.getCategoria().getNombre()).append("\n");
            detalles.append("Pregunta: ").append(selectedPregunta.getPregunta()).append("\n");
            detalles.append("Tipo: ").append(selectedPregunta.getTipo()).append("\n");
            detalles.append("Respuesta Correcta: ").append(selectedPregunta.getRespuestaCorrecta()).append("\n");
            detalles.append("Respuesta 2: ").append(selectedPregunta.getRespuesta2()).append("\n");
            if (selectedPregunta.getTipo().equals("multiple")) {
                detalles.append("Respuesta 3: ").append(selectedPregunta.getRespuesta3()).append("\n");
                detalles.append("Respuesta 4: ").append(selectedPregunta.getRespuesta4()).append("\n");
            }
            detalles.append("Imagen URL: ").append(selectedPregunta.getImagen()).append("\n");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Detalles de la Pregunta");
            alert.setHeaderText(null);
            alert.setContentText(detalles.toString());
            alert.showAndWait();
        } else {
            showAlert("Advertencia", "Seleccione una pregunta para ver sus detalles.");
        }
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

    public void irMain() throws Exception {
        switchScene("/org/example/discerial/MainApp_View.fxml");
    }

    @FXML
    private void handleVerIndividual(ActionEvent event) throws Exception {
        // Llamar a switchScene con la nueva vista
        switchScene("/org/example/discerial/preguntas.fxml");
    }





}
