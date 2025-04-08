package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;

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
    @FXML private Button btnIconosPerfil;  // Este es el botón que quieres ocultar por defecto
    @FXML private AnchorPane contenedorImagenes;

    private IusuariosImpl usuariosDao = new IusuariosImpl();
    private Usuarios usuarioActual;

    @FXML
    public void initialize() {
        cargarUsuarioActivo();
        configurarCampos();
        configurarVisibilidadInicial();
    }

    private void configurarVisibilidadInicial() {
        TituloStyle.setVisible(false);
        btnGuardar.setVisible(false);
        btnIconosPerfil.setVisible(false); // El botón comienza oculto
        contenedorImagenes.setVisible(false);
    }

    private void cargarUsuarioActivo() {
        usuarioActual = usuariosDao.currentUser();
        if (usuarioActual != null) {
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
        if (usuarioActual.getImagen() != null) {
            try {
                String path = "/Images/IconosPerfil/" + usuarioActual.getImagen();
                Image image = new Image(getClass().getResource(path).toExternalForm());
                imagenSeleccionada.setImage(image);

                // Crear un clip circular para la imagen
                Circle clip = new Circle(160, 160, 160); // Establecer el centro y el radio del círculo
                imagenSeleccionada.setClip(clip); // Aplicar el clip al ImageView

            } catch (Exception e) {
                System.err.println("Error cargando imagen de perfil: " + usuarioActual.getImagen());
                e.printStackTrace();
            }
        }
    }


    private void configurarCampos() {
        usuarioNombre.setEditable(false);
        usuarioCorreo.setEditable(false);
        usuarioPreguntasAcertadas.setEditable(false);
        usuarioPreguntasErroneas.setEditable(false);
        usuarioCategoriaFavorita.setEditable(false);
    }

    @FXML
    private void entrarModoEdicion() {
        habilitarEdicion(true);
        configurarVisibilidadEdicion(true);
        btnIconosPerfil.setVisible(true); // El botón se hace visible cuando entramos en modo edición
    }

    @FXML
    private void guardarCambios() {
        if (validarCampos()) {
            actualizarDatosUsuario();
            usuariosDao.update(usuarioActual);
            habilitarEdicion(false);
            configurarVisibilidadEdicion(false);
            btnIconosPerfil.setVisible(false); // El botón se oculta al guardar
            contenedorImagenes.setVisible(false);
        }
    }

    private boolean validarCampos() {
        if (usuarioNombre.getText().isBlank() || usuarioCorreo.getText().isBlank()) {
            mostrarAlerta("Campos requeridos", "Nickname y correo son obligatorios");
            return false;
        }
        return true;
    }

    private void actualizarDatosUsuario() {
        usuarioActual.setNickname(usuarioNombre.getText());
        usuarioActual.setCorreo(usuarioCorreo.getText());
    }

    private void habilitarEdicion(boolean habilitar) {
        usuarioNombre.setEditable(habilitar);
        usuarioCorreo.setEditable(habilitar);
        TituloStyle.setVisible(habilitar);
        contenedorImagenes.setVisible(false);
    }

    private void configurarVisibilidadEdicion(boolean enEdicion) {
        btnEditar.setVisible(!enEdicion);
        btnGuardar.setVisible(enEdicion);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void visualizarImagen() {
        try {
            // Ocultar el botón cuando se pulse para cambiar la foto
            btnIconosPerfil.setVisible(false);

            // Modificar la ruta al archivo FXML correctamente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/Panels/SelectImages.fxml"));
            AnchorPane panel = loader.load();

            // Obtenemos el controlador del panel cargado
            SelectImagesController controller = loader.getController();

            // Le decimos qué hacer cuando seleccionen una imagen
            controller.setOnImageSelected(nombreImagen -> {
                usuarioActual.setImagen(nombreImagen);
                cargarImagenPerfil();
                contenedorImagenes.setVisible(false);

                // Mostrar el botón nuevamente después de la selección de imagen
                btnIconosPerfil.setVisible(true);
            });

            contenedorImagenes.getChildren().setAll(panel);
            contenedorImagenes.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de error
        }
    }
}
