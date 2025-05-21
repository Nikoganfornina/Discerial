package org.example.discerial.Controladores;

import org.example.discerial.DAO.ICategoriaImpl;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.Util.MusicManager;
import org.example.discerial.entities.Categoria;
import org.hibernate.Session;

import static org.example.discerial.Util.SessionManager.switchScene;

public class MainAppController {

    MusicManager musicManager = new MusicManager();

    public void switchToInicioSesion() throws Exception {
        musicManager.playRandomSoundEffect();
        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void irCrudPreguntas() throws Exception {
        musicManager.playRandomSoundEffect();

        switchScene("/org/example/discerial/CrudPreguntas.fxml");

    }


}