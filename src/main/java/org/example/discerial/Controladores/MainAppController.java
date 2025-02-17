package org.example.discerial.Controladores;

import static org.example.discerial.MainApp.switchScene;

public class MainAppController {

    public void switchToInicioSesion() throws Exception {
        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }
}
