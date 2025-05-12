package org.example.discerial.Controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.discerial.DAO.*;
import org.example.discerial.Util.SessionManager;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.example.discerial.Util.SessionManager.switchScene;

public class TabulaController {

    @FXML private Pane contenedorFXML;
    @FXML private ImageView usuarioImagen;
    @FXML private Label usuarioNombre;

    // Este VBox debe estar declarado en tu FXML dentro de contenedorFXML
    @FXML private VBox chartContainer;

    @FXML
    public void initialize() {
        mostrarNombreUsuario();
        cargarGraficaAvance();
    }

    private void mostrarNombreUsuario() {
        Usuarios user = new IusuariosImpl().currentUser();
        if (user != null && user.getNombre() != null && !user.getNombre().isBlank()) {
            String cap = user.getNombre().substring(0,1).toUpperCase() + user.getNombre().substring(1);
            usuarioNombre.setText(cap);
        } else {
            usuarioNombre.setText("Sin usuario activo");
        }
    }

    private void cargarGraficaAvance() {
        // 1) Consulta al DAO de EstadoUsuario las estadísticas por categoría
        IEstadoUsuario estadoDao = new IEstadoUsuarioImpl();
        int userId = new IusuariosImpl().currentUser().getId();
        Map<String, int[]> stats = estadoDao.getEstadisticasPorCategoria(userId);

        // 2) Asegúrate de incluir todas las categorías (aunque estén a cero)
        ICategoria categoriaDao = new ICategoriaImpl();
        List<Categoria> todasCats = categoriaDao.findAll();
        for (Categoria cat : todasCats) {
            stats.putIfAbsent(cat.getNombre(), new int[]{0, 0});
        }

        // 3) Crea ejes y gráfico de barras
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis   = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Avance por categoría");
        xAxis.setLabel("Categoría");
        yAxis.setLabel("Cantidad");

        // 4) Dos series: aciertos y fallos
        XYChart.Series<String, Number> serieAciertos = new XYChart.Series<>();
        serieAciertos.setName("Aciertos");
        XYChart.Series<String, Number> serieFallos    = new XYChart.Series<>();
        serieFallos.setName("Fallos");

        // 5) Rellena las series con los datos del Map (preserva orden de inserción)
        Map<String, int[]> ordered = new LinkedHashMap<>(stats);
        ordered.forEach((cat, arr) -> {
            serieAciertos.getData().add(new XYChart.Data<>(cat, arr[0]));
            serieFallos   .getData().add(new XYChart.Data<>(cat, arr[1]));
        });

        chart.getData().addAll(serieAciertos, serieFallos);

        // 6) Que ocupe todo el VBox (chartContainer)
        chart.prefWidthProperty().bind(chartContainer.widthProperty());
        chart.prefHeightProperty().bind(chartContainer.heightProperty());

        // 7) Inserta el gráfico
        chartContainer.getChildren().setAll(chart);
    }

    @FXML
    private void handleJugar() {
        try {
            //switchScene("/org/example/discerial/VistaGameController.fxml");
            switchScene("/org/example/discerial/CategoriasJuego_View.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void FxmlHomoPanel() {
        try {
            Parent pnl = FXMLLoader
                    .load(getClass().getResource("/org/example/discerial/Panels/HomoPanel_view.fxml"));
            contenedorFXML.getChildren().setAll(pnl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BotoncerrarSesion() throws IOException {
        var dao = new IusuariosImpl();
        Usuarios u = dao.currentUser();
        if (u == null) {
            new Alert(Alert.AlertType.INFORMATION, "No hay ningún usuario conectado.")
                    .showAndWait();
            switchScene("/org/example/discerial/MainApp_View.fxml");
            return;
        }

        Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
        conf.setTitle("Cerrar sesión");
        conf.setHeaderText(null);
        conf.setContentText("¿Deseas cerrar sesión de la cuenta: "+u.getNombre()+"?");
        conf.getButtonTypes().setAll(
                new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE)
        );

        Optional<ButtonType> res = conf.showAndWait();
        if (res.orElse(ButtonType.CANCEL).getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            dao.cerrarSesion(u.getId());
            switchScene("/org/example/discerial/MainApp_View.fxml");
        }
    }
}
