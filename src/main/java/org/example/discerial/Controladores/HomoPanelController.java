package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class HomoPanelController {

    @FXML
    private ComboBox<Image> comboBoxImagenes;

    @FXML
    private ImageView imagenSeleccionada;

    @FXML
    public void initialize() {
        List<Image> imagenes = cargarImagenesPerfil();
        comboBoxImagenes.getItems().addAll(imagenes);

        // Personalizar el ComboBox para mostrar imágenes en miniatura
        comboBoxImagenes.setCellFactory(new Callback<ListView<Image>, ListCell<Image>>() {
            @Override
            public ListCell<Image> call(ListView<Image> listView) {
                return new ListCell<Image>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(Image image, boolean empty) {
                        super.updateItem(image, empty);
                        if (image != null) {
                            imageView.setImage(image);
                            imageView.setFitWidth(40);
                            imageView.setFitHeight(40);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        // Cambiar la imagen principal cuando se selecciona una en el ComboBox
        comboBoxImagenes.setOnAction(event -> {
            Image selectedImage = comboBoxImagenes.getSelectionModel().getSelectedItem();
            if (selectedImage != null) {
                imagenSeleccionada.setImage(selectedImage);
            }
        });
    }

    private List<Image> cargarImagenesPerfil() {
        List<Image> imagenes = new ArrayList<>();
        String rutaBase = "/Images/IconosPerfil/";

        // Cargar imágenes de hombre1.jpg hasta hombre8.jpg
        for (int i = 1; i <= 8; i++) {
            String nombreArchivo = "hombre" + i + ".jpg";
            Image imagen = cargarImagen(rutaBase + nombreArchivo);
            if (imagen != null) {
                imagenes.add(imagen);
            }
        }

        // Cargar imágenes de mujer1.jpg hasta mujer8.jpg
        for (int i = 1; i <= 8; i++) {
            String nombreArchivo = "mujer" + i + ".jpg";
            Image imagen = cargarImagen(rutaBase + nombreArchivo);
            if (imagen != null) {
                imagenes.add(imagen);
            }
        }

        return imagenes;
    }

    private Image cargarImagen(String ruta) {
        try {
            return new Image(getClass().getResource(ruta).toExternalForm());
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
            return null;
        }
    }
}
