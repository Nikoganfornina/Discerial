package org.example.discerial.Controladores;

import org.example.discerial.DAO.ICategoriaImpl;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Categoria;
import org.hibernate.Session;

import static org.example.discerial.Util.SessionManager.switchScene;

public class MainAppController {

    public void switchToInicioSesion() throws Exception {
        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void irCrudPreguntas() throws Exception {
        switchScene("/org/example/discerial/CrudPreguntas.fxml");
    }


}