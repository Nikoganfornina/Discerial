package org.example.discerial.Controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.example.discerial.DAO.IEstadoUsuarioImpl;
import org.example.discerial.DAO.IPreguntaImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.Util.SessionTimer;
import org.example.discerial.entities.Usuarios;
import org.hibernate.Session;

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
    @FXML private Button btnIconosPerfil;  // Botón oculto por defecto
    @FXML private AnchorPane contenedorImagenes;

    @FXML private ImageView premiohistoria;
    @FXML private ImageView premiobiologia;
    @FXML private ImageView premiofilosofia;
    @FXML private ImageView premioliteratura;


    private IEstadoUsuarioImpl estadoUsuarioDao = new IEstadoUsuarioImpl();
    private final IusuariosImpl usuarioDao = new IusuariosImpl();
    private final IPreguntaImpl preguntaDao = new IPreguntaImpl();

    MusicManager musicManager = MusicManager.getInstance();
    private Usuarios usuarioActual;
    private Timeline timelineActualizacionTiempo;


    @FXML
    public void initialize() {
        cargarUsuarioActivo();
        configurarCampos();
        configurarVisibilidadInicial();
        mostrarCategoriaFavorita();  // Actualiza el campo aquí
        iniciarActualizacionTiempo(); // Nueva línea
        cargarImagenesPremios();
    }

    private void mostrarCategoriaFavorita() {
        if (usuarioActual != null) {
            String categoria = estadoUsuarioDao.getCategoriaFavorita(usuarioActual.getId());
            if (categoria != null && !categoria.isEmpty()) {
                usuarioCategoriaFavorita.setText(categoria);
            } else {
                usuarioCategoriaFavorita.setText("Sin categoría favorita");
            }
        } else {
            usuarioCategoriaFavorita.setText("Sin usuario activo");
        }
    }

    private void configurarVisibilidadInicial() {
        TituloStyle.setVisible(false);
        btnGuardar.setVisible(false);
        btnIconosPerfil.setVisible(false); // El botón comienza oculto
        contenedorImagenes.setVisible(false);
    }

    private void cargarUsuarioActivo() {
        usuarioActual = usuarioDao.currentUser();
        if (usuarioActual != null) {
            // Actualizar campos antes de iniciar el timeline
            actualizarCamposUsuario();
            cargarImagenPerfil();

            // Forzar primera actualización
            Platform.runLater(() -> actualizarTiempoJugado());
        }
    }

    private void actualizarCamposUsuario() {
        usuarioNombre.setText(usuarioActual.getNickname());
        usuarioCorreo.setText(usuarioActual.getCorreo());

        int acertadas = usuarioDao.getPreguntasAcertadas(usuarioActual.getId());
        int erroneas = usuarioDao.getPreguntasErroneas(usuarioActual.getId());

        usuarioActual.setPreguntasAcertadas(acertadas);
        usuarioActual.setPreguntasErroneas(erroneas);

        usuarioPreguntasAcertadas.setText(String.valueOf(acertadas));
        usuarioPreguntasErroneas.setText(String.valueOf(erroneas));

        lblTiempoJugado.setText(usuarioActual.getHorasJugadasFormato());
    }



    private String formatMillisToHHMM(long millis) {
        long totalSeconds = millis / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        return String.format("%02d:%02d", hours, minutes);
    }


    // Método para detener la actualización cuando sea necesario
    public void detenerActualizacionTiempo() {
        if (timelineActualizacionTiempo != null) {
            timelineActualizacionTiempo.stop();
        }
    }
    private void cargarImagenPerfil() {
        if (usuarioActual.getImagen() != null) {
            try {
                InputStream inputStream = getClass().getResourceAsStream(
                        "/Images/IconosPerfil/" + usuarioActual.getImagen()
                );

                if (inputStream != null) {
                    Image image = new Image(inputStream);
                    imagenSeleccionada.setImage(image);

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
        musicManager.playRandomSoundEffect();

        habilitarEdicion(true);
        configurarVisibilidadEdicion(true);
        btnIconosPerfil.setVisible(true);
        btnEditar.setVisible(false);
    }

    @FXML
    private void guardarCambios() {
        if (validarCampos()) {
            musicManager.playRandomSoundEffect();

            actualizarDatosUsuario();
            usuarioDao.update(usuarioActual);
            habilitarEdicion(false);
            configurarVisibilidadEdicion(false);
            btnIconosPerfil.setVisible(false);
            contenedorImagenes.setVisible(false);
            btnEditar.setVisible(true);
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

    private void iniciarActualizacionTiempo() {
        timelineActualizacionTiempo = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (usuarioActual != null) {
                        // Obtener tiempo DESDE LA BD (no del timer)
                        long tiempoBD = usuarioDao.getHorasJugadasFromBD(usuarioActual.getId());
                        lblTiempoJugado.setText(formatMillisToHHMM(tiempoBD));
                    }
                })
        );
        timelineActualizacionTiempo.setCycleCount(Timeline.INDEFINITE);
        timelineActualizacionTiempo.play();
    }
    // Añadir método en IusuariosImpl
    public long getHorasJugadasFromBD(long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return ((Number) session.createNativeQuery("SELECT horasJugadas FROM usuarios WHERE id = ?")
                    .setParameter(1, userId)
                    .uniqueResult()).longValue();
        }
    }

    private void actualizarTiempoJugado() {
        if (usuarioActual != null) {
            if (SessionTimer.getInstance().isRunning()) {
                // Tiempo en sesión activa (actual + acumulado)
                long tiempoTotal = SessionTimer.getInstance().getElapsedTime();
                lblTiempoJugado.setText(formatMillisToHHMM(tiempoTotal));
            } else {
                // Tiempo histórico desde BD
                lblTiempoJugado.setText(usuarioActual.getHorasJugadasFormato());
            }
        }
    }

    @FXML
    private void visualizarImagen() {
        try {
            musicManager.playRandomSoundEffect();

            btnIconosPerfil.setVisible(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/Panels/SelectImages.fxml"));
            AnchorPane panel = loader.load();

            SelectImagesController controller = loader.getController();
            controller.setOnImageSelected(nombreImagen -> {
                usuarioActual.setImagen(nombreImagen);
                cargarImagenPerfil();
                contenedorImagenes.setVisible(false);
                btnIconosPerfil.setVisible(true);
            });

            contenedorImagenes.getChildren().setAll(panel);
            contenedorImagenes.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
private void cargarImagenesPremios() {
    cargarImagen(premiohistoria, "/Images/Premios/Historia/hnegro.png");
    cargarImagen(premiobiologia, "/Images/Premios/Biologia/bnegro.png");
    cargarImagen(premiofilosofia, "/Images/Premios/Filosofia/fnegro.png");
    cargarImagen(premioliteratura, "/Images/Premios/Literatura/lnegro.png");
}
private void cargarImagen(ImageView imageView, String ruta) {
    try {
        InputStream inputStream = getClass().getResourceAsStream(ruta);
        if (inputStream != null) {
            Image image = new Image(inputStream);
            imageView.setImage(image);
        } else {
            System.err.println("No se encontró la imagen en la ruta: " + ruta);
        }
    } catch (Exception e) {
        System.err.println("Error al cargar la imagen: " + e.getMessage());
    }
}



}
