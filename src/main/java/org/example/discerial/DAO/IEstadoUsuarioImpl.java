package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.EstadoUsuario;
import org.example.discerial.entities.Pregunta;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IEstadoUsuarioImpl implements IEstadoUsuario {

    /**
     * Guarda o actualiza un EstadoUsuario para evitar duplicados para el mismo usuario y pregunta.
     */
    @Override
    public EstadoUsuario save(EstadoUsuario eu) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Busca si ya existe un EstadoUsuario para ese usuario y pregunta
            EstadoUsuario existente = session.createQuery(
                            "FROM EstadoUsuario eu WHERE eu.usuario.id = :uid AND eu.pregunta.id = :pid", EstadoUsuario.class)
                    .setParameter("uid", eu.getUsuario().getId())
                    .setParameter("pid", eu.getPregunta().getId())
                    .uniqueResult();

            if (existente != null) {
                // Actualiza el registro existente
                existente.setAcertada(eu.isAcertada());
                existente.setFecha(eu.getFecha() != null ? eu.getFecha() : existente.getFecha());
                session.update(existente);
                eu = existente;
            } else {
                // No existe, persistimos nuevo
                session.persist(eu);
            }

            tx.commit();
            return eu;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Integer> getIdsPreguntasNoRespondidas(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p.id FROM Pregunta p " +
                    "WHERE p.id NOT IN (" +
                    "   SELECT eu.pregunta.id FROM EstadoUsuario eu WHERE eu.usuario.id = :uid" +
                    ")";
            return session.createQuery(hql, Integer.class)
                    .setParameter("uid", userId)
                    .getResultList();
        }
    }

    public List<Pregunta> obtenerPreguntasErroneasPorCategoria(int userId, int categoriaId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT eu.pregunta FROM EstadoUsuario eu " +
                    "WHERE eu.usuario.id = :uid AND eu.pregunta.categoria.id = :catId AND eu.acertada = false";

            return session.createQuery(hql, Pregunta.class)
                    .setParameter("uid", userId)
                    .setParameter("catId", categoriaId)
                    .getResultList();
        }
    }


    public List<Integer> getIdsPreguntasRespondidasErroneamente(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT eu.pregunta.id FROM EstadoUsuario eu " +
                    "WHERE eu.usuario.id = :uid AND eu.acertada = false";
            return session.createQuery(hql, Integer.class)
                    .setParameter("uid", userId)
                    .getResultList();
        }
    }

    public int getPreguntasAcertadasPorCategoria(Long usuarioId, String nombreCategoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(e) FROM EstadoUsuario e " +
                    "WHERE e.usuario.id = :usuarioId " +
                    "AND e.pregunta.categoria.nombre = :nombreCategoria " +
                    "AND e.acertada = true";

            Long count = session.createQuery(hql, Long.class)
                    .setParameter("usuarioId", usuarioId)
                    .setParameter("nombreCategoria", nombreCategoria)
                    .uniqueResult();

            return count == null ? 0 : count.intValue();
        }
    }

    public String getCategoriaFavorita(int userId) {
        Map<String, int[]> estadisticas = getEstadisticasPorCategoria(userId);

        String categoriaFavorita = null;
        int maxAciertos = -1;

        for (Map.Entry<String, int[]> entry : estadisticas.entrySet()) {
            int aciertos = entry.getValue()[0];
            if (aciertos > maxAciertos) {
                maxAciertos = aciertos;
                categoriaFavorita = entry.getKey();
            }
        }

        return categoriaFavorita;  // puede devolver null si no hay datos
    }

    public List<EstadoUsuario> findByUsuario(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM EstadoUsuario eu WHERE eu.usuario.id = :uid", EstadoUsuario.class)
                    .setParameter("uid", userId)
                    .getResultList();
        }
    }

    @Override
    public Map<String,int[]> getEstadisticasPorCategoria(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            @SuppressWarnings("unchecked")
            List<Object[]> rows = session.createQuery(
                            "SELECT eu.pregunta.categoria.nombre, " +
                                    "SUM(CASE WHEN eu.acertada = true THEN 1 ELSE 0 END), " +
                                    "SUM(CASE WHEN eu.acertada = false THEN 1 ELSE 0 END) " +
                                    "FROM EstadoUsuario eu " +
                                    "WHERE eu.usuario.id = :uid " +
                                    "GROUP BY eu.pregunta.categoria.nombre")
                    .setParameter("uid", userId)
                    .list();

            Map<String,int[]> stats = new LinkedHashMap<>();
            for (Object[] row : rows) {
                String categoria = (String) row[0];
                int aciertos = ((Number) row[1]).intValue();
                int fallos = ((Number) row[2]).intValue();
                stats.put(categoria, new int[]{aciertos, fallos});
            }
            return stats;
        }
    }
}
