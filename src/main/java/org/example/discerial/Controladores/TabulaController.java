package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
<<<<<<< HEAD
import javafx.scene.input.MouseEvent;
=======
>>>>>>> origin/main
import javafx.scene.layout.Pane;

import java.io.IOException;

<<<<<<< HEAD
import static org.example.discerial.MainApp.switchScene;

=======
>>>>>>> origin/main
public class TabulaController {

    @FXML
    private Pane contenedorFXML;

    @FXML
    private void cargarFXMLPequeno() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/Panels/HomoPanel_view.fxml"));
            Parent nuevoContenido = loader.load();

            // Limpiar el contenedor antes de añadir el nuevo contenido
            contenedorFXML.getChildren().clear();
            contenedorFXML.getChildren().add(nuevoContenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
   

    public void ExireBoton(MouseEvent mouseEvent) throws Exception {
        switchScene("/org/example/discerial/InicioSesion_View.fxml");

    }
=======
>>>>>>> origin/main
}
