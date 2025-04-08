package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class SelectImagesController {

    @FXML
    private Label img1;
    @FXML
    private Label img2;
    @FXML
    private Label img3;
    @FXML
    private Label img4;
    @FXML
    private Label img5;
    @FXML
    private Label img6;
    @FXML
    private Label img7;
    @FXML
    private Label img8;
    @FXML
    private Label img9;
    @FXML
    private Label img10;
    @FXML
    private Label img11;
    @FXML
    private Label img12;
    @FXML
    private Label img13;
    @FXML
    private Label img14;
    @FXML
    private Label img15;

    private Consumer<String> onImageSelected;

    // Método para configurar el callback cuando se selecciona una imagen
    public void setOnImageSelected(Consumer<String> callback) {
        this.onImageSelected = callback;
    }

    @FXML
    public void initialize() {
        // Enlace de cada imagen con su respectiva acción
        img1.setOnMouseClicked(e -> seleccionarImagen("hombre1.jpg"));
        img2.setOnMouseClicked(e -> seleccionarImagen("hombre2.jpg"));
        img3.setOnMouseClicked(e -> seleccionarImagen("hombre3.jpg"));
        img4.setOnMouseClicked(e -> seleccionarImagen("hombre4.jpg"));
        img5.setOnMouseClicked(e -> seleccionarImagen("hombre5.jpg"));
        img6.setOnMouseClicked(e -> seleccionarImagen("mujer1.jpg"));
        img7.setOnMouseClicked(e -> seleccionarImagen("mujer2.jpg"));
        img8.setOnMouseClicked(e -> seleccionarImagen("mujer3.jpg"));
        img9.setOnMouseClicked(e -> seleccionarImagen("mujer4.jpg"));
        img10.setOnMouseClicked(e -> seleccionarImagen("mujer5.jpg"));
        img11.setOnMouseClicked(e -> seleccionarImagen("mujer6.jpg"));
        img12.setOnMouseClicked(e -> seleccionarImagen("mujer7.jpg"));
        img13.setOnMouseClicked(e -> seleccionarImagen("hombre6.jpg"));
        img14.setOnMouseClicked(e -> seleccionarImagen("hombre7.jpg"));
        img15.setOnMouseClicked(e -> seleccionarImagen("hombre8.jpg"));
    }

    // Método para seleccionar la imagen y notificar al controlador principal
    private void seleccionarImagen(String nombreImagen) {
        if (onImageSelected != null) {
            onImageSelected.accept(nombreImagen);  // Notificar al controlador principal
        }
    }

    // Método para cerrar la ventana
    @FXML
    public void cerrarVentana() {
        // Obtener el escenario (ventana) actual y cerrarlo
        Stage stage = (Stage) img1.getScene().getWindow(); // Aquí asumo que img1 está en la escena
        stage.close();  // Cerrar la ventana
    }
}
