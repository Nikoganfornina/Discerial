package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Categoria;
import org.hibernate.Session;

import java.util.List;

public class ICategoriaImpl implements ICategoria {

    @Override
    public Categoria save(Categoria categoria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(categoria);
        session.getTransaction().commit();
        session.close();
        return categoria;
    }

    @Override
    public List<Categoria> findByNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Categoria WHERE nombre = :nombre", Categoria.class)
                    .setParameter("nombre", nombre)
                    .list();
        }
    }

    @Override
    public int getIdCategoriaPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT id FROM Categoria WHERE nombre = :nombre", Integer.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        }
    }
    @Override
    public List<Categoria> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Categoria", Categoria.class).list();
        }
    }

    @Override
    public Categoria findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Categoria WHERE id = :id", Categoria.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }
}
