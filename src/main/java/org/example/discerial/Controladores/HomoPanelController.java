package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Usuarios;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomoPanelController {

    @FXML private Label TituloStyle;
    @FXML private ImageView imagenSeleccionada;
    @FXML private TextField usuarioNombre;
    @FXML private TextField usuarioCorreo;
    @FXML private TextField usuarioPreguntasAcertadas;
    @FXML private TextField usuarioPreguntasErroneas;
    @FXML private TextField usuarioCategoriaFavorita;
    @FXML private Label lblTiempoJugado;
    @FXML private Button btnEditar;
    @FXML private Button btnGuardar;
    @FXML private ComboBox<String> imageComboBox;

    private IusuariosImpl usuariosDao = new IusuariosImpl();
    private Usuarios usuarioActual;
    private final String IMAGE_PATH = "E:/REPOSITORIO/JAVA/Discerial/src/main/resources/Images/IconosPerfil/";

    @FXML
    public void initialize() {
        cargarUsuarioActivo();
        configurarCampos();
        cargarImagenes();
        configurarVisibilidadInicial();
    }

    private void configurarVisibilidadInicial() {
        TituloStyle.setVisible(false);
        imageComboBox.setVisible(false);
        btnGuardar.setVisible(false);
    }

    private void cargarUsuarioActivo() {
        usuarioActual = usuariosDao.currentUser();
        if(usuarioActual != null) {
            actualizarCamposUsuario();
            cargarImagenPerfil();
        }
    }

    private void actualizarCamposUsuario() {
        usuarioNombre.setText(usuarioActual.getNickname());
        usuarioCorreo.setText(usuarioActual.getCorreo());
        usuarioPreguntasAcertadas.setText(String.valueOf(usuarioActual.getPreguntasAcertadas()));
        usuarioPreguntasErroneas.setText(String.valueOf(usuarioActual.getPreguntasErroneas()));
        lblTiempoJugado.setText(usuarioActual.getHorasJugadasFormato());
    }

    private void cargarImagenPerfil() {
        if(usuarioActual.getImagen() != null) {
            Image image = new Image(new File(IMAGE_PATH + usuarioActual.getImagen()).toURI().toString());
            imagenSeleccionada.setImage(image);
        }
    }

    private void configurarCampos() {
        usuarioNombre.setEditable(false);
        usuarioCorreo.setEditable(false);
        usuarioPreguntasAcertadas.setEditable(false);
        usuarioPreguntasErroneas.setEditable(false);
        usuarioCategoriaFavorita.setEditable(false);
    }

    private void cargarImagenes() {
        List<String> imagenes = new ArrayList<>();
        for(int i = 1; i <= 8; i++) {
            imagenes.add("hombre" + i + ".jpg");
            imagenes.add("mujer" + i + ".jpg");
        }
        imageComboBox.getItems().addAll(imagenes);
        configurarListenerImagen();
    }

    private void configurarListenerImagen() {
        imageComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null) {
                Image image = new Image(new File(IMAGE_PATH + newVal).toURI().toString());
                imagenSeleccionada.setImage(image);
            }
        });
    }

    @FXML
    private void entrarModoEdicion() {
        habilitarEdicion(true);
        configurarVisibilidadEdicion(true);
        seleccionarImagenActual();
    }

    @FXML
    private void guardarCambios() {
        if(validarCampos()) {
            actualizarDatosUsuario();
            usuariosDao.update(usuarioActual);
            habilitarEdicion(false);
            configurarVisibilidadEdicion(false);
        }
    }

    private boolean validarCampos() {
        if(usuarioNombre.getText().isBlank() || usuarioCorreo.getText().isBlank()) {
            mostrarAlerta("Campos requeridos", "Nickname y correo son obligatorios");
            return false;
        }
        return true;
    }

    private void actualizarDatosUsuario() {
        usuarioActual.setNickname(usuarioNombre.getText());
        usuarioActual.setCorreo(usuarioCorreo.getText());

        String nuevaImagen = imageComboBox.getValue();
        if(nuevaImagen != null) {
            usuarioActual.setImagen(nuevaImagen);
            cargarImagenPerfil();
        }
    }

    private void habilitarEdicion(boolean habilitar) {
        usuarioNombre.setEditable(habilitar);
        usuarioCorreo.setEditable(habilitar);
        imageComboBox.setVisible(habilitar);
        TituloStyle.setVisible(habilitar);
    }

    private void configurarVisibilidadEdicion(boolean enEdicion) {
        btnEditar.setVisible(!enEdicion);
        btnGuardar.setVisible(enEdicion);
    }

    private void seleccionarImagenActual() {
        if(usuarioActual.getImagen() != null) {
            imageComboBox.getSelectionModel().select(usuarioActual.getImagen());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}