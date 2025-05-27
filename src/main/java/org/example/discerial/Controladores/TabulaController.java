package org.example.discerial.Controladores;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.discerial.DAO.*;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.Util.SessionTimer;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;
import org.example.discerial.entities.Usuarios;

import java.io.IOException;
import java.util.*;

import static org.example.discerial.Controladores.CategoriasJuegoController.openGameWithWrongQuestions;
import static org.example.discerial.Util.SessionManager.switchScene;

public class TabulaController {

    @FXML
    private Pane contenedorFXML;
    @FXML
    private Label usuarioNombre;
    @FXML
    private Label lblAciertosNumero;
    private final IusuariosImpl usuarioDao = new IusuariosImpl();
    private final IPreguntaImpl preguntaDao = new IPreguntaImpl(); // DAO de preguntas
    private final ICategoriaImpl categoriaDao = new ICategoriaImpl(); // DAO de categoria
    private final IEstadoUsuarioImpl estadoDao = new IEstadoUsuarioImpl();

    @FXML
    private VBox vboxFallos;
    // Este VBox debe estar declarado en tu FXML dentro de contenedorFXML
    @FXML
    private VBox chartContainer;

    @FXML
    public void initialize() {
        mostrarNombreUsuario();
        cargarGraficaAvance();
        cargarBotonesFallos();

    }



    private void cargarBotonesFallos() {
        vboxFallos.getChildren().clear();

        int userId = usuarioDao.currentUser().getId();
        Map<Integer, Long> fallosPorCategoria = preguntaDao.getFallosPorCategoria(userId); // categoríaId -> número fallos

        for (Map.Entry<Integer, Long> entry : fallosPorCategoria.entrySet()) {
            int categoriaId = entry.getKey();
            long numFallos = entry.getValue();

            Categoria categoria = categoriaDao.findById(categoriaId); // Obtenemos datos categoría

            if (categoria != null && numFallos > 0) {
                Button button = new Button(categoria.getNombre() + " (" + numFallos + ")");

                // Estilo verde #4c5b3d y bordes redondeados
                button.setStyle(
                        "-fx-background-color: #4c5b3d; " +
                                "-fx-text-fill: white; " +
                                "-fx-background-radius: 15; " +
                                "-fx-font-weight: bold; " +
                                "-fx-padding: 10 20 10 20;"
                );

                button.setOnAction(e -> {
                    List<Pregunta> preguntasErroneas = estadoDao.obtenerPreguntasErroneasPorCategoria(userId, categoriaId);
                    if (preguntasErroneas != null && !preguntasErroneas.isEmpty()) {
                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        openGameWithWrongQuestions(stage, categoriaId, preguntasErroneas);
                    } else {
                        System.out.println("No hay preguntas falladas en esta categoría.");
                    }
                });

                vboxFallos.getChildren().add(button);
            }
        }

        if (vboxFallos.getChildren().isEmpty()) {
            vboxFallos.getChildren().add(new Label("No hay preguntas falladas aún."));
        }
    }


    private void mostrarNombreUsuario() {
        Usuarios user = usuarioDao.currentUser();
        usuarioNombre.setText(usuarioDao.currentUser().getNombre());

        if (user != null) {
            // Obtener aciertos del usuario
            int aciertos = usuarioDao.getPreguntasAcertadas(user.getId());

            // Obtener total de preguntas en la BD
            int totalPreguntas = preguntaDao.countTotalPreguntas();

            // Actualizar el texto del Label
            lblAciertosNumero.setText(aciertos + "/" + totalPreguntas);

        } else {
            lblAciertosNumero.setText("0/0");
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

        // 3) Calcular el valor máximo entre aciertos y fallos para ajustar el rango Y
        int maxValor = 0;
        for (int[] arr : stats.values()) {
            maxValor = Math.max(maxValor, Math.max(arr[0], arr[1]));
        }

        // Redondear maxValor hacia arriba al siguiente múltiplo de 5
        int maxEjeY = ((maxValor + 4) / 5) * 5;
        if (maxEjeY == 0) maxEjeY = 5; // evitar rango 0 para que se vea el eje

        // 4) Configurar ejes con estilo
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickLabelRotation(45);
        xAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 16)); // Texto más grande

        NumberAxis yAxis = new NumberAxis(0, maxEjeY, 5);
        yAxis.setMinorTickVisible(false);
        yAxis.setTickLabelFont(Font.font("Arial", FontWeight.BOLD, 16)); // Texto más grande

