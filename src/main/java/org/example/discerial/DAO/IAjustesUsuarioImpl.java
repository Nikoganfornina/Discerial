package org.example.discerial.DAO;

import org.example.discerial.entities.AjustesUsuario;
import org.example.discerial.entities.Usuarios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class IAjustesUsuarioImpl implements IAjustesUsuario {

    private final SessionFactory sessionFactory;

    public IAjustesUsuarioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public AjustesUsuario getAjustesByUsuarioId(int usuarioId) {
        try (Session session = sessionFactory.openSession()) {
            AjustesUsuario ajustes = session.createQuery(
                            "FROM AjustesUsuario WHERE usuario.id = :usuarioId",
                            AjustesUsuario.class
                    )
                    .setParameter("usuarioId", usuarioId)
                    .uniqueResult();

            // Si no existe, crear uno nuevo
            if (ajustes == null) {
                ajustes = new AjustesUsuario();
                ajustes.setUsuario(session.get(Usuarios.class, usuarioId));
                session.beginTransaction();
                session.persist(ajustes);
                session.getTransaction().commit();
            }

            return ajustes;
        }
    }
    @Override
    public void actualizarAjustes(AjustesUsuario ajustes) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(ajustes);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al actualizar ajustes: " + e.getMessage());
        }
    }
}