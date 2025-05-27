package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import org.example.discerial.DAO.IAjustesUsuario;
import org.example.discerial.entities.AjustesUsuario;
import org.example.discerial.entities.Usuarios;
import org.example.discerial.Util.TiempoPreguntas;

public class AdaptationesController {

    @FXML private CheckBox chkVolumen;
    @FXML private CheckBox chkEfectos;
    @FXML private Slider sliderVolumen;
    @FXML private ChoiceBox<String> choiceTiempo;
    @FXML private Button btnAplicar;

    private IAjustesUsuario ajustesDAO;
    private AjustesUsuario ajustesActuales;
    private Usuarios usuarioActual; // Debes obtenerlo de tu sistema de autenticación

    @FXML
    public void initialize() throws Exception {
        // Cargar valores del enum TiempoPreguntas en el ChoiceBox
        choiceTiempo.getItems().addAll(
                "15 segundos",
                "20 segundos",
                "30 segundos"
        );

        cargarAjustesActuales();
    }

    private void cargarAjustesActuales() throws Exception {
        if (usuarioActual != null) {
            ajustesActuales = ajustesDAO.getAjustesByUsuarioId(usuarioActual.getId());
            if (ajustesActuales != null) {
                // Sincronizar UI con valores de la base de datos
                chkVolumen.setSelected(ajustesActuales.isMusicaActivada());
                chkEfectos.setSelected(ajustesActuales.isEfectosActivados());
                sliderVolumen.setValue(ajustesActuales.getNivelMusica());
                choiceTiempo.setValue(ajustesActuales.getTiempoPreguntas().toString());
            }
        }
    }

    @FXML
    private void handleAplicarCambios() {
        if (ajustesActuales == null || usuarioActual == null) return;

        // Actualizar entidad con valores de la UI
        ajustesActuales.setMusicaActivada(chkVolumen.isSelected());
        ajustesActuales.setEfectosActivados(chkEfectos.isSelected());
        ajustesActuales.setNivelMusica((int) sliderVolumen.getValue());
        ajustesActuales.setTiempoPreguntas(
                TiempoPreguntas.fromString(choiceTiempo.getValue())
        );

        // Persistir en base de datos
        ajustesDAO.actualizarAjustes(ajustesActuales);

        // Opcional: Mostrar confirmación
        mostrarConfirmacion();
    }

    private void mostrarConfirmacion() {
        // Implementar lógica de alerta (ej: JavaFX Alert)
        System.out.println("Ajustes guardados correctamente");
    }

    // Métodos para inyección de dependencias (deben llamarse al crear el controlador)
    public void setAjustesDAO(IAjustesUsuario dao) {
        this.ajustesDAO = dao;
    }

    public void setUsuarioActual(Usuarios usuario) {
        this.usuarioActual = usuario;
    }
}