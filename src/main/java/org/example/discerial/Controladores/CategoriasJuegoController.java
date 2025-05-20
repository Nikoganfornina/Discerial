package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.discerial.Util.SessionManager.switchScene;

public class CategoriasJuegoController {

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
        switchScene("/org/example/discerial/Tabula_view.fxml");
    }

    private void aplicarClipRedondeado(ImageView imageView, double width, double height, double arc) {
        Rectangle clip = new Rectangle(width, height);
        clip.setArcWidth(arc);
        clip.setArcHeight(arc);
        imageView.setClip(clip);
    }

    private void openGameWithCategory(int categoria_id) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/discerial/LoadingPanelView.fxml")
            );
            Parent root = loader.load();

            // Aquí está el problema: NO ESTABAS LLAMANDO A INIT
            LoadingPanelController controller = loader.getController();
            controller.initData(categoria_id);  // 💥 Esto hace que empiece la barra

            Stage stage = (Stage) imagenHistoriacategorias.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cargando...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML private void onHistoriaClicked()     { openGameWithCategory(1); }

    @FXML private void onLiteraturaClicked()   { openGameWithCategory(2); }

    @FXML private void onFilosofiaClicked()    { openGameWithCategory(3); }

    @FXML private void onBiologiaClicked()     { openGameWithCategory(4); }

    @FXML private void onMixtaClicked()        { openGameWithCategory(5); }
}
