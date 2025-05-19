package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingPanelController {

    @FXML private ProgressBar progressBar;
    @FXML private ImageView imagenCuriosa;
    @FXML private Label sabiasQueLabel;
    @FXML private Label curiosidadLabel;

    private int categoria_id;

    public void initData(int categoria_id) {
        this.categoria_id = categoria_id;
        mostrarImagenAleatoria();
        mostrarCuriosidadAleatoria();
        startLoadingProgress();  // Aquí empieza la barra
    }

    private void mostrarImagenAleatoria() {
        int index = (int) (Math.random() * 15 + 1); // del 1 al 15
        String ruta = "/Images/LoadingGame/loadingimage" + index + ".jpg";
        try {
            Image img = new Image(getClass().getResource(ruta).toExternalForm());
            imagenCuriosa.setImage(img);
        } catch (Exception e) {
            System.err.println("No se pudo cargar imagen: " + ruta);
            e.printStackTrace();
        }
    }

    private void mostrarCuriosidadAleatoria() {
        String[] curiosidades = {
                "Napoleón medía 1,69 m, no 1,50 m como se cree.",
                "Cleopatra vivió más cerca del iPhone que de las pirámides.",
                "La Universidad de Oxford es más antigua que los aztecas.",
                "Julio César fue secuestrado por piratas.",
                "La Torre Eiffel puede crecer 15 cm en verano.",
                "Los vikingos usaban miel como antibiótico.",
                "Shakespeare inventó más de 1.700 palabras.",
                "Los egipcios adoraban gatos como dioses.",
                "Leonardo da Vinci podía escribir al revés.",
                "Einstein reprobó su primer examen de ingreso.",
                "Los romanos usaban orina para lavarse los dientes.",
                "Alejandro Magno fundó más de 70 ciudades.",
                "Tesla dormía 2 horas al día.",
                "El calendario juliano tenía 445 días un año.",
                "Isaac Newton descubrió el cálculo antes que Leibniz."
        };
        String curiosidad = curiosidades[(int)(Math.random() * curiosidades.length)];
        curiosidadLabel.setText(curiosidad);
        sabiasQueLabel.setText("¿Sabías que...");
    }

    private void startLoadingProgress() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        final double[] progress = {0};

        KeyFrame kf = new KeyFrame(Duration.millis(100), event -> {
            double incremento = Math.random() * 0.04 + 0.01; // entre 1% y 5%
            progress[0] += incremento;

            if (progress[0] >= 1.0) {
                progress[0] = 1.0;
                progressBar.setProgress(1.0);
                timeline.stop();
                openGame(); // → aquí se abre el juego real
            } else {
                progressBar.setProgress(progress[0]);
            }
        });

        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void openGame() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/discerial/VistaGameController.fxml")
            );
            Parent root = loader.load();

            GameController gc = loader.getController();
            gc.initData(categoria_id);  // se lo pasamos igual

            Stage stage = (Stage) progressBar.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Categoría " + categoria_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
