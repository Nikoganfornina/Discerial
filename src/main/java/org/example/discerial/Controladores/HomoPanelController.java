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
import java.io.InputStream;

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

        int acertadas = usuariosDao.getPreguntasAcertadas(usuarioActual.getId());
        int erroneas = usuariosDao.getPreguntasErroneas(usuarioActual.getId());

        usuarioActual.setPreguntasAcertadas(acertadas);
        usuarioActual.setPreguntasErroneas(erroneas);

        usuarioPreguntasAcertadas.setText(String.valueOf(acertadas));
        usuarioPreguntasErroneas.setText(String.valueOf(erroneas));


        lblTiempoJugado.setText(usuarioActual.getHorasJugadasFormato());
    }

    private void cargarImagenPerfil() {
        if (usuarioActual.getImagen() != null) {
            try {
                // Usa getResourceAsStream con rutas absolutas
                InputStream inputStream = getClass().getResourceAsStream(
                        "/Images/IconosPerfil/" + usuarioActual.getImagen()
                );

                if (inputStream != null) {
                    Image image = new Image(inputStream);
                    imagenSeleccionada.setImage(image);

                    // Configuración del clip circular
                    Circle clip = new Circle(
                            imagenSeleccionada.getFitWidth()/2,
                            imagenSeleccionada.getFitHeight()/2,
                            imagenSeleccionada.getFitWidth()/2
                    );
                    imagenSeleccionada.setClip(clip);
                } else {
                    System.err.println("No se encontró la imagen: " + usuarioActual.getImagen());
                }
            } catch (Exception e) {
                System.err.println("Error cargando imagen: " + e.getMessage());
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
        btnIconosPerfil.setVisible(true);
        btnEditar.setVisible(false);  // Ocultar botón Editar inmediatamente
// El botón se hace visible cuando entramos en modo edición
    }

    @FXML
    private void guardarCambios() {
        if (validarCampos()) {
            actualizarDatosUsuario();
            usuariosDao.update(usuarioActual);
            habilitarEdicion(false);
            configurarVisibilidadEdicion(false);
            btnIconosPerfil.setVisible(false);
            contenedorImagenes.setVisible(false);
            btnEditar.setVisible(true);  // Mostrar botón Editar después de guardar
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
        TituloStyle.setVisible(enEdicion);
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
            btnIconosPerfil.setVisible(false);  // Ocultar al abrir selector
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/Panels/SelectImages.fxml"));
            AnchorPane panel = loader.load();

            SelectImagesController controller = loader.getController();
            controller.setOnImageSelected(nombreImagen -> {
                usuarioActual.setImagen(nombreImagen);
                cargarImagenPerfil();
                contenedorImagenes.setVisible(false);
                btnIconosPerfil.setVisible(true);  // Restaurar visibilidad
            });

            contenedorImagenes.getChildren().setAll(panel);
            contenedorImagenes.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}