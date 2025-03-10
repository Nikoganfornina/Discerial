package org.example.discerial.DAO;

import org.example.discerial.Util.HibernateUtil;
import org.example.discerial.entities.Categoria;
import org.example.discerial.entities.Pregunta;
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
    public List<Categoria> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Categoria> categoriaList = session.createQuery("from Categoria", Categoria.class).list();
        session.close();
        return categoriaList;
    }
}
