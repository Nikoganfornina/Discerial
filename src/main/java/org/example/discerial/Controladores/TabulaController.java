package org.example.discerial.Controladores;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        // 2) Asegurar todas las categorías
        ICategoria categoriaDao = new ICategoriaImpl();
        List<Categoria> todasCats = categoriaDao.findAll();
        for (Categoria cat : todasCats) {
            stats.putIfAbsent(cat.getNombre(), new int[]{0, 0});
        }

        // 3) Configurar ejes con estilo
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Categoría");
        xAxis.setTickLabelRotation(45);
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 12));

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cantidad");
        yAxis.setTickUnit(5);
        yAxis.setMinorTickVisible(false);

        // 4) Crear gráfico con estilos
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Progreso de Aprendizaje");
        chart.setStyle("-fx-background-color: #F8F9FA; -fx-font-family: 'Arial';");
        chart.setCategoryGap(20);
        chart.setBarGap(5);
        chart.setLegendSide(Side.RIGHT);
        chart.setAnimated(true);

        // 5) Configurar series
        XYChart.Series<String, Number> serieAciertos = new XYChart.Series<>();
        serieAciertos.setName("✅ Aciertos");
        XYChart.Series<String, Number> serieFallos = new XYChart.Series<>();
        serieFallos.setName("❌ Fallos");

        // 6) Rellenar datos manteniendo orden
        Map<String, int[]> ordered = new LinkedHashMap<>(stats);
        ordered.forEach((cat, arr) -> {
            serieAciertos.getData().add(new XYChart.Data<>(cat, arr[0]));
            serieFallos.getData().add(new XYChart.Data<>(cat, arr[1]));
        });

        chart.getData().addAll(serieAciertos, serieFallos);

        // 7) Personalizar colores y tooltips después de renderizar
        Platform.runLater(() -> {
            // Colores diferenciados
            String estiloAciertos = "-fx-bar-fill: #2E8540;";
            String estiloFallos = "-fx-bar-fill: #CC0000;";

            for (XYChart.Data<String, Number> data : serieAciertos.getData()) {
                data.getNode().setStyle(estiloAciertos);
                configurarTooltip(data, "Aciertos");
            }

            for (XYChart.Data<String, Number> data : serieFallos.getData()) {
                data.getNode().setStyle(estiloFallos);
                configurarTooltip(data, "Fallos");
            }

            // Estilo leyenda
            Node leyenda = chart.lookup(".chart-legend");
            if (leyenda != null) {
                leyenda.setStyle("-fx-background-color: #E9ECEF; -fx-padding: 10;");
            }
        });

        // 8) Configurar responsividad
        chart.prefWidthProperty().bind(chartContainer.widthProperty());
        chart.prefHeightProperty().bind(chartContainer.heightProperty().subtract(20));

        // 9) Añadir al contenedor
        chartContainer.getChildren().setAll(chart);
    }

    private void configurarTooltip(XYChart.Data<String, Number> data, String tipo) {
        Tooltip tooltip = new Tooltip(
                String.format("%s\n%s: %d",
                        data.getXValue(),
                        tipo,
                        data.getYValue().intValue())
        );
        tooltip.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        Tooltip.install(data.getNode(), tooltip);
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
    @FXML
    private void FxmlTabula() throws IOException {
        switchScene("/org/example/discerial/Tabula_view.fxml");
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
