package org.example.discerial.Controladores.Preguntas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.discerial.Util.SessionManager;

public class ResultadoTestController {

    @FXML private Label lblTitulo;
    @FXML private VBox vboxFeedback;
    @FXML private Button btnVolver;

    private int aciertos;

    /**
     * Se debe llamar desde quien carga esta escena: setResultados(número de aciertos)
     */
    public void setResultados(int aciertos) {
        this.aciertos = aciertos;
        mostrarFeedback();
    }

    private void mostrarFeedback() {
        lblTitulo.setText("Has acertado " + aciertos + " de 10 preguntas!");

        // Mensajes personalizados según puntuación
        String mensaje;
        if (aciertos == 10) {
            mensaje = "¡10/10 Perfecto! Eres admirable.";
        } else if (aciertos >= 8) {
            mensaje = "¡Muy bien! Casi perfecto.";
        } else if (aciertos >= 5) {
            mensaje = "Bien hecho, sigue practicando.";
        } else {
            mensaje = "No te rindas, ¡inténtalo de nuevo!";
        }
        // Añadir cada frase en labels
        vboxFeedback.getChildren().clear();
        vboxFeedback.getChildren().add(new Label(mensaje));
        vboxFeedback.getChildren().add(new Label("")); // espacio
        vboxFeedback.getChildren().add(new Label("Tu puntuación: " + aciertos + "/10"));
    }

    @FXML
    private void handleVolver() throws Exception {
        // Regresar a la vista de categorías
        SessionManager.switchScene("/org/example/discerial/Tabula_view.fxml");
    }
}