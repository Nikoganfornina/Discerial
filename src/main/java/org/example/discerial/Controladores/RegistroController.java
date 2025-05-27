package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.discerial.DAO.IAjustesUsuario;
import org.example.discerial.DAO.IAjustesUsuarioImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.Util.SessionTimer;
import org.example.discerial.Util.TiempoPreguntas;
import org.example.discerial.entities.AjustesUsuario;
import org.example.discerial.entities.Usuarios;


import static org.example.discerial.Util.SessionManager.switchScene;


public class RegistroController {

    MusicManager musicManager = MusicManager.getInstance();
    @FXML
    public  TextField RegistroNombre , RegistroNickname , RegistroCorreo , RegistroContrasena , RegistroConfirmarContrasena;
    IAjustesUsuario ajustesDao = new IAjustesUsuarioImpl(HibernateUtil.getSessionFactory()); // Añade esto

    public void handleClick(MouseEvent event) throws Exception {
        musicManager.playRandomSoundEffect();

        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void initialize() {
        RegistroNombre.setFocusTraversable(false);
        RegistroCorreo.setFocusTraversable(false);
        RegistroContrasena.setFocusTraversable(false);
        RegistroNickname.setFocusTraversable(false);
        RegistroConfirmarContrasena.setFocusTraversable(false);
    }
    private void limpiarCampos() {
        RegistroNombre.clear();
        RegistroNickname.clear();
        RegistroCorreo.clear();
        RegistroContrasena.clear();
        RegistroConfirmarContrasena.clear();
    }

    public void AgregarUsuario() {
        IusuariosImpl dao = new IusuariosImpl();

        String nombre = RegistroNombre.getText().trim();
        String nickname = RegistroNickname.getText().trim();
        String correo = RegistroCorreo.getText().trim();
        String contrasena = RegistroContrasena.getText().trim();
        String confirmarContrasena = RegistroConfirmarContrasena.getText().trim();

        try {
            // Validación manual
            if (nombre.isEmpty() || nickname.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios");
            }
            if (!correo.contains("@")) {
                throw new IllegalArgumentException("El correo debe contener una @");
            }
            if (!contrasena.equals(confirmarContrasena)) {
                throw new IllegalArgumentException("Las contraseñas no coinciden");
            }

            // Crear y guardar usuario
            Usuarios usuario = new Usuarios(nombre, nickname, correo, contrasena, 0, 0, "hombre2.jpg", false, 0);
            Usuarios usuarioGuardado = dao.save(usuario);

            if (usuarioGuardado != null) {
                musicManager.playRandomSoundEffect();
                AjustesUsuario ajustes = new AjustesUsuario(
                        true,   // efectosActivados
                        true,   // musicaActivada
                        100,    // nivelMusica (100% por defecto)
                        TiempoPreguntas.SEGUNDOS_20,
                        usuarioGuardado  // Usuario recién creado
                );

                ajustesDao.actualizarAjustes(ajustes); // Guardar ajustes
                limpiarCampos();
                switchScene("/org/example/discerial/InicioSesion_View.fxml");
            }

        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            showAlert("Error técnico", "Error al registrar: " + e.getMessage());
        }
    }

    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}

