package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Usuarios;
import org.hibernate.Session;

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
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuarios> UsuarioList = session.createQuery("from Usuarios", Usuarios.class).list();
        session.close();
        return UsuarioList;
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id Identificador único del usuario.
     * @return Lista con el usuario encontrado (normalmente debería contener solo un elemento o estar vacía).
     */
    @Override
    public List<Usuarios> findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuarios> UsuarioList = session.createQuery("from Usuarios where id = :id", Usuarios.class)
                .setParameter("id", id)
                .list();
        session.close();
        return UsuarioList;
    }

    /**
     * Busca usuarios por su correo electrónico.
     *
     * @param correo Correo electrónico del usuario a buscar.
     * @return Lista de usuarios con el correo proporcionado.
     */
    @Override
    public List<Usuarios> findByCorreo(String correo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuarios> UsuarioList = session.createQuery("from Usuarios where correo = :correo", Usuarios.class)
                .setParameter("correo", correo)
                .list();
        session.close();
        return UsuarioList;
    }

    /**
     * Busca usuarios por su nickname.
     *
     * @param nickname Nombre de usuario a buscar.
     * @return Lista de usuarios con el nickname especificado.
     */
    @Override
    public List<Usuarios> findByNickname(String nickname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuarios> UsuarioList = session.createQuery("from Usuarios where nickname = :nickname", Usuarios.class)
                .setParameter("nickname", nickname)
                .list();
        session.close();
        return UsuarioList;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param usuarios Objeto Usuarios a guardar.
     * @return El usuario guardado con su ID generado.
     */
    @Override
    public Usuarios save(Usuarios usuarios) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(usuarios);
        session.getTransaction().commit();
        session.close();
        return usuarios;
    }

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuarios Objeto Usuarios con los nuevos datos.
     * @return El usuario actualizado.
     */
    @Override
    public Usuarios update(Usuarios usuarios) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(usuarios);
        session.getTransaction().commit();
        session.close();
        return usuarios;
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id Identificador único del usuario a eliminar.
     * @return El usuario eliminado o null si no se encontró.
     */
    @Override
    public Usuarios deleteById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Usuarios usuarios = session.get(Usuarios.class, id);
        if (usuarios != null) {
            session.delete(usuarios);
            session.getTransaction().commit();
        }
        session.close();
        return usuarios;
    }
}