        // 5) Crear gráfico con fondo totalmente transparente
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);
        chart.setAnimated(true);
        chart.setCategoryGap(20);
        chart.setBarGap(5);

        // Aplicar estilos directamente al gráfico y fondo interior
        chart.setStyle("-fx-background-color: transparent;");
        Node plotBackground = chart.lookup(".chart-plot-background");
        if (plotBackground != null) {
            plotBackground.setStyle("-fx-background-color: transparent;");
        }

        // Aumentar tamaño del título
        Label title = new Label("Progreso de Aprendizaje");
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 24));
        title.setPadding(new Insets(10, 0, 20, 0));
        title.setTextAlignment(TextAlignment.CENTER);

        // 6) Configurar series
        XYChart.Series<String, Number> serieAciertos = new XYChart.Series<>();
        XYChart.Series<String, Number> serieFallos = new XYChart.Series<>();

        // 7) Rellenar datos manteniendo orden
        Map<String, int[]> ordered = new LinkedHashMap<>(stats);
        ordered.forEach((cat, arr) -> {
            serieAciertos.getData().add(new XYChart.Data<>(cat, arr[0]));
            serieFallos.getData().add(new XYChart.Data<>(cat, arr[1]));
        });

        chart.getData().addAll(serieAciertos, serieFallos);

        // 8) Usar PauseTransition para esperar renderizado antes de personalizar colores, redondear barras y leyenda
        PauseTransition pause = new PauseTransition(Duration.millis(250));
        pause.setOnFinished(event -> {
            String colorAciertos = "#2E8540";
            String colorFallos = "#CC0000";

            // Colorear y redondear barras, y añadir tooltips
            for (XYChart.Data<String, Number> data : serieAciertos.getData()) {
                Node node = data.getNode();
                if (node != null) {
                    node.setStyle(
                            "-fx-bar-fill: " + colorAciertos + ";" +
                                    "-fx-background-radius: 10 10 0 0;" +  // redondeo arriba solo
                                    "-fx-border-radius: 10 10 0 0;"
                    );
                    configurarTooltip(data, "Aciertos");
                }
            }

            for (XYChart.Data<String, Number> data : serieFallos.getData()) {
                Node node = data.getNode();
                if (node != null) {
                    node.setStyle(
                            "-fx-bar-fill: " + colorFallos + ";" +
                                    "-fx-background-radius: 10 10 0 0;" +
                                    "-fx-border-radius: 10 10 0 0;"
                    );
                    configurarTooltip(data, "Fallos");
                }
            }

            // Líneas guía visibles
            chart.setHorizontalGridLinesVisible(true);
            chart.setVerticalGridLinesVisible(true);
        });
        pause.play();

        // 9) Responsividad
        chart.prefWidthProperty().bind(chartContainer.widthProperty());
        chart.prefHeightProperty().bind(chartContainer.heightProperty().subtract(20));

        // 10) Añadir al contenedor con título separado
        VBox contenedor = new VBox(title, chart);
        contenedor.setSpacing(10);
        chartContainer.getChildren().setAll(contenedor);
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
            MusicManager.getInstance().playRandomSoundEffect();

            //switchScene("/org/example/discerial/VistaGameController.fxml");
            switchScene("/org/example/discerial/CategoriasJuego_View.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void FxmlHomoPanel() {
        try {
            MusicManager.getInstance().playRandomSoundEffect();

            Parent pnl = FXMLLoader
                    .load(getClass().getResource("/org/example/discerial/Panels/HomoPanel_view.fxml"));
            contenedorFXML.getChildren().setAll(pnl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void FxmlTabula() throws IOException {
        MusicManager.getInstance().playRandomSoundEffect();
        switchScene("/org/example/discerial/Tabula_view.fxml");
    }

    @FXML
    private void FxmlNuntiato() throws IOException {
        MusicManager.getInstance().playRandomSoundEffect();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vista no disponible");
        alert.setHeaderText(null);
        alert.setContentText("Mantengase a la espera, estamos trabajando en la implementacion de esta seccion.");
        alert.showAndWait();
        //switchScene("/org/example/discerial/Nuntiato_view.fxml");

    }

    @FXML
    private void FxmlAdaptationes() throws IOException {
        MusicManager.getInstance().playRandomSoundEffect();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vista no disponible");
        alert.setHeaderText(null);
        alert.setContentText("Mantengase a la espera, estamos trabajando en la implementacion de esta seccion.");
        alert.showAndWait();
        //switchScene("/org/example/discerial/Adaptationes_view.fxml");
    }

    @FXML
    private void FxmlAuxilium() throws IOException {
        MusicManager.getInstance().playRandomSoundEffect();
        try {
            MusicManager.getInstance().playRandomSoundEffect();

            Parent pnl = FXMLLoader
                    .load(getClass().getResource("/org/example/discerial/Panels/Auxilium_View.fxml"));
            contenedorFXML.getChildren().setAll(pnl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void BotoncerrarSesion() throws IOException {
        IusuariosImpl dao = new IusuariosImpl();
        Usuarios u = dao.currentUser();
        MusicManager.getInstance().playRandomSoundEffect();

        if (u == null) {
            new Alert(Alert.AlertType.INFORMATION, "No hay ningún usuario conectado.")
                    .showAndWait();
            switchScene("/org/example/discerial/MainApp_View.fxml");
            return;
        }

        Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
        conf.setTitle("Cerrar sesión");
        conf.setHeaderText(null);
        conf.setContentText("¿Deseas cerrar sesión de la cuenta: " + u.getNombre() + "?");
        conf.getButtonTypes().setAll(
                new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE),
                new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE)
        );

        conf.showAndWait().ifPresent(response -> {
            if (response.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                try {
                    SessionTimer timer = SessionTimer.getInstance();
                    timer.stop(); // Detiene el timer y guarda el tiempo

                    // Cerrar sesión en BD
                    dao.cerrarSesionSoloSessionActive(u.getId());

                    // Forzar liberación de recursos
                    HibernateUtil.shutdown();

                    switchScene("/org/example/discerial/MainApp_View.fxml");

                    // Verificación
                    System.out.println("Timer detenido. Estado: " + timer.isRunning());

                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Error al cerrar sesión: " + e.getMessage()).show();
                }
            }
        });
    }
}