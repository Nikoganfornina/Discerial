package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.Usuarios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.*;

import static org.example.discerial.Util.SessionManager.switchScene;

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
    public void Registro() throws IOException {
        switchScene("/org/example/discerial/Registro_View.fxml");
    }

    @FXML
    public void iniciarSesion() throws Exception {
        IusuariosImpl dao = new IusuariosImpl();
        String correo = SesionCorreo.getText();
        String contrasena = SesionContrasena.getText();

        // Llamamos al método login del DAO para validar las credenciales
        Usuarios usuario = dao.login(correo, contrasena);

        if (usuario != null) {
            // Cambiamos a la vista principal
            SessionManager.switchScene("/org/example/discerial/Tabula_view.fxml");
        } else {
            // Si las credenciales son incorrectas, mostramos un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR, "Credenciales inválidas. Por favor, inténtalo de nuevo.");
            alert.showAndWait();
        }
    }
}
