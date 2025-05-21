package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Usuarios;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            List<Usuarios> UsuarioList = session.createQuery("from Usuarios", Usuarios.class).list();
            session.close();
            return UsuarioList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Usuarios> UsuarioList = session.createQuery("from Usuarios where id = :id", Usuarios.class)
                    .setParameter("id", id)
                    .list();
            session.close();
            return UsuarioList;
        } finally {
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
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Usuarios> UsuarioList = session.createQuery("from Usuarios where correo = :correo", Usuarios.class)
                    .setParameter("correo", correo)
                    .list();
            session.close();
            return UsuarioList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Usuarios> UsuarioList = session.createQuery("from Usuarios where nickname = :nickname", Usuarios.class)
                    .setParameter("nickname", nickname)
                    .list();
            session.close();
            return UsuarioList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(usuarios);
            session.getTransaction().commit();
            session.close();
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Usuarios cerrarSesion(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Actualizar el estado de la sesión
            session.createQuery("UPDATE Usuarios SET sessionActive = false WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            // Obtener el usuario actualizado para devolverlo
            Usuarios usuario = session.get(Usuarios.class, id);
            transaction.commit();
            return usuario;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Usuarios currentUser() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuarios WHERE sessionActive = true", Usuarios.class)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void activateUser(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Primero desactivar todas las sesiones activas
            session.createQuery("UPDATE Usuarios SET sessionActive = false")
                    .executeUpdate();

            // Activar la sesión específica
            session.createQuery("UPDATE Usuarios SET sessionActive = true WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Usuarios login(String identificador, String contrasena) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Usuarios usuario = session.createQuery(
                            "FROM Usuarios WHERE (correo = :identificador OR nickname = :identificador) AND contrasena = :contrasena",
                            Usuarios.class)
                    .setParameter("identificador", identificador)
                    .setParameter("contrasena", contrasena)
                    .uniqueResult();

            if (usuario != null) {
                // Desactivar cualquier sesión previa
                session.createQuery("UPDATE Usuarios SET sessionActive = false")
                        .executeUpdate();

                // Activar la nueva sesión
                usuario.setSessionActive(true);
                session.update(usuario);
            }

            transaction.commit();
            return usuario;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Incrementa en 1 el contador de preguntas acertadas del usuario dado.
     */
    public void incrementAcertadas(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("UPDATE Usuarios SET preguntasAcertadas = preguntasAcertadas + 1 WHERE id = :id")
                    .setParameter("id", userId)
                    .executeUpdate();
            tx.commit();
        }
    }

    /**
     * Incrementa en 1 el contador de preguntas erróneas del usuario dado.
     */
    public void incrementErroneas(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("UPDATE Usuarios SET preguntasErroneas = preguntasErroneas + 1 WHERE id = :id")
                    .setParameter("id", userId)
                    .executeUpdate();
            tx.commit();
        }
    }


    public int getPreguntasAcertadas(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT preguntasAcertadas FROM Usuarios WHERE id = :id", Integer.class)
                    .setParameter("id", userId)
                    .uniqueResult();
        }
    }

    public int getPreguntasErroneas(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT preguntasErroneas FROM Usuarios WHERE id = :id", Integer.class)
                    .setParameter("id", userId)
                    .uniqueResult();
        }


    }

}