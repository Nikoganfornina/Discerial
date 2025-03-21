package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Pregunta;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Implementación de la interfaz IPregunta para gestionar operaciones CRUD en la entidad Pregunta.
 * Utiliza Hibernate para interactuar con la base de datos.
 */
public class IPreguntaImpl implements IPregunta {

    /**
     * Obtiene todas las preguntas de la base de datos.
     * @return Lista de todas las preguntas.
     */

    @Override
    public List<Pregunta> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Pregunta> preguntaList = session.createQuery("from Pregunta", Pregunta.class).list();
        session.close();
        return preguntaList;
    }

    /**
     * Busca preguntas por su ID.
     * @param id Identificador único de la pregunta.
     * @return Lista con la(s) pregunta(s) encontradas.
     */
    @Override
    public List<Pregunta> findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Pregunta> preguntaList = session.createQuery("from Pregunta where id = :id", Pregunta.class)
                .setParameter("id", id)
                .list();
        session.close();
        return preguntaList;
    }

    /**
     * Busca preguntas por el ID de la categoría.
     * @param categoriaId Identificador de la categoría.
     * @return Lista de preguntas que pertenecen a la categoría indicada.
     */
    @Override
    public List<Pregunta> findByCategoria(int categoriaId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Pregunta> preguntaList = session.createQuery("from Pregunta where categoria.id = :categoriaId", Pregunta.class)
                .setParameter("categoriaId", categoriaId)
                .list();
        session.close();
        return preguntaList;
    }

    /**
     * Guarda una nueva pregunta en la base de datos.
     * @param pregunta Objeto Pregunta a guardar.
     * @return La pregunta guardada con su ID generado.
     */
    @Override
    public Pregunta save(Pregunta pregunta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(pregunta);
        session.getTransaction().commit();
        session.close();
        return pregunta;
    }

    /**
     * Actualiza la información de una pregunta existente.
     * @param pregunta Objeto Pregunta con los nuevos datos.
     * @return La pregunta actualizada.
     */
    @Override
    public Pregunta update(Pregunta pregunta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(pregunta);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return pregunta;
    }

    /**
     * Elimina una pregunta por su ID.
     * @param id Identificador único de la pregunta a eliminar.
     * @return La pregunta eliminada o null si no se encontró.
     */
    @Override
    public Pregunta deleteById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Pregunta pregunta = session.get(Pregunta.class, id);
        if (pregunta != null) {
            session.delete(pregunta);
            transaction.commit();
        }
        session.close();
        return pregunta;
    }
}
