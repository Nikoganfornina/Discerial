package org.example.discerial.Controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SelectImagesController {

    @FXML private Label img1, img2, img3, img4, img5,
            img6, img7, img8, img9, img10,
            img11, img12, img13, img14, img15;

    @FXML private GridPane contenedorImagenes;
    private Consumer<String> onImageSelected;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            cargarImagenes();
            configurarEventos();
            agregarEfectosHover();
        });
    }

    private void cargarImagenes() {
        List<Label> labels = Arrays.asList(img1, img2, img3, img4, img5,
                img6, img7, img8, img9, img10,
                img11, img12, img13, img14, img15);

        List<String> nombresImagenes = Arrays.asList(
                "hombre1.jpg", "hombre2.jpg", "hombre3.jpg", "hombre4.jpg",
                "mujer1.jpg", "mujer2.jpg", "mujer3.jpg", "mujer4.jpg",
                "hombre5.jpg", "hombre6.jpg", "hombre7.jpg", "hombre8.jpg",
                "mujer5.jpg", "mujer6.jpg", "mujer7.jpg"
        );

        for (int i = 0; i < labels.size(); i++) {
            cargarImagenEnLabel(labels.get(i), nombresImagenes.get(i));
        }
    }

    private void cargarImagenEnLabel(Label label, String nombreArchivo) {
        try {
            String ruta = "/Images/IconosPerfil/" + nombreArchivo;
            InputStream inputStream = getClass().getResourceAsStream(ruta);

            if (inputStream != null) {
                Image image = new Image(inputStream);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                imageView.setUserData(nombreArchivo);  // Almacenar nombre para eventos
                label.setGraphic(imageView);
            } else {
                System.err.println("⚠️ Imagen no encontrada: " + ruta);
                label.setStyle("-fx-background-color: #ff000030;");
            }
        } catch (Exception e) {
            System.err.println("🚨 Error cargando imagen: " + e.getMessage());
        }
    }

    private void configurarEventos() {
        List<Label> imagenes = Arrays.asList(img1, img2, img3, img4, img5,
                img6, img7, img8, img9, img10,
                img11, img12, img13, img14, img15);

        imagenes.forEach(label -> {
            label.setOnMouseClicked(e -> {
                ImageView imageView = (ImageView) label.getGraphic();
                if (imageView != null) {
                    String nombreImagen = (String) imageView.getUserData();
                    seleccionarImagen(nombreImagen);
                }
            });
        });
    }

    private void agregarEfectosHover() {
        List<Label> imagenes = Arrays.asList(img1, img2, img3, img4, img5,
                img6, img7, img8, img9, img10,
                img11, img12, img13, img14, img15);

        imagenes.forEach(label -> {
            label.setOnMouseEntered(e -> {
                label.setStyle("-fx-border-color: #2E8540; -fx-border-width: 2;");
                label.setScaleX(1.05);
                label.setScaleY(1.05);
            });

            label.setOnMouseExited(e -> {
                label.setStyle("-fx-border-color: transparent;");
                label.setScaleX(1.0);
                label.setScaleY(1.0);
            });
        });
    }

    private void seleccionarImagen(String nombreImagen) {
        if (onImageSelected != null) {
            onImageSelected.accept(nombreImagen);
            cerrarVentana();
        }
    }

    public void setOnImageSelected(Consumer<String> callback) {
        this.onImageSelected = callback;
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) contenedorImagenes.getScene().getWindow();
        stage.close();
    }
}