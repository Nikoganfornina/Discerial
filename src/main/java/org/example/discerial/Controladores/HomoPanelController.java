package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.Usuarios;

public class HomoPanelController {

    @FXML
    private TextField usuarioNombre;

    @FXML
    private TextField usuarioCorreo;

    @FXML
    private TextField usuarioPreguntasAcertadas;

    @FXML
    private TextField usuarioPreguntasErroneas;

    @FXML
    private TextField usuarioCategoriaFavorita;

    @FXML
    private TextField usuarioHorasEnJuego;

    @FXML
    private ImageView imagenSeleccionada;

    @FXML
    private ComboBox<Image> comboBoxImagenes;

    @FXML
    public void initialize() {
        Usuarios currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            usuarioNombre.setText(currentUser.getNombre());
            usuarioCorreo.setText(currentUser.getCorreo());
            usuarioPreguntasAcertadas.setText(currentUser.getPreguntasAcertadas() + ""); // Placeholder
            usuarioPreguntasErroneas.setText("" + currentUser.getPreguntasErroneas());
            usuarioCategoriaFavorita.setText("Ninguna");
            usuarioHorasEnJuego.setText("0"); // Placeholder

            // Cargar la imagen del usuario
            String imagePath = "/Images/IconosPerfil/" + currentUser.getImagen();
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                imagenSeleccionada.setImage(image);
            } catch (Exception e) {
                System.err.println("No se pudo cargar la imagen: " + imagePath);
            }
        }
        // Hacer invisible el ComboBox
        comboBoxImagenes.setVisible(false);
    }
}
