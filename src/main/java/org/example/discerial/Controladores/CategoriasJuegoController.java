package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.discerial.Util.SessionManager;

public class CategoriasJuegoController {

    @FXML private Pane imagenHistoriacategorias;
    @FXML private Pane imagenLiteraturacategorias;
    @FXML private Pane imagenFilosofiacategorias;
    @FXML private Pane imagenBiologiacategorias;
    @FXML private Pane imagenMixtacategorias;

    @FXML
    public void initialize() {
        // Puedes añadir efectos hover o cursores aquí
    }

    private void openGameWithCategory(int categoria_id) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/discerial/VistaGameController.fxml")
            );
            Parent root = loader.load();

            // Obtén el controlador del juego y pásale la categoría
            GameController gc = loader.getController();
            gc.initData(categoria_id);

            // Crea una nueva Stage o reutiliza la actual
            Stage stage = (Stage) imagenHistoriacategorias.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Categoria: " + categoria_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML private void onHistoriaClicked()     { openGameWithCategory(1); }
    @FXML private void onLiteraturaClicked()   { openGameWithCategory(3); }
    @FXML private void onFilosofiaClicked()    { openGameWithCategory(4); }
    @FXML private void onBiologiaClicked()     { openGameWithCategory(5); }
    @FXML private void onMixtaClicked()        { openGameWithCategory(2); } //CATEGORIA ANTERIOR GEOGRAFIA
}
