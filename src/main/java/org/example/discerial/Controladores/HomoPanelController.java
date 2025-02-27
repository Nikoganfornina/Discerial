package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.Usuarios;

public class HomoPanelController {

    // [Tus componentes existentes...]
    @FXML private TextField usuarioNombre;
    @FXML private TextField usuarioCorreo;
    @FXML private TextField usuarioPreguntasAcertadas;
    @FXML private TextField usuarioPreguntasErroneas;
    @FXML private TextField usuarioCategoriaFavorita;
    @FXML private Label lblTiempoJugado;
    @FXML private ImageView imagenSeleccionada;
    @FXML private ComboBox<Image> comboBoxImagenes;

    private Timeline timeline;  // Para actualizar el tiempo en la UI

    @FXML
    public void initialize() {
        Usuarios currentUser = SessionManager.getCurrentUser();

        // [Tu código existente de inicialización...]
        if (currentUser != null) {
            usuarioNombre.setText(currentUser.getNombre());
            usuarioCorreo.setText(currentUser.getCorreo());
            usuarioPreguntasAcertadas.setText(String.valueOf(currentUser.getPreguntasAcertadas()));
            usuarioPreguntasErroneas.setText(String.valueOf(currentUser.getPreguntasErroneas()));
            usuarioCategoriaFavorita.setText("Ninguna");

            // Cargar imagen
            String imagePath = "/Images/IconosPerfil/" + currentUser.getImagen();
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                imagenSeleccionada.setImage(image);
            } catch (Exception e) {
                System.err.println("Error cargando imagen: " + imagePath);
            }

            // Iniciar contador UI
            iniciarContadorTiempo();
        }

        comboBoxImagenes.setVisible(false);
    }

    private void iniciarContadorTiempo() {
        // Corrección: Añade una coma después de Duration.seconds(1)
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), // <-- Aquí va la coma
                        event -> actualizarTiempo()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void actualizarTiempo() {
        // Obtener tiempo formateado desde SessionManager
        String tiempo = SessionManager.getTiempoJugadoFormateado();
        lblTiempoJugado.setText(tiempo);

        // Opcional: Actualizar consola
        System.out.println("Tiempo actual: " + tiempo);
    }

    // Detener el contador cuando no sea necesario
    public void detenerContador() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}