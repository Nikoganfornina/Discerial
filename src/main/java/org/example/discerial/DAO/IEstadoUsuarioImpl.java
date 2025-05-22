// src/main/java/org/example/discerial/DAO/IEstadoUsuarioImpl.java
package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.EstadoUsuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IEstadoUsuarioImpl implements IEstadoUsuario {

    @Override
    public EstadoUsuario save(EstadoUsuario eu) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(eu);
            tx.commit();
            return eu;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
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
                                    "GROUP BY eu.pregunta.categoria.nombre"
                    )
                    .setParameter("uid", userId)
                    .list();

            Map<String,int[]> stats = new LinkedHashMap<>();
            for (Object[] row : rows) {
                String categoria = (String) row[0];
                int aciertos = ((Number) row[1]).intValue();
                int fallos   = ((Number) row[2]).intValue();
                stats.put(categoria, new int[]{ aciertos, fallos });
            }
            return stats;
        }
    }
}
