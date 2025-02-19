package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.discerial.Sqlite.ConexionSQLite;

import java.sql.*;

import static org.example.discerial.MainApp.switchScene;

public class InicioSesionController {

    @FXML
    private TextField SesionCorreo;

    @FXML
    private TextField SesionContrasena;

    @FXML
    public void initialize() {
        SesionCorreo.setFocusTraversable(false);
        SesionContrasena.setFocusTraversable(false);
    }

    @FXML
    public void handleClickIngresar(MouseEvent event) throws Exception {
        String correoOrNickname = SesionCorreo.getText();
        String contrasena = SesionContrasena.getText();

        if (correoOrNickname.isEmpty() || contrasena.isEmpty()) {
            showAlert("Error", "Por favor, complete todos los campos.", AlertType.ERROR);
            return;
        }

        // Verificar las credenciales
        if (verificarCredenciales(correoOrNickname, contrasena)) {
            // Si las credenciales son correctas, cambiar de escena
            switchScene("/org/example/discerial/Tabula_view.fxml");
        } else {
            // Si las credenciales son incorrectas, mostrar alerta
            showAlert("Error", "Credenciales incorrectas. Intenta de nuevo.", AlertType.ERROR);
        }
    }

    @FXML
    public void handleClick(MouseEvent event) throws Exception {
        switchScene("/org/example/discerial/Registro_View.fxml");
    }

    // Método para verificar las credenciales en la base de datos
    private boolean verificarCredenciales(String correoOrNickname, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE (correo = ? OR nickname = ?) AND contraseña = ?"; // Cambio aquí
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correoOrNickname); // Establecer correo o nickname
            stmt.setString(2, correoOrNickname); // Establecer correo o nickname
            stmt.setString(3, contrasena); // Establecer la contraseña

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Si hay una fila que coincide con las credenciales, devuelve true
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrió un error al conectar con la base de datos.", AlertType.ERROR);
            return false;
        }
    }

    // Método para mostrar alertas
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
