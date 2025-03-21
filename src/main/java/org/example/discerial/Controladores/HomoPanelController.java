package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HomoPanelController {

    // FXML de la vista
    @FXML
    private VBox contenedor;  // Contenedor donde añadimos elementos dinámicamente

    @FXML
    private Label homoText, lblTiempoJugado;  // Labels para mostrar información

    @FXML
    private ImageView imagenSeleccionada;  // Imagen del perfil

    @FXML
    private TextField usuarioNombre, usuarioCorreo, usuarioPreguntasAcertadas, usuarioPreguntasErroneas, usuarioCategoriaFavorita;  // Campos de texto

    @FXML
    private Button btnEditar, btnGuardar;  // Botón para activar el modo de edición y guardar cambios

    // Método que se ejecuta al inicializar el controlador
    @FXML
    public void initialize() {
        // Verificamos si el contenedor ha sido correctamente inicializado
        if (contenedor == null) {
            System.out.println("ERROR: El contenedor es NULL. Revisa el FXML.");
        } else {
            System.out.println("Contenedor cargado correctamente.");
            // Llamamos a crearComboBox para añadir un ComboBox de ejemplo al contenedor
            crearComboBox();
        }

        // Deshabilitar todos los campos de texto al inicio
        usuarioNombre.setEditable(false);
        usuarioCorreo.setEditable(false);
        usuarioPreguntasAcertadas.setEditable(false);
        usuarioPreguntasErroneas.setEditable(false);
        usuarioCategoriaFavorita.setEditable(false);

        // Deshabilitar el botón de guardar hasta que el usuario edite el perfil
        btnGuardar.setDisable(true);
    }

    // Método para crear dinámicamente un ComboBox y añadirlo al VBox
    private void crearComboBox() {
        // Creamos un ComboBox con algunas opciones
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Opción 1", "Opción 2", "Opción 3");
        comboBox.setValue("Opción 1");

        // Añadimos el ComboBox al contenedor
        contenedor.getChildren().add(comboBox);
    }

    // Método que se ejecuta cuando el usuario hace click en el botón "Editar"
    @FXML
    private void editarPerfil() {
        System.out.println("Editar perfil pulsado.");

        // Habilitamos solo los campos nombre y correo
        usuarioNombre.setEditable(true);
        usuarioCorreo.setEditable(true);

        // Los demás campos siguen deshabilitados
        usuarioPreguntasAcertadas.setEditable(false);
        usuarioPreguntasErroneas.setEditable(false);
        usuarioCategoriaFavorita.setEditable(false);

        // Habilitar el botón de guardar
        btnGuardar.setDisable(false);
    }

    // Método que se ejecuta cuando el usuario hace click en el botón "Guardar"
    @FXML
    private void guardarPerfil() {
        System.out.println("Guardando perfil...");

        // Validar los campos antes de guardar
        if (usuarioNombre.getText().trim().isEmpty() || usuarioCorreo.getText().trim().isEmpty()) {
            // Si los campos son vacíos, mostrar un mensaje de error
            mostrarError("Por favor, complete los campos obligatorios (Nombre y Correo).");
        } else {
            // Si todo está bien, guardar los cambios (esto es solo un ejemplo)
            // Aquí agregarías la lógica para guardar los datos en una base de datos, archivo, etc.
            System.out.println("Nombre: " + usuarioNombre.getText());
            System.out.println("Correo: " + usuarioCorreo.getText());

            // Mostrar mensaje de éxito
            mostrarExito("Perfil actualizado correctamente.");
        }
    }

    // Método para mostrar un mensaje de error
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para mostrar un mensaje de éxito
    private void mostrarExito(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para manejar el cambio de imagen de perfil
    @FXML
    private void cambiarImagenPerfil() {
        System.out.println("Cambiar imagen de perfil.");
        // Aquí implementas la lógica para que el usuario pueda cambiar su imagen de perfil
        // Podrías abrir un diálogo de selección de archivo y actualizar la imagen del ImageView
    }
}
