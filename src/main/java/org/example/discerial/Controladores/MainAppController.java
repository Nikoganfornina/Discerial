package org.example.discerial.Controladores;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.example.discerial.Controladores.Preguntas.PreguntasBiologia;
import org.example.discerial.Controladores.Preguntas.PreguntasFilosofia;
import org.example.discerial.Controladores.Preguntas.PreguntasHistoria;
import org.example.discerial.Controladores.Preguntas.PreguntasLiteratura;
import org.example.discerial.DAO.ICategoriaImpl;
import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Categoria;
import org.hibernate.Session;

import static org.example.discerial.Util.SessionManager.switchScene;

public class MainAppController {

    @FXML
    private AnchorPane root;

    public void switchToInicioSesion() throws Exception {
        switchScene("/org/example/discerial/InicioSesion_View.fxml");
    }

    public void irCrudPreguntas() throws Exception {
        switchScene("/org/example/discerial/CrudPreguntas.fxml");
    }

    @FXML
    public void initialize() {
        crearCategorias();
        PreguntasFilosofia.crearPreguntasFilosofia();
        PreguntasHistoria.crearPreguntasHistoria();
        PreguntasBiologia.crearPreguntasBiologia();
        PreguntasLiteratura.crearPreguntasLiteratura();
    }

    /**
     * Verifica si ya existen categorías en la base de datos.
     * Si no existen, crea las categorías: Filosofía, Historia, Literatura y Biología.
     */
    private void crearCategorias() {
        // Abrir una sesión para consultar la cantidad de categorías existentes
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Long count = (Long) session.createQuery("select count(c) from Categoria c").uniqueResult();
        session.getTransaction().commit();
        session.close();

        if (count == 0) {
            ICategoriaImpl categoriaDao = new ICategoriaImpl();
            categoriaDao.save(new Categoria(1 ,"Filosofia"));
            categoriaDao.save(new Categoria(2 ,"Historia"));
            categoriaDao.save(new Categoria(3, "Literatura"));
            categoriaDao.save(new Categoria(4, "Biologia"));
        }
    }

}