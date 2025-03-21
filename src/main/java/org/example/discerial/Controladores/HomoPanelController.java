package org.example.discerial.Controladores;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import org.example.discerial.DAO.IusuariosImpl;
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


    @FXML
    public void initialize() {

        IusuariosImpl usuario = new IusuariosImpl();
        Usuarios currentUser = usuario.currentUser();

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
        }

        comboBoxImagenes.setVisible(false);
    }



}