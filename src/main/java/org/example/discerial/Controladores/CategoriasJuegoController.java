package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.entities.Pregunta;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.example.discerial.Util.SessionManager.switchScene;

public class CategoriasJuegoController {

    static MusicManager musicManager = MusicManager.getInstance();
    @FXML
    private ImageView imagenHistoriacategorias;

    @FXML
    private ImageView imagenFilosofiacategorias;

    @FXML
    private ImageView imagenBiologiacategorias;

    @FXML
    private ImageView imagenLiteraturacategorias;

    @FXML
    private ImageView imagenMixtacategorias;

    @FXML
    private Label labelHistoria, labelFilosofia, labelBiologia, labelLiteratura, labelMixta;

    private void aplicarHoverYClickLabel(Label label, int categoria_id) {
        label.setOnMouseEntered(e -> {
            label.setScaleX(1.05);
            label.setScaleY(1.05);
        });

        label.setOnMouseExited(e -> {
            label.setScaleX(1.0);
            label.setScaleY(1.0);
        });

        label.setOnMousePressed(e -> {
            label.setScaleX(0.95);
            label.setScaleY(0.95);
        });

        label.setOnMouseReleased(e -> {
            label.setScaleX(1.05);
            label.setScaleY(1.05);
        });

        label.setOnMouseClicked(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            openGameWithCategory(stage, categoria_id);
        });
    }


    private void aplicarHoverYClick(ImageView imageView) {
        // Hover: agrandar un poco y sombra
        imageView.setOnMouseEntered(e -> {
            imageView.setScaleX(1.05);
            imageView.setScaleY(1.05);
            imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 15, 0.5, 0, 0);");
        });

        // Cuando el ratón sale, vuelve a tamaño normal
        imageView.setOnMouseExited(e -> {
            imageView.setScaleX(1.0);
            imageView.setScaleY(1.0);
            imageView.setStyle(null);
        });

        // Clic: animación rápida (efecto rebote)
        imageView.setOnMousePressed(e -> {
            imageView.setScaleX(0.95);
            imageView.setScaleY(0.95);
        });

        imageView.setOnMouseReleased(e -> {
            imageView.setScaleX(1.05);
            imageView.setScaleY(1.05);
        });
    }

    // Declaración de rutas absolutas de recursos dentro de resources
    private final String rutaHistoria = "/Images/CategoryImages/Historia.jpg";
    private final String rutaFilosofia = "/Images/CategoryImages/Filosofia.jpg";
    private final String rutaBiologia = "/Images/CategoryImages/Biologia.jpg";
    private final String rutaLiteratura = "/Images/CategoryImages/Literatura.jpg";
    private final String rutaMixta = "/Images/CategoryImages/Mix.jpg";

    @FXML
    public void initialize() {
        aplicarClipRedondeado(imagenHistoriacategorias, 400, 250, 30);
        aplicarClipRedondeado(imagenFilosofiacategorias, 400, 250, 30);
        aplicarClipRedondeado(imagenBiologiacategorias, 400, 250, 30);
        aplicarClipRedondeado(imagenLiteraturacategorias, 400, 250, 30);
        aplicarClipRedondeado(imagenMixtacategorias, 300, 566, 30);

        cargarImagen(imagenHistoriacategorias, rutaHistoria);
        cargarImagen(imagenFilosofiacategorias, rutaFilosofia);
        cargarImagen(imagenBiologiacategorias, rutaBiologia);
        cargarImagen(imagenLiteraturacategorias, rutaLiteratura);
        cargarImagen(imagenMixtacategorias, rutaMixta);

        aplicarHoverYClick(imagenHistoriacategorias);
        aplicarHoverYClick(imagenFilosofiacategorias);
        aplicarHoverYClick(imagenBiologiacategorias);
        aplicarHoverYClick(imagenLiteraturacategorias);
        aplicarHoverYClick(imagenMixtacategorias);
        aplicarHoverYClickLabel(labelHistoria, 1);
        aplicarHoverYClickLabel(labelLiteratura, 2);
        aplicarHoverYClickLabel(labelFilosofia, 3);
        aplicarHoverYClickLabel(labelBiologia, 4);
        aplicarHoverYClickLabel(labelMixta, 5);

    }

    private void cargarImagen(ImageView iv, String ruta) {
        try {
            Image img = new Image(getClass().getResource(ruta).toExternalForm());
            iv.setImage(img);
        } catch (Exception e) {
            System.err.println("ERROR: No se pudo cargar la imagen: " + ruta);
            e.printStackTrace();
        }
    }

    @FXML
    public void volverTabula() throws IOException {
        musicManager.playRandomSoundEffect();

        switchScene("/org/example/discerial/Tabula_view.fxml");
    }

    private void aplicarClipRedondeado(ImageView imageView, double width, double height, double arc) {
        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(arc);
        clip.setArcHeight(arc);
        imageView.setClip(clip);
    }

    public static void openGameWithCategory(Stage stage, int categoria_id) {
        musicManager.playRandomSoundEffect();

        try {
            FXMLLoader loader = new FXMLLoader(
                    CategoriasJuegoController.class.getResource("/org/example/discerial/LoadingPanelView.fxml")
            );
            Parent root = loader.load();

            LoadingPanelController controller = loader.getController();
            controller.initData(categoria_id);  // Inicia la carga

            stage.getScene().setRoot(root);
            stage.setTitle("Cargando...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void openGameWithWrongQuestions(Stage stage, int categoriaId, List<Pregunta> preguntasErroneas) {
        musicManager.playRandomSoundEffect();

        try {
            FXMLLoader loader = new FXMLLoader(LoadingPanelController.class.getResource("/org/example/discerial/LoadingPanelView.fxml"));
            Parent root = loader.load();

            LoadingPanelController controller = loader.getController();
            controller.initTestFallos(preguntasErroneas, categoriaId);

            stage.getScene().setRoot(root);
            stage.setTitle("Cargando preguntas erróneas...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    private void onHistoriaClicked() {
        Stage stage = (Stage) imagenHistoriacategorias.getScene().getWindow();
        openGameWithCategory(stage, 1);
    }

    @FXML
    private void onLiteraturaClicked() {
        Stage stage = (Stage) imagenHistoriacategorias.getScene().getWindow();
        openGameWithCategory(stage, 2);
    }

    @FXML
    private void onFilosofiaClicked() {
        Stage stage = (Stage) imagenHistoriacategorias.getScene().getWindow();
        openGameWithCategory(stage, 3);
    }

    @FXML
    private void onBiologiaClicked() {
        Stage stage = (Stage) imagenHistoriacategorias.getScene().getWindow();
        openGameWithCategory(stage, 4);
    }

    @FXML
    private void onMixtaClicked() {
        Stage stage = (Stage) imagenHistoriacategorias.getScene().getWindow();
        openGameWithCategory(stage, 5);
    }
}
