package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Pregunta;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

/**
 * Implementación de la interfaz IPregunta para gestionar operaciones CRUD en la entidad Pregunta.
 * Utiliza Hibernate para interactuar con la base de datos.
 */
public class IPreguntaImpl implements IPregunta {
    private final IEstadoUsuario estadoUsuarioDAO; // Inyectar como dependencia
    public IPreguntaImpl() {
        this.estadoUsuarioDAO = new IEstadoUsuarioImpl(); // O usar inyección de dependencias
    }
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

    public List<Pregunta> findNoRespondidasPorCategoria(int categoriaId, int userId) {
        IEstadoUsuarioImpl estadoUsuarioDAO = new IEstadoUsuarioImpl();

        // Obtener IDs de preguntas no respondidas
        List<Integer> idsNoRespondidas = estadoUsuarioDAO.getIdsPreguntasNoRespondidas(userId);

        // Obtener IDs de preguntas respondidas de forma errónea
        List<Integer> idsErroneas = estadoUsuarioDAO.getIdsPreguntasRespondidasErroneamente(userId);

        // Unir ambas listas, evitando duplicados
        Set<Integer> idsAConsiderar = new HashSet<>();
        idsAConsiderar.addAll(idsNoRespondidas);
        idsAConsiderar.addAll(idsErroneas);

        if (idsAConsiderar.isEmpty()) {
            return List.of(); // No hay preguntas que cumplirían la condición
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Pregunta p WHERE p.categoria.id = :categoriaId AND p.id IN (:ids)";
            Query<Pregunta> query = session.createQuery(hql, Pregunta.class);
            query.setParameter("categoriaId", categoriaId);
            query.setParameter("ids", idsAConsiderar);

            return query.getResultList();
        }
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




    public int countTotalPreguntas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return ((Number) session.createQuery("SELECT COUNT(p) FROM Pregunta p")
                    .uniqueResult()).intValue();
        }
    }



    @Override
    public List<Pregunta> findPreguntasMixta(int userId) {
        IEstadoUsuarioImpl estadoDAO = new IEstadoUsuarioImpl();
        Map<String, int[]> stats = estadoDAO.getEstadisticasPorCategoria(userId);

        List<Integer> categoriasValidas = new ArrayList<>();
        ICategoriaImpl categoriaDAO = new ICategoriaImpl();

        for (String nombreCategoria : stats.keySet()) {
            int totalPreguntas = getTotalPreguntasCategoria(nombreCategoria);
            int[] datos = stats.get(nombreCategoria);
            int totalRespondidas = datos[0] + datos[1]; // Aciertos + Fallos

            if (totalRespondidas < totalPreguntas) {
                int idCategoria = categoriaDAO.getIdCategoriaPorNombre(nombreCategoria);
                categoriasValidas.add(idCategoria);
            }
        }

        if (categoriasValidas.isEmpty()) categoriasValidas = List.of(1, 2, 3, 4);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Pregunta p "
                    + "WHERE p.categoria.id IN (1, 2, 3, 4) "
                    + "AND p.id NOT IN ("
                    + "  SELECT eu.pregunta.id FROM EstadoUsuario eu "
                    + "  WHERE eu.usuario.id = :userId AND eu.acertada = true"
                    + ") ORDER BY RAND()";

            return session.createQuery(hql, Pregunta.class)
                    .setParameter("userId", userId)
                    .setMaxResults(15) // Trae más para seleccionar las mejores
                    .getResultList();
        }
    }

    @Override
    public int getTotalPreguntasCategoria(String nombreCategoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                            "SELECT COUNT(p) FROM Pregunta p WHERE p.categoria.nombre = :nombre", Long.class)
                    .setParameter("nombre", nombreCategoria)
                    .uniqueResult();

            return count != null ? count.intValue() : 0;
        }
    }
}