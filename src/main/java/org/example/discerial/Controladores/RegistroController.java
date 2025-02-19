package org.example.discerial.Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.discerial.Sqlite.ConexionSQLite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.discerial.MainApp.switchScene;

public class RegistroController {

    @FXML private TextField RegistroNombre;

    @FXML public TextField RegistroNickname;

    @FXML private TextField RegistroCorreo;

    @FXML private  TextField  RegistroContrasena ;

    @FXML public TextField RegistroConfirmarContrasena;

    public void handleClick(MouseEvent event) throws Exception {
        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void initialize() {
        RegistroNombre.setFocusTraversable(false);
        RegistroCorreo.setFocusTraversable(false);
        RegistroContrasena.setFocusTraversable(false);
        RegistroNickname.setFocusTraversable(false);
        RegistroConfirmarContrasena.setFocusTraversable(false);
    } // Método para comprobar si el nickname ya existe en la base de datos
    private boolean nicknameExistente(String nickname) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE nickname = ?";
        try (Connection connection = ConexionSQLite.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nickname);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Si el resultado es mayor que 0, el nickname existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // El nickname no existe
    }

    // Método para crear una cuenta de usuario
    @FXML
    private void crearCuenta(ActionEvent event) {
        String nombre = RegistroNombre.getText();
        String nickname = RegistroNickname.getText();
        String correo = RegistroCorreo.getText();
        String contraseña = RegistroContrasena.getText();

        // Validar si el nickname ya existe
        if (nicknameExistente(nickname)) {
            mostrarAlerta(AlertType.ERROR, "Error", "El nickname ya está en uso");
            return;
        }

        // Si todo está bien, se crea el nuevo usuario en la base de datos
        String query = "INSERT INTO usuarios (nombre, nickname, correo, contraseña) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConexionSQLite.conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nombre);
            statement.setString(2, nickname);
            statement.setString(3, correo);
            statement.setString(4, contraseña);

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                mostrarAlerta(AlertType.INFORMATION, "Cuenta creada", "La cuenta se ha creado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Error", "Hubo un error al crear la cuenta");
        }
    }

    // Método para mostrar una alerta
    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}

