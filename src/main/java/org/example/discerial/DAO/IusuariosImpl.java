package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.Util.LoadingManager;
import org.example.discerial.entities.Usuarios;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Implementación de la interfaz Iusuarios para gestionar operaciones CRUD en la entidad Usuarios.
 * Utiliza Hibernate para interactuar con la base de datos.
 */
public class IusuariosImpl implements Iusuarios {

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Lista de todos los usuarios registrados.
     */
    @Override
    public List<Usuarios> findAll() {
        LoadingManager.showLoading();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            List<Usuarios> UsuarioList = session.createQuery("from Usuarios", Usuarios.class).list();
            session.close();
            return UsuarioList;
        } finally {
            LoadingManager.hideLoading();
        }
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id Identificador único del usuario.
     * @return Lista con el usuario encontrado (normalmente debería contener solo un elemento o estar vacía).
     */
    @Override
    public List<Usuarios> findById(int id) {
        LoadingManager.showLoading();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Usuarios> UsuarioList = session.createQuery("from Usuarios where id = :id", Usuarios.class)
                    .setParameter("id", id)
                    .list();
            session.close();
            return UsuarioList;
        } finally {
            LoadingManager.hideLoading();
        }

    }

    /**
     * Busca usuarios por su correo electrónico.
     *
     * @param correo Correo electrónico del usuario a buscar.
     * @return Lista de usuarios con el correo proporcionado.
     */
    @Override
    public List<Usuarios> findByCorreo(String correo) {
        LoadingManager.showLoading();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Usuarios> UsuarioList = session.createQuery("from Usuarios where correo = :correo", Usuarios.class)
                    .setParameter("correo", correo)
                    .list();
            session.close();
            return UsuarioList;
        } finally {
            LoadingManager.hideLoading();
        }

    }

    /**
     * Busca usuarios por su nickname.
     *
     * @param nickname Nombre de usuario a buscar.
     * @return Lista de usuarios con el nickname especificado.
     */

    @Override
    public List<Usuarios> findByNickname(String nickname) {
        LoadingManager.showLoading();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Usuarios> UsuarioList = session.createQuery("from Usuarios where nickname = :nickname", Usuarios.class)
                    .setParameter("nickname", nickname)
                    .list();
            session.close();
            return UsuarioList;
        } finally {
            LoadingManager.hideLoading();
        }

    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuarios Objeto Usuarios a guardar.
     * @return El usuario guardado con su ID generado.
     */
    @Override
    public Usuarios save(Usuarios usuarios) {
        LoadingManager.showLoading();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(usuarios);
            session.getTransaction().commit();
            session.close();
            return usuarios;
        } finally {
            LoadingManager.showLoading();
        }

    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuarios Objeto Usuarios con los nuevos datos.
     * @return El usuario actualizado.
     */
    @Override
    public Usuarios update(Usuarios usuarios) {
        LoadingManager.showLoading();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(usuarios);
                transaction.commit(); // Confirma los cambios
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close(); // Cierra la sesión de Hibernate
            }
            return usuarios;
        } finally {
            LoadingManager.hideLoading();
        }

    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id Identificador único del usuario a eliminar.
     * @return El usuario eliminado o null si no se encontró.
     */
    @Override
    public Usuarios deleteById(int id) {
        LoadingManager.showLoading();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Usuarios usuarios = session.get(Usuarios.class, id);
            if (usuarios != null) {
                session.delete(usuarios);
                session.getTransaction().commit();
            }
            session.close();
            return usuarios;
        } finally {
            LoadingManager.hideLoading();
        }

    }

    @Override
    public void activateUser(int id) {
        LoadingManager.showLoading();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                session.beginTransaction();
                session.createQuery("UPDATE Usuarios SET sessionActive = true WHERE id = :id")
                        .setParameter("id", id)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        } finally {
            LoadingManager.hideLoading();

        }

    }

    @Override
    public void deactivateUser(int id) {
        LoadingManager.showLoading();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                session.beginTransaction();
                session.createQuery("UPDATE Usuarios SET sessionActive = false WHERE id = :id")
                        .setParameter("id", id)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        } finally {
            LoadingManager.hideLoading();

        }

    }

    @Override
    public boolean isUserActive(int id) {
        LoadingManager.showLoading();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Usuarios user = session.get(Usuarios.class, id);
            session.close();
            return user != null && user.isSessionActive();
        } finally {
            LoadingManager.hideLoading();

        }

    }

    public Usuarios login(String identificador, String contrasena) {
        Transaction transaction = null;
        Usuarios usuario = null;
        LoadingManager.showLoading();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Consulta mejorada
            Query<Usuarios> query = session.createQuery(
                    "FROM Usuarios WHERE (correo = :identificador OR nickname = :identificador) AND contrasena = :contrasena",
                    Usuarios.class
            );
            query.setParameter("identificador", identificador);
            query.setParameter("contrasena", contrasena);

            usuario = query.uniqueResult();

            if (usuario != null) {
                usuario.setSessionActive(true);
                session.update(usuario);
                transaction.commit();
            }

            return usuario;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            LoadingManager.hideLoading();
        }
    }

}
