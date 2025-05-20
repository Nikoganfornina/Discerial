package org.example.discerial.Controladores.Preguntas;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.discerial.DAO.IEstadoUsuarioImpl;
import org.example.discerial.DAO.IusuariosImpl;
import org.example.discerial.entities.EstadoUsuario;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ResultadoTestViewController {

    @FXML private Label lblFallos;
    @FXML private Label lblAciertos;
    @FXML private Label lblMensaje;
    @FXML private Button btnVolverMenu;

    private final IEstadoUsuarioImpl estadoDao = new IEstadoUsuarioImpl();
    private final IusuariosImpl usuarioDao = new IusuariosImpl();

    public void initData(List<Pregunta> preguntasRespondidas) {
        Usuarios usuario = usuarioDao.currentUser();
        if (usuario == null) return;

        List<EstadoUsuario> estados = estadoDao.findByUsuario(usuario.getId());

        int[] contador = new int[2]; // contador[0] = aciertos, contador[1] = fallos

        List<String> resultados = preguntasRespondidas.stream().map(p -> {
            boolean acertada = estados.stream()
                    .filter(e -> e.getPregunta().getId() == p.getId())
                    .findFirst()
                    .map(EstadoUsuario::isAcertada)
                    .orElse(false);

            if (acertada) contador[0]++;
            else contador[1]++;

            return p.getPregunta() + " --> " + (acertada ? "✅ Correcta" : "❌ Incorrecta");
        }).collect(Collectors.toList());

        int aciertos = contador[0];
        int fallos = contador[1];

        lblAciertos.setText(String.valueOf(aciertos));
        lblFallos.setText(String.valueOf(fallos));

        String mensaje;
        int total = aciertos + fallos;
        double porcentaje = total > 0 ? (aciertos * 100.0 / total) : 0;

        if (porcentaje == 100) {
            mensaje = "¡Perfecto! Has acertado todas las preguntas.";
        } else if (porcentaje >= 75) {
            mensaje = "Muy bien, buen trabajo. Sigue así.";
        } else if (porcentaje >= 50) {
            mensaje = "No está mal, pero puedes mejorar.";
        } else {
            mensaje = "Necesitas practicar más. ¡Ánimo!";
        }
        lblMensaje.setText(mensaje);
    }

    @FXML
    private void handleVolverMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/discerial/Tabula_view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnVolverMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
